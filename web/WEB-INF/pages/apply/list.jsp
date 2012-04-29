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
                            <a href="${base}/apply/toAdd">添加申请</a> &nbsp;&nbsp;
                            <a href="${base}/apply/rejects">驳回申请</a> &nbsp;&nbsp;
                        </div>
                        <table class="dept_manage">
                            <thead>
                                <tr>
                                    <th>物资</th>
                                    <th>开始</th>
                                    <th>结束</th>
                                    <th>状态</th>
                                    <th class="function">操作</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${list}" var="bean" varStatus="status">
                                    <tr>
                                        <td>${bean.goods.name}</td>
                                        <td>${bean.began}</td>
                                        <td>${bean.end}</td>
                                        <td>
                                            <select name="goodsId"  disabled="disabled" >
                                                <option value="1" ${bean.state==1 ? "selected=\"selected\" " :""} >未审核</option>
                                                <option value="2" ${bean.state==2 ? "selected=\"selected\" " :""} >通过</option>
                                                <option value="3" ${bean.state==3 ? "selected=\"selected\" " :""} >不通过</option>
                                            </select>
                                        </td>
                                        <td><a href="${base}/apply/get/${bean.id}">查看</a>&nbsp;&nbsp;    
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
