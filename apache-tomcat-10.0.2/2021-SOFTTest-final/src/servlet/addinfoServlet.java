package servlet;

import bean.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import util.JDBC;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@WebServlet("/addinfoServlet")
public class addinfoServlet extends HttpServlet {
    public static boolean isRealName(String str) {

        Pattern p = Pattern.compile("^[\u4e00-\u9fa5]{2,4}$|^[A-Za-z]{1,20}$");
        Matcher m = p.matcher(str);
        if (m.find()) {
            return true;
        }
        return false;
    }

    public static boolean numsum(String str) {
        int n=0;
        for(int i=0;i<str.length();i++) {
            if(str.charAt(i)>='0'&&str.charAt(i)<='9')
                n++;
        }
        if(n==8) {
            return true;}
        else
            return false;
    }

    public static boolean isPhone(String phone) {
        if(phone=="") {
            return true;
        }
        Pattern p = Pattern.compile("^[1][3,4,5,7,8][0-9]{9}$");
        Matcher m = p.matcher(phone);
        if(m.find()) {
            return true;
        }
        return false;
    }

    public static boolean isDate(String date){
        if(date.equals(""))
            return false;
        if(!numsum(date))
            return false;
        String str;
        Date d = new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String now = df.format(d);
        String year = date.substring(0,4);
        if(Integer.parseInt(year)<1900){
            return false;
        }
        if(date.contains("年")){

            String month = date.substring(date.indexOf("年")+1,date.indexOf("月"));
            String day = date.substring(date.indexOf("月")+1,date.indexOf("日"));
            str = year+'-'+month+'-'+day;
        }else{
            str = date;
        }
        if(Integer.parseInt(str.substring(0, 4))>=Integer.parseInt(now.substring(0, 4))&&Integer.parseInt(str.substring(5, 7))>=Integer.parseInt(now.substring(5, 7))&&Integer.parseInt(str.substring(8, 10))>=Integer.parseInt(now.substring(8, 10))){
            return false;
        }
        String regex = "(([0-9]{3}[1-9]|[0-9]{2}[1-9][0-9]{1}|[0-9]{1}[1-9][0-9]{2}|[1-9][0-9]{3})-(((0[13578]|1[02])-(0[1-9]|[12][0-9]|3[01]))|((0[469]|11)-(0[1-9]|[12][0-9]|30))|(02-(0[1-9]|[1][0-9]|2[0-8]))))|((([0-9]{2})(0[48]|[2468][048]|[13579][26])|((0[48]|[2468][048]|[3579][26])00))-02-29)(([0-9]{3}[1-9]|[0-9]{2}[1-9][0-9]{1}|[0-9]{1}[1-9][0-9]{2}|[1-9][0-9]{3})-(((0[13578]|1[02])-(0[1-9]|[12][0-9]|3[01]))|((0[469]|11)-(0[1-9]|[12][0-9]|30))|(02-(0[1-9]|[1][0-9]|2[0-8]))))|((([0-9]{2})(0[48]|[2468][048]|[13579][26])|((0[48]|[2468][048]|[3579][26])00))-02-29) ";

        boolean flag = str.matches(regex);
        if(flag) {
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String realname = request.getParameter("realname");
        String phone = request.getParameter("phone");
        String birthday = request.getParameter("birthday");
        int age= 2021-Integer.parseInt(birthday.substring(0, 4));
        JDBC jdbc=new JDBC();
        User user = new User();
        user = (User) request.getSession().getAttribute("user");
        int flag = 0;

        if(user != null){
            user.setRealname(realname);
            user.setPhone(phone);
            user.setBirthday(birthday);
            user.setAge(age);
            request.getSession().setAttribute("user", user);
        }
        if(realname.equals("") || birthday.equals("")){
            flag = 1;
        }else if(isRealName(realname) == false){
            flag = 2;
        }else if(!isPhone(phone)){
            flag = 3;
        }else if(!isDate(birthday)){
            flag = 4;
        }
        if(flag != 0){
            request.getRequestDispatcher("addinfo_fail.jsp").forward(request,response);
        }
        else {
            jdbc.addinfo(user);
            response.sendRedirect("addinfo_success.jsp");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
