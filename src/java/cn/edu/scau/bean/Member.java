package cn.edu.scau.bean;


import org.nutz.dao.entity.annotation.*;


@Table("member")
public class Member implements java.io.Serializable {

    @Column
    @Id
    private int id;
    @Column
    private String name;
    @Column
    private String major;
    @Column
    private String academy;
    @Column
    private int grade;
    @Column("organization_id")
    private int organizationId;
    @Column("student_id")
    private String studentId;
    
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
    public void  setMajor (String major) {
        this.major = major;
    }
    
    public String getMajor () {
        return major;
    }
    public void  setAcademy (String academy) {
        this.academy = academy;
    }
    
    public String getAcademy () {
        return academy;
    }
    public void  setGrade (int grade) {
        this.grade = grade;
    }
    
    public int getGrade () {
        return grade;
    }
    public void  setOrganizationId (int organizationId) {
        this.organizationId = organizationId;
    }
    
    public int getOrganizationId () {
        return organizationId;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

}
