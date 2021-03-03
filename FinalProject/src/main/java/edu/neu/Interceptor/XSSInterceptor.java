package edu.neu.Interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;

public class XSSInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        try{
            Enumeration<String> paraNames = request.getParameterNames();
            while(paraNames.hasMoreElements()){
                String key = (String)paraNames.nextElement();
                String val = request.getParameter(key);
                System.out.println("拦截器执行了");
                if(xssCheck(val)){
                    request.setAttribute("unsafe_request","true");
                    break;
                }
            }
        }catch (Exception e){
            System.out.println(e);
        }
        //Write Code here

        return true;
    }

    private boolean xssCheck(String value) {
        if (value != null) {
            return (value.matches("<script>(.*?)</script>") || value.matches("\"<script(.*?)>\""));
        }
        return false;
    }
}