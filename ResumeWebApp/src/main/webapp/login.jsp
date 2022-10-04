<%--
  Created by IntelliJ IDEA.
  User: Polad Sarukhanov
  Date: 06.06.2022
  Time: 16:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Admin Login</title>
    <link rel="stylesheet" href="assets/css/users.css">
    <!-- CSS only(Bootstrap) -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor" crossorigin="anonymous">
</head>
<body class="login_background">
<form action="login" method="post">
    <div class="col-4 container login_fix_">
    <center>
        <h1>Login:</h1>
    </center>
    <div class="form-group">
    <label>Email address</label>
    <input type="email" class="form-control" placeholder="email@example.com" name="email">
    </div>
    <div class="form-group">
        <label>Password</label>
        <input type="password" class="form-control" placeholder="Password" name="password">
    </div>
        <button type="submit" class="btn btn-primary" name="login">Login</button>
    </div>
</form>
</body>
</html>
