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
<title></title>
</head>
<body>
<script type="text/javascript"> 
function match(){
	var param="";
	var loader=new net.AjaxRequest("../StudentServlet?action=match&nocache="+new Date().getTime(),deal_match,onerror,"POST",encodeURI(param));

 }
function onerror(){
		alert("您的操作有误！");
	}
function deal_match(){
	/*****************显示提示信息******************************/
	var h=this.req.responseText;

	document.getElementById("teammate").innerHTML=h;
		
}
window.onload=function(){
	 match();
//		window.setInterval(getExpApplication(), 120000);

}
</script>
<div style="text-align: center">
	<table class="table table-border table-bordered table-bg">
	<thead>
	<tr class="text-c">
			<th width="20">排序</th>
			<th width="30">姓名</th>
			<th width="20">性别</th>
			<th width="25">年级</th>
			<th width="40">学院</th>
			<th width="50">综合推荐度</th>
			<th width="120">联系方式</th>	
				</tr>
			</thead>
			<tbody id="teammate">
		
			
			</tbody>
		</table>

</div>
<!--_footer ÃÃ·ÃÂªÂ¹Â«Â¹Â²ÃÂ£Â°Ã¦Â·ÃÃÃ«Â³Ã¶ÃÂ¥-->
<script type="text/javascript" src="../lib/jquery/1.9.1/jquery.min.js"></script>
<script type="text/javascript" src="../lib/layer/2.4/layer.js"></script>
<script type="text/javascript" src="../static/h-ui/js/H-ui.min.js"></script>
<script type="text/javascript" src="../static/h-ui.admin/js/H-ui.admin.js"></script> 
<!--/_footer ÃÃ·ÃÂªÂ¹Â«Â¹Â²ÃÂ£Â°Ã¦Â·ÃÃÃ«Â³Ã¶ÃÂ¥-->

</body>
</html>