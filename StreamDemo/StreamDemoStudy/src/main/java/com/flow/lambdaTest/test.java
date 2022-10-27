package com.flow.lambdaTest;


import java.util.Collections;
import java.util.Comparator;
import java.util.TreeSet;
import java.util.function.Predicate;

public class test {
    public static void main(String[] args) {

        TreeSet treeSet = new TreeSet(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {

                return o1.compareTo(o2);
            }
        });
        Collections.addAll(treeSet, "刘", "关", "张");
        System.out.println(treeSet);
        Predicate<String> predicate = new Predicate<String>() {
            @Override
            public boolean test(String s) {
                return false;
            }

            @Override
            public Predicate<String> and(Predicate<? super String> other) {
                return Predicate.super.and(other);
            }

            @Override
            public Predicate<String> negate() {
                return Predicate.super.negate();
            }

            @Override
            public Predicate<String> or(Predicate<? super String> other) {
                return Predicate.super.or(other);
            }
        };
    }
}