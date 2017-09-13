<%@ page import="java.text.SimpleDateFormat"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()

		+ path + "/";
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

	<title>SB Admin 2 - Bootstrap Admin Theme</title>

	<!-- Bootstrap Core CSS -->
	<link href="/SSM/assets/vendor/bootstrap/css/bootstrap.min.css"
		  rel="stylesheet">

	<!-- MetisMenu CSS -->
<link href="/SSM/assets/vendor/metisMenu/metisMenu.min.css"
	rel="stylesheet">

<!-- Custom CSS -->
<link href="/SSM/assets/dist/css/sb-admin-2.css" rel="stylesheet">

<!-- Morris Charts CSS -->
<link href="/SSM/assets/vendor/morrisjs/morris.css" rel="stylesheet">

<!-- Custom Fonts -->
<link href="/SSM/assets/vendor/font-awesome/css/font-awesome.min.css"
	rel="stylesheet" type="text/css">

<!-- jQuery -->
<script
	src="http://cdn.static.runoob.com/libs/jquery/2.1.1/jquery.min.js"></script>

<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
<!-- 机器人消息处理 -->
<script type="text/javascript" src="/SSM/js/ibot.js" charset="UTF-8" > </script>
<!-- 图表引入 -->
<script src="/SSM/js/chart/Chart.js"></script>
<!-- 日期计算 -->
<script src="/SSM/js/datejisuan.js"></script>

<script type="text/javascript">
	$(function(){
		var lables = new Array();
		var datas  = new Array();
		$.each(${requestScope.listip_date.list}, function(index, content)
				  { 
				   lables.push(timeStamp2String(content.date.time));
				   datas.push(content.count);
				  });
		var defaults = {    
			    scaleStartValue :null,     // Y 轴的起始值
			    scaleLineColor : "rgba(0,0,0,.1)",    // Y/X轴的颜色
			    scaleLineWidth : 1,        // X,Y轴的宽度
			    scaleShowLabels : true,    // 刻度是否显示标签, 即Y轴上是否显示文字   
			    scaleLabel : "count", // Y轴上的刻度,即文字  
			    scaleFontFamily : "'Arial'",  // 字体  
			    scaleFontSize : 20,        // 文字大小 
			    scaleFontStyle : "normal",  // 文字样式  
			    scaleFontColor : "#666",    // 文字颜色  
			    scaleShowGridLines : true,   // 是否显示网格  
			    scaleGridLineColor : "rgba(0,0,0,.05)",   // 网格颜色
			    scaleGridLineWidth : 2,      // 网格宽度  
			    bezierCurve : false,         // 是否使用贝塞尔曲线? 即:线条是否弯曲     
			    pointDot : true,             // 是否显示点数  
			    pointDotRadius : 8,          // 圆点的大小  
			    pointDotStrokeWidth : 1,     // 圆点的笔触宽度, 即:圆点外层边框大小 
			    datasetStroke : true,        // 数据集行程
			    datasetStrokeWidth : 2,      // 线条的宽度, 即:数据集
			    datasetFill : false,         // 是否填充数据集 
			    animation : true,            // 是否执行动画  
			    animationSteps : 60,          // 动画的时间   
			    animationEasing : "easeOutQuart",    // 动画的特效   
			    onAnimationComplete : null    // 动画完成时的执行函数   
			    }
        var ctx = document.getElementById("myChart").getContext("2d");
		new Chart(ctx, {
		    type:'line',
		    data: {
				labels : lables,
				datasets : [
					{
						label:"当日访问量",						
						fillColor : "rgba(220,220,220,0.5)",
						strokeColor : "rgba(220,220,220,1)",
						pointColor : "rgba(220,220,220,1)",
						pointStrokeColor : "#fff",
						backgroundColor:"rgba(240,255,255,0.25)",
						data : datas
					}
				]
			}
		});
	});
</script>
</head>

