package com.mljr.heil.service.rule.impl;

import com.alibaba.fastjson.JSON;
import com.lyqc.base.page.PageForm;
import com.lyqc.base.re.SyArgControlRe;
import com.mljr.enums.LogTitleEnum;
import com.mljr.heil.common.consts.CommonConstant;
import com.mljr.heil.common.consts.SyArgConTypeConstant;
import com.mljr.heil.entity.RateRule;
import com.mljr.heil.facade.common.ArgControlFacade;
import com.mljr.heil.form.RateDealerForm;
import com.mljr.heil.form.RateRuleForm;
import com.mljr.heil.mapper.RateDealerMapper;
import com.mljr.heil.mapper.RateRuleMapper;
import com.mljr.heil.service.rule.RateRuleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 
	* @ClassName: RateRuleServiceImpl  
	* @Description: 利率规则配置
	* @author lihaitao  
	* @date 2018年2月5日  
	*
 */
@Service
public class RateRuleServiceImpl implements RateRuleService {
	private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
    private final LogTitleEnum LOG_TITLE = LogTitleEnum.RATE_RULE;
	
	@Resource
	RateRuleMapper rateRuleMapper;
	
	@Resource
	RateDealerMapper rateDealerMapper;

	@Autowired
	private ArgControlFacade argControlFacade;

	@Override
	public List<RateRule> queryByPage(PageForm<RateRuleForm> form) {
		List<RateRule> rateRules = null;
		try {
			rateRules = rateRuleMapper.pageQuery(form);
			// 获取参数配置数据,封装raterule
			List<SyArgControlRe> syArgControls = argControlFacade.queryOne(SyArgConTypeConstant.RATE_RULE_CON_TYPE);
			Map<String, String> map = syArgControls.stream().collect(Collectors.toMap(SyArgControlRe::getValue, SyArgControlRe::getName));
			if(!CollectionUtils.isEmpty(syArgControls) && !CollectionUtils.isEmpty(rateRules)){
				for (RateRule rateRule : rateRules) {
					if(!StringUtils.isEmpty(rateRule.getRateLevel())){
						rateRule.setRateLevel(map.get(rateRule.getRateLevel()));
					}
				}
			}
		} catch (Exception e) {
			LOGGER.error("{}查询异常-form:{} ",LOG_TITLE.getName(),JSON.toJSON(form),e);
		}
		return rateRules;
	}

	@Override
	public int queryCount(PageForm<RateRuleForm> form) {
		return rateRuleMapper.queryCount(form);
	}

	@Override
	public RateRule queryRecord(Integer primaryKey) {
		return rateRuleMapper.selectByPrimaryKey(primaryKey);
	}

	@Override
	public int insertRecord(RateRule record) {
		rateRuleMapper.insertSelective(record);
		return record.getRuleSeq();
	}

	@Override
	public int updateRecord(RateRule record) {
		return rateRuleMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int deleteRecord(Integer primaryKey) {
		RateRule record = queryRecord(primaryKey);
		//是否适用于所有经销商
		if(null != record && CommonConstant.IS_ALL_DEALER.equals(record.getIsAllDealer())){
			RateDealerForm form = new RateDealerForm();
			form.setRuleSeq(primaryKey);
			int count = rateDealerMapper.delByParams(form);
			LOGGER.info("delRateRule-rateDealer删除{}条",count);
		}
		int count = rateRuleMapper.deleteByPrimaryKey(primaryKey);
		return count;
	}

	@Override
	public List<RateRule> queryList(RateRuleForm form) {
		return rateRuleMapper.queryList(form);
	}

	@Override
	public List<RateRule> queryRulesUnBindProduct() {
		return rateRuleMapper.queryRulesUnBindProduct();
	}
}
