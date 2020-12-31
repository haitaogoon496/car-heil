package com.mljr.heil.service.product.impl;

import com.lyqc.base.page.PageForm;
import com.lyqc.base.re.SyArgControlRe;
import com.mljr.heil.base.entity.BaseDealerRes;
import com.mljr.heil.base.form.RuleRefForm;
import com.mljr.heil.common.consts.SyArgConTypeConstant;
import com.mljr.heil.common.enums.RuleBindProductCheckTableEnum;
import com.mljr.heil.entity.AccountRule;
import com.mljr.heil.entity.GpsRule;
import com.mljr.heil.entity.PdRuleProduct;
import com.mljr.heil.entity.RateRule;
import com.mljr.heil.entity.SerFinRule;
import com.mljr.heil.entity.YanbaoRule;
import com.mljr.heil.facade.common.ArgControlFacade;
import com.mljr.heil.form.PdRuleProductForm;
import com.mljr.heil.mapper.PdRuleProductMapper;
import com.mljr.heil.service.product.PdRuleProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @description:
 * @Date : 2018/8/6 14:52
 * @Author : lihaitao
 */
@Service
public class PdRuleProductServiceImpl implements PdRuleProductService {
	
	@Resource
	PdRuleProductMapper pdRuleProductMapper;

	@Autowired
	private ArgControlFacade argControlFacade;

	@Override
	public int deleteByForm(PdRuleProductForm form) {
		return pdRuleProductMapper.deleteByForm(form);
	}

	@Override
	public List<SerFinRule> querySerFinRules(PageForm<PdRuleProductForm> pageForm) {
		return pdRuleProductMapper.querySerFinRules(pageForm);
	}

	@Override
	public int querySerFinRulesCount(PageForm<PdRuleProductForm> pageForm) {
		return pdRuleProductMapper.querySerFinRulesCount(pageForm);
	}


	@Override
	public List<RateRule> queryRateRules(PageForm<PdRuleProductForm> pageForm) {
		List<RateRule> rateRules = pdRuleProductMapper.queryRateRules(pageForm);
		formatRateRule(rateRules);

		return rateRules;
	}

	/**
	 * 格式化rateRule中的rateLevel
	 * @param rateRules
	 */
	public void formatRateRule(List<RateRule> rateRules) {
		// 获取参数配置数据,封装raterule
		List<SyArgControlRe> syArgControls = argControlFacade.queryOne(SyArgConTypeConstant.RATE_RULE_CON_TYPE);
		Map<String, String> map = syArgControls.stream().collect(Collectors.toMap(SyArgControlRe::getValue, SyArgControlRe::getName));
		if (!CollectionUtils.isEmpty(syArgControls) && !CollectionUtils.isEmpty(rateRules)) {
			rateRules.stream().filter(rateRule -> !StringUtils.isEmpty(rateRule.getRateLevel())).collect(Collectors.toList()).forEach(rate -> {
				rate.setRateLevel(map.get(rate.getRateLevel()));
			});
		}
	}


	@Override
	public int queryRateRulesCount(PageForm<PdRuleProductForm> pageForm) {
		return pdRuleProductMapper.queryRateRulesCount(pageForm);
	}

	@Override
	public List<GpsRule> queryGpsRules(PageForm<PdRuleProductForm> pageForm) {
		return pdRuleProductMapper.queryGpsRules(pageForm);
	}


	@Override
	public int queryGpsRulesCount(PageForm<PdRuleProductForm> pageForm) {
		return pdRuleProductMapper.queryGpsRulesCount(pageForm);
	}

	@Override
	public List<YanbaoRule> queryYanbaoRules(PageForm<PdRuleProductForm> pageForm) {
		return pdRuleProductMapper.queryYanbaoRules(pageForm);
	}

	@Override
	public int queryYanbaoRulesCount(PageForm<PdRuleProductForm> pageForm) {
		return pdRuleProductMapper.queryYanbaoRulesCount(pageForm);
	}


	@Override
	public List<AccountRule> queryAccountRules(PageForm<PdRuleProductForm> pageForm) {
		return pdRuleProductMapper.queryAccountRules(pageForm);
	}


	@Override
	public int queryAccountRulesCount(PageForm<PdRuleProductForm> pageForm) {
		return pdRuleProductMapper.queryAccountRulesCount(pageForm);
	}

	@Override
	public List<PdRuleProduct> queryByPage(PageForm<PdRuleProductForm> form) {
		return pdRuleProductMapper.pageQuery(form);
	}

	@Override
	public int queryCount(PageForm<PdRuleProductForm> form) {
		return pdRuleProductMapper.queryCount(form);
	}

	@Override
	public PdRuleProduct queryRecord(Integer primaryKey) {
		return pdRuleProductMapper.selectByPrimaryKey(primaryKey);
	}

	@Override
	public int insertRecord(PdRuleProduct record) {
		return pdRuleProductMapper.insertSelective(record);
	}

	@Override
	public int updateRecord(PdRuleProduct record) {
		return pdRuleProductMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int deleteRecord(Integer primaryKey) {
		return pdRuleProductMapper.deleteByPrimaryKey(primaryKey);
	}

	@Override
	public int batchInsert(List<PdRuleProduct> list) {
		return pdRuleProductMapper.batchInsert(list);
	}

	@Override
	public int batchInsertIgnore(List<PdRuleProduct> baseDealerResList) {
		return pdRuleProductMapper.batchInsertIgnore(baseDealerResList);
	}

	@Override
	public int batchDelete(List<PdRuleProduct> baseDealerResList) {
		return pdRuleProductMapper.batchDelete(baseDealerResList);
	}

	@Override
	public List<BaseDealerRes> queryRuleProductListByParam(RuleBindProductCheckTableEnum tableEnum) {
		RuleRefForm form = RuleRefForm.builder().masterRefKey(tableEnum.getMasterRefKey()).masterTable(tableEnum.getMasterTable())
				.slaveTable(tableEnum.getSlaveTable()).slaveRefKey(tableEnum.getSlaveRefKey()).classify(tableEnum.getClassify()).build();
		return pdRuleProductMapper.queryRuleProductListByParam(form);
	}

}
