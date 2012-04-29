/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.edu.scau.dao;

import cn.edu.scau.bean.Apply;
import java.util.List;
import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.ioc.loader.annotation.IocBean;

/**
 *
 * @author Administrator
 */
@IocBean(args = {"refer:dao"})
public class ApplyDao  extends BasicDao<Apply>{

    public ApplyDao(Dao dao) {
        super(dao);
    }
    
    public boolean  check(Apply apply){    
        List list=this.query(Cnd.where("began", "<=", apply.getBegan()).
                and("end", ">=", apply.getEnd()).
                and("state", "=", Apply.PASS).
                and("goods_id", "=", apply.getGoodsId()).
                or("began", "<=", apply.getEnd()).
                and("end", ">=", apply.getEnd()).
                and("state", "=", Apply.PASS).
                and("goods_id", "=", apply.getGoodsId()), null);
        if(list.isEmpty()){
            return true;
        }else{
            return false;
        }
    }
    
    public List<Apply> listByState(int state){
        List<Apply> list=this.getList(Cnd.where("state", "=", state), null);
        for(Apply a:list){
            this.dao().fetchLinks(a, null);
            this.dao().fetchLinks(a.getUser(), null);
        }
        return list;
    }
    
    public List<Apply> list(int userId){
         List<Apply> list=this.getList(Cnd.where("user_id", "=", userId), null);
        for(Apply a:list){
            this.dao().fetchLinks(a, null);
            this.dao().fetchLinks(a.getUser(), null);
        }
        return list;
    }
    
    @Override
    public List<Apply> getAll(){
        List<Apply> list=super.getAll();
        for(Apply a:list){
            this.dao().fetchLinks(a, null);
            this.dao().fetchLinks(a.getUser(), null);
        }
        return list;
    }
    
}
