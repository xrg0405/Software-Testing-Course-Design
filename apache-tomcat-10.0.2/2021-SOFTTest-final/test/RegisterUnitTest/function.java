package RegisterUnitTest;

import bean.User;
import util.JDBC;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class function {

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

    public  boolean isnull(String str) {
        if (str.equals("")) {
            return true;
        }
        return false;
    }

    public String login(String uname,String upwd){
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

}
