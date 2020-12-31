package com.mljr.heil.facade.rule;

import com.alibaba.fastjson.JSON;
import com.lyqc.base.common.Result;
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
import com.mljr.heil.entity.AccountRule;
import com.mljr.heil.form.AccountRuleForm;
import com.mljr.heil.service.rule.AccountRuleService;
import com.mljr.heil.vo.rule.AccountRuleVo;
import com.mljr.heil.voconvertor.rule.AccountRuleVoConvertor;
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
 * @description: 账户管理费规则配置
 * @Date : 2018/2/11 10:19
 * @Author : lihaitao
 */
@Component
public class AccountRuleFacade implements BaseFacade<AccountRuleVo,AccountRule, Integer, AccountRuleForm> {
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
    private final String LOG_TITLE = LogTitleEnum.ACCOUNT_RULE.getName();
    private final UserOperateLogConstant.AuthModelEnumForProduct authModelEnum = UserOperateLogConstant.AuthModelEnumForProduct.ACCOUNT_RULE;
    @Autowired
    private AccountRuleService accountRuleService;
    @Autowired
    private UserOperateLogComponent userOperateLogComponent;
    @Autowired
    private CommonComponent commonComponent;

    @LogMonitor("账户管理费规则配置")
    @Override
    public Result<PageVO<AccountRuleVo>> loadRecords(PageForm<AccountRuleForm> form) {
        List<AccountRuleVo> voList = Collections.emptyList();
        List<AccountRule> list = Collections.emptyList();
        int count = 0;
        try {
            list = this.accountRuleService.queryByPage(form);
            count = this.accountRuleService.queryCount(form);
            voList = new AccountRuleVoConvertor().convertList(list);
            commonComponent.bindTags(TwoTuple.newInstance(TagConstant.BuzTypeEnum.ACCOUNT_RULE.getIndex(),voList),
                    rule -> Integer.valueOf(rule.getRuleSeq()),(rule, tags) -> rule.setTags(tags));
        } catch (Exception e) {
            LOGGER.error("{}loadRecords加载数据异常,form={}",LOG_TITLE, JSON.toJSON(form),e);
            return Result.failInServer(new PageVO<>(form.getDraw(),count,voList));
        }
        return Result.suc(new PageVO<>(form.getDraw(),count,voList));
    }

    @LogMonitor("账户管理费规则配置")
    @Override
    public Result<AccountRuleVo> queryRecord(Integer primaryKey) {
        AccountRule record;
        AccountRuleVo vo = null;
        try {
            record = this.accountRuleService.queryRecord(primaryKey);
            if (record == null) {
                return Result.failInEmptyRecord(null);
            }
            vo = new AccountRuleVoConvertor().convert(record);
        } catch (Exception e) {
            LOGGER.error("{}查询异常,id={}",LOG_TITLE,primaryKey,e);
            return Result.failInServer(vo);
        }
        return Result.suc(vo);
    }

    @Override
    public Result<String> saveRecord(AccountRule record) {
        return commonComponent.saveRecord(record,authModelEnum,LOG_TITLE,accountRuleService,entity -> {
            AccountRule rule = (AccountRule)entity;
            Date current = TimeTools.createNowTime();
            rule.setId(rule.getRuleSeq());
            rule.setId(record.getRuleSeq());
            rule.setUpdateTime(current);
            if(rule.isInsert()){
                rule.setCreateTime(current);
            }
        });
    }

    @Override
    public Result<String> deleteRecord(Integer primaryKey) {
        return commonComponent.deleteRecord(primaryKey,LOG_TITLE,accountRuleService,(PK) ->
                saveLog(UserOperateLogConstant.AuthTypeEnum.DELETE, MessageFormat.format("删除primaryKey={0}",PK))
        );
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
