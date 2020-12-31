package com.mljr.heil.service.fund.impl;

import com.alibaba.fastjson.JSON;
import com.lyqc.base.page.PageForm;
import com.mljr.enums.LogTitleEnum;
import com.mljr.heil.entity.fund.PdFundRule;
import com.mljr.heil.form.fund.PdFundRuleForm;
import com.mljr.heil.mapper.fund.PdFundRuleMapper;
import com.mljr.heil.service.fund.PdFundRuleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @description: 资金方规则
 * @Date : 2018/6/13 11:08
 * @Author : lihaitao
 */
@Service
public class PdFundRuleServiceImpl implements PdFundRuleService {
	private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
    private final LogTitleEnum LOG_TITLE = LogTitleEnum.RATE_RULE;
	
	@Resource
	private PdFundRuleMapper pdFundRuleMapper;

	@Override
	public List<PdFundRule> queryByPage(PageForm<PdFundRuleForm> form) {
		List<PdFundRule> pdFundRules = null;
		try {
			pdFundRules = pdFundRuleMapper.pageQuery(form);
		} catch (Exception e) {
			LOGGER.error("{}查询异常-form:{} ",LOG_TITLE.getName(),JSON.toJSON(form),e);
		}
		return pdFundRules;
	}

	@Override
	public int queryCount(PageForm<PdFundRuleForm> form) {
		return pdFundRuleMapper.queryCount(form);
	}

	@Override
	public PdFundRule queryRecord(Integer primaryKey) {
		return pdFundRuleMapper.selectByPrimaryKey(primaryKey);
	}

	@Override
	public int insertRecord(PdFundRule record) {
		pdFundRuleMapper.insertSelective(record);
		return record.getId();
	}

	@Override
	public int updateRecord(PdFundRule record) {
		return pdFundRuleMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKeySelective(PdFundRule record) {
		return pdFundRuleMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int deleteRecord(Integer primaryKey) {
		int count = pdFundRuleMapper.deleteByPrimaryKey(primaryKey);
		return count;
	}

	@Override
	public List<PdFundRule> queryList(PdFundRuleForm form) {
		return pdFundRuleMapper.queryList(form);
	}

}
