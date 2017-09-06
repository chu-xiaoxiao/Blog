<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<aside id="fh5co-hero" class="js-fullheight">
	<div class="flexslider js-fullheight">
		<ul class="slides">
		<c:forEach items="${requestScope.juzis }" var="juzi">
		   	<li style="background-image: url(/home/imgs/${juzi.key });">
		   		<div class="overlay"></div>
		   		<div class="container-fluid">
		   			<div class="row">
			   			<div class="col-md-8 col-md-offset-2 text-center js-fullheight slider-text">
			   				<div class="slider-text-inner">
			   					<h1>${juzi.value }</h1>
			   					<h2>By ZYC </h2>
			   				</div>
			   			</div>
			   		</div>
		   		</div>
		   	</li>
	   	</c:forEach>
	  	</ul>
  	</div>
</aside>