<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>注册</title>
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
            height: 522px;
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
            width: 600px;

            margin: 50px auto 0;
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
            // window.location = "/2021_SOFTTest_final_war_exploded/registerServlet?time="+time;
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
    <h3>注册新用户
        <div class="login">已有账号，去<a href="login.jsp">登陆</a></div>
        <div class="time" id="time"> </div>
        <div class="date">软件测试课设-1902-6
        </div>
    </h3>
    <%!
        public String isnull(String str){
            if(str == null){
                str = "";
            }
            return str;
        }
    %>
    <%
        String username = "";
        String upwd1 = "";
        String upwd2 = "";
        String email = "";
        String unit = "";

        username = (String)session.getAttribute("uname");
        upwd1 = (String) session.getAttribute("upwd1");
        upwd2 = (String) session.getAttribute("upwd2");
        email = (String) session.getAttribute("email");
        unit = (String) session.getAttribute("unit");
        username = isnull(username);
        upwd1 = isnull(upwd1);
        upwd2 = isnull(upwd2);
        email = isnull(email);
        unit = isnull(unit);
    %>

    <div class="reg_form">
        <form action="/2021_SOFTTest_final_war_exploded/registerServlet" method="post">
            <ul id="list">
                <li id="user"><label>*用户名：</label> <input type="text" class="inp" name="username" value=<%=username%>><span>用户名由6-10位数字或字母组成</span></li>
                <li id="pwd"><label>*登陆密码：</label> <input type="password" class="inp" name="upwd1" value="<%=upwd1%>"><span>密码由6-12位字符组成。至少一个大写字母，一个小写字母，一个数字和其他字符</span></li>
                <li id="check"><label>*确认密码：</label> <input type="password" class="inp" name="upwd2" value="<%=upwd2%>"><span>请确认输入的密码</span></li>
                <li id="e-mail"><label>*邮件地址：</label> <input type="text" class="inp" name="email" value=<%=email%>><span>请输入正确的邮箱地址</span></li>
                <li id="unit"><label>单位：</label> <input type="text" class="inp" name="unit" value=<%=unit%>><span></span></li>
                <li>
                    <input type="submit" value="完成注册" class="btn"><span></span>
                </li>
            </ul>
        </form>
    </div>
</div>
</body>
<script>
    var ul = document.getElementById("list");
    var lis = ul.querySelectorAll('li');
    for (var i = 0; i < lis.length; i++) {
        lis[i].onkeypress = RegisterUnitTest.function () {
            // alert(user.lastElementChild);
            for (var i = 0; i < lis.length; i++) {
                lis[i].lastElementChild.style.display = "none";
            }
            this.lastElementChild.style.display = "block";
        }
    }
</script>
</html>
