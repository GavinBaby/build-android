package com.anuode.build_andriod.modules.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class RegexUtil {

    //判断手机格式是否正确
    public static boolean isMobileNO(String mobiles) {
        Pattern p = Pattern
                .compile("^1[3-9]\\d{9}$");
        Matcher m = p.matcher(mobiles);
        return m.matches();
    }

    //判断email格式是否正确
    public static boolean isEmail(String email) {
        String str = "^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$";
        Pattern p = Pattern.compile(str);
        Matcher m = p.matcher(email);
        return m.matches();
    }

    //判断是否全是数字
    public static boolean isNumeric(String str) {
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(str);
        return isNum.matches();
    }

    /**
     * 格式化数字保留两位小数
     */
    public static String decimalFormat(Double num) {
        if(num == 0){
            return "0.00";
        }
        if(num<1){
            return num.toString();
        }
        return new java.text.DecimalFormat("##.00").format(num);
    }
}


