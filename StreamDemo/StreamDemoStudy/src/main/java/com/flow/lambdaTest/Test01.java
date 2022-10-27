package com.flow.lambdaTest;

import java.util.function.IntPredicate;

/**
 * liudong
 * 指定数组判断奇偶
 **/
public class Test01 {
    public static void main(String[] args) {
printNum(new IntPredicate() {
    @Override
    public boolean test(int value) {
        return value%2==0;
    }
});
printNum(value -> value%2==0);
    }
    public static void printNum(IntPredicate intPredicate){
        int arr[]={1,2,3,4,56,6};
        for (int i : arr) {
            if (intPredicate.test(i))
            System.out.println(i);
        }
    }
    public void s(int a){
        System.out.println();
    }
    public void s(int a,int b){
        System.out.println();
    }
}
