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
          href="/dist/css/wangEditor.min.css">
    <!-- <script src="/js/jquery.js"></script> -->
    <script
            src="http://cdn.static.runoob.com/libs/jquery/2.1.1/jquery.min.js"></script>
    <script src="/js/jquery-confirm.js"></script>

    <script type="text/javascript" src="/dist/js/wangEditor.js"></script>
    <script type="text/javascript">
        var countpara = 0;
        $(function () {
            $("#requestPost").click(function () {
                var requestType = $("#requestType").val();
                var params = "url=" + $("#inputUrl").val();
                var flag = true;
                for (var i = 1; i <= countpara; i++) {
                    if (flag) {
                        params += "%3F";
                        flag = false;
                    }
                    params += ($("#paramname" + i).val() + "=" + $("#paramvalue" + i).val()) + "%26";
                }
                if (requestType == "get") {
                    $.ajax({
                        url: "/webutil/simulationGet.do",
                        data: params,
                        dataType: "json",
                        type: "post",
                        success: function (date) {
                            $("#resultResponse").val(JSON.stringify(date.result));
                        }
                    });
                } else {
                    $.ajax({
                        url: "/webutil/simulationPost.do",
                        data: params,
                        dataType: "json",
                        type: "post",
                        success: function (date) {
                            $("#resultResponse").val(JSON.stringify(date.result));
                        }
                    });
                }
                return false;
            })
            $("#addParam").click(function () {
                countpara++;
                $("#paramses").append('<label class="control-label">参数' + countpara + '</label> <div class="controls" > <input type="text" placeholder="输入参数名称" class="form-control" id="paramname' + countpara + '"> <input type="text" placeholder="输入参数值" class="form-control" id="paramvalue' + countpara + '"> </div>');
            });
        });
    </script>
</head>

<body>
<div id="wrapper">
    <jsp:include page="includes/nav.jsp"></jsp:include>
</div>
<div id="page-wrapper">
    <div class="row">
        <div class="col-lg-12">
            <h1 class="page-header">请求模拟</h1>
        </div>
        <!-- /.col-lg-12 -->
    </div>
    <div class="panel panel-default">
        <div class="panel-heading">
            请求模拟
        </div>
        <div class="panel-body">
            <form class="form-horizontal" role="form">
                <div class="col-sm-8">
                    <div class="form-group">
                        <!-- Text input-->
                        <label class="col-sm-1 control-label">请求地址url</label>
                        <div class="col-sm-10">
                            <input type="text" placeholder="请输入请求的url" class="form-control" id="inputUrl">
                        </div>
                    </div>
                    <div class="form-group">
                        <!-- Text input-->
                        <label class="col-sm-1 control-label">请求方式</label>
                        <div class="col-sm-10">
                            <select class="form-control" id="requestType">
                                <option value="get">GET</option>
                                <option value="post">POST</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-1 control-label"></label>
                        <!-- Button -->
                        <div class="controls">
                            <input type="button" class="btn btn-success" id="requestPost" value="提交"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <!-- Textarea -->
                        <label class="col-sm-1 control-label">url响应结果</label>
                        <div class="controls">
                            <div class="col-sm-5">
                                <textarea type="" class="form-control" id="resultResponse"
                                          readonly="readonly"> </textarea>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-sm-4" id="params">
                    <form class="form-horizontal" role="form">
                        <div>参数列表</div>
                        <hr/>
                        <div class="controls" id="paramses"></div>
                        <input type="button" class="btn btn-success" id="addParam" value="添加参数"/>
                    </form>
                </div>
            </form>
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
