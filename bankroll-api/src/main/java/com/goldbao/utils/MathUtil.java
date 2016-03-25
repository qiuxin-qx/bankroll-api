package com.goldbao.utils;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

public class MathUtil {

    /**
     * 0.00
     */
    public static final BigDecimal ZERO = format("0");

    /**
     * 格式化一个数值为值+两位小数点
     */
    public static BigDecimal format(Object scale) {
        return format(scale, 2);// 取小数点后2位   /ROUND_HALF_UP
    }

    /**
     * 格式化一个数值为值+bit位小数点
     */
    public static BigDecimal format(Object scale, int bit) {
        BigDecimal bd = null;
        try {
            bd = new BigDecimal(scale.toString());
        } catch (Exception ex) {
            bd = new BigDecimal("0");
        }
        bd = bd.setScale(bit,BigDecimal.ROUND_HALF_UP);   // 取小数点后bit位   /ROUND_HALF_UP
        return bd;
    }

    public static BigDecimal floor(Object scale, int bit) {
        BigDecimal bd = new  BigDecimal(scale.toString());
        bd = bd.setScale(bit,BigDecimal.ROUND_FLOOR);   // 取小数点后bit位   /ROUND_FLOOR
        return bd;
    }

    public static BigDecimal floor(Object scale) {
        return floor(scale, 0);
    }


    public static BigDecimal yuanToFen(Object scale) {
        BigDecimal fen = new BigDecimal(scale.toString());
        fen = fen.multiply(format("100", 0));
        return fen;
    }

    /**
     * 两数相加
     */
    public static BigDecimal add(Object v1, Object v2) {
        BigDecimal value1 = new BigDecimal(v1.toString());
        BigDecimal value2 = new BigDecimal(v2.toString());
        value1 = value1.add(value2);
        return format(value1);
    }

    /**
     * 两数相减
     */
    public static BigDecimal subtract(Object v1, Object v2) {
        BigDecimal value1 = new BigDecimal(v1.toString());
        BigDecimal value2 = new BigDecimal(v2.toString());
        value1 = value1.subtract(value2);
        return format(value1);
    }
    /**
     * 两数相除
     */
    public static BigDecimal divide(Object v1, Object v2) {
        MathContext mc = new MathContext(10, RoundingMode.HALF_DOWN);
        BigDecimal value1 = new BigDecimal(v1.toString());
        BigDecimal value2 = new BigDecimal(v2.toString());
        value1 = value1.divide(value2, mc);
        return (value1);
    }
    /**
     * 两数相乘
     */
    public static BigDecimal multiply(Object v1, Object v2) {
        BigDecimal value1 = new BigDecimal(v1.toString());
        BigDecimal value2 = new BigDecimal(v2.toString());
        value1 = value1.multiply(value2);
        return (value1);
    }


    /**
     * v1大于v2返回true
     * @param v1
     * @param v2
     * @return
     */
    public static boolean gt(Object v1, Object v2) {
        BigDecimal value1 = format(v1);
        BigDecimal value2 = format(v2);
        return value1.compareTo(value2) == 1;
    }


}
