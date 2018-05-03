package com.xml;

import org.apache.commons.lang3.StringUtils;
import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by liweiping on 2018/4/12.
 */
@XmlRootElement(name="module")
@XmlAccessorType(XmlAccessType.FIELD)
public class Module {
     private int moduleId;
     @XmlAttribute(name="name")
     private String moduleName;
     @XmlAttribute
     private String desc;
     @XmlElement
     private String remark;
     @XmlElement(name = "protocol")
     private List<Protocol> protocols;

     private String cModuleName;

     private String uModuleName;

     private String moduleClassName;

     private String messageClassName;

    public int getModuleId() {
        return moduleId;
    }

    public void setModuleId(int moduleId) {
        this.moduleId = moduleId;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public List<Protocol> getProtocols() {
        return protocols;
    }

    public void setProtocols(List<Protocol> protocols) {
        this.protocols = protocols;
    }

    /**首字母大写*/
    public String  getCModuleName(){
        return cModuleName;
    }

    public String getUModuleName(){
        return uModuleName;
    }

    public String getModuleClassName(){
        return moduleClassName;
    }

    public void init(){
        cModuleName=StringUtils.capitalize(moduleName);
        uModuleName=moduleName.toUpperCase();
        moduleClassName= StringUtils.capitalize(moduleName)+"Module";
        messageClassName=cModuleName+"Messages";
        if(protocols!=null){
            for(Protocol protocol:protocols){
                protocol.init();
            }
        }
    }

    public String getMessageClassName() {
        return messageClassName;
    }

    public List<Map<String,Object>> getMessageInfos() {
        List<Map<String,Object>> objectList=new ArrayList<>();

        if(protocols!=null){
            for(Protocol protocol:protocols){
                objectList.add(protocol.getMessageInfos(moduleName));
            }
        }
        return objectList;
    }

    public Set<String> getHandlerImports() {
        Set<String> imports=new HashSet<>();
        if(protocols!=null){
            for(Protocol protocol:protocols){
                if(protocol.getCode()%2==1){
                    imports.addAll(protocol.getImports());
                }
            }
        }
        return imports;
    }

    public Set<String> getMessagesImports() {
        Set<String> imports=new HashSet<>();
        if(protocols!=null){
            for(Protocol protocol:protocols){
                if(protocol.getCode()%2==0){
                    imports.addAll(protocol.getImports());
                }
            }
        }
        return imports;
    }
}
