<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" media="screen" href="${pageContext.request.contextPath}/css/login/login.css">
			
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/jslib/jquery-easyui-1.3.3/themes/default/easyui.css">  
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/jslib/jquery-easyui-1.3.3/themes/icon.css">   
		<script type="text/javascript" src="${pageContext.request.contextPath}/jslib/jquery-1.8.3.js"></script>   
		<script type="text/javascript" src="${pageContext.request.contextPath}/jslib/jquery-easyui-1.3.3/jquery.easyui.min.js"></script>  
		
		<script type="text/javascript">
			$(document).ready(function() {
			// submit the form    
			$("#submitBtn").on("click",function(){
				 $.messager.progress();	// 显示进度条
				 
				 var name=$("#username").val();
				 var password=$("#password").val();
				 
				 var url="${pageContext.request.contextPath}/userController/userLogin";
				$('#loginForm').form('submit',{    
				    url:url,    
				    onSubmit: function(param){    
				        // do some check    
				        // return false to prevent submit;  
				        param.name=name;
				        param.pwd=password;
				        console.log("in onSubmit");
				        var isValid = $('#loginForm').form('validate');
				        console.log(isValid);
						if (!isValid){
							console.log("in close");
							$.messager.progress('close');	// 如果表单是无效的则隐藏进度条
						}
						return isValid;	// 返回false终止表单提交
				    },    
				    success:function(json){  
				    	console.log("in success");
				    	$.messager.progress('close');	//如果提交成功则隐藏进度条
				    	console.log(json);
				    	var data =$.parseJSON(json);
				    	if(data.success){
				    		self.location.href="${pageContext.request.contextPath}/index.jsp";
				    	}else if(data.success==false){
				    		alert(data.msg);
				    	}else{
				    		alert("服务器异常,请联系管理员!");
				    	}
				    }    
				});    
			});
			
			});
		</script>
	</head>
<body style="height: 635px;" class="prs_login_body">
	<div style="height: 508px; padding-top: 127px;" class="prs_login_main">
		<div class="prs_login_logo">
			<h1></h1>
		</div>
		<div class="prs_login_box">
			<div class="prs_login_box_t"></div>
			<div class="prs_login_box_m">
				<div class="prs_login_list">
					<form novalidate="novalidate" name="loginForm" method="post" id="loginForm" action="#" autocomplete="off">
						<dl>
							<dt>用户名：</dt>
							<dd>
								<label class="prs_login_inp"><input id="username" name="username" class="easyui-validatebox"  data-options="required:true,missingMessage:'用户名不能为空'" type="text"></label>
							</dd>
						</dl>
						<dl>
							<dt>密码</dt>
							<dd>
								<label class="prs_login_inp"><input id="password" name="password" class="easyui-validatebox"  data-options="required:true,missingMessage:'密码不能为空'" type="password"></label>
							</dd>
						</dl>
						<!--<dl class="">
							<dt>éªè¯ç ï¼</dt>
							<dd>
								<span class="prs_inline_block"> <label class="prs_login_inp_code"> <input id="verification" name="verification" class="login_input" type="text">
								</label>
								</span> <span class="prs_inline_block pl15"><img src="iManager%20PRS_1_files/vcode.png" id="validateimg"></span> <span class="prs_inline_block pl10"><a style="cursor: pointer;" id="vcRefresher" class="rco"></a></span>
							</dd>
						</dl>-->
						<dl>
							<dt></dt>
							<dd>
								<a style="cursor: pointer;" id="submitBtn" class="prs_btn_green mt6"><span>登录</span></a>
							</dd>
						</dl>
					</form>
					<div class="prs_login_info_box">
						<span class="prs_login_info"></span>
					</div>
				</div>
			</div>
			<div class="prs_login_box_b"></div>
		</div>
		<div class="prs_login_copyright">版权所有 © 河南省信息咨询设计研究有限公司</div>
		<div class="prs_login_recommend">推荐分辨率1280x1024；使用过程中，如需协助或遇到问题请反馈至QQ:25137234</div>
	</div>
</body>
</html>