package com.yunxin.cb.util;

/**
 * Created by Aidy_He on 16/3/12.
 */
public class CalculateHelper {

    public static final String ADD = "+";
    public static final String SUBTRACT = "-";
    public static final String MULTIPLY = "*";
    public static final String DIVIDE = "/";

    public static double calculate(double a, double b, String op) {
        switch (op.trim()) {
            case "+":
                return a + b;
            case "-":
                return a - b;
            case "*":
                return a * b;
            case "/":
                return a / b;
        }
        return -1;
    }
}
