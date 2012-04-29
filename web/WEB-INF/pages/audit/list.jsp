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
                    <h2 class="topic">申请列表</h2>
                    <div class="manage_box">
                        <div class="function_box">
                            <a href="${base}/audit/unAudits">未读申请</a> &nbsp;&nbsp;
                            <a href="${base}/audit/list/2">通过申请</a> &nbsp;&nbsp;
                            <a href="${base}/audit/list/3">不通过申请</a> &nbsp;&nbsp;
                        </div>
                        <table class="dept_manage">
                            <thead>
                                <tr>
                                    <th>申请人</th>
                                    <th>单位</th>
                                    <th>物资</th>
                                    <th>状态</th>
                                    <th>冲突</th>

                                    <th class="function">操作</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${list}" var="bean" varStatus="status">
                                    <tr>
                                        <td>${bean.user.name}</td>
                                        <td>${bean.user.organization.name}</td>
                                        <td>${bean.goods.name}</td>

                                        <td>
                                            <select   disabled="disabled" >
                                                <option value="1" ${bean.state==1 ? "selected=\"selected\" " :""} >未审核</option>
                                                <option value="2" ${bean.state==2 ? "selected=\"selected\" " :""} >通过</option>
                                                <option value="3" ${bean.state==3 ? "selected=\"selected\" " :""} >不通过</option>
                                            </select>
                                        </td>
                                        <td>
                                            <c:if test="${bean.isConflict}">
                                                <span>冲突</span>
                                            </c:if>
                                            <c:if test="${!bean.isConflict}">
                                                <span>正常</span>
                                            </c:if>
                                        </td>
                                        <td>

                                            <c:if test="${bean.state==1}">
                                                <a href="${base}/audit/toAudit/${bean.taskId}">审核</a>&nbsp;&nbsp;                                                                                                
                                            </c:if>
                                            <c:if test="${bean.state==2}">
                                                <a href="${base}/audit/get/${bean.id}">查看</a>&nbsp;&nbsp;                                              
                                            </c:if>  
                                            <c:if test="${bean.state==3}">
                                                <a href="${base}/apply/get/${bean.id}">查看</a>&nbsp;&nbsp;                                              
                                            </c:if>  
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
