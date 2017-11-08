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

    <script type="text/javascript"
            src="http://webapi.amap.com/maps?v=1.4.0&key=06f4dc2a23f3e7e8a778f573d682e4f6"></script>
    <title>${sessionScope.user.usernickname}-后台管理页面</title>
    <script src="//webapi.amap.com/ui/1.0/main.js"></script>

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
    <!-- <script src="/js/jquery.js"></script> -->
    <script
            src="http://cdn.static.runoob.com/libs/jquery/2.1.1/jquery.min.js"></script>
    <script src="/js/jquery-confirm.js"></script>
    <script type="text/javascript">
        $(function () {
                var map = new AMap.Map('container', {
                    resizeEnable: true,
                    zoom: 11,
                    center: [116.397428, 39.90923]
                });
                 AMap.plugin(['AMap.ToolBar','AMap.Scale','AMap.OverView'],
                function(){
                    map.addControl(new AMap.ToolBar());

                    map.addControl(new AMap.Scale());

                    map.addControl(new AMap.OverView({isOpen:true}));

                });
            AMapUI.loadUI(['control/BasicControl'], function(BasicControl) {
                //图层切换控件
                map.addControl(new BasicControl.LayerSwitcher({
                    position: 'rt'
                }));
                });
                function getCenter(data) {
                    var result = data.split("_");
                    var result1 = result[0].split(",");
                    var result2 = result[1].split(",");
                    var x1 = parseFloat(result1[0]);
                    var y1 = parseFloat(result1[1]);
                    var x2 = parseFloat(result2[0]);
                    var y2 = parseFloat(result2[1]);
                    return [(x1 + x2) / 2, (y1 + y2) / 2]
                }

                function createMark(data, mapObj, hide) {
                    AMapUI.loadUI(['overlay/SvgMarker'], function(SvgMarker) {
                        if (!SvgMarker.supportSvg) {
                            //当前环境并不支持SVG，此时SvgMarker会回退到父类，即SimpleMarker
                            alert('当前环境不支持SVG');
                        }

                        var shape = new SvgMarker.Shape.SquarePin({
                            height:20, //高度
                            width:String(data.count).length*5+20, //不指定,维持默认的宽高比
                            fillColor: "rgb(0,153,255)" //填充色
                            //strokeWidth: 1, //描边宽度
                            //strokeColor: '#666' //描边颜色
                        });

                        var marker = new SvgMarker(shape,{
                            iconLabel: data.count,
                            position: getCenter(data.group),
                            zIndex: data.count
                        });
                        marker.subMarkers = [];
                        if (!hide) {
                            marker.setMap(mapObj)
                        }
                    });
                }

                $.ajax({
                    url: "/Houtai/map/markIp.do",
                    type: "get",
                    dataType: "json",
                    success: function (data) {
                        $(data.CountIpByCountry).each(function (i, j) {
                            createMark(j, map, false);
                        });
                    }
                });
        });
    </script>
</head>

<body>
<div id="wrapper">
    <jsp:include page="nav.jsp"></jsp:include>
</div>
<div id="page-wrapper">
    <div class="panel panel-default">
        <div class="panel-heading">
            ip访问分布
        </div>
        <div class="panel-body">
            <div id="container" style="width:1200px; height:800px"></div>
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
