package cn.edu.scau.bean;


import org.nutz.dao.entity.annotation.*;


@Table("inform")
public class Inform implements java.io.Serializable {

    @Column
    @Id
    private int id;
    @Column("user_id")
    private int userId;
    @Column
    private String content;
    @Column
    private java.sql.Timestamp tiem;
    
    @One(target = User.class, field = "userId")
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    

    public void  setId (int id) {
        this.id = id;
    }
    
    public int getId () {
        return id;
    }
    public void  setUserId (int userId) {
        this.userId = userId;
    }
    
    public int getUserId () {
        return userId;
    }
    public void  setContent (String content) {
        this.content = content;
    }
    
    public String getContent () {
        return content;
    }
    public void  setTiem (java.sql.Timestamp tiem) {
        this.tiem = tiem;
    }
    
    public java.sql.Timestamp getTiem () {
        return tiem;
    }

}
