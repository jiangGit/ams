/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.edu.scau.dao;

import cn.edu.scau.bean.Member;
import java.util.List;
import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.ioc.loader.annotation.IocBean;

/**
 *
 * @author Administrator
 */
@IocBean(args = {"refer:dao"})
public class MemberDao  extends BasicDao<Member>{

    public MemberDao(Dao dao) {
        super(dao);
    }
    
    public List<Member> list(int organizationId){
        List<Member> list=this.getList(Cnd.where("organization_id", "=", organizationId), null);
        for(Member m:list){
            dao().fetchLinks(m, null);
        }
        return list;
    }
    
}
