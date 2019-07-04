
<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<meta http-equiv="Cache-Control" content="no-siteapp" />
<script type="text/javascript" src="../lib/My97DatePicker/4.8/WdatePicker.js"></script>
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
<!--/meta 作为公共模版分离出去-->

<title>开设实验</title>
</head>
<body onbeforeunload="return '真的要关闭此窗口吗?'">
<script language="javascript">
function setExpCourse(){
	if(confirm("确定提交吗？")){
		var param=""; 
		//alert(expadd_form.title.value);
		//alert(expadd_form.exptime.value);
		//alert(expadd_form.address.value);
		//alert(expadd_form.content.value);
		//var my_checkbox = document.getElementsByName("ability");
		//var num=0;
		//for ( var i = my_checkbox.length-1; i >= 0; i--) { 
			//if (my_checkbox[i].checked) { 
			///	num+=Math.pow(10,5-i);
			//}
		//}
		//var ability=new String(num);
		//for(var i=0;ability.length<6;i++)
			//ability="0"+ability;
		if(expadd_form.title.value==""){
			alert("标题不可为空！");
			return;
		}
		if(expadd_form.exptime.value==""){
			alert("实验时间不可为空！");
			return;
		}
		if(expadd_form.address.value=="null"){
			alert("请选择实验室地址！");
			return;
		}
		if(expadd_form.admins.value=="null"){
			alert("请选择审核的管理员！");
			return;
		}
/*		if(ability=="000000"){
			alert("请至少选择一项考查能力！");
			return;
		}*/
		param+="title="+expadd_form.title.value+"&exptime="+expadd_form.exptime.value+"&erId="+expadd_form.address.value+"&aid="+expadd_form.admins.value+"&content="+expadd_form.content.value;
		var loader=new net.AjaxRequest("../ExpCourseServlet?action=insert_newcourse&nocache="+new Date().getTime(),deal_setExpCourse,onerror,"POST",encodeURI(param));
	}
}
function deal_setExpCourse(){
	/*****************显示提示信息******************************/
	var h=this.req.responseText;
	alert(h);
}
function getERAddress(){
	var param=""; 
	var loader=new net.AjaxRequest("../ExpRoomServlet?action=address_for_option&nocache="+new Date().getTime(),deal_getERAddress,onerror,"POST",encodeURI(param));
}
function getAdmins(){
	var param="";
	var loader=new net.AjaxRequest("../AdministratorServlet?action=admin_for_option&nocache="+new Date().getTime(),deal_getAdmins,onerror,"POST",encodeURI(param));
}
function onerror(){
	alert("您的操作有误！");
}
function deal_getERAddress(){
	/*****************显示提示信息******************************/
	var h=this.req.responseText;
	document.getElementById("eraddress").innerHTML=h;
}

function deal_getAdmins(){
	/*****************显示提示信息******************************/
	var h=this.req.responseText;
	document.getElementById("admins").innerHTML=h;
}
window.onload=function(){
	getERAddress();
	getAdmins();
//	window.setInterval(getExpApplication(), 120000);
}
</script>
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页
	<span class="c-gray en">&gt;</span>
	开设实验

	<a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a>
</nav>
<div class="page-container">
	<form class="form form-horizontal" id="form-add" name="expadd_form">
		<div id="tab-system" class="HuiTab">
			<div class="tabBar cl">
				<span>填写实验信息</span>
			
			</div>

			<div class="tabCon">
				<div class="row cl">
					<label class="form-label col-xs-4 col-sm-2">
						<span class="c-red">*</span>
						实验标题：
					</label>
					<div class="formControls col-xs-8 col-sm-9">
						<input type="text" id="website-title" name="title" placeholder="控制在25个字、50个字节以内" value="" class="input-text">
					</div>
				</div>

				<div class="row cl">
					<label class="form-label col-xs-4 col-sm-2">
						<span class="c-red">*</span>
						实验时间：</label>
<div class="formControls col-xs-8 col-sm-9">
				<input type="text" onfocus="WdatePicker({skin:'whyGreen',minDate:'%y-%M-%d',dateFmt: 'yyyy-MM-dd HH:mm:ss'})" id="date" name="exptime" class="input-text Wdate" style="width:200px;">