<body>
	<div id="wrapper"> 
		<!-- Navigation -->
		<jsp:include page="/Houtai/nav.jsp"></jsp:include>
		<div id="page-wrapper">
			<div class="row">
				<div class="col-lg-12">
					<h1 class="page-header">Dashboard</h1>
				</div>
				<!-- /.col-lg-12 -->
			</div>
			<!-- /.row -->
			<div class="row">
				<div class="col-lg-3 col-md-6">
					<div class="panel panel-primary">
						<div class="panel-heading">
							<div class="row">
								<div class="col-xs-3">
									<i class="fa fa-comments fa-5x"></i>
								</div>
								<div class="col-xs-9 text-right">
									<div class="huge">${requestScope.countIp}</div>
									<div>总访问量</div>
								</div>
							</div>
						</div>
						<a href="/SSM/Houtai/iplist.do">
							<div class="panel-footer">
								<span class="pull-left">View Details</span> <span
									class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
								<div class="clearfix"></div>
							</div>
						</a>
					</div>
				</div>
				<div class="col-lg-3 col-md-6">
					<div class="panel panel-green">
						<div class="panel-heading">
							<div class="row">
								<div class="col-xs-3">
									<i class="fa fa-tasks fa-5x"></i>
								</div>
								<div class="col-xs-9 text-right">
									<div class="huge">${count}</div>
									<div>当前文章总数</div>
								</div>
							</div>
						</div>
						<a href="/SSM/Houtai/findByPage.do">
							<div class="panel-footer">
								<span class="pull-left">View Details</span> <span
									class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
								<div class="clearfix"></div>
							</div>
						</a>
					</div>
				</div>
				<div class="col-lg-3 col-md-6">
					<div class="panel panel-yellow">
						<div class="panel-heading">
							<div class="row">
								<div class="col-xs-3">
									<i class="fa fa-shopping-cart fa-5x"></i>
								</div>
								<div class="col-xs-9 text-right">
									<div class="huge">${requestScope.juziCount }</div>
									<div>句库总量</div>
								</div>
							</div>
						</div>
						<a href="/SSM/Houtai/listjuzi.do">
							<div class="panel-footer">
								<span class="pull-left">View Details</span> <span
									class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
								<div class="clearfix"></div>
							</div>
						</a>
					</div>
				</div>
				<div class="col-lg-3 col-md-6">
					<div class="panel panel-red">
						<div class="panel-heading">
							<div class="row">
								<div class="col-xs-3">
									<i class="fa fa-support fa-5x"></i>
								</div>
								<div class="col-xs-9 text-right">
									<div class="huge">13</div>
									<div>Support Tickets!</div>
								</div>
							</div>
						</div>
						<a href="#">
							<div class="panel-footer">
								<span class="pull-left">View Details</span> <span
									class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
								<div class="clearfix"></div>
							</div>
						</a>
					</div>
				</div>
			</div>
			<!-- /.row -->
			<div class="row">
				<div class="col-lg-8">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <i class="fa fa-bar-chart-o fa-fw"></i>访问量
                            <div class="pull-right">
                                <div class="btn-group">
                                    <button type="button"
                                            class="btn btn-default btn-xs dropdown-toggle"
                                            data-toggle="dropdown">
                                        Actions <span class="caret"></span>
                                    </button>
                                    <ul class="dropdown-menu pull-right" role="menu">
                                        <li><a href="#">Action</a></li>
                                        <li><a href="#">Another action</a></li>
                                        <li><a href="#">Something else here</a></li>
                                        <li class="divider"></li>
                                        <li><a href="#">Separated link</a></li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                        <!-- /.panel-heading -->
                        <div class="panel-body">
                            <!-- 图表注入 -->
                            <canvas id="myChart" width="400" height="200"></canvas>
                        </div>
                        <!-- /.panel-body -->
                    </div>
                    <!-- /.panel -->
					<div class="panel panel-default">
						<div class="panel-heading">
							<i class="fa fa-clock-o fa-fw"></i> 历史上的今天
						</div>
						<!-- /.panel-heading -->
						<div class="panel-body">
                            <ul class="timeline">
                                <c:forEach items="${requestScope.todayInHistory}" var="historyTemp">
                                    <c:choose>
                                        <c:when test="${fn:split(fn:split(historyTemp.value,'::')[1],'-')[0]%2==0}">
                                                <li class="timeline-inverted">
                                                    <div class="timeline-badge warning">
                                                        <i>${fn:split(fn:split(historyTemp.value,'::')[1],'-')[0]}</i>
                                                    </div>
                                                    <div class="timeline-panel">
                                                        <div class="timeline-heading">
                                                            <h4 class="timeline-title">${fn:split(historyTemp.value,"::")[0]}</h4>
                                                        </div>
                                                        <div class="timeline-body">
                                                            <a href="${historyTemp.key}" target="_blank" class="list-group-item">${historyTemp.value}</a>
                                                        </div>
                                                        <hr>
                                                            ${fn:split(historyTemp.value,"::")[1]}
                                                    </div>
                                                </li>
                                        </c:when>
                                        <c:otherwise>
                                                <li>
                                                    <div class="timeline-badge info">
                                                        <i>${fn:split(fn:split(historyTemp.value,'::')[1],'-')[0]}</i>
                                                    </div>
                                                    <div class="timeline-panel">
                                                        <div class="timeline-heading">
                                                            <h4 class="timeline-title">${fn:split(historyTemp.value,"::")[0]}</h4>
                                                        </div>
                                                        <div class="timeline-body">
                                                            <a href="${historyTemp.key}" class="list-group-item" target="_blank">${historyTemp.value}</a>
                                                            <hr>
                                                                ${fn:split(historyTemp.value,"::")[1]}
                                                        </div>
                                                    </div>
                                                </li>

                                        </c:otherwise>
                                    </c:choose>
                                </c:forEach>
                            </ul>
						</div>
						<!-- /.panel-body -->
					</div>
				</div>
				<div class="col-lg-4">
					<div class="panel panel-default">
						<div class="panel-heading">
							<i class="fa fa-bell fa-fw"></i> 国际新闻
						</div>
						<!-- /.panel-heading -->
						<div class="panel-body">
							<div class="list-group" id="news">
							<c:forEach items="${requestScope.news }" var="temp">
								<a href="${temp.value }" class="list-group-item" target="_blank"> <i
									class="fa fa-comment fa-fw"></i> ${temp.key }
									<em  align="right" ><p class="list-group-item-text">${requestScope.nowDate}</p></em>
								</a>
							</c:forEach>
							</div>
							<!-- /.list-group -->
							<a href="#" class="btn btn-default btn-block">View All Alerts</a>
						</div>
						<!-- /.panel-body -->
					</div>
					<!-- /.panel -->
					<div class="panel panel-default">
						<div class="panel-heading">
							<i class="fa fa-bar-chart-o fa-fw"></i>句子类型比例
						</div>
						<div class="panel-body" style="height:300px; overflow:auto;">
							<%-- <canvas id="typeChart" width="200" height="200"></canvas>
							<a href="#" class="btn btn-default btn-block">View Details</a> --%>
							 <table class="table table-striped table-bordered table-hover" style="overflow:auto">
                                <thead>
                                    <tr>
                                        <th>序号</th>
                                        <th>句子类型</th>
                                        <th>数量</th>
                                    </tr>
                                </thead>
                                <tbody >
                                <c:set value="1" var="count"></c:set>
                                <c:forEach items="${requestScope.typecount.typecount}" var="juzitype">
                                    <tr>
                                        <td>${count}</td>
                                        <td>${juzitype.leixingming}</td>
                                        <td>${juzitype.count}</td>
                                    </tr>
                                <c:set value="${count+1 }" var="count"></c:set>
                                </c:forEach>
                                </tbody>
                            </table>
						</div>
						<!-- /.panel-body -->
					</div>
					<!-- /.panel -->
					<!-- /.col-lg-8 -->
					<!-- 聊天注入 -->
					<jsp:include page="/Houtai/ibot.jsp"></jsp:include>
				</div>
				<!-- /.col-lg-4 -->
			</div>
			<!-- /.row -->
		</div>
		<!-- /#page-wrapper -->

	</div>
	<!-- /#wrapper -->



	<!-- Bootstrap Core JavaScript -->
	<script src="/SSM/assets/vendor/bootstrap/js/bootstrap.min.js"></script>

	<!-- Metis Menu Plugin JavaScript -->
	<script src="/SSM/assets/vendor/metisMenu/metisMenu.min.js"></script>

	<!-- Custom Theme JavaScript -->
	<script src="/SSM/assets/dist/js/sb-admin-2.js"></script>
</body>
</html>
