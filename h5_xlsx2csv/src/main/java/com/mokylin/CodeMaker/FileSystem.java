package com.mokylin.CodeMaker;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;

public class FileSystem {

    public static String getFilename(String path) {     //获文件名
        path = path.replace('\\', '/');
        int index = path.lastIndexOf('/');
        String str = path.substring(index + 1);
        return str.substring(0, str.lastIndexOf("."));
    }

    public static String mergeFilename(String _front, String _end) {
        String front, end;
        String fullname;

        if (File.separatorChar == '\\') {
            front = _front.replace('/', File.separatorChar);
            end = _end.replace('/', File.separatorChar);
        } else {
            front = _front.replace('\\', File.separatorChar);
            end = _end.replace('\\', File.separatorChar);
        }

        if (front.endsWith(File.separator)) {
            if (end.startsWith(File.separator)) {
                fullname = front + end.substring(File.separator.length());
            } else {
                fullname = front + end;
            }
        } else {
            if (end.startsWith(File.separator)) {
                fullname = front + end;
            } else {
                fullname = front + File.separator + end;
            }
        }

        return fullname;
    }

    public static Writer createFileWriter(final String filename) throws IOException {
        File file = new File(filename);

        if (file.exists()) {
            file.delete();
        }

        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }

        if (!file.createNewFile()) {
            System.err.println("Can't Create file <" + filename + ">");
            return (null);
        } else {
            System.out.println("Create file <" + filename + "> OK!");
        }

        Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "UTF-8"));

        return out;
    }

    public static void findFiles(String path, String ext_name, ArrayList<String> filename_list) {
        File dir = new File(path);
        if (!dir.exists()) {
            return;
        }

        File[] files = dir.listFiles();

        if (files.length == 0) {
            return;
        }

        for (File f : files) {
            if (f.isDirectory()) {
                findFiles(f.toString(), ext_name, filename_list);
                continue;
            }

            if (f.isFile() && f.getName().endsWith(ext_name)) {
                filename_list.add(f.toString());
            }
        }
    }
}
