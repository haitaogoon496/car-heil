package com.mljr.heil;

import java.util.StringJoiner;

/**
 * @description:
 * @Date : 2018/7/25 下午8:34
 * @Author : 石冬冬-Seig Heil(dongdong.shi@mljr.com)
 */
public class Test {
    public static void main(String[] args) {
         StringJoiner sj = new StringJoiner("','", "'", "'");
         sj.add("A").add("B").add("C");
         String desiredString = sj.toString();
         System.out.println(desiredString);

        System.out.println(String.format("%1$s%2$s%3$04d","R","WZ",999));
        System.out.println(String.format("%1$s%2$s%3$04d","D","BH",12));
        System.out.println(String.format("%1$s%2$s%3$04d","C","QT",100));
    }
}
