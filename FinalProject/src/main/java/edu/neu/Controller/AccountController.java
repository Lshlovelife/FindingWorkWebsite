package edu.neu.Controller;


import edu.neu.Dao.JobDAO;
import edu.neu.Dao.UserDAO;
import edu.neu.Pojo.Employee;
import edu.neu.Pojo.Job;
import edu.neu.Pojo.Manager;
import edu.neu.Pojo.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class AccountController {



    @RequestMapping(value = "ManagerInfo", method = RequestMethod.GET)
    public String getAccountInfo(HttpServletRequest request, ModelMap map) {
        HttpSession session = request.getSession();
        User currentUser = (User) session.getAttribute("user");
        Manager manager = (Manager) currentUser;
        System.out.println("getManagerInfo exe");
        map.addAttribute("manager", manager);
        return "ManagerInfo";
    }

    @RequestMapping(value = "EmployeeInfo", method = RequestMethod.GET)
    public String getAccountInfo1(HttpServletRequest request, ModelMap map) {
        HttpSession session = request.getSession();
        User currentUser = (User) session.getAttribute("user");
        Employee employee = (Employee) currentUser;
        System.out.println("getEmployeeInfo exe");
        map.addAttribute("employee", employee);
        return "EmployeeInfo";
    }

    @RequestMapping(value = "ManagerInfo", method = RequestMethod.POST)
    public String updateAccountInfo(HttpServletRequest request, UserDAO userDAO, ModelMap map) throws Exception {
        HttpSession session = request.getSession();
        System.out.println("updatemanagerInfo exe");
        if(request.getAttribute("unsafe_request") == "true"){
            return "error";
        }
        System.out.println(session.getAttribute("user").getClass());
        User user = (User) session.getAttribute("user");
        String action = request.getParameter("action");

        int currentId = user.getUserId();
        if (action.equals("modify-info")) {
            // modify basic info
            String name = request.getParameter("name");
            String age = request.getParameter("age");
            String phone = request.getParameter("phone");
            String address = request.getParameter("address");
            String email = request.getParameter("email");
            User newUser=(User)userDAO.updateManagerInfo(currentId + "", name, age, phone, address, email);
            session.setAttribute("user",newUser);
//            map.addAttribute("status", "changeBasic");
        } else if (action == "modify-password") {

        }
        return "manager-updateSuccess";
    }

    @RequestMapping(value = "EmployeeInfo", method = RequestMethod.POST)
    public String updateAccountInfo1(HttpServletRequest request, UserDAO userDAO, ModelMap map) throws Exception {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        System.out.println("updateemployeeInfo exe");
        if(request.getAttribute("unsafe_request") == "true"){
            return "error";
        }
        String action = request.getParameter("action");
        int currentId = user.getUserId();
        if (action.equals("modify-info")) {
            // modify basic info
            String name = request.getParameter("name");
            String age = request.getParameter("age");
            String phone = request.getParameter("phone");
            String address = request.getParameter("address");
            String email = request.getParameter("email");


            User newUser=(User)userDAO.updateEmployeeInfo(currentId + "", name, age, phone, address, email);
            session.setAttribute("user",newUser);
            map.addAttribute("status", "changeBasic");
        } else if (action == "modify-password") {

        }
        return "employee-updateSuccess";
    }

    @RequestMapping(value = "PostJob.htm",method = RequestMethod.GET)
    public String postJob(HttpServletRequest request,  ModelMap map){
        System.out.println("account postjob exe");

        return "postJob";
    }

    @RequestMapping(value = "viewAllJob",method = RequestMethod.GET)
    public String viewAllJob(HttpServletRequest request, JobDAO jobDAO){
        System.out.println("view all job 执行了");
        List<Job> allJobList = jobDAO.getAllJob();
        HttpSession session=request.getSession();
        session.setAttribute("allJobList",allJobList);

        return "allJob";
    }



}
