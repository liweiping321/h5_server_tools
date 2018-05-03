package com.xml;

import com.StringUtil;

import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

/**
 * Created by liweiping on 2018/4/12.
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class Property {
    private static final Map<String, String> readAttrMap = new HashMap<String, String>();

    private static final Map<String,String>  paramAttrMap=new HashMap<>();

    private static final Map<String,String> writeAttrMap=new HashMap<>();

    static {
        initReadAttr();
        initParamAttr();
        intiWriteAttr();
    }

    private static void initReadAttr() {
        readAttrMap.put("byte", "final int $name  = BufferUtil.readByte(buffer);  //");
        readAttrMap.put("boolean", "final boolean $name  =  BufferUtil.readBoolean(buffer);  //");
        readAttrMap.put("short", "final int $name  =  BufferUtil.readShort(buffer);  //");
        readAttrMap.put("int", "final int $name  = BufferUtil.readVarInt32(buffer);  //");
        readAttrMap.put("long", "final long $name  =  BufferUtil.readVarInt64(buffer);  //");
        readAttrMap.put("string", "final String $name  =  BufferUtil.readUTF(buffer);  //");
        readAttrMap.put("date", "final Date $name  =  BufferUtil.readDate(buffer);  //");
        readAttrMap.put("bytes", "final byte[] $name  =  BufferUtil.readBytes(buffer);  //");
//        readAttrMap.put("int32", "final int $name  = BufferUtil.readVarInt32(buffer);  //");
//        readAttrMap.put("int64", "final long $name  =  BufferUtil.readVarInt64(buffer);  //");
//        readAttrMap.put("bool", "final boolean $name  =  BufferUtil.readBoolean(buffer);  //");
        readAttrMap.put("list#boolean", "final boolean[] $name  =  BufferUtil.readBooleans(buffer);  //");
        readAttrMap.put("list#short", "final int[] $name  =  BufferUtil.readShorts(buffer);  //");
        readAttrMap.put("list#int", "final int[] $name  = BufferUtil.readInts(buffer);  //");
        readAttrMap.put("list#long", "final long[] $name  =  BufferUtil.readLongs(buffer);  //");
        readAttrMap.put("list#string", "final String[] $name  =  BufferUtil.readUTFs(buffer);  //");
        readAttrMap.put("list#date", "final Date[] $name  =  BufferUtil.readDates(buffer);  //");
    }

    private static void initParamAttr(){
        paramAttrMap.put("byte", "byte $name");
        paramAttrMap.put("boolean", "boolean $name");
        paramAttrMap.put("short", "int $name");
        paramAttrMap.put("int", "int $name");
        paramAttrMap.put("long", "long $name");
        paramAttrMap.put("string", "String $name");
        paramAttrMap.put("date", "Date $name");
        paramAttrMap.put("bytes", "byte[] $name");
//        paramAttrMap.put("int32", "int $name");
//        paramAttrMap.put("int64", "long $name");
//        paramAttrMap.put("bool", "boolean $name");
        paramAttrMap.put("list#boolean", "Collection<Boolean> $name");
        paramAttrMap.put("list#short", "Collection<Integer> $name");
        paramAttrMap.put("list#int", "Collection<Integer> $name");
        paramAttrMap.put("list#long", "Collection<Long> $name");
        paramAttrMap.put("list#string", "Collection<String> $name");
        paramAttrMap.put("list#date", "Collection<Date> $name");
    }
    private static void intiWriteAttr(){
        writeAttrMap.put("byte", "BufferUtil.writeByte(buffer,$name); //");
        writeAttrMap.put("boolean", "BufferUtil.writeBoolean(buffer,$name); //");
        writeAttrMap.put("short", "BufferUtil.writeShort(buffer,$name); //");
        writeAttrMap.put("int", "BufferUtil.writeVarInt32(buffer,$name); //");
        writeAttrMap.put("long", "BufferUtil.writeVarInt64(buffer,$name); //");
        writeAttrMap.put("string", "BufferUtil.writeUTF(buffer,$name); //");
        writeAttrMap.put("date", "BufferUtil.writeDate(buffer,$name); //");
        writeAttrMap.put("bytes", "BufferUtil.writeBytes(buffer,$name); //");
//        writeAttrMap.put("int32", "BufferUtil.writeVarInt32(buffer,$name); //");
//        writeAttrMap.put("int64", "BufferUtil.writeVarInt64(buffer,$name); //");
//        writeAttrMap.put("bool", "BufferUtil.writeBoolean(buffer,$name); //");
        writeAttrMap.put("list#boolean", "BufferUtil.writeBooleans(buffer,$name); //");
        writeAttrMap.put("list#short", "BufferUtil.writeShorts(buffer,$name); //");
        writeAttrMap.put("list#int", "BufferUtil.writeInts(buffer,$name); //");
        writeAttrMap.put("list#long", "BufferUtil.writeLongs(buffer,$name); //");
        writeAttrMap.put("list#string", "BufferUtil.writeUTFs(buffer,$name); //");
        writeAttrMap.put("list#date", "BufferUtil.writeDates(buffer,$name); //");
    }

    @XmlAttribute
    private String type;
    @XmlAttribute
    private String name;
    @XmlAttribute
    private String desc;
    @XmlAttribute(required = false)
    private String proto;
    @XmlAttribute(required = false)
    private String listType;

    @XmlElement(name = "BinProperty")
    private List<Property> props;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getProto() {
        return proto;
    }

    public void setProto(String proto) {
        this.proto = proto;
    }

    public String getListType() {
        return listType;
    }

    public void setListType(String listType) {
        this.listType = listType;
    }

    public List<Property> getProps() {
        return props;
    }

    public void setProps(List<Property> props) {
        this.props = props;
    }

    public void init(){
        if(!StringUtils.isEmpty(listType)){
            type=type+"#"+listType;
        }
        type=type.toLowerCase();
    }

    public String getReadAttr() {
        String readAttr = readAttrMap.get(type);
        if (readAttr == null) {
            readAttr = "找不到类型： type=" + type + ", name=" + name;
            if(!type.equals("bytes#int")){
                throw new RuntimeException(readAttr);
            }

        } else {
            readAttr = readAttr.replace("$name", name) + desc;
        }

        return readAttr;
    }
    
    public String getWriteAttr(){
        String writeAttr = writeAttrMap.get(type);
        if (writeAttr == null) {
            writeAttr = "找不到类型： type=" + type + ", name=" + name;
            if(!type.equals("bytes#int")){
                throw new RuntimeException(writeAttr);
            }

        } else {
            writeAttr = writeAttr.replace("$name", name) + desc;
        }

        return writeAttr;
    }

    public String getParamAttr(){
        String paramAttr = paramAttrMap.get(type);
        if (paramAttr == null) {
            paramAttr = "找不到类型： type=" + type + ", name=" + name;
            if(!type.equals("bytes#int")){
                throw new RuntimeException(paramAttr);
            }

        } else {
            paramAttr = paramAttr.replace("$name", name);
        }

        return paramAttr;
    }

}
