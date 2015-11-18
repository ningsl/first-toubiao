<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
	<!--bootstrap css-->
		<link rel="stylesheet" href="${pageContext.request.contextPath}/frame/bootstrap-3.3.5-dist/css/bootstrap.css">
		
		<!--JQuery-->
		<script src="${pageContext.request.contextPath}/jslib/jquery-2.1.1.js"></script>
		
		<!--Layer -->
		<script src="${pageContext.request.contextPath}/jslib/layer/layer.js"></script>
		
		<!--	bootstarp js-->
		<script src="${pageContext.request.contextPath}/frame/bootstrap-3.3.5-dist/js/bootstrap.js"></script>	
		
		<!-- nsl tools -->
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/nsltools.js" charset="utf-8"></script>
		
		<script>
			var contextPath=nsl.getContextPath();	
		
			$(document).ready(function() {
				
				function deleteProjectPhotoFile(){
					console.log("in function deleteProjectPhotoFile ")
					var photo=nsl.serializeObject($("#photo-form"));
					console.log(photo);
					console.log(contextPath);
					$.ajax({  
					    url:contextPath+"/photoController/deleteProjectPhotoFile",// 跳转到 action  
					    data:photo,  
					    type:'post',  
					    cache:false,  
					    dataType:'json',
					    success:function(data) {   
					    	console.log(data.msg);
					    	var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
							parent.layer.msg("照片未保存");
							parent.layer.close(index);
					    },  
					     error : function() {  
					          // view("异常！");  
					          console.log("异常！");  
					     }  
					});
					console.log("in function deleteProjectPhotoFile endding")
				}
				
				
				//regist cancle event
				$("#photo-edit-cancle").on("click",function(){
					deleteProjectPhotoFile();
					 
					
				});
				
				//注册 保存事件
				$("#photo-edit-save").on("click",function(event){
					var photo=nsl.serializeObject($("#photo-form"));
					console.log(photo);
					var index;
					$.ajax( {  
					    url:contextPath+"/photoController/saveProjectPhoto",// 跳转到 action  
					    data:photo,  
					    type:'post',  
					    cache:false,  
					    dataType:'json',
					    beforeSend:function(XMLHttpRequest ){
					    	index=layer.load(2,{
					    		shade: [0.2,'#000'] 
					    	});
					    },
					    success:function(data) {  
					    	console.log(data);
					    	layer.close(index);
					    	
					    	
					    	//刷新父页面内的project photo part
					    	$("#contract-pic",window.parent.document).html(data.obj);
					    	console.log("refresh success");
					    	
					    	parent.reDotDot();
					    	parent.layer.msg(data.msg);
					    	var selfIndex = parent.layer.getFrameIndex(window.name);
							parent.layer.close(selfIndex);
					    },  
					     error : function() {  
					          // view("异常！");  
					          alert("异常！");  
					     }  
					});
				});
				
			})
		
		</script>
</head>
<body class="container" style="width: 870px;" >
		<!--<div class="row">
			<div class="col-lg-10 col-md-10">
				<div class="thumbnail" >
				     <img src="/img/2.jpg" />
				</div>
			</div>
			<div class="col-lg-2 col-md-2">
				<div class="caption">
					<h5 style="font:normal normal bold 1.1em/1.1em '微软雅黑';text-align: center;">获奖照片1111</h5>
				</div>
			</div>
		</div>-->
		
		<div class="row">
		    <div class="col-sm-9 col-md-9 col-lg-9">
			    <div class="thumbnail">
			      <img src="${pageContext.request.contextPath}${photo.webPath}" alt="..." style="height: 500px;"></img>
			    </div>
		    </div>
		    <div class="col-lg-3 col-md-3 col-sm-3" style="padding-left: 0;">
		    	<form id="photo-form">
					  <div class="form-group">
					    <label for="exampleInputEmail1">标题</label>
					    <input type="text" class="form-control" id="exampleInputEmail1" name="title" value="${photo.title}">
					  </div>
					  <div class="form-group">
					    <label for="exampleInputPassword1">照片描述</label>
					    <textarea class="form-control" style="resize: none;height: 300px;" name="description">${photo.description}</textarea>
					  </div>
					  <input type="hidden" name="projectId" value="${photo.projectId}">
					  <input type="hidden" name="WebPath" value="${photo.webPath}">
					  
				</form>
				<button type="button" class="btn  btn-info" id='photo-edit-cancle' data-id="${photo.id}" style="margin-left: 20px;">取消</button>
				<button type="button" class="btn  btn-success" id='photo-edit-save' style="margin-left: 40px;">保存</button>
			</div>
		</div>
	</body>
</html>