<%@page import="java.text.SimpleDateFormat" %>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
<jsp:include page="includes/basiccss.jsp" />
</head>

<body>
<div id="wrapper">
    <jsp:include page="includes/nav.jsp"></jsp:include>
</div>
<div id="page-wrapper">
    <div class="panel panel-default">
        <div class="panel-heading">
            句库
        </div>
        <div class="panel-body">
            <div class="table-responsive">
            </div>
        </div>
    </div>
</div>
<jsp:include page="includes/basicjs.jsp" />
</body>
</html>
