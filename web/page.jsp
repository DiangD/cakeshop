<%--
  Created by IntelliJ IDEA.
  User: diangd
  Date: 2019/5/9
  Time: 23:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div style='text-align:center;'>
    <a class='btn btn-info' <c:if test="${page.pageNumber==1}">disabled</c:if>
       <c:if test="${page.pageNumber!=1}">href="${pageContext.request.contextPath}${param.url}?pageNo=1&${param.param}"</c:if> >首页</a>
    <a class='btn btn-info' <c:if test="${page.pageNumber==1}">disabled</c:if>
       <c:if test="${page.pageNumber!=1}">href="${pageContext.request.contextPath}${param.url}?pageNo=${page.pageNumber-1}${param.param}"</c:if>>上一页</a>
    <h3 style='display:inline;'>[${page.pageNumber}/${page.totalPage}]</h3>
    <h3 style='display:inline;'>${page.totalCount}</h3>
    <a class='btn btn-info' <c:if test="${page.totalPage==0||page.pageNumber==page.totalPage}">disabled</c:if>
       <c:if test="${page.pageNumber!=page.totalPage}">href="${pageContext.request.contextPath}${param.url}?pageNo=${page.pageNumber+1}${param.param}"</c:if>>下一页</a>
    <a class='btn btn-info' <c:if test="${page.totalPage==0||page.pageNumber==page.totalPage}">disabled</c:if>
       <c:if test="${page.pageNumber!=page.totalPage}">href="${pageContext.request.contextPath}${param.url}?pageNo=${page.totalPage}${param.param}"</c:if>>尾页</a>
    <input type='text' class='form-control' style='display:inline;width:60px;' value=''/><a class='btn btn-info' href='javascript:void(0);' onclick='location.href="${pageContext.request.contextPath}${param.url}?${param.param}&pageNo="+(this.previousSibling.value)'>GO</a>
</div>
