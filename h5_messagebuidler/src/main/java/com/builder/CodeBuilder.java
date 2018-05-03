package com.builder;

import com.xml.JaxbReadXml;
import com.xml.Module;
import com.xml.Protocol;

import freemarker.template.Configuration;
import freemarker.template.Template;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by liweiping on 2018/4/13.
 */
public class CodeBuilder {

    private static  SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private static Configuration cfg  = new Configuration();

    private static  Template handlerTemplate;
    private static  Template heroModuleTemplate;

    private static Template messagesTemplate;
    static{
        cfg.setClassForTemplateLoading(CodeBuilder.class, "/ftl");
        try{
            handlerTemplate = cfg.getTemplate("Handler.ftl");
            heroModuleTemplate = cfg.getTemplate("HeroModule.ftl");
            messagesTemplate=cfg.getTemplate("Messages.ftl");
        }catch (Exception e){
            e.printStackTrace();;
        }


    }

    public static void main(String args[])throws Exception{
        List<Module> modules=parseModules("protocol");
        checkMsgCode(modules);
        for(Module module:modules){
            try{
                builder(module);
            }catch (Exception e){
                e.printStackTrace();
                System.out.println("生成模块代码:"+module.getModuleName()+" 出错了！");
            }

        }
        System.out.println("生成代码成功！");

    }
    //检查消息是否重复
    private static void checkMsgCode(List<Module> modules) {
        Map<Integer,Protocol> protocolMap=new HashMap<>();
        Set<String> codeNames=new HashSet<>();
        for(Module module:modules){


            for(Protocol protocol:module.getProtocols()){
               if(protocolMap.containsKey(protocol.getCode())){
                   System.out.println("模块："+module.getModuleName()+",消息号："+protocol.getKey()+"已经被使用了！");
               }else{
                   protocolMap.put(protocol.getCode(),protocol);
               }
               String codeName= protocol.getCodeName(module.getModuleName());
               if(codeNames.contains(codeName)){
                   System.out.println("模块："+module.getModuleName()+",消息常量名："+codeName+"已经被使用了！");
               }else{
                   codeNames.add(codeName);
               }
            }
        }
    }


    private static void builder(Module module)throws Exception {
        builderModule(module);

        builderMessages(module);

        builderHandlers(module);
    }

    private static void builderHandlers(Module module)throws Exception {

        for(Protocol protocol:module.getProtocols()){
            if(protocol.getCode()%2==1){
                builderHandler(module, protocol,handlerTemplate);
            }

        }
    }

    private static void builderHandler(Module module, Protocol protocol,Template template) {
        Map<String,Object> rootMap=new HashMap<String, Object>();
        String handlerClass=module.getCModuleName()+ StringUtils.capitalize(protocol.getHandlerName())+"Handler";
        rootMap.put("className", handlerClass);
        rootMap.put("moduleName",module.getModuleName());
        rootMap.put("dateStr",sdf.format(new Date()));
        rootMap.put("codeName",protocol.getCodeName(module.getModuleName()));
        rootMap.put("cModuleName",module.getCModuleName());
        rootMap.put("desc",protocol.getDesc());
        rootMap.put("imports",module.getHandlerImports());
        rootMap.put("attrs",protocol.getReadAttrs());

         String filePath="protocol"+File.separator+"java"+File.separator+module.getModuleName()+File.separator+"handler"+File.separator+handlerClass+".java";
        ceateCodeFile(template,rootMap,filePath);
    }

    private static void builderMessages(Module module) {
        Map<String, Object> rootMap = new HashMap<>();
        rootMap.put("className", module.getMessageClassName());
        rootMap.put("moduleName", module.getModuleName());
        rootMap.put("uModuleName", module.getUModuleName());
        rootMap.put("dateStr", sdf.format(new Date()));
        rootMap.put("protocols",module.getMessageInfos());
        rootMap.put("desc",module.getDesc());
        rootMap.put("imports",module.getMessagesImports());
        String filePath="protocol"+File.separator+"java"+File.separator+module.getModuleName()+File.separator+module.getMessageClassName()+".java";
        ceateCodeFile(messagesTemplate,rootMap,filePath);
    }

    private static void builderModule(Module module)throws Exception {
        Map<String,Object> rootMap=new HashMap<String, Object>();
        rootMap.put("className",module.getModuleClassName());
        rootMap.put("moduleName",module.getModuleName());
        rootMap.put("dateStr",sdf.format(new Date()));
        String filePath="protocol"+File.separator+"java"+File.separator+module.getModuleName()+File.separator+module.getModuleClassName()+".java";
        ceateCodeFile(heroModuleTemplate,rootMap,filePath);
    }

    private static void ceateCodeFile(Template template, Map<String, Object> rootMap, String filePath)  {
        try {
            File file = new File(filePath);
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            template.process(rootMap, new FileWriter(file));
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("生成文件： "+filePath+" 出错了!");
        }
    }

    public static List<Module> parseModules(String path)throws Exception{
        String[] exts={"xml"};
        Collection<File> files=   FileUtils.listFiles(new File(path),exts,true);

        List<Module> modules=new ArrayList<Module>();
        for(File file:files){
            paraseModule(modules, file);
        }

        return modules;
    }

    private static void paraseModule(List<Module> modules, File file) {
        try{
            FileInputStream fis=new FileInputStream(file);
            Module module= JaxbReadXml.readConfigFromStream(Module.class, fis);
            module.setModuleId(Integer.parseInt(file.getName().split("_")[0]));
            module.init();
            modules.add(module);
        }catch (Exception e){
            e.printStackTrace();
            System.err.println("解析文件："+file.getName()+" 出错了!");
        }

    }
}
