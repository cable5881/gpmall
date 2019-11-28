package com.gpmall.commons.tool.utils;

import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * @author jzx
 * @Description
 * @Date: 2019-08-14 22:21
 */
public class NumberUtils {

    /**
     * 保留二位小数
     * @param data
     * @return
     */
    public static double toDouble(BigDecimal data) {
        return data.setScale(2, BigDecimal.ROUND_DOWN).doubleValue();
    }

    public static float toFloat(float f) {
        return Float.valueOf(new DecimalFormat("0.00").format(f));
    }

}
