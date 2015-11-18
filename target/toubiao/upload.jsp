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
	var uploader = WebUploader.create({

	    // swf文件路径
	    swf: "http://localhost:9999/toubiao/jslib/webUpload/Uploader.swf",

	    // 文件接收服务端。
	    server:"http://localhost:9999/toubiao/photoController/uploadFile",

	    // 选择文件的按钮。可选。
	    // 内部根据当前运行是创建，可能是input元素，也可能是flash.
	    pick: '#picker',

	    // 不压缩image, 默认如果是jpeg，文件上传前会压缩一把再上传！
	    resize: false
	});
	
	uploader.on( 'fileQueued', function( file ) {
	    $("#thelist").append( '<div id="' + file.id + '" class="item">' +
	        '<h4 class="info">' + file.name + '</h4>' +
	        '<p class="state">等待上传...</p>' +
	    '</div>' );
	});
	
	// 文件上传过程中创建进度条实时显示。
	uploader.on( 'uploadProgress', function( file, percentage ) {
	    var $li = $( '#'+file.id ),
	        $percent = $li.find('.progress .progress-bar');

	    // 避免重复创建
	    if ( !$percent.length ) {
	        $percent = $('<div class="progress progress-striped active">' +
	          '<div class="progress-bar" role="progressbar" style="width: 0%">' +
	          '</div>' +
	        '</div>').appendTo( $li ).find('.progress-bar');
	    }

	    $li.find('p.state').text('上传中');

	    $percent.css( 'width', percentage * 100 + '%' );
	});
	
	uploader.on( 'uploadSuccess', function( file ) {
	    $( '#'+file.id ).find('p.state').text('已上传');
	});

	uploader.on( 'uploadError', function( file ) {
	    $( '#'+file.id ).find('p.state').text('上传出错');
	});

	uploader.on( 'uploadComplete', function( file ) {
	    $( '#'+file.id ).find('.progress').fadeOut();
	});
	
	$("#ctlBtn").on("click",function(){
		uploader.upload();
	});
	
	
})
	</script>
</head>
<body>
	<div id="uploader" class="wu-example">
	    <!--用来存放文件信息-->
	    <div id="thelist" class="uploader-list"></div>
	    <div class="btns">
	        <div id="picker">选择文件</div>
	        <button id="ctlBtn" class="btn btn-default">开始上传</button>
	    </div>
	</div>
</body>
</html>