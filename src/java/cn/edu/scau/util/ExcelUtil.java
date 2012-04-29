/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.edu.scau.util;


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;



/**
 *
 * @author jiang
 */
public class ExcelUtil {
    
    
    
    /**
     * 需要poi-3.7-20101029.jar
     *
     * @param head 表头
     *
     * @param content 内容
     * 
     * @return InputStream
     */
    public static  InputStream createExcelStream (List<String> head,List<List<String>> content ) throws IOException {
        
        org.apache.poi.ss.usermodel.Workbook wb = new org.apache.poi.hssf.usermodel.HSSFWorkbook();
        org.apache.poi.ss.usermodel.Sheet sheet = wb.createSheet();
        org.apache.poi.ss.usermodel.Row row = sheet.createRow(0);
        int i=0;
        for (String s:head) {
            org.apache.poi.ss.usermodel.Cell cell = row.createCell(i);
            cell.setCellValue(s);
            i++;
        }
        i=0;
        for (List<String> list:content) {
            row = sheet.createRow(++i);
            int j=0;
            for (String s:list) {                
                org.apache.poi.ss.usermodel.Cell cell = row.createCell(j);
                cell.setCellValue(s);
                j++;
            }
            
        }
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        wb.write(baos);
        byte[] buf = baos.toByteArray();
        ByteArrayInputStream bais = new ByteArrayInputStream(buf);
        return bais;
        
    }
}
