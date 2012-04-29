/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.edu.scau.module;

import cn.edu.scau.RoleValidator;
import cn.edu.scau.Roles;
import cn.edu.scau.bean.Apply;
import cn.edu.scau.bean.Record;
import cn.edu.scau.bean.User;
import cn.edu.scau.dao.*;
import cn.edu.scau.util.MailUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.jbpm.api.Configuration;
import org.jbpm.api.ExecutionService;
import org.jbpm.api.ProcessEngine;
import org.jbpm.api.RepositoryService;
import org.jbpm.api.TaskService;
import org.jbpm.api.task.Task;
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
@At("/audit")
@IocBean
@Filters(
@By(type = RoleValidator.class, args = {"/error.html"}))
public class AuditModule {

    @Inject
    private ApplyDao applyDao;
    @Inject
    private RecordDao recordDao;
    @Inject
    private GoodsDao goodsDao;
    @Inject
    private UserDao userDao;

    @At("/toAudit/?")
    @Ok("jsp:pages.audit.audit")
    @Roles({User.FUNCTIONARY, User.ADMIN})
    public void toAudit(String id, HttpServletRequest request) throws Exception {
        ProcessEngine processEngine = Configuration.getProcessEngine();
        TaskService taskService = processEngine.getTaskService();
        Apply a = this.applyDao.get((Integer) taskService.getVariable(id, "applyId"));
        a.setTaskId(id);
        if (a.getState() != Apply.UNREAD) {
            throw new Exception("已审核！");
        }
        request.setAttribute("bean", a);

    }

    @POST
    @At
    @Ok("json")
    public Map check(@Param("id") int id) {
        Map m = new HashMap();
        if (this.recordDao.check(this.applyDao.fetch(id))) {
            m.put("state", "ok");
        } else {
            m.put("state", "fail");
        }
        return m;
    }

    @POST
    @At
    @Roles({User.FUNCTIONARY, User.ADMIN})
    @Ok("redirect:/audit/unAudits")
    public void audit(@Param("id") String id, @Param("state") int state, @Param("content") String content) throws Exception {
        ProcessEngine processEngine = Configuration.getProcessEngine();
        TaskService taskService = processEngine.getTaskService();
        Apply a = this.applyDao.get((Integer) taskService.getVariable(id, "applyId"));
        if (a.getState() != Apply.UNREAD) {
            throw new Exception("已审核！");
        }
        this.applyDao.dao().fetchLinks(a, null);
        User u = this.userDao.fetch(a.getUserId());
        MailUtil mail = new MailUtil();
        mail.addTo(u.getEmail());
        if (state == Apply.PASS) {

            a.setState(state);
            Record r = new Record();
            r.setBegan(a.getBegan());
            r.setEnd(a.getEnd());
            r.setGoodsId(a.getGoodsId());
            r.setOrganizationId(u.getOrganizationId());
            r.setContent(content);
            r.setApplyId(a.getId());
            this.applyDao.update(a);
            this.recordDao.add(r);
            mail.setSubject("申请物资通过通知");
            mail.setContent("你申请的" + a.getGoods().getName() + "通过审核啦！" + content);
            mail.sendMail();
            Map<String, Object> m = new HashMap();
            m.put("type","结束");
            m.put("applyId", a.getId());
            taskService.completeTask(id, m);

        } else {
            a.setState(state);
            this.applyDao.update(a);
            mail.setSubject("申请物资通知");
            mail.setContent("抱歉，你申请的" + a.getGoods().getName() + "没有通过审核！" + content);
            mail.sendMail();
            Map<String, Object> m = new HashMap();
            m.put("type","驳回");
            m.put("applyId", a.getId());
            taskService.completeTask(id, m);
        }



    }

    @GET
    @At("/get/?")
    @Ok("jsp:pages.audit.get")
    @Roles({User.FUNCTIONARY, User.ADMIN})
    public void get(int id, HttpServletRequest request) {
        request.setAttribute("bean", this.recordDao.getByApply(id));

    }

    @GET
    @At("/list/?")
    @Ok("jsp:pages.audit.list")
    @Roles({User.FUNCTIONARY, User.ADMIN})
    public void list(int state, HttpServletRequest request) {
        List l;
        if (state == Apply.UNREAD || state == Apply.PASS || state == Apply.FAIL) {
            l = this.applyDao.listByState(state);
        } else {
            l = this.applyDao.getAll();
        }
        request.setAttribute("list", l);
    }

    @GET
    @At
    @Ok("jsp:pages.audit.list")
    @Roles({User.FUNCTIONARY, User.ADMIN})
    public void unAudits(HttpServletRequest request) {
        ProcessEngine processEngine = Configuration.getProcessEngine();
        TaskService taskService = processEngine.getTaskService();
        List<Task> taskList = taskService.findPersonalTasks("FUNCTIONARY");
        List<Apply> l = new ArrayList<Apply>();
        System.err.println(taskList.size());
        for (Task task : taskList) {
            Apply a = this.applyDao.get((Integer) taskService.getVariable(task.getId(), "applyId"));
            this.applyDao.dao().fetchLinks(a.getUser(), null);
            a.setTaskId(task.getId());
            if (!this.recordDao.check(a)) {
                a.setIsConflict(true);
            }
            l.add(a);

        }
        request.setAttribute("list", l);
    }
}
