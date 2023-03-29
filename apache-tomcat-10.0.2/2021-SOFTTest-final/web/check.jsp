<%@page import="java.util.ArrayList"%>
<%@page import="bean.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>查看信息</title>
    <style>
        body {
            width: 1200px;
            margin: 0 auto;
        }

        li {
            list-style: none
        }

        button {
            cursor: pointer
        }

        a {
            color: #666;
            text-decoration: none
        }

        a:hover {
            color: #c81623
        }

        button,
        input {
            font-family: Microsoft YaHei, Heiti SC, tahoma, arial, Hiragino Sans GB, "\5B8B\4F53", sans-serif;
            border: 0;
            outline: none;
        }

        .registerarea {

            border: 1px solid #ccc;
            margin-top: 20px;
        }

        .registerarea h3 {
            height: 42px;
            border-bottom: 1px solid #ccc;
            background-color: #ececec;
            line-height: 42px;
            padding: 0 10px;
            font-size: 18px;
            font-weight: 400;
            margin-top: 0;
        }

        .login {
            float: right;
            font-size: 14px;
        }

        .login a {
            color: #c81523;
        }

        .date {
            float: right;
            font-size: 14px;
            font-weight: 600;
            margin-right: 10px;
        }

        .time {
            float: right;
            font-size: 14px;
            font-weight: 600;
            margin-right: 30px;
        }

        .reg_form {
            width: 900px;

            margin: 50px auto 0;
            margin-left:400px;
        }

        .reg_form ul li {
            margin-bottom: 20px;
        }

        .reg_form ul li label {
            display: inline-block;
            width: 88px;
            text-align: right;
        }

        .reg_form ul li span {
            display: none;
            margin-left: 90px;
            font-size: 13px;
            color: #666;
        }


        .reg_form ul li .inp {
            width: 242px;
            height: 37px;
            border: 1px solid #ccc;
        }

        .btn {
            width: 200px;
            height: 34px;
            background-color: #c81623;
            font-size: 14px;
            color: #fff;
            margin: 30px 0 0 100px;
        }
    </style>

    <script>
        window.onload = RegisterUnitTest.function () {

            setInterval(RegisterUnitTest.function () {
                fnDate();
            }, 1000);
        }


        RegisterUnitTest.function fnDate() {
            var oDiv = document.getElementById("time");
            var date = new Date();
            var year = date.getFullYear();
            var month = date.getMonth();
            var data = date.getDate();
            var hours = date.getHours();
            var minute = date.getMinutes();
            var second = date.getSeconds();
            var time = year + "-" + fnW((month + 1)) + "-" + fnW(data) + " " + fnW(hours) + ":" + fnW(minute) + ":" + fnW(second);
            oDiv.innerHTML = time;
        }

        RegisterUnitTest.function fnW(str) {
            var num;
            str >= 10 ? num = str : num = "0" + str;
            return num;
        }

    </script>
</head>
<body>
<div class="registerarea">
    <h3>查看信息
        <div class="login"><a href="logout.jsp">退出登录</a></div>
        <div class="time" id="time"> </div>
        <div class="date">软件测试课设-1902-6
        </div>
    </h3>
    <%
        ArrayList<User> users =new ArrayList<User>();
        users =(ArrayList<User>) session.getAttribute("users");
        User user = new User();
        user = (User) session.getAttribute("user");
        if (user != null) {
    %>
    <div class="reg_form">
        <div>
            <h2>注册信息</h2>
            <ul>
                <li>用户名:  <%=user.getUname() %></li>
                <li>密码:  <%=user.getUpwd() %></li>
                <li>邮箱:  <%=user.getEmail() %></li>
                <li>单位:  <%=user.getUnit() %></li>
                <li>注册时间:  <%=user.getDatetime() %></li>
            </ul>
        </div>
        <div>
            <h2>补充信息</h2>
            <ul>
                <li>真实姓名:  <%=user.getRealname() %></li>
                <li>电话:  <%=user.getPhone() %></li>
                <li>出生日期:  <%=user.getBirthday() %></li>
            </ul>
        </div>
        <div>
            <h2>同年龄人注册信息：</h2>
            <%if(users!=null){ %>
            <table align="center"  border="1" cellspacing="0" style="transform:translateX(-250px);margin-botttom:30px">
                <tr>
                    <th>用户名：</th>
                    <th>邮箱： </th>
                    <th>单位：</th>
                    <th>注册时间：</th>
                    <th>真实姓名：</th>
                    <th>电话：</th>
                    <th>出生日期：</th>
                    <th>年龄：</th>
                </tr>
                <%
                    for(int i=0;i<users.size();i++){%>
                <tr>
                    <td><%=users.get(i).getUname() %></td>
                    <td><%=users.get(i).getEmail()%></td>
                    <td><%=users.get(i).getUnit()%></td>
                    <td><%=users.get(i).getDatetime() %></td>
                    <td><%=users.get(i).getRealname() %></td>
                    <td><%=users.get(i).getPhone() %></td>
                    <td><%=users.get(i).getBirthday() %></td>
                    <td><%=users.get(i).getAge() %></td>
                </tr>
                <%
                    }}
                else{
                %>
                <p>暂无同龄人信息</p>
                <%
                    }
                %>
            </table>

        </div>
         <div style="height:20px;margin-top:20px"></div>
    </div>
</div>
<%} else {%>
请先<a href="login.jsp">登录</a>
<%}%>

</body>
</html>