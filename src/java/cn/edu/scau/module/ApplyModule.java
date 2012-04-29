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
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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
import org.nutz.mvc.annotation.Fail;
import org.nutz.mvc.annotation.Filters;
import org.nutz.mvc.annotation.GET;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.POST;
import org.nutz.mvc.annotation.Param;

/**
 *
 * @author Administrator
 */
@At("/apply")
@IocBean
@Filters(
@By(type = RoleValidator.class, args = {"/error.html"}))
public class ApplyModule {

    @Inject
    private ApplyDao applyDao;
    @Inject
    private RecordDao recordDao;
    @Inject
    private GoodsDao goodsDao;
    @Inject
    private UserDao userDao;

    @At
    @Ok("jsp:pages.apply.add")
    @Roles({User.PRINCIPAL})
    public void toAdd(HttpServletRequest request, HttpSession session) {
        List l = this.goodsDao.getAll();
        request.setAttribute("goodsList", l);
        ProcessEngine processEngine = Configuration.getProcessEngine();
        RepositoryService repositoryService = processEngine.getRepositoryService();
        repositoryService.createDeployment().addResourceFromClasspath("apply.jpdl.xml").deploy();
        ExecutionService executionService = processEngine.getExecutionService();
        Map map = new HashMap();
        map.put("owner", ((User) session.getAttribute("user")).getName());
        map.put("type", "new");

        String id = executionService.startProcessInstanceByKey("apply", map).getId();

        TaskService taskService = processEngine.getTaskService();
        List<Task> taskList = taskService.findPersonalTasks(((User) session.getAttribute("user")).getName());
        for (Task t : taskList) {
            if (t.getExecutionId().equals(id)) {
                request.setAttribute("task", taskList.get(0).getId());
                break;
            }

        }
    }

    @POST
    @At
    @Roles({User.PRINCIPAL})
    @Ok("redirect:/apply/list")
    public void apply(@Param("taskId") String taskId, @Param("..") Apply apply, HttpSession session) {
        apply.setTime(new Timestamp((new Date()).getTime()));
        apply.setState(Apply.UNREAD);
        this.applyDao.add(apply);
        ProcessEngine processEngine = Configuration.getProcessEngine();
        TaskService taskService = processEngine.getTaskService();
        Map<String, Object> m = new HashMap();
        m.put("applyId", apply.getId());
        m.put("type", "提交");
        Task t = taskService.getTask(taskId);
        taskService.completeTask(taskId, m);
    }

    @At("/toModify/?")
    @Ok("jsp:pages.apply.modify")
    @Roles({User.PRINCIPAL})
    public void toModify(String taskId, HttpServletRequest request){
         ProcessEngine processEngine = Configuration.getProcessEngine();
        TaskService taskService = processEngine.getTaskService();
        Apply a = this.applyDao.get((Integer) taskService.getVariable(taskId, "applyId"));
        a.setTaskId(taskId);
         List l = this.goodsDao.getAll();
        request.setAttribute("goodsList", l);
        request.setAttribute("bean", a);
    }
    
    @POST
    @At
    @Roles({User.PRINCIPAL})
    @Ok("redirect:/apply/list")
    public void modify(@Param("taskId") String taskId, @Param("..") Apply apply) throws Exception {
         ProcessEngine processEngine = Configuration.getProcessEngine();
        TaskService taskService = processEngine.getTaskService();        
        apply.setTime(new Timestamp((new Date()).getTime()));
        apply.setState(Apply.UNREAD);
        this.applyDao.update(apply);
        Map<String, Object> m = new HashMap();
        m.put("applyId", apply.getId());
        m.put("type", "提交");
        Task t = taskService.getTask(taskId);
        taskService.completeTask(taskId, m);
    }

    @At("/cancel/?")
    @Roles({User.PRINCIPAL})
    @Ok("redirect:/apply/list")
    public void cancel(String taskId) throws Exception {
        ProcessEngine processEngine = Configuration.getProcessEngine();
        TaskService taskService = processEngine.getTaskService();
        Task task=taskService.getTask(taskId);
        Apply a = this.applyDao.get((Integer) taskService.getVariable(task.getId(), "applyId"));
        this.applyDao.delete(a.getId());
        Map<String, Object> m = new HashMap();
        m.put("type", "取消");
        Task t = taskService.getTask(taskId);
        taskService.completeTask(taskId, m);
    }

    @At("/get/?")
    @Ok("jsp:pages.apply.get")
    public void get(int id, HttpServletRequest request) {
        List l = this.goodsDao.getAll();
        request.setAttribute("goodsList", l);
        request.setAttribute("bean", this.applyDao.get(id));
    }

    @At
    @Ok("jsp:pages.apply.list")
    @Roles({User.PRINCIPAL})
    public void list(HttpSession session, HttpServletRequest request) {
        User u = (User) session.getAttribute("user");
        List l = this.applyDao.list(u.getId());
        request.setAttribute("list", l);
    }

    @At
    @Ok("jsp:pages.apply.rejects")
    @Roles({User.PRINCIPAL})
    public void rejects(HttpSession session, HttpServletRequest request) {
        User u = (User) session.getAttribute("user");
        ProcessEngine processEngine = Configuration.getProcessEngine();
        TaskService taskService = processEngine.getTaskService();
        List<Task> taskList = taskService.findPersonalTasks(u.getName());
        List<Apply> l = new ArrayList<Apply>();
        System.err.println(taskList.size());
        for (Task task : taskList) {
            if (taskService.getVariable(task.getId(), "type")!=null&&taskService.getVariable(task.getId(), "type").equals("驳回")) {
                Apply a = this.applyDao.get((Integer) taskService.getVariable(task.getId(), "applyId"));
                a.setTaskId(task.getId());
                l.add(a);
            }
        }
        request.setAttribute("list", l);
    }
}
