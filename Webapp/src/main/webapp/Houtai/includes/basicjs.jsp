<%@page import="java.text.SimpleDateFormat" %>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>

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

<%--文件上传--%>
<script src="/js/fileupload/jquery.Huploadify.js"></script>
<%--富文本--%>
<script type="text/javascript" src="/dist/js/wangEditor.js"></script>
<%--日期转换--%>
<script type="text/javascript" src="/js/dateFormat.js"></script>