package com.topjava.webapp.web;

import com.Config;
import com.topjava.webapp.model.Resume;
import com.topjava.webapp.storage.Storage;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.Writer;

@WebServlet("/resume")
public class ResumeServlet extends HttpServlet {
    private Storage storage;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        storage = Config.get().getStorage();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
//        response.setHeader("Content-type", "text/html; charset=UTF-8");
//        String name = request.getParameter("name");
//        response.getWriter().write(name == null ? "<h1>Hello world!</h1>" : "Hello " + name);
        Writer writer = response.getWriter();
        writer.write(
                "<html>" +
                        "<head>" +
                        "   <title>All resumes</title>" +
                        "</head>" +
                        "<body>" +
                        "   <table border='1'>" +
                        "        <tr>" +
                        "           <th>uuid</th>" +
                        "           <th>full name</th>" +
                        "       </tr>");
        for (Resume resume : storage.getAllSorted()) {
            writer.write("<tr>" +
                            "   <td>" + resume.getUuid() + "</td>" +
                            "   <td>" + resume.getFullName() + "</td>" +
                            "</tr>");
        }
        writer.write("</table>" +
                        "</body>" +
                "</html>");

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
