<%@ page import="jakarta.servlet.http.HttpSession" %><%--
  Created by IntelliJ IDEA.
  User: hlh
  Date: 2022/3/4
  Time: 9:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录页面</title>
</head>
<body>

<h2>登录页面</h2>
<%
    HttpSession s = request.getSession();
s.setAttribute("user", "user");
%>
<h3>
    <%=request.getAttribute("msg")%>
</h3>
</body>
</html>
