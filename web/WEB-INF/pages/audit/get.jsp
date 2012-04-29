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
                    <h2 class="topic">审核申请</h2>
                    <div class="manage_box">
                        <div>
                            <label>物资：</label>
                            <span>${bean.goods.name}</span>
                        </div>
                        <div>
                            <label>开始时间：</label>
                            <span>${bean.began}</span>
                        </div>
                        <div>
                            <label>结束时间：</label>
                            <span>${bean.end}</span>
                        </div>
                        <div>
                            <label>申请理由：</label>
                            <span>${bean.apply.reason}</span>
                        </div>          
                            <div>
                                <label>审核：</label>
                                <select name="state" disabled="disabled" >
                                    <option value="2" ${bean.apply.state==2 ? "selected=\"selected\" " :""}>通过</option>
                                    <option value="3" ${bean.apply.state==3 ? "selected=\"selected\" " :""} >不通过</option>
                                </select>
                            </div>
                            <div>
                                <label style="display: block" >审核意见：</label>
                                <p>${bean.content}</p>
                            </div>

                    </div>
                </div>
            </div>
        </div>
    </body>
</html>

