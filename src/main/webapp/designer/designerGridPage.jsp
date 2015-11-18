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
<c:if test="${fn:contains(sessionInfo.resourceList, '/designerController/designerDetail')}">
	<script type="text/javascript">
		console.log("canEdit");
		$.canDetail = true;
	</script>
</c:if>
<c:if test="${fn:contains(sessionInfo.resourceList, '/designerController/deleteDesigner')}">
	<script type="text/javascript">
		$.canDelete = true;
		//console.log("can delete");
	</script>
</c:if>
<c:if test="${fn:contains(sessionInfo.resourceList, '/designerController/addDesigner')}">
	<script type="text/javascript">
		$.canAdd= true;
	</script>
</c:if>
<script type="text/javascript">
	var dataGrid;
	var webRootPath=nsl.getContextPath();
	$(function() {
		dataGrid = $('#dataGrid').datagrid({
			url : '${pageContext.request.contextPath}/designerController/dataGrid',
			fit : true,
			fitColumns : true,
			singleSelect:true,
			border : false,
			pagination : true,
			idField : 'id',
			pageSize : 10,
			pageList : [ 10, 20, 30, 40, 50 ],
			sortName : 'name',
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
				field : 'name',
				title : '姓名',
				width : 150,
				sortable : true,
				formatter: function(value,row,index){
					/* console.log(value);
					console.log(index);
					console.log(row); */
					var designerId=row.id;
					var name=row.name;
					/* console.log(projectId); */
					if(typeof(value) == "undefined" ){
						value='';
					}
					//value=$.formatString('<div class="project-detail" onclick="addProjectDetailTab(\'{0}\',\'{1}\')"> {2}</div> ',projectId,designCode, value);
					return value;
				}
			} ] ],
			columns : [ [ {
				field : 'gender',
				title : '性别',
				width : 50,
				sortable : true,
				hidden : false,
			}, {
				field : 'education',
				title : '学历',
				width : 100,
				sortable : true
			},{
				field : 'workingYears',
				title : '工作年限',
				width : 75,
				sortable : true
			}, {
				field : 'professionalTitle',
				title : '技术职称',
				width : 100,
				sortable : true
			},{
				field : 'workingMajor',
				title : '从事专业',
				width : 100,
				sortable : true
			},{
				field : 'departmentName',
				title : '所属部门',
				width : 75,
				sortable : true
			},{
				field : 'headship',
				title : '岗位职务',
				width : 75,
				sortable : true
			},{
				field : 'phone',
				title : '联系电话',
				width : 75,
				sortable : true
			},{
				field : 'action',
				title : '操作',
				width : 100,
				formatter : function(value, row, index) {
					var str = '';
					//详情页
					if ($.canDetail) {
					str += $.formatString('<img onclick="addDesignerDetailTab(\'{0}\',\'{1}\');" src="{2}" title="详情"/>', row.id,row.name, '${pageContext.request.contextPath}/style/images/extjs_icons/book_open.png');
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
					param.webClass="designer";
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
					$.post('${pageContext.request.contextPath}/desingerController/deleteDesigner', {
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
					$.getJSON('${pageContext.request.contextPath}/designerController/batchDelete', {
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
	
	//增加详情显示页面
	 function addDesignerDetailTab(designerId,name) {
		parent.addDesignerDetailTab(designerId,name);
	} 
	
	//增加新项目tab
	 function addNewDesignerTab() {
		parent.addNewDesignerTab();
	} 
	
	//excle  docx 下载
	function downloadAll (type){
		/* var myframe=document.getElementById('downloadframe');
        myframe.src="/download"; */
        var excelUrl="${pageContext.request.contextPath}/designerController/downloadExcelAll";
        var docxUrl="${pageContext.request.contextPath}/designerController/downloadDocxAll";
        if(type=='excel'){
        	console.log("download excel");
        	window.open(excelUrl);
        }
        if(type=='docx'){
        	console.log("download docx");
        	window.open(docxUrl);
        }
	}
			
	function downloadChecked(type){
		var excelUrl="${pageContext.request.contextPath}/designerController/downloadExcelChecked?ids=";
        var docxUrl="${pageContext.request.contextPath}/designerController/downloadDocxChecked?ids=";
		var rows = dataGrid.datagrid('getChecked');
		var idArray = [];
		if (rows.length > 0) {
			var flag = false;
			for ( var i = 0; i < rows.length; i++) {
					idArray.push(rows[i].id);
					flag = true;
				}
			var ids=idArray.join(",");
			if(type=='excel'){
	        	window.open(excelUrl+ids);
	        	console.log("download excel");
	        }
	        if(type=='docx'){
	        	window.open(docxUrl+ids);
	        	console.log("download docx");
	        }
		}else{
			parent.$.messager.alert('下载提示','未勾选任何项目');  
		}
	}
	
	function downloadFiltered(type){
		var excelUrl="${pageContext.request.contextPath}/designerController/downloadExcelFiltered";
	    var docxUrl="${pageContext.request.contextPath}/designerController/downloadDocxFiltered";
		var conditions=new Array();
		var classField=$("#designer-field").val()
		var value=$("#designer-field-value").val().trim();
		
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
		if(type=='excel'){
			console.log("download excel");
        	var tempForm=nsl.post(excelUrl,{"conditionsJson":conditionsJson});
        }
        if(type=='docx'){
        	console.log("download docx");
        	var tempForm=nsl.post(docxUrl,{"conditionsJson":conditionsJson});
        }
		
		nsl.removeElement(tempForm);
	}
	
</script>

<!-------------------  instance filter  js -------------------->
<script type="text/javascript">
	
	var filterConditions=[];
	
	//datagrid 筛选入口函数，结合filter 和   search	
	function datagridQuery(){
		var conditions=new Array();
		var classField=$("#designer-field").val()
		var value=$("#designer-field-value").val().trim();
		
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
		$("#designer-field-value").val('');
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
var rowClass='designer';
var rowField="professionalTitle";
var rowName="技术职称";
var rowId="id_professionalTitle"
var isFirstRow=true;
var items=[
			{
				title:'助理工程师',
				value: '=助理工程师',
				text:'助理工程师'
			},
			{
				title:'工程师',
				value:"=工程师" ,
				text:'工程师'
			},
			{
				title:'技术员',
				value:"=技术员" ,
				text:'技术员'
			},
			{
				title:'高级工程师',
				value:"=高级工程师" ,
				text:'高级工程师'
			},{
				title:'高级经济师',
				value:"=高级经济师" ,
				text:'高级经济师'
			}
			];
	var rowClass1='designer';
	var rowField1="workingMajor";
	var rowName1="从事专业";
	var rowId1="id_workingMajor"
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
					value:"&传输设备" ,
					text:'传输设备'
				}
				];
	var rowClass2='designer';
	var rowField2="department";
	var rowName2="所属部门";
	var rowId2="id_department"
	var isFirstRow2=false;
	var items2=[{
					title:'一分',
					value:"一分" ,
					text:'0-50万'
				},{
					title:'二分',
					value:"二分" ,
					text:'二分'
				},{
					title:'三分',
					value:"三分" ,
					text:'三分'
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
		</div>
		<div data-options="region:'center',border:true">
			
			<table id="dataGrid"></table>
		</div>
	</div>
	<div id="toolbar" style="display: none;">
		搜索项目：
		<select id="designer-field">
			  <option value ="designer.name" selected>姓名</option>
			  <option value="opel">Opel</option>
			  <option value="audi">Audi</option>
		</select>&nbsp;&nbsp;：
		<input type="text" value="" id="designer-field-value">
		<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'brick_add',plain:true" onclick="datagridQuery();">搜索</a>
		<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'brick_delete',plain:true" onclick="removeSearch();">清空搜索</a>
		<c:if test="${fn:contains(sessionInfo.resourceList, '/designerController/addDesigner')}">
			<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'brick_add',plain:true" onclick="addNewDesignerTab();">添加项目</a>
		</c:if>
		<c:if test="${fn:contains(sessionInfo.resourceList, '/designerController/deleteDesigner')}">
			<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'brick_delete',plain:true" onclick="batchDeleteFun();">删除勾选</a>
		</c:if>
		<a href="javascript:void(0)" id="mb" class="easyui-menubutton"  data-options="menu:'#mm1',iconCls:'book_next'">导出Excel</a>   
			<div id="mm1" style="width:150px;">   
			    <div data-options="iconCls:'icon-undo'" onclick="downloadFiltered('excel');" >导出筛选</div>   
			    <div data-options="iconCls:'icon-redo'" onclick="downloadChecked('excel');">导出勾选</div>   
			    <div data-options="iconCls:'icon-redo'" onclick="downloadAll('excel');">导出所有</div>   
			    <div class="menu-sep"></div>   
			    <div>导出全部</div>   
			</div> 
		<a href="javascript:void(0)" id="mb" class="easyui-menubutton"  data-options="menu:'#mm2',iconCls:'book_next'">导出Docx</a>   
			<div id="mm2" style="width:150px;">   
			    <div data-options="iconCls:'icon-undo'" onclick="downloadFiltered('docx');" >导出筛选</div>   
			    <div data-options="iconCls:'icon-redo'" onclick="downloadChecked('docx');">导出勾选</div>   
			    <div data-options="iconCls:'icon-redo'" onclick="downloadAll('docx');">导出所有</div>   
			    <div class="menu-sep"></div>   
			    <div>导出全部</div>   
			</div> 
	</div>
	<iframe id="downloadframe" style="display:none"></iframe>
	<div id="menu" class="easyui-menu" style="width: 120px; display: none;">
		<c:if test="${fn:contains(sessionInfo.resourceList, '/designerController/designerDetail')}">
		<div onclick="designerDetail();" data-options="iconCls:'pencil'">详情</div>
		</c:if>
		<c:if test="${fn:contains(sessionInfo.resourceList, '/designerController/addDesigner')}">
			<div onclick="addFun();" data-options="iconCls:'pencil_add'">增加</div>
		</c:if>
		<c:if test="${fn:contains(sessionInfo.resourceList, '/designerController/deleteDesigner')}">
			<div onclick="deleteFun();" data-options="iconCls:'pencil_delete'">删除</div>
		</c:if>
		
	</div>
</body>
</html>