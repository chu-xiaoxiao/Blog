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

<!-- DataTables CSS -->
<link
	href="/SSM/assets/vendor/datatables-plugins/dataTables.bootstrap.css"
	rel="stylesheet">

<!-- DataTables Responsive CSS -->
<link
	href="/SSM/assets/vendor/datatables-responsive/dataTables.responsive.css"
	rel="stylesheet">

<!-- Custom CSS -->
<link href="/SSM/assets/dist/css/sb-admin-2.css" rel="stylesheet">

<!-- Custom Fonts -->
<link href="/SSM/assets/vendor/font-awesome/css/font-awesome.min.css"
	rel="stylesheet" type="text/css">

<link rel="stylesheet" href="/SSM/css/jquery-confirm.css">
<link rel="stylesheet" href="/SSM/css/fenye.css">

<link rel="stylesheet" type="text/css"
	href="/SSM//dist/css/wangEditor.min.css">
<!-- <script src="/SSM/js/jquery.js"></script> -->
<script
	src="http://cdn.static.runoob.com/libs/jquery/2.1.1/jquery.min.js"></script>
<script src="/SSM/js/jquery-confirm.js"></script>

<script type="text/javascript" src="/SSM/dist/js/wangEditor.js"></script>


<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
<script type="text/javascript"></script>
<script type="text/javascript">
//修改富文本编辑器初始化						
$(function() {
	var editor = new wangEditor('editor-trigger1');
	editor.config.uploadImgUrl = '/SSM/Houtai/upLoadImg.do';
	editor.config.mapAk = 'ByDEc7m5D1gKYKOGTqiltNNjqHvqQmmj';
	editor.create();
	var editor1 = new wangEditor('editor-trigger');
	editor1.config.uploadImgUrl = '/SSM/Houtai/upLoadImg.do';
	editor1.config.mapAk = 'ByDEc7m5D1gKYKOGTqiltNNjqHvqQmmj';
	editor1.create();
	//加载分页
	 $("#fenye").paginate({
       count       : ${page.allPage},//总页数  
       start       : ${page.currentPage},//当前页码  
       display     : (${page.allPage}>5)?"5":${page.allPage},//设置每页显示页码数  
       border                  : true,  
       border_color            : '#B0B0CC',//border颜色  
       text_color              : '#5C6C90',//字体颜色  
       background_color        : '#FAFFFF',      
       border_hover_color      : '#000E53',  
       text_hover_color        : '#fff',  
       background_hover_color  : '#2F6BA7',   
       images                  : false,  
       mouse                   : 'press',  
       onChange: function(p) {
    	   $("#frompage").attr("action","/SSM/Houtai/findByPage.do?currentPage="+(p));
    	   $("#frompage").submit();
/*      		$.get("/SSM/Houtai/findByPage2.do", {currentPage:p},function(data){
     			var list = JSON.parse(data);
     			$("#neirong").html("");
     			$("#neirong").append("<table class='table table-striped table-bordered table-hover' id='pagetable'> <thead><tr><th>id</th><th>文章标题</th><th>文章类型</th><th>发布日期</th><th>修改/删除</th></tr></thead><tbody >");
     			$.each(list.lists, function(i, item) {
     				$("#neirong").append("<tr>");
     				$("#neirong").append("<td id='wenzhangid'>"+item.id+"</td>");
     				$("#neirong").append("<td><a href=/SSM/wenzhang/xiangxi.do?wenzhangid="+item.id+">"+item.wenzhangbiaoti+"</a></td>");
     				$("#neirong").append("<td>"+item.wenzhangleixing+"</td>");
     				//$("#neirong").append("<td>"+item.wenzhangriqi+"</td>");
     				$("#neirong").append("<td><button type='button' class='btn btn-info' id='modify"+item.id+"' value='"+item.id+"'>修改</button> <button type='button' class='btn btn-danger' id='deletewenzhang"+item.id+"' value='"+item.id+"'>删除</button> <input type='hidden' id='deletneirong"+item.id+"' value="+item.wenzhangbiaoti+"/> </td>");
     				$("#neirong").append("</tr></tbody></table>");
     				//$("#pagetable").trigger("create");
     	        });
     			
     		}); */
       }
	 });
	$("#sieze").change(function() {
				$("#frompage").submit();
	});
	$("#addwenzhang").click(function(){
		$("#myModa1").modal("show");
		$("#tijiao").click(function() {
			$("#hiddentext").val(editor1.$txt.html());
			$("#wenzhangchunwenben").val(editor1.$txt.text());
			$("#hiddentext").val(editor1.$txt.html());
			$("#form1").submit();
		});
	});
	$("button[id*=deletewenzhang]").on('click',function(){
		var id = $(this).val();
		$.confirm({
			title:"不可逆的删除操作",
			content:"确认要删除文章《"+$("#deletneirong"+id).val()+"》吗？",
		    theme: 'supervan' ,// 'material', 'bootstrap'
		    buttons:{
		    	ok:function(){
		    		location.href="/SSM/Houtai/delete.do?wenzhangid="+id;
		    	},
				cancel:function(){
					return;
				}
		    }
		});
	});
	$("button[id*=modify]").click(function(){
		var id = $(this).val();
		 $.ajax({
	        type:"post",
	        url:"/SSM/Houtai/modifywenzhang.do?wenzhangid="+id,
	        data:null,
	        dataType:"text",
	        beforeSend: function(XMLHttpRequest){},
	        success:function(data){
	        	var obj = JSON.parse(data);
        		$("#myModal1").modal("show");
				$("#wenzhangbiaoti1").val(obj.wenzhang.wenzhangbiaoti);
				$("#wenzhangleixing1").val(obj.wenzhang.wenzhangleixing);
				$("#wenzhangid1").val(obj.wenzhang.id);
				editor.$txt.html(obj.wenzhang.wenzhangneirong);
				$("#tijiao1").click(function() {
					$("#wenzhangchunwenben1").val(editor.$txt.text())
					$("#hiddentext1").val(editor.$txt.html())
 					$("#form11").submit();  
				});
	        },
	        complete:function(XMLHttpRequest,textStatus){},
	        error:function(message){
	        alert("调用ajax请求出错");
	        alert(message);}
	    }); 
	});
 });
