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
                    <h2 class="topic">使用列表</h2>
                    <div class="manage_box">
                        <div class="function_box">
                            
                        </div>
                        <table class="dept_manage">
                            <thead>
                                <tr>
                                    <th>物资</th>
                                    <th>单位</th>
                                    <th>开始</th>
                                    <th>结束</th>                              
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${list}" var="bean" varStatus="status">
                                    <tr>
                                        <td>${bean.goods.name}</td>
                                        <td>${bean.organization.name}</td>
                                        <td>${bean.began}</td>
                                        <td>${bean.end}</td>
                                        
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
