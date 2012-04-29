<%-- 
    Document   : list
    Created on : 2012-4-27, 13:04:19
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
                    <h2 class="topic">用户管理</h2>
                    <div class="manage_box">
                        <div class="function_box">
                            <a href="${base}/user/toAdd">添加用户</a> &nbsp;&nbsp;<a href="${base}/user/export" target="_bank" >导出用户数据</a>
                        </div>
                        <table class="user_manage">
                            <thead>
                                <tr>
                                    <th>姓名</th>
                                    <th>角色</th>
                                    <th>社团</th>
                                    <th class="function">操作</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${list}" var="bean" varStatus="status">
                                    <tr>
                                        <td>${bean.name}</td>
                                        <td>
                                             <select   disabled="disabled" >
                                                <option value="1" ${bean.role==1 ? "selected=\"selected\" " :""} >管理员</option>
                                                <option value="2" ${bean.role==2 ? "selected=\"selected\" " :""} >工作人员</option>
                                                <option value="3" ${bean.role==3 ? "selected=\"selected\" " :""} >社团负责人</option>
                                            </select>
                                          </td>
                                        <td>${bean.organization!=null ?bean.organization.name:"工作人员"}</td>
                                        <td><a href="${base}/user/get/${bean.id}">修改</a>&nbsp;&nbsp;
                                            <a href="${base}/user/delete/${bean.id}">删除</a>&nbsp;&nbsp;
                                            <a href="${base}/user/passowrd/reset/${bean.id}">重置密码</a></td>
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