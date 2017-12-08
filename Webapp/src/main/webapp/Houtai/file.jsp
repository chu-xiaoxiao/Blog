<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <jsp:include page="includes/basiccss.jsp"/>
    <!-- filetree -->
    <link rel="stylesheet" href="/css/filetree/jQueryFileTree.min.css">

    <!-- filetree -->
    <script src="/js/filetree/jQueryFileTree.min.js"></script>
    <script type="text/javascript">
        $(document).ready(function () {
            $('#filetree').fileTree({
                root: '/home/imgs',
                script: '/Houtai/filelist.do',
                expandSpeed: 1000,
                collapseSpeed: 1000,
                multiFolder: false
            });
        });
    </script>
</head>
<body>
<jsp:include page="/Houtai/includes/nav.jsp"></jsp:include>

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
<jsp:include page="includes/basicjs.jsp"/>
</html>