/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.edu.scau.module;

import cn.edu.scau.AmgSetup;
import cn.edu.scau.RoleValidator;
import cn.edu.scau.Roles;
import cn.edu.scau.bean.User;
import cn.edu.scau.dao.NoticeDao;
import cn.edu.scau.dao.UserDao;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.By;
import org.nutz.mvc.annotation.Encoding;
import org.nutz.mvc.annotation.Fail;
import org.nutz.mvc.annotation.Filters;
import org.nutz.mvc.annotation.IocBy;
import org.nutz.mvc.annotation.Modules;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.POST;
import org.nutz.mvc.annotation.Param;
import org.nutz.mvc.annotation.SetupBy;
import org.nutz.mvc.ioc.provider.ComboIocProvider;

/**
 *
 * @author Administrator
 */
@Modules(scanPackage = true)
@Encoding(input = "UTF-8", output = "UTF-8")
@SetupBy(AmgSetup.class)
@IocBy(type = ComboIocProvider.class,
args = {"*org.nutz.ioc.loader.json.JsonLoader",
    "ioc/dao.js",
    "ioc/aop.js",
    "*org.nutz.ioc.loader.annotation.AnnotationIocLoader",
    "cn.edu.scau.module",
    "cn.edu.scau.dao"
})
@IocBean
@Fail("redirect:/error.html")
public class MainModule {

    @Inject
    private UserDao userDao;
    @Inject
    private NoticeDao noticeDao;

    @At
    @POST
    @Ok("redirect:/main")
    public void login(HttpSession session,@Param("name") String name, @Param("password") String password) {
        User u =this.userDao.get(name);
        if(u.getPassword().equals(password)){
            session.setAttribute("user", u);
        }else{
            System.err.println(password);
            System.err.println(u.getPassword());
        }
    }

    @At
    @Ok("redirect:/index.jsp")
    public void logout(HttpSession session) {
        session.setAttribute("user", null);
    }
    
    @At
    @Ok("jsp:pages.main")
    public void main(HttpServletRequest request){
        request.setAttribute("list", this.noticeDao.getTops(5));
    }
    
}
