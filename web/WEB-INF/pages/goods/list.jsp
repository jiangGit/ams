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
                    <h2 class="topic">物资管理</h2>
                    <div class="manage_box">
                        <div class="function_box">
                            <c:if test="${user.role!=3}">    
                            <a href="${base}/goods/toAdd">添加物资</a> &nbsp;&nbsp;<a href="${base}/goods/export" target="_bank" >导出物资数据</a>
                            </c:if>
                        </div>
                        <table class="goods_manage">
                            <thead>
                                <tr>
                                    <th>物资</th>
                                   
                                    <th class="function">操作</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${list}" var="bean" varStatus="status">
                                    <tr>
                                        <td>${bean.name}</td>
                                        
                                        <td>
                                            <c:if test="${user.role!=3}">    
                                                <a href="${base}/goods/get/${bean.id}">修改</a>&nbsp;&nbsp;
                                                <a href="${base}/goods/delete/${bean.id}">删除</a>&nbsp;&nbsp;
                                            </c:if>
                                             <a href="${base}/goods/records/${bean.id}" >使用记录</a>&nbsp;&nbsp;
                                            <a href="${base}/goods/records/export/${bean.id}" target="_bank" >导出使用记录</a></td>                         
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