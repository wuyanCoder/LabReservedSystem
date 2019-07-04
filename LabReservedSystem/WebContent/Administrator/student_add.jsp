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
	学生管理
	<span class="c-gray en">&gt;</span>
	添加学生
	<a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a>
</nav>

<div class="page-container">
	<form class="form form-horizontal" name="form-change-pwd" method="post" action="${pageContext.request.contextPath}/student/insert" > 
		<div id="tab-system" class="HuiTab">
			<div class="tabBar cl">
				<span>添加学生</span>
			</div>
			<div class="tabCon">
				<div class="row cl">
					<label class="form-label col-xs-4 col-sm-2">
						<span class="c-red">*</span>
						学号：</label>
					<div class="formControls col-xs-8 col-sm-9">
						<input type="text" name="studentNumber" placeholder="请输入学号"  class="input-text">
					</div>
				</div>
				<div class="row cl">
					<label class="form-label col-xs-4 col-sm-2">
						<span class="c-red">*</span>
						密码：</label>
					<div class="formControls col-xs-8 col-sm-9">
						<input type="text" name="password" placeholder="请输入密码"  class="input-text">
					</div>
				</div>
				<div class="row cl">
					<label class="form-label col-xs-4 col-sm-2">
						<span class="c-red">*</span>
						姓名：</label>
					<div class="formControls col-xs-8 col-sm-9">
						<input type="text" name="name" placeholder="请输入姓名"  class="input-text">
					</div>
				</div>
				<div class="row cl">
					<label class="form-label col-xs-4 col-sm-2">
						<span class="c-red">*</span>
						性别：</label>
					<div class="formControls col-xs-8 col-sm-9">
					    <select class="form-control" name="sex" >
								<option value="男">男</option>
								<option value="女">女</option>
						</select>
					</div>
				</div>
				 
				<div class="row cl">
					<label class="form-label col-xs-4 col-sm-2">
						<span class="c-red">*</span>
						学院：</label>
					<div class="formControls col-xs-8 col-sm-9" >
						<!--  <input type="text" name="department" placeholder="请输入学院"  class="input-text">-->
					            
					            
					            <select class="form-control" name="department" >
					           

					            <option value=" 文学院"> 文学院</option>
								<option value="历史学院">历史学院</option>
								 <option value="哲学与公共管理学院"> 哲学与公共管理学院</option>
								<option value="经济学院">经济学院</option>
								 <option value="文学院"> 文学院</option>
								<option value="商学院">商学院</option>
								 <option value="国际关系学院"> 国际关系学院</option>
								<option value="外国语学院">外国语学院</option>
								 <option value="数学院"> 数学院</option>
								<option value="物理学院">物理学院</option>
								 <option value=" 信息学院"> 信息学院</option>
								<option value="化学院">化学院</option>
								 <option value=" 生命科学院">生命科学院</option>
								<option value="环境学院">环境学院</option>
								 <option value=" 公共基础学院"> 公共基础学院</option>
								<option value="马克思主义学院">马克思主义学院</option>
								 <option value=" 体育教研部"> 体育教研部</option>
								<option value="人口所">人口所</option>
								 <option value=" 计算中心"> 计算中心</option>
								<option value="广播影视学院">广播影视学院</option>
								 <option value=" 留学生院"> 留学生院</option>
								<option value="艺术学院">艺术学院</option>
								 <option value=" 新华国际商学院">新华国际商学院</option>
								<option value="亚澳商学院">亚澳商学院</option>
								 <option value=" 轻型产业学院"> 轻型产业学院</option>
								<option value="药学院">药学院</option>
								 <option value=" 汉语国际教育学院">汉语国际教育学院</option>
								<option value="新闻传播学院">新闻传播学院</option>
								</select>
								
								
					</div>
				</div> 
				<div class="row cl">
					<label class="form-label col-xs-4 col-sm-2">
						<span class="c-red">*</span>
						班级：</label>
					<div class="formControls col-xs-8 col-sm-9">
						<input type="text" name="s_class" placeholder="请输入班级 "  class="input-text">
					</div>
				</div>
				<div class="row cl">
					<label class="form-label col-xs-4 col-sm-2">
						<span class="c-red">*</span>
						电话：</label>
					<div class="formControls col-xs-8 col-sm-9">
						<input type="text" name="telnum" placeholder="请输入电话 "  class="input-text">
					</div>
				</div>
		
		
		</div>
		<div class="row cl">
			<div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-2">
				<button  class="btn btn-primary radius" type="submit"><i class="Hui-iconfont">&#xe632;</i> 确定</button>
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

function onerror(){
	alert("您的操作有误！");
}


</script>
<!--/请在上方写此页面业务相关的脚本-->
</body>
</html>
