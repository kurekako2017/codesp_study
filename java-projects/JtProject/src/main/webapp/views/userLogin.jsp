<%--
    用户登录页面
    功能：提供用户登录表单，验证用户名和密码
    提交到：/userloginvalidate 接口
--%>
<!doctype html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <!-- 响应式视口设置 -->
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">

    <!-- 引入 Bootstrap 4.4.1 CSS框架 -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">

    <!-- 引入 FontAwesome 图标库 -->
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.0/css/all.css"
          integrity="sha384-lZN37f5QGtY3VHgisS14W3ExzMWZxybE1SJSEsQp9S+oqd12jhcu+A56Ebc1zFSJ" crossorigin="anonymous">

    <title>用户登录 - JT电商系统</title>
</head>
<body>


<!-- 主容器：包含登录表单 -->
<div class="container my-3">
    
        <div class="col-sm-6">
            <!-- 登录标题 -->
            <h2>User Login coke1</h2>

            <!--
                用户登录表单
                - action: userloginvalidate - 表单提交的后端接口
                - method: post - 使用POST方法提交（安全性考虑）
            -->
            <form action="userloginvalidate" method="post">

                <!-- 用户名输入框 -->
                <div class="form-group">
                    <label for="username">Username</label>
                    <input type="text"
                           name="username"
                           id="username"
                           placeholder="Username*"
                           required
                           class="form-control form-control-lg">
                </div>
					
                <!-- 密码输入框 -->
                <div class="form-group">
                    <label for="password">Password</label>
                    <input type="password"
                           class="form-control form-control-lg"
                           placeholder="Password*"
                           required
                           name="password"
                           id="password">
                </div>

                <!-- 注册链接提示 -->
                <span>Don't have an account <a class="linkControl" href="/register">Register here</a></span> <br><br>

                <!-- 登录提交按钮 -->
                <input type="submit" value="Login" class="btn btn-primary btn-block">

                <!-- 错误消息显示区域（使用EL表达式从后端获取） -->
                <br><h3 style="color:red;">${msg}</h3>
                <br>
            </form>
        </div>

</div>

<!-- JavaScript 库引用 -->
<!-- jQuery 3.4.1 - JavaScript库，用于DOM操作 -->
<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>

<!-- Popper.js - Bootstrap的工具提示和弹出框依赖 -->
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>

<!-- Bootstrap 4.4.1 JavaScript - 提供交互组件功能 -->
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
</body>
</html>