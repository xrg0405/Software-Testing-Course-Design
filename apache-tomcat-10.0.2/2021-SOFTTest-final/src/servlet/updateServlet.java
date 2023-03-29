package servlet;

import bean.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import util.JDBC;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@WebServlet("/updateServlet")
public class updateServlet extends HttpServlet {

    public static boolean isEmail(String email) {
        Pattern emailPattern = Pattern.compile("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*");
        Matcher matcher = emailPattern.matcher(email);
        if(matcher.find()){
            return true;
        }
        return false;
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String method = request.getParameter("method");
        JDBC jdbc = new JDBC();
        User user = new User();
        user = (User) request.getSession().getAttribute("user");
        int flag = 0;
        if (user != null) {
            if (method.equals("updatePwd")) {
                int pwdflag = 0, pwdflag1 = 0, pwdflag2 = 0, pwdflag3 = 0;
                String oldpwd = request.getParameter("oldpwd");
                String newpwd1 = request.getParameter("newpwd1");
                String newpwd2 = request.getParameter("newpwd2");
                String old = jdbc.findpwd(user);
                for (int i = 0; i < newpwd1.length(); i++) {                //判定密码输入规则
                    if (newpwd1.charAt(i) >= '0' && newpwd1.charAt(i) <= '9') {
                        pwdflag1 = 1;
                    } else if (newpwd1.charAt(i) >= 'A' && newpwd1.charAt(i) <= 'Z') {
                        pwdflag2 = 1;
                    } else if (newpwd1.charAt(i) >= 'a' && newpwd1.charAt(i) <= 'z') {
                        pwdflag3 = 1;
                    }
                    if ((pwdflag1 == 1 && pwdflag2 == 1 && pwdflag3 == 1)) {
                        pwdflag = 1;
                        break;
                    }
                }
                if (!old.equals(oldpwd)) {
                    flag = 1;
                }
                if (newpwd1.equals("") || newpwd1.length() < 6 || newpwd1.length() > 10 || newpwd2.equals("") || pwdflag == 0) {
                    flag = 2;
                }
                if (oldpwd.equals(newpwd1)){
                    flag = 3;
                }
                if(!newpwd1.equals(newpwd2)){
                    flag = 4;
                }
                request.setAttribute("flag", flag);
                if (flag != 0) {
                    request.getRequestDispatcher("update_fail.jsp").forward(request, response);
                } else {
                    jdbc.updatePwd(user, newpwd1);
                    response.sendRedirect("update_success.jsp");
                }
            } else if (method.equals("updateEmail")) {
                String email = request.getParameter("email");
                boolean emailflag = jdbc.findEmail(email);
                if (emailflag == true) {
                    flag = 3;
                    request.setAttribute("flag", flag);
                    request.getRequestDispatcher("update_fail.jsp").forward(request, response);
                }else if(isEmail(email) == false){
                    flag = 4;
                    request.setAttribute("flag", flag);
                    request.getRequestDispatcher("update_fail.jsp").forward(request, response);
                } else {
                    jdbc.updateEmail(user, email);
                    response.sendRedirect("update_success.jsp");
                }
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
