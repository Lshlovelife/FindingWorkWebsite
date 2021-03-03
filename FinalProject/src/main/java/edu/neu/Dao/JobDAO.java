package edu.neu.Dao;

import edu.neu.Pojo.Job;
import edu.neu.Pojo.Manager;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class JobDAO extends DAO {


    public int addJob(Job job) {
        int result = 0;
        try {
            begin();
            System.out.println("addJob execute");
            getSession().save(job);
            commit();
            close();
            result = 1;
        } catch (HibernateException e) {
            rollback();
        }
        return result;
    }

    public List<Job> getJobList(String userID) {
        List<Job> jobList = new ArrayList<>();
        try {
            begin();
            String hql = "from Job where manager =:uid";
            Query q = getSession().createQuery(hql);
            q.setString("uid", userID);
            jobList = q.list();
        } catch (HibernateException e) {
            getSession().getTransaction().rollback();
        } finally {
            close();
        }
        return jobList;
    }

    public Job getJobInfo(String jobID) {
        Job job = new Job();
        try {

            begin();
            String hql = "from Job where jobId =:jid";
            Query q = getSession().createQuery(hql);
            q.setString("jid", jobID);
            job = (Job) q.uniqueResult();
            close();
        } catch (HibernateException e) {
            getSession().getTransaction().rollback();
        }
        return job;
    }

    public boolean updateJobInfo(String jobID, String title, String cn, String toj, String des, String nop) throws Exception {
        try {
            begin();
            System.out.println("updatejobinfo execute");
            String hqlQuery = "from Job where jobId = :jid";
            Query q = getSession().createQuery(hqlQuery);

            q.setString("jid", jobID);
            Job currentJob = (Job) q.uniqueResult();
            if (currentJob != null) {
                currentJob.setTitle(title);
                currentJob.setCompanyName(cn);
                currentJob.setTypeOfJob(toj);
                currentJob.setDescription(des);
                currentJob.setNumberOfPosition(Integer.parseInt(nop));
                getSession().update(currentJob);
                commit();
                close();
                return true;
            } else {
                return false;
            }

        } catch (HibernateException e) {
            rollback();
            throw new Exception("Exception while creating user: " + e.getMessage());
        }
    }


    public void deleteJob(String jobID) {
        try {
            begin();
            String hql = "delete from Job where jobId = :jid";
            Query q = getSession().createQuery(hql);
            q.setString("jid", jobID);
            q.executeUpdate() ;
            commit();
            close();

        } catch (HibernateException e) {
            getSession().getTransaction().rollback();
        }
    }

    public List<Job> getAllJob(){
        List<Job> allJobList = new ArrayList<>();
        System.out.println("allUserList exe");
        try {
            begin();
            String hql = "from Job ";
            Query q = getSession().createQuery(hql);
            allJobList = q.list();
        } catch (HibernateException e) {
            getSession().getTransaction().rollback();
        } finally {
            close();
        }
        return allJobList;
    }
}
