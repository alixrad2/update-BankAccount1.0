package ir.bankaccount1.controller.Servlet;

import ir.bankaccount1.controller.PersonController;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name=req.getParameter("Username");
        String nc_number=req.getParameter("nationalcode");

        if (name.equals("admin")&nc_number.equals("admin")){
            req.getSession().setAttribute("role","admin");
            req.getRequestDispatcher("/AdminPage.jsp").forward(req,resp);
        }else if (PersonController.findByNameAndNC(name,nc_number).equals("true")){
            String info=PersonController.findByNC_info(nc_number);
            req.getSession().setAttribute("info",info);
            String nextHTML = "/userPage.jsp";
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextHTML);

            dispatcher.forward(req, resp);
            }else{
              resp.sendError(404);
        }

    }

}
