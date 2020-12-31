package com.mljr.heil.vo.common;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @description: 运营门店VO
 * @Date : 2018/3/21$ 17:11$
 * @Author : liht
 */
@Data
public class SyDealerVo implements Serializable{

    private static final long serialVersionUID = 2325522658155156789L;
    private String extendSafeDealerId;
    @ApiModelProperty(name="dealerCode",value="经销商门店编码")
    private String dealerCode;
    @ApiModelProperty(name="dealerName",value="经销商门店名称")
    private String dealerName;
    @ApiModelProperty(name="dealerType",value="经销商门店类型 目前分两类：DD(1)、 SP(2,3) ")
    private String dealerType;
    @ApiModelProperty(name="companyCode",value="所属经销商单位编码")
    private String companyCode;
    @ApiModelProperty(name="recAccountName",value="收款账户名")
    private String recAccountName;
    @ApiModelProperty(name="recAccountCard",value="收款账户证件号码")
    private String recAccountCard;
    @ApiModelProperty(name="recAccountBank",value="收款开户行")
    private String recAccountBank;
    @ApiModelProperty(name="bankProvince",value="收款开户行所属省份")
    private String bankProvince;
    @ApiModelProperty(name="bankCity",value="收款开户行所属市")
    private String bankCity;
    @ApiModelProperty(name="recAccountNo",value="收款借记卡号")
    private String recAccountNo;
    @ApiModelProperty(name="address",value="门店地址")
    private String address;
    @ApiModelProperty(name="rtamil",value="AM邮箱地址")
    private String rtamil;
    @ApiModelProperty(name="am",value="AM")
    private String am;
    @ApiModelProperty(name="sv",value="sv")
    private String sv;
    @ApiModelProperty(name="gmName",value="经销商门店GM姓名")
    private String gmName;
    @ApiModelProperty(name="gmMobile",value="经销商门店GM手机号码")
    private String gmMobile;
    @ApiModelProperty(name="gmPhone",value="经销商门店GM电话")
    private String gmPhone;
    @ApiModelProperty(name="gmEmail",value="经销商门店GM email")
    private String gmEmail;
    @ApiModelProperty(name="smName",value="经销商门店SM姓名")
    private String smName;
    @ApiModelProperty(name="smMobile",value="经销商门店SM手机号码")
    private String smMobile;
    @ApiModelProperty(name="smPhone",value="经销商门店SM电话")
    private String smPhone;
    @ApiModelProperty(name="smPsw",value="SM维护密码")
    private String smPsw;
    @ApiModelProperty(name="smEamil",value="经销商门店SM email")
    private String smEamil;
    @ApiModelProperty(name="fmName",value="经销商门店FM姓名")
    private String fmName;
    @ApiModelProperty(name="fmMobile",value="经销商门店FM手机号码")
    private String fmMobile;
    @ApiModelProperty(name="fmPhone",value="经销商门店FM电话")
    private String fmPhone;
    @ApiModelProperty(name="fmEamil",value="经销商门店FM email")
    private String fmEamil;
    @ApiModelProperty(name="fmPsw",value="FM维护密码")
    private String fmPsw;
    @ApiModelProperty(name="mmName",value="经销商门店MM姓名")
    private String mmName;
    @ApiModelProperty(name="mmMobile",value="经销商门店MM手机号码")
    private String mmMobile;
    @ApiModelProperty(name="mmPhone",value="经销商门店MM电话")
    private String mmPhone;
    @ApiModelProperty(name="mmEamil",value="经销商门店MM email")
    private String mmEamil;
    @ApiModelProperty(name="saleName",value="经销商门店售后姓名")
    private String saleName;
    @ApiModelProperty(name="saleMobile",value="经销商门店售后手机号码")
    private String saleMobile;
    @ApiModelProperty(name="salePhone",value="经销商门店售后电话")
    private String salePhone;
    @ApiModelProperty(name="saleEmail",value="经销商门店售后 email")
    private String saleEmail;
    @ApiModelProperty(name="trainName",value="经销商门店内训师姓名")
    private String trainName;
    @ApiModelProperty(name="trainMobile",value="经销商门店内训师手机号码")
    private String trainMobile;
    @ApiModelProperty(name="trainPhone",value="经销商门店内训师电话")
    private String trainPhone;
    @ApiModelProperty(name="trainEmail",value="经销商门店内训师 email")
    private String trainEmail;
    @ApiModelProperty(name="clcType",value="CLC Review时间")
    private String clcType;
    @ApiModelProperty(name="serverInvoiceName",value="经销商门店服务费发票名称")
    private String serverInvoiceName;
    @ApiModelProperty(name="serverInvoiceType",value="经销商门店服务费发票类型")
    private String serverInvoiceType;
    @ApiModelProperty(name="dealerEmail",value="经销商门店电子邮件地址")
    private String dealerEmail;
    @ApiModelProperty(name="contactPerson",value="经销商门店联系人")
    private String contactPerson;
    @ApiModelProperty(name="accountName",value="经销商门店账户名")
    private String accountName;
    @ApiModelProperty(name="remarks1",value="经销商门店电话")
    private String remarks1;
    @ApiModelProperty(name="interestRate",value="Interest Rate")
    private String interestRate;
    @ApiModelProperty(name="provinceRate",value="Province Rate")
    private String provinceRate;
    @ApiModelProperty(name="status",value="域状态  1-有效,  0-无效")
    private String status;
    @ApiModelProperty(name="onlineTime",value="创建时间")
    private String onlineTime;
    @ApiModelProperty(name="updateTime",value="更新时间")
    private String updateTime;
    @ApiModelProperty(name="remarks",value="备注")
    private String remarks;
    @ApiModelProperty(name="saleArea",value="门店所属销售分区")
    private String saleArea;
    @ApiModelProperty(name="province",value="所属省份")
    private String province;
    @ApiModelProperty(name="city",value="所属城市")
    private String city;
    @ApiModelProperty(name="recAccountBank2",value="收款开户银行")
    private String recAccountBank2;
    @ApiModelProperty(name="accountType",value="账户类型")
    private String accountType;
    @ApiModelProperty(name="bankMobile",value="经销商银行卡手机预留号码 ")
    private String bankMobile;
    @ApiModelProperty(name="lineNumber",value="联行号")
    private String lineNumber;
    @ApiModelProperty(name="isFraudFlight",value="秒拒接口（1:正常 0:禁用） ")
    private String isFraudFlight;
    @ApiModelProperty(name="bankProvinceRegCode",value="收款开户行所属省份reg_code")
    private String bankProvinceRegCode;
    @ApiModelProperty(name="bankCityRegCode",value="收款开户行所属市reg_code")
    private String bankCityRegCode;
    @ApiModelProperty(name="provinceRegCode",value="所属省份reg_code")
    private String provinceRegCode;
    @ApiModelProperty(name="cityRegCode",value="所属城市reg_code")
    private String cityRegCode;
    @ApiModelProperty(name="companyName",value="公司名称")
    private String companyName;
    @ApiModelProperty(value = "类型描述")
    private String dealerTypeDesc;

}
