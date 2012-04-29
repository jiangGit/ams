package cn.edu.scau.bean;


import org.nutz.dao.entity.annotation.*;


@Table("apply")
public class Apply implements java.io.Serializable {

    public static final int UNREAD=1;
    public static final int PASS=2;
    public static final int FAIL=3; 
    
    @Column
    @Id
    private int id;
    @Column("user_id")
    private int userId;
    @Column("goods_id")
    private int goodsId;
    @Column
    private java.sql.Date began;
    @Column
    private java.sql.Date end;
    @Column
    private java.sql.Timestamp time;
    @Column
    private String reason;
    @Column
    private int state;

    private String taskId;
    
    private boolean isConflict;
 
    @One(target = User.class, field = "userId")
    private User user;
    
    @One(target = Goods.class, field = "goodsId")
    private Goods goods;

    public boolean isIsConflict() {
        return isConflict;
    }

    public void setIsConflict(boolean isConflict) {
        this.isConflict = isConflict;
    }

    
    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }
    public Goods getGoods() {
        return goods;
    }

    public void setGoods(Goods goods) {
        this.goods = goods;
    }

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
    public void  setGoodsId (int goodsId) {
        this.goodsId = goodsId;
    }
    
    public int getGoodsId () {
        return goodsId;
    }
    public void  setBegan (java.sql.Date began) {
        this.began = began;
    }
    
    public java.sql.Date getBegan () {
        return began;
    }
    public void  setEnd (java.sql.Date end) {
        this.end = end;
    }
    
    public java.sql.Date getEnd () {
        return end;
    }
    public void  setTime (java.sql.Timestamp time) {
        this.time = time;
    }
    
    public java.sql.Timestamp getTime () {
        return time;
    }
    public void  setReason (String reason) {
        this.reason = reason;
    }
    
    public String getReason () {
        return reason;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

}
