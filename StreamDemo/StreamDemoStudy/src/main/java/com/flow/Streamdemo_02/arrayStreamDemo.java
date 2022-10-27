package com.flow.Streamdemo_02;

import java.util.stream.Stream;

/**
 * liudong
 * 数组创建Stream流
 **/
public class arrayStreamDemo {
    public static void main(String[] args) {
        Integer[]ints={1,22,33,4566,6};
//        IntStream stream = Arrays.stream(ints);
        Stream<Integer> stream = Stream.of(ints);
        stream.forEach(integer-> System.out.println(integer));
    }
}
