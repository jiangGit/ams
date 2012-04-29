<%-- 
    Document   : main
    Created on : 2012-4-27, 11:16:52
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
        <%@include file="header.jsp" %> 
	<div id="container">
		<%@include file="left.jsp" %> 
		<div id="right_bar">
			<h2 class="topic">最新通知</h2>
			<div class="notices">
                             <c:forEach items="${list}" var="bean" varStatus="status">
				<div class="notice">
                                    <div class="info">
					<h3 class="title">${bean.title}</h3>
                                        <span class="date">${bean.time}</span>
                                    </div>
					<div class="content">
						<p>${bean.content}</p>
					</div>
				</div>
                             </c:forEach>
				
			</div>
		</div>
	</div>
</div>
    </body>
</html>
