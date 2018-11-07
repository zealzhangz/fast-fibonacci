package com.zealzhangz.fibonacci;

import com.google.common.collect.Lists;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

/**
 * @author Created by zhanga/zealzhangz@gmail.com.<br/>
 * @version Version: 1.0
 * @date DateTime: 2018/11/07 19:34:00<br/>
 */
public class TestJavaFibonacci {
    /**
     * The slowest algorithm
     * @param n
     * @return
     */
    public static long defaultFib(int n) {
        if (n < 3) return 1;
        return defaultFib(n - 1) + defaultFib(n - 2);
    }

    @Test
    public void testDefaultFib(){
        Integer i = 50;
        Long start = System.currentTimeMillis();
        System.out.println("The " + i + "th Fibonacci is " + defaultFib(i));
        System.out.println("Time(ms): " + (System.currentTimeMillis() - start));
    }


    /**
     * The cache algorithm
     */
    public static Map<Integer,BigDecimal> fibCache = new HashMap<>();
    public static BigDecimal cacheFib(Integer n){
        if (n < 3){
            return new BigDecimal(1);
        }
        Function<Integer,BigDecimal> compute = j -> {
            BigDecimal left = cacheFib(j - 1);
            BigDecimal right = cacheFib(j - 2);
            BigDecimal result =  left.add(right);
            fibCache.put(n,result);
            return result;
        };
        return fibCache.computeIfAbsent(n,compute);
    }

    @Test
    public  void testCacheFib(){
        Integer i = 2000;
        Long start = System.currentTimeMillis();
        System.out.println("The " + i + "th Fibonacci is " + cacheFib(i));
        System.out.println("Time(ms): " + (System.currentTimeMillis() - start));
    }

    /**
     * By List
     */
    public static BigDecimal listCalcFib(Integer n) {
        if (n < 3) {
            return new BigDecimal(1);
        }
        List<BigDecimal> result = Lists.newArrayListWithExpectedSize(n - 1);
        result.add(new BigDecimal(1));
        result.add(new BigDecimal(1));

        for (int j = 2; j < n; j++) {
            result.add(result.get(j - 2).add(result.get(j - 1)));
        }
        return result.get(n - 1);
    }

    @Test
    public void testListCalcFib() {
        Integer i = 400000;
        Long start = System.currentTimeMillis();
        System.out.println("The " + i + "th Fibonacci is " + listCalcFib(i));
        System.out.println("Time(ms): " + (System.currentTimeMillis() - start));
    }

    /**
     * By Array
     */
    public static BigDecimal arrayCalcFib(Integer n) {
        if (n < 3) {
            return new BigDecimal(1);
        }
        BigDecimal[] result = new BigDecimal[n];
        result[0] = new BigDecimal(1);
        result[1] = new BigDecimal(1);

        for (int j = 2; j < n; j++) {
            result[j] = result[j - 2].add(result[j - 1]);
        }
        return result[n - 1];
    }

    @Test
    public void testArrayCalcFib() {
        Integer i = 400000;
        Long start = System.currentTimeMillis();
        System.out.println("The " + i + "th Fibonacci is " + arrayCalcFib(i));
        System.out.println("Time(ms): " + (System.currentTimeMillis() - start));
    }
    /**
     * By local variable
     */
    public static BigDecimal tmpVariableCalcFib(Integer n) {
        if (n < 3) {
            return new BigDecimal(1);
        }
        BigDecimal  result = new BigDecimal(0);
        BigDecimal first = new BigDecimal(1);
        BigDecimal second = new BigDecimal(1);

        for (int j = 2; j < n; j++) {
            result = first.add(second);
            first = second;
            second = result;
        }
        return result;
    }

    @Test
    public void testTmpVariableCalcFib() {
        Integer i = 4000000;
        Long start = System.currentTimeMillis();
        System.out.println("The " + i + "th Fibonacci is " + tmpVariableCalcFib(i));
        System.out.println("Time(ms): " + (System.currentTimeMillis() - start));
    }

}
