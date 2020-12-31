package com.mljr.heil.entity;

/**
 *贴息规则附加属性
 *
 *@author Cyn
 */
public class Discount {

	public String salePriceMin;
	public String salePriceMax;
	public String carloanAmountMin;
	public String carloanAmountMax;
	public String isOld;
	public String isHouse;
	public String isLcv;
	public String loanAmountMin;
	public String loanAmountMax;
	public String initScaleMin;
	public String initScaleMax;
	public String loanPeriods;
	public String carAgeMin;
	public String carAgeMax;
	public String courseMin;
	public String courseMax;
	public String comFlag;//计算方式：1 贴金额、 2 贴点数
	public String ruleExpression;
	
	public String getSalePriceMin() {
		return salePriceMin;
	}
	public void setSalePriceMin(String salePriceMin) {
		this.salePriceMin = salePriceMin;
	}
	public String getSalePriceMax() {
		return salePriceMax;
	}
	public void setSalePriceMax(String salePriceMax) {
		this.salePriceMax = salePriceMax;
	}
	public String getCarloanAmountMin() {
		return carloanAmountMin;
	}
	public void setCarloanAmountMin(String carloanAmountMin) {
		this.carloanAmountMin = carloanAmountMin;
	}
	public String getCarloanAmountMax() {
		return carloanAmountMax;
	}
	public void setCarloanAmountMax(String carloanAmountMax) {
		this.carloanAmountMax = carloanAmountMax;
	}
	public String getIsOld() {
		return isOld;
	}
	public void setIsOld(String isOld) {
		this.isOld = isOld;
	}
	public String getIsHouse() {
		return isHouse;
	}
	public void setIsHouse(String isHouse) {
		this.isHouse = isHouse;
	}
	public String getIsLcv() {
		return isLcv;
	}
	public void setIsLcv(String isLcv) {
		this.isLcv = isLcv;
	}
	public String getLoanAmountMin() {
		return loanAmountMin;
	}
	public void setLoanAmountMin(String loanAmountMin) {
		this.loanAmountMin = loanAmountMin;
	}
	public String getLoanAmountMax() {
		return loanAmountMax;
	}
	public void setLoanAmountMax(String loanAmountMax) {
		this.loanAmountMax = loanAmountMax;
	}
	public String getInitScaleMin() {
		return initScaleMin;
	}
	public void setInitScaleMin(String initScaleMin) {
		this.initScaleMin = initScaleMin;
	}
	public String getInitScaleMax() {
		return initScaleMax;
	}
	public void setInitScaleMax(String initScaleMax) {
		this.initScaleMax = initScaleMax;
	}
	public String getLoanPeriods() {
		return loanPeriods;
	}
	public void setLoanPeriods(String loanPeriods) {
		this.loanPeriods = loanPeriods;
	}
	public String getCarAgeMin() {
		return carAgeMin;
	}
	public void setCarAgeMin(String carAgeMin) {
		this.carAgeMin = carAgeMin;
	}
	public String getCarAgeMax() {
		return carAgeMax;
	}
	public void setCarAgeMax(String carAgeMax) {
		this.carAgeMax = carAgeMax;
	}
	public String getCourseMin() {
		return courseMin;
	}
	public void setCourseMin(String courseMin) {
		this.courseMin = courseMin;
	}
	public String getCourseMax() {
		return courseMax;
	}
	public void setCourseMax(String courseMax) {
		this.courseMax = courseMax;
	}
	public String getComFlag() {
		return comFlag;
	}
	public void setComFlag(String comFlag) {
		this.comFlag = comFlag;
	}
	public String getRuleExpression() {
		return ruleExpression;
	}
	public void setRuleExpression(String ruleExpression) {
		this.ruleExpression = ruleExpression;
	}
}
