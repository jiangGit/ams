<%-- 
    Document   : list
    Created on : 2012-4-27, 14:31:52
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
                    <h2 class="topic">组织管理</h2>
                    <div class="manage_box">
                        <div class="function_box">
                            <a href="${base}/organization/toAdd">添加组织</a> &nbsp;&nbsp;<a href="${base}/organization/export" target="_bank" >导出组织数据</a>
                        </div>
                        <table class="dept_manage">
                            <thead>
                                <tr>
                                    <th>名称</th>
                                    <th class="function">操作</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${list}" var="bean" varStatus="status">
                                    <tr>
                                        <td>${bean.name}</td>
                                        <td><a href="${base}/organization/get/${bean.id}">修改</a>&nbsp;&nbsp;
                                            <a href="${base}/organization/delete/${bean.id}">删除</a>&nbsp;&nbsp;
                                            <a href="${base}/organization/records/export/${bean.id}" target="_bank" >导出借用记录</a>
                                            <a href="${base}/member/export/${bean.id}" target="_bank" >导出成员列表</a>
                                        </td>   
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
