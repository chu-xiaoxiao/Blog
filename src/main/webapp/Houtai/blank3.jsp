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
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>${sessionScope.user.usernickname}-后台管理页面</title>
    <!-- Bootstrap Core CSS -->
    <link href="/assets/vendor/bootstrap/css/bootstrap.min.css"
          rel="stylesheet">
    <!-- MetisMenu CSS -->
    <link href="/assets/vendor/metisMenu/metisMenu.min.css"
          rel="stylesheet">

    <!-- DataTables CSS -->
    <link
            href="/assets/vendor/datatables-plugins/dataTables.bootstrap.css"
            rel="stylesheet">

    <!-- DataTables Responsive CSS -->
    <link
            href="/assets/vendor/datatables-responsive/dataTables.responsive.css"
            rel="stylesheet">

    <!-- Custom CSS -->
    <link href="/assets/dist/css/sb-admin-2.css" rel="stylesheet">

    <!-- Custom Fonts -->
    <link href="/assets/vendor/font-awesome/css/font-awesome.min.css"
          rel="stylesheet" type="text/css">

    <link rel="stylesheet" href="/css/jquery-confirm.css">
    <link rel="stylesheet" href="/css/fenye.css">

    <link rel="stylesheet" type="text/css"
          href="//dist/css/wangEditor.min.css">
    <!-- <script src="/js/jquery.js"></script> -->
    <script
            src="http://cdn.static.runoob.com/libs/jquery/2.1.1/jquery.min.js"></script>
    <script src="/js/jquery-confirm.js"></script>

    <script type="text/javascript" src="/dist/js/wangEditor.js"></script>
</head>

<body>
<div id="wrapper">
    <jsp:include page="nav.jsp"></jsp:include>
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
<script src="/assets/vendor/bootstrap/js/bootstrap.min.js"></script>

<!-- Metis Menu Plugin JavaScript -->
<script src="/assets/vendor/metisMenu/metisMenu.min.js"></script>

<!-- DataTables JavaScript -->
<script src="/assets/vendor/datatables/js/jquery.dataTables.min.js"></script>
<script
        src="/assets/vendor/datatables-plugins/dataTables.bootstrap.min.js"></script>
<script
        src="/assets/vendor/datatables-responsive/dataTables.responsive.js"></script>

<!-- Custom Theme JavaScript -->
<script src="/assets/dist/js/sb-admin-2.js"></script>
<script type="text/javascript" src="/js/bootstrap-paginator.min.js"></script>
<script src="/js/jqueryForm.js"></script>
</body>
</html>