</div>
<!--
</div>

				<div class="row cl">
					<label class="form-label col-xs-4 col-sm-2">
						<span class="c-red">*</span>
						课程文件：</label>
<div class="formControls col-xs-8 col-sm-9">
				<input type="text" class="input-text" style="width:200px;"> 
					<button  class="btn btn-default radius" type="button">&nbsp;&nbsp;浏&nbsp;&nbsp;览&nbsp;&nbsp;</button>
					<button  class="btn btn-default radius" type="button">&nbsp;&nbsp;上&nbsp;&nbsp;传&nbsp;&nbsp;</button>
</div>
-->
</div>

				<div class="row cl">
					<label class="form-label col-xs-4 col-sm-2">
						<span class="c-red">*</span>
						 实验室地址：</label>
					<div class="formControls col-xs-8 col-sm-9">
								<select id="eraddress" name="address"> 
								<option value="null" selected="selected">请选择...</option>
								</select>
					<i class="icon_check" id="icon_check"> </i>
					</div>
				</div>

				<div class="row cl">
					<label class="form-label col-xs-4 col-sm-2">
						<span class="c-red">*</span>
						审核管理员：</label>
					<div class="formControls col-xs-8 col-sm-9">
						<select id="admins" name="admins">
							<option value="null" selected="selected">请选择...</option>
						</select>
						<i class="icon_check" id="icon_check_admin"> </i>
					</div>
				</div>


				<div class="row cl">
					<label class="form-label col-xs-4 col-sm-2">	<span class="c-red">*</span>实验内容：</label>
					<div class="formControls col-xs-8 col-sm-9">
						<textarea class="textarea" name="content"></textarea>
					</div>
				</div>
<!--
				<div class="row cl">
					<label class="form-label col-xs-4 col-sm-2">	<span class="c-red">*</span>能力考查：</label>
					<div class="formControls col-xs-8 col-sm-9">
						<input type="checkbox" name="ability" value="1">领导能力
						<input type="checkbox" name="ability" value="2">团队协作能力
						<input type="checkbox" name="ability" value="3">语言理解能力
						<input type="checkbox" name="ability" value="4">实践能力
						<input type="checkbox" name="ability" value="5">分析能力
						<input type="checkbox" name="ability" value="6">创新能力					
				</div>
-->
				<div class="row cl">
					<div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-2">
						<button onClick="setExpCourse();" class="btn btn-primary radius" type="submit"><i class="Hui-iconfont">&#xe632;</i> 申请开设</button>
				<button onClick="layer_close();" class="btn btn-default radius" type="button">&nbsp;&nbsp;取&nbsp;&nbsp;消&nbsp;&nbsp;</button>
					</div>

				</div>
		</div>
	</div>
	</form>

</div>

<!--_footer 作为公共模版分离出去-->
<script type="text/javascript" src="../lib/jquery/1.9.1/jquery.min.js"></script>
<script type="text/javascript" src="../lib/layer/2.4/layer.js"></script>
<script type="text/javascript" src="../static/h-ui/js/H-ui.min.js"></script>
<script type="text/javascript" src="../static/h-ui.admin/js/H-ui.admin.js"></script> 
<!--/_footer 作为公共模版分离出去-->

<!--请在下方写此页面业务相关的脚本-->

<script type="text/javascript" src="../lib/datatables/1.10.0/jquery.dataTables.min.js"></script> 
<script type="text/javascript" src="../lib/laypage/1.2/laypage.js"></script> 
<script type="text/javascript" src="../lib/jquery.validation/1.14.0/jquery.validate.js"></script>
<script type="text/javascript" src="../lib/jquery.validation/1.14.0/validate-methods.js"></script>




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

function layer_close(){
	if(confirm("您确定要关闭本页吗？")){
	window.opener=null;
	window.open('','_self');
	window.close();
	}
}
</script>
<!--/请在上方写此页面业务相关的脚本-->
</body>
</html>
