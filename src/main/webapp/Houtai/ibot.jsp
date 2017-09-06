<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <!-- 日期相关函数 -->
	<script src="/SSM/js/datejisuan.js"></script>
     <div class="chat-panel panel panel-default">
         <div class="panel-heading">
             <i class="fa fa-comments fa-fw"></i> Chat
             <div class="btn-group pull-right">
                 <button type="button" class="btn btn-default btn-xs dropdown-toggle" data-toggle="dropdown">
                     <i class="fa fa-chevron-down"></i>
                 </button>
                 <ul class="dropdown-menu slidedown">
                     <li>
                         <a href="#">
                             <i class="fa fa-refresh fa-fw"></i> Refresh
                         </a>
                     </li>
                     <li>
                         <a href="#">
                             <i class="fa fa-check-circle fa-fw"></i> Available
                         </a>
                     </li>
                     <li>
                         <a href="#">
                             <i class="fa fa-times fa-fw"></i> Busy
                         </a>
                     </li>
                     <li>
                         <a href="#">
                             <i class="fa fa-clock-o fa-fw"></i> Away
                         </a>
                     </li>
                     <li class="divider"></li>
                     <li>
                         <a href="#">
                             <i class="fa fa-sign-out fa-fw"></i> Sign Out
                         </a>
                     </li>
                 </ul>
             </div>
         </div>
         <!-- /.panel-heading -->
         <div class="panel-body" id="chatpanel">
             <ul class="chat" id="showxiaoxi" >
                 <li class="right clearfix">
                     <span class="chat-img pull-right">
                         <img src="/SSM/imgs/touxiang.png" alt="User Avatar" class="img-circle" hight="50px" width="50px"/>
                     </span>
                     <div class="chat-body clearfix">
                         <div class="header">
                             <strong class="primary-font">墨染琉璃殇</strong>
                             <small class="pull-left text-muted">
                                 <i class="fa fa-clock-o fa-fw"></i><%=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())%>
                             </small>
                         </div>
                         <p>
                         	这里是墨染琉璃殇
                         </p>
                     </div> 
                 </li>
             </ul>
         </div>
         <!-- /.panel-body -->
         <div class="panel-footer">
             <div class="input-group">
                 <input id="xiaoxi" type="text" class="form-control input-sm" placeholder="Type your message here..." />
                 <span class="input-group-btn">
                     <button class="btn btn-warning btn-sm" id="btn-chat">
                         Send
                     </button>
                 </span>
             </div>
         </div>
         <!-- /.panel-footer -->
     </div>
     <!-- /.panel .chat-panel -->
