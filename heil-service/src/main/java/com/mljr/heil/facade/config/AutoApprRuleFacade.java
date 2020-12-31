package com.mljr.heil.facade.config;

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
import com.mljr.heil.common.enums.DictionaryConstant;
import com.mljr.heil.common.exception.BizException;
import com.mljr.heil.common.util.StringUtils;
import com.mljr.heil.common.util.ValidateUtils;
import com.mljr.heil.component.CommonComponent;
import com.mljr.heil.component.UserOperateLogComponent;
import com.mljr.heil.entity.AutoApprRule;
import com.mljr.heil.entity.AutoApprRuleKey;
import com.mljr.heil.entity.AutoApprRuleProp;
import com.mljr.heil.form.AutoApprRuleForm;
import com.mljr.heil.form.AutoApprRulePropForm;
import com.mljr.heil.service.config.AutoApprRulePropService;
import com.mljr.heil.service.config.AutoApprRuleService;
import com.mljr.heil.vo.config.AutoApprRuleVo;
import com.mljr.heil.voconvertor.config.AutoApprRuleVoConvertor;
import com.mljr.util.TimeTools;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.text.MessageFormat;
import java.util.Collections;
import java.util.List;

/**
 * @description: 前置规则 - CA系统自动审批规则表
 * @Date
 * @Author : liht
 */
@Component
public class AutoApprRuleFacade implements BaseFacade<AutoApprRuleVo,AutoApprRule,AutoApprRuleKey,AutoApprRuleForm> {
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
    private final String LOG_TITLE = LogTitleEnum.AUTO_APPR_RULE.getName();
    private final UserOperateLogConstant.AuthModelEnumForProduct authModelEnum = UserOperateLogConstant.AuthModelEnumForProduct.AUTO_APPR_RULE;

    @Autowired
    private AutoApprRuleService autoApprRuleService;
    @Autowired
    private AutoApprRulePropService autoApprRulePropService;
    @Autowired
    private UserOperateLogComponent userOperateLogComponent;
    @Autowired
    private CommonComponent commonComponent;

    @Value("${spring.profiles.active}")
    private String env;
    @Autowired
    private RedisUtil redisUtil;

    @LogMonitor("前置规则")
    @Override
    public Result<PageVO<AutoApprRuleVo>> loadRecords(PageForm<AutoApprRuleForm> form) {
        List<AutoApprRuleVo> voList = Collections.emptyList();
        int count = 0;
        try {
            List<AutoApprRule> list = autoApprRuleService.queryByPage(form);
            if (CollectionUtils.isEmpty(list)) {
                return Result.failInEmptyRecord(null);
            }
            voList = new AutoApprRuleVoConvertor().convertList(list);
            count = autoApprRuleService.queryCount(form);
        } catch (Exception e) {
            LOGGER.error("{}loadRecords加载数据异常,form={}",LOG_TITLE, JSON.toJSON(form),e);
            return Result.failInServer(new PageVO<>(form.getDraw(),count,voList));
        }
        return Result.suc(new PageVO<>(form.getDraw(),count,voList));
    }

    @LogMonitor("前置规则")
    @Override
    public Result<AutoApprRuleVo> queryRecord(AutoApprRuleKey primaryKey) {
        AutoApprRuleVo vo = null;
        try {
            AutoApprRule record = this.autoApprRuleService.queryRecord(primaryKey);
            if(null == record ){
                return Result.failInEmptyRecord(null);
            }
            vo = new AutoApprRuleVoConvertor().convert(record);
        } catch (Exception e) {
            LOGGER.error("{}查询异常,id={}",LOG_TITLE,primaryKey,e);
            return Result.failInServer(vo);
        }
        return Result.suc(vo);
    }

    @Override
    @LogMonitor("前置规则")
    @Transactional(rollbackFor = {BizException.class,Exception.class})
    public Result<String> saveRecord(AutoApprRule record) {
        AutoApprRuleKey key = record;
        String sysIdn = env.contains("zy") ? "D" : "C";
        AutoApprRule autoApprRule = autoApprRuleService.queryRecord(key);
        boolean isInsert = autoApprRule == null;
        String type = record.getType();
        String subType = record.getSubType();
        if (isInsert) {
            record.setSysIdn(sysIdn);
            record.setRuleSeq(record.getRuleSeq() == null ? 1 : record.getRuleSeq());
            record.setStatus(String.valueOf(DictionaryConstant.YesOrNoEnum.NO.getIndex()));
            String redisKey = redisUtil.getKeyWithSystemCode(MessageFormat.format("{0}{1}",type,subType));
            redisUtil.incr(redisKey.getBytes(),0);
            record.setCreateDate(TimeTools.createNowTime());
            long currentValue = redisUtil.getIncrValue(redisKey);
            //生成规则：type+subType+classify+6位占位符+起始种子
            record.setRuleId(String.format("%1$s%2$s%3$s%4$06d",type,subType,record.getClassify(),currentValue));
            autoApprRuleService.insertRecord(record);
            this.saveLog(UserOperateLogConstant.AuthTypeEnum.CREATE, JSON.toJSONString(record));
        }else{
            autoApprRuleService.updateRecord(record);
            this.saveLog(UserOperateLogConstant.AuthTypeEnum.UPDATE, JSON.toJSONString(record));
        }
        return Result.suc();
    }

    @Override
    @LogMonitor("前置规则")
    public Result<String> deleteRecord(AutoApprRuleKey primaryKey) {
        ValidateUtils.notNull(primaryKey.getRuleId(), HeilCode.E_400, "ruleId不能为空");
        ValidateUtils.notNull(primaryKey.getSysIdn(), HeilCode.E_400, "sysIdn不能为空");
        return commonComponent.deleteRecord(primaryKey,LOG_TITLE,autoApprRuleService,(PK) ->
                saveLog(UserOperateLogConstant.AuthTypeEnum.DELETE, MessageFormat.format("删除primaryKey={0}",PK))
        );
    }

    @LogMonitor("前置规则")
    public Result<String> modifyStatus(AutoApprRule record) {
        LOGGER.info("状态更新 - 参数:{}", JSON.toJSON(record));
        ValidateUtils.notNull(record.getRuleId(),HeilCode.E_400,"参数主键不能为空");
        ValidateUtils.notNull(record.getSysIdn(), HeilCode.E_400, "参数主键错误");
        // 如果启用改规则时，需要配置子属性
        if (!StringUtils.isEmpty(record.getStatus()) && record.getStatus().equals("1")) {
            AutoApprRulePropForm form = new AutoApprRulePropForm();
            form.setRuleId(record.getRuleId());
            List<AutoApprRuleProp> autoApprRuleProps = autoApprRulePropService.queryByPage(new PageForm<>(form));
            if (CollectionUtils.isEmpty(autoApprRuleProps)) {
                return Result.fail(HeilCode.E_500, "当前记录未配置属性规则，请先配置");
            }
        }

        int i = this.autoApprRuleService.updateRecord(record);
        ValidateUtils.notEquals(0, i, HeilCode.E_400, "更新状态失败");
        this.saveLog(UserOperateLogConstant.AuthTypeEnum.UPDATE, MessageFormat.format("状态更新param={0}",JSON.toJSON(record)));
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
