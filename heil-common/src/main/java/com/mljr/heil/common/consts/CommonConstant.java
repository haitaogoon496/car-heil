package com.mljr.heil.common.consts;

/**
 * 
	* @ClassName: SyArgConTypeConstant  
	* @Description: 常量
	* @author lihaitao  
	* @date 2018年2月6日  
	*
 */
public class CommonConstant {
	
	
	/**
	 * 适用于所有经销商
	 */
	public static final Integer IS_ALL_DEALER = 0;


	public static final Integer SYSTEM_USER_ID = 1;
	public static final String SYSTEM_USER_NAME = "系统管理员";
	// 合同对应产品模块
	// 直营系统
	public static final String SYSTEM_CODE_ZY = "ZX";
	// 渠道系统
	public static final String SYSTEM_CODE_SP = "SP";
	// 灰度控制总开关key
	public static final String GRAY_CONTROL_SWITCH = "Common:grayControlSwitch";
	/**
	 *token
	 */
	public static final int JWT_ERRCODE_NULL = 4000; //Token不存在
	public static final int JWT_ERRCODE_EXPIRE = 4001; //Token过期
	public static final int JWT_ERRCODE_FAIL = 4002; //验证不通过
	/**
	 * JWT
	 */
	public static final String JWT_SECERT = "8677df7fc3a34e26a61c034d5ec8245d"; //密匙
	public static final long JWT_TTL = 30 * 60 * 1000; //token有效时间为半小时
}
