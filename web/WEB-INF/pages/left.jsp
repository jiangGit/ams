<%-- 
    Document   : left
    Created on : 2012-4-27, 11:22:41
    Author     : Administrator
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="/WEB-INF/tld/c.tld" %>
<!DOCTYPE html>
<div id="left_bar">
    <ul class="nav">
        <li><a href="${base}" class="current">最新通知 »</a></li>
        <li><a href="${base}/goods/list">物资管理 »</a></li>
        <c:if test="${user.role==1}">
            <li><a href="${base}/user/list">用户管理 »</a></li>
        </c:if>
        <c:if test="${user.role!=3}">       
            <li><a href="${base}/notice/list">公告管理 »</a></li>         
            <li><a href="${base}/organization/list">组织管理 »</a></li>
            <li><a href="${base}/audit/unAudits">审核管理 »</a></li>
        </c:if>
        <c:if test="${user.role==3}">
            <li><a href="${base}/member/list">会员管理 »</a></li>
            <li><a href="${base}/apply/list">申请管理 »</a></li>
        </c:if>



    </ul>
</div>
