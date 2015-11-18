console.log("in js file");
function activeEdit(jShow,jHide){
	
	jShow.removeClass("hidden");
	jHide.addClass();
	$(this).addClass("hidden");
}

function reDotDot(){
	//处理多行字符省略号
	$(".handle-dot").dotdotdot();
}

function info2From(){
	$("#form-project-designCode").val($("#project-designCode").text().trim());
	$("#form-project-investAmount").val($("#project-investAmount").text().trim());
	$("#form-project-classByProfession").val($("#project-classByProfession").text().trim());
	$("#form-project-classByPhase").val($("#project-classByPhase").text().trim());
	$("#form-project-departmentIds").val($("#project-departmentIds").text().trim());
	$("#form-project-departmentNames").val($("#project-departmentNames").text().trim());
	$("#form-project-contractSns").val($("#project-contractSns").text().trim());
	$("#form-project-classByImportance").val($("#project-classByImportance").text().trim());
	$("#form-project-designerIds").val($("#project-designerIds").text().trim());
	$("#form-project-designerNames").val($("#project-designerNames").text().trim());
	$("#form-project-designerPhones").val($("#project-designerPhones").text().trim());
	$("#form-project-partA").val($("#project-partA").text().trim());
	$("#form-project-contactId").val($("#project-contactId").text().trim());
	$("#form-project-contactName").val($("#project-contactName").text().trim());
	$("#form-project-contactPhone").val($("#project-contactPhone").text().trim());
	$("#form-project-contractIds").val($("#project-contractIds").text().trim());
	$("#form-project-contractNames").val($("#project-contractNames").text().trim());
	$("#description-textarea").val($("#description-text").html());
	
	$("#form-project-beginDate").val($("#project-beginDate").text().trim().substr(0,4));
}



