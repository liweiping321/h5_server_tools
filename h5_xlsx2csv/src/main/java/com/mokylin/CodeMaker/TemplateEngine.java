package com.mokylin.CodeMaker;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.Writer;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class TemplateEngine {
    private HashMap<String, String> key_value = new HashMap<String, String>();

    static private HashMap<String, String> file_buffer = new HashMap<String, String>();

    public TemplateEngine() {
        Date date = new Date();

        this.add("DATE", date.toString());
    }

    public void add(final String key, String value) {
        String item = "%" + key + "%";

        key_value.remove(item);

        if (value == null || value.length() <= 0) {
            return;
        }

        key_value.put(item, value);
    }

    public String replaceString(String str) {
        String result = str;

        Set<Map.Entry<String, String>> entry_set = key_value.entrySet();

        for (Map.Entry<String, String> entry : entry_set) {
            result = result.replace(entry.getKey(), entry.getValue());
        }

        return result;
    }

    public String replace(String template_filename) throws IOException {

        String origin_template;

        origin_template = file_buffer.get(template_filename);

        if (origin_template == null) {
            origin_template = IOUtils.toString(this.getClass().getClassLoader().getResourceAsStream(template_filename), "UTF-8");
            file_buffer.put(template_filename, origin_template);
        }

        if (origin_template == null || origin_template.length() <= 0) {
            return null;
        }

        return replaceString(origin_template);
    }

    public void writeString(Writer out, String str) throws IOException {
        out.write(this.replaceString(str));
    }

    public void write(Writer out, String template_filename) throws IOException {
        out.write(this.replace(template_filename));
    }
}
