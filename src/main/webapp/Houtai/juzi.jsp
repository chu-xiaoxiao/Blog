<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
<script type="text/javascript">
$(function(){
	var options = {
         bootstrapMajorVersion:3,
         currentPage: ${page2.currentPage+1},
         numberOfPages: ${page2.allPage}>5?"5":${page2.allPage},
         totalPages:${page2.allPage}, 
         onPageClicked : function (event, originalEvent, type, page) {
	        $("#frompage").attr("action","/Houtai/listjuzi.do?currentpage="+(page-1)+"&size=${requestScope.page2.size}");
	      	$("#frompage").submit();
         }
     };
	 $('#fenye').bootstrapPaginator(options);
	 $("a[id^=listsize]").click(function() {
		 var count = $(this).attr("id").split("_")[1];
		 $("#frompage").attr("action","/Houtai/listjuzi.do?currentpage=0&size="+count);
	     $("#frompage").submit();
	});
	 $("#exportxls").click(function(){

	     var flag = false;
	     if($("#juzi").val()!=""){
            flag=true;
         }
         if($("#chuchu").val()!=""){
             flag=true;
         }
         if($("#juzileixing").val()!=""){
             flag=true;
         }
         if(flag==false){
             $("#exportalert").attr("class", "alert alert-danger");
             $("#exportalert").html( '<strong>导出时请至少选择一个筛选条件</strong>');
             return false;
         }
         $("#exportxls").attr("class","btn btn-primary btn disabled");
         $("#exportxls").val("正在生成xls文件,请耐心等待。。。");
         var form=$("<form>");//定义一个form表单
         form.attr("style","display:none");
         form.attr("target","");
         form.attr("method","post");
         form.attr("action",'/Houtai/exportExcle.do?juzi='+$("#juzi").val()+'&chuchu='+$("#chuchu").val()+'&juzileixing='+$("#juzileixing").val());
         var input1=$("<input>");
         input1.attr("type","hidden");
         input1.attr("name","exportData");
         input1.attr("value",(new Date()).getMilliseconds());
         $("body").append(form);//将表单放置在web中

         form.submit();//表单提交
         $("#exportalert").attr("class", "alert alert-success alert-dismissable");
         $("#exportalert").html( '<strong>导出成功。。。。。</strong>');

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
				句库
				<div class="pull-right">
					<div class="btn-group">
						<button type="button"
							class="btn btn-default btn-xs dropdown-toggle"
							data-toggle="dropdown">
							数量 <span class="caret"></span>
						</button>
						<ul class="dropdown-menu pull-right" role="menu">
							<li><a href="#" id="listsize_10">10</a></li>
							<li><a href="#" id="listsize_30">30</a></li>
							<li><a href="#" id="listsize_60">60</a></li>
							<li><a href="#" id="listsize_100">100</a></li>
						</ul>
					</div>
				</div>
			</div>
			<div class="panel-body">
				<div class="table-responsive">
					<table class="table table-striped table-bordered table-hover">
						<thead>
							<tr>
								<td>序号</td>
								<th>句子内容</th>
								<th>句子出处</th>
								<th>句子类型</th>
							</tr>
						</thead>
						<tbody>
							<c:set value="1" var="count"></c:set>
							<c:forEach items="${requestScope.page2.lists}" var="juzis">
								<tr>
									<td>${count}</td>
									<td>${juzis.juzineirong}</td>
									<td>${juzis.juzichuchu }</td>
									<td>${juzis.juziTypeKey.leixingming }</td>
								</tr>
								<c:set value="${count+1}" var="count"></c:set>
							</c:forEach>
						</tbody>
					</table>
				</div>
				<!-- /.table-responsive -->
				<!-- 分页注入 -->
				<!-- <div id="fenye"></div> -->
				<ul id='fenye'></ul>

				<hr class="dirver"/>
				<form id="frompage" method="post" action="" role="form">
					<div class="form-group">
						<label for="name">内容</label> <input name="juzi" type="text"
							value="${requestScope.juzi }" class="form-control" id="juzi"/>
					</div>
					<div class="form-group">
						<label for="name">出处/作者</label> <input name="chuchu" type="text"
							value="${requestScope.chuchu }" class="form-control" id="chuchu"/>
					</div>
					<div class="form-group">
						<label for="name">句子类型</label> <select name="juzileixing"
							class="form-control" id="juzileixing">
							<option></option>
							<c:forEach items="${requestScope.juzileixing}" var="juzileixing">
								<c:choose>
									<c:when test="${juzileixing.leixingid==requestScope.juzitype}">
										<option value="${juzileixing.leixingid }" selected="selected">${juzileixing.leixingming}</option>
									</c:when>
									<c:otherwise>
										<option value="${juzileixing.leixingid }">${juzileixing.leixingming}</option>
									</c:otherwise>
								</c:choose>
							</c:forEach>
						</select>
					</div>
					<input type="submit" value="查询" class="btn btn-primary">
                    <span style="float:right" ><input type="button" value="导出至xls" class="btn btn-primary" id="exportxls"></span>
                    <div id="exportalert"></div>
				</form>
				<hr class="dirver"/>
				<form action="/Houtai/updataJuzi.do" role="form">
					<div class="form-group">
						<label for="name"> 爬取网站</label> <input name="juziurl" type="text" class="form-control" />
					</div>
					<input type="submit" value="爬取" class="btn btn-primary">
				</form>
			</div>
			<!-- 条件查询 -->
			<div></div>
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
