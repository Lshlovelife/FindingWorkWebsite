package edu.neu.Controller;

import edu.neu.Dao.UserDAO;
import edu.neu.Pojo.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
public class AJAXController {
    @RequestMapping(value = "/checkUserExistence.htm", method = RequestMethod.GET)
    @ResponseBody
    public String checkUserNameExisted(HttpServletRequest request, UserDAO userDao) {
        String username = request.getParameter("username");
        User user = userDao.get(username);
        if (user == null) {
            return "You can use this username!";
        } else {
            return "UserName has already been used!";
        }
    }
//    @RequestMapping(value = "/checkAjaxLogin.htm", method = RequestMethod.POST)
//    @ResponseBody
//    public String checkLogin(HttpServletRequest request,UserDAO userDAO){
//        String username = request.getParameter("username");
//        String password = request.getParameter("password");
//        User user = userDAO.checkLogin(username,password);
//
//        String result = "";
//
//        if(user==null){
//            result = result+"invalid username and password";
//        }
//
//
//        return result;
//    }
}
