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
<!--[if IE 6]>
<script type="text/javascript" src="../lib/DD_belatedPNG_0.0.8a-min.js" ></script>
<script>DD_belatedPNG.fix('*');</script>
<![endif]-->
<title>实验课程</title>
</head>
<body>
<script language="javascript">
var cur_page=1;
function getExpCourse()
{

	var param="cur_page="+cur_page; 

	var loader=new net.AjaxRequest("../TeacherServlet?action=ask_applied&nocache="+new Date().getTime(),deal_getExpCourse,onerror,"POST",encodeURI(param));
	
}

function onerror(){
	alert("您的操作有误！");
}

function deal_getExpCourse(){
	/*****************显示提示信息******************************/
	var h=this.req.responseText;
	var k="共有数据：<strong> "+h.substr(-1)+"</strong> 条</span>";
	var j=h.substr(0,h.length-1);
	document.getElementById("count_num").innerHTML=k;
	document.getElementById("applied").innerHTML=j;
	
}
function goto_page(page){
	cur_page=page;
	getExpCourse();
}
window.onload=function(){
	getExpCourse();
//	window.setInterval(getExpApplication(), 120000);
}
</script>
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 预约实验 <span class="c-gray en">&gt;</span>实验课程 <a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a></nav>
<div class="page-container">
	<div class="text-c">
		
	
	<div class="cl pd-5 bg-1 bk-gray mt-20" id="count_num">  </div>
	<div class="mt-20">
		<table class="table table-border table-bordered table-bg table-hover table-sort table-responsive">
			<thead>
				<tr class="text-c">
				
					<th width="80">课程ID</th>
					<th>标题</th>
					<th width="100">实验室地址</th>
					<th width="140">实验时间</th>
					<th width="80">是否同意开设</th>
					
				</tr>
			</thead>
			<tbody id="applied">
			
				
			</tbody>
		</table>
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
	  {"orderable":false,"aTargets":[4]}// 不参与排序的列
	]
});
</script> 
</body>
</html>