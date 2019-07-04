<!DOCTYPE HTML>
<%@   page   import= "com.sean.* "%>
<html>
<head>
<meta charset="utf-8">
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<meta http-equiv="Cache-Control" content="no-siteapp" />
<!--[if lt IE 9]>
<script type="text/javascript" src="../lib/html5shiv.js"></script>
<script type="text/javascript" src="../lib/respond.min.js"></script>
<![endif]-->
<link rel="stylesheet" type="text/css" href="../static/h-ui/css/H-ui.min.css" />
<link rel="stylesheet" type="text/css" href="../static/h-ui.admin/css/H-ui.admin.css" />
<link rel="stylesheet" type="text/css" href="../lib/Hui-iconfont/1.0.8/iconfont.css" />

<!--[if IE 6]>
<script type="text/javascript" src="../lib/DD_belatedPNG_0.0.8a-min.js" ></script>
<script>DD_belatedPNG.fix('*');</script>
<![endif]-->
<title>欢迎使用</title>
</head>
<body>
<p class="f-20 text-success" style="text-align: center;margin:30px;">欢迎使用辽宁大学创新实验室系统教师端！</p>
	<p style="margin:20px">上次登录IP：<%=session.getAttribute("last_ip") %>
 </br></br>上次登录时间：<%=session.getAttribute("last_date") %></p>
<div class="page-container">
您只有30分钟的使用时间，请节约时间，超时需重新登录。
</div>
</body>
</html>