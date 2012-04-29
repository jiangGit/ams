package cn.edu.scau.bean;


import org.nutz.dao.entity.annotation.*;


@Table("record")
public class Record implements java.io.Serializable {

    @Column
    @Id
    private int id;
    @Column("goods_id")
    private int goodsId;
    @Column("organization_id")
    private int organizationId;
    @Column("apply_id")
    private int applyId;
    @Column
    private java.sql.Date began;
    @Column
    private java.sql.Date end;
    @Column
    private String content;

    @One(target = Goods.class, field = "goodsId")
    private Goods goods;
    
    @One(target = Organization.class, field = "organizationId")
    private Organization organization;
    
    @One(target = Apply.class, field = "applyId")
    private Apply apply;

    public Apply getApply() {
        return apply;
    }

    public void setApply(Apply apply) {
        this.apply = apply;
    }

    public int getApplyId() {
        return applyId;
    }

    public void setApplyId(int applyId) {
        this.applyId = applyId;
    }

    
    public Goods getGoods() {
        return goods;
    }

    public void setGoods(Goods goods) {
        this.goods = goods;
    }

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }
     
    
    public void  setId (int id) {
        this.id = id;
    }
    
    public int getId () {
        return id;
    }
    public void  setGoodsId (int goodsId) {
        this.goodsId = goodsId;
    }
    
    public int getGoodsId () {
        return goodsId;
    }
    public void  setOrganizationId (int organizationId) {
        this.organizationId = organizationId;
    }
    
    public int getOrganizationId () {
        return organizationId;
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
    public void  setContent (String content) {
        this.content = content;
    }
    
    public String getContent () {
        return content;
    }

}
