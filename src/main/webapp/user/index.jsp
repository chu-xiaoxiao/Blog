<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>墨染琉璃殇-后台管理</title>
<link
	href="/css/bootstrap.css"
	rel="stylesheet" />
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<script type="text/javascript" src="/js/jquery.js"></script>
<script type="text/javascript">
	$(document)
			.ready(
					function() {
						$("#nextPage").click(function() {
											var nextPage = ${page.currentPage};
											if (nextPage >= ${page.allPage}) {
												$("#form1").attr("action","/wenzhang/findByPage.do?currentPage=${page.allPage}");
												$("#currentPage").attr("value","${page.allPage}");
											} else {
												$("#form1")
														.attr(
																"action",
																"/wenzhang/findByPage.do?currentPage=${page.currentPage+1}");
												$("#currentPage").attr("value","${page.currentPage+1}");
											}
											$("#form1").submit();
										});
						$("#lastPage").click(function() {
											var lastPage = ${page.currentPage};
											if (lastPage <= 1) {
												$("#form1")
														.attr("action",
																"/wenzhang/findByPage.do?currentPage=1");
												$("#currentPage").attr("value","1");
											} else {
												$("#form1")
														.attr(
																"action",
																"/wenzhang/findByPage.do?currentPage=${page.currentPage-1}");
												$("#currentPage").attr("value","${page.currentPage-1}");
											}
											$("#form1").submit();
										});
						$("#sieze").change(function() {
									$("#fom1").submit();
								});
						$("#currentPage").change(function() {
									$("#fom1").submit();
								});
					}
				);
</script>
</head>
<body>
	<br />
	<table class="table table-striped" style="width: 75%">
		<caption>文章列表</caption>
		<thead>
			<tr>
				<th>id</th>
				<th>文章标题</th>
				<th>文章类型</th>
				<th>发布日期</th>
				<th>修改/删除</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${page.lists }" var="wenzhang1">
				<tr>
					<td>${wenzhang1.id}</td>
					<td>${wenzhang1.wenzhangbiaoti}</td>
					<td>${wenzhang1.wenzhangleixing}</td>
					<td>${wenzhang1.wenzhangriqi}</td>
					<td><a
						href="/wenzhang/modifywenzhang.do?wenzhangid=${wenzhang1.id}">修改</a>/
						<a href="/wenzhang/delete.do?wenzhangid=${wenzhang1.id}">删除</a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<form action="/wenzhang/findByPage.do" method="post" id="fom1"
		 class="form-inline">
		<div class="form-group">
			<label class="col-sm-2 control-label">文章题目</label>
			<div class="col-sm-10" style="width: 300px">
				<input type="text" name="wenzhangbiaoti" class="form-control" 
					value="${page.wenZhangSearch.name }" />
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">文章类型</label>
			<div class="col-sm-10" style="width: 300px">
				<input type="text" name="wenzhangleixing" class="form-control" 
					value="${page.wenZhangSearch.type }" />
			</div>
			<div class="form-group">
				<input type="submit" value="查找" class="btn btn-info" />
			</div>
		</div>
		<div class="form-group" style="width: 40%">
			<button id="lastPage" class="btn btn-default">上一页</button>
			<select name="currentPage" id="currentPage" class="form-control">
				<c:forEach begin="${1}" end="${page.allPage}" var="i">
					<c:choose>
						<c:when test="${i==page.currentPage}">
							<option value="${i }" selected="selected">${i}
						</c:when>
						<c:otherwise>
							<option value="${i }">${i}
						</c:otherwise>
					</c:choose>
				</c:forEach>
			</select>
			<button id="nextPage" class="btn btn-default">下一页</button>
			一页 <select name="sieze" id="sieze" class="form-control">
				<c:forEach begin="${1 }" end="${15}" var="i">
					<c:choose>
						<c:when test="${i==page.sieze}">
							<option value="${i }" selected="selected">${i}
						</c:when>
						<c:otherwise>
							<option value="${i }">${i}
						</c:otherwise>
					</c:choose>
				</c:forEach>
			</select> 条记录 <br>
		</div>
	</form>
	<a href = "/wenzhang/addWenzhang.jsp">添加文章</a>
	<a href ="/user/modifytouxiang.jsp">修改头像</a>
	<a href="/user/addUser.jsp">添加用户</a>
	<a href ="">主页</a>
</body>
</html>
