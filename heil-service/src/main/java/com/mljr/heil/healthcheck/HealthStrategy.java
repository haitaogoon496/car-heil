package com.mljr.heil.healthcheck;

import com.alibaba.fastjson.JSON;
import com.lyqc.base.common.Result;
import com.lyqc.base.page.PageForm;
import com.lyqc.base.page.PageVO;
import com.mljr.annotation.LogMonitor;
import com.mljr.redis.service.RedisUtil;
import com.mljr.heil.common.consts.HeilCode;
import com.mljr.heil.common.enums.HealthCheckIndexEnum;
import com.mljr.heil.entity.PdHealthReport;
import com.mljr.heil.form.PdHealthReportForm;
import com.mljr.heil.healthcheck.healthhandle.*;
import com.mljr.heil.mapper.PdHealthReportMapper;
import com.mljr.heil.service.HealthCheckService;
import com.mljr.heil.vo.healthCheck.HealthCheckMainPartVo;
import com.mljr.heil.vo.healthCheck.PdHealthReportVo;
import com.mljr.heil.voconvertor.PdHealthReportVoConvertor;
import com.mljr.util.TimeTools;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.*;

/**
 * @description: 健康检查接口对外暴露策略
 * @Date : 2018/7/23 11:00
 * @Author : 樊康康-(kangkang.fan@mljr.com)
 */
@Component
public class HealthStrategy implements Serializable{

    private static final Logger logger = LoggerFactory.getLogger(HealthStrategy.class);
    private String LOG_TITLE = "健康检查操作";

    /**
     * 接口，用于对象向上转型
     */
    HealthCheckService healthCheckService;

    @Autowired
    ProductBindRuleCheck productBindRuleCheck;

    @Autowired
    FeeRuleBindDealerCheck feeRuleBindDealerCheck;

    @Autowired
    ProdDealerMatchSFRCheck prodDealerMatchSFRCheck;

    @Autowired
    ProdDealerMatchRateCheck proDealerMatchRateRuleCheck;

    @Autowired
    ProductBindedSerFinMutexCheck productBindedSerFinMutexCheck;

    @Autowired
    private ProdDealerMatchGpsCheck prodDealerMatchGpsCheck;

    @Autowired
    private UselessDealerBindRuleCheck uselessDealerBindRuleCheck;

    @Autowired
    private UselessProductBindCheck uselessProductBindCheck;

    @Autowired
    private FeeRuleBindProductCheck feeRuleBindProductCheck;

    @Autowired
    private PdHealthReportMapper pdHealthReportMapper;

    Map<HealthCheckIndexEnum,List<HealthCheckMainPartVo>> healthCheckMap = new LinkedHashMap<>();

    @Autowired
    private RedisUtil redisUtil;

    /**
     * 不同健康检查策略向上转型初始化。
     * @param indexEnum
     */
    public void healthCheckMain(HealthCheckIndexEnum indexEnum){
        switch (indexEnum){
            case PRODUCT_NO_NECESSARY_RULE:
                healthCheckService = productBindRuleCheck;
                break;
            case PRODUCT_DEALER_UNMATCHED_SERFIN_RULE:
                healthCheckService = prodDealerMatchSFRCheck;
                break;
            case PRODUCT_DEALER_UNMATCHED_RATE_RULE:
                healthCheckService = proDealerMatchRateRuleCheck;
                break;
            case PRODUCT_SERFIN_MUTEX:
                healthCheckService = productBindedSerFinMutexCheck;
                break;
            case FEE_RULE_UNBOUND_DEALER:
                healthCheckService = feeRuleBindDealerCheck;
                break;
            case PRODUCT_DEALER_UNMATCHED_GPS_RULE:
                healthCheckService = prodDealerMatchGpsCheck;
                break;
            case USELESSDEALER_BINDCHECK:
                healthCheckService = uselessDealerBindRuleCheck;
                break;
            case USELESSPRODUCT_BINDCHECK:
                healthCheckService = uselessProductBindCheck;
                break;
            case FEE_RULE_UNBOUND_PRODUCT:
                healthCheckService = feeRuleBindProductCheck;
                break;
            default:break;
        }
        if(healthCheckService != null) {
            healthCheckService.init(healthCheckMap);
        }
    }

    /**
     * 执行每个策略的检查方法
     */
    public void doCheck(){
        if (healthCheckService != null) {
            healthCheckService.doCheck();
        }
    }

    /**
     * 供外部调用总接口，遍历策略并生成所有检查结果
     * @return
     */
    @Async
    @LogMonitor("健康检测数据处理")
    public  Result<Map<HealthCheckIndexEnum,List<HealthCheckMainPartVo>>> generateHealthResult(Integer userId){
        try {

            for(HealthCheckIndexEnum checkIndexEnum :HealthCheckIndexEnum.values()){
                this.healthCheckMain(checkIndexEnum);
                this.doCheck();
            }
            String healthCheckResultStr = JSON.toJSONString(healthCheckMap);
            PdHealthReport report = new PdHealthReport();
            report.setReportDetail(healthCheckResultStr);
            report.setReportTime(TimeTools.createNowTime());
            pdHealthReportMapper.insertSelective(report);
        } catch (Exception e) {
            logger.error("产品中心生成健康检查结果失败。",e);
        }finally {
            redisUtil.del("healthCheckDataFlag" + userId);
        }
        return Result.suc(healthCheckMap);
    }

    @LogMonitor("健康检查数据报告查询列表")
    public Result<PageVO<PdHealthReportVo>> loadRecords(PageForm<PdHealthReportForm> form) {
        List<PdHealthReportVo> reportVos = Collections.emptyList();
        int count = 0;
        try {
            List<PdHealthReport> reports = pdHealthReportMapper.pageQuery(form);
            reports.stream().forEach(per ->{
                per.setReportDetail("");
            });
            reportVos = new PdHealthReportVoConvertor().convertList(reports);
            count = pdHealthReportMapper.queryCount(form);
        }catch (Exception e){
            logger.error("{}loadRecords加载数据异常,form={}",LOG_TITLE, JSON.toJSON(form),e);
            return  Result.fail(HeilCode.E_500,"查询数据异常->{}"+e.getMessage());
        }
        return Result.suc(new PageVO<>(form.getDraw(),count,reportVos));
    }
    @LogMonitor("健康检查数据报告详情")
    public Result<PdHealthReportVo> queryRecord(Integer primaryKey) {
        PdHealthReportVo vo = null;
        try {
            PdHealthReport report = this.pdHealthReportMapper.selectByPrimaryKey(primaryKey);
            if(null != report ){
                vo = new PdHealthReportVoConvertor().convert(report);
            }else{
                return Result.failInEmptyRecord(vo);
            }
        } catch (Exception e) {
            logger.error("{}查询异常,id={}",LOG_TITLE,primaryKey,e);
            return Result.failInServer(vo);
        }
        return Result.suc(vo);
    }
}
