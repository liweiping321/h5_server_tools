package com.xml;



import com.StringUtil;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
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
public class Protocol {
    @XmlAttribute()
    private String key;
    @XmlAttribute()
    private String desc;
    @XmlElement(name="BinProperty")
    private List<Property> props;
    @XmlElement()
    private List<Error> errors;
    @XmlElement
    private String remark;

    private int code;

    private String handlerName;

    private String uHandlerName;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;

    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public List<Property> getProps() {
        return props;
    }

    public void setProps(List<Property> props) {
        this.props = props;
    }

    public List<Error> getErrors() {
        return errors;
    }

    public void setErrors(List<Error> errors) {
        this.errors = errors;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public int getCode() {
        return code;
    }


    public String getHandlerName() {
        return handlerName;
    }


    public void init(){
        String [] items= key.split("_");
        code=Integer.parseInt(items[0]);
        handlerName= StringUtils.capitalize(items[1]);
        uHandlerName= StringUtil.upperCharToUnderLine(handlerName).toUpperCase();
        if(props!=null){
            for(Property prop:props){
                prop.init();
            }
        }
    }

    public String getCodeName(String moduleName){
        String codeName=((code%2==1?"C2S_":"S2C_")+moduleName.toUpperCase()+"_"+uHandlerName);
        return codeName;
    }

    public List<String> getReadAttrs(){
        List<String> readAttrs=new ArrayList<String>();
        if(props!=null){
            for(Property prop:props){
                readAttrs.add(prop.getReadAttr());
            }
        }

        return readAttrs;
    }

    public Map<String,Object> getMessageInfos(String moduleName) {
        Map<String,Object> objectMap=new HashMap<>();
        objectMap.put("codeName",getCodeName(moduleName));
        objectMap.put("code",getCode()+"");
        objectMap.put("desc",getDesc());
        objectMap.put("uName",handlerName);
        if(code%2==0){
            if(props!=null&&props.size()>0){
                objectMap.put("sc2Info",getS2cInfos());
            }else{
                objectMap.put("msgName",uHandlerName);
            }

        }

        return objectMap;
    }

    private Map<String,Object> getS2cInfos() {
        Map<String,Object> objectMap=new HashMap<>();
        List<String> attrs=new ArrayList<>();
        String paramAttrs="";
        if(props!=null){
            for(Property prop:props){
                attrs.add(prop.getWriteAttr());
                paramAttrs=paramAttrs+prop.getParamAttr()+",";
            }
        }
        if(paramAttrs.length()>0){
            paramAttrs=paramAttrs.substring(0,paramAttrs.length()-1);
        }
        objectMap.put("attrs",attrs);
        objectMap.put("paramAttrs",paramAttrs);
        return objectMap;
    }

    public Set<String> getImports(){
        Set<String> imports=new HashSet<>();
        if(props!=null){
            for(Property prop:props){
                if(code%2==1){
                    if("date".equals(prop.getType())||"date".equals(prop.getListType())){
                        imports.add("java.util.Date");
                    }
                }else{
                    if("date".equals(prop.getType())||"date".equals(prop.getListType())){
                        imports.add("java.util.Date");
                    }
                    if(prop.getType().startsWith("list")){
                        imports.add("java.util.Collection");
                    }
                }

            }
        }
        return imports;
    }
}
