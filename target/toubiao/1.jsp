<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- 引入jQuery -->
<script src="${pageContext.request.contextPath}/jslib/jquery-1.8.3.js" type="text/javascript" charset="utf-8"></script>
<script type="text/javascript">

$(document).ready(function() {
	
	/* $.ajax({
	type:"get",
	url:"http://localhost:9999/toubiao/department/treeWithDesigner",
	async:true,
	dataType:"json",
	success:function(data,textStatus){
			console.log("in success sfunction")
			console.log(data);
		},
	statusCode: {
        404: function() {
            alert( "找不到页面" );
        },
        200: function(){
            alert("请求成功");
        }
    },
    error: function(XMLHttpRequest, textStatus, errorThrown) {
					 alert(XMLHttpRequest.status);
					 alert(XMLHttpRequest.readyState);
					 alert(textStatus);
  	 }

	}); */
	
	
});



 function y(){
	 
 };
 var x={"a":{"b":"c"}};
 console.log(x['a']['b']);



</script>
<style type="text/css">
a:hover {
color: #ba2636;
text-decoration: none;
cursor:point;
color: #f60;
}
a:visited {
text-decoration: none;
}
a {
color: #252525;
text-decoration: none;
}
span {display:block;line-height:30px;margin:5px 0;background:#f0f0f0;text-align:center;}
</style>
<title>Insert title here</title>
</head>
<body>
	<a href="javascript:void(0)" onclick="alert(hit);">一个项目一个项目一个项目一个项目</a><br><br>
	<span  onclick="alert('hit');">abcdef</span><br>
	<form name="upload" action="${pageContext.request.contextPath}/photoController/uploadFile" enctype="multipart/form-data" method="post">
  		<input type="file" name="thefile" /> <input type="submit" value="上传文件" />
 	</form>
 	<span style="cursor:pointer;">hand 手型</span>
<span style="cursor:crosshair;">crosshair 十字</span>
<span style="cursor:text;">text 文本</span>
<span style="cursor:wait;">wait 等待</span>
<span style="cursor:help;">help 问号</span>
<span style="cursor:e-resize;">e-resize 右的箭头</span>
<span style="cursor:ne-resize;">ne-resize 右上的箭头</span>
<span style="cursor:n-resize;">n-resize 上的箭头</span>
<span style="cursor:nw-resize;">nw-resize 左上的箭头</span>
<span style="cursor:w-resize;">w-resize 左的箭头</span>
<span style="cursor:sw-resize;">sw-resize 左下的箭头</span>
<span style="cursor:s-resize;">s-resize 下的箭头</span>
<span style="cursor:se-resize;">se-resize 右下的箭头</span>
<span style="cursor:move;">move 移动</span>
</body>
</html>