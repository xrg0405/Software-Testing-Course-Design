<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>登陆失败</title>
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
            width: 300px;

            margin: 50px auto 0;
        }

        .reg_form ul {
            margin-top: 20px;
            margin-bottom: 40px;
        }

        .reg_form p {
            font-size: 18px;
            font-weight: 600;
            color: orangered;
        }

        .reg_form ul li {
            margin-top: 20px;
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
    <h3>登陆失败
        <div class="time" id="time"> </div>
        <div class="date">软件测试课设-1902-6
        </div>
    </h3>
    <div class="reg_form">
        <h1>登录失败！</h1>
        <%
            String error = (String) request.getAttribute("error");
        %>
        <h2><%=error%>></h2>
        <p> <a href="login.jsp">重新登陆</a></p>
    </div>
</div>
</body>
</html>
