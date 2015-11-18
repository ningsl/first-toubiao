<%@page import="toubiao.pageModel.Project"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  
<%@page import="java.util.Date"%>
<%@page import="java.util.Calendar"%>
<form class="form-horizontal" id="detail-form" method="get">
	<input type="hidden" id="project-id" name="id" value="${project.id}">
	<div class="row">
		<div class="col-lg-4 col-md-4">
			<div class="form-group">
		   		<label for="inputEmail3" class="control-label propName col-lg-4 col-md-4 " >项目时间：</label>
			    <div class="col-lg-8 col-md-8 propValue">
			     	<select class="form-control" name='beginDate'>
		     			<option  value="" ${project.beginDate == null ?"selected= 'selected'" : ""} >请选择</option>
						<c:forEach var="i"  begin="2000"  end="<%=Calendar.getInstance().get(Calendar.YEAR)%>">
							<option value="${i}-01-01 00:00:00" ${project.beginDate.year+1900 == i ?"selected= 'selected'" : ""}><c:out value="${i}"></c:out>年</option>
						</c:forEach>
					</select>
			    </div>
		  	</div>
		</div>
		<div class="col-lg-4 col-md-4">
			<div class="form-group">
		   		<label for="inputEmail3" class="control-label col-lg-4 col-md-4 propName" >项目编号：</label>
			    <div class="col-lg-8 col-md-8 propValue">
			      <input type="text" class="form-control " id="inputEmail3" name='designCode' value="${project.designCode}">
			      <input type="hidden"  name="achievementCode" value="${project.achievementCode}">
			    </div>
		  	</div>
		</div>
		<div class="col-lg-4 col-md-4">
			<div class="form-group">
		   		<label for="inputEmail3" class="control-label col-lg-4 col-md-4 propName">投资金额：</label>
			    <div class="col-lg-8 col-md-8 propValue">
			    	<div class="input-group">
						  <input type="text" class="form-control" name='investAmount' value="${project.investAmount}" aria-label="Amount (to the nearest dollar)">
						  <span class="input-group-addon">万</span>
					</div>
			    </div>
		  	</div>
		</div>
	</div>	
	
	<div class="row">
		<div class="col-lg-4 col-md-4">
			<div class="form-group">
		   		<label for="inputEmail3" class="control-label propName col-lg-4 col-md-4 " >项目专业：</label>
			    <div class="col-lg-8 col-md-8 propValue">
			      	<input type="text" class="form-control " id="inputEmail3" name='classByProfession' value="${project.classByProfession}">										    	
			    </div>
		  	</div>
		</div>
		<div class="col-lg-4 col-md-4">
			<div class="form-group">
		   		<label for="inputEmail3" class="control-label col-lg-4 col-md-4 propName" >设计阶段：</label>
			    <div class="col-lg-8 col-md-8 propValue">
			      <input type="text" class="form-control " id="inputEmail3"  name='classByPhase' value="${project.classByPhase}">
			    </div>
		  	</div>
		</div>
		<div class="col-lg-4 col-md-4">
			<div class="form-group">
		   		<label for="inputEmail3" class="control-label col-lg-4 col-md-4 propName">项目分类：</label>
			    <div class="col-lg-8 col-md-8 propValue">
			      	<input type="text" class="form-control " id="inputEmail3"   name='classByImportance' value="${project.classByImportance}">										    
			    </div>
		  	</div>
		</div>
	</div>
	
	<div class="row">
		<div class="col-lg-4 col-md-4">
			<div class="form-group">
		   		<label for="inputEmail3" class="control-label propName col-lg-4 col-md-4 " >承担部门：</label>
			    <div class="col-lg-8 col-md-8 propValue">
			      	<%-- <input type="text" class="form-control" id="inputEmail3"  name='departmentNames'  value="${project.departmentNames}"> --%>										    	
			    	 <div class="input-group">
					      <input type="text" class="form-control" id="departmentNames" name="departmentNames" value="${project.departmentNames}" readonly >
					   	  <input type="hidden" id="departmentIds" name="departmentIds" value="${project.departmentIds}">
					      <span class="input-group-btn">
					      		<button class="btn btn-default" type="button" id="btn_select_department">
					      			<span class="caret"></span>
					      		</button>
					      </span>
			   	 	</div>
			    </div>
		  	</div>
		</div>
		<div class="col-lg-4 col-md-4">
			<div class="form-group">
		   		<label for="inputEmail3" class="control-label col-lg-4 col-md-4 propName" >设计负责：</label>
			    <div class="col-lg-8 col-md-8 propValue">
			     <%--  <input type="text" class="form-control " id="inputEmail3" value="${project.designerNames}"> --%>
			      <div class="input-group">
				      <input type="text" class="form-control" id="designerNames" name="designerNames" value="${project.designerNames}" readonly >
				   	  <input type="hidden" id="designerIds" name="designerIds" value="${project.designerIds}">
				      <span class="input-group-btn">
				      		<button class="btn btn-default" type="button" id="btn_select_designers">
				      			<span class="caret"></span>
				      		</button>
				      </span>
			   	 </div>
			    </div>
		  	</div>
		</div>
		<div class="col-lg-4 col-md-4">
			<div class="form-group">
		   		<label for="inputEmail3" class="control-label col-lg-4 col-md-4 propName">负责人电话：</label>
			    <div class="col-lg-8 col-md-8 propValue">
			      	<input type="text" class="form-control " id="designerPhones"   name='designerPhones' value="${project.designerPhones}" readonly>										    
			    </div>
		  	</div>
		</div>
	</div>
	
	<div class="row">
		<div class="col-lg-4 col-md-4">
			<div class="form-group">
		   		<label for="inputEmail3" class="control-label propName col-lg-4 col-md-4 " >委托单位：</label>
			    <div class="col-lg-8 col-md-8 propValue">
			      	<input type="text" class="form-control " id="inputEmail3"   name='partA' value="${project.partA}">										    	
			    </div>
		  	</div>
		</div>
		<div class="col-lg-4 col-md-4">
			<div class="form-group">
		   		<label for="inputEmail3" class="control-label col-lg-4 col-md-4 propName" >委托人：</label>
			    <div class="col-lg-8 col-md-8 propValue">
			      <input type="text" class="form-control " id="inputEmail3"   name='contact.name' value="${project.contactName}">
			    </div>
		  	</div>
		</div>
		<div class="col-lg-4 col-md-4">
			<div class="form-group">
		   		<label for="inputEmail3" class="control-label col-lg-4 col-md-4 propName">委托人电话：</label>
			    <div class="col-lg-8 col-md-8 propValue">
			      	<input type="text" class="form-control " id="inputEmail3"   name='contact.telephone' value="${project.contactPhone}">										    
			    </div>
		  	</div>
		</div>
	</div>
	
	<div class="row">
		<div class="col-lg-4 col-md-4">
			<div class="form-group">
		   		<label for="inputEmail3" class="control-label propName col-lg-4 col-md-4 " >合同编号：</label>
			    <div class="col-lg-8 col-md-8 propValue">
			      	<input type="text" class="form-control " id="inputEmail3"   name='contractSns' value="${project.contractSns}">										    	
			    </div>
		  	</div>
		</div>
		<div class="col-lg-8 col-md-8">
			<div class="form-group">
		   		<label for="inputEmail3" class="control-label col-lg-2 col-md-2 propName" >合同名称：</label>
			    <div class="col-lg-10 col-md-10 propValue">
			      <input type="text" class="form-control " id="inputEmail3"   name='contractNames' value="${project.contractNames}">
			    </div>
		  	</div>
		</div>
	</div>
</form>	