package com.mokylin.CodeMaker;

import org.apache.poi.xssf.eventusermodel.XSSFSheetXMLHandler;
import org.apache.poi.xssf.usermodel.XSSFComment;

import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;

public class SheetToCSV implements XSSFSheetXMLHandler.SheetContentsHandler {

    private final String module_name;
    private final String csv_full_filename;
    private SheetData sheet_data = null;
    private Writer csv_out = null;

    private int cur_row;
    private int last_col;

    public SheetToCSV(String code_english_name, String csv_filename, String mn) {
        module_name=mn;
        csv_full_filename = csv_filename;
        sheet_data = new SheetData(module_name);
    }

    public SheetData getSheetData() {
        return sheet_data;
    }

    public void close() throws IOException {
        if (csv_out != null) {
            csv_out.close();
        }
    }

    @Override
    public void startRow(int row_num) {
        //System.out.println("start row: "+String.valueOf(row_num));
        cur_row = row_num;
        last_col = 0;
    }

    @Override
    public void endRow(int row_num) {
        //System.out.println("end row: "+String.valueOf(row_num));

        if (row_num == 3) {
            sheet_data.mergeField();
            sheet_data.printFieldLog();

            try {
                csv_out = FileSystem.createFileWriter(csv_full_filename);
                if (csv_out == null) {
                    return;
                }
                sheet_data.writeCsvHeader(csv_out);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (row_num > 3 && csv_out != null) {
            try {
                csv_out.write("\r\n");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private int getCol(String str) {
        int bit = 0;
        int[] col_value = new int[16];
        char ch;

        for (int i = 0; i < 16; i++) {
            if (i < str.length()) {
                ch = str.charAt(i);

                if (ch >= 'A' && ch <= 'Z') {
                    col_value[i] = ch - 'A';
                    ++bit;
                } else {
                    break;
                }
            } else {
                col_value[i] = 0;
            }
        }

        if (bit == 4) {
            return (col_value[0] + 1) * 26 * 26 * 26 + col_value[1] * 26 * 26 + col_value[2] * 26 + col_value[3];
        } else if (bit == 3) {
            return (col_value[0] + 1) * 26 * 26 + col_value[1] * 26 + col_value[2];
        } else if (bit == 2) {
            return (col_value[0] + 1) * 26 + col_value[1];
        } else if (bit == 1) {
            return col_value[0];
        }

        return 0;
    }

    @Override
    public void cell(String cell_ref, String format, XSSFComment comment) {
        //System.out.println("cell: "+cell_ref);
        //System.out.println("format:"+format);

        ArrayList<SheetField> origin_field_list = sheet_data.getOriginFieldList();

        if (cur_row == 0) {
            SheetField f = new SheetField(module_name);

            f.comment = format;

            origin_field_list.add(f);
        } else {
            int cur_col = getCol(cell_ref);

            if (cur_col < origin_field_list.size()) {
                SheetField f = origin_field_list.get(cur_col);

                if (cur_row == 1) {
                    f.setName(format);
                } else if (cur_row == 2) {
                    if (format.equalsIgnoreCase("c")) {
                        f.c = true;
                        f.s = false;
                        f.cs = 'c';
                    } else if (format.equalsIgnoreCase("s")) {
                        f.c = false;
                        f.s = true;
                        f.cs = 's';
                    } else if (format.equalsIgnoreCase("a")) {
                        f.c = true;
                        f.s = true;
                        f.cs = 'a';
                    }
                } else if (cur_row == 3) {
                    f.setType(format);
                } else if (csv_out != null) {
                    try {
                        if (cur_col > 0) {
                            csv_out.write('\t');
                        }

                        if (cur_col > last_col + 1) {
                            for (int i = last_col + 1; i < cur_col; i++) {
                                csv_out.write('\t');
                            }
                        }

                        if (f.is_bool) {
                            if (format.equalsIgnoreCase("true") || format.equalsIgnoreCase("t") || format.equalsIgnoreCase("yes") ||
                                    format.equalsIgnoreCase("y") || format.equalsIgnoreCase("male") || format.equalsIgnoreCase("m") ||
                                    format.equalsIgnoreCase("真") || format.equalsIgnoreCase("男") || format.equals("1")) {
                                csv_out.write("1");
                            } else {
                                csv_out.write("0");
                            }
                        } else {
                            csv_out.write(format);
                        }

                        last_col = cur_col;
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public void headerFooter(String s, boolean b, String s1) {
    }
}