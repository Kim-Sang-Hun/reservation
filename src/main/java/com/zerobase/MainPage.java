package com.zerobase;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@RestController
public class MainPage {

    @RequestMapping("/")
    public String index(HttpServletRequest request, HttpServletResponse response) throws IOException {

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter printWriter = response.getWriter();

        String msg = "<html>" +
                "<head>" +
                "<meta charset='UTF-8'" +
                "</head>" +
                "<body>" +
                "<p>hello</p>" +
                "</body>" +
                "</html>";

        printWriter.write(msg);
        printWriter.close();

        return "/";
    }



}