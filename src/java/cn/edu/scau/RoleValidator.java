/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.edu.scau;

import cn.edu.scau.bean.User;
import java.lang.reflect.Method;
import javax.servlet.http.HttpServletRequest;
import org.nutz.mvc.ActionContext;
import org.nutz.mvc.ActionFilter;
import org.nutz.mvc.View;
import org.nutz.mvc.view.ServerRedirectView;

/**
 *
 * @author Administrator
 */
public class RoleValidator implements ActionFilter {

    private String path;

    public RoleValidator(String path) {
        this.path = path;
    }

    public View match(ActionContext actionContext) {
        Method method = actionContext.getMethod();
        Roles roles = method.getAnnotation(Roles.class);

        HttpServletRequest req = actionContext.getRequest();
        User a = (User) req.getSession().getAttribute("user");
        if (a != null) {
            if (roles == null) {
                return null;
            }                    
            int[] tmp = roles.value();

            for (int roleId : tmp) {
                if (a.getRole() == roleId) {
                    return null;
                }
            }
            
            
            
            return new ServerRedirectView(path);
        } else {
            return new ServerRedirectView(path);
        }
    }
}
