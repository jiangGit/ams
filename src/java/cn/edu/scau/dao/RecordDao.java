/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.edu.scau.dao;

import cn.edu.scau.bean.Apply;
import cn.edu.scau.bean.Record;
import java.util.List;
import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.ioc.loader.annotation.IocBean;

/**
 *
 * @author Administrator
 */
@IocBean(args = {"refer:dao"})
public class RecordDao  extends BasicDao<Record>{

    public RecordDao(Dao dao) {
        super(dao);
    }
    
    public Record getByApply(int applyId){
        Record r=this.fetch(Cnd.where("apply_id", "=", applyId));
        this.dao().fetchLinks(r, null);
        this.dao().fetchLinks(r.getApply(), null);
        return r;
    }
    
    public List<Record> records (int goodsId){
        List<Record> list=this.getList(Cnd.where("goods_id", "=", goodsId), null);
        for(Record r:list){
             dao().fetchLinks(r, null);
        }
        return list;
    }
    
    public List<Record> recordsForOrganization (int organizationId){
        List<Record> list=this.getList(Cnd.where("organization_id", "=", organizationId), null);
        for(Record r:list){
             dao().fetchLinks(r, null);
        }
        return list;
    }
    
    public boolean  check(Apply apply){    
        List list=this.query(Cnd.where("began", "<=", apply.getBegan()).
                and("end", ">=", apply.getEnd()).
                and("goods_id", "=", apply.getGoodsId()).
                or("began", "<=", apply.getEnd()).
                and("end", ">=", apply.getEnd()).
                and("goods_id", "=", apply.getGoodsId()), null);
        if(list.isEmpty()){
            return true;
        }else{
            return false;
        }
    }
    
}
