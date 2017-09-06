<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>修改头像</title>
<link
	href="/SSM/css/bootstrap.css"
	rel="stylesheet" />
</head>
<body style = "padding-left: 55px;
			padding-right: 55px;
			padding-top: 55px"> 
	<form action="/SSM/user/modifyTouXiang.do" method="post" role="form" enctype="multipart/form-data">
		<input type="file" name = "touxiang"/> 
		<input type="submit" class="btn btn-info" value="修改"/>
	</form>
</body>
</html>
