package com.mljr.heil.service.rule.impl;

import com.alibaba.fastjson.JSON;
import com.lyqc.base.page.PageForm;
import com.mljr.enums.LogTitleEnum;
import com.mljr.heil.common.consts.CommonConstant;
import com.mljr.heil.entity.AccountRule;
import com.mljr.heil.form.AccountDealerForm;
import com.mljr.heil.form.AccountRuleForm;
import com.mljr.heil.mapper.AccountDealerMapper;
import com.mljr.heil.mapper.AccountRuleMapper;
import com.mljr.heil.service.rule.AccountRuleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 
	* @ClassName: RateRuleServiceImpl  
	* @Description: 账户管理费规则配置
	* @author lihaitao  
	* @date 2018年2月5日  
	*
 */
@Service
public class AccountRuleServiceImpl implements AccountRuleService {
	private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
    private final LogTitleEnum LOG_TITLE = LogTitleEnum.ACCOUNT_RULE;
	
	@Resource
	AccountRuleMapper accountRuleMapper;
	@Resource
	AccountDealerMapper accountDealerMapper;

	@Override
	public List<AccountRule> queryList(AccountRuleForm form) {
		return accountRuleMapper.queryList(form);
	}

	@Override
	public List<AccountRule> queryByPage(PageForm<AccountRuleForm> form) {
		List<AccountRule> accountRules = null;
		try {
			accountRules = accountRuleMapper.pageQuery(form);
		} catch (Exception e) {
			LOGGER.error("{}查询异常-form:{} ",LOG_TITLE.getName(),JSON.toJSON(form),e);
		}
		return accountRules;
	}

	@Override
	public int queryCount(PageForm<AccountRuleForm> form) {
		return accountRuleMapper.queryCount(form);
	}

	@Override
	public AccountRule queryRecord(Integer primaryKey) {
		return accountRuleMapper.selectByPrimaryKey(primaryKey);
	}

	@Override
	public int insertRecord(AccountRule record) {
		accountRuleMapper.insertSelective(record);
		return record.getRuleSeq();
	}

	@Override
	public int updateRecord(AccountRule record) {
		return accountRuleMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int deleteRecord(Integer primaryKey) {
		AccountRule record = queryRecord(primaryKey);
		//是否适用于所有经销商
		if(null != record && CommonConstant.IS_ALL_DEALER.equals(record.getIsAllDealer())){
			AccountDealerForm form = new AccountDealerForm();
			form.setRuleSeq(primaryKey);
			int count = accountDealerMapper.delByParams(form);
			LOGGER.info("delRateRule-rateDealer删除{}条",count);
		}
		int count = accountRuleMapper.deleteByPrimaryKey(primaryKey);
		return count;
	}

}
