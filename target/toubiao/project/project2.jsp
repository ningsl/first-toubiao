<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<title>业绩信息</title>
<jsp:include page="../inc.jsp"></jsp:include>

<!-- import filter js and css -->
<link href="${pageContext.request.contextPath}/jslib/filter/filter.css" rel="stylesheet" type="text/css"/> 
<script type="text/javascript" src="${pageContext.request.contextPath}/jslib/filter/filter.js" charset="utf-8"></script>
	
	
<c:if test="${fn:contains(sessionInfo.resourceList, '/projectController/projectDetail')}">
	<script type="text/javascript">
		console.log("canEdit");
		$.canEdit = true;
	</script>
</c:if>
<c:if test="${fn:contains(sessionInfo.resourceList, '/projectController/delete')}">
	<script type="text/javascript">
		$.canDelete = true;
		console.log("can delete");
	</script>
</c:if>
<c:if test="${fn:contains(sessionInfo.resourceList, '/userController/grantPage')}">
	<script type="text/javascript">
		$.canGrant = true;
	</script>
</c:if>
<c:if test="${fn:contains(sessionInfo.resourceList, '/userController/editPwdPage')}">
	<script type="text/javascript">
		$.canEditPwd = true;
	</script>
</c:if>
<script type="text/javascript">
console.log("${test}");


