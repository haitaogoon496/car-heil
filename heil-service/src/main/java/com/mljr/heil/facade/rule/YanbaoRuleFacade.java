package com.mljr.heil.facade.rule;

import com.alibaba.fastjson.JSON;
import com.lyqc.base.common.Result;
import com.lyqc.base.enums.RemoteEnum;
import com.lyqc.base.enums.UserOperateLogConstant;
import com.lyqc.base.page.PageForm;
import com.lyqc.base.page.PageVO;
import com.lyqc.heil.enums.TagConstant;
import com.mljr.common.TwoTuple;
import com.mljr.heil.base.service.BaseFacade;
import com.mljr.annotation.LogMonitor;
import com.mljr.enums.LogTitleEnum;
import com.mljr.heil.common.exception.BizException;
import com.mljr.heil.component.CommonComponent;
import com.mljr.heil.component.UserOperateLogComponent;
import com.mljr.heil.entity.YanbaoRule;
import com.mljr.heil.entity.YanbaoTc;
import com.mljr.heil.form.YanbaoRuleForm;
import com.mljr.heil.form.YanbaoTcForm;
import com.mljr.heil.service.rule.YanbaoDealerService;
import com.mljr.heil.service.rule.YanbaoRuleService;
import com.mljr.heil.service.rule.YanbaoTcService;
import com.mljr.heil.vo.rule.YanbaoRuleVo;
import com.mljr.heil.voconvertor.rule.YanbaoRuleVoConvertor;
import com.mljr.util.CollectionsTools;
import com.mljr.util.TimeTools;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.text.MessageFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @description: 第二/三年保险费规则 门面
 * @Date : 上午11:37 2018/2/7
 * @Author : 石冬冬-Heil Hitler(dongdong.shi@mljr.com)
 */
@Component
public class YanbaoRuleFacade implements BaseFacade<YanbaoRuleVo,YanbaoRule,Integer,YanbaoRuleForm> {
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
    private final String LOG_TITLE = LogTitleEnum.SECOND_INSURANCE_RULE.getName();
    private final UserOperateLogConstant.AuthModelEnumForProduct authModelEnum = UserOperateLogConstant.AuthModelEnumForProduct.LIFE_INSURANSE;

    @Autowired
    private YanbaoRuleService yanbaoRuleService;
    @Autowired
    private YanbaoDealerService yanbaoDealerService;
    @Autowired
    private YanbaoTcService yanbaoTcService;
    @Autowired
    private UserOperateLogComponent userOperateLogComponent;
    @Autowired
    private CommonComponent commonComponent;

    @LogMonitor("保险费规则配置")
    @Override
    public Result<PageVO<YanbaoRuleVo>> loadRecords(PageForm<YanbaoRuleForm> form) {
        List<YanbaoRuleVo> voList = Collections.emptyList();
        int count = 0;
        if (form.getForm() == null || form.getForm().getClassify() == null) {
            return Result.fail(RemoteEnum.ERROR_WITH_EMPTY_PARAM, "参数不能为空");
        }
        TagConstant.BuzTypeEnum buzTypeEnum = null;

        try {
            if (form.getForm() != null) {
                if (form.getForm().getClassify() == 2) {
                    buzTypeEnum = TagConstant.BuzTypeEnum.INSURANSE_SECOND_YEAR;
                    form.getForm().setBuzType(new Byte("4"));
                } else if (form.getForm().getClassify() == 3) {
                    buzTypeEnum = TagConstant.BuzTypeEnum.INSURANSE_THIRD_YEAR;
                    form.getForm().setBuzType(new Byte("5"));
                }
            }
            List<YanbaoRule> queryList = this.yanbaoRuleService.queryByPage(form);
            count = this.yanbaoRuleService.queryCount(form);
            voList = new YanbaoRuleVoConvertor().convertList(queryList);
            formatYanbaoRules(queryList, voList);
            commonComponent.bindTags(TwoTuple.newInstance(buzTypeEnum.getIndex(),voList),
                    rule -> Integer.valueOf(rule.getRuleSeq()),(rule, tags) -> rule.setTags(tags));
        } catch (Exception e) {
            LOGGER.error("{}loadRecords加载数据异常,form={}",LOG_TITLE, JSON.toJSON(form),e);
            return Result.failInServer(new PageVO<>(form.getDraw(),count,voList));
        }
        return Result.suc(new PageVO<>(form.getDraw(),count,voList));
    }
    /**
     * @description:封装第二/三年保险费用规则
     * @Date : 2018/10/12 15:29
     * @Author : lihaitao
     */
    public void formatYanbaoRules(List<YanbaoRule> queryList,List<YanbaoRuleVo> yanbaoRuleVos) {
        if (CollectionsTools.isNotEmpty(queryList)) {
            List<Integer> yanbaoList = queryList.stream().map(YanbaoRule::getRuleSeq).collect(Collectors.toList());
            YanbaoTcForm yanbaoTcForm = new YanbaoTcForm();
            yanbaoTcForm.setRuleSeqList(yanbaoList);
            //根据规则Id查找
            List<YanbaoTc> yanbaoTcList = yanbaoTcService.queryList(yanbaoTcForm);
            Map<Integer, List<YanbaoTc>> yanbaoMap = yanbaoTcList.stream().collect(Collectors.groupingBy(YanbaoTc::getRuleSeq));
            yanbaoRuleVos.forEach(vo -> vo.setTcList(Optional.ofNullable(yanbaoMap.get(Integer.valueOf(vo.getRuleSeq()))).orElse(Collections.emptyList())));
        }
    }

