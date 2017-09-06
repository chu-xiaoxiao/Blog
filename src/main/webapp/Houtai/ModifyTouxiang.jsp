<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>Insert title here</title>

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

<link rel="stylesheet" type="text/css" href="/SSM/css/fileupload/Huploadify.css"/>
<!-- <script src="/SSM/js/jquery.js"></script> -->
<script
	src="http://cdn.static.runoob.com/libs/jquery/2.1.1/jquery.min.js"></script>
<script src="/SSM/js/jquery-confirm.js"></script>
<script type="text/javascript">
$(function(){
	$('#upload').Huploadify({
		auto:true,
		multi:true,
		formData:{key:123456,key2:'vvvv'},
		fileSizeLimit:9999,
		showUploadedPercent:true,//是否实时显示上传的百分比，如20%
		showUploadedSize:true,
		removeTimeout: 1000,
		uploader:'/SSM/user/uploadTemp.do',
		onUploadStart:function(){
			//alert('开始上传');
			},
		onInit:function(){
			//alert('初始化');
			},
		onUploadComplete:function(file,data){
				$("#tempimg").attr("src",data)
			},
		onDelete:function(file){
			console.log('删除的文件：'+file);
			console.log(file);
		}
		});
	
	$("#tijiaoTemp").click(function(){
		$.ajax({
			type:"GET",
			url:"/SSM/user/xiugaitouxiang.do",
			success:function(){
				location.href="/SSM/Houtai/index.jsp"
			}
		});
	});
});
</script>
</head>
<body>
	<jsp:include page="/Houtai/nav.jsp"></jsp:include>
	<!-- Page Content -->
        <div id="page-wrapper">
            <div class="container-fluid">
                <div class="row">
                    <div class="col-lg-12">
                        <h1 class="page-header">修改头像</h1>
                    </div>
                    <!-- /.col-lg-12 -->
                </div>
                <!-- /.row -->
            </div>
            <!-- /.container-fluid -->
            <div class="panel panel-default">
             <div class="panel-heading">
             </div>
             <div class="form-group">
				<img src="" width="200px" height="200px" id="tempimg">
				<div id="upload"></div>
             <button id="tijiaoTemp" class="btn btn-default">修改头像</button>
            </div>
          </div>
        </div>
        <!-- /#page-wrapper -->
	
</body>
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
	
	<script src="/SSM/js/fileupload/jquery.Huploadify.js"></script>

</html>