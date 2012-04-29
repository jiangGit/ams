<%-- 
    Document   : index
    Created on : 2012-4-27, 12:32:10
    Author     : Administrator
--%>

<%@page import="cn.edu.scau.bean.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="/WEB-INF/tld/c.tld" %>
<!DOCTYPE html>
<%
      User user=(User)session.getAttribute("user");
   if(user!=null){
       response.sendRedirect("main");
       System.err.println("not login");
   }
%>

<html>
<head>
	<title>login</title>
	<meta http-equiv="content-type" content="text/html; charset=utf-8" />
	<link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
<div id="login_box">
	<h1>社团管理系统</h1>
        <form action="login" method="post" >
	<label><span>用户名</span><input class="username" type="text" name="name"></label>
	<label><span>密 码</span><input class="passwd" type="password" name="password"></label>
	<input class="submit" type="submit" value="登录">
        </form>
</div>
</body>
</html>
