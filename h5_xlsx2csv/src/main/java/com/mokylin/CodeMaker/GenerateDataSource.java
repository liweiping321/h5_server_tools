package com.mokylin.CodeMaker;

import java.io.IOException;
import java.io.Writer;

public class GenerateDataSource extends GenerateSource {

    private void writeDecodeTypeImport(Writer out, SheetData sheet) throws IOException {

        out.write('\n');

        if (sheet.hasList()) {
            out.write("import java.util.ArrayList;\n");
        }
        if (sheet.hasSet()) {
            out.write("import java.util.HashSet;\n");
        }
        if (sheet.hasMap()) {
            out.write("import java.util.Map;\n");
            out.write("import java.util.HashMap;\n");
        }
        if (sheet.hasDate()) {
            out.write("import java.util.Date;\n");
        }
    }

    private void createClientProtoSource(String sheet_name, SheetData sheet) throws IOException {
        Writer out = FileSystem.createFileWriter(FileSystem.mergeFilename(java_source_path, code_proto_filename + "_client.proto"));

        if (out == null) {
            return;
        }

        template_engine.add("side", "client");
        template_engine.add("SIDE", "Client");
        template_engine.write(out, "ModuleContent.proto");

        out.write("{\n");

        sheet.writeFieldListToProto(out, 'c', "Client");

        out.write("}\n\n");

        sheet.writeMapFieldToProto(out, 'c', "Client");

        out.close();
    }

    private void createServerProtoSource(String sheet_name, SheetData sheet) throws IOException {
        Writer out = FileSystem.createFileWriter(FileSystem.mergeFilename(java_source_path, code_proto_filename + "_server.proto"));

        if (out == null) {
            return;
        }

        template_engine.add("side", "server");
        template_engine.add("SIDE", "Server");
        template_engine.write(out, "ModuleContent.proto");

        out.write("{\n");

        sheet.writeFieldListToProto(out, 's', "Server");

        out.write("}\n\n");

        sheet.writeMapFieldToProto(out, 's', "Server");

        out.close();
    }

    private void createJavaSource(String sheet_name, SheetData sheet) throws IOException {

        Writer out = FileSystem.createFileWriter(FileSystem.mergeFilename(java_source_path, module_name + "Info.java"));

        if (out == null) {
            return;
        }

        out.write("package app.game.module."+module_name.toLowerCase()+";\n\n");

        writeDecodeTypeImport(out, sheet);

        out.write('\n');

        if (sheet.hasClient()) {
            out.write("import app.protobuf.client." + module_name + "ClientContent;\n");
        }
        if (sheet.hasServer()) {
            out.write("import app.protobuf.server." + module_name + "ServerContent;\n");
        }

        out.write('\n');
        writeCodeMakerComment(out);

        out.write("public class " + module_name + "Info {\n");

        sheet.writeFieldList(out);

        if (sheet.hasClient()) {
            String client_content = module_name + "ClientContent";
            String client_proto = client_content + "." + module_name + "ClientProto";

            out.write("\tpublic " + client_proto + " encode4Client() {\n" + "\t\t" + client_proto + ".Builder builder = " + client_proto +
                    ".newBuilder();\n\n");

            sheet.writeFieldSetList(out, client_content, 'c',"Client");

            out.write('\n');
            out.write("\t\treturn builder.build();\n");
            out.write("\t}\n\n");
        }

        if (sheet.hasServer()) {
            String server_content = module_name + "ServerContent";
            String server_proto = server_content + "." + module_name + "ServerProto";

            out.write("\tpublic " + server_proto + " encode4Server() {\n" + "\t\t" + server_proto + ".Builder builder = " + server_proto +
                    ".newBuilder();\n\n");

            sheet.writeFieldSetList(out, server_content, 's',"Server");

            out.write('\n');
            out.write("\t\treturn builder.build();\n");
            out.write("\t}\n\n");
        }

        {
            String server_content = module_name + "ServerContent";
            String server_proto = server_content + "." + module_name + "ServerProto";

            out.write("\tpublic void decode(" + server_proto + " obj)\n" + "\t{\n");

            sheet.writeFieldGetList(out, server_content, 's',"Server");
            out.write("\t}\n");
        }

        out.write("}\n");
        out.close();
    }

    public void gen(String sheet_name, SheetData sheet_data) throws IOException {
        createJavaSource(sheet_name, sheet_data);
        createClientProtoSource(sheet_name, sheet_data);
        createServerProtoSource(sheet_name, sheet_data);
    }
}
