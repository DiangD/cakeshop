<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>商品列表</title>
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
    <div class="text-right"><a class="btn btn-warning" href="${pageContext.request.contextPath}/admin/goods_add.jsp">添加商品</a></div>

    <br>

    <ul role="tablist" class="nav nav-tabs">
        <li <c:if test="${type==0}">class="active"</c:if> role="presentation"><a href="${pageContext.request.contextPath}/admin/goods_list">全部商品</a></li>
        <li <c:if test="${type==1}">class="active"</c:if>  role="presentation"><a href="${pageContext.request.contextPath}/admin/goods_list?type=1">条幅推荐</a></li>
        <li <c:if test="${type==2}">class="active"</c:if>  role="presentation"><a href="${pageContext.request.contextPath}/admin/goods_list?type=2">热销推荐</a></li>
        <li <c:if test="${type==3}">class="active"</c:if>  role="presentation"><a href="${pageContext.request.contextPath}/admin/goods_list?type=3">新品推荐</a></li>
    </ul>





    <br>

    <table class="table table-bordered table-hover">

        <tr>
            <th width="5%">ID</th>
            <th width="10%">图片</th>
            <th width="10%">名称</th>
            <th width="20%">介绍</th>
            <th width="10%">价格</th>
            <th width="10%">类目</th>
            <th width="25%">操作</th>
        </tr>
        <c:forEach items="${page.list}" var="goods">
            <tr>
                <td><p>${goods.id}</p></td>
                <td><p><a href="${pageContext.request.contextPath}/goods_detail?id=${goods.id}" target="_blank"><img src="${pageContext.request.contextPath}${goods.cover}" width="100px" height="100px"></a></p></td>
                <td><p><a href="${pageContext.request.contextPath}/goods_detail?id=${goods.id}" target="_blank">${goods.name}</a></p></td>
                <td><p>${goods.intro}</p></td>
                <td><p>${goods.price}</p></td>
                <td><p>${goods.type.name}</p></td>
                <td>
                    <p>
                        <c:choose>
                            <c:when test="${goods.isScroll}">
                                <a class="btn btn-info" href="${pageContext.request.contextPath}/admin/goods_recommend?id=${goods.id}&method=remove&typeTarget=1&pageNo=${page.pageNumber}&type=${type}">移出条幅</a>
                            </c:when>
                            <c:otherwise>
                                <a class="btn btn-primary" href="${pageContext.request.contextPath}/admin/goods_recommend?id=${goods.id}&method=add&typeTarget=1&pageNo=${page.pageNumber}&type=${type}">加入条幅</a>
                            </c:otherwise>
                        </c:choose>
                        <c:choose>
                            <c:when test="${goods.isHot}">
                                <a class="btn btn-primary" href="${pageContext.request.contextPath}/admin/goods_recommend?id=${goods.id}&method=remove&typeTarget=2&pageNo=${page.pageNumber}&type=${type}">移出热销</a>
                            </c:when>
                            <c:otherwise>
                                <a class="btn btn-primary" href="${pageContext.request.contextPath}/admin/goods_recommend?id=${goods.id}&method=add&typeTarget=2&pageNo=${page.pageNumber}&type=${type}">加入热销</a>
                            </c:otherwise>
                        </c:choose>
                        <c:choose>
                            <c:when test="${goods.isNew}">
                                <a class="btn btn-primary" href="${pageContext.request.contextPath}/admin/goods_recommend?id=${goods.id}&method=remove&typeTarget=3&pageNo=${page.pageNumber}&type=${type}">移出新品</a>
                            </c:when>
                            <c:otherwise>
                                <a class="btn btn-primary" href="${pageContext.request.contextPath}/admin/goods_recommend?id=${goods.id}&method=add&typeTarget=3&pageNo=${page.pageNumber}&type=${type}">加入新品</a>
                            </c:otherwise>
                        </c:choose>
                    </p>
                    <a class="btn btn-success" href="${pageContext.request.contextPath}/admin/goods_editShow?id=${goods.id}&pageNo=${page.pageNumber}&type=${type}">修改</a>
                    <a class="btn btn-danger" href="${pageContext.request.contextPath}/admin/goods_delete?id=${goods.id}&pageNo=${page.pageNumber}&type=${type}">删除</a>
                </td>
            </tr>
        </c:forEach>
    </table>

    <jsp:include page="/page.jsp">
        <jsp:param name="url" value="/admin/goods_list"/>
        <jsp:param name="param" value="&type=${type}"/>
    </jsp:include>
    <br>
</div>
</body>
</html>