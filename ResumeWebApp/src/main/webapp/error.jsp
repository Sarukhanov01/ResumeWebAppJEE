<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Why are you here?</title>
</head>
<body>
<%
    String msg = request.getParameter("msg");
%>
<center
<%=msg%>
    <center/>
</body>
</html>
