<!--_meta 作为公共模版分离出去-->
<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<meta http-equiv="Cache-Control" content="no-siteapp" />
<link rel="Bookmark" href="/favicon.ico" >
<link rel="Shortcut Icon" href="/favicon.ico" />
<script type="text/javascript" src="../JS/AjaxRequest.js"></script>
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
<!--/meta 作为公共模版分离出去-->
<title>修改密码</title>
</head>
<body>
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页
	<span class="c-gray en">&gt;</span>
	个人信息
	<span class="c-gray en">&gt;</span>
	修改密码
	<a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a>
</nav>

<div class="page-container">
	<form class="form form-horizontal" name="form-change-pwd" method="post" action="" > 
		<div id="tab-system" class="HuiTab">
			<div class="tabBar cl">
				<span>修改密码</span>
			</div>
			<div class="tabCon">
				<div class="row cl">
					<label class="form-label col-xs-4 col-sm-2">
						<span class="c-red">*</span>
						原密码：</label>
					<div class="formControls col-xs-8 col-sm-9">
						<input type="password" id="old_password" placeholder="请输入原密码" value="" class="input-text">
					</div>
				</div>
				<div class="row cl">
					<label class="form-label col-xs-4 col-sm-2">
						<span class="c-red">*</span>
						新密码：</label>
					<div class="formControls col-xs-8 col-sm-9">
						<input type="password" id="new_password" placeholder="请输入新密码" value="" class="input-text">
					</div>
				</div>
				<div class="row cl">
					<label class="form-label col-xs-4 col-sm-2">
						<span class="c-red">*</span>
						确认新密码：</label>
					<div class="formControls col-xs-8 col-sm-9">
						<input type="password" id="confirm_password" placeholder="请再次输入新密码" value="" class="input-text">
					</div>
				</div>
	
		
		</div>
		<div class="row cl">
			<div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-2">
				<button onClick="rectifySubmit();" class="btn btn-primary radius" type="button"><i class="Hui-iconfont">&#xe632;</i> 确定</button>
				<button class="btn btn-default radius" type="reset">&nbsp;&nbsp;重置&nbsp;&nbsp;</button>
			</div>
		</div>
	</form>
</div>

<!--_footer 作为公共模版分离出去-->
<script type="text/javascript" src="../lib/jquery/1.9.1/jquery.min.js"></script>
<script type="text/javascript" src="../lib/layer/2.4/layer.js"></script>
<script type="text/javascript" src="../static/h-ui/js/H-ui.min.js"></script>
<script type="text/javascript" src="../static/h-ui.admin/js/H-ui.admin.js"></script> <!--/_footer 作为公共模版分离出去-->

<!--请在下方写此页面业务相关的脚本-->
<script type="text/javascript" src="../lib/My97DatePicker/4.8/WdatePicker.js"></script>
<script type="text/javascript" src="../lib/jquery.validation/1.14.0/jquery.validate.js"></script>
<script type="text/javascript" src="../lib/jquery.validation/1.14.0/validate-methods.js"></script>
<script type="text/javascript" src="../lib/jquery.validation/1.14.0/messages_zh.js"></script>
<script type="text/javascript">
$(function(){
	$('.skin-minimal input').iCheck({
		checkboxClass: 'icheckbox-blue',
		radioClass: 'iradio-blue',
		increaseArea: '20%'
	});
	$("#tab-system").Huitab({
		index:0
	});
});
function rectifySubmit(){
	var old_password=document.getElementById("old_password").value;
	var new_password = document.getElementById("new_password").value;
	var confirm_password = document.getElementById("confirm_password").value;
	if(old_password== ''||new_password==''||confirm_password=='' ){
	   alert('密码不能为空');
	   return false;
 }
	if(new_password != confirm_password) {
	  alert("两次密码不同，请重新输入");
	  document.getElementById("old_password").value="";
		document.getElementById("new_password").value="";//清空密码文本框
		document.getElementById("confirm_password").value="";
		document.getElementById("old_password").focus();//获得焦点
	  return false; 
	  }

	var param="old_password="+old_password+"&new_password="+new_password; 	//将登录信息连接成字符串，作为发送请求的参数
	var loader=new net.AjaxRequest("../AdministratorServlet?action=change_password&nocache="+new Date().getTime(),deal_change_password,onerror,"POST",encodeURI(param));
}
function onerror(){
	alert("您的操作有误！");
}
function deal_change_password(){
	/*****************显示提示信息******************************/

	var h=this.req.responseText;
	h=h.replace(/\s/g,"");	//去除字符串中的Unicode空白
	alert(h);
	if(h=="修改成功！"){
		window.top.location.href="adminLogin.jsp";
	}else{
		document.getElementById("old_password").value="";
		document.getElementById("new_password").value="";//清空密码文本框
		document.getElementById("confirm_password").value="";
		document.getElementById("old_password").focus();//获得焦点
	}
	
}

</script>
<!--/请在上方写此页面业务相关的脚本-->
</body>
</html>
