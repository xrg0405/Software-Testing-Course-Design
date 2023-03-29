<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>登录</title>
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
    <h3>登录
        <div class="login">未拥有账号，去<a href="register.jsp">注册</a></div>
        <div class="time" id="time"> </div>
        <div class="date">软件测试课设-1902-6
        </div>
    </h3>
    <div class="reg_form">
        <form action="/2021_SOFTTest_final_war_exploded/loginServlet" method="post">
            <ul id="list">
                <li id="user"><label>用户名：</label> <input type="text" class="inp" name="username"></li>
                <li id="pwd"><label>密&nbsp码：</label> <input type="password" class="inp" name="upwd"></li>
                <li>
                    <input type="submit" value="登录" class="btn"><span></span>
                </li>
            </ul>
        </form>
    </div>
</div>
</body>
</html>
