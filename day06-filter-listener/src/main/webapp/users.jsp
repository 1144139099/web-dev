<%@ page import="java.util.Map" %><%--
  Created by IntelliJ IDEA.
  User: hlh
  Date: 2022/3/4
  Time: 21:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>游戏大厅在线用户</title>
</head>
<body>
<h2>游戏大厅在线用户</h2>
<%
    Map<String, Object> sessionMap = (Map<String, Object>) application.getAttribute("sessionMap");
//每隔5秒刷新页面
    response.setIntHeader( "Refresh", 5);
%>
<ul>
    <%
        for (Map.Entry<String,Object> entry : sessionMap.entrySet()) {
    %>
    <li>
        <%=entry.getValue()%></li>
    <%
        }
        %>
</ul>

</body>
</html>
