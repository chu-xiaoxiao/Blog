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

<link rel="stylesheet" type="text/css"
	href="/SSM/css/fileupload/Huploadify.css" />
<!-- filetree -->
<link rel="stylesheet" href="/SSM/css/filetree/jQueryFileTree.min.css">
<!-- <script src="/SSM/js/jquery.js"></script> -->
<script
	src="http://cdn.static.runoob.com/libs/jquery/2.1.1/jquery.min.js"></script>
<script src="/SSM/js/jquery-confirm.js"></script>

<!-- filetree -->
<script src="/SSM/js/filetree/jQueryFileTree.min.js"></script>
<script type="text/javascript">
$(document).ready( function() {
    $('#filetree').fileTree({
        root: '/home/imgs',
        script: '/SSM/Houtai/filelist.do',
        expandSpeed: 1000,
        collapseSpeed: 1000,
        multiFolder: false
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
					<h1 class="page-header">文件浏览</h1>
				</div>
				<!-- /.col-lg-12 -->
			</div>
			<!-- /.row -->
		</div>
		<!-- /.container-fluid -->
		<div class="panel panel-default">
			<div id="filetree"></div>
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

</html>