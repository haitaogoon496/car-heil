package com.mljr.heil.common.util;


/**
 * 保险费与购置税计算规则
 * @author Cyn
 *
 */
public class ComputeFeeUtil {
	
	//有心有利
	public final static Double PURCHASE_TAX = 11.7d;
	public final static Double INSURANCE_RATE_11 = 0.115d;
	public final static Double INSURANCE_RATE_10 = 0.105d;
	public final static Double INSURANCE_RATE_09 = 0.095d;
	public final static Double INSURANCE_RATE_08 = 0.085d;
	public final static Double INSURANCE_RATE_07 = 0.075d;
	public final static Double INSURANCE_RATE_06 = 0.065d;
	
	public final static Double INSURANCE_RATE_05 = 0.055d;
	public final static Double INSURANCE_RATE_050 = 0.050d;
	
	//融保险
	public final static Double TCI_FEE = 950d;//交强险费用
	public final static Double TPI_FEE = 1700d;//三者险费用
	public final static Double CARDAMAGE_RADIX_FEE = 590d;//车损基数费用
	public final static Double ROBBERY_RADIX_FEE = 120d;//三者险基数费用
	
	//计算百分比 x10000
	public final static Double PERCENT_45 = 0.0045d;
	public final static Double PERCENT_600 = 0.0060d;
	public final static Double PERCENT_125 = 0.0125d;
	public final static Double PERCENT_130 = 0.0130d;
	public final static Double PERCENT_140 = 0.0140d;
	public final static Double PERCENT_1500 = 0.1500d;
	public final static Double PERCENT_2000 = 0.2000d;
	//折扣
	public final static Double PERCENT_9000 = 0.90d;
	
	
	public static Integer getInsuranceFee(Integer carMonth,Double newReferenceCarPrice,Double trueSalePrice,String productCode){
		Integer fee=null;
		String flag=null;
		Double guidePrice=0d;
		if ("7".equals(productCode)||"20".equals(productCode)||"17".equals(productCode)) { //有心有利产品与E时贷B,长安无忧贷
			fee=computeFee(trueSalePrice,productCode);
		}else{ //新时贷
			if("14".equals(productCode)||"15".equals(productCode)){ //新车
				flag="0";
			}else if("11".equals(productCode)||"12".equals(productCode)||"13".equals(productCode)){ //二手车
				if (carMonth>0&&carMonth<=24) { //0-2年
					flag="1";
				}else if (carMonth>24&&carMonth<=84) { //2-7年
					flag="2";
				}
			}
			guidePrice= lowPrice(carMonth, newReferenceCarPrice, trueSalePrice, flag);
			fee = getFAI(guidePrice, carMonth, flag);
		}
		return fee;
	}
	
	/**
	 * 指导价规则 二者取低
	 * @param carMonth
	 * @param trueSalePrice
	 * @param flag 0 1 2 0新车 1，2二手车
	 * @return guidePrice 指导价
	 */
	public static Double lowPrice(Integer carMonth,Double newReferenceCarPrice,Double trueSalePrice,String flag){
		Double guidePrice=null;
		if (!"0".equals(flag)) { //二手车
			newReferenceCarPrice=ArithUtil.mul(newReferenceCarPrice, 1-PERCENT_600*carMonth);
		}
		if (ArithUtil.sub(newReferenceCarPrice, trueSalePrice)>=0) {
			guidePrice=trueSalePrice ;
		}else {
			guidePrice=newReferenceCarPrice;
		}
		return guidePrice;
	}
	
	/**
	 * 有心有利产品计算公式
	 * @param carFee
	 * @param productCode
	 * @return
	 */
	public static Integer computeFee(Double carFee,String productCode){
		Double insurance =null;
		Double purchase =null;
		Double computeFee =null;
		if (null == carFee) 
			return 0;
		
		if (carFee>80000d&&("7".equals(productCode)||"20".equals(productCode))) {
			return 0;
		}else if (carFee>120000d&&"17".equals(productCode)) {
			return 0;
		}else{
			insurance = ArithUtil.mul(carFee, getInsRate(carFee,productCode));
			purchase = ArithUtil.div(carFee, PURCHASE_TAX);
			computeFee = ArithUtil.add(insurance, purchase);
		}
		return computeFee.intValue(); //取整
	}
	
	
	/**
	 * 融保险计算规则
	 * @param guidePrice
	 * @param carMonth
	 * @param flag 0:新车  1:0~2年  2:2-7年
	 * @return
	 */
	public static Integer getFAI(Double guidePrice,Integer carMonth,String flag){
		Double carDamage=null;//车损
		Double robbery=null;//盗抢
		Double nonDeductible=null;//不计免赔
		Double result=null;//最终费用
		try {
			if ("0".equals(flag)) {
				carDamage=CARDAMAGE_RADIX_FEE+ArithUtil.mul(guidePrice, PERCENT_140);
				robbery=ROBBERY_RADIX_FEE+ArithUtil.mul(guidePrice, PERCENT_45);
			}else if ("1".equals(flag)) {
				carDamage=CARDAMAGE_RADIX_FEE+ArithUtil.mul(guidePrice, PERCENT_130);
				robbery=ROBBERY_RADIX_FEE+ArithUtil.mul(guidePrice, PERCENT_45);
			}else if ("2".equals(flag)) {
//				carDamage=CARDAMAGE_RADIX_FEE+ArithUtil.mul(ArithUtil.mul(guidePrice, 1-PERCENT_600*carMonth),PERCENT_125);
				carDamage=CARDAMAGE_RADIX_FEE+ArithUtil.mul(guidePrice, PERCENT_125);
				robbery=ROBBERY_RADIX_FEE+ArithUtil.mul(guidePrice, PERCENT_45);
			}
			nonDeductible=ArithUtil.mul(TPI_FEE, PERCENT_1500)+ArithUtil.mul(carDamage, PERCENT_1500)+ArithUtil.mul(robbery, PERCENT_2000);
		} catch (Exception e) {
			e.printStackTrace();
		}
		result=ArithUtil.add(ArithUtil.add(TCI_FEE, TPI_FEE),ArithUtil.add(ArithUtil.add(carDamage, robbery),nonDeductible)); //五项相加
		result=ArithUtil.mul(result, PERCENT_9000);//折扣
		return result.intValue();
	}
	
	/**
	 * 根据产品返回保险费利率
	 * @param carFee
	 * @param productCode 7  17
	 * @return
	 */
	public static Double getInsRate(Double carFee,String productCode){
		if ("17".equals(productCode)) { //E时贷B
			if (carFee<=80000d) {
				return INSURANCE_RATE_06;
			}else if (carFee>80000&&carFee<=100000d){
				return INSURANCE_RATE_05;
			}else if (carFee>100000d&&carFee<=120000d){
				return INSURANCE_RATE_050;
			}else {
				return 0d;
			}
		}
		if ("7".equals(productCode)||"20".equals(productCode)) { //有心有利产品,长安无忧贷
			if (carFee>0&&carFee<=30000d) {
				return INSURANCE_RATE_11;
			}else if (carFee>30000&&carFee<=40000d){
				return INSURANCE_RATE_10;
			}else if (carFee>40000d&&carFee<=50000d) {
				return INSURANCE_RATE_09;
			}else if (carFee>50000d&&carFee<=60000d) {
				return INSURANCE_RATE_08;
			}else if (carFee>60000d&&carFee<=70000d) {
				return INSURANCE_RATE_07;
			}else if (carFee>70000d&&carFee<=80000d) {
				return INSURANCE_RATE_06;
			}else {
				return 0d;
			}
		}
		return 0d;
	}
}
