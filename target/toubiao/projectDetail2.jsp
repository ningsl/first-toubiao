<%@page import="toubiao.pageModel.Project"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  
<%@page import="java.util.Date"%>
<%@page import="java.util.Calendar"%>



<!DOCTYPE html>
<html>
<head>
	 <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"> 
	<!-- <meta charset="utf-8"> -->
	<title>项目详情</title>
	
	<!--bootstrap css-->
	<link rel="stylesheet" href="${pageContext.request.contextPath}/frame/bootstrap-3.3.5-dist/css/bootstrap.css">
	
	<!--JQuery-->
	<script src="${pageContext.request.contextPath}/jslib/jquery-2.1.1.js"></script>
	
	<!--Layer -->
	<script src="${pageContext.request.contextPath}/jslib/layer/layer.js"></script>
	
	<!--JQuery dotdotdot-->
	<script src="${pageContext.request.contextPath}/jslib/jquery.dotdotdot.min.js" type="text/javascript" charset="utf-8"></script>
	
	<!--	bootstrap js-->
	<script src="${pageContext.request.contextPath}/frame/bootstrap-3.3.5-dist/js/bootstrap.js"></script>	
	
	<!--引入web uploader CSS-->
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/jslib/webUpload/webuploader.css">
	
	<!--引入 web uploader JS-->
	<script type="text/javascript" src="${pageContext.request.contextPath}/jslib/webUpload/webuploader.js"></script>
	
	<!-- nsl tools -->
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/nsltools.js" charset="utf-8"></script>
	
	<script src="${pageContext.request.contextPath}/js/projectDetail2.js?v=20151023" type="text/javascript" charset="utf-8"></script>
	
	<link rel="stylesheet"  href="${pageContext.request.contextPath}/css/projectDetail.css"/>

<title>Insert title here</title>
</head>
<body class="container">
	<div id="project-detail">
		<jsp:include page="detailPart2.jsp"></jsp:include>
	</div>	
	<!-- 按钮需要在外面，防止重新加载后，丢失已注册事件 -->
	<div class="row" id ="detail-buttons" style="margin-top: 25px;">
			<div class="col-lg-12 col-md-12 text-center">
					<button type="button" class="btn btn-default" id='detail-edit-reset'  style="padding-left: 30px;padding-right: 30px;margin-left: 20px;">重置</button>
					<button type="button" class="btn btn-info"  id='detail-edit-cancel' style="padding-left: 30px;padding-right: 30px;margin-left: 20px;">取消</button>
					<button type="submit" class="btn btn-success" id='detail-edit-save' style="padding-left: 30px;padding-right: 30px;margin-left: 20px;">保存</button>
			</div>
	</div>
			
	<section class="info">
		<div class="infoHead">
			<div class="row">
				<div class="col-lg-12 col-md-12 ">
					<h3>获奖情况&nbsp;&nbsp;
						<small>
							2014年中国联通湖北移动网配套扩容工程
							<a href="#" style="margin-top: 0px;margin-left: 10px;">
								<span class="glyphicon glyphicon-pencil"  aria-hidden="true"></span>
							</a>
						</small>
					</h3>
				</div>
			</div>
		</div>
		
		<div class="infoBody honor">
			<div class="row">
				<div class="col-lg-12 col-md-12">
					<table class="table table-hover text-center honor">
						<thead style="text-align: center;">
							<tr >
								<th>序号</th>
								<th>获奖情况</th>
								<th>颁奖机构	</th>
								<th>级别</th>
								<th>类别</th>
								<th>专业</th>
								<th>文号</th>
								<th>操作</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td>1</td>
								<td>2014年度河南省优秀工程咨询成果一等奖</td>
								<td>河南省工程咨询协会</td>
								<td>省级</td>
								<td>咨询</td>
								<td>有线</td>
								<td>豫咨询[2014]04号</td>
								<td>各类操作</td>
							</tr>
							<tr>
								<td>2</td>
								<td>2014年度河南省优秀工程咨询成果二等奖</td>
								<td>中国通信企业协会通信工程建设分会</td>
								<td>部级</td>
								<td>咨询</td>
								<td>信息化</td>
								<td>工建分咨字【2014】8号</td>
								<td>各类操作</td>
							</tr>
							
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</section>
					
	<section class="info">
		<div class="infoHead">
			<div class="row">
				<div class="col-lg-12 col-md-12 ">
					<h3>电子照片&nbsp;&nbsp;
						<small>
							2014年中国联通湖北移动网配套扩容工程
							<a href="#" style="margin-top: 0px;margin-left: 10px;">
								<span class="glyphicon glyphicon-pencil"  aria-hidden="true"></span>
							</a>
						</small>
					</h3>
				</div>
			</div>
		</div>
		
		<div class="infoBody pic">
			<div class="row">
				<div class="col-lg-12 col-md-12 pic-title">
					<h4>
						合同照片
						<!-- <small>
							<a href="#" style="margin-top: 0px;margin-left: 10px;">
								<span class="glyphicon glyphicon-plus project-photo-add"  aria-hidden="true"></span>
							</a>
						</small> -->
					</h4>
				</div>
			</div>
			
			<div class="row pic-album">
				<div id="contract-pic">
					<jsp:include page="projectPhotoPart.jsp"></jsp:include>
				</div>
				<div class="col-lg-4 col-md-4" >
				    <div class="thumbnail" >
					      <img  src="${pageContext.request.contextPath}/image/image.png" alt=""> 
					      <div class="caption " id="project-photo-add" style="text-align:center;">
						       <!--  <button class="btn btn-primary  project-photo-add" style="text-align: center;">添加合同照片</button> -->
						   </div>
				 	 </div>
				</div>
			</div>
		</div>
	</section>
</body>
</html>