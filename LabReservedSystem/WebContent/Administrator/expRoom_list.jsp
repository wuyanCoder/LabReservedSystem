<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="d" uri="http://displaytag.sf.net"%>
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
	<title></title>
</head>
<body>
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 实验室管理 <span class="c-gray en">&gt;</span>实验室信息查询 <a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a></nav>
<div class="page-container">

	<div class="cl pd-5 bg-1 bk-gray mt-20">
		<span><a href="${pageContext.request.contextPath}/Administrator/expRoom_add.jsp"  class="btn btn-primary radius"><i class="Hui-iconfont">&#xe600;</i> 添加</a></span>
	</div>
	<div class="mt-20">

		<table class="table table-hover table-striped table-bordered">
			<tr>

				<th>地址</th>
				<th>联系方式</th>
				<th>实验室状态</th>
				<th>分院</th>
				<th>操作</th>
			</tr>
			<c:forEach items="${list}" var="list">
				<tr>

					<td>${list.eraddress}</td>
					<td>${list.ertelnum }</td>
					<td>${list.erstate }</td>
					<td>${list.superordinate }</td>

					<td>
						<a class="btn btn-warning btn-sm" href="${pageContext.request.contextPath}/expRoom/update?ID=${list.erid}">修改</a>
						<a class="btn btn-danger btn-sm" href="${pageContext.request.contextPath}/expRoom/delete?ID=${list.erid}">删除</a>
					</td>

				</tr>
			</c:forEach>
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

</script>
</body>
</html>