function handleFieldArray(webField,obj){
	if(webField.indexOf('.')==-1){
			if(webField.indexOf('[]')==-1){
				return obj[webField];
			}else{
				webField=webField.substr(webField.indexOf(2,webField.length));
				var values=new Array();
				for(var i=0;i<obj[webField].length;i++){
					values.push(obj[webField][i]);
				}
				return values.join(";");
			}
		}else{
			var firstField=webField.substr(0,webField.indexOf('.'));
			var leftField=webField.substr(webField.indexOf('.')+1,webField.length);
			if(firstField.indexOf('[]')==-1){
				return handleFieldArray(leftField,obj[firstField]);
			}else{
				firstField=firstField.substr(2,firstField.length);
				var values=new Array();
				console.log(firstField);
				console.log(leftField);
				console.log(obj[firstField]);
				for(var i=0;i<obj[firstField].length;i++){
					var v=handleFieldArray(leftField,obj[firstField][i]);
					values.push(v);			
				}
				return values.join(";");
			}
		}
}



	var dataGrid;
	var webRootPath=nsl.getContextPath();
	$(function() {
		dataGrid = $('#dataGrid').datagrid({
			url : '${pageContext.request.contextPath}/projectController/dataGrid',
			fit : true,
			fitColumns : true,
			singleSelect:true,
			border : false,
			pagination : true,
			idField : 'id',
			pageSize : 10,
			pageList : [ 10, 20, 30, 40, 50 ],
			sortName : 'projectName',
			sortOrder : 'asc', 
			multiSort : true,
			striped: true,
			checkOnSelect : false,
			selectOnCheck : false,
			nowrap : false,
			frozenColumns : [ [ {
				field : 'sn',
				title : '序号',
				width : 150,
				checkbox : true
			}, {
				field : 'projectName',
				title : '业绩名称',
				width : 350,
				sortable : true,
				formatter: function(value,row,index){
					/* console.log(value);
					console.log(index);
					console.log(row); */
					var projectId=row.id;
					var designCode=row.designCode;
					/* console.log(projectId); */
					if(typeof(value) == "undefined" ){
						value='';
					}
					value=$.formatString('<div class="project-detail" onclick="addProjectDetailTab(\'{0}\',\'{1}\')"> {2}</div> ',projectId,designCode, value);
					/* value=$.formatString('<img onclick="editFun(\'{0}\');" src="{1}" title="编辑"/>', row.id, '${pageContext.request.contextPath}/style/images/extjs_icons/pencil.png'); */
					return value;
				}
			} ] ],
			columns : [ [ {
				field : 'designCode',
				title : '设计编号',
				width : 75,
				sortable : true,
				hidden : false,
			}, {
				field : 'classByProfession',
				title : '项目专业',
				width : 100,
				sortable : true
			},{
				field : 'partA',
				title : '委托单位',
				width : 150,
				sortable : true
			}, {
				field : 'investAmount',
				title : '投资金额',
				width : 75,
				sortable : true
			},{
				field : 'classByPhase',
				title : '阶段类型',
				width : 100,
				sortable : true
			},{
				field : 'classByImportance',
				title : '项目类型',
				width : 75,
				sortable : true
			},{
				field : 'beginDate',
				title : '委托日期',
				width : 75,
				sortable : true
			},{
				field : 'departmentNames',
				title : '承担部门',
				width : 75,
				sortable : true
			},{
				field : 'designerNames',
				title : '设计负责人',
				width : 75,
				sortable : true
			},{
				field : 'action',
				title : '操作',
				width : 100,
				formatter : function(value, row, index) {
					var str = '';
					if ($.canEdit) {
						str += $.formatString('<img onclick="addProjectDetailTab(\'{0}\',\'{1}\');" src="{2}" title="详情"/>', row.id,row.designCode, '${pageContext.request.contextPath}/style/images/extjs_icons/book_open.png');
					}
					str += '&nbsp;&nbsp;&nbsp;&nbsp;';
					if ($.canDelete) {
						str += $.formatString('<img onclick="deleteFun(\'{0}\');" src="{1}" title="删除"/>', row.id, '${pageContext.request.contextPath}/style/images/extjs_icons/cancel.png');
					}
					return str;
				}
			} ] ],
			toolbar : '#toolbar',
			onLoadSuccess : function() {
				$('#searchForm table').show();
				parent.$.messager.progress('close');

				$(this).datagrid('tooltip');
			},
			onRowContextMenu : function(e, rowIndex, rowData) {
				e.preventDefault();
				$(this).datagrid('unselectAll').datagrid('uncheckAll');
				$(this).datagrid('selectRow', rowIndex);
				$('#menu').menu('show', {
					left : e.pageX,
					top : e.pageY
				});
			},
			onBeforeLoad : function(param){
				console.log(param);
				if(param.sort){
					var options=$(this).datagrid('getColumnOption',param.sort);
					param.webClass="project";
					console.log(param);
				}
			}
			
			
			/* onSortColumn: function (sort, order) {
				sort="sn";
				console.log(sort);
			} */
		});
		
		//datagride end
		
		
		/* console.log(dataGrid);
		console.log(dataGrid.datagrid('options')); */
		
	});

	function editPwdFun(id) {
		dataGrid.datagrid('unselectAll').datagrid('uncheckAll');
		parent.$.modalDialog({
			title : '编辑用户密码',
			width : 500,
			height : 300,
			href : '${pageContext.request.contextPath}/userController/editPwdPage?id=' + id,
			buttons : [ {
				text : '编辑',
				handler : function() {
					parent.$.modalDialog.openner_dataGrid = dataGrid;//因为添加成功之后，需要刷新这个dataGrid，所以先预定义好
					var f = parent.$.modalDialog.handler.find('#form');
					f.submit();
				}
			} ]
		});
	}

	function deleteFun(id) {
		if (id == undefined) {//点击右键菜单才会触发这个
			var rows = dataGrid.datagrid('getSelections');
			console.log(rows);
			id = rows[0].id;
		} else {//点击操作里面的删除图标会触发这个
		/* 	dataGrid.datagrid('unselectAll').datagrid('uncheckAll'); */
			dataGrid.datagrid('unselectAll');
		}
		console.log("delete id="+id);
		parent.$.messager.confirm('询问', '您是否要删除当前项目？', function(b) {
			if (b) {
					parent.$.messager.progress({
						title : '提示',
						text : '数据处理中，请稍后....'
					});
					$.post('${pageContext.request.contextPath}/projectController/delete', {
						id : id
					}, function(result) {
						if (result.success) {
							parent.$.messager.alert('提示', result.msg, 'info');
							dataGrid.datagrid('reload');
						}
						parent.$.messager.progress('close');
					}, 'JSON');
			}
		});
	}

	function batchDeleteFun() {
		var rows = dataGrid.datagrid('getChecked');
		var ids = [];
		if (rows.length > 0) {
			parent.$.messager.confirm('确认', '您是否要删除当前选中的项目？', function(r) {
				if (r) {
					parent.$.messager.progress({
						title : '提示',
						text : '数据处理中，请稍后....'
					});
					var currentUserId = '${sessionInfo.id}';/*当前登录用户的ID*/
					var flag = false;
					for ( var i = 0; i < rows.length; i++) {
							ids.push(rows[i].id);
							flag = true;
						}
					
					console.log(ids);
					$.getJSON('${pageContext.request.contextPath}/projectController/batchDelete', {
						ids : ids.join(',')
					}, function(result) {
						if (result.success) {
							dataGrid.datagrid('load');
							dataGrid.datagrid('uncheckAll').datagrid('unselectAll').datagrid('clearSelections');
						}
						parent.$.messager.alert('提示', result.msg, 'info');
						parent.$.messager.progress('close');
					});
				}
			});
		} else {
			parent.$.messager.show({
				title : '提示',
				msg : '请勾选要删除的记录！'
			});
		}
	}

	function detailFun(id) {
		if (id == undefined) {
			var rows = dataGrid.datagrid('getSelections');
			id = rows[0].id;
		} else {
			dataGrid.datagrid('unselectAll').datagrid('uncheckAll');
		}
		
	}

	function addFun() {
		parent.$.modalDialog({
			title : '添加用户',
			width : 500,
			height : 300,
			href : '${pageContext.request.contextPath}/userController/addPage',
			buttons : [ {
				text : '添加',
				handler : function() {
					parent.$.modalDialog.openner_dataGrid = dataGrid;//因为添加成功之后，需要刷新这个dataGrid，所以先预定义好
					var f = parent.$.modalDialog.handler.find('#form');
					f.submit();
				}
			} ]
		});
	}

	function batchGrantFun() {
		var rows = dataGrid.datagrid('getChecked');
		var ids = [];
		if (rows.length > 0) {
			for ( var i = 0; i < rows.length; i++) {
				ids.push(rows[i].id);
			}
			parent.$.modalDialog({
				title : '用户授权',
				width : 500,
				height : 300,
				href : '${pageContext.request.contextPath}/userController/grantPage?ids=' + ids.join(','),
				buttons : [ {
					text : '授权',
					handler : function() {
						parent.$.modalDialog.openner_dataGrid = dataGrid;//因为授权成功之后，需要刷新这个dataGrid，所以先预定义好
						var f = parent.$.modalDialog.handler.find('#form');
						f.submit();
					}
				} ]
			});
		} else {
			parent.$.messager.show({
				title : '提示',
				msg : '请勾选要授权的记录！'
			});
		}
	}

	function grantFun(id) {
		dataGrid.datagrid('unselectAll').datagrid('uncheckAll');
		parent.$.modalDialog({
			title : '用户授权',
			width : 500,
			height : 300,
			href : '${pageContext.request.contextPath}/userController/grantPage?ids=' + id,
			buttons : [ {
				text : '授权',
				handler : function() {
					parent.$.modalDialog.openner_dataGrid = dataGrid;//因为授权成功之后，需要刷新这个dataGrid，所以先预定义好
					var f = parent.$.modalDialog.handler.find('#form');
					f.submit();
				}
			} ]
		});
	}

	function searchFun() {
		dataGrid.datagrid('load', $.serializeObject($('#searchForm')));
	}
	function cleanFun() {
		$('#searchForm input').val('');
		dataGrid.datagrid('load', {});
	}
	
	//增加详情显示页面
	 function addProjectDetailTab(projectId,designCode) {
		parent.addProjectDetailTab(projectId,designCode);
	} 
	
	//增加新项目tab
	 function addNewProjectTab() {
		parent.addNewProjectTab();
	} 
	
	//excle 下载
	function downloadExcelAll (){
		/* var myframe=document.getElementById('downloadframe');
        myframe.src="/download"; */
        window.open("${pageContext.request.contextPath}/projectController/downloadExcleAll");             
	}
			
	function downloadExcelChecked(){
		var rows = dataGrid.datagrid('getChecked');
		var idArray = [];
		if (rows.length > 0) {
			var flag = false;
			for ( var i = 0; i < rows.length; i++) {
					idArray.push(rows[i].id);
					flag = true;
				}
			var ids=idArray.join(",");
			window.open("${pageContext.request.contextPath}/projectController/downloadExcleChecked?ids="+ids);
		}else{
			parent.$.messager.alert('下载提示','未勾选任何项目');  
		}
	}
	
	function downloadExcelFiltered(){
		var conditions=new Array();
		var classField=$("#project-field").val()
		var value=$("#project-field-value").val().trim();
		
		conditions=filterConditions.slice(0);
		
		if(value!=''){
			var condition=new Object();
			var valueArray=new Array();
			value="&"+value;
			valueArray.push(value);

			var className=classField.split(".")[0];
			var classField=classField.split(".")[1];
			condition.className=className;
			condition.classField=classField;
			condition.valueArray=valueArray;
			console.log(condition);
			conditions.push(condition);
		}
		var conditionsJson=JSON.stringify(conditions);
		console.log(conditionsJson);
		var url="${pageContext.request.contextPath}/projectController/downloadExcelFiltered";
		console.log(url);
		var tempForm=nsl.post(url,{"conditionsJson":conditionsJson});
		nsl.removeElement(tempForm);
	}
	
