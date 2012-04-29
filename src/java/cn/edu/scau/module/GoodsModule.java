/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.edu.scau.module;

import cn.edu.scau.RoleValidator;
import cn.edu.scau.Roles;
import cn.edu.scau.bean.Goods;
import cn.edu.scau.bean.Record;
import cn.edu.scau.bean.User;
import cn.edu.scau.dao.GoodsDao;
import cn.edu.scau.dao.RecordDao;
import cn.edu.scau.util.ExcelUtil;
import cn.edu.scau.util.FileDownload;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
@At("/goods")
@IocBean
@Filters(
@By(type = RoleValidator.class, args = {"/error.html"}))
public class GoodsModule {

    @Inject
    private GoodsDao goodsDao;
    @Inject
    private RecordDao recordDao;

    @At
    @Ok("jsp:pages.goods.add")
    @Roles({User.ADMIN,User.FUNCTIONARY})
    public void toAdd() {
    }

    @POST
    @At
    @Ok("redirect:/goods/list")
    @Roles({User.ADMIN,User.FUNCTIONARY})
    public void add(@Param("..") Goods g) {
        this.goodsDao.add(g);
    }

    @POST
    @At
    @Ok("redirect:/goods/list")
    @Roles({User.ADMIN,User.FUNCTIONARY})
    public void modify(@Param("..") Goods g) {
        this.goodsDao.update(g);
    }


    @At("/delete/?")
    @Ok("redirect:/goods/list")
    @Aop("transactionInterceptor")
    @Roles({User.ADMIN,User.FUNCTIONARY})
    public void delete(int id) {
        this.goodsDao.delete(id);
    }

    @GET
    @At("/get/?")
    @Ok("jsp:pages.goods.modify")
    @Roles({User.ADMIN,User.FUNCTIONARY})
    public void get(int id,HttpServletRequest request) {
        Goods g = this.goodsDao.get(id);
        request.setAttribute("bean", g);
    }

    @GET
    @At("/list")
    @Ok("jsp:pages.goods.list") 
    public void list(HttpServletRequest request) {
        List l = this.goodsDao.getAll();
        request.setAttribute("list", l);
    }

    @GET
    @At("/export")
    @Roles({User.ADMIN,User.FUNCTIONARY})
    public void export(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<Goods> l = this.goodsDao.getAll();
        List<String> head = new ArrayList<String>();
        head.add("物资名");
        head.add("简介");
        List<List<String>> content = new ArrayList<List<String>>();
        for (Goods g : l) {
            List<String> c = new ArrayList<String>();
            c.add(g.getName());
            c.add(g.getContent());
            content.add(c);
        }
        FileDownload.download(request, response, ExcelUtil.createExcelStream(head, content), "物资列表.xls");
    }

    @GET
    @At("/records/?")
    @Ok("jsp:pages.goods.records") 
    public void records(int id, HttpServletRequest request) {
        List l = this.recordDao.records(id);
        request.setAttribute("list", l);
    }

    @GET
    @At("/records/export/?")
    public void exportRecords(int id, HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<Record> l = this.recordDao.records(id);
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
