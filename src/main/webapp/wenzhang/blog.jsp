<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<!--[if lt IE 7]>      <html class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]>         <html class="no-js lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]>         <html class="no-js lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!-->
<html class="no-js">
<!--<![endif]-->
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>Marble &mdash; Free HTML5 Bootstrap Website Template by
	FreeHTML5.co</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description"
	content="Free HTML5 Website Template by FreeHTML5.co" />
<meta name="keywords"
	content="free html5, free template, free bootstrap, free website template, html5, css3, mobile first, responsive" />
	<!-- jQuery -->
	<script src="/SSM/wenzhang/mar/js/jquery.min.js"></script>



<!-- Facebook and Twitter integration -->
<meta property="og:title" content="" />
<meta property="og:image" content="" />
<meta property="og:url" content="" />
<meta property="og:site_name" content="" />
<meta property="og:description" content="" />
<meta name="twitter:title" content="" />
<meta name="twitter:image" content="" />
<meta name="twitter:url" content="" />
<meta name="twitter:card" content="" />


<!-- Place favicon.ico and apple-touch-icon.png in the root directory -->
<link rel="shortcut icon" href="favicon.ico">

<link
	href="https://fonts.googleapis.com/css?family=Roboto:100,300,400,500,700"
	rel="stylesheet">

<!-- Animate.css -->
<link rel="stylesheet" href="/SSM/wenzhang/mar/css/animate.css">
<!-- Icomoon Icon Fonts-->
<link rel="stylesheet" href="/SSM/wenzhang/mar/css/icomoon.css">
<!-- Bootstrap  -->
<link rel="stylesheet" href="/SSM/wenzhang/mar/css/bootstrap.css">
<!-- Flexslider  -->
<link rel="stylesheet" href="/SSM/wenzhang/mar/css/flexslider.css">
<!-- Theme style  -->
<link rel="stylesheet" href="/SSM/wenzhang/mar/css/style.css">
<link rel="stylesheet" href="/SSM/css/fenye.css">

<!-- Modernizr JS -->
<script src="/SSM/wenzhang/mar/js/modernizr-2.6.2.min.js"></script>
<!-- FOR IE9 below -->
<!--[if lt IE 9]>
	<script src="js/respond.min.js"></script>
	<![endif]-->
<script type="text/javascript">
	$(function(){
		var options = {
		         bootstrapMajorVersion:3,
		         currentPage: ${page.currentPage},
		         numberOfPages: ${page.allPage}>5?"5":${page.allPage},
		         totalPages:${page.allPage}, 
		         onPageClicked : function (event, originalEvent, type, page) {
			        $("#frompage").attr("action","/SSM/wenzhang/blogs.do?currentPage="+(page));
			      	$("#frompage").submit();
		         }
		     };
			 $('#fenye').bootstrapPaginator(options);
	});
	</script>
</head>
<body>
	<div id="fh5co-page">
		<jsp:include page="nav.html"></jsp:include>
		<div id="fh5co-main">
			<div class="fh5co-narrow-content">
				<h2 class="fh5co-heading animate-box"
					data-animate-effect="fadeInLeft">Read Our Blog</h2>
				<div class="row row-bottom-padded-md">
				<c:set value="1" var="count"></c:set>
					<c:forEach items="${page.lists }" var="wenzhang1">
						<div class="col-md-3 col-sm-6 col-padding animate-box"
							data-animate-effect="fadeInLeft">
							<div class="blog-entry">
								<!-- <a href="#" class="blog-img"><img src="images/img-1.jpg" class="img-responsive" alt="Free HTML5 Bootstrap Template by FreeHTML5.co"></a> -->
								<div class="desc">
									<h3>
										<a href="/SSM/wenzhang/xiangxi.do?wenzhangid=${wenzhang1.id }">${wenzhang1.wenzhangbiaoti}</a>
									</h3>
									<span><small>${wenzhang1.wenzhangriqi}</small></span>
									<p>
										<c:if test="${fn:length(wenzhang1.wenzhangchunwenben)<=100 }">${fn:replace(wenzhang1.wenzhangchunwenben,"<","")}</c:if>
										<c:if test="${fn:length(wenzhang1.wenzhangchunwenben)>=100 }">${fn:substring(fn:replace(wenzhang1.wenzhangchunwenben,"<","") ,0,100)} ...</c:if>
									</p>
									<a href="/SSM/wenzhang/xiangxi.do?wenzhangid=${wenzhang1.id }" class="lead">Read More <i
										class="icon-arrow-right3"></i></a>
								</div>
							</div>
						</div>
						<c:if test="${count%4==0}">
							<div class="row">
							</div>
						</c:if>
						<c:set value="${count=count+1}" var="count"></c:set>
					</c:forEach>
				</div>
			</div>
			<div class="row">
				<div class="col-md-6 col-md-offset-3 col-md-pull-3 animate-box" data-animate-effect="fadeInLeft" align="center" >
				<form action="" method="post" id="frompage" class="form-inline">
					<p>文章标题: <input type="search" name="wenzhangbiaoti" class="form-control" value="${page.wenZhangSearch.name }" /></p>
					<p>文章类型:<input type="text" name="wenzhangleixing" class="form-control" value="${page.wenZhangSearch.type }" /></p>
					<p><input type="submit" value="查找" class="btn btn-default" /></p>
				</form>
					<ul id='fenye'></ul>
				</div>
			</div>
			<div id="get-in-touch">
				<div class="fh5co-narrow-content">
					<div class="row">
						<div class="col-md-4 animate-box" data-animate-effect="fadeInLeft">
							<h1 class="fh5co-heading-colored">Get in touch</h1>
						</div>
					</div>
					<div class="row">
						<div class="col-md-6 col-md-offset-3 col-md-pull-3 animate-box"
							data-animate-effect="fadeInLeft">
							<p class="fh5co-lead">Separated they live in Bookmarksgrove
								right at the coast of the Semantics, a large language ocean.</p>
							<p>
								<a href="#" class="btn btn-primary">Learn More</a>
							</p>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<!-- jQuery -->
	<script src="/SSM/wenzhang/mar/js/jquery.min.js"></script>
	<!-- jQuery Easing -->
	<script src="/SSM/wenzhang/mar/js/jquery.easing.1.3.js"></script>
	<!-- Bootstrap -->
	<script src="/SSM/wenzhang/mar/js/bootstrap.min.js"></script>
	<!-- Waypoints -->
	<script src="/SSM/wenzhang/mar/js/jquery.waypoints.min.js"></script>
	<!-- Flexslider -->
	<script src="/SSM/wenzhang/mar/js/jquery.flexslider-min.js"></script>
	<!-- MAIN JS -->
	<script src="/SSM/wenzhang/mar/js/main.js"></script>
	<!-- 分页 -->
	<script type="text/javascript" src="/SSM/js/bootstrap-paginator.min.js"></script>
	<script type="text/javascript" src="/SSM/js/bootstrap-paginator.min.js"></script>
</body>
</html>

