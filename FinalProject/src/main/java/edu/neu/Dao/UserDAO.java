package edu.neu.Dao;

import edu.neu.Pojo.Employee;
import edu.neu.Pojo.Job;
import edu.neu.Pojo.Manager;
import edu.neu.Pojo.User;
import org.hibernate.HibernateException;
import org.hibernate.query.Query;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

public class UserDAO extends DAO {
    public User checkLogin(String un, String up) {

        try {
            begin();
            String hqlQuery = "From User Where uname=:un and upassword=:up";
            System.out.println("checklogin执行了");
            Query query = getSession().createQuery(hqlQuery);
            query.setString("un", un);
            query.setString("up", up);
            query.setComment("***Login HQL in UserDao***");
            User user=  (User) query.uniqueResult();
            close();
            return user;
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    public User addUser(User user) {

        try {
            begin();
            System.out.println("adduser execute");
            getSession().save(user);
            commit();

        } catch (HibernateException e) {
            getSession().getTransaction().rollback();
        } finally {
            getSession().close();
        }
        return user;
    }
     public User getUser(String userName){
         String hql="FROM User where uname=:username";
         Query query =getSession().createQuery(hql);
         query.setString("username",userName);
         return (User) query.uniqueResult();
     }

    public Manager updateManagerInfo(String userId,String name,String  age,String phone,String address,String email)throws Exception{
        try {
            begin();
            System.out.println("updateManageInfo execute");
            String hqlQuery = "from User where userId = :uid";
             Query q = getSession().createQuery(hqlQuery);
            q.setString("uid", userId);
            Manager manager =(Manager)q.uniqueResult();
            if(manager!=null){
                manager.setName(name);
                manager.setAge(Integer.parseInt(age));
                manager.setPhone(phone);
                manager.setAddress(address);
                manager.setEmail(email);
                getSession().update(manager);
                commit();
                close();
                return manager;
            }else{
                return null;
            }

        } catch (HibernateException e) {
            rollback();
            throw new Exception("Exception while creating user: " + e.getMessage());
        }
    }

    public Employee updateEmployeeInfo(String userId, String name, String  age, String phone, String address, String email) throws Exception {
        try {
            begin();
            System.out.println("updatEmployeeInfo execute");
            String hqlQuery = "from User where userId = :uid";
            Query q = getSession().createQuery(hqlQuery);
            q.setString("uid", userId);
            Employee employee =(Employee) q.uniqueResult();
            if(employee!=null){
                employee.setName(name);
                employee.setAge(Integer.parseInt(age));
                employee.setPhone(phone);
                employee.setAddress(address);
                employee.setEmail(email);
                getSession().update(employee);
                commit();
                close();
                return employee;
            }else{
                return null;
            }

        } catch (HibernateException e) {
            rollback();
            throw new Exception("Exception while creating user: " + e.getMessage());
        }
    }

    public List<User> getAllUser(){
        List<User> allUserList = new ArrayList<>();
        try {
            begin();
            String hql = "from User ";
            Query q = getSession().createQuery(hql);
            allUserList = q.list();
        } catch (HibernateException e) {
            getSession().getTransaction().rollback();
        } finally {
            close();
        }
        return allUserList;
    }

    public User get(String username){
        try {
            begin();
            org.hibernate.Query q = getSession().createQuery("from User where uname = :uname");
            q.setString("uname", username);
            User user = (User) q.uniqueResult();
            close();
            return user;
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return null;
    }
}

