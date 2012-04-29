<%-- 
    Document   : add
    Created on : 2012-4-26, 21:04:20
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
        <script type="text/javascript" src="${base}/js/calendar.js" ></script>  
        <script type="text/javascript" src="${base}/js/calendar-zh.js" ></script>
        <script type="text/javascript" src="${base}/js/calendar-setup.js"></script>
        <link href="${base}/css/calendar.css" rel="stylesheet" type="text/css" />
    </head>
    <body>
        <div id="wrap">
            <%@include file="../header.jsp" %> 
            <div id="container">
                <%@include file="../left.jsp" %> 
                <div id="right_bar">
                    <h2 class="topic">添加申请</h2>
                    <div class="manage_box">
                        <form action="${base}/apply/apply" method="post" >
                            <input type="hidden" value="${task}" name="taskId" >
                            <input type="hidden" value="${user.id}" name="userId" >
                            <div>
                                <label>物资：</label>
                                <select name="goodsId" >
                                    <c:forEach items="${goodsList}" var="bean" varStatus="status">
                                        <option value="${bean.id}" >${bean.name}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div>
                                <label>开始时间：</label>
                                <input type="text"  name="began" id="began" onclick="return showCalendar('began', 'y-mm-dd');" >
                            </div>
                            <div>
                                <label>结束时间：</label>
                                <input type="text"  name="end" id="end" onclick="return showCalendar('end', 'y-mm-dd');" >
                            </div>
                            <div>
                                <label>申请理由：</label>
                                <textarea name="reason" cols="25" rows="4" ></textarea>
                            </div>
                            <div>
                                <input type="submit" value="提交" >
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>

