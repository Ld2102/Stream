package com.flow.Streamdemo_02;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 * liudong
 * map创建Stream流
 **/
public class mapStreamDemo {
    public static void main(String[] args) {
        Map<String,Integer>map=new HashMap<>();
        map.put("雪代巴", 18);
        map.put("慕南栀",25);
        map.put("怀庆",18);
        //map key,value转为单列集合
        Set<Map.Entry<String, Integer>> entries = map.entrySet();
        //输出年龄小于20的所有信息
         entries.stream()
                .filter(new Predicate<Map.Entry<String, Integer>>() {
                    @Override
                    public boolean test(Map.Entry<String, Integer> stringIntegerEntry) {
                        return stringIntegerEntry.getValue()<20;
                    }
                })
                .forEach(new Consumer<Map.Entry<String, Integer>>() {
                    @Override
                    public void accept(Map.Entry<String, Integer> stringIntegerEntry) {
                        System.out.println(stringIntegerEntry.getKey()+"--"+stringIntegerEntry.getValue());
                    }
                });
    }
}
