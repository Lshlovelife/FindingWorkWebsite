package edu.neu.Controller;


import edu.neu.Dao.ApplicationDAO;
import edu.neu.Dao.JobDAO;
import edu.neu.Pojo.Job;
import edu.neu.Pojo.Manager;
import edu.neu.Pojo.User;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class JobController {

    @RequestMapping(value = "postJob.htm", method = RequestMethod.POST)
    public String addJob(HttpServletRequest request, JobDAO jobDAO, @Validated Job vjob, BindingResult bindingResult, HttpServletResponse response) throws IOException {
        //这样add会在对应的id下么
        System.out.println(vjob.getTitle());
        System.out.println("addJob exe");
        Map<String, Object> resultMap = new HashMap<String, Object>();
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();
        if (bindingResult.hasErrors()) {

            List<ObjectError> errors = bindingResult.getAllErrors();
            for (ObjectError error : errors) {
                System.out.println(error.getDefaultMessage());
                out.println("<script>alert('==="+ error.getDefaultMessage()+"===')</script>");
            }
            resultMap.put("errors", errors);
        }
        User currentUser = (User) session.getAttribute("user");
        Manager manager = (Manager) currentUser;

        String title = request.getParameter("title");
        String cn = request.getParameter("companyName");
        String toj = request.getParameter("typeOfJob");
        String des = request.getParameter("description");
        String nop = request.getParameter("numberOfPosition");

        Job job = new Job();
        job.setTitle(title);
        job.setCompanyName(cn);
        job.setTypeOfJob(toj);
        job.setDescription(des);
        job.setNumberOfPosition(Integer.valueOf(nop));
        job.setManager(manager);
        List<Job> jobList = new ArrayList<>();
        manager.setJoblist(jobList);

//        List<Job> testList = new ArrayList<>();
//        String iiii = String.valueOf(manager.getUserId());
//        testList = jobDAO.getJobList(iiii);
//        for(Job j : testList){
//            System.out.println(j.getTitle());
//        }
//        session.setAttribute("managerJobList",);
        int result = jobDAO.addJob(job);
        if(result==1){
            return "postSuccess";
        }else{
            return "error";
        }

    }

    @RequestMapping(value="updateJobInformation.htm",method = RequestMethod.GET)
    public String updateJobInformation(HttpServletRequest request, JobDAO jobDAO,ModelMap map){


        System.out.println("update get exe");
        String jid=request.getParameter("jobId");
        HttpSession session=request.getSession();

        System.out.println(jid);
        request.setAttribute("jid",jid);

        Job job = jobDAO.getJobInfo(jid);
        map.addAttribute("job",job);
        session.setAttribute("jobUpdate",job);

        return "jobUpdate";
    }

    @RequestMapping(value="updateJobInfoSuccess",method = RequestMethod.POST)
    public String updateJobInfoSuccess(HttpServletRequest request, JobDAO jobDAO, ApplicationDAO applicationDAO) throws Exception {
        String action = request.getParameter("action");
        System.out.println(action);
        HttpSession session = request.getSession();

        System.out.println(session.getAttribute("jobList").getClass());
        Job job = (Job)session.getAttribute("jobUpdate");
        int jobId = job.getJobId();
        if(action.equals("Update")){
            System.out.println("update  post exe");
            String title = request.getParameter("title");
            String cn = request.getParameter("companyName");
            String toj = request.getParameter("typeOfJob");
            String des = request.getParameter("description");
            String nop = request.getParameter("numberOfPosition");

            jobDAO.updateJobInfo(jobId+"",title,cn,toj,des,nop);
            return "updateJobInfoSuccess";
        }else if(action.equals("Delete")){
            System.out.println("delete exe");
            jobDAO.deleteJob(jobId+"");
            applicationDAO.deleteApplicationByJobID(jobId+"");
            return "deleteJobSuccess";
        }
return null;
    }
}
