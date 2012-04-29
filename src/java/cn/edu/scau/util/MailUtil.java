/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.edu.scau.util;


import cn.edu.scau.Config;
import java.io.UnsupportedEncodingException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;

/**
 *
 * @author jiang
 */
public class MailUtil {

    // 邮箱服务器
    private static String host=Config.host;
    // 这个是你的邮箱用户名
    private static String username =Config.mailUser;
    // 你的邮箱密码
    private static String password=Config.mailPassword ;
    
    private static String mail_from =Config.mailAddress;

    private String mail_head_name = "";

    private String mail_head_value = "";

    private List<String> mail_to_list=new ArrayList<String>();



    private String mail_subject = "";

    private String mail_content = "";

    private String filename = "";//附件文件名

    private List<String> file_list =new ArrayList<String>();//附件文件集合


    public  MailUtil(){

    }


    /**
     *<br>方法说明：设置邮件服务器地址
     *<br>输入参数：String host 邮件服务器地址名称
     *<br>返回类型：
     */
    public void setHost(String host) {
        this.host = host;
    }

    /**
     *<br>方法说明：设置登录服务器校验密码
     *<br>输入参数：
     *<br>返回类型：
     */
    public void setPassWord(String pwd) {
        this.password = pwd;
    }

    /**
     *<br>方法说明：设置登录服务器校验用户
     *<br>输入参数：
     *<br>返回类型：
     */
    public void setUserName(String usn) {
        this.username = usn;
    }


    /**
     *<br>方法说明：添加邮件发送目的邮箱
     *<br>输入参数：
     *<br>返回类型：
     */
    public void addTo(String mail_to) {

        this.mail_to_list.add(mail_to);

    }

    /**
     *<br>方法说明：设置邮件发送源邮箱
     *<br>输入参数：
     *<br>返回类型：
     */
    public void setFrom(String mail_from) {
        this.mail_from = mail_from;
    }

    /**
     *<br>方法说明：设置邮件主题
     *<br>输入参数：
     *<br>返回类型：
     */
    public void setSubject(String mail_subject) {
        this.mail_subject = mail_subject;
    }

    /**
     *<br>方法说明：设置邮件内容
     *<br>输入参数：
     *<br>返回类型：
     */
    public void setContent(String mail_content) {
        this.mail_content = mail_content;
    }

    /**
     *<br>方法说明：往附件组合中添加附件
     *<br>输入参数：
     *<br>返回类型：
     */
    public void addAttachfile(String filePath) {
        this.file_list.add(filePath);
    }

     /**
     *<br>方法说明：发送邮件
     *<br>输入参数：
     *<br>返回类型：boolean 成功为true，反之为false
     */
    public boolean sendMail() {
        //构造mail session
        Properties props = System.getProperties();
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.auth", "true");
        Session session=null;
        try{
        session = Session.getDefaultInstance(props, new Authenticator() {

            public PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });
        }catch(SecurityException e){
            System.err.println("得到session 失败，SecurityExceptio 需重启服务否则可能无法发送附件");
            
            try{
            session = Session.getInstance(props, new Authenticator() {

            public PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
                }
            });
            }catch(SecurityException ex){
                 System.err.println("得到session 失败，SecurityExceptio 需重启服务");
                  return false;
            }
            
           
        }

        try {
            //构造MimeMessage 并设定基本的值
            MimeMessage msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress(mail_from));

            for(String mail_to_address:mail_to_list){
             Address address = new InternetAddress(mail_to_address);
             msg.addRecipient(Message.RecipientType.TO, address);

            }


            msg.setSubject(mail_subject);

            //构造Multipart
            Multipart mp = new MimeMultipart();

            //向Multipart添加正文
            MimeBodyPart mbpContent = new MimeBodyPart();
            mbpContent.setText(mail_content);
            //向MimeMessage添加（Multipart代表正文）
            mp.addBodyPart(mbpContent);

            for(String filePath:file_list){

                Vector file = new Vector();
                file.addElement(filePath);
                System.err.println(filePath);
            //向Multipart添加附件
            Enumeration efile = file.elements();
            while (efile.hasMoreElements()) {

                MimeBodyPart mbpFile = new MimeBodyPart();
                filename = efile.nextElement().toString();
                FileDataSource fds = new FileDataSource(filename);
                System.err.println(filename);
                    try {
                        mbpFile.setFileName(MimeUtility.encodeText(fds.getName()));
                    } catch (UnsupportedEncodingException ex) {
                        Logger.getLogger(MailUtil.class.getName()).log(Level.SEVERE, null, ex);
                    }
                mbpFile.setDataHandler(new DataHandler(fds));
               // mbpFile.setFileName(fds.getName());
                System.err.println(fds.getName());
                //向MimeMessage添加（Multipart代表附件）
                mp.addBodyPart(mbpFile);
                System.err.println(mbpFile.getFileName());
            }
            file.removeAllElements();
            }

            //向Multipart添加MimeMessage
            msg.setContent(mp);
            msg.setSentDate(new Date());

            //发送邮件
            Transport.send(msg);

        } catch (MessagingException mex) {
            /*
            mex.printStackTrace();
            Exception ex = null;
            if ((ex = mex.getNextException()) != null) {
                ex.printStackTrace();
            }
             * 
             */
            return false;
        }
        return true;
    }


}

