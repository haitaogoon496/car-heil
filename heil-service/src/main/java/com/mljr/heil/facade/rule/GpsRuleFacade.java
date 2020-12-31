package com.mljr.heil.facade.rule;

import com.alibaba.fastjson.JSON;
import com.lyqc.base.common.Result;
import com.lyqc.base.enums.CarGpsConstant;
import com.lyqc.base.enums.UserOperateLogConstant;
import com.lyqc.base.page.PageForm;
import com.lyqc.base.page.PageVO;
import com.lyqc.heil.enums.TagConstant;
import com.mljr.annotation.LogMonitor;
import com.mljr.common.TwoTuple;
import com.mljr.enums.LogTitleEnum;
import com.mljr.heil.base.service.BaseFacade;
import com.mljr.heil.component.CommonComponent;
import com.mljr.heil.component.UserOperateLogComponent;
import com.mljr.heil.entity.GpsRule;
import com.mljr.heil.entity.PdTag;
import com.mljr.heil.form.GpsRuleForm;
import com.mljr.heil.service.rule.GpsDealerService;
import com.mljr.heil.service.rule.GpsRuleService;
import com.mljr.heil.service.common.PdTagService;
import com.mljr.heil.vo.rule.GpsRuleVo;
import com.mljr.heil.voconvertor.rule.GpsRuleVoConvertor;
import com.mljr.util.CollectionsTools;
import com.mljr.util.StringTools;
import com.mljr.util.TimeTools;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.MessageFormat;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * @description: GPS规则 Facade
 * @Date : 上午11:37 2018/2/7
 * @Author : 石冬冬-Heil Hitler(dongdong.shi@mljr.com)
 */
@Component
public class GpsRuleFacade implements BaseFacade<GpsRuleVo,GpsRule,Integer,GpsRuleForm> {
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
    private final String LOG_TITLE = LogTitleEnum.GPS_RULE.getName();
    private final UserOperateLogConstant.AuthModelEnumForProduct authModelEnum = UserOperateLogConstant.AuthModelEnumForProduct.GPS_RULE;
    @Autowired
    private GpsRuleService gpsRuleService;
    @Autowired
    private UserOperateLogComponent userOperateLogComponent;
    @Autowired
    private GpsDealerService gpsDealerService;
    @Autowired
    private CommonComponent commonComponent;
    @Autowired
    private PdTagService pdTagService;

    @LogMonitor("GPS规则配置")
    @Override
    public Result<PageVO<GpsRuleVo>> loadRecords(PageForm<GpsRuleForm> form) {
        List<GpsRuleVo> voList = Collections.emptyList();
        int count = 0;
        try {
            List<GpsRule> queryList = this.gpsRuleService.queryByPage(form);
            count = this.gpsRuleService.queryCount(form);
            voList = new GpsRuleVoConvertor().convertList(queryList);
            formatGpsRuleVo(voList);

            commonComponent.bindTags(TwoTuple.newInstance(TagConstant.BuzTypeEnum.GPS_RULE.getIndex(),voList),
                    rule -> Integer.valueOf(rule.getRuleSeq()),(rule, tags) -> rule.setTags(tags));
        } catch (Exception e) {
            LOGGER.error("{}loadRecords加载数据异常,form={}",LOG_TITLE, JSON.toJSON(form),e);
            return Result.failInServer(new PageVO<>(form.getDraw(),count,voList));
        }
        return Result.suc(new PageVO<>(form.getDraw(),count,voList));
    }

    /**
     * 格式化gpsVo数据
     * @param gpsRuleVos
     */
    public void formatGpsRuleVo(List<GpsRuleVo> gpsRuleVos) {
        if (CollectionsTools.isNotEmpty(gpsRuleVos)){
            commonComponent.bindConArgs(TwoTuple.newInstance(Arrays.asList(37), gpsRuleVos),
                    gpsRuleVo -> gpsRuleVo.getLevel(),
                    (gpsRuleVo, levelDesc) -> {
                        if (StringTools.isNotEmpty(gpsRuleVo.getGpsPro())){
                            gpsRuleVo.setGpsProDesc(CarGpsConstant.GpsProEnum.getNameByIndex(Integer.valueOf(gpsRuleVo.getGpsPro())));
                        }else {
                            gpsRuleVo.setGpsProDesc("");
                        }
                        gpsRuleVo.setLevelDesc(levelDesc);
                    });
        }
    }

    @LogMonitor("GPS规则配置")
    @Override
    public Result<GpsRuleVo> queryRecord(Integer primaryKey) {
        GpsRuleVo vo = null;
        try {
            GpsRule gpsRule = this.gpsRuleService.queryRecord(primaryKey);
            if(null != gpsRule ){
                vo = new GpsRuleVoConvertor().convert(gpsRule);
            }else{
                return Result.failInEmptyRecord(vo);
            }
        } catch (Exception e) {
            LOGGER.error("{}查询异常,id={}",LOG_TITLE,primaryKey,e);
            return Result.failInServer(vo);
        }
        return Result.suc(vo);
    }

    @Override
    public Result<String> saveRecord(GpsRule record) {
        return commonComponent.saveRecord(record,authModelEnum,LOG_TITLE,gpsRuleService,entity -> {
            GpsRule rule = (GpsRule)entity;
            Date current = TimeTools.createNowTime();
            rule.setId(rule.getRuleSeq());
            rule.setUpdateTime(current);
            if(rule.isInsert()){
                rule.setCreateTime(current);
            }
        });
    }

    @Override
    public Result<String> deleteRecord(Integer primaryKey) {
        PdTag pdTag = new PdTag();
        pdTag.setSourceId(primaryKey);
        pdTag.setBuzType(TagConstant.BuzTypeEnum.GPS_RULE.getIndex());
        return commonComponent.deleteRecord(primaryKey,LOG_TITLE,gpsRuleService,(PK) -> {
            gpsDealerService.deleteByRuleId(primaryKey);
            pdTagService.deleteRecord(pdTag);
            saveLog(UserOperateLogConstant.AuthTypeEnum.DELETE, MessageFormat.format("删除primaryKey={0}", PK));
        });
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
