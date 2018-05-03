package com.mokylin.CodeMaker;

public class Naming {
    /**
     * 转换到大骆驼命名法名称
     */
    public static String toAdultCamelNaming(String name) {
        String java_name = "";
        char last_ch = name.charAt(0);

        for (int i = 0; i < name.length(); i++) {
            if (i == 0)    //首字母大写
            {
                java_name = String.valueOf(last_ch).toUpperCase();
                continue;
            }

            if (name.charAt(i) == '_') {
                last_ch = '_';
                continue;
            }

            if (last_ch == '_') {
                last_ch = name.charAt(i);
                java_name += String.valueOf(last_ch).toUpperCase();
                continue;
            }

            last_ch = name.charAt(i);
            java_name += last_ch;
        }

        return java_name;
    }

    /**
     * 转换到小骆驼命名法名称
     */
    public static String toCamelNaming(String name) {
        String java_name = "";
        char last_ch = name.charAt(0);

        for (int i = 0; i < name.length(); i++) {
            if (i == 0)    //首字母小写
            {
                java_name = String.valueOf(last_ch).toUpperCase();
                continue;
            }

            if (name.charAt(i) == '_') {
                last_ch = '_';
                continue;
            }

            if (last_ch == '_') {
                last_ch = name.charAt(i);
                java_name += String.valueOf(last_ch).toUpperCase();
                continue;
            }

            last_ch = name.charAt(i);
            java_name += last_ch;
        }

        return java_name;
    }

    public static String removeUnderline(String str) {    //去掉下划线
        return str.replace("_", "");
    }

    /**
     * 转换用户数据类型到Java常规类型
     */
    public static String convertToJavaType(String origin_type) {
        final String lower_type=origin_type.toLowerCase();

        switch (lower_type) {
            case "bool":
            case "boolean":
                return "boolean";
            case "byte":
            case "uint8":
                return "byte";
            case "short":
            case "int16":
                return "short";
            case "int":
            case "int32":
                return "int";
            case "long":
            case "int64":
                return "long";
            case "float":
                return "float";
            case "double":
                return "double";
            case "date":
            case "date_time":
                return "Date";
            case "string":
            case "String":          //由于java中就没有小写的string，一开始string不会转换成String，所以在合并字段时，会走到这一行
                return "String";
            default:
                return origin_type;
        }
    }

    /**
     * 转换用户数据类型到Java对象类型
     */
    public static String convertToJavaObjectType(String origin_type) {
        final String lower_type=origin_type.toLowerCase();

        switch (lower_type) {
            case "bool":
            case "boolean":
                return "Boolean";
            case "byte":
            case "uint8":
                return "Byte";
            case "short":
            case "int16":
                return "Short";
            case "int":
            case "int32":
                return "Integer";
            case "long":
            case "int64":
                return "Long";
            case "float":
                return "Float";
            case "double":
                return "Double";
            case "date":
            case "date_time":
                return "Date";
            case "string":
            case "String":          //由于java中就没有小写的string，一开始string不会转换成String，所以在合并字段时，会走到这一行
                return "String";
            default:
                return origin_type;
        }
    }

    /**
     * 转换用户数据类型到ProtoBuffer类型
     */
    public static String convertToProtoType(String origin_type) {

        final String lower_type=origin_type.toLowerCase();

        switch (lower_type) {
            case "bool":
            case "boolean":
                return "bool";
            case "byte":
            case "short":
            case "int":
                return "int32";
            case "long":
                return "int64";
            case "float":
                return "float";
            case "double":
                return "double";
            case "date":
            case "datetime":
                return "int64";
            case "string":
                return "string";
            default:
                return origin_type;
        }
    }

    /**
     * 将名称转换为全小写使用下划线分隔的形式，专供proto buffer使用
     */
    public static String convertToProtoName(String str) {
        char ch = str.charAt(0);    //获取第一个字符
        String result = String.valueOf(ch).toLowerCase();  //转小写后赋值

        for (int i = 1; i < str.length(); i++)    //遍历 字符串
        {
            ch = str.charAt(i);    //获得每一个字符

            if (ch >= 'A' && ch <= 'Z')    //大写
            {
                result += '_';      //转小写后拼接
                result += String.valueOf(ch).toLowerCase();
            } else {
                result += String.valueOf(ch);   //拼接
            }
        }

        return result;
    }
}
