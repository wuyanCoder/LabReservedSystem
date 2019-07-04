<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<meta http-equiv="Cache-Control" content="no-siteapp" />
<script type="text/javascript" src="../JS/AjaxRequest.js"></script>
<!--[if lt IE 9]>
<script type="text/javascript" src="../lib/html5shiv.js"></script>
<script type="text/javascript" src="../lib/respond.min.js"></script>
<![endif]-->
<link rel="stylesheet" type="text/css" href="../static/h-ui/css/H-ui.min.css" />
<link rel="stylesheet" type="text/css" href="../static/h-ui.admin/css/H-ui.admin.css" />
<link rel="stylesheet" type="text/css" href="../lib/Hui-iconfont/1.0.8/iconfont.css" />
<link rel="stylesheet" type="text/css" href="../static/h-ui.admin/skin/default/skin.css" id="skin" />
<!--[if IE 6]>
<script type="text/javascript" src="../lib/DD_belatedPNG_0.0.8a-min.js" ></script>
<script>DD_belatedPNG.fix('*');</script>
<![endif]-->
<title>匹配实验队友</title>
</head>
<body>
<script type="text/javascript"> 
function getAppliedCourseName(){
	var param="";
	var loader=new net.AjaxRequest("../StudentServlet?action=get_appliedcoursename&nocache="+new Date().getTime(),deal_getAppliedCourseName,onerror,"POST",encodeURI(param));
 }
function onerror(){
		alert("您的操作有误！");
	}
function deal_getAppliedCourseName(){
	/*****************显示提示信息******************************/
	var h=this.req.responseText;
	document.getElementById("course").innerHTML=h;		
}
window.onload=function(){
	 getAppliedCourseName();
//		window.setInterval(getExpApplication(), 120000);
}
</script>
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span>  匹配实验队友 <span class="c-gray en">&gt;</span> 匹配实验队友 <a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a></nav>
<div class="page-container">
	<div class="text-c">
	<div class="cl pd-5 bg-1 bk-gray mt-20">
	请选择你需要参加的实验，我们将为您匹配队友
	</div>
	<span class="select-box inline">
		<select id="course" class="select">	
		</select>
	</span>
		<button id="show" type="submit" class="btn btn-success radius" onClick="teammate_show('match_result.jsp','900','540');"><i class="Hui-iconfont">&#xe665;</i> 确定实验课程</button>
	</div>
</div>
<!--_footer 作为公共模版分离出去-->
<script type="text/javascript" src="../lib/jquery/1.9.1/jquery.min.js"></script> 
<script type="text/javascript" src="../lib/layer/2.4/layer.js"></script>
<script type="text/javascript" src="../static/h-ui/js/H-ui.min.js"></script> 
<script type="text/javascript" src="../static/h-ui.admin/js/H-ui.admin.js"></script> <!--/_footer 作为公共模版分离出去-->
<!--请在下方写此页面业务相关的脚本-->
<script type="text/javascript" src="../lib/My97DatePicker/4.8/WdatePicker.js"></script> 
<script type="text/javascript" src="../lib/datatables/1.10.0/jquery.dataTables.min.js"></script> 
<script type="text/javascript" src="../lib/laypage/1.2/laypage.js"></script>
<script type="text/javascript">
function teammate_show(url,w,h){
	var title="匹配结果";
	layer_show(title,url,w,h);
	
}
</script>
</body>
</html>