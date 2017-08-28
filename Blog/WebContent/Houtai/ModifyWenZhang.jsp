<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css"
	href="/SSM//dist/css/wangEditor.min.css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="/SSM/css/bootstrap.css" rel="stylesheet" />
<script type="text/javascript" src="/SSM/js/jquery.js"></script>
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

<title>修改文章</title>
</head>

<body>
	<form action="/SSM/Houtai/modifywenzhangaction.do" method="post"  class="form-inline"
		id="form1">
		<input type="hidden" name="wenzhangid" value="${wenZhang.id }" />
		<div class="form-group">
			<label class="col-sm-2 control-label">文章题目</label>
			<div class="col-sm-10" style="width: 300px">
				<input type="text" name="wenzhangbiaoti"
					value="${wenZhang.wenzhangbiaoti }" />
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">文章类型</label>
			<div class="col-sm-10" style="width: 300px">
				<input type="text" name="wenzhangleixing"
					value="${wenZhang.wenzhangleixing }" />
			</div>
		</div>
		<div id="editor-container" class="container" style="width: 75%">
			<div id="editor-trigger">
				<p>${wenZhang. wenzhangneirong}</p>
			</div>
		</div>
		<input type="hidden" id="hiddentext" name="wenzhangneirong" /> 
		<input type="hidden" id="wenzhangchunwenben" name="wenzhangchunwenben" />
		<div class="form-group">
				<div class="col-sm-10 col-sm-offset-2">
					<input type="hidden" id="hiddentext" name="wenzhangneirong" /> <input
						type="hidden" id="wenzhangchunwenben" name="wenzhangchunwenben" />
					<button id="tijiao" class="btn btn-default">修改文章</button>
				</div>
			</div>
	</form>
	<script type="text/javascript"
		src="/SSM//dist/js/lib/jquery-1.10.2.min.js"></script>
	<script type="text/javascript" src="/SSM/dist/js/wangEditor.js"></script>
	<script type="text/javascript">
		// 阻止输出log
		// wangEditor.config.printLog = false;

		var editor = new wangEditor('editor-trigger');
		editor.config.uploadImgUrl = '/SSM/wenzhang/upLoadImg.do';
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