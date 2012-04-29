/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.edu.scau.util;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author jiang
 */
public class FileDownload {
    /**
     * 
     * @param request
     * @param response
     * @param out
     * @param filePath
     * @param fileName
     * @return 
     */
    public static boolean download(HttpServletRequest request,HttpServletResponse response,OutputStream out,String filePath,String fileName){
        
        try {
            BufferedInputStream bis;
            String contentType = request.getSession().getServletContext().getMimeType(fileName);
            if (contentType == null) {
                contentType = "application/octet-stream";
            }
            response.setContentType(contentType);
            response.setHeader("Content-disposition", "attachment;filename=" + fileName);
            
            bis = new BufferedInputStream(new FileInputStream(filePath+"/"+fileName));
            
            byte[] buff = new byte[2048];
            int bytesRead;
            while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
                out.write(buff, 0, bytesRead);
                
            }
            
            bis.close();
            
        } catch (IOException ex) {
            return false;
        } 
        return true;
    }
    
    /**
     * 
     * @param request
     * @param response
     * @param in
     * @param fileName
     * @return 
     */
     public static boolean download(HttpServletRequest request,HttpServletResponse response,InputStream in,String fileName){
        OutputStream os = null;
        try {
            String contentType = request.getSession().getServletContext().getMimeType(fileName);
            if (contentType == null) {
                contentType = "application/octet-stream";
            }
            response.setContentType(contentType);
            response.addHeader("Content-Disposition", "attachment; filename=" + new String(fileName.getBytes(), "iso8859-1"));
            os = response.getOutputStream();
            byte[] buffer = new byte[1024];
            int i = -1;
            while ((i = in.read(buffer)) != -1) {
                os.write(buffer, 0, i);
            }
            os.flush();
            os.close();
            in.close();
           
        } catch (IOException ex) {
            return false;
        } 
        return true;
    }
}

