<%@page import="java.sql.*"%>
<%@page import="com.sean.tools.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="CSS/index.css?ver=2.0">
<script type="text/javascript" src="JS/jquery.js"></script>
<script type="text/javascript" src="JS/jquery.SuperSlide.js"></script>
<script type="text/javascript" src="JS/AjaxRequest.js"></script>
<script type="text/javascript" src="lib/My97DatePicker/4.8/WdatePicker.js"></script> 

<title>实验室状态</title>
</head>
<body>
 <script type="text/javascript"> 
function submit_time(){
	
	var loader=new net.AjaxRequest("ExpRoomServlet?action=roomstate_lite&nocache="+new Date().getTime(),deal_submit_time,onerror,"POST");
}
//
//function onerror(){
	//alert("您的操作有误！");
//}
function deal_submit_time(){
	var h=this.req.responseText;
	document.getElementById("room_state").innerHTML=h;
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
    <p align="center" style="font-size:30px">实验室状态</p>
    <div class="time_picker">
    <form  name="form_time_picker" method="post" action="" > 
     <input align="center" name="start_time" type="text"    class="Wdate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm'})">
     -
      <input align="center" name="start_time" type="text"    class="Wdate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm'})">
      &nbsp
      <button type="button" onClick="submit_time()">确定</button>
      </form>
    </div>
 
    <div id="room_state" class="img_bar">

</div>
     <div class="footer">
 		 <p><a href="index.jsp">首页</a> | <a href="">实验室状态</a> | <a href="">学生登录</a> | <a href="template/tourism.html">教师登录</a> | <a href="">联系我们</a></p>

			<p>Copyright &copy; 2017－2018 All Rights Reserved 版权所有 XXXXXXXXXXXXXXXXXX</p>
		  <p>地址：辽宁省沈阳市皇姑区崇山中路66号 </p>
   	 </div>


</div>

</body>
</html>
