/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.edu.scau.module;

import cn.edu.scau.Principal;
import cn.edu.scau.PrincipalValidator;
import cn.edu.scau.RoleValidator;
import cn.edu.scau.Roles;
import cn.edu.scau.bean.Member;
import cn.edu.scau.bean.User;
import cn.edu.scau.dao.MemberDao;
import cn.edu.scau.dao.OrganizationDao;
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
@At("/member")
@IocBean
@Filters({
    @By(type = RoleValidator.class, args = {"/error.html"}),
    @By(type = PrincipalValidator.class, args = {"/error.html"})
})
public class MemberModule {

    @Inject
    private MemberDao memberDao;
    @Inject
    private OrganizationDao organizationDao;

    @At
    @Roles({User.PRINCIPAL})
    @Ok("jsp:pages.member.add")
    public void toAdd() {
    }

    @POST
    @At
    @Roles({User.PRINCIPAL})
    @Ok("redirect:/member/list")
    public void add(@Param("..") Member m) {
        this.memberDao.add(m);
    }

    @POST
    @At
    @Roles({User.PRINCIPAL})
    @Ok("redirect:/member/list")
    public void modify(@Param("..") Member m) {
        this.memberDao.update(m);
    }

    @At("/delete/?")
    @Roles({User.PRINCIPAL})
    @Ok("redirect:/member/list")
    public void delete(int id) {
        this.memberDao.delete(id);
    }

    @GET
    @At("/get/?")
    @Ok("jsp:pages.member.modify")
    @Roles({User.PRINCIPAL})
    public void get(int id, HttpServletRequest request) {
        Member m = this.memberDao.get(id);
        request.setAttribute("bean", m);
    }

    @GET
    @At("/list")
    @Ok("jsp:pages.member.list")
    @Roles({User.PRINCIPAL})
    public void list(HttpServletRequest request) {
        User u = (User) request.getSession().getAttribute("user");
        List l = this.memberDao.list(u.getOrganizationId());
        request.setAttribute("list", l);
    }

    @GET
    @At("/list/?")
    @Ok("jsp:pages.member.list")
    public void list(int organizationId, HttpServletRequest request) {
        List l = this.memberDao.list(organizationId);
        request.setAttribute("list", l);
    }

    @GET
    @At("/export/?")
    public void export(int organizationId, HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<Member> l = this.memberDao.list(organizationId);
        List<String> head = new ArrayList<String>();
        head.add("学号");
        head.add("名称");
        head.add("学院");
        head.add("专业");
        head.add("年级");
        head.add("组织");
        List<List<String>> content = new ArrayList<List<String>>();
        for (Member m : l) {
            List<String> c = new ArrayList<String>();
            c.add(m.getStudentId());
            c.add(m.getName());
            c.add(m.getAcademy());
            c.add(m.getMajor());
            c.add(String.valueOf(m.getGrade()));
            c.add(m.getOrganization().getName());
            content.add(c);
        }
        FileDownload.download(request, response, ExcelUtil.createExcelStream(head, content), this.organizationDao.fetch(organizationId).getName() + "会员列表.xls");
    }

    @GET
    @At("/export")
    @Roles({User.PRINCIPAL})
    public void export(HttpServletRequest request, HttpServletResponse response) throws IOException {
        User u = (User) request.getSession().getAttribute("user");
        this.export(u.getOrganizationId(), request, response);
    }
}