    @LogMonitor("保险费规则配置")
    @Override
    public Result<YanbaoRuleVo> queryRecord(Integer primaryKey) {
        YanbaoRuleVo YanbaoRuleVo = null;
        try {
            YanbaoRule YanbaoRule = this.yanbaoRuleService.queryRecord(primaryKey);
            if (YanbaoRule == null) {
                return Result.failInEmptyRecord(null);
            }
            YanbaoRuleVo = new YanbaoRuleVoConvertor().convert(YanbaoRule);
            List<YanbaoTc> tcList = this.yanbaoTcService.queryList(new YanbaoTcForm(primaryKey));
            YanbaoRuleVo.setTcList(tcList);
        } catch (Exception e) {
            LOGGER.error("{}查询异常,id={}",LOG_TITLE,primaryKey,e);
            return Result.failInServer(YanbaoRuleVo);
        }
        return Result.suc(YanbaoRuleVo);
    }

    @Override
    @Transactional(rollbackFor = {BizException.class})
    public Result<String> saveRecord(YanbaoRule record) {
        return commonComponent.saveRecord(record,authModelEnum,LOG_TITLE,yanbaoRuleService,
        entity -> {
            YanbaoRule rule = (YanbaoRule)entity;
            rule.setId(record.getRuleSeq());
            Date current = TimeTools.createNowTime();
            rule.setUpdateTime(current);
            if(rule.isInsert()){
                rule.setCreateTime(current);
            }
        },entity -> {
            YanbaoRule rule = (YanbaoRule)entity;
            final Integer ruleId = rule.getRuleSeq();
            rule.setId(ruleId);
            if(!rule.isInsert()){
                yanbaoTcService.deleteByRuleSeq(ruleId);
            }
            List<YanbaoTc> tcList = rule.getTcList();
            if (CollectionsTools.isNotEmpty(tcList)) {
                tcList.forEach(tc -> tc.setRuleSeq(ruleId));
                yanbaoTcService.insertBatch(tcList);
            }

            //如果是全量绑定门店则删除绑定关系
            if(!rule.isInsert() && rule.getIsAllDealer() != null && rule.getIsAllDealer() == 1) {
               yanbaoDealerService.deleteByRuleId(ruleId);
            }
        });
    }

    @Override
    public Result<String> deleteRecord(Integer primaryKey) {
        return commonComponent.deleteRecord(primaryKey,LOG_TITLE,yanbaoRuleService,(PK) -> {
            yanbaoTcService.deleteByRuleSeq(primaryKey);
            yanbaoDealerService.deleteByRuleId(primaryKey);
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
