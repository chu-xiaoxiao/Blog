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
<!--[if gt IE 8]><!--> <html class="no-js"> <!--<![endif]-->
	<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<title>Marble &mdash; Free HTML5 Bootstrap Website Template by FreeHTML5.co</title>
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta name="description" content="Free HTML5 Website Template by FreeHTML5.co" />
	<meta name="keywords" content="free html5, free template, free bootstrap, free website template, html5, css3, mobile first, responsive" />
  	<!-- Facebook and Twitter integration -->
	<meta property="og:title" content=""/>
	<meta property="og:image" content=""/>
	<meta property="og:url" content=""/>
	<meta property="og:site_name" content=""/>
	<meta property="og:description" content=""/>
	<meta name="twitter:title" content="" />
	<meta name="twitter:image" content="" />
	<meta name="twitter:url" content="" />
	<meta name="twitter:card" content="" />

	<!-- Place favicon.ico and apple-touch-icon.png in the root directory -->
	<link rel="shortcut icon" href="favicon.ico">

	<link href="https://fonts.googleapis.com/css?family=Roboto:100,300,400,500,700" rel="stylesheet">
	
	<!-- Animate.css -->
	<link rel="stylesheet" href="/wenzhang/mar/css/animate.css">
	<!-- Icomoon Icon Fonts-->
	<link rel="stylesheet" href="/wenzhang/mar/css/icomoon.css">
	<!-- Bootstrap  -->
	<link rel="stylesheet" href="/wenzhang/mar/css/bootstrap.css">
	<!-- Flexslider  -->
	<link rel="stylesheet" href="/wenzhang/mar/css/flexslider.css">
	<!-- Theme style  -->
	<link rel="stylesheet" href="/wenzhang/mar/css/style.css">

	<!-- Modernizr JS -->
	<script src="/wenzhang/mar/js/modernizr-2.6.2.min.js"></script>
	<!-- FOR IE9 below -->
	<!--[if lt IE 9]>
	<script src="js/respond.min.js"></script>
	<![endif]-->

	</head>
	<body>
	<div id="fh5co-page">
	<jsp:include page="nav.html"></jsp:include>
		<div id="fh5co-main">
			<jsp:include page="yemian.jsp"></jsp:include>
			<div class="fh5co-narrow-content">
				<h2 class="fh5co-heading animate-box" data-animate-effect="fadeInLeft">Services</h2>
				<div class="row">
					<jsp:include page="/wenzhang/4tubiao.jsp"></jsp:include>
				</div>
			</div>
			<div class="fh5co-narrow-content">
				<h2 class="fh5co-heading animate-box" data-animate-effect="fadeInLeft">Recent Blog</h2>
				<div class="row row-bottom-padded-md">
					<c:forEach items="${page.lists }" var="wenzhang1">
						<div class="col-md-3 col-sm-6 col-padding animate-box" data-animate-effect="fadeInLeft">
							<div class="blog-entry">
	<!-- 							<a href="#" class="blog-img"><img src="/wenzhang/mar/css/images/img-1.jpg" class="img-responsive" alt="Free HTML5 Bootstrap Template by FreeHTML5.co"></a> -->
								<div class="desc">
									<h3><a href="/wenzhang/xiangxi.do?wenzhangid=${wenzhang1.wenzhangid }">${wenzhang1.wenzhangbiaoti}</a></h3>
									<span><small>${wenzhang1.wenzhangriqi}</small></span>
									<p><c:if test="${fn:length(wenzhang1.wenzhangchunwenben)>100 }">${fn:substring(fn:replace(wenzhang1.wenzhangchunwenben,"<","") ,0,100)} ...</c:if>
	 								<c:if test="${fn:length(wenzhang1.wenzhangchunwenben)<=100 }">${fn:replace(wenzhang1.wenzhangchunwenben,"<","")}</c:if></p>
									<a href="/wenzhang/xiangxi.do?wenzhangid=${wenzhang1.wenzhangid }" class="lead">Read More <i class="icon-arrow-right3"></i></a>
								</div>
							</div>
						</div>
					</c:forEach>
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
						<div class="col-md-6 col-md-offset-3 col-md-pull-3 animate-box" data-animate-effect="fadeInLeft">
							<p class="fh5co-lead">Separated they live in Bookmarksgrove right at the coast of the Semantics, a large language ocean.</p>
							<p><a href="#" class="btn btn-primary">Learn More</a></p>
						</div>
						
					</div>
				</div>
			</div>
		</div>
	</div>

	<!-- jQuery -->
	<script src="/wenzhang/mar/js/jquery.min.js"></script>
	<!-- jQuery Easing -->
	<script src="/wenzhang/mar/js/jquery.easing.1.3.js"></script>
	<!-- Bootstrap -->
	<script src="/wenzhang/mar/js/bootstrap.min.js"></script>
	<!-- Waypoints -->
	<script src="/wenzhang/mar/js/jquery.waypoints.min.js"></script>
	<!-- Flexslider -->
	<script src="/wenzhang/mar/js/jquery.flexslider-min.js"></script>
	
	<!-- MAIN JS -->
	<script src="/wenzhang/mar/js/main.js"></script>

	</body>
</html>

