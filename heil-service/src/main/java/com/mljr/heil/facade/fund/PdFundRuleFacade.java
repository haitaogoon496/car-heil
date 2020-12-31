package com.mljr.heil.facade.fund;

import com.alibaba.fastjson.JSON;
import com.lyqc.base.common.Result;
import com.lyqc.base.enums.UserOperateLogConstant;
import com.lyqc.base.page.PageForm;
import com.lyqc.base.page.PageVO;
import com.mljr.annotation.LogMonitor;
import com.mljr.enums.LogTitleEnum;
import com.mljr.heil.base.service.BaseFacade;
import com.mljr.redis.service.RedisUtil;
import com.mljr.heil.common.consts.HeilCode;
import com.mljr.heil.common.util.StringUtils;
import com.mljr.heil.common.util.ValidateUtils;
import com.mljr.heil.component.CommonComponent;
import com.mljr.heil.component.EntityComponent;
import com.mljr.heil.component.UserOperateLogComponent;
import com.mljr.heil.entity.fund.Fund;
import com.mljr.heil.entity.fund.PdFundRule;
import com.mljr.heil.entity.fund.PdFundRuleProp;
import com.mljr.heil.form.fund.FundForm;
import com.mljr.heil.form.fund.PdFundRuleCensusForm;
import com.mljr.heil.form.fund.PdFundRuleForm;
import com.mljr.heil.form.fund.PdFundRulePropForm;
import com.mljr.heil.mapper.fund.PdFundRuleCensusMapper;
import com.mljr.heil.service.fund.FundService;
import com.mljr.heil.service.fund.PdFundRulePropService;
import com.mljr.heil.service.fund.PdFundRuleService;
import com.mljr.heil.vo.fund.FundVo;
import com.mljr.heil.vo.fund.PdFundRuleVo;
import com.mljr.heil.voconvertor.fund.FundVoConvertor;
import com.mljr.heil.voconvertor.fund.PdFundRuleVoConvertor;
import com.mljr.util.StringTools;
import com.mljr.util.TimeTools;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.text.MessageFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;


/**
 * @description:资金方规则处理facade
 * @Date : 2018/6/13 11:16
 * @Author : lihaitao
 */
@Component
public class PdFundRuleFacade implements BaseFacade<PdFundRuleVo,PdFundRule, Integer, PdFundRuleForm> {
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
    private final String LOG_TITLE = LogTitleEnum.PD_FUND_RULE.getName();
    private final UserOperateLogConstant.AuthModelEnumForProduct authModelEnum = UserOperateLogConstant.AuthModelEnumForProduct.PD_FUND_RULE;
    @Autowired
    private PdFundRuleService pdFundRuleService;
    @Autowired
    private FundService fundService;
    @Autowired
    private PdFundRulePropService pdFundRulePropService;
    @Autowired
    private PdFundRuleCensusMapper pdFundRuleCensusMapper;
    @Autowired
    private UserOperateLogComponent userOperateLogComponent;
    @Autowired
    private EntityComponent entityComponent;
    @Autowired
    private CommonComponent commonComponent;
    @Autowired
    private RedisUtil redisUtil;

    @LogMonitor("资金方准入规则")
    @Override
    public Result<PageVO<PdFundRuleVo>> loadRecords(PageForm<PdFundRuleForm> form) {
        List<PdFundRuleVo> voList = Collections.emptyList();
        List<PdFundRule> list = Collections.emptyList();
        int count = 0;
        try {
            list = this.pdFundRuleService.queryByPage(form);
            if (CollectionUtils.isEmpty(list)) {
                return Result.failInEmptyRecord(null);
            }
            count = this.pdFundRuleService.queryCount(form);
            voList = new PdFundRuleVoConvertor().convertList(list);
            voList.forEach(vo->{
                if (!StringUtils.isEmpty(vo.getFundId())) {
                    Fund fund = fundService.queryRecord(Integer.parseInt(vo.getFundId()));
                    vo.setFundName(fund == null ? "" : fund.getFundName());
                }
            });

        } catch (Exception e) {
            LOGGER.error("{}loadRecords加载数据异常,form={}",LOG_TITLE, JSON.toJSON(form),e);
            return Result.failInServer(new PageVO<>(form.getDraw(),count,voList));
        }
        return Result.suc(new PageVO<>(form.getDraw(),count,voList));
    }