$(document).ready(function() {
	console.log("in ready function");
	var contextPath=nsl.getContextPath();
	var projectId=$("#form-project-id").val();
	console.log(projectId);
	
	//初始化
	$("#detail-edit").hide();
	$("#detail-buttons").hide();
	$("#description-edit-part").hide();

	
	//产生文件上传器
	var projectPhotoUploader = WebUploader.create({
		 // 选完文件后，是否自动上传。
	    auto: true,
	    // swf文件路径
	    swf: contextPath+"/jslib/webUpload/Uploader.swf",

	    // 文件接收服务端。
	    server:contextPath+"/photoController/uploadProjectPhoto",

	    // 选择文件的按钮。可选。
	    // 内部根据当前运行是创建，可能是input元素，也可能是flash.
	    /*pick: '.project-photo-add',*/
	    
	    formData: {
	        projectId: projectId
	    },
	    
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
		//console.log("progress=="+progressInt);
		progressInt++;
	});
	
	var projectPhotoIndex;
	projectPhotoUploader.on('startUpload',function(){
		/*projectPhotoIdex=layer.msg('上传中', {icon: 16,shade:0.8,time: 500000});*/
	});
	
	
	// 文件上传成功。
	projectPhotoUploader.on( 'uploadSuccess', function( file,response  ) {
	/*	console.log(response);
		console.log(response.obj.webPath);*/
		/*layer.close(projectPhotoIdex);*/
		layer.msg(response.msg);
		var photo=response.obj;
		var photoStr=$.param(photo);
		/*console.log(photoStr);*/
		//新开layer层，显示图片信息
		var url=contextPath+"/photoController/projectPhotoSavePage?"+photoStr;
		layer.open({
		    type: 2,
		    title: '合同照片-保存',
		    shadeClose: false,
		    shade: 0.8,
		    area: ['900px', '90%'],
		    content: url,
		    cancel :function(){
		    	layer.msg("照片未保存");
		    	$.ajax( {  
				    url:contextPath+"/photoController/deleteProjectPhotoFile",// 跳转到 action  
				    data:photo,  
				    type:'post',  
				    cache:false,  
				    dataType:'json',
				    success:function(data) {   
				    	console.log(data.msg);
				    },  
				     error : function() {  
				          // view("异常！");  
				          console.log("删除临时上传的照片时，发生异常！");  
				     }  
				});
		    	console.log("in cancle bottom");
		    }
		}); 
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
	
	
	//处理多行字符省略号
	$(".handle-dot").dotdotdot();
	
	$("#project-detail").on("click","#detail-edit-toggle",function(event){
		event.preventDefault();
		$("#detail-info").hide();
		$("#description-part").hide();
		$("#detail-edit").show();
		$("#description-edit-part").show();
		$("#description-part").hide();
		$("#detail-buttons").show();
		$(this).hide();
	});
	
	// 注册 编辑详情取消事件
	$("#detail-edit-cancel").on("click",function(event){
		info2From();
		$("#detail-edit").hide();
		$("#detail-info").show();
		$("#description-part").show();
		$("#description-edit-part").hide();
		$("#detail-buttons").hide();
		$("#detail-edit-toggle").show();
	});
	
	//注册 编辑详情 reset事件
	$("#detail-edit-reset").on("click",function(event){
		info2From();
	});
	
	//注册 编辑详情 保存事件
	$("#detail-edit-save").on("click",function(event){
		
		var postData=nsl.serializeObject($("#detail-form"));
		console.log(postData);
		/*var projectName=$("form-project-projectName").text();*/
		var description=$("#description-textarea").val();
		postData.description=description;
		console.log(postData);
		var index;
		$.ajax( {  
		    url:contextPath+"/projectController/saveOrUpdateDetailPart",// 跳转到 action  
		    data:postData,  
		    type:'post',  
		    cache:false,  
		    dataType:'json',  
		    beforeSend:function(XMLHttpRequest ){
		    	index=layer.load(2,{
		    		shade: [0.2,'#000'] 
		    	});
		    },
		    success:function(data) {  
		    	layer.close(index);
		    	layer.msg(data.msg);
		    	if(data.success){
		    		
		    		$("#project-detail").html(data.obj.html);
    		  		$("#detail-info").show();
					$("#detail-edit-toggle").show();
					$("#detail-edit").hide();
					$("#detail-buttons").hide();
					$("#description-edit-part").hide();
		    	}
		    },  
		     error : function() {  
		          // view("异常！");  
		          alert("异常！");  
		     }  
		});
		
	});
	
	//注册 选择designers 按钮事件
	$("#btn_select_designers").on("click",function(){
			layer.open({
	        type: 2,
	        title: '选择设计负责人',
	        maxmin: false,
	        shadeClose: false, //点击遮罩关闭层
	        area: ['750px', '600px'],
	        content: contextPath+'/left_right_selection_designer.jsp'
			});
	});
	
	//注册 选择department 按钮事件,用live绑定，防止新元素无事件
	$("#btn_select_department").on("click",function(){
			layer.open({
	        type: 2,
	        title: '选择部门',
	        maxmin: false,
	        shadeClose: false, //点击遮罩关闭层
	        area: ['750px', '600px'],
	        content: contextPath+'/left_right_selection_department.jsp'
			});
	});
	
	//注册点击照片编辑 event,代理绑定
	$("#contract-pic").on("click",".btn-project-photo-edit",function(){
		var id=$(this).data("id");
		var url=contextPath+"/photoController/projectPhotoEditPage?photoId="+id;
		layer.open({
		    type: 2,
		    title: '合同照片-编辑',
		    shadeClose: false,
		    shade: 0.8,
		    area: ['900px', '90%'],
		    content: url
		}); 
	});
	
	//register project photo delete event
	$("#contract-pic").on("click",".btn-project-photo-delete",function(){
		var id=$(this).data("id");
		//询问框
		var index=layer.confirm('确定删除该照片吗？', {
		    btn: ['确定','取消'] //按钮
			}, function(){
					//删除页面元素，删除数据库信息，输出文件
					$.ajax( {  
					    url:contextPath+"/photoController/deleteProjectPhoto?id="+id,// 跳转到 action  
					    data:id,  
					    type:'get',  
					    cache:false,  
					    dataType:'json',  
					   /* beforeSend:function(XMLHttpRequest ){
					    	index=layer.load(2,{
					    		shade: [0.2,'#000'] 
					    	});
					    },*/
					    success:function(data) {  
					    	layer.msg(data.msg);
					    	$("#project-photo-"+id).remove();
							layer.close(index);
					    },  
					     error : function() {  
					          // view("异常！");  
					          alert("异常！");  
					     }  
					});
			}, function(){
			    //do nothing....
				layer.close(index);
			});
	});
	
	
	
	
	//register description edit btn
	$("#description-edit-btn").on("click",function(event){
		event.preventDefault();
		$("#description-part").hide();
		$("#description-edit-part").show();
		$(this).hide();
	});
	

	$("#description-edit-cancle").on("click",function(){
		$("#description-part").show();
		$("#description-edit-part").hide();
		$("#description-edit-btn").show();
		console.log($("#description-textarea").text());
		$("#description-textarea").val($("#description-old-text").html());
	});
	
	$("#description-edit-save").on("click",function(){
		var description=$("#description-textarea").val();
		var projectId=$(this).data("id");
		console.log(description);
		console.log(projectId);
		
		var project=new Object();
		project.id=projectId;
		project.description=description;
		console.log(project);
		
		$.ajax( {  
		    url:contextPath+"/projectController/updateDescription",// 跳转到 action  
		    data:project,  
		    type:'post',  
		    cache:false,  
		    dataType:'json',  
		   /* beforeSend:function(XMLHttpRequest ){
		    	index=layer.load(2,{
		    		shade: [0.2,'#000'] 
		    	});
		    },*/
		    success:function(data) {  
		    	layer.msg(data.msg);
		    	$("#description-old-text").html(description);
		    	$("#description-part").show();
		    	$("#description-edit-part").hide();
				$("#description-edit-btn").show();
		    },  
		     error : function() {  
		          // view("异常！");  
		          alert("异常！");  
		     }  
		});
	});
	
	
});



