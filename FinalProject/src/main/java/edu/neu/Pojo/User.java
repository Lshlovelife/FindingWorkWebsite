package edu.neu.Pojo;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="userstable")
public class User {
    @Id
    @Column(name="UserID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;

    @Basic
    @Column(name="UserName")
    @NotEmpty(message = "username cant be empty")
    private String uname;

    @Basic
    @Column(name="UserPassword")
    @NotEmpty(message = "password cant be empty")
    private String upassword;

    public User() {
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getUpassword() {
        return upassword;
    }

    public void setUpassword(String upassword) {
        this.upassword = upassword;
    }

    public String getType() {
        String typeName = getClass().getName();
        int length = typeName.lastIndexOf(".");
        String type = typeName.substring(length + 1);
        return type;
    }
    }
