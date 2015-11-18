<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
	<!--JQuery-->
	<script src="${pageContext.request.contextPath}/jslib/jquery-2.1.1.js"></script>
	
	<!--bootstrap css-->
	<link rel="stylesheet" href="${pageContext.request.contextPath}/frame/bootstrap-3.3.5-dist/css/bootstrap.css">
	
	<!--	bootstrap js-->
	<script src="${pageContext.request.contextPath}/frame/bootstrap-3.3.5-dist/js/bootstrap.js"></script>	
	
	<!--引入webuploader CSS-->
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/jslib/webUpload/webuploader.css">
	
	<!--引入 webuploader JS-->
	<script type="text/javascript" src="${pageContext.request.contextPath}/jslib/webUpload/webuploader.js"></script>
	<script>
$(function() {
	var contextPath='http://localhost:9999/toubiao';
	var projectPhotoUploader = WebUploader.create({
		 // 选完文件后，是否自动上传。
	    auto: true,
	    // swf文件路径
	    swf: contextPath+"/jslib/webUpload/Uploader.swf",

	    // 文件接收服务端。
	    server:contextPath+"/photoController/uploadFile2?a=1",

	    // 选择文件的按钮。可选。
	    // 内部根据当前运行是创建，可能是input元素，也可能是flash.
	    /*pick: '.project-photo-add',*/
	    
	    pick: {
           id: '#project-photo-add',
           label: '点击添加照片'
       },

	    accept: {
	        title: 'Images',
	        extensions: 'gif,jpg,jpeg,bmp,png',
	        mimeTypes: 'image/*'
	    }
	});
	
	var progressInt=1;
	projectPhotoUploader.on( 'uploadProgress', function( file, percentage ) {
		console.log("progress=="+progressInt);
		progressInt++;
	});
	
	var projectPhotoIndex;
	projectPhotoUploader.on('startUpload',function(){
		/*projectPhotoIdex=layer.msg('上传中', {icon: 16,shade:0.8,time: 500000});*/
	});
	
	
	// 文件上传成功。
	projectPhotoUploader.on( 'uploadSuccess', function( file,response  ) {
		console.log("success");
		/*console.log(response.obj.webPath);*/
		/*layer.close(projectPhotoIdex);*/
		/* layer.msg(response.msg);
		var photo=response.obj;
		var photoStr=$.param(photo);
		/*console.log(photoStr);*/
		//新开layer层，显示图片信息
		
		
		
	});

	// 文件上传失败
	projectPhotoUploader.on( 'uploadError', function( file ) {
		/*layer.close(projectPhotoIdex);*/
		console.log("upload failed");
	});

	// 完成上传完了，成功或者失败，先删除进度条。
	projectPhotoUploader.on( 'uploadComplete', function( file ) {
		/*layer.close(projectPhotoIdex);*/
		console.log("upload complete");
	});	
})
	</script>
</head>
<body>
	<div id="uploader" class="wu-example">
	    <!--用来存放文件信息-->
	    <div id="project-photo-add" class="uploader-list"></div>
	    <!-- <div class="btns">
	        <div id="picker">选择文件</div>
	        <button id="ctlBtn" class="btn btn-default">开始上传</button>
	    </div> -->
	</div>
</body>
</html>