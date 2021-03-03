package edu.neu.Util;

import com.lowagie.text.Document;
import com.lowagie.text.Table;
import com.lowagie.text.pdf.PdfWriter;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;
import org.springframework.web.servlet.view.document.AbstractPdfView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

public class PDFGenerator extends AbstractPdfView {

    @Override
    protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
        String title = httpServletRequest.getParameter("title");
        String cn = httpServletRequest.getParameter("cn");
        String toj = httpServletRequest.getParameter("toj");

        String des = httpServletRequest.getParameter("des");
        String  num = httpServletRequest.getParameter("num");

//        ApplicationContext context = new ClassPathXmlApplicationContext("dispatcher-servlet.xml");
//        People people = context.getBean("people",People.class);
//        System.out.println(people.getName());


        Map<String, String> userData = new HashMap<>();
        userData.put("Title", title);
        userData.put("Company Name", cn);
        userData.put("Type Of Job", toj);
        userData.put("Description", des);
        userData.put("Number of position",num);
//        for(int i=0;i<hobby.length;i++){
//            userData.put("hobby"+(i+1),hobby[i]);
//        }

        Table table = new Table(2);



        for (Map.Entry<String, String> entry : userData.entrySet()) {
            table.addCell(entry.getKey());
            table.addCell(entry.getValue());
        }
        document.add(table);
    }
}

