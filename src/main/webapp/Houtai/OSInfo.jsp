<%@ page language="java" pageEncoding="UTF-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <jsp:include page="includes/basiccss.jsp"/>
    <script type="text/javascript">
        function init() {
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
                    $.each(strTemp, function (i, n) {
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
                    $("#memoryBar").attr("aria-valuenow", data.JvmMemoryInfo.used);
                    $("#memoryBar").attr("aria-valuemax", data.JvmMemoryInfo.max);
                    $("#memoryBar").css("width", (data.JvmMemoryInfo.used / data.JvmMemoryInfo.max) * 100 + "%");
                    $("#printMemroy").text(((data.JvmMemoryInfo.used / data.JvmMemoryInfo.max) * 100).toFixed(2) + "% Complete")
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
    <jsp:include page="includes/nav.jsp"></jsp:include>
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
                            <div class="progress-bar progress-bar-sakura" role="progressbar" aria-valuenow=""
                                 aria-valuemin="0"
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
</div>
<jsp:include page="includes/basicjs.jsp"/>
</body>
</html>
