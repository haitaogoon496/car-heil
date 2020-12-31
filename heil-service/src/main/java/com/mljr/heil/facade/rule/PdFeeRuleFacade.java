package com.mljr.heil.facade.rule;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lyqc.base.common.Result;
import com.lyqc.base.enums.ProductConstant;
import com.lyqc.base.enums.RemoteEnum;
import com.lyqc.base.enums.UserOperateLogConstant;
import com.lyqc.base.page.PageForm;
import com.lyqc.base.page.PageVO;
import com.lyqc.heil.enums.TagConstant;
import com.mljr.annotation.LogMonitor;
import com.mljr.common.TwoTuple;
import com.mljr.enums.LogTitleEnum;
import com.mljr.heil.base.service.BaseFacade;
import com.mljr.heil.component.CommonComponent;
import com.mljr.heil.component.EntityComponent;
import com.mljr.heil.component.UserOperateLogComponent;
import com.mljr.heil.entity.PdFeeRule;
import com.mljr.heil.entity.PdTag;
import com.mljr.heil.form.PdFeeRuleForm;
import com.mljr.heil.form.RuleStatusForm;
import com.mljr.heil.service.common.PdTagService;
import com.mljr.heil.service.rule.PdFeeDealerService;
import com.mljr.heil.service.rule.PdFeeProductService;
import com.mljr.heil.service.rule.PdFeeRuleService;
import com.mljr.heil.vo.rule.PdFeeRuleVo;
import com.mljr.heil.voconvertor.rule.PdFeeRuleVoConvertor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.text.MessageFormat;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * @description: 人身保险费规则 Facade
 * @Date : 上午11:37 2018/2/7
 * @Author : 石冬冬-Heil Hitler(dongdong.shi@mljr.com)
 */
@Component
public class PdFeeRuleFacade implements BaseFacade<PdFeeRuleVo, PdFeeRule, Integer, PdFeeRuleForm> {
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
    private final String LOG_TITLE = LogTitleEnum.LIFE_INSURANCE.getName();
    private final UserOperateLogConstant.AuthModelEnumForProduct authModelEnum = UserOperateLogConstant.AuthModelEnumForProduct.LIFE_INSURANSE;

    @Autowired
    private PdFeeRuleService pdFeeRuleService;
    @Autowired
    private PdFeeDealerService pdFeeDealerService;
    @Autowired
    private UserOperateLogComponent userOperateLogComponent;
    @Autowired
    private EntityComponent entityComponent;
    @Autowired
    private CommonComponent commonComponent;
    @Autowired
    private PdFeeProductService pdFeeProductService;
    @Autowired
    private PdTagService pdTagService;

    @LogMonitor("人身险/保险/贴息/续保/盗抢相关费用规则查询")
    @Override
    public Result<PageVO<PdFeeRuleVo>> loadRecords(PageForm<PdFeeRuleForm> form) {
        if (form.getForm() == null || form.getForm().getClassify() == null) {
            return Result.fail(RemoteEnum.WARN_EMPTY_RECORD, "费用分类不能为空");
        }
        TagConstant.BuzTypeEnum buzTypeEnum = TagConstant.BuzTypeEnum.getTagTypeByClassify(form.getForm().getClassify());
        LOGGER.info("规则查询----规则类型->{}", buzTypeEnum.getName());
        form.getForm().setBuzType((byte)buzTypeEnum.getIndex() );
        List<PdFeeRuleVo> voList = Collections.emptyList();
        int count = 0;
        try {
            List<PdFeeRule> queryList = this.pdFeeRuleService.queryByPage(form);
            count = this.pdFeeRuleService.queryCount(form);
            voList = new PdFeeRuleVoConvertor().convertList(queryList);
            commonComponent.bindTags(TwoTuple.newInstance(buzTypeEnum.getIndex(),voList),
                    rule -> Integer.valueOf(rule.getId()),(rule, tags) -> rule.setTags(tags));
        } catch (Exception e) {
            LOGGER.error("{}loadRecords加载数据异常,form={}", LOG_TITLE, JSON.toJSON(form), e);
            return Result.failInServer(new PageVO<>(form.getDraw(), count, voList));
        }
        return Result.suc(new PageVO<>(form.getDraw(), count, voList));
    }



