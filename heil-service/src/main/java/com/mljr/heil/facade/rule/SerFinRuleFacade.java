package com.mljr.heil.facade.rule;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.lyqc.base.common.Result;
import com.lyqc.base.enums.SerFinConstant;
import com.lyqc.base.enums.UserOperateLogConstant;
import com.lyqc.base.page.PageForm;
import com.lyqc.base.page.PageVO;
import com.lyqc.heil.enums.TagConstant;
import com.mljr.annotation.LogMonitor;
import com.mljr.common.TwoTuple;
import com.mljr.enums.LogTitleEnum;
import com.mljr.heil.base.service.BaseFacade;
import com.mljr.heil.common.consts.CommonConstant;
import com.mljr.heil.common.consts.HeilCode;
import com.mljr.heil.common.util.StringUtils;
import com.mljr.heil.common.util.ValidateUtils;
import com.mljr.heil.component.CommonComponent;
import com.mljr.heil.component.EntityComponent;
import com.mljr.heil.component.UserOperateLogComponent;
import com.mljr.heil.entity.PdTag;
import com.mljr.heil.entity.SerFinRate;
import com.mljr.heil.entity.SerFinRule;
import com.mljr.heil.form.SerFinDealerForm;
import com.mljr.heil.form.SerFinRateForm;
import com.mljr.heil.form.SerFinRuleForm;
import com.mljr.heil.mapper.SerFinRateMapper;
import com.mljr.heil.service.common.PdTagService;
import com.mljr.heil.service.rule.SerFinDealerService;
import com.mljr.heil.service.rule.SerFinRuleService;
import com.mljr.heil.vo.rule.SerFinRateVo;
import com.mljr.heil.vo.rule.SerFinRuleVo;
import com.mljr.heil.voconvertor.rule.SerFinRateVoConvertor;
import com.mljr.heil.voconvertor.rule.SerFinRuleVoConvertor;
import com.mljr.util.CollectionsTools;
import com.mljr.util.TimeTools;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @description: 平台费规则配置
 * @Date : 2018/2/11 17:11
 * @Author : lihaitao
 */
@Component
public class SerFinRuleFacade implements BaseFacade<SerFinRuleVo,SerFinRule,Integer,SerFinRuleForm> {
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
    private final String LOG_TITLE = LogTitleEnum.SER_FIN_RULE.getName();
    private final UserOperateLogConstant.AuthModelEnumForProduct authModelEnum = UserOperateLogConstant.AuthModelEnumForProduct.SER_FIN_RULE;

    @Autowired
    private SerFinRuleService serFinRuleService;
    @Resource
    private SerFinDealerService serFinDealerService;
    @Autowired
    private UserOperateLogComponent userOperateLogComponent;
    @Autowired
    private EntityComponent entityComponent;
    @Autowired
    private SerFinRateMapper serFinRateMapper;
    @Autowired
    private CommonComponent commonComponent;
    @Autowired
    private PdTagService pdTagService;

    @LogMonitor("平台费规则配置")
    @Override
    public Result<PageVO<SerFinRuleVo>> loadRecords(PageForm<SerFinRuleForm> form) {
        List<SerFinRuleVo> voList = Collections.emptyList();
        int count = 0;
        try {
            List<SerFinRule> queryList = this.serFinRuleService.queryByPage(form);
            count = this.serFinRuleService.queryCount(form);
            voList = new SerFinRuleVoConvertor().convertList(queryList);
            formatSerFinRule(queryList, voList);
            commonComponent.bindTags(TwoTuple.newInstance(TagConstant.BuzTypeEnum.SER_FIN_RULE.getIndex(),voList),
                    rule -> Integer.valueOf(rule.getRuleSeq()),(rule, tags) -> rule.setTags(tags));
        } catch (Exception e) {
            LOGGER.error("{}loadRecords加载数据异常,form={}",LOG_TITLE, JSON.toJSON(form),e);
            return Result.failInServer(new PageVO<>(form.getDraw(),count,voList));
        }
        return Result.suc(new PageVO<>(form.getDraw(),count,voList));
    }

    public void formatSerFinRule(List<SerFinRule> queryList,List<SerFinRuleVo> voList) {
        if (CollectionsTools.isNotEmpty(queryList)){
            List<Integer> serFinList = queryList.stream().map(SerFinRule::getRuleSeq).collect(Collectors.toList());
            SerFinRateForm serFinRateForm = new SerFinRateForm();
            serFinRateForm.setRuleSeqList(serFinList);
            List<SerFinRate> serFinRateList = serFinRateMapper.queryList(serFinRateForm);
            List<SerFinRateVo> serFinRateVos = new SerFinRateVoConvertor().convertList(serFinRateList);
            Map<String,List<SerFinRateVo>> serFinMap = serFinRateVos.stream().collect(Collectors.groupingBy(SerFinRateVo::getRuleSeq));
            voList.forEach(serFinRuleVo -> {
                List<SerFinRateVo> serFinRateVosTmp = serFinMap.get(serFinRuleVo.getRuleSeq());
                if (!CollectionsTools.isEmpty(serFinRateVosTmp)) {
                    ;List<String> strList = new ArrayList<String>();
                    serFinRateVosTmp.forEach(vo -> {
                        StringBuffer all = new StringBuffer();
                        all.append(StringUtils.isEmpty(vo.getSerFinRate()) ? "--" : vo.getSerFinRate());
                        all.append("/");
                        all.append(StringUtils.isEmpty(vo.getBasicRate()) ? "--" : vo.getBasicRate());
                        all.append("/");
                        all.append(StringUtils.isEmpty(vo.getSerFinRebateRate()) ? "--" : vo.getSerFinRebateRate());
                        all.append("/");
                        all.append(StringUtils.isEmpty(vo.getHighRate()) ? "--" : vo.getHighRate());
                        strList.add(all.toString());
                    });
                    serFinRuleVo.setRateList(strList);
                }
                serFinRuleVo.setSerFinRateVos(serFinRateVosTmp);
            });
        }
    }

