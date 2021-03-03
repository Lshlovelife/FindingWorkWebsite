package edu.neu.Controller;

import edu.neu.Dao.JobDAO;
import edu.neu.Dao.UserDAO;
import edu.neu.Pojo.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

@Controller
public class LoginController {

    @RequestMapping(value = "/login.htm",method = RequestMethod.GET)
    public String login(HttpServletRequest request){
        HttpSession   session   =   request.getSession();
// 获取session中所有的键值
        Enumeration<String> attrs = session.getAttributeNames();
// 遍历attrs中的
        while(attrs.hasMoreElements()){
// 获取session键值
            String name = attrs.nextElement().toString();
            // 根据键值取session中的值
            Object vakue = session.getAttribute(name);
            // 打印结果
            System.out.println("------" + name + ":" + vakue +"--------\n");
        }
        System.out.println("login exe");
        return "login";
    }
    @RequestMapping(value = "/signup.htm",method = RequestMethod.GET)
    public String signup(){
        System.out.println("signup exe");
        return "signup";
    }

    @RequestMapping(value="test.htm",method = RequestMethod.GET)
    public String test(){
        System.out.println("test exe");
        return "test";
    }

    @RequestMapping(value = "/checklogin.htm", method = RequestMethod.POST)
    public String handlePostRequest(HttpServletRequest request, @Validated User vuser, BindingResult bindingResult,HttpServletResponse response, UserDAO userDAO) throws IOException {
        System.out.println(vuser.getUname());
        Map<String, Object> resultMap = new HashMap<String, Object>();
        PrintWriter out = response.getWriter();

        if (bindingResult.hasErrors()) {

            List<ObjectError> errors = bindingResult.getAllErrors();
            for (ObjectError error : errors) {
                System.out.println(error.getDefaultMessage());
                out.println("<script>alert('==="+ error.getDefaultMessage()+"===')</script>");
            }
            resultMap.put("errors", errors);
        }else{
            String uname = request.getParameter("uname");

            System.out.println("handlePostRequest exe");
            String upassword = request.getParameter("upassword");
            User user = userDAO.checkLogin(uname, upassword);
            if(user.equals("null")){
                out.println("<script>alert('invalid username and password');</script>");
            }else{
                HttpSession session = request.getSession();
                session.setAttribute("user", user);

                if (user instanceof Manager) {
                    return "redirect:/manager/dashboard.htm";
                } else if (user instanceof Employee) {
                    return "redirect:/employee/dashboard.htm";
                }else if (user instanceof Admin){
                    return "redirect:/admin/dashboard.htm";
                }
            }
        }



        return null;
    }
        @RequestMapping(value="create.htm",method=RequestMethod.GET)
        public String handlerCreateRequest (HttpServletRequest request, UserDAO userDAO){
            String uname = request.getParameter("newUsername");
            String upassword = request.getParameter("newPassword");
            System.out.println("handleCreateRequest exe");
            HttpSession session = request.getSession();
            String role = request.getParameter("role");
            User user = new User();
            System.out.println(uname+","+upassword);
            if (role.equals("manager")) {
                user = new Manager();
            } else if (role.equals("employee")) {
                user = new Employee();
            } else if (role.equals("admin")){
                user = new Admin();
            }
            user.setUname(uname);
            user.setUpassword(upassword);
            User newUser = userDAO.addUser(user);
            return "createAccountSuccess";

        }
        @RequestMapping(value="logout.htm",method = RequestMethod.GET)
        public String logout(HttpServletRequest request){

        HttpSession session = request.getSession(false);
// 获取session中所有的键值
            Enumeration<String> attrs = session.getAttributeNames();
// 遍历attrs中的
            while(attrs.hasMoreElements()){
// 获取session键值
                String name = attrs.nextElement().toString();
                // 根据键值取session中的值
                Object value = session.getAttribute(name);
                // 打印结果
                System.out.println("------" + name + ":" + value +"--------\n");
            }
        if(session!=null){
            session.invalidate();
        }
        return "logout";
        }



    @RequestMapping(value = "/manager/dashboard.htm", method = RequestMethod.GET)
    public String handleManagerDashboard(HttpServletRequest request, JobDAO jobDAO, ModelMap map) {
        HttpSession session = request.getSession();
        User currentUser = (User) session.getAttribute("user");
        Manager manager = (Manager) currentUser;
        List<Job> jobList = new ArrayList<>();
        String iiii = String.valueOf(manager.getUserId());
        jobList = jobDAO.getJobList(iiii);
        for(Job j : jobList){
            System.out.println(j.getTitle());
        }
        session.setAttribute("jobList",jobList);
        return "manager-dashboard";

    }

    @RequestMapping(value = "/employee/dashboard.htm", method = RequestMethod.GET)
    public String handleEmployeeDashboard(HttpServletRequest request, UserDAO userDao, ModelMap map) {
        return "employee-dashboard";
    }
    @RequestMapping(value = "/admin/dashboard.htm", method = RequestMethod.GET)
    public String handleAdminDashboard(HttpServletRequest request, UserDAO userDao, ModelMap map) {
        HttpSession session = request.getSession();
        List<User> userList = userDao.getAllUser();
        session.setAttribute("userList",userList);

        return "admin-dashboard";
    }
}
