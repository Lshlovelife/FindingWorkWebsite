package edu.neu.Pojo;

import org.hibernate.annotations.Proxy;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Proxy(lazy = false)
@Table(name="apptable")
public class Application {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="applicationID")
    private int applicationID;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="UserID")
    private Employee employee;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="jobId")
    private Job job;

    @Column(name="description")
    @NotNull
    private String description;

    @Column(name = "graduationSchool")
    @NotNull
    private String graduationSchool;

    private String resumePath;

    @Transient
    private MultipartFile resume;

    public Application() {
    }

    public int getApplicationID() {
        return applicationID;
    }

    public void setApplicationID(int applicationID) {
        this.applicationID = applicationID;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getGraduationSchool() {
        return graduationSchool;
    }

    public void setGraduationSchool(String graduationSchool) {
        this.graduationSchool = graduationSchool;
    }

    public String getResumePath() {
        return resumePath;
    }

    public void setResumePath(String resumePath) {
        this.resumePath = resumePath;
    }

    public MultipartFile getResume() {
        return resume;
    }

    public void setResume(MultipartFile resume) {
        this.resume = resume;
    }
}
