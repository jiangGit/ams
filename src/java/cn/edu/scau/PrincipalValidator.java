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
public class PrincipalValidator implements ActionFilter {

    private String path;

    public PrincipalValidator(String path) {
        this.path = path;
    }

    public View match(ActionContext ac) {
        Method method = ac.getMethod();
        Principal p = method.getAnnotation(Principal.class);
        if (p == null) {
            return null;
        }
        HttpServletRequest req = ac.getRequest();
        User a = (User) req.getSession().getAttribute("user");
        if (a != null) {
            if(a.getRole()!=User.PRINCIPAL){
                return null;
            }
            Object[] args= ac.getMethodArgs();
            if(args.length>0){
                if(a.getOrganizationId()==(Integer)args[0]){
                    return null;
                }else{
                    return new ServerRedirectView(path);
                }
            }else{
                return null;
            }
        } else {
            return new ServerRedirectView(path);
        }
    }
}
