package com.mljr.heil.param;

/**
 * 请求参数
 *@author Cyn
 *
 */
public class RuleReqPam {

	public String salePrice;//实际销售价
	public String carloanAmount;//车辆贷款金额
	public String isOld;
	public String isHouse;
	public String isLcv;
	public String loanAmount;//贷款总金额
	public String initScale;
	public String loanPeriods;
	public String carAge;//车龄(月)
	public String course;//里程
	
	public String getSalePrice() {
		return salePrice;
	}
	public void setSalePrice(String salePrice) {
		this.salePrice = salePrice;
	}
	public String getCarloanAmount() {
		return carloanAmount;
	}
	public void setCarloanAmount(String carloanAmount) {
		this.carloanAmount = carloanAmount;
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
	public String getLoanAmount() {
		return loanAmount;
	}
	public void setLoanAmount(String loanAmount) {
		this.loanAmount = loanAmount;
	}
	public String getInitScale() {
		return initScale;
	}
	public void setInitScale(String initScale) {
		this.initScale = initScale;
	}
	public String getLoanPeriods() {
		return loanPeriods;
	}
	public void setLoanPeriods(String loanPeriods) {
		this.loanPeriods = loanPeriods;
	}
	public String getCarAge() {
		return carAge;
	}
	public void setCarAge(String carAge) {
		this.carAge = carAge;
	}
	public String getCourse() {
		return course;
	}
	public void setCourse(String course) {
		this.course = course;
	}
	@Override
	public String toString() {
		return "RuleReqPam [salePrice=" + salePrice + ", carloanAmount="
				+ carloanAmount + ", isOld=" + isOld + ", isHouse=" + isHouse
				+ ", isLcv=" + isLcv + ", loanAmount=" + loanAmount
				+ ", initScale=" + initScale + ", loanPeriods=" + loanPeriods
				+ ", carAge=" + carAge + ", course=" + course + "]";
	}
	
}
