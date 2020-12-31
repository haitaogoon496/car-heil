package com.mljr.heil.service.fund.impl;

import com.alibaba.fastjson.JSON;
import com.lyqc.base.page.PageForm;
import com.mljr.enums.LogTitleEnum;
import com.mljr.heil.entity.fund.PdFundRuleProp;
import com.mljr.heil.form.fund.PdFundRulePropForm;
import com.mljr.heil.mapper.fund.PdFundRulePropMapper;
import com.mljr.heil.service.fund.PdFundRulePropService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @description: 资金方规则属性serviceImpl
 * @Date : 2018/6/13 11:08
 * @Author : lihaitao
 */
@Service
public class PdFundRulePropServiceImpl implements PdFundRulePropService {
	private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
    private final LogTitleEnum LOG_TITLE = LogTitleEnum.RATE_RULE;

	@Resource
	private PdFundRulePropMapper pdFundRulePropMapper;

	@Override
	public List<PdFundRuleProp> queryByPage(PageForm<PdFundRulePropForm> form) {
		List<PdFundRuleProp> pdFundRuleProps = null;
		try {
			pdFundRuleProps = pdFundRulePropMapper.pageQuery(form);
		} catch (Exception e) {
			LOGGER.error("{}查询异常-form:{} ",LOG_TITLE.getName(),JSON.toJSON(form),e);
		}
		return pdFundRuleProps;
	}

	@Override
	public int queryCount(PageForm<PdFundRulePropForm> form) {
		return pdFundRulePropMapper.queryCount(form);
	}

	@Override
	public PdFundRuleProp queryRecord(Integer primaryKey) {
		return pdFundRulePropMapper.selectByPrimaryKey(primaryKey);
	}

	@Override
	public int insertRecord(PdFundRuleProp record) {
		pdFundRulePropMapper.insertSelective(record);
		return record.getId();
	}

	@Override
	public int updateRecord(PdFundRuleProp record) {
		return pdFundRulePropMapper.updateByPrimaryKey(record);
	}

	@Override
	public int deleteRecord(Integer primaryKey) {
		int count = pdFundRulePropMapper.deleteByPrimaryKey(primaryKey);
		return count;
	}

	@Override
	public List<PdFundRuleProp> queryList(PdFundRulePropForm form) {
		return pdFundRulePropMapper.queryList(form);
	}

	@Override
	public int deleteByFundRuleId(Integer fundRuleId) {
		return pdFundRulePropMapper.deleteByFundRuleId(fundRuleId);
	}

	@Override
	public int batchInsert(List<PdFundRuleProp> list) {
		return pdFundRulePropMapper.batchInsert(list);
	}

}
