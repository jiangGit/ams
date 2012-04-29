<%-- 
    Document   : header
    Created on : 2012-4-27, 11:21:11
    Author     : Administrator
--%>

<%@page import="cn.edu.scau.bean.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="/WEB-INF/tld/c.tld" %>
<!DOCTYPE html>
<%
   User user=(User)session.getAttribute("user");
   if(user==null){
       response.sendRedirect("index.html");
   }
%>

<div id="header">
    <h1 class="logo"><a href="">社团管理系统</a></h1>
    <p class="welcome"><span>${user.name}</span> 欢迎回来 <a href="${base}/logout" >注销</a></p>
</div>
