<%@page import="java.sql.*"%>
<%@page import="com.sean.tools.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>辽宁大学实验室管理系统</title>
<link rel="stylesheet" type="text/css" href="CSS/index.css?ver=1">
<script type="text/javascript" src="JS/jquery.js"></script>
<script type="text/javascript" src="JS/jquery.SuperSlide.js"></script>
<script type="text/javascript" src="JS/AjaxRequest.js"></script>
</head>
<body>
<script language="javascript">

var cur_page =1;
function getExpCourse()
{
	var param="cur_page="+cur_page; 	
	var loader=new net.AjaxRequest("ExpCourseServlet?action=show&nocache="+new Date().getTime(),deal_getExpCourse,onerror,"POST",encodeURI(param));
}
function getNotification(){
	var param="cur_page="+cur_page; 	
	var loader=new net.AjaxRequest("NotificationServlet?action=show&nocache="+new Date().getTime(),deal_getNotification,onerror,"POST",encodeURI(param));
	
}
function onerror(){
	alert("您的操作有误！");
}


function deal_getNotification(){
	var h=this.req.responseText;
	document.getElementById("notify").innerHTML=h;
	
}
function deal_getExpCourse(){
	/*****************显示提示信息******************************/
	var h=this.req.responseText;
	document.getElementById("expcourse").innerHTML=h;
	
}
window.onload=function(){
	//getExpCourse();
	//getNotification();
	window.setInterval(getExpCourse(), 600000);//十分钟更新一次
	window.setInterval(getNotification(), 120000);//两分钟更新一次

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
		</div>
    </div> 
  
    <div class="center">
    <!--banner开始-->
    	<div class="banner">
    	  <div class="bd">

                <ul>
                    <li style="background:url(images/banner_1.jpg) no-repeat center top;"></li>
                    <li style="background:url(images/banner_2.jpg) no-repeat center top;"></li>
                </ul>
            </div>
            <div class="hd">
                <ul>
                   <li></li>
                   <li></li>
                </ul>
            </div>

            <a class="prev" href="javascript:void(0)"></a>

            <a class="next" href="javascript:void(0)"></a>

        </div>
		 <!--调用JS模块图片滚动--> 
        <script type="text/javascript">jQuery(".banner").slide({ titCell:".hd ul", mainCell:".bd ul", effect:"fold",delayTime:1000,interTime:5000,autoPlay:true, autoPage:true, trigger:"click" });</script>

		<!--banner结束--> 
    	
    	</div>  
    	<div class="split_line_h" ></div>
    	<div class="content">
    		<div class="lesson">
    		<p align="center">实验室最新课程</p>
    		<div class="split_line_h" ></div>
    		<ul id="expcourse">
    		
    		</ul>
    		</div>
    		<div id="narrow"></div>
    		<div class="notification">
    		<p align="center">通知与公告</p>
    		<div class="split_line_h" ></div>
    		<ul id="notify">
    		</ul>
    		</div>
    	</div>  
		<p>&nbsp</p>
		<div class="split_line_h" ></div>
   		 <div class="footer">
 		 <p><a href="index.jsp">首页</a> | <a href="">实验室状态</a> | <a href="">学生登录</a> | <a href="template/tourism.html">教师登录</a><a href="Administrator/adminLogin.jsp">管理员登录</a> | <a href="">联系我们</a></p>

			<p>Copyright &copy; 2017－2018 All Rights Reserved 版权所有 XXXXXXXXXXXXXXXXXX</p>
		  <p>地址：辽宁省沈阳市皇姑区崇山中路66号 </p>
   	 </div>


</div>
</body>
</html>