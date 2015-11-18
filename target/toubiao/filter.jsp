<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>

<jsp:include page="inc.jsp"></jsp:include>

<link href="${pageContext.request.contextPath}/jslib/filter/filter.css" rel="stylesheet" type="text/css"/> 
<script type="text/javascript" src="${pageContext.request.contextPath}/jslib/filter/filter.js" charset="utf-8"></script>


<!-------------------  instance filter  js -------------------->
<script type="text/javascript">
$(document).ready(function() {

	var crumb1=new FilterCrumb("crumbId1",function(queryParameter){
			/* if(queryParameter==""){
					table.ajax.url("/bid/employee/listAll.do").load();
			 }else{
				table.ajax.url("/bid/employee/queryByHql.do?condition="+encodeURI(encodeURI(queryParameter))).load();
			 }   */
	});

	
	/* var filterRow=new FilterRow("row1",crumb1);
	var filterRow=new FilterRow("row2",crumb1); */
	var rowClass='Tproject';
	var rowField="deparment";
	var rowName="部门";
	var rowId="id_deparment"
	var isFirstRow=true;
	var items=[
				{
					title:'一分',
					value:"department like '一分'" ,
					text:'一分'
				},
				{
					title:'二分',
					value:"department like '二分'" ,
					text:'二分'
				},
				{
					title:'三分',
					value:"department like '%三分%'" ,
					text:'三分'
				},
				{
					title:'市场部',
					value:"department like '市场部'" ,
					text:'市场部'
				},
				];
	var rowClass1='Tproject';
	var rowField1="classByProfession";
	var rowName1="学历";
	var rowId1="id_education"
	var isFirstRow1=true;
	var items1=[
				{
					title:'本科',
					value:"education like '本科'" ,
					text:'本科'
				},
				{
					title:'大专',
					value:"education like '大专'" ,
					text:'大专'
				},
				{
					title:'研究生',
					value:"education like '研究生'" ,
					text:'研究生'
				}
				];
 	var filterTest=createFilterRow(rowId,rowClass,rowField,rowName,items,isFirstRow,crumb1,"filter_group");
 	var filterTest2=createFilterRow(rowId1,rowClass1,rowField1,rowName1,items1,isFirstRow,crumb1,"filter_group");
 	
});	
</script>
</head>
<body>
<div  id="crumbId1">
</div>	
<div id="filter_group">
</div>
</body>
</html>