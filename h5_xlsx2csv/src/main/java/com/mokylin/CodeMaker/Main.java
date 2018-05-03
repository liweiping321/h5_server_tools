package com.mokylin.CodeMaker;

import org.apache.poi.openxml4j.exceptions.OpenXML4JException;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.ParserConfigurationException;

public class Main {

    private interface IGenerateSourceFactory {
        GenerateSource create();

        default GenerateSource create(String java_source_path, String module_name, String cfg_filename, String code_english_name) {
            GenerateSource gs = create();

            gs.setString(java_source_path, module_name, cfg_filename, code_english_name);

            return gs;
        }
    }

    private static class GenerateCfgFactory implements IGenerateSourceFactory {
        public GenerateSource create() {
            return (new GenerateCfgSource());
        }
    }

    private static class GenerateDataFactory implements IGenerateSourceFactory {
        public GenerateSource create() {
            return (new GenerateDataSource());
        }
    }

    public static void generateFile(IGenerateSourceFactory genFactory, String sourceFilePath, String desFilePath, String codeFilePath)
            throws OpenXML4JException, ParserConfigurationException, SAXException, IOException {
        ArrayList<String> xlsx_filename_list = new ArrayList<>();

        FileSystem.findFiles(sourceFilePath, "xlsx", xlsx_filename_list); //找到目录下的所有xlxs文件，并将其目录存储在集合内

        for (String xlsx_filename : xlsx_filename_list) {

            File xlsx_file = new File(xlsx_filename);   //创建文件对象

            if (!xlsx_file.exists()) {
                System.err.println("Not found or not a file: " + xlsx_file.getPath());  //判断
                return;
            }

            final String short_pathname = xlsx_filename.substring(sourceFilePath.length(), xlsx_filename.lastIndexOf('.'));
            final String code_english_name = FileSystem.getFilename(xlsx_filename);
            final String csv_filename = desFilePath + short_pathname + ".txt";
            final String java_source_path = codeFilePath + File.separator + Naming.removeUnderline(code_english_name);
            final String cfg_filename = ("config" + short_pathname + ".txt").replace('\\', '/');
            final String module_name = Naming.toAdultCamelNaming(code_english_name);

            GenerateSource gen = genFactory.create(java_source_path, module_name, cfg_filename, code_english_name);

            gen.parseXLSX(xlsx_file, csv_filename);
        }
    }

    public static void main(String[] args) throws OpenXML4JException, ParserConfigurationException, SAXException, IOException {
        System.out.println("CodeMaker v1.0 (author: HuYingzhuo)\n(C)Copyright 2018 Shenzhen Mokylin Technology Co., Ltd.\n");

        if (args[0].equalsIgnoreCase("cfg")) {
            String sourceFilePath = "config_table";
            String desFilePath = "gen/config_data";
            String codeFilePath = "gen/cfg_code";
            generateFile(new GenerateCfgFactory(), sourceFilePath, desFilePath, codeFilePath);
        } else {
            String sourceFilePath = "server_table";
            String desFilePath = "gen/info_data";
            String codeFilePath = "gen/info_code";
            generateFile(new GenerateDataFactory(), sourceFilePath, desFilePath, codeFilePath);
        }
    }
}