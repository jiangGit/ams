/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.edu.scau.module;

import cn.edu.scau.RoleValidator;
import cn.edu.scau.Roles;
import cn.edu.scau.bean.Organization;
import cn.edu.scau.bean.Record;
import cn.edu.scau.bean.User;
import cn.edu.scau.dao.OrganizationDao;
import cn.edu.scau.dao.RecordDao;
import cn.edu.scau.util.ExcelUtil;
import cn.edu.scau.util.FileDownload;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
@At("/organization")
@IocBean
@Filters(
@By(type = RoleValidator.class, args = {"/error.html"}))
public class OrganizationModule {

    @Inject
    private OrganizationDao organizationDao;
    @Inject
    private RecordDao recordDao;

    @At
    @Ok("jsp:pages.organization.add")
    @Roles({User.ADMIN, User.FUNCTIONARY})
    public void toAdd(){
        
    }
    
    @POST
    @At
    @Ok("redirect:/organization/list")
    @Roles({User.ADMIN, User.FUNCTIONARY})
    public void add(@Param("..") Organization o) {
        this.organizationDao.add(o);
    }

    @POST
    @At
    @Ok("redirect:/organization/list")
    @Roles({User.ADMIN, User.FUNCTIONARY})
    public void modify(@Param("..") Organization o) {
        this.organizationDao.update(o);
    }

    @At("/delete/?")
    @Ok("redirect:/organization/list")
    @Roles({User.ADMIN, User.FUNCTIONARY})
    public void delete(int id) {
        this.organizationDao.delete(id);
    }

    @GET
    @At("/get/?")
    @Ok("jsp:pages.organization.modify")
    @Roles({User.ADMIN, User.FUNCTIONARY})
    public void get(int id,HttpServletRequest request) {
        Organization o = this.organizationDao.get(id);
        request.setAttribute("bean", o);
    }

    @GET
    @At
    @Ok("jsp:pages.organization.list")
    @Roles({User.ADMIN, User.FUNCTIONARY})
    public void list(HttpServletRequest request) {
        List<Organization> l = this.organizationDao.getAll();
        request.setAttribute("list", l);
    }

    @GET
    @At
    @Roles({User.ADMIN, User.FUNCTIONARY})
    public void export(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<Organization> l = this.organizationDao.getAll();
        List<String> head = new ArrayList<String>();
        head.add("名称");
        head.add("简介");
        List<List<String>> content = new ArrayList<List<String>>();
        for (Organization o : l) {
            List<String> c = new ArrayList<String>();
            c.add(o.getName());
            c.add(o.getContent());
            content.add(c);
        }
        FileDownload.download(request, response, ExcelUtil.createExcelStream(head, content), "社团组织列表.xls");
    }

    @GET
    @At("/records/?")
    @Roles({User.ADMIN, User.FUNCTIONARY})
    public void records(int id) {
        List l = this.recordDao.recordsForOrganization(id);
    }

    @GET
    @At("/records/export/?")
    @Roles({User.ADMIN, User.FUNCTIONARY})
    public void exportRecords(int id, HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<Record> l = this.recordDao.recordsForOrganization(id);
        List<String> head = new ArrayList<String>();
        head.add("物资名");
        head.add("借用者");
        head.add("开始时间");
        head.add("归还时间");
        head.add("其他");
        List<List<String>> content = new ArrayList<List<String>>();
        for (Record r : l) {
            List<String> c = new ArrayList<String>();
            c.add(r.getGoods().getName());
            c.add(r.getOrganization().getName());
            c.add(r.getBegan().toString());
            c.add(r.getEnd().toString());
            c.add(r.getContent());
            content.add(c);
        }
        FileDownload.download(request, response, ExcelUtil.createExcelStream(head, content), "物资使用记录列表.xls");
    }
}
