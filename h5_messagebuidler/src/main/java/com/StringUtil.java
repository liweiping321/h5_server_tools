package com;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by liweiping on 2018/4/13.
 */
public class StringUtil {
    private static  Pattern p=Pattern.compile("[A-Z]");
    public static String upperCharToUnderLine(String param) {

        if(param==null ||param.equals("")){
            return "";
        }
        StringBuilder builder=new StringBuilder(param);
        Matcher mc=p.matcher(param);
        int i=0;
        while (mc.find()) {
            builder.replace(mc.start()+i, mc.end()+i, "_"+mc.group().toLowerCase());
            i++;
        }

        if('_' == builder.charAt(0)){
            builder.deleteCharAt(0);
        }
        return builder.toString();
    }

}
