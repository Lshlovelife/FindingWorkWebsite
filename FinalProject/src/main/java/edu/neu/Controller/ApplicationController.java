package edu.neu.Controller;


import edu.neu.Dao.ApplicationDAO;
import edu.neu.Dao.JobDAO;
import edu.neu.Pojo.Application;
import edu.neu.Pojo.Employee;
import edu.neu.Pojo.Job;
import edu.neu.Pojo.User;
import edu.neu.Util.FileUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ApplicationController {

    @RequestMapping(value = "applyJob.htm",method = RequestMethod.GET)
    public String applyJob(HttpServletRequest request){
         String jid = request.getParameter("jobId");
         System.out.println(jid);
         HttpSession session = request.getSession();

        session.setAttribute("jid",jid);
        return "applyJob";
    }

    @RequestMapping(value="applySuccess",method = RequestMethod.POST)
    public String applySuccess(HttpServletRequest request, JobDAO jobDAO, ApplicationDAO applicationDAO,@RequestParam("uploadFile") MultipartFile file) throws IOException {
        System.out.println("applySuccess exe");
        HttpSession session =  request.getSession();
        User currentUser = (User) session.getAttribute("user");
        Employee employee= (Employee)currentUser;
        String jid = (String) session.getAttribute("jid");

        Job job = jobDAO.getJobInfo(jid);

        FileUtil fu = new FileUtil();
        String resumePath = fu.createResumeJobFolder(jid, currentUser);


        String fileName = file.getOriginalFilename().replace(" ", "_");

        File localFile = new File(resumePath + File.separator + fileName);

        file.transferTo(localFile);

        String description = request.getParameter("description");
        String graduationSchool = request.getParameter("graduationSchool");

        Application application = new Application();

        application.setDescription(description);
        application.setGraduationSchool(graduationSchool);
        application.setJob(job);
        application.setEmployee(employee);

        application.setResumePath(localFile.getPath());

        List<Application> empAppList = new ArrayList<>();
        employee.setAppList(empAppList);
        job.setAppList(empAppList);
        int result = applicationDAO.addApplication(application);
        if(result==1){
            return "applySuccess";
        }else{
            return "error";
        }

    }


    @RequestMapping(value = "viewApplication.htm",method = RequestMethod.GET)
    public String viewApplication(HttpServletRequest request,ApplicationDAO applicationDAO){
        System.out.println("viewApplication exe");
        String jid = request.getParameter("jobId");
        List<Application> list = new ArrayList<>();
        list = applicationDAO.viewJobApplication(jid);
        HttpSession session = request.getSession();
        session.setAttribute("jobApplication",list);
        return "viewJobApplication";
    }
    @RequestMapping(value="viewRecord.htm",method = RequestMethod.GET)
    public String viewRecord(HttpServletRequest request,ApplicationDAO applicationDAO){
       System.out.println("viewRecord exe");
       String uid =request.getParameter("uid");
       List<Application> list = new ArrayList<>();
       list = applicationDAO.viewApplicationRecord(uid);
       HttpSession session =request.getSession();
       session.setAttribute("record",list);

        return "viewRecord";
    }

    @RequestMapping(value = "deleteApplication",method = RequestMethod.GET)
    public String withdrawApplication(HttpServletRequest request,ApplicationDAO applicationDAO){
       System.out.println("withdraw exe");
       String aid = request.getParameter("aid");
       applicationDAO.withdrawApplication(aid);
        return "withdrawApplication";
    }

    @RequestMapping(value="downloadResume.htm",method = RequestMethod.GET)
    public String downloadResume(HttpServletRequest request,FileUtil fileUtil){
        HttpSession session=request.getSession();
        User user = (User) session.getAttribute("user");
        String name =request.getParameter("aName");
        String path = request.getParameter("path");
        fileUtil.download(name,"resume",path);
        return "downloadSuccess";
    }
}
