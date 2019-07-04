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
<script language="javascript">
//获取url中的参数
function getQueryString() {  
  var qs = location.search.substr(1), // 获取url中"?"符后的字串   
    args = {}, // 保存参数数据的对象
    items = qs.length ? qs.split("&") : [],  // 取得每一个参数项,
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
function getExpRoomInfo(){
	var qs = getQueryString(); 
	var eraddress=qs["eraddress"]
	var param="eraddress="+eraddress; 	
	var loader=new net.AjaxRequest("../ExpRoomServlet?action=show&nocache="+new Date().getTime(),deal_getExpRoomInfo,onerror,"POST",encodeURI(param));

}
function onerror(){
	alert("您的操作有误！");
}
function deal_getExpRoomInfo(){
	var h=this.req.responseText;
	document.getElementById("info").innerHTML=h;
	
}

window.onload=function(){	
	getExpRoomInfo();
}

</script>
<title>实验室信息</title>
</head>
<body>
<div id="info">

</div>

<script type="text/javascript" src="../lib/jquery/1.9.1/jquery.min.js"></script>
<script type="text/javascript" src="../lib/layer/2.4/layer.js"></script>
<script type="text/javascript" src="../static/h-ui/js/H-ui.min.js"></script>
<script type="text/javascript" src="../static/h-ui.admin/js/H-ui.admin.js"></script> 

</body>
</html>