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
    <script type="text/javascript" src="/js/dateFormat.js"></script>
    <script type="text/javascript">
        function init(){
            $.ajax({
                method: "get",
                url: "/Houtai/getOS.do",
                dataType: "json",
                success: function (data) {
                    var strTemp;
                    strTemp = data.JVMInfo.jvm_bootClassPath.split(";");
                    $("#JVMmessage").html("");
                    $("#OSmessage").html("");
                    $("#JVMmessage").append("<div>JVM名称:" + data.JVMInfo.jvm_name + "</div>");
                    $("#JVMmessage").append("<div>JVM版本:" + data.JVMInfo.jvm_version + "</div>");
                    $("#JVMmessage").append("<div>JVM启动时间:" + TimeObjectUtil.longMsTimeConvertToDateTime(data.JVMInfo.jvm_start_time) + "</div>");
                    $("#JVMmessage").append("<div>JVMClassPath:</div>");
                    $.each(strTemp,function(i,n){
                        $("#JVMmessage").append("<div>--" + n + "</div>");
                    });
                    $("#OSmessage").append("<div>操作系统架构:" + data.OSInfo.Architecture + "</div>");
                    $("#OSmessage").append("<div>核心数:" + data.OSInfo.Processors + "</div>");
                    $("#OSmessage").append("<div>操作系统名称:" + data.OSInfo.System_name + "</div>");
                    $("#OSmessage").append("<div>操作系统版本:" + data.OSInfo.System_version + "</div>");
                }
            });
        }
        function getData() {
            $.ajax({
                method: "get",
                url: "/Houtai/getOS.do",
                dataType: "json",
                success: function (data) {
                    $("#memoryMessage").html("");
                    $("#memoryMessage").append("<div>JVM内存堆栈:" + data.JvmMemoryInfo.Heap_committed / 1024 / 1024 + "M</div>");
                    $("#memoryMessage").append("<div>JVM内存初值:" + data.JvmMemoryInfo.init / 1024 / 1024 + "M</div>");
                    $("#memoryMessage").append("<div>JVM内存最大值:" + data.JvmMemoryInfo.max / 1024 / 1024 + "M</div>");
                    $("#memoryMessage").append("<div>JVM内存当前使用量:" + (data.JvmMemoryInfo.used / 1024 / 1024).toFixed(2) + "M</div>");
                    $("#memoryBar").attr("aria-valuenow",data.JvmMemoryInfo.used);
                    $("#memoryBar").attr("aria-valuemax",data.JvmMemoryInfo.max);
                    $("#memoryBar").css("width",(data.JvmMemoryInfo.used/data.JvmMemoryInfo.max)*100+"%");
                    $("#printMemroy").text(((data.JvmMemoryInfo.used/data.JvmMemoryInfo.max)*100).toFixed(2)+"% Complete")
                }
            });
        }
        $(function () {
            //页面初始加载
            getData();
            init();
            t = setInterval("getData()", 5000);
        })

    </script>
</head>

<body>
<div id="wrapper">
    <jsp:include page="nav.jsp"></jsp:include>
</div>
<div id="page-wrapper">
    <div class="row">
        <div class="col-lg-12">
            <h1 class="page-header">当前服务器运行信息</h1>
        </div>
        <!-- /.col-lg-12 -->
    </div>
    <div class="row">
        <div class="col-md-5">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <i class="fa fa-clock-o fa-fw"></i> 系统参数
                </div>
                <div class="panel-body">
                    <div id="OSmessage"></div>
                </div>
            </div>
        </div>
        <div class="col-md-6">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <i class="fa fa-clock-o fa-fw"></i> 内存使用
                </div>
                <div class="panel-body">
                    <div id="memoryMessage"></div>
                    <div>
                        <strong>内存使用量</strong>
                        <span class="pull-right text-muted" id="printMemroy">40% Complete</span>
                        <div class="progress progress-striped active">
                            <div class="progress-bar progress-bar-sakura" role="progressbar" aria-valuenow="" aria-valuemin="0"
                                 aria-valuemax="100" style="width: 0%" id="memoryBar">
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-md-5">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <i class="fa fa-clock-o fa-fw"></i> JVM参数
                </div>
                <div class="panel-body">
                    <div id="JVMmessage"></div>
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
