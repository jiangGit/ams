/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.edu.scau.dao;

import cn.edu.scau.bean.Notice;
import java.util.List;
import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.ioc.loader.annotation.IocBean;

/**
 *
 * @author Administrator
 */
@IocBean(args = {"refer:dao"})
public class NoticeDao  extends BasicDao<Notice>{

    public NoticeDao(Dao dao) {
        super(dao);
    }
    
    public List<Notice> getTops(int size){
        return this.getList(Cnd.orderBy().desc("time"), this.dao().createPager(1, size));
    }
}
