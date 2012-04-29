<%-- 
    Document   : list
    Created on : 2012-4-27, 15:29:41
    Author     : Administrator
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="/WEB-INF/tld/c.tld" %>
<!DOCTYPE html>
<html>
    <head>
        <title>main</title>
        <meta http-equiv="content-type" content="text/html; charset=utf-8" />
        <link rel="stylesheet" type="text/css" href="${base}/css/main.css">
    </head>
    <body>
        <div id="wrap">
            <%@include file="../header.jsp" %> 
            <div id="container">
                <%@include file="../left.jsp" %> 
                <div id="right_bar">			
                    <h2 class="topic">会员管理</h2>
                    <div class="manage_box">
                        <div class="function_box">
                            <a href="${base}/member/toAdd">添加会员</a> &nbsp;&nbsp;<a href="${base}/member/export" target="_bank" >导出会员数据</a>
                        </div>
                        <table class="member_manage">
                            <thead>
                                <tr>
                                    <th>姓名</th>
                                    <th>学号</th>
                                    <th>学院</th>
                                    <th>专业</th>
                                    <th>年纪</th>
                                    <th class="function">操作</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${list}" var="bean" varStatus="status">
                                    <tr>
                                        <td>${bean.name}</td>
                                        <td>${bean.studentId}</td>
                                        <td>${bean.academy}</td>
                                        <td>${bean.major}</td>
                                        <td>${bean.grade}</td>
                                        <td>
                                            <a href="${base}/member/get/${bean.id}">修改</a>&nbsp;&nbsp;
                                            <a href="${base}/member/delete/${bean.id}">删除</a>&nbsp;&nbsp;</td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>