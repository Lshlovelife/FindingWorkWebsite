package edu.neu.Dao;

import edu.neu.Pojo.Application;
import org.hibernate.HibernateException;
import org.hibernate.query.Query;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

public class ApplicationDAO extends DAO{


    public int addApplication(Application application){
        int result = 0;
        try {
            begin();
            System.out.println("addApplication execute");
            getSession().save(application);
            commit();
            result=1;
        } catch (HibernateException e) {
            getSession().getTransaction().rollback();
        } finally {
            getSession().close();
        }
        return result;
    }

    public List<Application> viewJobApplication(String jid){
        List<Application> list = new ArrayList<>();
        try {
            begin();
            String hql = "from Application where job =:jid";
            Query q = getSession().createQuery(hql);
            q.setString("jid", jid);
            list = q.list();
            close();
            return list;
        } catch (HibernateException e) {
            getSession().getTransaction().rollback();
        }
        return  null;
    }


     public List<Application> viewApplicationRecord(String uid){
        List<Application> list = new ArrayList<>();
         try {
             begin();
             String hql = "from Application where employee =:uid";
             Query q = getSession().createQuery(hql);
             q.setString("uid", uid);
             list = q.list();
             close();
             return list;
         } catch (HibernateException e) {
             getSession().getTransaction().rollback();
         }
       return null;
     }

     public void withdrawApplication(String applicationID){
         try {
             begin();
             String hql = "delete from Application where applicationID = :applicationID";
             Query q = getSession().createQuery(hql);
             q.setString("applicationID", applicationID);
             q.executeUpdate() ;
             commit();

         } catch (HibernateException e) {
             getSession().getTransaction().rollback();
         } finally {
             getSession().close();
         }
     }

     public void deleteApplicationByJobID(String jobID){
        System.out.println("deleteApplicationByJobID exe");
         try {
             begin();
             String hql = "delete from Application where job=:jid";
             Query q = getSession().createQuery(hql);
             q.setString("jid",jobID);
             q.executeUpdate() ;
             commit();

         } catch (HibernateException e) {
             getSession().getTransaction().rollback();
         } finally {
             getSession().close();
         }
     }
}
