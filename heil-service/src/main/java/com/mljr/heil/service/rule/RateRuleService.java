package com.mljr.heil.service.rule;

import com.mljr.heil.base.service.BaseService;
import com.mljr.heil.entity.RateRule;
import com.mljr.heil.form.RateRuleForm;

import java.util.List;

/**
 * 
	* @ClassName: RateRuleService  
	* @Description: 利率规则配置 
	* @author lihaitao  
	* @date 2018年2月5日  
	*
 */
public interface RateRuleService extends BaseService<RateRule, Integer, RateRuleForm> {

	/**
	 * 查询未绑定启用中产品的利率规则
	 * @return
	 */
	List<RateRule> queryRulesUnBindProduct();
}
