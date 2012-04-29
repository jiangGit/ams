package cn.edu.scau.bean;


import org.nutz.dao.entity.annotation.*;


@Table("user")
public class User implements java.io.Serializable {

    public static final int ADMIN=1;
    public static final int FUNCTIONARY=2;
    public static final int PRINCIPAL=3;
    
    @Column
    @Id
    private int id;
    @Column
    private String name;
    @Column
    private int role;
    @Column
    private String password;
    @Column("organization_id")
    private int organizationId;
    @Column
    private String email;
    
    @One(target = Organization.class, field = "organizationId")
    private Organization organization;

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
    public void  setName (String name) {
        this.name = name;
    }
    
    public String getName () {
        return name;
    }
    public void  setRole (int role) {
        this.role = role;
    }
    
    public int getRole () {
        return role;
    }
    public void  setPassword (String password) {
        this.password = password;
    }
    
    public String getPassword () {
        return password;
    }
    public void  setOrganizationId (int organizationId) {
        this.organizationId = organizationId;
    }
    
    public int getOrganizationId () {
        return organizationId;
    }
    public void  setEmail (String email) {
        this.email = email;
    }
    
    public String getEmail () {
        
        return email;
    }

}
