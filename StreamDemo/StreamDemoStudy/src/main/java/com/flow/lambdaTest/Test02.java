package com.flow.lambdaTest;

import java.util.function.Function;

/**
 * liudong
 * 字符串转数字
 **/
public class Test02 extends Test01{
    public static void main(String[] args) {
        Integer typeconver = typeconver(aa -> Integer.valueOf(aa));
        System.out.println(typeconver);
    }
    public static <R>R typeconver(Function<String,R>function){
        String str="12345";
        R apply = function.apply(str);
        return apply;
    }
}
