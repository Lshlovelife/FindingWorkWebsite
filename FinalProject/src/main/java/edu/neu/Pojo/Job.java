package edu.neu.Pojo;

import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Proxy(lazy = false)
@Table(name="jobtable")
public class Job {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="jobId")
    private Integer jobId;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="UserID")
    private Manager manager;

//    private Employee employee;
    @NotEmpty(message = "title cant be empty")
    private String title;

    @NotEmpty(message = "company name cant be empty")
    private String companyName;

    @NotEmpty(message = "type cant be empty")
    private String typeOfJob;
    @NotEmpty(message = "description cant be empty")
    private String description;

    private Integer numberOfPosition;

    @OneToMany(mappedBy = "job")
    private List<Application> appList;


    public Job() {
        System.out.println("job initial");
    }

    public Integer getNumberOfPosition() {
        return numberOfPosition;
    }

    public void setNumberOfPosition(Integer numberOfPosition) {
        this.numberOfPosition = numberOfPosition;
    }

    public Integer getJobId() {
        return jobId;
    }

    public void setJobId(Integer jobId) {
        this.jobId = jobId;
    }

    public Manager getManager() {
        return manager;
    }

    public void setManager(Manager manager) {
        this.manager = manager;
    }

//    public Employee getEmployee() {
//        return employee;
//    }
//
//    public void setEmployee(Employee employee) {
//        this.employee = employee;
//    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getTypeOfJob() {
        return typeOfJob;
    }

    public void setTypeOfJob(String typeOfJob) {
        this.typeOfJob = typeOfJob;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Application> getAppList() {
        return appList;
    }

    public void setAppList(List<Application> appList) {
        this.appList = appList;
    }
}
