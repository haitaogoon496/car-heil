package com.mljr.heil.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

public class IdCardNoValidateUtils {

    private static Logger logger = LoggerFactory.getLogger(IdCardNoValidateUtils.class);

    public static  void main(String[] args) {
    	String num="510623197307181691";
        IdCardNoValidateUtils utils=new IdCardNoValidateUtils();
           System.out.println(utils.checkIdNum(num));

    }
    public static Boolean checkIdNum(String num){
    	 char[] id = {};
         for (int i = 0; i < num.length(); i++) {
             id = Arrays.copyOf(id, id.length + 1);
             id[id.length - 1] = num.charAt(i);
         }
         boolean IsFormRight = verForm(num);
         if (IsFormRight) {
             boolean IsCorrect = verify(id);
             if (IsCorrect) {
                 logger.info("IdCardNo is right!" + num);
                 return true;
             }
             else {
                 logger.error("IdCardNo is wrong!" + num);
                 return false;
             }
         }else{
        	 return false;
         }
    }
    public static boolean verForm(String num) {
        String reg = "^\\d{15}$|^\\d{17}[0-9Xx]$";
        if (!num.matches(reg)) {
            logger.error("IdCardNo Format Error!" + num);
            return false;
        }
        return true;
    }
    public static boolean verify(char[] id) {
        int sum = 0;
        int w[] = { 7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2 };
        char[] ch = { '1', '0', 'X', '9', '8', '7', '6', '5', '4', '3', '2' };
        for (int i = 0; i < id.length - 1; i++) {
            sum += (id[i] - '0') * w[i];
        }
        int c = sum % 11;
        char code = ch[c];
        char last = id[id.length-1];
        last = last == 'x' ? 'X' : last;
        return last == code;
    }

}