</script>
</head>

<body>
	<div id="outp	"></div>
	<jsp:include page="nav.html"></jsp:include>
	<div id="page-wrapper">
		<div class="row">
			<div class="col-lg-12">
				<h1 class="page-header">文章管理</h1>
			</div>
			<!-- /.col-lg-12 -->
		</div>
		<div class="panel panel-default">
			<div class="panel-heading">文章列表</div>
			<!-- /.panel-heading -->
			<div class="panel-body">
				<div class="table-responsive" id="neirong">
					<!-- 分页表单 -->
					<form action="" method="post" id="frompage" class="form-inline">
						<div class="col-sm-6">
							<div class="dataTables_length" id="dataTables-example_length">
								<label>Show <select id ="sieze" name="sieze" aria-controls="dataTables-example" class="form-control input-sm">
										<option value="5" >5</option>
										<option value="10">10</option>
										<option value="20">20</option>
										<option value="50">50</option>
								</select>entries
								</label>
							</div>
						</div>
						<div class="col-sm-6">
							<div id="dataTables-example_filter" class="dataTables_filter">
								<label>文章标题: <input type="search" name="wenzhangbiaoti"
									class="form-control" value="${page.wenZhangSearch.name }" /></label> <label>文章类型:
									<input type="text" name="wenzhangleixing" class="form-control"
									value="${page.wenZhangSearch.type }" />
								</label> <input type="submit" value="查找" class="btn btn-default" />
							</div>
						</div>
						<table class="table table-striped table-bordered table-hover"
							id="pagetable">
							<thead>
								<tr>
									<th>id</th>
									<th>文章标题</th>
									<th>文章类型</th>
									<th>发布日期</th>
									<th>修改/删除</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${page.lists }" var="wenzhang1">
									<tr>
										<td id="wenzhangid">${wenzhang1.id}</td>
										<td><a
											href="/SSM/wenzhang/xiangxi.do?wenzhangid=${wenzhang1.id }">${wenzhang1.wenzhangbiaoti}</a>
										</td>
										<td>${wenzhang1.wenzhangleixing}</td>
										<td>${wenzhang1.wenzhangriqi}</td>
										<td><button type="button" class="btn btn-info"
												id="modify${wenzhang1.id}" value="${wenzhang1.id}">修改</button>
											<button type="button" class="btn btn-danger"
												id="deletewenzhang${wenzhang1.id}" value="${wenzhang1.id}">删除</button>
											<input type="hidden" id="deletneirong${wenzhang1.id}"
											value="${wenzhang1.wenzhangbiaoti}" /></td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</form>
					<button class="btn btn-default" id="addwenzhang">添加文章</button>
				</div>
				<!-- /.table-responsive -->
				<!-- 分页注入 -->
				<div id="fenye"></div>
			</div>
			<!-- /.panel-body -->
		</div>
		<!-- /.panel -->
	</div>
	<jsp:include page="modal.html"></jsp:include>
	<!-- Bootstrap Core JavaScript -->
	<script src="/SSM/assets/vendor/bootstrap/js/bootstrap.min.js"></script>

	<!-- Metis Menu Plugin JavaScript -->
	<script src="/SSM/assets/vendor/metisMenu/metisMenu.min.js"></script>

	<!-- DataTables JavaScript -->
	<script src="/SSM/assets/vendor/datatables/js/jquery.dataTables.min.js"></script>
	<script
		src="/SSM/assets/vendor/datatables-plugins/dataTables.bootstrap.min.js"></script>
	<script
		src="/SSM/assets/vendor/datatables-responsive/dataTables.responsive.js"></script>

	<!-- Custom Theme JavaScript -->
	<script src="/SSM/assets/dist/js/sb-admin-2.js"></script>
	<script src="/SSM/js/jquery.paginate.js"></script>
	<script src="/SSM/js/jqueryForm.js"></script>
</body>

</html>
