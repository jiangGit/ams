<%-- 
    Document   : add
    Created on : 2012-4-26, 20:38:16
    Author     : Administrator
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="/WEB-INF/tld/c.tld" %>
<!DOCTYPE html>
<html>
    <head>
        <title>社团管理系统</title>
        <meta http-equiv="content-type" content="text/html; charset=utf-8" />
        <link rel="stylesheet" type="text/css" href="${base}/css/main.css">
    </head>
    <body>
        <div id="wrap">
            <%@include file="../header.jsp" %> 
            <div id="container">
                <%@include file="../left.jsp" %> 
                <div id="right_bar">
                    <h2 class="topic">添加用户</h2>
                    <div class="manage_box">
                        <form action="${base}/user/add" method="post" >
                            <div>
                            <label>用户名：</label>
                            <input type="text" name="name" >
                            </div>
                            <div>
                            <label>权限</label>
                            <select name="role" >
                                <option value="1" >管理员</option>
                                <option value="2" >工作人员</option>
                                <option value="3" >社团负责人</option>
                            </select>
                            </div>
                            <div>
                            <label>邮箱：</label>
                            <input type="text" name="email" >
                            </div>
                            <div>
                                <label>所属机构：</label>
                            <select name="organizationId" >
                                 <option value="0" >无</option>
                                <c:forEach items="${organizationList}" var="organization" varStatus="status">
                                    <option value="${organization.id}" >${organization.name}</option>
                                </c:forEach>
                            </select>
                            </div>
                            <input type="submit" value="提交" >
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>

