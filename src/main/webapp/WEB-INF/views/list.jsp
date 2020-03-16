<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>员工列表</title>
<%
	pageContext.setAttribute("APP_PATH", request.getContextPath());
%>
<!-- 引入jQuery依赖 -->
<script src="${APP_PATH }/static/js/jquery-2.1.0.min.js"></script>
<!-- 引入bootstrap样式 -->
<link href="${APP_PATH }/static/dist/css/bootstrap.min.css"
	rel="stylesheet" media="screen">
<script src="${APP_PATH }/static/dist/js/bootstrap.min.js"></script>
</head>
<body>
	<!-- 搭建显示页面 -->
	<div class="container">
		<!-- 标题 -->
		<div class="row">
			<div class="col-md-12">
				<h1>SSM-CRUD</h1>
			</div>
		</div>
		<!-- 按钮 -->
		<div class="row">
			<div class="col-md-4 col-md-offset-8">
				<button class="btn btn-primary">新增</button>
				<button class="btn btn-danger">删除</button>
			</div>
		</div>
		<!-- 显示表格数据 -->
		<div class="row">
			<div class="col-md-12">
				<table class="table table-hover">
					<tr>
						<th>#</th>
						<th>empName</th>
						<th>gender</th>
						<th>email</th>
						<th>deptName</th>
						<th>操作</th>
					</tr>
					<c:forEach items="${pageInfo.list }" var="emp">
					  <tr>
						<th>${emp.empId }</th>
						<th>${emp.empName }</th>
						<th>${emp.gender=="M"?"男":"女" }</th>
						<th>${emp.email }</th>
						<th>${emp.department.deptName }</th>
						<th>
							<button class="btn btn-primary btn-sm">
								<span class="glyphicon glyphicon-pencil"></span> 编辑
							</button>
							<button class="btn btn-danger btn-sm">
								<span class="glyphicon glyphicon-trash"></span>删除
							</button>
						</th>
					</tr>
					
					
					
					</c:forEach>
					
					
				</table>
			</div>
		</div>
		<!-- 显示分页信息 -->
		<div class="row">
			<!-- 分页文字信息 -->
			<div class="col-md-6">
			   当前${pageInfo.pageNum }页,总共${pageInfo.pages } 页,总共${pageInfo.total }条记录
			</div>

			<!-- 分页条 -->
			<div class="col-md-6">
				<ul class="pagination">
				
				    <li><a href="${APP_PATH }/emps?pn=1">首页</a></li>
				    <c:if test="${pageInfo.hasPreviousPage}">
					<li><a href="${APP_PATH }/emps?pn=${page_Num }-1">&laquo;</a></li>
					</c:if>
					<c:forEach items="${pageInfo.navigatepageNums }" var="page_Num">
					<c:if test="${page_Num == pageInfo.pageNum }">
					  <li class="active"><a href="#">${page_Num }</a></li>
					</c:if>
					<c:if test="${page_Num != pageInfo.pageNum }">
					  <li ><a href="${APP_PATH }/emps?pn=${page_Num }">${page_Num }</a></li>
					</c:if>
					</c:forEach>
				
					<c:if test="${pageInfo.hasNextPage}">
					<li><a href="${APP_PATH }/emps?pn=${page_Num }+1">&raquo;</a></li>
					</c:if>
					<li><a href="${APP_PATH }/emps?pn=${pageInfo.pages}">末页</a></li>
				</ul>
			</div>
		</div>
	</div>
</body>
</html>