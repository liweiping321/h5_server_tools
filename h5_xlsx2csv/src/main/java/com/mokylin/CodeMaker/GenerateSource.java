package com.mokylin.CodeMaker;

import org.apache.poi.openxml4j.exceptions.OpenXML4JException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.openxml4j.opc.PackageAccess;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.util.SAXHelper;
import org.apache.poi.xssf.eventusermodel.ReadOnlySharedStringsTable;
import org.apache.poi.xssf.eventusermodel.XSSFReader;
import org.apache.poi.xssf.eventusermodel.XSSFSheetXMLHandler;
import org.apache.poi.xssf.model.StylesTable;
import org.xml.sax.ContentHandler;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;

import javax.xml.parsers.ParserConfigurationException;

public abstract class GenerateSource {

    protected TemplateEngine template_engine = new TemplateEngine();  //创建对象

    protected String java_source_path;
    protected String module_name;
    protected String cfg_filename;
    protected String code_english_name;
    protected String code_proto_filename;  //code_english_name的全小写加下划线分隔版

    protected void writeCodeMakerComment(Writer out) throws IOException {
        template_engine.write(out, "Comment.java");
    }

    public void setString(String jsp, String mn, String cfn, String cen) {
        java_source_path = jsp;
        module_name = mn;
        cfg_filename = cfn;
        code_english_name = cen;
    }

    abstract void gen(String sheet_name, SheetData sheet_data) throws IOException;

    private void processSheet(String sheet_name, StylesTable style, ReadOnlySharedStringsTable shared_strings, SheetToCSV sheet2csv, InputStream is)
            throws IOException, org.xml.sax.SAXException, ParserConfigurationException {
        DataFormatter formatter = new DataFormatter();
        InputSource sheet_source = new InputSource(is);

        XMLReader parse = SAXHelper.newXMLReader();

        ContentHandler handler = new XSSFSheetXMLHandler(style, null, shared_strings, sheet2csv, formatter, false);
        parse.setContentHandler(handler);
        parse.parse(sheet_source);
    }

    public void parseXLSX(File xlsx_file, String csv_filename) throws OpenXML4JException, IOException, SAXException, ParserConfigurationException {
        OPCPackage pack = OPCPackage.open(xlsx_file.getPath(), PackageAccess.READ);
        ReadOnlySharedStringsTable shared_strings = new ReadOnlySharedStringsTable(pack);
        XSSFReader reader = new XSSFReader(pack);      //读取xlxs文件
        StylesTable style = reader.getStylesTable();    //打开样式表，解析它，并返回一个用于处理单元格样式的方便对象。
        XSSFReader.SheetIterator iter = (XSSFReader.SheetIterator) reader.getSheetsData();// 返回一个迭代器，该迭代器将让您依次获得所有不同的表。

        template_engine.add("PACKAGE", code_english_name.toLowerCase());   //添加键和值
        template_engine.add("MODULE_NAME", module_name);
        template_engine.add("CONFIG_FILENAME", cfg_filename);  //ok

        code_proto_filename = Naming.convertToProtoName(code_english_name);

        while (iter.hasNext()) {    //迭代器 找到元素
            InputStream is = iter.next();  //赋值给 输入流对象
            String sheet_name = iter.getSheetName();   // 获取当前页名

            System.out.println("sheet name: " + sheet_name); //打印

            SheetToCSV sheet2csv = new SheetToCSV(code_english_name, csv_filename, module_name);  //创建对象
            processSheet(sheet_name, style, shared_strings, sheet2csv, is);

            {
                SheetData sheet_data = sheet2csv.getSheetData();

                template_engine.add("PRIMARY_FIELD_TYPE", sheet_data.getPrimaryKeyType());

                gen(sheet_name, sheet_data);
            }

            sheet2csv.close();
        }
    }
}