    @LogMonitor("资金方准入规则")
    @Override
    public Result<PdFundRuleVo> queryRecord(Integer primaryKey) {
        PdFundRule record;
        PdFundRuleVo vo = null;
        try {
            record = this.pdFundRuleService.queryRecord(primaryKey);
            if (record == null) {
                return Result.failInEmptyRecord(null);
            }
            vo = new PdFundRuleVoConvertor().convert(record);
            Fund fund = fundService.queryRecord(Integer.parseInt(vo.getId()));
            vo.setFundName(fund == null ? "" : fund.getFundName());
        } catch (Exception e) {
            LOGGER.error("{}查询异常,id={}",LOG_TITLE,primaryKey,e);
            return Result.failInServer(vo);
        }
        return Result.suc(vo);
    }

    @Override
    public Result<String> saveRecord(PdFundRule record) {
        return commonComponent.saveRecord(record,authModelEnum,LOG_TITLE,pdFundRuleService,entity -> {
            PdFundRule rule = (PdFundRule)entity;
            Date current = TimeTools.createNowTime();
            rule.setModifyTime(current);
            String ruleNo = record.getRuleNo();
            entityComponent.setCreateInfo(rule,user -> {
                if(rule.isInsert()){
                    record.setCreateUserName(user.getTrueName());
                }
                rule.setModifyUserName(user.getTrueName());
            });
            if(rule.isInsert()){
                // 默认无效
                rule.setStatus(new Byte("0"));
                rule.setCreateTime(current);
                if(StringTools.isEmpty(ruleNo)){
                    String fundNo = record.getFundNo();
                    String redisKey = redisUtil.getKeyWithSystemCode("fundNo_"+fundNo);
                    redisUtil.incr(redisKey.getBytes(),0);
                    long incrValue = redisUtil.getIncrValue(redisKey);
                    String suffix = String.format("%1$04d",incrValue);
                    rule.setRuleNo(fundNo + TimeTools.format(TimeTools.createNowTime(),"yyMMdd") + suffix);
                }
            }
        });
    }

    @Override
    public Result<String> deleteRecord(Integer primaryKey) {
        ValidateUtils.notNull(primaryKey, HeilCode.E_400, "删除主键不能为空");
        return commonComponent.deleteRecord(primaryKey,LOG_TITLE,pdFundRuleService,(PK) -> {
            // 删除对应的规则属性
            pdFundRulePropService.deleteByFundRuleId(primaryKey);
            // 删除对应户籍数据
            pdFundRuleCensusMapper.deleteByForm(new PdFundRuleCensusForm(primaryKey));
            saveLog(UserOperateLogConstant.AuthTypeEnum.DELETE, MessageFormat.format("删除primaryKey={0}", PK));
        });
    }


    @LogMonitor("资金方准入规则")
    public Result<String> modifyStatus(PdFundRule record) {
        LOGGER.info("状态更新 - 参数:{}", JSON.toJSON(record));
        ValidateUtils.notNull(record, HeilCode.E_400,"参数主键不能为空");
        ValidateUtils.notNull(record.getId(), HeilCode.E_400, "参数主键错误");
        // 如果启用改规则时，需要配置子属性
        if (record.getStatus() != null && record.getStatus() == new Byte("1").byteValue()) {
            PdFundRulePropForm form = new PdFundRulePropForm();
            form.setFundRuleId(record.getId());
            List<PdFundRuleProp> pdFundRuleProps = pdFundRulePropService.queryByPage(new PageForm<>(false,form));
            if (CollectionUtils.isEmpty(pdFundRuleProps)) {
                return Result.fail(HeilCode.E_500, "当前记录未配置属性规则，请先配置");
            }
        }

        int i = this.pdFundRuleService.updateByPrimaryKeySelective(record);
        ValidateUtils.notEquals(0, i, HeilCode.E_400, "更新状态失败");
        this.saveLog(UserOperateLogConstant.AuthTypeEnum.UPDATE, MessageFormat.format("状态更新param={0}",JSON.toJSON(record)));
        return Result.suc();
    }

    /**
     * 获取资金方
     * @param fundForm
     * @return
     */
    public Result<List<FundVo>> getFundList(FundForm fundForm) {
        LOGGER.info("{} - getFundList 获取资金方列表 参数:{}", LOG_TITLE, JSON.toJSON(fundForm));
        fundForm.setStatus(1);
        List<Fund> fundList = fundService.queryByPage(new PageForm<>(false, fundForm));
        if (CollectionUtils.isEmpty(fundList)) {
            return Result.failInEmptyRecord(null);
        }
        List<FundVo> fundVoList = new FundVoConvertor().convertList(fundList);
        return Result.suc(fundVoList);
    }

    /**
     * 保存操作日志
     * @param authTypeEnum
     * @param authDetail
     */
    public void saveLog(UserOperateLogConstant.AuthTypeEnum authTypeEnum,String authDetail){
        this.userOperateLogComponent.log(log -> {
            log.setAuthDetail(authDetail);
            log.setAuthModel(authModelEnum);
            log.setAuthType(authTypeEnum);
        });
    }
}
