package servlet;

import java.io.IOException;
import java.util.ArrayList;

import bean.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import util.JDBC;

@WebServlet("/checkServlet")
public class checkServlet extends HttpServlet{
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user =new User();
        ArrayList<User> users =new ArrayList<User>();
        JDBC jdbc=new  JDBC();
        user = (User)request.getSession().getAttribute("user");
        if(user!=null) {
            user=jdbc.check(user);
            request.getSession().setAttribute("user",user);
            users =jdbc.selectpeers(user);
            request.getSession().setAttribute("users", users);
            request.getRequestDispatcher("check.jsp").forward(request, response);
        }
        else {
            response.sendRedirect("login.jsp");
        }
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        doPost(request, response);
    }
}