    @LogMonitor("平台费规则配置")
    @Override
    public Result<SerFinRuleVo> queryRecord(Integer primaryKey) {
        SerFinRuleVo serFinRuleVo = null;
        try {
            SerFinRule serFinRule = this.serFinRuleService.queryRecord(primaryKey);
            SerFinRateForm serFinRateForm = new SerFinRateForm();
            serFinRateForm.setRuleSeq(primaryKey);

            if (serFinRule == null) {
                return Result.failInEmptyRecord(null);
            }
            //CA融服务费率
            List<SerFinRate> serFinRates = serFinRateMapper.pageQuery(new PageForm<>(serFinRateForm));
            List<SerFinRateVo> serFinRateVos = new SerFinRateVoConvertor().convertList(serFinRates);
            serFinRuleVo = new SerFinRuleVoConvertor().convert(serFinRule);
            serFinRuleVo.setSerFinRateVos(serFinRateVos);
        } catch (Exception e) {
            LOGGER.error("{}查询异常,id={}",LOG_TITLE,primaryKey,e);
            return Result.failInServer(serFinRuleVo);
        }
        return Result.suc(serFinRuleVo);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Result<String> saveRecord(SerFinRule record) {
        return commonComponent.saveRecord(record,authModelEnum,LOG_TITLE,serFinRuleService,
        entity -> {
            SerFinRule rule = (SerFinRule)entity;
            Date current = TimeTools.createNowTime();
            rule.setId(record.getRuleSeq());
            rule.setUpdateTime(current);
            rule.setTakenMode(Optional.ofNullable(rule.getTakenMode()).orElse(SerFinConstant.TakenModeEnum.ONLINE.getIndex()));
            entityComponent.setCreateInfo(rule);
            if(rule.isInsert()){
                rule.setCreateTime(current);
            }
        },entity -> {
            SerFinRule rule = (SerFinRule)entity;
            Integer ruleId = rule.getRuleSeq();
            if(!rule.isInsert()){
                SerFinRateForm serFinRateForm = new SerFinRateForm();
                serFinRateForm.setRuleSeq(ruleId);
                serFinRateMapper.delByForm(serFinRateForm);
            }
            List<SerFinRate> rateList = rule.getSerFinRates();
            if (!CollectionUtils.isEmpty(rateList)) {
                rateList.forEach(rate -> {
                    rate.setRuleSeq(ruleId);
                    if(rate.getSerFinRate().equals(0d)){
                        rate.setSerFinRebateRate(BigDecimal.ZERO);
                    }
                });
                serFinRateMapper.insertBatch(rateList);
            }
        });
    }

    @Override
    public Result<String> deleteRecord(Integer primaryKey) {
        LOGGER.info("{} - deleteRecord 参数：{}", LOG_TITLE,primaryKey);
        try {
            SerFinRule finRule = serFinRuleService.queryRecord(primaryKey);
            ValidateUtils.notNull(finRule, HeilCode.E_400, "删除记录不存在！请核实操作");
            if(CommonConstant.IS_ALL_DEALER.equals(finRule.getIsAllDealer())){
                SerFinDealerForm dealerForm = new SerFinDealerForm();
                dealerForm.setRuleSeq(primaryKey);
                int count = serFinDealerService.deleteByParams(dealerForm);
                LOGGER.info("SerFinRuleFacade-删除规则对应门店条数:{}",count);
            }
            PdTag pdTag = new PdTag();
            pdTag.setSourceId(primaryKey);
            pdTag.setBuzType(TagConstant.BuzTypeEnum.SER_FIN_RULE.getIndex());
            int countTag = pdTagService.deleteRecord(pdTag);
            LOGGER.info("SerFinRuleFacade-删除规则对应标签条数:{}",countTag);
            countTag = serFinRateMapper.delByForm(SerFinRateForm.builder().ruleSeq(primaryKey).build());
            LOGGER.info("SerFinRuleFacade-删除规则对应利率条数:{}",countTag);
            serFinRuleService.deleteRecord(primaryKey);
            this.saveLog(UserOperateLogConstant.AuthTypeEnum.DELETE, MessageFormat.format("删除primaryKey={0}",primaryKey));
        } catch (Exception e) {
            LOGGER.error("{}删除异常,id={}",LOG_TITLE,primaryKey,e);
            return Result.failInServer("删除异常");
        }
        return Result.suc();
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
