<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>左右选择框</title>
	
	<!--JQuery-->
	<script src="${pageContext.request.contextPath}/jslib/jquery-2.1.1.js"></script>
	
	<!-- 引入EasyUI -->
	<link id="easyuiTheme" rel="stylesheet" href="${pageContext.request.contextPath}/jslib/jquery-easyui-1.4.2/themes/<c:out value="${cookie.easyuiThemeName.value}" default="bootstrap"/>/easyui.css" type="text/css">
	<link id="easyuiTheme" rel="stylesheet" href="${pageContext.request.contextPath}/jslib/jquery-easyui-1.4.2/themes/icon.css" type="text/css">
	<script type="text/javascript" src="${pageContext.request.contextPath}/jslib/jquery-easyui-1.4.2/jquery.easyui.min.js" charset="utf-8"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/jslib/jquery-easyui-1.4.2/locale/easyui-lang-zh_CN.js" charset="utf-8"></script>
	
	<!-- 扩展EasyUI -->
	<script type="text/javascript" src="${pageContext.request.contextPath}/jslib/extEasyUI.js" charset="utf-8"></script>

	<!-- nsl tools -->
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/nsltools.js" charset="utf-8"></script>

	<!-- self css js -->
	<link  rel="stylesheet" href="${pageContext.request.contextPath}/css/left_right_selection.css" type="text/css">
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/left_right_selection.js" charset="utf-8"></script>
	
</head>
<body>
	<div  style="width:700px;height:480px;margin-left: 10px;margin-bottom: 10px;" >
			<div id="cc" class="easyui-layout" data-options="fit:true" >   
				<div data-options="region:'west',iconCls:'icon-reload',title:'人员分组' , collapsible:false " style="width:300px;">
					<div  style="vertical-align: middle;border-bottom: 1px solid #E3E3E3;margin-bottom: 10px;">
					   	<label for="exampleInputName2" style="margin-left: 10px;">姓名</label>
					    <input id="name" type="text" style="width: 70px;margin: 0 5px 0 5px;" placeholder="" >
						<button type="button" class="" id="filter">筛选</button>
						<button type="button" class="" id="reset">清除</button>
					</div>
					<div id="p" class="easyui-panel" data-options="border:false,noheader:true,fit:true">
						<ul id="designerTree"></ul>
					</div>
				</div>   	    
			    <div data-options="region:'center'" style="background:#eee">
			    	<div class="operators">
				    	<ul style="list-style: none">
				    		<li style="margin-bottom:20px,margin-left: 0px;">
				    			<input type="button" id="right" value="  >  " />
				    		</li>
				    		<li>
				    			<input type="button" id="left" value="  <  " />
				    		</li>
				    		<li>
				    			<input type="button" id="right_all" value=" >> " />
				    		</li>
				    		<li>
				    			<input type="button" id="left_all" value=" << " />
				    		</li>
				    	</ul>
			    	</div>
			    </div>   
			    <div data-options="region:'east',iconCls:'icon-reload',title:'已选择人员' , collapsible:false,doSize:true" style="width:300px;">
			    	<div id="grid_table" class="easyui-panel" data-options="border:false,noheader:true,fit:true,">
			    		<table id="dg"></table>
			    	</div>
			    </div> 
			    <div data-options="region:'south',iconCls:'icon-reload' , collapsible:false,doSize:true" style="height:40px;">
			    	<div style="text-align: center;margin-top: 5px;">
				    	<button id="cancle">取消</button>
				    	<button id="ok" style="margin-left: 20px;">确认</button>
				    </div>
			    </div> 
			</div>
		</div>
</body>
</html>