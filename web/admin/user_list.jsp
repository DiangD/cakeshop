<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<title>客户列表</title>
<meta charset="utf-8"/>
<link rel="stylesheet" href="css/bootstrap.css"/> 
</head>
<body>
<div class="container-fluid">

	<nav class="navbar navbar-default" role="navigation">
		<jsp:include page="/admin/header.jsp"></jsp:include>
	</nav>
	<c:if test="${!empty successMsg}">
		<div class="alert alert-success">${successMsg}</div>
	</c:if>
	<c:if test="${!empty failMsg}">
		<div class="alert alert-danger">${failMsg}</div>
	</c:if>
	<div class="text-right"><a class="btn btn-warning" href="${pageContext.request.contextPath}/admin/user_add.jsp">添加客户</a></div>
		
	<br>
	<table class="table table-bordered table-hover">
		<c:forEach items="${page.list}" var="user">
			<tr>
				<th width="5%">ID</th>
				<th width="10%">用户名</th>
				<th width="10%">邮箱</th>
				<th width="10%">收件人</th>
				<th width="10%">电话</th>
				<th width="10%">地址</th>
				<th width="10%">操作</th>
			</tr>


			<tr>
				<td><p>${user.id}</p></td>
				<td><p>${user.username}</p></td>
				<td><p>${user.email}</p></td>
				<td><p>${user.name}</p></td>
				<td><p>${user.phone}</p></td>
				<td><p>${user.address}</p></td>
				<td>
					<a class="btn btn-info" href="${pageContext.request.contextPath}/admin/user_reset.jsp?id=${user.id}&username=${user.username}&email=${user.email}">重置密码</a>
					<a class="btn btn-primary" href="${pageContext.request.contextPath}/admin/user_editShow?id=${user.id}">修改</a>
					<a class="btn btn-danger" href="${pageContext.request.contextPath}/admin/user_delete?id=${user.id}">删除</a>
				</td>
			</tr>
		</c:forEach>
	</table>


<br>
<jsp:include page="/page.jsp">
	<jsp:param name="url" value="/admin/user_list"/>
</jsp:include>
<br>
</div>
</body>
</html>