package com.mljr.heil.common.util;

import org.apache.commons.codec.digest.DigestUtils;

import java.util.Date;

public class TokenUtil {

    /**
     * 生成token
     * @param value
     * @return
     */
    public static String generateToken(String value) {
        Long currentTime = new Date().getTime();
        String tmpStr = String.valueOf(value) + currentTime;
        String token = DigestUtils.md5Hex(tmpStr);
        return token;
    }

    public static void main(String args[]){

        String v = "kBIN1rNMXp7YbZhtdxlhMw==|qwer|10a4119fc42d2bbaacabf50f90178829";

        String[] v1 = v.split("\\|");
        System.out.println(generateToken("13120076700"));
    }

}
