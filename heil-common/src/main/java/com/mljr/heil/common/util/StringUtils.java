package com.mljr.heil.common.util;

import ch.qos.logback.core.net.SyslogOutputStream;
import net.sf.oval.ConstraintViolation;
import net.sf.oval.Validator;
import org.apache.log4j.Logger;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtils {
    private static Logger logger = Logger.getLogger(StringUtils.class);

    private StringUtils() {

    }

    public static String fmtMicrometer(String text) {
        String str = checkAmountNumber(text);
        DecimalFormat df = null;
        if (str.indexOf(".") > 0) {
            if (str.length() - str.indexOf(".") - 1 == 0) {
                df = new DecimalFormat("###,##0.");
            } else {
                df = new DecimalFormat("###,##0.00");
            }
        } else {
            df = new DecimalFormat("###,##0.00");
        }
        double number = 0.0;
        try {
            number = Double.parseDouble(text);
        } catch (Exception e) {
            logger.info(e);
            number = 0.0;
        }
        return df.format(number);
    }

    public static BigDecimal str2BigDecimal(String value) {
        return new BigDecimal(value);
    }

    public static String bigDecimal2String(BigDecimal d) {
        if (d == null) {
            return "";
        } else {
            return StringUtils.fmtMicrometer(d.toPlainString());
        }
    }

    public static String bigDecimal2String(Double d) {
        BigDecimal bigDecimal = new BigDecimal(d);
        if (d == null) {
            return "0.00";
        } else {
            return StringUtils.fmtMicrometer(bigDecimal.toPlainString());
        }
    }
    
    

    public static BigDecimal string2bigDecimal(String d) {
        if ("".equals(d)) {
            return new BigDecimal("0");
        } else {
            return new BigDecimal(d);
        }
    }

    public static String bigDecimal2StringForIOS(BigDecimal amount) {
        if (amount == null) {
            return "0万";
        } else {
            MathContext mc = new MathContext(6, RoundingMode.HALF_UP);
            BigDecimal result = amount.divide(new BigDecimal("10000"), mc);
            return result.toPlainString() + "万";
        }
    }

    public static String subStr2(BigDecimal value) {
        String v = checkPercentNumber(value);
        if (v.length() > 2) {
            return v.substring(0, v.length() - 2);
        }
        return v;
    }

    public static String subStr2(BigDecimal value, String unit) {
        String v = checkPercentNumber(value);
        if (v.length() > 2) {
            return v.substring(0, v.length() - 2) + unit;
        }
        return v + unit;
    }

    public static String checkAmountNumber(BigDecimal value) {
        if (null == value || "".equals(value.toPlainString())) {
            return "0.00";
        }
        return value.toPlainString();
    }

    public static String checkAmountNumber(String value) {
        if (null == value || "".equals(value)) {
            return "0.00";
        }
        return value;
    }

    public static String checkPercentNumber(BigDecimal value) {
        if (null == value || "".equals(value.toPlainString())) {
            return "0.0000";
        }
        return value.toPlainString();
    }

    /**
     * 判断是否是空字符串 null和"" 都返回 true
     * 
     * @author Robin Chang
     * @param s
     * @return
     */
    public static boolean isEmpty(String s) {
        if (s != null && !"".equals(s)) {
            return false;
        }
        return true;
    }
    
    /**
     * 判断是否是空字符串 null和"" 都返回 true
     * 
     * @author Robin Chang
     * @param obj
     * @return
     */
    public static boolean isEmpty(Object obj) {
        if ("".equals(killNull(obj))) {
            return true;
        }
        return false;
    }

    public static String toProgress(BigDecimal decimal) {
        if (null == decimal || "".equals(decimal)) {
            return "";
        }
        String multifarious = "%";
        BigDecimal multiplicand = new BigDecimal("100");
        String result = decimal.multiply(multiplicand).toPlainString();
        int index = result.indexOf(".");
        String rate = "";
        if (index != -1) {
            rate = result.substring(0, index + 3);
        }
        return rate + multifarious;

    }

    public static String toProgressByBit(BigDecimal decimal) {
        if (null == decimal || "".equals(decimal)) {
            return "";
        }
        String multifarious = "%";
        BigDecimal multiplicand = new BigDecimal("100");
        String result = decimal.multiply(multiplicand).toPlainString();
        int index = result.indexOf(".");
        String rate = "";
        if (index != -1) {
            rate = result.substring(0, index);
        }
        return rate + multifarious;

    }

    /**
     * 将数据库中查询来利率转换为万分之几
     * 
     * @param decimal
     * @return
     */
    public static String toCreditRate(BigDecimal decimal) {
        if (null == decimal || "".equals(decimal)) {
            return "";
        }
        String multifarious = "万分之";
        BigDecimal multiplicand = new BigDecimal("10000").multiply(decimal);
        String temp = new DecimalFormat("#0.00").format(multiplicand.doubleValue());
        return multifarious + temp;

    }

    /**
     * 将数据库中查询来利率或者接口传过来的利率转换为万分之几
     *
     * @param interestRate
     * @return
     */
    public static String toCreditRate(String interestRate) {
        if (isEmpty(interestRate)) {
            return "";
        }
        String multifarious = "万分之";
        BigDecimal multiplicand = new BigDecimal("10000").multiply(new BigDecimal(interestRate));
        String temp = new DecimalFormat("#0.##").format(multiplicand.doubleValue());
        return multifarious + temp;

    }
    /**
     * 将数据库中查询来利率或者接口传过来的利率转换为万分之几
     * 各利率为日利率，记为万分之XX.X,保留1位小数，四舍五入；例如，万分之3.289，记为万分之3.3
     * @param decimal
     * @return
     */
    public static String toCreditInterestRate(BigDecimal decimal) {
        if (null == decimal || "".equals(decimal)) {
            return "";
        }
        String multifarious = "万分之";
        BigDecimal multiplicand = new BigDecimal("10000").multiply(decimal);
        String temp = new DecimalFormat("#0.#").format(multiplicand.doubleValue());
        return multifarious + temp;
    }

    public static String toCreditRateNofomat(BigDecimal amount) {
        if (amount == null) {
            return "0";
        } else {
            MathContext mc = new MathContext(10, RoundingMode.HALF_UP);
            BigDecimal result = amount.multiply(new BigDecimal("10000"), mc);
            return subZeroAndDot(result.toPlainString());
        }
    }

    public static String toRate(BigDecimal decimal) {
        if (null == decimal || "".equals(decimal)) {
            return "";
        }
        BigDecimal multiplicand = new BigDecimal("100").multiply(decimal);
        String temp = new DecimalFormat("#0.00").format(multiplicand.doubleValue());
        return temp;

    }

    public static String doubleToRate(Double decimal) {

        if (null == decimal || "".equals(decimal)) {
            return "";
        }
        BigDecimal newBigdecimal = new BigDecimal(decimal);
        String temp = new DecimalFormat("#0.00").format(newBigdecimal.doubleValue());
        return temp;

    }

    public static String toRateWithUnit(BigDecimal decimal) {
        if (null == decimal || "".equals(decimal)) {
            return "";
        }
        String multifarious = "%";
        BigDecimal multiplicand = new BigDecimal("100").multiply(decimal);
        String temp = new DecimalFormat("#0.00").format(multiplicand.doubleValue());
        return temp + multifarious;

    }
    /**
     * 百分率只取整数部分
     * */
    public static String toGuaranteeRateWithUnit(BigDecimal decimal) {
        if (null == decimal || "".equals(decimal)) {
            return "";
        }
        String multifarious = "%";
        BigDecimal multiplicand = new BigDecimal("100").multiply(decimal);
        String temp = new DecimalFormat("#").format(multiplicand.doubleValue());
        return temp + multifarious;

    }

    /**
     * 百分率只取整数部分（不乘100）
     * */
    public static String toPercentageWithUnit(BigDecimal decimal) {
        if (null == decimal || "".equals(decimal)) {
            return "";
        }
        String multifarious = "%";
        String temp = new DecimalFormat("#").format(decimal);
        return temp + multifarious;
    }


    public static String toPeriodWithDays(Integer period) {
        if (null == period || "".equals(period)) {
            return "";
        }
        String multifarious = "天";
        return period.toString() + multifarious;

    }

    public static String toAmountWithUnit(BigDecimal amount) {
        if (null == amount || "".equals(amount)) {
            return "";
        }
        return bigDecimal2String(amount) + "元";

    }

    public static String toRateAlias(BigDecimal decimal, String unit) {
        if (null == decimal || "".equals(decimal)) {
            return "";
        }
        String multifarious = "%";
        BigDecimal multiplicand = new BigDecimal("100");
        String result = decimal.multiply(multiplicand).toPlainString();
        int index = result.indexOf(".");
        String rate = "";
        if (index != -1) {
            rate = result.substring(0, index + 3);
        }
        return rate + multifarious + "/" + unit;

    }

    public static String toFlag(String flag) {
        if ("Y".equals(flag)) {
            return "是";
        } else if ("N".equals(flag)) {
            return "否";
        }
        return "";
    }

    public static String killNull(String text) {
        return text == null ? "" : text;
    }

    public static String killNull(Object obj) {
        return obj == null ? "" : obj.toString();
    }

    public  static String killNull(Double d){
        if(d == null) return  "";
        BigDecimal bigDecimal = new BigDecimal(d);
        if(bigDecimal.equals(new BigDecimal("0"))){
            return "0";
        }
       return bigDecimal.toPlainString();
    }

    public static String killNullByAmount(String text) {
        return text == null ? "0" : text;
    }

    public static String killNullForDouble(String text) {
    	if("".equals(text)){
    		return "0";
    	}else{
    		return text;
    	}
    }

    public static String killNull(Long longVal) {
        return longVal == null ? "" : String.valueOf(longVal);
    }

    public static String killNull(Integer longVal) {
        return longVal == null ? "" : String.valueOf(longVal);
    }

    public static String maskString(String msg) {
        if (msg != null) {
            StringBuffer sb = new StringBuffer(msg.length());
            if (msg.length() < 3) {
                return msg;
            } else {
                sb.append(msg.substring(0, 1));
                for (int i = 0; i < msg.length() - 2; i++) {
                    sb.append("*");
                }
                sb.append(msg.substring(msg.length() - 1, msg.length()));
                return sb.toString();
            }
        } else {
            return "";
        }
    }

    public static String maskIdNo(String idNo) {
        if ((idNo == null) || (idNo.length() < 10)) {
            throw new RuntimeException("Invaid id no.");
        }

        String prefix = idNo.substring(0, 3);
        String postfix = idNo.substring(idNo.length() - 3);

        return prefix + "************" + postfix;
    }

    public static String maskName(String name) {
        String subStr = "";
        if ((name == null) || (name.length() == 0)) {
            return subStr;
        }

        if (name.length() == 2) {
            subStr = name.substring(0, 1) + "*";
        } else if (name.length() == 3) {
            subStr = name.substring(0, 1) + "**";
        } else {
            subStr = name.substring(0, 1) + "***";
        }

        return subStr;
    }

    public static String maskBankAccountNo(String bankAccountNo) {
        if ((bankAccountNo == null) || (bankAccountNo.length() < 10)) {
            throw new RuntimeException("Invaid bank account no.");
        }

        String prefix = bankAccountNo.substring(0, 6);
        String postfix = bankAccountNo.substring(bankAccountNo.length() - 4);

        return prefix + "************" + postfix;
    }

    public static boolean regexLoginName(String loginName) {
        loginName = loginName.trim();
        boolean flag = loginName.matches("^(?!_)(?!.*?_$)[a-zA-Z0-9_\u4e00-\u9fa5]+$");
        return flag;
    }

    public static boolean regexMobile(String mobile) {
        if (null == mobile || "".equals(mobile) || mobile.length() < 11) {
            return false;
        } else {
            boolean flag = mobile.matches("^[0-9]*$");
            return flag;
        }
    }

    public static boolean regexIdNo(String idNo) {
        Pattern idNumPattern = Pattern.compile("(\\d{14}[0-9a-zA-Z])|(\\d{17}[0-9a-zA-Z])");
        Matcher idNumMatcher = idNumPattern.matcher(idNo);
        boolean flag = idNumMatcher.matches();
        return flag;
    }

    public static boolean regexEmail(String email) {
        boolean flag = email.matches(
                "^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$");
        return flag;
    }

    public static String maskLoginName(String loginName) {

        String subStr = "";
        if ((loginName == null) || (loginName.length() == 0)) {
            return subStr;
        }

        if (loginName.length() == 3) {
            subStr = loginName.substring(0, 1) + "**" + loginName.substring(2, loginName.length());
        } else {
            subStr = loginName.substring(0, 1) + "***"
                    + loginName.substring(loginName.length() - 2, loginName.length());
        }

        return subStr;
    }

    public static String maskMobile(String mobile) {
        String subMobile = "";
        if (null == mobile || "".equals(mobile) || mobile.length() < 11) {
            return "";
        } else {
            subMobile = mobile.substring(0, 3) + "****" + mobile.substring(7, 11);
        }

        return subMobile;
    }

    public static String maskEmail(String email) {
        String subEmail = "";
        if (null == email || "".equals(email) && !email.contains("@")) {
            return "";
        }
        int index = email.lastIndexOf("@");
        subEmail = "****" + email.substring(index, email.length());

        return subEmail;
    }

    public static String maskProjectName(String projectName) {
        if (null == projectName || "".equals(projectName)) {
            return "";
        }

        if (projectName.length() <= 15) {
            return projectName;
        } else {
            String maskName = projectName.substring(0, 15);
            return maskName + "...";
        }
    }

    /**
     * 生成流水号：规则：流水号前缀+当前时间+4位随机数字
     *
     * @param tmnNum
     *            流水号前缀
     * @return 指定格式的流水号
     */
    public static String genOrderNo(String tmnNum) {
        DateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
        String time = df.format(new Date());
        Long random = (long) (Math.random() * Math.pow(10, 4));
        return tmnNum + time + random.toString();
    }

    /**
     * 取出一对标签之间的值
     *
     * @param name
     *            标签名
     * @param xml
     * @return
     */
    public static String getTagValue(String name, String xml) {
        String start = "<" + name + ">";
        String end = "</" + name + ">";
        String value = xml.substring(xml.indexOf(start) + start.length(), xml.indexOf(end));
        return value;
    }

    /**
     * 取出一对标签值(包括那对标签符)
     *
     * @param name
     *            标签名
     * @param xml
     * @return
     */
    public static String getTagValueWithTag(String name, String xml) {
        String start = "<" + name + ">";
        String end = "</" + name + ">";
        String value = xml.substring(xml.indexOf(start), xml.indexOf(end) + end.length());
        return value;
    }

    public static String getCurrentTime() {
        DateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
        String time = df.format(new Date());
        return time;
    }

    public static String removeIt(String str) {
        if (str != null) {
            return str.replaceAll(" ", "");
        }
        return str;
    }

    public static String paddingPrefix(long oldValue, int length) {
        String f = "%0" + length + "d";
        return String.format(f, oldValue);
    }

    public static String getSexByIdNo(String idNo) {
        String sex = "";
        if ((idNo == null) || (idNo.length() == 0)) {
            return sex;
        }
        if (idNo.length() == 15) {
            String sexFlag = idNo.substring(14, idNo.length());
            sex = new Integer(sexFlag) % 2 == 1 ? "M" : "F";
            return sex;
        } else if (idNo.length() == 18) {
            String sexFlag = idNo.substring(16, idNo.length() - 1);
            sex = new Integer(sexFlag) % 2 == 1 ? "M" : "F";
            return sex;
        } else {
            return sex;
        }
    }

    public static int getAgeByIdNo(String idNo) {
        int age = 0;
        String year;
        if ((idNo == null) || (idNo.length() == 0)) {
            return age;
        }
        if (idNo.length() == 15) {
            year = "19" + idNo.substring(6, 8); // 1989
        } else if (idNo.length() == 18) {
            year = idNo.substring(6, 10); // 1989
        } else {
            return age;
        }
        Calendar now = Calendar.getInstance();
        int currentYear = now.get(Calendar.YEAR); // 2011
        age = currentYear - Integer.parseInt(year);
        return age;
    }

    /**
     * 读取excel中 费率4.8% 转换成 0.048
     *
     * @param str
     * @return
     */
    public static BigDecimal strturnToBigDec(double str) {
        BigDecimal dig = new BigDecimal(str);
        return dig;
    }

    /**
     * 判断str 是否是double类型
     *
     * @param str
     * @return
     */
    public static boolean isNumeric(String str) {
        boolean flag = true;
        try {
            double dou = Double.valueOf(str);
        } catch (Exception e) {
            flag = false;
            return flag;
        }
        return flag;
    }

    /**
     * 判断字符串是否是Integer类型
     *
     * @param str
     * @return
     */
    public static boolean isIntegeric(String str) {
        boolean flag = true;
        try {
            int dou = Integer.parseInt(str);
        } catch (Exception e) {
            flag = false;
            return flag;
        }
        return flag;
    }

    /**
     * 判断str 是否为日期类型 format为日期格式
     *
     * @param str
     * @param format
     * @return
     */
    public static boolean isValidDate(String str, String format) {
        boolean convertSuccess = true;
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        sdf.setLenient(false);
        try {
            sdf.parse(str);
        } catch (ParseException e) {
            convertSuccess = false;
            return convertSuccess;
        }
        return convertSuccess;
    }

    // add by zhaohongyan thousand
    public static String bigDecimalToThousand(BigDecimal amount) {
        if (amount == null) {
            return "0";
        } else {
            MathContext mc = new MathContext(10, RoundingMode.HALF_UP);
            BigDecimal result = amount.divide(new BigDecimal("10000"), mc);
            return result.toPlainString();
        }
    }

    /**
     * 判断票的格式是否正确
     *
     * @param str
     * @param // STOPSHIP: 2018/2/7
     * @return
     */
    public static boolean checkDoubleDec(double str) {
        String str_1 = String.valueOf(str);
        String str_2 = str_1.substring(str_1.indexOf(".") + 1, str_1.length());
        if (!(str_2.length() > 4)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * string数组转换为Long类型的数组
     *
     * @param strValue
     * @return
     */
    public static List<Long> stringArrToLongArr(String strValue, String regex) {
        List<Long> longList = new ArrayList<Long>();

        if (strValue != null && !"".equals(strValue)) {
            String[] strs = strValue.split(regex);
            for (String str : strs) {
                longList.add(Long.valueOf(str));
            }
        }
        return longList;
    }

    /**
     * 格式化金额，保留两位小数
     */
    public static BigDecimal decimalFormatFor2DecimalPlaces(BigDecimal amount) {
        return new BigDecimal(new DecimalFormat("#0.00").format(amount));
    }

    /**
     * 
     * getPageCount:分页查询获取页数
     * 
     * @author zhaohy
     * @param total
     * @return
     * @since JDK 1.7
     */
    public static int getPageCount(int total, int pageSize) {
        return total % pageSize == 0 ? total / pageSize : (total / pageSize + 1);
    }
    
    public static String getDurationUnit(String unit){
    	if("Y".equals(unit)){
    		return "年";
    	}else if("M".equals(unit)){
    		return "个月";
    	}else if("D".equals(unit)){
    		return "天";
    	}
    	return "";
    }

    public static void main(String[] args) {
        System.out.println(subZeroAndDot("1234.00"));
        System.out.println(bigdecimalToStringWithoutZero("1234.00"));
        System.out.println(new BigDecimal("100").toPlainString());
    }
    
    
    /** 
     * 使用java正则表达式去掉多余的.与0 
     * @param s 
     * @return  
     * @author zhiguo.wang
     */  
    public static String subZeroAndDot(String s){ 
    	if(isEmpty(s)){
    		return "0";
    	}
        if(s.indexOf(".") > 0){  
            s = s.replaceAll("0+?$", "");//去掉多余的0  
            s = s.replaceAll("[.]$", "");//如最后一位是.则去掉  
        }  
        return s;  
    }  
    
    /**
     * 去掉小数点后多余的0，同时","进行分隔
     * 
     * @param d
     * @return
     */
    public static String bigdecimalToStringWithoutZero(BigDecimal d){
    	if(d==null){
    		return "0";
    	}
    	DecimalFormat df=new DecimalFormat("###,###.##");
    	return df.format(d);
    }
    
    /**
     * 去掉小数点后多余的0，同时","进行分隔
     * 
     * @param s
     * @return
     */
    public static String bigdecimalToStringWithoutZero(String s){
    	if(isEmpty(s)){
    		return "0";
    	}
    	if(s.contains(",")){
    		return s;
    	}
    	DecimalFormat df=new DecimalFormat("###,###.##");
    	return df.format(new BigDecimal(s));
    }
    
    /**
     * 将元显示为万元，截掉小数位
     * 
     * @param decimal
     * @return
     */
    public static String fomatAmount(BigDecimal decimal) {
        if (null == decimal || "".equals(decimal)) {
            return "";
        }
        int value = decimal.divide(BigDecimal.valueOf(10000)).intValue();
        return killNull(value);
    }
    
    public static String getMinValue(String first,String second){
    	
    	if(new BigDecimal(first).compareTo(new BigDecimal(second))>-1){
    		return bigdecimalToStringWithoutZero(second);
    	}
    	return bigdecimalToStringWithoutZero(first);
    }
    
  //超过3位时前3位显示，@之前显示*号，如ceshi@126.com显示为ces***@126.com
  	public static String formatEmail(String email) {
  		String subEmail = "";
  		if (StringUtils.isEmpty(email) || !email.contains("@"))
  			return "";
  		if (email.split("@")[0].length() > 3)
  		{
  			int index = email.lastIndexOf("@");
  			subEmail = email.substring(0, 3) + "***" + email.substring(index, email.length());;
  			return subEmail;
  		}else{
  			return email;
  		}
  	}

  	public static Integer toInteger(String value,Integer defaultValue){
        return isEmpty(value) ?  defaultValue : Integer.valueOf(value);
    }

    public static Integer toInteger(String value){
        return isEmpty(value) ?  0 : Integer.valueOf(value);
    }

    public static Double toDouble(String value,Double defaultValue){
        return isEmpty(value) ?  defaultValue : Double.valueOf(value);
    }

    public static Double toDouble(String value){
        return isEmpty(value) ?  0 : Double.valueOf(value);
    }

    public static Long toLong(String value,Long defaultValue){
        return isEmpty(value) ?  defaultValue : Long.valueOf(value);
    }

    public static Long toLong(String value){
        return isEmpty(value) ?  0 : Long.valueOf(value);
    }

    public static BigDecimal toBigDecimal(String value,BigDecimal defaultValue){
        return isEmpty(value) ?  defaultValue : new BigDecimal(value);
    }

    public static BigDecimal toBigDecimal(String value){
        return isEmpty(value) ?  BigDecimal.ZERO : new BigDecimal(value);
    }

    public static String join(List<ConstraintViolation> list, String refix) {
        if (CollectionUtils.isEmpty(list)) {
            return "";
        }
        String result = null;
        for(int i=0;i<list.size();i++) {
            if (i == 0) {
                result = list.get(i).getMessage();
            }else{
                result = result + refix + list.get(i).getMessage();
            }
        }
        return result;
    }

}






