/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.edu.scau.dao;

import cn.edu.scau.bean.Organization;
import org.nutz.dao.Dao;
import org.nutz.ioc.loader.annotation.IocBean;

/**
 *
 * @author Administrator
 */
@IocBean(args = {"refer:dao"})
public class OrganizationDao  extends BasicDao<Organization>{

    public OrganizationDao(Dao dao) {
        super(dao);
    }
    
}
