<%@page import="java.sql.*"%>
<%@page import="com.sean.tools.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="CSS/index.css">
<script type="text/javascript" src="JS/jquery.js"></script>
<script type="text/javascript" src="JS/jquery.SuperSlide.js"></script>
<script type="text/javascript" src="JS/AjaxRequest.js"></script>
<title>通知公告</title>
</head>
<body>
<script language="javascript">
var cur_page;
//获取url中的参数
function getQueryString() {  
  var qs = location.search.substr(1), // 获取url中"?"符后的字串  
    args = {}, // 保存参数数据的对象
    items = qs.length ? qs.split("&") : [], // 取得每一个参数项,
    item = null,
    len = items.length;
	
  if(len==0) return null;
  for(var i = 0; i < len; i++) {
    item = items[i].split("=");
    var name = decodeURIComponent(item[0]),
      value = decodeURIComponent(item[1]);
    if(name) {
      args[name] = value;
    }
  }
  return args;
}
function getNotification(){
	var qs = getQueryString(); 
	if(qs==null) cur_page=1;
	else var cur_page = qs["cur_page"];
	var param="cur_page="+cur_page; 	
	var loader=new net.AjaxRequest("NotificationServlet?action=display&nocache="+new Date().getTime(),deal_getNotification,onerror,"POST",encodeURI(param));
	
}
function onerror(){
	alert("您的操作有误！");
}
function deal_getNotification(){
	var h=this.req.responseText;
	document.getElementById("notify").innerHTML=h;
	
}

window.onload=function(){	
	getNotification();
}

</script>
<div class="whole">
	<div class="top">  
		<div class="logo">
		<a href="index.jsp"><img alt="辽宁大学" src="images/logo.jpg"></a></div>
		<div class="nav">
		<ul>
			<li><a href="index.jsp">首页</a></li>
			<li><a href="roomState.jsp">实验室状态</a></li>
			<li><a href="notificationShow.jsp">通知公告</a></li>
			<li><a href="Student/studentLogin.jsp">学生登录</a></li>
			<li><a href="Teacher/teacherLogin.jsp">教师登录</a></li>
			<li><a href="Administrator/adminLogin.jsp">管理员登录</a></li></ul>
		</ul>
		</div>
    </div> 
    <p align="center" style="font-size:30px">通知与公告</p>
    <div class="content">
 		<div class="note_detail">
 		
 			<ul id="notify">
 			
    		</ul>
 		</div>
 		<nav>
  		
</nav>
    </div>
     <div class="footer">
 		 <p><a href="index.jsp">首页</a> | <a href="">实验室状态</a> | <a href="">学生登录</a> | <a href="template/tourism.html">教师登录</a> | <a href="">联系我们</a></p>

			<p>Copyright &copy; 2017－2018 All Rights Reserved 版权所有 XXXXXXXXXXXXXXXXXX</p>
		  <p>地址：辽宁省沈阳市皇姑区崇山中路66号 </p>
   	 </div>


</div>
    
</body>
</html>
