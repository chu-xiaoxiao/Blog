<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>  
<head>  
    <meta charset="UTF-8">  
    <title></title>  
  
    <style type="text/css">  
        html, body {  
            width: 100%;  
            height: 100%;  
            margin: 0;  
            padding: 0;  
            overflow: hidden;  
        }  
  
        #map {  
            width: 100%;  
            height: 100%;  
        }  
    </style>  
</head>  
<body>  
  
    <div id="map"></div>  
    <canvas id="canvas"></canvas>  
  
    <script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=ByDEc7m5D1gKYKOGTqiltNNjqHvqQmmj"></script>  
    <script type="text/javascript" src="mapv.js"></script>  
  
    <script type="text/javascript">  
	    var map = new BMap.Map("allmap",{minZoom:12,maxZoom:20});            // 创建Map实例  
	    //map.centerAndZoom(new BMap.Point(116.4035,39.915),15);  //初始化时，即可设置中心点和地图缩放级别。  
	    map.centerAndZoom("成都",13);                     // 初始化地图,设置中心点坐标和地图级别。  
	    map.enableScrollWheelZoom(true);//鼠标滑动轮子可以滚动  
	      
	    map.addEventListener("click", function(e){  
	        document.getElementById("r-result").innerHTML = e.point.lng + ", " + e.point.lat;  
	    });  

    </script>  
      
</body>  
</html>  
