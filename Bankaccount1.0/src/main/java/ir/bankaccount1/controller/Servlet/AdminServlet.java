package ir.bankaccount1.controller.Servlet;

import ir.bankaccount1.controller.PersonController;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDateTime;

@WebServlet("/admin")
public class AdminServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String name=req.getParameter("name");
        String family=req.getParameter("family");
        String nc_number=req.getParameter("nc_number");
        String father_name=req.getParameter("father_name");
        String cc_number=req.getParameter("cc_number");
        LocalDateTime create_date= LocalDateTime.parse(req.getParameter("create_date"));

        String msg=PersonController.save(name,family,nc_number,father_name,cc_number,create_date);

        resp.sendRedirect("AdminPage.jsp");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id= Integer.parseInt(req.getParameter("id"));
        String cc_number=req.getParameter("cc_number");

        String msg=PersonController.edit(id,cc_number);

        resp.getWriter().write(msg);
    }



    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id= Integer.parseInt(req.getParameter("id"));
        System.out.println("Servlet Delete " + id);

        String msg=PersonController.remove(id);

        resp.getWriter().write(msg);
    }
}
