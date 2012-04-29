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
                    <h2 class="topic">申请内容</h2>
                    <div class="manage_box">
                            <div>
                                <label>物资：</label>
                                <select name="goodsId"  ${bean.state!=1? "disabled=\"disabled\"":""}  >
                                    <c:forEach items="${goodsList}" var="gd" varStatus="status">
                                        <option value="${gd.id}" ${bean.goodsId==gd.id ? "selected=\"selected\" " :""}  >${gd.name}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div>
                                <p>开始时间：${bean.began}</p>
                            </div>
                            <div>
                                <p>结束时间：${bean.end}</p>                            
                            </div>
                            <div>
                                <label>申请理由：</label>
                                <p>${bean.reason}</p>
                            </div>

                    </div>
                </div>
            </div>
        </div>
    </body>
</html>

