<!DOCTYPE html>
<html lang="zh">
<head>
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"> 
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>系统管理员登录</title>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.min.css">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
	<link rel="stylesheet" type="text/css" href="../CSS/login_bg.css">
	<script type="text/javascript" src="../JS/AjaxRequest.js"></script>

<script language="javascript">


function loginSubmit(form1){
	
	if(form1.adminNumber.value==""){		//验证用户名是否为空
		alert("请输入账号！");form1.adminNumber.focus();return false;
	}
	
	
	
	if(form1.password.value==""){		//验证密码是否为空
		alert("请输入密码！");form1.passsword.focus();return false;
	}
	var param="adminNumber="+form1.adminNumber.value+"&password="+form1.password.value; 	//将登录信息连接成字符串，作为发送请求的参数
	//alert(param);
	var loader=new net.AjaxRequest("../AdministratorServlet?action=login&nocache="+new Date().getTime(),deal_login,onerror,"POST",encodeURI(param));
	
}
function onerror(){
	alert("您的操作有误！");
	//window.location.href="admin_index.jsp";
}
function deal_login(){
	/*****************显示提示信息******************************/

	var h=this.req.responseText;
	h=h.replace(/\s/g,"");	//去除字符串中的Unicode空白
	alert(h);
	if(h=="登录成功！"){
		window.location.href="admin_index.jsp";
	}else{
		form1.studentNumber.value="";//清空用户名文本框 
		form1.password.value="";//清空密码文本框
		form1.studentNumber.focus();//让用户名文本框获得焦点
	}
	
}
</script>
	<style type="text/css">
		body{
			background:#ffcc00;
			color: #D5D6E2;
			font-weight: 500;
			font-size: 1.05em;
			font-family: "Microsoft YaHei","Segoe UI", "Lucida Grande", Helvetica, Arial,sans-serif;
		}
		.form-horizontal{
		    background: #fff;
		    padding-bottom: 40px;
		    border-radius: 15px;
		    text-align: center;
		}
		.form-horizontal .heading{
		    display: block;
		    font-size: 35px;
		    font-weight: 700;
		    padding: 35px 0;
		    border-bottom: 1px solid #f0f0f0;
		    margin-bottom: 30px;
		}
		.form-horizontal .form-group{
		    padding: 0 40px;
		    margin: 0 0 25px 0;
		    position: relative;
		}
		.form-horizontal .form-control{
		    background: #f0f0f0;
		    border: none;
		    border-radius: 20px;
		    box-shadow: none;
		    padding: 0 20px 0 45px;
		    height: 40px;
		    transition: all 0.3s ease 0s;
		}
		.form-horizontal .form-control:focus{
		    background: #e0e0e0;
		    box-shadow: none;
		    outline: 0 none;
		}
		.form-horizontal .form-group i{
		    position: absolute;
		    top: 12px;
		    left: 60px;
		    font-size: 17px;
		    color: #c8c8c8;
		    transition : all 0.5s ease 0s;
		}
		.form-horizontal .form-control:focus + i{
		    color: #00b4ef;
		}
		.form-horizontal .fa-question-circle{
		    display: inline-block;
		    position: absolute;
		    top: 12px;
		    right: 60px;
		    font-size: 20px;
		    color: #808080;
		    transition: all 0.5s ease 0s;
		}
		.form-horizontal .fa-question-circle:hover{
		    color: #000;
		}
		.form-horizontal .main-checkbox{
		    float: left;
		    width: 20px;
		    height: 20px;
		    background: #ff7f00;
		    border-radius: 50%;
		    position: relative;
		    margin: 5px 0 0 5px;
		    border: 1px solid #ff7f00;
		}
		.form-horizontal .main-checkbox label{
		    width: 20px;
		    height: 20px;
		    position: absolute;
		    top: 0;
		    left: 0;
		    cursor: pointer;
		}
		.form-horizontal .main-checkbox label:after{
		    content: "";
		    width: 10px;
		    height: 5px;
		    position: absolute;
		    top: 5px;
		    left: 4px;
		    border: 3px solid #fff;
		    border-top: none;
		    border-right: none;
		    background: transparent;
		    opacity: 0;
		    -webkit-transform: rotate(-45deg);
		    transform: rotate(-45deg);
		}
		.form-horizontal .main-checkbox input[type=checkbox]{
		    visibility: hidden;
		}
		.form-horizontal .main-checkbox input[type=checkbox]:checked + label:after{
		    opacity: 1;
		}
		.form-horizontal .text{
		    float: left;
		    margin-left: 7px;
		    line-height: 20px;
		    padding-top: 5px;
		    text-transform: capitalize;
		}
		.form-horizontal .btn{
		    float: right;
		    font-size: 14px;
		    color: #fff;
		    background: #ff7f00;
		    border-radius: 30px;
		    padding: 10px 25px;
		    border: none;
		    text-transform: capitalize;
		    transition: all 0.5s ease 0s;
		}
		@media only screen and (max-width: 479px){
		    .form-horizontal .form-group{
		        padding: 0 25px;
		    }
		    .form-horizontal .form-group i{
		        left: 45px;
		    }
		    .form-horizontal .btn{
		        padding: 10px 20px;
		    }
		}
	</style>
	<!--[if IE]>
		<script src="http://cdn.bootcss.com/html5shiv/3.7.3/html5shiv.min.js"></script>
	<![endif]-->
</head>
<body>
	
	<div class="demo" style="padding: 20px 0;">
		<div class="container">
			<div class="row">
				<div class="col-md-offset-3 col-md-6">
					<form class="form-horizontal" name="form1" method="post" action="">
						<span class="heading">系统管理员登录</span>
						<div class="form-group" >
							<input type="text" class="form-control" name="adminNumber" placeholder="账   号">
							<i class="fa fa-user"></i>
						</div>
						<div class="form-group help">
							<input type="password" class="form-control" name="password" placeholder="密　码">
							<i class="fa fa-lock"></i>
							<a href="#" class="fa fa-question-circle"></a>
						</div>
						<div class="form-group">
							<div class="main-checkbox">
								<input type="checkbox" value="None" id="checkbox1" name="check"/>
								<label for="checkbox1"></label>
							</div>
							<span class="text">记住密码</span>
							<button type="button" class="btn btn-default" onclick="loginSubmit(this.form)">登录</button>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
	<div style="text-align:center;">
</div>
</body>
</html>