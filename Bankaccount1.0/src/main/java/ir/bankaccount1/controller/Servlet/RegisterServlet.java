package ir.bankaccount1.controller.Servlet;

import ir.bankaccount1.controller.PersonController;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDateTime;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name=req.getParameter("name");
        String family=req.getParameter("family");
        String nc_number=req.getParameter("nc_number");
        String father_name=req.getParameter("father_name");
        String cc_number=req.getParameter("cc_number");
        LocalDateTime createDate= LocalDateTime.parse(req.getParameter("create_date"));
        String msg= PersonController.save(name,family,nc_number,father_name,cc_number,createDate);

        if (PersonController.findByNC(nc_number).equals("true")) {
            resp.sendRedirect("valid.html");
        }else {
            resp.sendError(401);
        }


    }
}




