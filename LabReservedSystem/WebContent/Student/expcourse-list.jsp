<!DOCTYPE HTML>
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
<title>实验课程</title>
<script type="text/javascript" src="../JS/AjaxRequest.js"></script>
</head>
<body>
<script language="javascript">
/**
 *对Date的扩展，将 Date 转化为指定格式的String
 *月(M)、日(d)、小时(h)、分(m)、秒(s)、季度(q) 可以用 1-2 个占位符，
 *年(y)可以用 1-4 个占位符，毫秒(S)只能用 1 个占位符(是 1-3 位的数字)
 *例子：
 *(new Date()).Format("yyyy-MM-dd hh:mm:ss.S") ==> 2006-07-02 08:09:04.423
 *(new Date()).Format("yyyy-M-d h:m:s.S")      ==> 2006-7-2 8:9:4.18
 */
Date.prototype.format = function (fmt) {
    var o = {
        "M+": this.getMonth() + 1, //月份
        "d+": this.getDate(), //日
        "h+": this.getHours(), //小时
        "m+": this.getMinutes(), //分
        "s+": this.getSeconds(), //秒
        "q+": Math.floor((this.getMonth() + 3) / 3), //季度
        "S": this.getMilliseconds() //毫秒
    };
    if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var k in o)
        if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
    return fmt;
}
function find_course()
{	
	var department=document.getElementById("department").value;
	if(department=="0"){
		alert("请选择学院！");
		return;
	}

	var from_date=document.getElementById("from_date").value;
	var to_date=document.getElementById("to_date").value;

	if(from_date==""||to_date==""){
		var now=new Date()
		from_date="1970-01-01";
		//to_date="2018-8-22"
		now.setDate(now.getDate()+1);
		to_date=now.format("yyyy-MM-dd");
	}

	var keyword=document.getElementById("keyword").value;;
	var param="department="+department+"&from_date="+from_date+"&to_date="+to_date+"&keyword="+keyword; 	//将登录信息连接成字符串，作为发送请求的参数
	var loader=new net.AjaxRequest("../ExpCourseServlet?action=find_course&nocache="+new Date().getTime(),deal_find_course,onerror,"POST",encodeURI(param));
}

function onerror(){
	alert("您的操作有误！");
}

function deal_find_course(){
	/*****************显示提示信息******************************/
	var h=this.req.responseText;
	var k="共有数据：<strong> "+h.substr(-1)+"</strong> 条</span>";
	var j=h.substr(0,h.length-1);
	document.getElementById("count_num").innerHTML=k;
	document.getElementById("course").innerHTML=j;
}


</script>
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 预约实验 <span class="c-gray en">&gt;</span>实验课程 <a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a></nav>
<div class="page-container">
	<div class="text-c">
		
	 <span class="select-box inline">
		<select name="" id="department" class="select">
			<option value="0">所属学院</option>
			<option value="文学院">文学院</option>
			<option value="历史学院">历史学院</option>
			<option value="哲学与公共管理学院">哲学与公共管理学院</option>
			<option value="经济学院">经济学院</option>
			<option value="商学院">商学院</option>
			<option value="国际关系学院">国际关系学院</option>
			<option value="法学院">法学院</option>
			<option value="外国语学院">外国语学院</option>
			<option value="数学院">数学院</option>
			<option value="物理学院">物理学院</option>
			<option value="信息学院">信息学院</option>
			<option value="化学院">化学院</option>
			<option value="生命科学院">生命科学院</option>
			<option value="环境学院">环境学院</option>
			<option value="公共基础学院">公共基础学院</option>			
			<option value="马克思主义学院">马克思主义学院</option>
			<option value="体育教研部">体育教研部</option>
			<option value="人口所">人口所</option>
			<option value="计算中心">计算中心</option>
			<option value="广播影视学院">广播影视学院</option>
			<option value="留学生院">留学生院</option>
			<option value="艺术学院">艺术学院</option>
			<option value="新华国际商学院">新华国际商学院</option>
			<option value="亚澳商学院">亚澳商学院</option>
			<option value="轻型产业学院">轻型产业学院</option>
			<option value="药学院">药学院</option>
			<option value="汉语国际教育学院">汉语国际教育学院</option>
			<option value="新闻传播学院">新闻传播学院</option>
		</select>
		</span> 实验时间：
		<input type="text" onfocus="WdatePicker({ maxDate:'#F{$dp.$D(\'from_date\')||\'%y-%M-%d\'}' })" id="from_date" class="input-text Wdate" style="width:120px;">
		-
		<input type="text" onfocus="WdatePicker({ minDate:'#F{$dp.$D(\'to_date\')}',maxDate:'%y-%M-%d' })" id="to_date" class="input-text Wdate" style="width:120px;">
		<input type="text" name="" id="keyword" placeholder="课程名称" style="width:250px" class="input-text">
		<button name="" id="" class="btn btn-success" type="submit" onClick="find_course();" ><i class="Hui-iconfont">&#xe665;</i> 查找实验</button>
	</div>
	<div class="cl pd-5 bg-1 bk-gray mt-20"> 
	 <span><a href="javascript:;" onclick="application_add()" class="btn btn-primary radius"><i class="Hui-iconfont">&#xe600;</i> 申请预约</a></span> 
	<span id="count_num" class="r"></span></div>
	<div class="mt-20" id="course">
		
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
$('.table-sort').dataTable({
	"aaSorting": [[ 1, "desc" ]],//默认第几个排序
	"bStateSave": true,//状态保存
	"pading":false,
	"aoColumnDefs": [
	  //{"bVisible": false, "aTargets": [ 3 ]} //控制列的隐藏显示
	  {"orderable":false,"aTargets":[6]}// 不参与排序的列
	]
});
function application_add(){
	var param="cid=";
	var temp="";
	var my_checkbox = document.getElementsByName("selected");
	for ( var i = 0; i < my_checkbox.length; i++) { 
		if (my_checkbox[i].checked) { 
		temp = my_checkbox[i].value;
		param = param + "," +temp; 
		}
	}
	var loader=new net.AjaxRequest("../ExpCourseServlet?action=apply_course&nocache="+new Date().getTime(),deal_application_add,onerror,"POST",encodeURI(param));
}

function onerror(){
	alert("您的操作有误！");
}

function deal_application_add(){
	/*****************显示提示信息******************************/
	var h=this.req.responseText;
	alert(h);
	find_course();
}
</script> 
</body>
</html>