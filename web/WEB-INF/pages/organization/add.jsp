<%-- 
    Document   : newjsp
    Created on : 2012-4-26, 21:05:35
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
                    <h2 class="topic">添加组织</h2>
                    <div class="manage_box">
                        <form action="${base}/organization/add" method="post" >
                            <div>
                            <label>组织名：</label>
                            <input type="text" name="name" >
                            </div>
                            <div>
                            <label>组织简介</label>
                            <textarea name="content" cols="25" rows="4" ></textarea>
                            </div>
                            <input type="submit" value="提交" >
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>




