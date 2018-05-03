package com.mokylin.CodeMaker;

import static com.mokylin.CodeMaker.Naming.convertToJavaObjectType;
import static com.mokylin.CodeMaker.Naming.convertToJavaType;
import static com.mokylin.CodeMaker.Naming.convertToProtoType;

public class SheetField {
    private final String module_name;

    public TemplateEngine template_engine = new TemplateEngine();

    public String comment;      //注释
    public String name;         //字段名称              原始表格中的名称
    public boolean c, s;
    public char cs;
    public String type;         //变量类型              原始表格中的类型

    public String java_name;    //Java变量名称          小字字母开头的驼峰命名法名称
    public String java_type;    //Java变量类型          Java语法下的变量类型

    public String java_map_type;//Map类型               Java语法下的Map类型定义

    public String proto_name;   //ProtoBuffer变量名称   大写字母开头的驼峰命名法名称
    public String proto_type;   //ProtoBuffer类型描述   带repeated/optional等等的完整定义

    public String proto_key_type;
    public String proto_value_type;

    public boolean is_bool = false;
    public boolean is_string = false;
    public boolean is_map = false;
    public boolean is_date = false;

    public boolean is_list = false;
    public boolean is_set = false;

    public boolean is_merge = false;        //是否混合起来的

    public SheetField(String mn)
    {
        module_name=mn;
    }

    public void updateTemplateEngine() {
        template_engine.add("ORIGIN_NAME", name);
        template_engine.add("ORIGIN_TYPE", type);
        template_engine.add("JAVA_NAME", java_name);
        template_engine.add("JAVA_TYPE", java_type);
        template_engine.add("JAVA_MAP_TYPE", java_map_type);
        template_engine.add("PROTO_NAME", proto_name);
        template_engine.add("PROTO_TYPE", proto_type);
        template_engine.add("MODULE_PROTO_NAME", module_name+proto_name);
        template_engine.add("PROTO_MAP_KEY_TYPE", proto_key_type);
        template_engine.add("PROTO_MAP_VALUE_TYPE", proto_value_type);
    }

    public SheetField createListField()        //创建当前字段的List版本
    {
        SheetField lf = new SheetField(module_name);

        lf.comment = comment;
        lf.name = name;
        lf.c = c;
        lf.s = s;
        lf.cs = cs;

        lf.type = "List<" + type + ">";
        lf.java_name = java_name;
        lf.java_type = "List<" + convertToJavaObjectType(java_type) + ">";

        lf.proto_name = proto_name;
        lf.proto_type = "repeated " + convertToProtoType(type) + " " + java_name;

        lf.is_list = true;
        lf.is_merge = true;

        return lf;
    }

    public void setName(String n) {
        name = n;

        char last_ch = name.charAt(0);

        for (int i = 0; i < name.length(); i++) {
            if (i == 0) {   //首字母小写
                java_name = String.valueOf(last_ch).toLowerCase();
                proto_name = String.valueOf(last_ch).toUpperCase();

                continue;
            }

            if (name.charAt(i) == '_') {
                last_ch = '_';
                continue;
            }

            if (last_ch == '_') {
                last_ch = name.charAt(i);
                java_name += String.valueOf(last_ch).toUpperCase();
                proto_name += String.valueOf(last_ch).toUpperCase();
                continue;
            }

            last_ch = name.charAt(i);
            java_name += last_ch;
            proto_name += last_ch;
        }
    }

    public void setType(String origin_type) {
        type = origin_type.toLowerCase();       //全部小写化

        if (type.startsWith("list")) {
            String sub_type = type.substring(type.indexOf('<') + 1, type.indexOf('>'));

            String java_sub_type = convertToJavaObjectType(sub_type);

            java_type = "List<" + java_sub_type + ">";
            is_list = true;

            proto_type = "repeated " + convertToProtoType(sub_type) + " " + java_name;
        } else if (type.startsWith("set")) {
            String sub_type = type.substring(type.indexOf('<') + 1, type.indexOf('>'));

            String java_sub_type = convertToJavaObjectType(sub_type);

            java_type = "Set<" + java_sub_type + ">";
            is_set = true;

            proto_type = "repeated " + convertToProtoType(sub_type) + " " + java_name;
        } else if (type.startsWith("map")) {
            String sub_type_key = type.substring(type.indexOf('<') + 1, type.indexOf(','));
            String sub_type_value = type.substring(type.indexOf(',') + 1, type.indexOf('>'));

            String java_sub_type_key = convertToJavaObjectType(sub_type_key);
            String java_sub_type_value = convertToJavaObjectType(sub_type_value);

            java_type = "Map<" + java_sub_type_key + "," + java_sub_type_value + ">";
            java_map_type = "<" + java_sub_type_key + "," + java_sub_type_value + ">";
            is_map = true;

            proto_type = "repeated "+ module_name + proto_name + "%SIDE%Proto " + java_name;

            proto_key_type = convertToProtoType(sub_type_key);
            proto_value_type = convertToProtoType(sub_type_value);

        } else if (type.equals("date") || type.equals("date_time")) {
            java_type = "Date";
            is_date = true;

            proto_type = "optional int64 " + java_name;
        } else if (type.equalsIgnoreCase("bool") || type.equalsIgnoreCase("boolean")) {
            java_type = "boolean";
            proto_type = "optional bool " + java_name;

            is_bool = true;
        } else {
            if (type.equalsIgnoreCase("string")) {
                java_type = "String";
            } else {
                java_type = convertToJavaType(origin_type);
            }

            String sub_proto_type = convertToProtoType(origin_type);

            proto_type = "optional " + sub_proto_type + " " + java_name;

            if (sub_proto_type.equals("string")) {
                is_string = true;
            }
        }
    }
}
