package servlet;

import bean.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import util.JDBC;

import java.io.IOException;

@WebServlet("/loginServlet")
public class loginServlet extends HttpServlet {

    public static boolean isnull(String str){
        if(str.equals("")){
            return true;
        }
        return false;
    }
    public static String login(String uname,String upwd){
        JDBC jdbc = new JDBC();
        User user = jdbc.findUser(uname);
        if(isnull(uname)|| isnull(upwd)){
            return "用户名或密码为空";
        } else if(user.getUpwd().equals(upwd)){
            return "登录成功";
        }else{
            return "用户名或密码错误";
        }
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uname = request.getParameter("username");
        String upwd = request.getParameter("upwd");
        JDBC jdbc = new JDBC();
        User user = jdbc.findUser(uname);
        if(login(uname,upwd).equals("登录成功")){
            request.getSession().setAttribute("user",user);
            request.getRequestDispatcher("login_success.jsp").forward(request,response);
        }else{
            request.setAttribute("error",login(uname,upwd));
            request.getRequestDispatcher("login_failure.jsp").forward(request,response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
