package org.zmsoft.framework.utils;

import java.math.BigDecimal;

public class NumberHelper {
    /**
     * 等于
     * @param b1
     * @param b2
     * @return
     */
    public static boolean eq(BigDecimal b1, BigDecimal b2) {
        if (null == b1 || null == b2) {
            return false;
        }
        return b1.compareTo(b2) == 0;
    }

    /**
     * 大于
     * @param b1
     * @param b2
     * @return
     */
    public static boolean gt(BigDecimal b1, BigDecimal b2) {
        if (null == b1 || null == b2) {
            return false;
        }
        return b1.compareTo(b2) > 0;
    }

    /**
     * 小于
     * @param b1
     * @param b2
     * @return
     */
    public static boolean lt(BigDecimal b1, BigDecimal b2) {
        if (null == b1 || null == b2) {
            return false;
        }
        return b1.compareTo(b2) < 0;
    }

    /**
     * 大于等于
     * @param b1
     * @param b2
     * @return
     */
    public static boolean ge(BigDecimal b1, BigDecimal b2) {
        if (null == b1 || null == b2) {
            return false;
        }
        return b1.compareTo(b2) >= 0;
    }

    /**
     * 小于等于
     * @param b1
     * @param b2
     * @return
     */
    public static boolean le(BigDecimal b1, BigDecimal b2) {
        if (null == b1 || null == b2) {
            return false;
        }
        return b1.compareTo(b2) <= 0;
    }
}
