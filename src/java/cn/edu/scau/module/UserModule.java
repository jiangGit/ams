/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.edu.scau.module;

import cn.edu.scau.Config;
import cn.edu.scau.RoleValidator;
import cn.edu.scau.Roles;
import cn.edu.scau.bean.User;
import cn.edu.scau.dao.OrganizationDao;
import cn.edu.scau.dao.UserDao;
import cn.edu.scau.util.ExcelUtil;
import cn.edu.scau.util.FileDownload;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.nutz.ioc.aop.Aop;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.By;
import org.nutz.mvc.annotation.Filters;
import org.nutz.mvc.annotation.GET;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.POST;
import org.nutz.mvc.annotation.Param;

/**
 *
 * @author Administrator
 */
@At("/user")
@IocBean
@Filters(
@By(type = RoleValidator.class, args = {"/error.html"}))
public class UserModule {
    
    @Inject
    private UserDao userDao;
    @Inject
    private OrganizationDao organizationDao;
    
    @At
    @Ok("jsp:pages.user.add")
    @Roles({User.ADMIN})
    public void toAdd(HttpServletRequest request){
        request.setAttribute("organizationList", this.organizationDao.getAll());
    }
    
    @POST
    @At
    @Aop("transactionInterceptor")
    @Ok("redirect:/user/list")
    @Roles({User.ADMIN})
    public void add( @Param("..") User user){
        user.setPassword(Config.initPassword);
        this.userDao.add(user);
    }
    
    @POST
    @At
    @Aop("transactionInterceptor")
    @Ok("redirect:/user/list")
    @Roles({User.ADMIN})
    public void modify( @Param("..") User user){
        user.setPassword(this.userDao.fetch(user.getId()).getPassword());
        this.userDao.update(user);
    }
    

    @At("/passowrd/reset/?")
    @Ok("redirect:/user/list")
    @Roles({User.ADMIN})
    public void resetPassword( int id){
        User u=this.userDao.fetch(id);
        u.setPassword(Config.initPassword);
        this.userDao.update(u);
    }
    
    @POST
    @Ok("redirect:/user/list")
    @At("/passowrd/modify")
    public void modifyPassword( HttpSession session, @Param("password") String password){
        User u=(User) session.getAttribute("user");
        u.setPassword(password);
        this.userDao.update(u);
    }
    

    @At("/delete/?")
    @Aop("transactionInterceptor")
    @Ok("redirect:/user/list")
    @Roles({User.ADMIN})
    public void delete(int id){
        this.userDao.delete(id);
    }
    
    @GET
    @At("/get/?")
    @Ok("jsp:pages.user.modify")
    @Roles({User.ADMIN})
    public void get(int id,HttpServletRequest request){
        User u=this.userDao.get(id);
        request.setAttribute("bean", u);
        request.setAttribute("organizationList", this.organizationDao.getAll());
    }
    
    @GET
    @At("/list")
    @Ok("jsp:pages.user.list")
    @Roles({User.ADMIN})
    public void list(HttpServletRequest request){
        List l=this.userDao.getAll();
        request.setAttribute("list", l);
    }
    
    @GET
    @At("/export")
    @Roles({User.ADMIN})
    public void export(HttpServletRequest request,HttpServletResponse response) throws IOException{
        List<User> l=this.userDao.getAll();
        List<String> head=new ArrayList<String>();
        head.add("用户名");
        head.add("类型");
        head.add("所属组织");
        List<List<String>> content=new ArrayList<List<String>>();
        for(User u:l){
            List<String> c=new ArrayList<String>();
            c.add(u.getName());
            if(u.getRole()==User.PRINCIPAL){
                c.add("负责人");
                c.add(u.getOrganization().getName());
            }else if(u.getRole()==User.FUNCTIONARY){
                c.add("工作人员");
                c.add("社联");
            }else{
                c.add("管理员");
                c.add("");
            }
            content.add(c);
        }
        FileDownload.download(request, response,ExcelUtil.createExcelStream(head, content), "用户列表.xls");
    }
    
}
