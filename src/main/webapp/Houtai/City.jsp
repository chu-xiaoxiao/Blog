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
    <script type="text/javascript">
        getNextContinent(-1);
        $(function () {
            $("#continent").change(function () {
                getNextCountry($("#continent").val());
            })
            $("#country").change(function () {
                getNextProvinces($("#country").val());
            })
            $("#provinces").change(function () {
                getNextCity($("#provinces").val());
            });
            $("#city").change(function () {
                getNextArea($("#city").val())
            });
        });
        function getNextContinent(cityId) {
            $.ajax({
                method: "get",
                dataType: "json",
                data: {"cityId": cityId},
                url: "/webutil/getNextLevelCity.do",
                success: function (data) {
                    var first = 0;
                    $("#continent").html("");
                    $(data.citys).each(function (i, j) {
                        $("#continent").append("<option value='" + j.id + "'>" + j.cityname + "</option>");
                        if (first == 0) {
                            first = j.id;
                        }
                    });
                    getNextCountry(first);
                }
            });
        }
        function getNextCountry(cityId) {
            $.ajax({
                method: "get",
                dataType: "json",
                data: {"cityId": cityId},
                url: "/webutil/getNextLevelCity.do",
                success: function (data) {
                    var first = 0;
                    $("#country").html("");
                    $(data.citys).each(function (i, j) {
                        $("#country").append("<option value='" + j.id + "'>" + j.cityname + "</option>");
                        if (first == 0) {
                            first = j.id;
                        }
                    });
                    getNextProvinces(first);
                }
            });
        }
        function getNextProvinces(cityId) {
            $.ajax({
                method: "get",
                dataType: "json",
                data: {"cityId": cityId},
                url: "/webutil/getNextLevelCity.do",
                success: function (data) {
                    var first = 0;
                    $("#provinces").html("");
                    $(data.citys).each(function (i, j) {
                        $("#provinces").append("<option value='" + j.id + "'>" + j.cityname + "</option>");
                        if (first == 0) {
                            first = j.id;
                        }
                    });
                    if (first == 0) {
                        $("#provinces").append("<option value='当前精度不足'>当前精度不足</option>");
                        $("#city").empty();
                        $("#city").append("<option value='当前精度不足'>当前精度不足</option>");
                        $("#area").empty();
                        $("#area").append("<option value='当前精度不足'>当前精度不足</option>");
                        return;
                    }
                    getNextCity(first);
                }
            });
        }
        function getNextCity(cityId) {
            $.ajax({
                method: "get",
                dataType: "json",
                data: {"cityId": cityId},
                url: "/webutil/getNextLevelCity.do",
                success: function (data) {
                    var first = 0;
                    $("#city").empty();
                    $(data.citys).each(function (i, j) {
                        $("#city").append("<option value='" + j.id + "'>" + j.cityname + "</option>");
                        if (first == 0) {
                            first = j.id;
                        }
                    });
                    if (first == 0) {
                        $("#city").append("<option value='当前精度不足'>当前精度不足</option>");
                        $("#area").empty();
                        $("#area").append("<option value='当前精度不足'>当前精度不足</option>");
                        return;
                    }
                    getNextArea(first);
                }
            });
        }
        function getNextArea(cityId) {
            $.ajax({
                method: "get",
                dataType: "json",
                data: {"cityId": cityId},
                url: "/webutil/getNextLevelCity.do",
                success: function (data) {
                    $("#area").empty();
                    $(data.citys).each(function (i, j) {
                        $("#area").append("<option value='" + j.id + "'>" + j.cityname + "</option>");
                    });
                }
            });
        }
    </script>
</head>

<body>
<div id="wrapper">
    <jsp:include page="nav.jsp"></jsp:include>
</div>
<div id="page-wrapper">
    <div class="panel panel-default">
        <div class="panel-heading">
            省市区三级联动
        </div>
        <div class="panel-body">
            <div class="table-responsive">
                <form action="###" method="post" class="form-horizontal" role="form" id="cityLiandong">
                    <select id="continent" class="form-control"></select>洲
                    <select id="country" class="form-control"></select>国
                    <select id="provinces" class="form-control"></select>省
                    <select id="city" class="form-control"></select>市
                    <select id="area" class="form-control"></select>区
                </form>
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