    @LogMonitor("人身险/保险/贴息/续保/盗抢相关费用规则根据主键查询")
    @Override
    public Result<PdFeeRuleVo> queryRecord(Integer primaryKey) {
        PdFeeRuleVo vo = null;
        try {
            PdFeeRule feeRule = this.pdFeeRuleService.queryRecord(primaryKey);
            if (null != feeRule) {
                vo = new PdFeeRuleVoConvertor().convert(feeRule);

            } else {
                return Result.failInEmptyRecord(vo);
            }
        } catch (Exception e) {
            LOGGER.error("{}查询异常,id={}", LOG_TITLE, primaryKey, e);
            return Result.failInServer(vo);
        }
        return Result.suc(vo);
    }

    @Override
    public Result<String> saveRecord(PdFeeRule record) {
        return commonComponent.saveRecord(record, authModelEnum, LOG_TITLE, pdFeeRuleService, entity -> {
            PdFeeRule pdFeeRule = (PdFeeRule) entity;
            entityComponent.setCreateInfo(pdFeeRule);
            if (!record.isInsert()) {
                if (pdFeeRule.getIsAllProduct() != null && pdFeeRule.getIsAllProduct() == 1) {
                    pdFeeProductService.deleteByRuleId(record.getId());
                }
                if (pdFeeRule.getIsAllDealer() != null && pdFeeRule.getIsAllDealer() == 1) {
                    pdFeeDealerService.deleteByRuleId(record.getId());
                }
            } else if (record.isInsert()) {
                if (Objects.equals(pdFeeRule.getClassify(), ProductConstant.FeeRuleClassifyEnum.CAR_INSURANCE.getIndex()) ||
                        Objects.equals(pdFeeRule.getClassify(), ProductConstant.FeeRuleClassifyEnum.CAR_INTEREST.getIndex())) {
                    record.setRateValue(BigDecimal.ZERO);
                }
            }
        });
    }

    @Override
    public Result<String> deleteRecord(Integer primaryKey) {
        return commonComponent.deleteRecord(primaryKey, LOG_TITLE, pdFeeRuleService, (PK) -> {
            pdFeeDealerService.deleteByRuleId(primaryKey);
            pdFeeProductService.deleteByRuleId(primaryKey);
            pdTagService.deleteRecord(PdTag.builder().buzType(TagConstant.BuzTypeEnum.INSURANCE.getIndex()).sourceId(primaryKey).build());
            saveLog(UserOperateLogConstant.AuthTypeEnum.DELETE, MessageFormat.format("删除primaryKey={0}", PK));
        });
    }

    /**
     * 保存操作日志
     *
     * @param authTypeEnum
     * @param authDetail
     */
    public void saveLog(UserOperateLogConstant.AuthTypeEnum authTypeEnum, String authDetail) {
        this.userOperateLogComponent.log(log -> {
            log.setAuthDetail(authDetail);
            log.setAuthModel(authModelEnum);
            log.setAuthType(authTypeEnum);
        });
    }

    /**
     * 通用修改规则状态接口
     * @param record
     * @return
     */
    @LogMonitor(value = "【修改规则状态】")
    public Result<String> modifyStatus(RuleStatusForm record) {
        try {
            pdFeeRuleService.modifyStatus(record);
            saveLog(UserOperateLogConstant.AuthTypeEnum.UPDATE, MessageFormat.format("修改规则状态param={0}", JSONObject.toJSONString(record)));
        } catch (Exception e) {
            LOGGER.error("{}modifyStatus 修改规则状态异常,form={}", LOG_TITLE, JSON.toJSON(record), e);
            return Result.failInServer("数据保存异常");
        }
        return Result.suc();
    }
}
