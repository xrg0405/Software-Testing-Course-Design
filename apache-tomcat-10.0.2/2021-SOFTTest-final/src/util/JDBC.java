package util;

import bean.User;

import java.sql.*;
import java.util.ArrayList;

public class JDBC {
    String url = "jdbc:mysql://10.50.32.41:3306/06-Test-final";
    String username = "root";
    String password = "root";
    private PreparedStatement ps;
    private Connection cn;

    //获得数据库连接
    public Connection getCn(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            cn = DriverManager.getConnection(url,username,password);
            return cn;
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean addUser(User u){
        String sql = "insert into User values(?,?,?,?,?,null,null,null,null)";
        Connection cn = getCn();
        try {
            ps = cn.prepareStatement(sql);
            ps.setString(1,u.getUname());
            ps.setString(2,u.getUpwd());
            ps.setString(3,u.getEmail());
            ps.setString(4,u.getUnit());
            ps.setString(5,u.getDatetime());
            ps.executeUpdate();
            cn.close();
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    public User findUser(String uname){                 //通过uname直接查找一条数据库数据，并返回一个User类
        String sql = "select * from User where uname=?";
        Connection cn = getCn();
        User user = new User();
        try {
            ps = cn.prepareStatement(sql);
            ps.setString(1,uname);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                String username = rs.getString("uname");
                String upwd = rs.getString("upwd");
                String email = rs.getString("email");
                String unit = rs.getString("unit");
                user.setUname(username);
                user.setUpwd(upwd);
                user.setEmail(email);
                user.setUnit(unit);
                cn.close();
                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean findEmail(String email){
        String sql = "select email from User where email=?";
        Connection cn = getCn();
        try {
            ps = cn.prepareStatement(sql);
            ps.setString(1,email);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                cn.close();
                return true;
            }else{
                cn.close();
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


    public boolean updateEmail(User u,String email){
        String sql = "update User SET email = ? where email = ?";
        Connection cn = getCn();
        try {
            ps = cn.prepareStatement(sql);
            ps.setString(1,email);
            ps.setString(2,u.getEmail());
            ps.executeUpdate();
            cn.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean updatePwd(User u,String pwd){
        String sql = "update User SET upwd = ? where uname = ? && upwd = ?";
        Connection cn = getCn();
        try {
            ps = cn.prepareStatement(sql);
            ps.setString(1,pwd);
            ps.setString(2,u.getUname());
            ps.setString(3,u.getUpwd());
            ps.executeUpdate();
            cn.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public String findpwd(User u){
        String sql = "select upwd from User where uname =?";
        Connection cn = getCn();
        try {
            ps = cn.prepareStatement(sql);
            ps.setString(1,u.getUname());
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                cn.close();
                return rs.getString("upwd");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean addinfo(User u){
        String sql = "update User SET realname = ?,phone = ?,birthday = ?,age = ? where uname = ?";
        Connection cn = getCn();
        try {
            ps = cn.prepareStatement(sql);
            ps.setString(1,u.getRealname());
            ps.setString(2,u.getPhone());
            ps.setString(3,u.getBirthday());
            ps.setInt(4, u.getAge());
            ps.setString(5,u.getUname());
            ps.executeUpdate();
            cn.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    public User check(User u) {
        String sql = "select * from User where uname = ?";
        Connection cn = getCn();
        try {
            ps = cn.prepareStatement(sql);
            ps.setString(1,u.getUname());
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                String email = rs.getString("email");
                String unit = rs.getString("unit");
                String time =rs.getString("time");
                String realname=rs.getString("realname");
                String phone=rs.getString("phone");
                String birthday=rs.getString("birthday");
                int age=rs.getInt("age");
                u.setRealname(realname);
                u.setPhone(phone);
                u.setDatetime(time);
                u.setBirthday(birthday);
                u.setAge(age);
                cn.close();
                return u;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<User> selectpeers(User u) {
        String sql = "select * from User where age = ? and uname!= ?";
        Connection cn = getCn();
        ArrayList<User> users = new ArrayList<User>();
        User user = new User();
        try {
            ps = cn.prepareStatement(sql);
            ps.setInt(1,u.getAge());
            ps.setString(2,u.getUname());
            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                String username = rs.getString("uname");
                String upwd = rs.getString("upwd");
                String email = rs.getString("email");
                String unit = rs.getString("unit");
                String time =rs.getString("time");
                String realname=rs.getString("realname");
                String phone=rs.getString("phone");
                String birthday=rs.getString("birthday");
                int age=rs.getInt("age");
                user.setUname(username);
                user.setUpwd(upwd);
                user.setEmail(email);
                user.setUnit(unit);
                user.setUnit(unit);
                user.setDatetime(time);
                user.setRealname(realname);
                user.setPhone(phone);
                user.setBirthday(birthday);
                user.setAge(age);
                users.add(user);
            }
            cn.close();
            return users;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
