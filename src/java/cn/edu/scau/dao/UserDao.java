/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.edu.scau.dao;

import cn.edu.scau.bean.User;
import java.util.List;
import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.ioc.loader.annotation.IocBean;

/**
 *
 * @author Administrator
 */
@IocBean(args = {"refer:dao"})
public class UserDao extends BasicDao<User>{

    public UserDao(Dao dao) {
        super(dao);
    }
    
    public List<User> list(int role){
        List<User> list;
        list=this.getList(Cnd.where("role", "=", role), null);
        for(User u:list){
            dao().fetchLinks(u, null);
        }
        return list;
    }
    
    public User get(String name){
        return this.fetch(Cnd.where("name", "=", name));
    }
    
}
