	$(function(){
		$("#btn-chat").click(function(){
			var date = new Date($.ajax({async: false}).getResponseHeader("Date"));
			var bombay = date + (3600000 * 8);
			var time = new Date(bombay);
			time = new Date().format("yyyy-MM-dd hh:mm:ss");
			$("#showxiaoxi").append('<li class="left clearfix"><span class="chat-img pull-left"><img src="http://placehold.it/50/55C1E7/fff" alt="User Avatar" class="img-circle" /></span><div class="chat-body clearfix"><div class="header"><strong class="primary-font">I</strong><small class="pull-right text-muted"><i class="fa fa-clock-o fa-fw"></i>'+time+'</small></div><p>'+$("#xiaoxi").val()+'</p></div></li>');
			$('#chatpanel').scrollTop( $('#chatpanel')[0].scrollHeight );
			$.ajax({
				url:"/SSM/Ibot/getmsg.do",
				data:{xiaoxi:$("#xiaoxi").val()},
				dataType:"json",
				success:function(result){
					var result1 ="";
					result1+='<li class="right clearfix"><span class="chat-img pull-right"><img src="/SSM/imgs/touxiang.png" alt="User Avatar" class="img-circle" hight="50px" width="50px"/></span><div class="chat-body clearfix"><div class="header"><small class=" text-muted"><i class="fa fa-clock-o fa-fw"></i>'+result.time+'</small><strong class="pull-right primary-font">墨染琉璃殇</strong></div><p>'+result.text;
					if(result.url!=undefined){
						result1 +="<p><a href='"+result.url+"'>详细信息</a></p>"
						}
					if(result.list!=undefined){
						var lists = result.list;
						$.each(lists,function(index){
							result1+=lists[index].article;
							result1+=lists[index].source;
							result1+=lists[index].icon;
							result1+=lists[index].detailurl;
						});
					}
					result1 +=('</p></div></li>');
					$("#showxiaoxi").append(result1);
					$('#chatpanel').scrollTop( $('#chatpanel')[0].scrollHeight );
				}
			});
			$("#xiaoxi").val("");
		});	
	});