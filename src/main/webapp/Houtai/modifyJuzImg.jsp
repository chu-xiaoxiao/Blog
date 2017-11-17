<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="cf" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <jsp:include page="includes/basiccss.jsp"/>
    <script type="text/javascript">
        $(function () {
            for (var i = 1; i <= 3; i++) {
                $('#upload_' + i).Huploadify({
                    auto: true,
                    multi: true,
                    formData: {key: 123456, key2: 'vvvv'},
                    fileSizeLimit: 9999,
                    showUploadedPercent: true,//是否实时显示上传的百分比，如20%
                    showUploadedSize: true,
                    removeTimeout: 1000,
                    uploader: '/Houtai/modifyJuziImgAction.do?id=' + i,
                    onUploadStart: function () {

                    },
                    onInit: function () {
                        //alert('初始化');
                    },
                    onUploadComplete: function (file, data) {
                        $("#tempimg").attr("src", data)
                    },
                    onDelete: function (file) {
                        console.log('删除的文件：' + file);
                        console.log(file);
                    }
                });
            }
            ;
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
                <h1 class="page-header">修改首页句子图片</h1>
            </div>
            <!-- /.col-lg-12 -->
        </div>
        <!-- /.row -->
    </div>
    <!-- /.container-fluid -->
    <div class="panel panel-default">
        <div class="panel-heading">
            Basic Form Elements
        </div>
        <div class="form-group">
            <form action="/Houtai/modifyJuziaction.do">
                <c:set value="${1 }" var="count"></c:set>
                <c:forEach items="${requestScope.juzis }" var="juzi">
                    <div class="form-group">
                        <label> <img src="/home/imgs/${juzi.key}" height="200px" width="200px"
                                     name="img${cf:substring(juzi.key,4,cf:length(juzi.key))}"></label>
                        <div id="upload_${count }"></div>
                        <input class="form-control" value="${juzi.value}"
                               name="juzi${cf:substring(juzi.key,4,cf:length(juzi.key))}"/>
                        <c:set value="${count+1}" var="count"></c:set>
                    </div>
                </c:forEach>
                <input type="submit" class="btn btn-default" value="修改"/>
            </form>
        </div>
    </div>
</div>
<!-- /#page-wrapper -->

</body>
<jsp:include page="includes/basicjs.jsp"/>
</html>