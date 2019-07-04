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
<title>实验批阅</title>
</head>
<body>
<script language="javascript">
var cur_page=1;
function checkExpcourse()
{	
	var param="cur_page="+cur_page; 
	var loader=new net.AjaxRequest("../TeacherServlet?action=check_expcourse&nocache="+new Date().getTime(),deal_checkExpcourse,onerror,"POST",encodeURI(param));
}
function onerror(){
	alert("您的操作有误！");
}
function deal_checkExpcourse(){
	/*****************显示提示信息******************************/
	var h=this.req.responseText;

	document.getElementById("course").innerHTML=h;
	
}
function goto_page(page){
	cur_page=page;
	checkExpcourse()
}
window.onload=function(){
	checkExpcourse()
//	window.setInterval(getExpApplication(), 120000);

}
</script>
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span>  实验管理<span class="c-gray en">&gt;</span> 实验批阅 <a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a></nav>
<div class="page-container">
	<div class="text-c">
	<span>
		<input type="text" class="input-text" style="width:250px" placeholder="输入学生学号" id="studentId" >
		-
		<input type="text" class="input-text" style="width:250px" placeholder="输入课程ID" id="studentId" >
		<button id="show" type="submit" class="btn btn-success radius" onClick=""><i class="Hui-iconfont">&#xe665;</i> 搜索</button>
	
	</span>
	</div>
</div>


<div class="mt-20">
		<table class="table table-border table-bordered table-hover table-bg table-sort">
			<thead>
				<tr class="text-c">
					<th width="50">课程ID</th>
					<th width="120">课程名称</th>
					<th width="60">学生姓名</th>
						<th width="70">学生学号</th>
					<th >实验内容</th>
					<th width="100">实验得分</th>
					<th width="40">操作</th>
				</tr>
			</thead>
			<tbody id="course">
				
			</tbody>
		</table>
	</div>


<!--_footer 作为公共模版分离出去-->
<script type="text/javascript" src="../lib/jquery/1.9.1/jquery.min.js"></script> 
<script type="text/javascript" src="../lib/layer/2.4/layer.js"></script>
<script type="text/javascript" src="../static/h-ui/js/H-ui.min.js"></script> 
<script type="text/javascript" src="../static/h-ui.admin/js/H-ui.admin.js"></script> <!--/_footer 作为公共模版分离出去-->

<!--请在下方写此页面业务相关的脚本-->

<script type="text/javascript" src="../lib/laypage/1.2/laypage.js"></script>
<script type="text/javascript">


function edit(url,w,h){
	layer_show("实验评分",url,w,h);
}
</script>
</body>
</html>