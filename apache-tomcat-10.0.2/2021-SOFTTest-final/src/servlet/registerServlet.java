package servlet;

import bean.User;
import util.JDBC;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@WebServlet("/registerServlet")
public class registerServlet extends HttpServlet {
    public String isEmail(String email) {              //判定邮箱
        JDBC jdbc = new JDBC();
        Pattern emailPattern = Pattern.compile("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*");
        Matcher matcher = emailPattern.matcher(email);
        if (isnull(email)) {
            return "邮箱为空";
        } else if (!matcher.find()) {
            return "邮箱输入格式错误";
        } else if (jdbc.findEmail(email)) {
            return "邮箱已注册";
        } else {
            return "输入合法";
        }
    }

    public String getTime() {                             //生成当前时间
        Date d = new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String now = df.format(d);
        return now;
    }

    public String isUserName(String uname) {              //判定用户名
        JDBC jdbc = new JDBC();
        User user = new User();
        user = jdbc.findUser(uname);
        for (int i = 0; i < uname.length(); i++) {
            if (uname.charAt(i) < '0' || (uname.charAt(i) > '9' && uname.charAt(i) < 'A') || (uname.charAt(i) > 'Z' && uname.charAt(i) < 'a') ||  uname.charAt(i) > 'z') {
                return "用户名输入格式错误";
            }
        }
        if (isnull(uname)) {
            return "用户名为空";
        } else if (user != null) {
            return "用户名已存在";
        } else if (uname.length() >= 6 && uname.length() < 10) {
            return "输入合法";
        } else {
            return "用户名输入格式错误";
        }
    }

    public String isPwd(String pwd1, String pwd2) {                     //判定密码输入规则
        int pwdflag1 = 0, pwdflag2 = 0, pwdflag3 = 0;
        for (int i = 0; i < pwd1.length(); i++) {
            if (pwd1.charAt(i) >= '0' && pwd1.charAt(i) <= '9') {
                pwdflag1 = 1;
            } else if (pwd1.charAt(i) >= 'A' && pwd1.charAt(i) <= 'Z') {
                pwdflag2 = 1;
            } else if (pwd1.charAt(i) >= 'a' && pwd1.charAt(i) <= 'z') {
                pwdflag3 = 1;
            }
        }
        if (!pwd1.equals(pwd2)) {
            return "两次密码不一致";
        } else if (pwdflag1 == 1 && pwdflag2 == 1 && pwdflag3 == 1 && pwd1.length() > 6 && pwd1.length() < 12) {
            return "输入合法";
        } else if (isnull(pwd1) || isnull(pwd2)) {
            return "密码为空";
        } else {
            return "密码输入格式错误";
        }
    }

    public String isUnit(String unit) {
        Pattern p = Pattern.compile("^[A-Za-z\u4e00-\u9fa5]+$");
        Matcher m = p.matcher(unit);
        if (unit.length() > 50) {
            return "单位输入格式错误";
        } else if (m.find()) {
            return "输入合法";
        } else {
            return "单位输入格式错误";
        }
    }

    public boolean isnull(String str) {
        if (str.equals("")) {
            return true;
        }
        return false;
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        JDBC jdbc = new JDBC();
        String now = getTime();
        String uname = request.getParameter("username");
        String upwd1 = request.getParameter("upwd1");
        String upwd2 = request.getParameter("upwd2");
        String email = request.getParameter("email");
        String unit = request.getParameter("unit");
        User user = new User();
        User u = new User();                        //用来存储findUser 从数据库中读取的数据
        u = jdbc.findUser(uname);
        user.setEmail(email);
        user.setUname(uname);
        user.setUpwd(upwd1);
        user.setUnit(unit);
        user.setDatetime(now);
        boolean flag = true;

        ArrayList<String> errors = new ArrayList<String>();
        String error;

        String userflag = isUserName(uname);               //判定用户名是否符合
        String pwdflag = isPwd(upwd1,upwd2);               //判定密码是否符合
        String emailflag = isEmail(email);                 //判定邮箱是否符合
        String unitflag = isUnit(unit);                    //判定单位是否符合


        if( userflag.equals("输入合法")) {
            request.getSession().setAttribute("uname",uname);
        }else{
            flag = false;
            error = userflag;
            errors.add(error);
        }
        if( pwdflag.equals("输入合法")){
            request.getSession().setAttribute("upwd1",upwd1);
            request.getSession().setAttribute("upwd2",upwd2);
        }else{
            flag = false;
            error = pwdflag;
            errors.add(error);
        }
        if(emailflag.equals("输入合法")){
            request.getSession().setAttribute("email",email);
        }else{          //邮箱已被注册
            flag = false;
            error = emailflag;
            errors.add(error);
        }
        if (unitflag.equals("输入合法")){                                      //单位输入不合法
            request.getSession().setAttribute("unit",unit);
        }else{
            flag = false;
            error = unitflag;
            errors.add(error);
        }
        if(flag == false){
            request.setAttribute("errors",errors);
            request.getRequestDispatcher("register_fail.jsp").forward(request,response);
        }else{
            jdbc.addUser(user);
            response.sendRedirect("register_success.jsp");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
