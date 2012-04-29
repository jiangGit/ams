package cn.edu.scau.bean;


import java.util.*;
import org.nutz.dao.entity.annotation.*;


@Table("goods")
public class Goods implements java.io.Serializable {

    @Column
    @Id
    private int id;
    @Column
    private String name;
    @Column
    private String content;

    @Many(target = Record.class, field = "goodsId")
    private List<Record> records;

    public List<Record> getRecords() {
        return records;
    }

    public void setRecords(List<Record> records) {
        this.records = records;
    }
    
    
    
    public void  setId (int id) {
        this.id = id;
    }
    
    public int getId () {
        return id;
    }
    public void  setName (String name) {
        this.name = name;
    }
    
    public String getName () {
        return name;
    }
    public void  setContent (String content) {
        this.content = content;
    }
    
    public String getContent () {
        return content;
    }

}
