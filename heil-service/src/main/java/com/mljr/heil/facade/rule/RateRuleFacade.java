package com.mljr.heil.facade.rule;

import com.alibaba.fastjson.JSON;
import com.lyqc.base.common.Result;
import com.lyqc.base.enums.UserOperateLogConstant;
import com.lyqc.base.page.PageForm;
import com.lyqc.base.page.PageVO;
import com.lyqc.heil.enums.TagConstant;
import com.mljr.common.TwoTuple;
import com.mljr.heil.base.service.BaseFacade;
import com.mljr.annotation.LogMonitor;
import com.mljr.enums.LogTitleEnum;
import com.mljr.heil.component.CommonComponent;
import com.mljr.heil.component.EntityComponent;
import com.mljr.heil.component.UserOperateLogComponent;
import com.mljr.heil.entity.PdTag;
import com.mljr.heil.entity.RateRule;
import com.mljr.heil.form.RateRuleForm;
import com.mljr.heil.service.common.PdTagService;
import com.mljr.heil.service.rule.RateRuleService;
import com.mljr.heil.vo.rule.RateRuleVo;
import com.mljr.heil.voconvertor.rule.RateRuleVoConvertor;
import com.mljr.util.TimeTools;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.MessageFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;


/**
 * @description: 利率配置
 * @Date : 2018/2/11 10:19
 * @Author : lihaitao
 */
@Component
public class RateRuleFacade implements BaseFacade<RateRuleVo,RateRule, Integer, RateRuleForm> {
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
    private final String LOG_TITLE = LogTitleEnum.RATE_RULE.getName();
    private final UserOperateLogConstant.AuthModelEnumForProduct authModelEnum = UserOperateLogConstant.AuthModelEnumForProduct.RATE_RULE;
    @Autowired
    private RateRuleService rateRuleService;
    @Autowired
    private UserOperateLogComponent userOperateLogComponent;
    @Autowired
    private EntityComponent entityComponent;
    @Autowired
    private CommonComponent commonComponent;
    @Autowired
    private PdTagService pdTagService;

    @LogMonitor("利率规则配置")
    @Override
    public Result<PageVO<RateRuleVo>> loadRecords(PageForm<RateRuleForm> form) {
        List<RateRuleVo> voList = Collections.emptyList();
        List<RateRule> list = Collections.emptyList();
        int count = 0;
        try {
            list = this.rateRuleService.queryByPage(form);
            count = this.rateRuleService.queryCount(form);
            voList = new RateRuleVoConvertor().convertList(list);
            commonComponent.bindTags(TwoTuple.newInstance(TagConstant.BuzTypeEnum.RATE_RULE.getIndex(),voList),
                    rule -> Integer.valueOf(rule.getRuleSeq()),(rule, tags) -> rule.setTags(tags));
        } catch (Exception e) {
            LOGGER.error("{}loadRecords加载数据异常,form={}",LOG_TITLE, JSON.toJSON(form),e);
            return Result.failInServer(new PageVO<>(form.getDraw(),count,voList));
        }
        return Result.suc(new PageVO<>(form.getDraw(),count,voList));
    }

    @LogMonitor("利率规则配置")
    @Override
    public Result<RateRuleVo> queryRecord(Integer primaryKey) {
        RateRule record;
        RateRuleVo vo = null;
        try {
            record = this.rateRuleService.queryRecord(primaryKey);
            if (record == null) {
                return Result.failInEmptyRecord(null);
            }
            vo = new RateRuleVoConvertor().convert(record);
        } catch (Exception e) {
            LOGGER.error("{}查询异常,id={}",LOG_TITLE,primaryKey,e);
            return Result.failInServer(vo);
        }
        return Result.suc(vo);
    }

    @Override
    public Result<String> saveRecord(RateRule record) {
        return commonComponent.saveRecord(record,authModelEnum,LOG_TITLE,rateRuleService,entity -> {
            RateRule rule = (RateRule)entity;
            Date current = TimeTools.createNowTime();
            rule.setId(rule.getRuleSeq());
            rule.setUpdateTime(current);
            entityComponent.setCreateInfo(rule);
            if(rule.isInsert()){
                rule.setCreateTime(TimeTools.createNowTime());
            }
        });
    }

    @Override
    public Result<String> deleteRecord(Integer primaryKey) {
        PdTag pdTag = new PdTag();
        pdTag.setSourceId(primaryKey);
        pdTag.setBuzType(TagConstant.BuzTypeEnum.RATE_RULE.getIndex());
        return commonComponent.deleteRecord(primaryKey,LOG_TITLE,rateRuleService,(PK) -> {
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
