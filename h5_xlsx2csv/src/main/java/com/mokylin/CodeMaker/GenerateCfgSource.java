package com.mokylin.CodeMaker;

import java.io.IOException;
import java.io.Writer;

public class GenerateCfgSource extends GenerateSource {

    private void writeTypeImport(Writer out, SheetData sheet) throws IOException {

        out.write('\n');

        if (sheet.hasList()) {
            out.write("import java.util.List;\n");
        }
        if (sheet.hasSet()) {
            out.write("import java.util.Set;\n");
        }
        if (sheet.hasMap()) {
            out.write("import java.util.Map;\n");
        }
        if (sheet.hasDate()) {
            out.write("import java.util.Date;\n");
        }
    }

    public void createJavaCfgSource(String sheet_name, SheetData sheet) throws IOException {

        Writer out = FileSystem.createFileWriter(FileSystem.mergeFilename(java_source_path, module_name + "Cfg.java"));

        if (out == null) {
            return;
        }

        template_engine.write(out, "CfgBegin.java");

        writeTypeImport(out, sheet);

        if (sheet.hasMap()) {
            sheet.writeMapFieldCfgJavaImport(out);
            out.write("\n");
        }

        writeCodeMakerComment(out);

        template_engine.write(out, "CfgClassBegin.java");

        sheet.writeCfgFieldList(out);

        out.write("}\n");

        out.close();
    }

    public void createJavaCfgPrvdSource(String sheet_name, SheetData sheet) throws IOException {
        Writer out = FileSystem.createFileWriter(FileSystem.mergeFilename(java_source_path, module_name + "CfgPrvd.java"));

        if (out == null) {
            return;
        }

        template_engine.write(out, "CfgPrvdBegin.java");

        writeCodeMakerComment(out);

        template_engine.write(out, "CfgPrvdClassBegin.java");

        out.close();
    }

    private void createProtoSource(String sheet_name, SheetData sheet) throws IOException {

        Writer out = FileSystem.createFileWriter(FileSystem.mergeFilename(java_source_path, code_english_name + "_cfg.proto"));

        if (out == null) {
            return;
        }

        template_engine.write(out, "Cfg.proto");

        out.write("{\n");
        sheet.writeFieldListToProto(out, 'c', "Cfg");

        out.write("}\n\n");

        sheet.writeMapFieldToProto(out, 'c', "Cfg");

        out.close();
    }

    public void gen(String sheet_name, SheetData sheet_data) throws IOException {
        createJavaCfgSource(sheet_name, sheet_data);
        createJavaCfgPrvdSource(sheet_name, sheet_data);
        createProtoSource(sheet_name, sheet_data);
    }
}
