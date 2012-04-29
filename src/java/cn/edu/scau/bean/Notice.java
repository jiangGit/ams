package cn.edu.scau.bean;

import org.nutz.dao.entity.annotation.*;


@Table("notice")
public class Notice implements java.io.Serializable {

    @Column
    @Id
    private int id;
    @Column
    private String title;
    @Column
    private String content;
    @Column
    private java.sql.Timestamp time;

    public void  setId (int id) {
        this.id = id;
    }
    
    public int getId () {
        return id;
    }
    public void  setTitle (String title) {
        this.title = title;
    }
    
    public String getTitle () {
        return title;
    }
    public void  setContent (String content) {
        this.content = content;
    }
    
    public String getContent () {
        return content;
    }
    public void  setTime (java.sql.Timestamp time) {
        this.time = time;
    }
    
    public java.sql.Timestamp getTime () {
        return time;
    }

}
