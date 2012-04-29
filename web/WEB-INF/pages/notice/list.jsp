<%-- 
    Document   : list
    Created on : 2012-4-27, 14:02:19
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
                    <h2 class="topic">公告管理</h2>
                    <div class="manage_box">
                        <div class="function_box">
                            <a href="${base}/notice/toAdd">添加公告</a> &nbsp;&nbsp;
                        </div>
                        <table class="goods_manage">
                            <thead>
                                <tr>
                                    <th>标题</th>
                                    <th>时间</th>
                                    <th class="function">操作</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${list}" var="bean" varStatus="status">
                                    <tr>
                                        <td>${bean.title}</td>
                                        <td>${bean.time}</td>
                                        <td>
                                            <a href="${base}/notice/get/${bean.id}">修改</a>&nbsp;&nbsp;
                                            <a href="${base}/notice/delete/${bean.id}">删除</a>&nbsp;&nbsp;</td>                         
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