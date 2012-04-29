/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.edu.scau.module;

import cn.edu.scau.RoleValidator;
import cn.edu.scau.Roles;
import cn.edu.scau.bean.Notice;
import cn.edu.scau.bean.User;
import cn.edu.scau.dao.NoticeDao;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
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
@At("/notice")
@IocBean
@Filters(
@By(type = RoleValidator.class, args = {"/error.html"}))
public class NoticeModule {

    @Inject
    private NoticeDao noticeDao;

    @At
    @Ok("jsp:pages.notice.add")
    public void toAdd(){   
    }
    
    @POST
    @At
    @Roles({User.ADMIN, User.FUNCTIONARY})
    @Ok("redirect:/notice/list")
    public void add(@Param("..") Notice n) {
        n.setTime(new Timestamp((new Date()).getTime()));
        this.noticeDao.add(n);
    }

    @POST
    @At
    @Roles({User.ADMIN, User.FUNCTIONARY})
    @Ok("redirect:/notice/list")
    public void modify(@Param("..") Notice n) {
        n.setTime(new Timestamp((new Date()).getTime()));
        this.noticeDao.update(n);
    }


    @At("/delete/?")
    @Ok("redirect:/notice/list")
    @Roles({User.ADMIN, User.FUNCTIONARY})
    public void delete(int id) {
        this.noticeDao.delete(id);
    }


    @At("/get/?")
    @Ok("jsp:pages.notice.modify")
    @Roles({User.ADMIN, User.FUNCTIONARY})
    public void get(int id,HttpServletRequest request) {
        request.setAttribute("bean", this.noticeDao.get(id));
    }

    @GET
    @At
    @Ok("jsp:pages.notice.list")
    public void list(HttpServletRequest request) {
        List<Notice> l = this.noticeDao.getAll();
        request.setAttribute("list", l);
    }
}
