package cn.edu.scau.bean;


import java.util.*;
import org.nutz.dao.entity.annotation.*;


@Table("organization")
public class Organization implements java.io.Serializable {

    @Column
    @Id
    private int id;
    @Column
    private String name;
    @Column
    private String content;
    
    
    @Many(target = Member.class, field = "organizationId")
    private List<Member> members;
    
    @Many(target = User.class, field = "organizationId")
    private List<User> principals;
    
    @Many(target = Record.class, field = "organizationId")
    private List<Record> records;

    public List<Member> getMembers() {
        return members;
    }

    public void setMembers(List<Member> members) {
        this.members = members;
    }

    public List<User> getPrincipals() {
        return principals;
    }

    public void setPrincipals(List<User> principals) {
        this.principals = principals;
    }

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