</script>

<!-------------------  instance filter  js -------------------->
<script type="text/javascript">
	
	var filterConditions=[];
	
	//datagrid 筛选入口函数，结合filter 和   search	
	function datagridQuery(){
		var conditions=new Array();
		var classField=$("#project-field").val()
		var value=$("#project-field-value").val().trim();
		
		console.log(value);
		console.log(filterConditions);
		
		conditions=filterConditions.slice(0);
		
		if(value!=''){
			var condition=new Object();
			var valueArray=new Array();
			value="&"+value;
			valueArray.push(value);

			var className=classField.split(".")[0];
			var classField=classField.split(".")[1];
			condition.className=className;
			condition.classField=classField;
			condition.valueArray=valueArray;
			console.log(condition);
			conditions.push(condition);
		}
		
		console.log(conditions);
		var conditionsJson=JSON.stringify(conditions);
		console.log(conditions);
		dataGrid.datagrid('load', {"conditionsJson":conditionsJson}); 
	}

	function removeSearch(){
		$("#project-field-value").val('');
		datagridQuery();
	}


$(document).ready(function() {

	var crumb1=new FilterCrumb("crumbId1",function(conditions){
		filterConditions=conditions;
		datagridQuery();
		/* var conditionsJson=JSON.stringify({"conditionsJson":conditions}); */
		
		/* if(queryParameter==""){
				table.ajax.url("/bid/employee/listAll.do").load();
		 }else{
			table.ajax.url("/bid/employee/queryByHql.do?condition="+encodeURI(encodeURI(queryParameter))).load();
		 }   */
	});


/* var filterRow=new FilterRow("row1",crumb1);
var filterRow=new FilterRow("row2",crumb1); */
var rowClass='project';
var rowField="departmentNames";
var rowName="承担部门";
var rowId="id_department"
var isFirstRow=true;
var items=[
			{
				title:'一分',
				value: '&一分',
				text:'一分'
			},
			{
				title:'二分',
				value:"&二分" ,
				text:'二分'
			},
			{
				title:'三分',
				value:"=三分" ,
				text:'三分'
			},
			{
				title:'华北',
				value:"&华北" ,
				text:'华北'
			},{
				title:'华南',
				value:"project.department like '%华南%'" ,
				text:'华南'
			},{
				title:'华东',
				value:"project.department like '%华东%'" ,
				text:'华东'
			},
			{
				title:'建筑',
				value:"project.department like '%建筑%'" ,
				text:'建筑'
			},
			{
				title:'网优',
				value:"project.department like '%网优%'" ,
				text:'网优'
			},
			{
				title:'软信',
				value:"project.department like '%软信%'" ,
				text:'软信'
			},
			];
	var rowClass1='project';
	var rowField1="classByProfession";
	var rowName1="项目专业";
	var rowId1="id_education"
	var isFirstRow1=true;
	var items1=[
				{
					title:'移动通信',
					value:"&移动通信" ,
					text:'移动通信'
				},
				{
					title:'数据通信',
					value:"&数据通信" ,
					text:'数据通信'
				},{
					title:'传输设备',
					value:"传输设备" ,
					text:'传输设备'
				},{
					title:'移动通信',
					value:"classByProfession like '移动通信'" ,
					text:'移动通信'
				},{
					title:'移动通信',
					value:"classByProfession like '移动通信'" ,
					text:'移动通信'
				},{
					title:'移动通信',
					value:"classByProfession like '移动通信'" ,
					text:'移动通信'
				},{
					title:'移动通信',
					value:"classByProfession like '移动通信'" ,
					text:'移动通信'
				},
				];
	var rowClass2='project';
	var rowField2="investAmount";
	var rowName2="投资金额";
	var rowId2="id_investAmount"
	var isFirstRow2=false;
	var items2=[{
					title:'0-50万',
					value:"[0,500000]" ,
					text:'0-50万'
				},{
					title:'50-100万',
					value:"[500000,1000000]" ,
					text:'50-100万'
				},{
					title:'100-500万',
					value:"[1000000,5000000]" ,
					text:'100-500万'
				}
				];
	var filterTest=createFilterRow(rowId,rowClass,rowField,rowName,items,isFirstRow,crumb1,"filter_group");
	var filterTest2=createFilterRow(rowId1,rowClass1,rowField1,rowName1,items1,isFirstRow1,crumb1,"filter_group");
	var filterTest3=createFilterRow(rowId2,rowClass2,rowField2,rowName2,items2,isFirstRow2,crumb1,"filter_group");
	
});	
</script>
<style type="text/css">
.project-detail {
	color: #252525;
	text-decoration: none;
}
.project-detail:hover {
text-decoration: none;
cursor:point;
color: #f60;
}

