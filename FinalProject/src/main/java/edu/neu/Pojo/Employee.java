package edu.neu.Pojo;

import org.hibernate.annotations.Proxy;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@Proxy(lazy = false)
public class Employee extends User{
    @Column(name="name")
    private String name;
    @Column(name="age")
    private Integer age;
    @Column(name="phone")
    private String phone;
    @Column(name="email")
    private String email;
    @Column(name="address")
    private String address;

    @OneToMany(mappedBy = "employee")
    private List<Application> appList;

    public Employee() {
        System.out.println("employee initial");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<Application> getAppList() {
        return appList;
    }

    public void setAppList(List<Application> appList) {
        this.appList = appList;
    }
}
