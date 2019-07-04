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
<title>实验评分</title>
</head>
<body>
<%String sid = request.getParameter("sid"); %>
<%String cid = request.getParameter("cid"); %>
<script language="javascript">
function editGrade(){
	if(confirm("确定提交吗？")){
		//var param=""; 
		var form = document.getElementById('edit-grade');
		if(form.score.value=="" || form.leadership.value==""|| form.teamwork.value==""
				|| form.apprehension.value==""|| form.practice.value==""|| form.analysis.value==""
					|| form.innovation.value==""){
			alert("评分不可为空！");
			return;
		}
		if(form.score.value<0 || form.leadership.value<0|| form.teamwork.value<0
			|| form.apprehension.value<0|| form.practice.value<0|| form.analysis.value<0
				|| form.innovation.value<0){
			alert("评分应在1-99之间！");
			return;
		}
		if(form.score.value>100 || form.leadership.value>100|| form.teamwork.value>100
				|| form.apprehension.value>100|| form.practice.value>100|| form.analysis.value>100
					|| form.innovation.value>100){
				alert("评分应在1-99之间！");
				return;
			}
		/* param+="sid="+edit-grade.sid.value+"&cid="+edit-grade.cid.value
		+"&score="+edit-grade.score.value+"&leadership="+edit-grade.leadership.value
		+"&teamwork="+edit-grade.teamwork.value+"&apprehension="+edit-grade.apprehension.value
		+"&practice="+edit-grade.practice.value+"&analysis="+edit-grade.analysis.value+"&innovation="+edit-grade.innovation.value;
		var loader=new net.AjaxRequest("../TeacherServlet?action=edit_grade&nocache="+new Date().getTime(),deal_edit_grade,onerror,"POST",encodeURI(param)); */
		form.submit();
	}
}
//function deal_edit_grade(){
	/*****************显示提示信息******************************/
//	var h=this.req.responseText;
//	alert(h);
//}
//function onerror(){
//	alert("您的操作有误！");
//} 
</script>

<form class="form form-horizontal" id="edit-grade"  method="post" name="edit-grade" action="../TeacherServlet?action=edit_grade">
<div id="info" style="text-align: center">
实验成绩：<input class="input-text" style="width:100px;vertical-align:middle;" id="score" name="score"><br>
领导能力：<input class="input-text" style="width:100px;vertical-align:middle;" id="leadership" name="leadership"><br>
团队协作：<input class="input-text" style="width:100px;vertical-align:middle;" id="teamwork" name="teamwork"><br>
运用能力：<input class="input-text" style="width:100px;vertical-align:middle;" id="apprehension" name="apprehension"><br>
语言能力：<input class="input-text" style="width:100px;vertical-align:middle;" id="practice" name="practice"><br>
分析能力：<input class="input-text" style="width:100px;vertical-align:middle;" id="analysis" name="analysis"><br>
创新能力：<input class="input-text" style="width:100px;vertical-align:middle;" id="innovation" name="innovation"><br>
<input id="sid" type="hidden" name="sid" value="<%=sid%>">
<input id="cid" type="hidden" name="cid" value="<%=cid%>">
<button onClick="editGrade();"  class="btn btn-primary radius" type="button">&nbsp;&nbsp;确&nbsp;&nbsp;定&nbsp;&nbsp;</button>
<button  class="btn btn-default radius" type="reset" value="reset">&nbsp;&nbsp;重&nbsp;&nbsp;置&nbsp;&nbsp;</button>
</div>
</form>

<script type="text/javascript" src="../lib/jquery/1.9.1/jquery.min.js"></script>
<script type="text/javascript" src="../lib/layer/2.4/layer.js"></script>
<script type="text/javascript" src="../static/h-ui/js/H-ui.min.js"></script>
<script type="text/javascript" src="../static/h-ui.admin/js/H-ui.admin.js"></script> 

</body>
</html>