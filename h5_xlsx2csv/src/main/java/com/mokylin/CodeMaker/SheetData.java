package com.mokylin.CodeMaker;

import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javafx.util.Pair;

import static com.mokylin.CodeMaker.Naming.convertToJavaObjectType;

public class SheetData {

    TemplateEngine template_engine = new TemplateEngine();

    private String module_name;
    //是否拥有以下属性
    private boolean has_list = false;
    private boolean has_set = false;
    private boolean has_map = false;
    private boolean has_date = false;

    private boolean has_client = false;
    private boolean has_server = false;

    private int map_field_count = 0;

    private String primary_key_type;

    private ArrayList<SheetField> origin_field_list = new ArrayList<SheetField>();
    private ArrayList<SheetField> final_field_list = new ArrayList<SheetField>();
    private SheetField primary_field = null;

    public boolean hasList() {
        return has_list;
    }

    public boolean hasSet() {
        return has_set;
    }

    public boolean hasMap() {
        return has_map;
    }

    public boolean hasDate() {
        return has_date;
    }

    public SheetData(String cen) {
        module_name = cen;

        template_engine.add("MODULE_NAME", module_name);
    }

    private static void writeMultiLineComment(Writer out, String multi_comment,boolean tab) throws IOException {

        String[] comment = multi_comment.split("\n");
        String start;

        if(tab)
            start="\t";
        else
            start="";

        out.write(start+"/**\n");

        for (String c : comment) {
            out.write(start+" * " + c + "\n");
        }

        out.write(start+" */\n");
    }

    public boolean hasClient() {
        return has_client;
    }

    public boolean hasServer() {
        return has_server;
    }

    public ArrayList<SheetField> getOriginFieldList() {
        return origin_field_list;
    }

    public String getPrimaryKeyType() {
        return primary_key_type;
    }

    public SheetField getPrimaryField() {
        return primary_field;
    }

    private void addToFinalFieldList(SheetField sf) {
        sf.updateTemplateEngine();

        final_field_list.add(sf);
    }

    /**
     * 混合相同名称的字段，以及放弃没有配置类型的字段
     */
    public void mergeField() {
        SheetField last_field = null;
        SheetField cur_field = null;
        int count = 0;

        final_field_list.clear();

        Map<String,Integer> field_show_count=new HashMap<String,Integer>();             //每个字段出现的次数
        List<Pair<String,SheetField>> field_by_name=new ArrayList<Pair<String,SheetField>>();          //根据名字索引的字段

        for(SheetField sf:origin_field_list)
        {
            if(field_show_count.containsKey(sf.name)) {                                 //如果名称已经出现

                final int old_count=field_show_count.get(sf.name);                      //取出旧的值

                field_show_count.replace(sf.name,old_count+1);                          //替换为新的值
            }
            else {
                field_show_count.put(sf.name, 1);
                field_by_name.add(new Pair(sf.name,sf));
            }
        }

        for(Pair<String,SheetField> entry:field_by_name){
            if(field_show_count.get(entry.getKey())>1){
                addToFinalFieldList(entry.getValue().createListField());
            }
            else {
                addToFinalFieldList(entry.getValue());
            }
        }

        for (SheetField f : final_field_list) {
            System.out.println("FinalField:\"" + f.name + "\" ProtoType:\"" + f.proto_type + "\" ValueType:\"" + f.java_type + "\"");
        }
    }

    public void printFieldLog() {
        System.out.print('\n');
        System.out.println("SheetField count: " + String.valueOf(origin_field_list.size()));
        System.out.print('\n');

        boolean first = true;

        for (SheetField f : origin_field_list) {

            System.out.println("      name: " + f.name);
            System.out.println("      type: " + f.type);

            System.out.println(" java name: " + f.java_name);
            System.out.println(" java type: " + f.java_type);

            System.out.println("proto name: " + f.proto_name);
            System.out.println("proto type: " + f.proto_type);

            if (f.is_map) {
                System.out.println("  map type: " + f.java_map_type);
            }

            if (first) {
                primary_field = f;
                primary_key_type = convertToJavaObjectType(f.type);
                primary_field.template_engine.add("JAVA_OBJECT_TYPE", primary_key_type);
                first = false;
            }

            if (f.c && f.s) {
                System.out.println("      side: client,server");
            } else if (f.c) {
                System.out.println("      side: client");
            } else if (f.s) {
                System.out.println("      side: server");
            }

            System.out.println("   comment: " + f.comment);

            System.out.print('\n');

            if (f.is_date) {
                has_date = true;
            }
            if (f.is_list) {
                has_list = true;
            }
            if (f.is_set) {
                has_set = true;
            }
            if (f.is_map) {
                has_map = true;
                ++map_field_count;
            }

            if (f.c) {
                has_client = true;
            }
            if (f.s) {
                has_server = true;
            }
        }
    }

    public void writeCsvHeader(Writer csv_out) throws IOException {
        for (SheetField f : origin_field_list) {
            csv_out.write('"' + f.comment + '"');
            csv_out.write('\t');
        }

        csv_out.write("\r\n");

        for (SheetField f : origin_field_list) {
            if (f.name != null) {
                csv_out.write(f.name);
            }

            csv_out.write('\t');
        }

        csv_out.write("\r\n");

        for (SheetField f : origin_field_list) {
            if (f.c && f.s) {
                csv_out.write('a');
            } else if (f.c) {
                csv_out.write('c');
            } else if (f.s) {
                csv_out.write('s');
            }

            csv_out.write('\t');
        }

        csv_out.write("\r\n");

        for (SheetField f : origin_field_list) {
            if (f.type != null) {
                csv_out.write(f.type);
            }

            csv_out.write('\t');
        }

        csv_out.write("\r\n");
    }