</style>

</head>
<body>
	<div class="easyui-layout" data-options="fit : true,border : false">
		<div data-options="region:'north',title:'查询条件',border:false" style="height: 160px; overflow: hidden;">
			<div id="crumbId1"></div>
			<div id="filter_group"></div>
			<!-- <form id="searchForm">
				<table class="table table-hover table-condensed" style="display: none;">
					<tr>
						<th>登录名</th>
						<td><input name="name" placeholder="可以模糊查询登录名" class="span2" /></td>
					</tr>
					<tr>
						<th>创建时间</th>
						<td><input class="span2" name="createdatetimeStart" placeholder="点击选择时间" onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd HH:mm:ss'})" readonly="readonly" />至<input class="span2" name="createdatetimeEnd" placeholder="点击选择时间" onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd HH:mm:ss'})" readonly="readonly" /></td>
					</tr>
					<tr>
						<th>最后修改时间</th>
						<td><input class="span2" name="modifydatetimeStart" placeholder="点击选择时间" onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd HH:mm:ss'})" readonly="readonly" />至<input class="span2" name="modifydatetimeEnd" placeholder="点击选择时间" onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd HH:mm:ss'})" readonly="readonly" /></td>
					</tr>
				</table>
			</form> -->
		</div>
		<div data-options="region:'center',border:true">
			
			<table id="dataGrid"></table>
		</div>
	</div>
	<div id="toolbar" style="display: none;">
		<c:if test="${fn:contains(sessionInfo.resourceList, '/userController/addPage')}">
			<a onclick="addFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'pencil_add'">添加</a>
		</c:if>
		<c:if test="${fn:contains(sessionInfo.resourceList, '/userController/grantPage')}">
			<a onclick="batchGrantFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'tux'">批量授权</a>
		</c:if>
		<c:if test="${fn:contains(sessionInfo.resourceList, '/userController/batchDelete')}">
			<a onclick="batchDeleteFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'delete'">批量删除</a>
		</c:if>
		搜索项目：
		<select id="project-field">
			  <option value ="project.designCode" selected>设计编号</option>
			  <option value ="project.projectName">业绩名称</option>
			  <option value="opel">Opel</option>
			  <option value="audi">Audi</option>
		</select>&nbsp;&nbsp;：
		<input type="text" value="" id="project-field-value">
		<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'brick_add',plain:true" onclick="datagridQuery();">搜索</a>
		<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'brick_delete',plain:true" onclick="removeSearch();">清空搜索</a>
		<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'brick_add',plain:true" onclick="addNewProjectTab();">添加项目</a>
		<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'brick_delete',plain:true" onclick="batchDeleteFun();">删除勾选</a>
		<a href="javascript:void(0)" id="mb" class="easyui-menubutton"  data-options="menu:'#mm',iconCls:'book_next'">导出Excel</a>   
			<div id="mm" style="width:150px;">   
			    <div data-options="iconCls:'icon-undo'" onclick="downloadExcelFiltered();" >导出筛选</div>   
			    <div data-options="iconCls:'icon-redo'" onclick="downloadExcelChecked();">导出勾选</div>   
			    <div data-options="iconCls:'icon-redo'" onclick="downloadExcelAll();">导出所有</div>   
			    <div class="menu-sep"></div>   
			    <div>导出全部</div>   
			</div> 
			 
			<script type="text/javascript">
			
			
			</script>
	</div>
	<iframe id="downloadframe" style="display:none"></iframe>
	<div id="menu" class="easyui-menu" style="width: 120px; display: none;">
		<c:if test="${fn:contains(sessionInfo.resourceList, '/userController/addPage')}">
			<div onclick="addFun();" data-options="iconCls:'pencil_add'">增加</div>
		</c:if>
		<c:if test="${fn:contains(sessionInfo.resourceList, '/projectController/delete')}">
			<div onclick="deleteFun();" data-options="iconCls:'pencil_delete'">删除</div>
		</c:if>
		<c:if test="${fn:contains(sessionInfo.resourceList, '/projectController/projectDetail')}">
			<div onclick="projectDetail();" data-options="iconCls:'pencil'">详情</div>
		</c:if>
		<div onclick="deleteFun();" data-options="iconCls:'pencil_delete'">删除</div>
	</div>
</body>
</html>