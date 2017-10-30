<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css"
	href="//dist/css/wangEditor.min.css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="/js/jquery.js"></script>
<link
	href="/css/bootstrap.css"
	rel="stylesheet" />
<style type="text/css">
#editor-trigger {
	height: 400px;
	/*max-height: 500px;*/
}

.container {
	width: 100%;
	margin: 0 auto;
	position: relative;
}
</style>

<title>添加文章</title>
</head>

<body>
	<div class="modal-body"">
		<div>
			<form action="/Houtai/addWenZhang.do" method="post" id="form1"
				class="form-horizontal">
				<div class="form-group">
					<label class="col-sm-2 control-label">文章题目</label>
					<div class="col-sm-10" style="width: 300px">
						<input type="text" name="wenzhangbiaoti" class="form-control" />
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">文章类型</label>
					<div class="col-sm-10" style="width: 300px">
						<input type="text" name="wenzhangleixing" class="form-control" />
					</div>
				</div>
				<div id="editor-container" class="container" style="width: 75%">
					<div id="editor-trigger">
						<p>文章内容</p>
					</div>
				</div>
				<div class="form-group">
					<div class="col-sm-10 col-sm-offset-2">
						<input type="hidden" id="hiddentext" name="wenzhangneirong" /> <input
							type="hidden" id="wenzhangchunwenben" name="wenzhangchunwenben" />
						<button id="tijiao" class="btn btn-default">提交文章</button>
					</div>
				</div>
			</form>
		</div>
		<a href ="/wenzhang/ListWenZhang.jsp">回主页</a>
	</div>
	<script type="text/javascript" src="//dist/js/lib/jquery-1.10.2.min.js"></script>
	<script type="text/javascript" src="/dist/js/wangEditor.js"></script>
	<script type="text/javascript">
		// 阻止输出log
		// wangEditor.config.printLog = false;

		var editor = new wangEditor('editor-trigger');
		editor.config.uploadImgUrl = '/wenzhang/upLoadImg.do';
		  editor.config.mapAk = 'ByDEc7m5D1gKYKOGTqiltNNjqHvqQmmj';
		editor.create();
		function show() {
			var html = editor.$txt.html();
			alert(html);
		}
		$(document).ready(function() {
			$("#tijiao").click(function() {
				$("#hiddentext").val(editor.$txt.html())
				$("#wenzhangchunwenben").val(editor.$txt.text())
				$("#form1").submit;
			})
		})
	</script>
</body>
</html>