    public void writeCfgFieldList(Writer out) throws IOException {
        for (SheetField f : final_field_list) {
            if (f.type == null) {
                continue;
            }
            writeMultiLineComment(out, f.comment,true);

            f.template_engine.write(out, "CfgField.java");
        }

        template_engine.write(out, "CfgConstructFunc.java");

        for (SheetField f : final_field_list) {
            if (f.type == null) {
                continue;
            }
            f.template_engine.write(out, "FieldGetFunc.java");
        }

        primary_field.template_engine.write(out, "PrimaryFieldGetFunc.java");

        template_engine.write(out, "encode4ConfigBegin.java");

        if (final_field_list.size() > map_field_count) {
            for (SheetField f : final_field_list) {
                if (f.type == null) {
                    continue;
                }
                if (f.c == false) {
                    continue;
                }
                if (f.is_map) {
                    continue;
                }

                if (f.is_date) {
                    f.template_engine.write(out, "FieldSetDate.java");
                } else if (f.is_list || f.is_set) {
                    f.template_engine.write(out, "FieldSetArray.java");
                } else {
                    f.template_engine.write(out, "FieldSet.java");
                }
            }

            out.write("\n");
        }

        if (has_map) {
            for (SheetField f : final_field_list) {
                if (f.type == null) {
                    continue;
                }
                if (f.c == false) {
                    continue;
                }
                if (f.is_map) {
                    f.template_engine.add("SIDE","Cfg");
                    f.template_engine.write(out, "FieldSetMap.java");
                }
            }
        }

        template_engine.write(out, "encode4ConfigEnd.java");
    }

    public void writeFieldList(Writer out) throws IOException {
        for (SheetField f : final_field_list) {
            if (f.type == null) {
                continue;
            }

            writeMultiLineComment(out, f.comment,true);

            f.template_engine.write(out, "PublicField.java");
        }
    }

    public void writeFieldSetList(Writer out, String content, char cs,String side) throws IOException {
        for (SheetField f : final_field_list) {

            if (f.type == null) {
                continue;
            }
            if (f.cs != 'a' && f.cs != cs) {
                continue;
            }

            f.template_engine.add("SIDE",side);

            if (f.is_date) {
                f.template_engine.write(out, "FieldSafeSetDate.java");
            } else if (f.is_list || f.is_set) {
                f.template_engine.write(out, "FieldSafeSetArray.java");
            } else if (f.is_map) {
                f.template_engine.add("CONTENT_NAME", content);
                f.template_engine.write(out, "FieldSafeSetMap.java");
            } else {
                f.template_engine.write(out, "FieldSet.java");
            }
        }
    }

    public void writeFieldGetList(Writer out, String content, char cs,String side) throws IOException {
        for (SheetField f : final_field_list) {

            if (f.type == null) {
                continue;
            }

            if (f.cs != 'a' && f.cs != cs) {
                continue;
            }

            f.template_engine.add("SIDE",side);

            if (f.is_date) {
                f.template_engine.write(out, "CreateFieldDate.java");
            } else if (f.is_list) {
                f.template_engine.write(out, "CreateFieldArrayList.java");
            } else if (f.is_set) {
                f.template_engine.write(out, "CreateFieldHashSet.java");
            } else if (f.is_map) {
                f.template_engine.add("CONTENT_NAME", content);
                f.template_engine.write(out, "CreateFieldHashMap.java");
            } else {
                f.template_engine.write(out, "CreateField.java");
            }
        }
    }

    public void writeMapFieldCfgJavaImport(Writer out) throws IOException {
        if (!has_map) {
            return;
        }

        for (SheetField f : final_field_list) {
            if (f.type == null) {
                continue;
            }
            if (f.c == false) {
                continue;
            }
            if (f.is_map) {
                f.template_engine.add("MODULE_NAME", module_name);
                f.template_engine.write(out, "CfgTypeImport.java");
            }
        }
    }

    public void writeFieldListToProto(Writer out, char cs, String side) throws IOException {

        int index = 1;

        for (SheetField f : final_field_list) {
            if (f.type == null) {
                continue;
            }
            if (f.cs == 'a' || f.cs == cs || cs == 'a') {
                writeMultiLineComment(out, f.comment,true);
                f.template_engine.add("SIDE", side);
                f.template_engine.writeString(out, "\t" + f.proto_type + "=" + String.valueOf(index) + ";\n\n");
                ++index;
            }
        }
    }

    public void writeMapFieldToProto(Writer out, char cs, String side) throws IOException {

        for (SheetField f : final_field_list) {
            if (f.type == null) {
                continue;
            }
            if (!f.is_map) {
                continue;
            }

            if (f.cs == 'a' || f.cs == cs || cs == 'a') {
                writeMultiLineComment(out, f.comment,false);
                f.template_engine.add("SIDE", side);
                f.template_engine.write(out, "MapField.proto");
            }
        }
    }
}
