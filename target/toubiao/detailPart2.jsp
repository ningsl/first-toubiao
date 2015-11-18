<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@page import="java.util.Date"%>
<%@page import="java.util.Calendar"%>
	<section class="info">
		<div class="infoHead">
			<div class="row">
				<div class="col-lg-12 col-md-12 ">
					<h3>项目详情&nbsp;&nbsp;
						<small>
							<span id="project-projectName">${project.projectName}</span>
							<a href="#" style="margin-top: 0px;margin-left: 10px;" id="detail-edit-toggle">
								<span class="glyphicon glyphicon-pencil"  aria-hidden="true"></span>
							</a>
						</small>
					</h3>
				</div>
			</div>
		</div>
	
		<div class="infoBody" id="detail-info">
			<div class="row">
				<div class="col-lg-4 col-md-4">
						<div class="col-lg-4 col-md-4 propName">
							<span class="">
								项目时间：
							</span>
						</div>
						<div class="col-lg-8 col-md-8 propValue">
							<span class="" id="project-beginDate">
								<fmt:formatDate value="${project.beginDate}" pattern="yyyy年"/>  
								&nbsp;
							</span>
								
						</div>
				</div>
				<div class="col-lg-4 col-md-4">
						<div class="col-lg-4 col-md-4 propName">
							<span class="">
								设计编号：
							</span>
						</div>
						<div class="col-lg-8 col-md-8 propValue">
							<span class="" id="project-designCode">
								${project.designCode}
								&nbsp;
							</span>
							
						</div>
				</div>
				<div class="col-lg-4 col-md-4">
						<div class="col-lg-4 col-md-4 propName">
							<span class="">
								投资金额：
							</span>
						</div>
						<div class="col-lg-8 col-md-8 propValue">
							<span class="" id="project-investAmount">
								${project.investAmount}
							</span>
							<span>万&nbsp;</span>	
						</div>
				</div>								
			</div>
				
			<div class="row">
				<div class="col-lg-4 col-md-4">
						<div class="col-lg-4 col-md-4 propName">
							<span class="">
								项目专业：
							</span>
						</div>
						<div class="col-lg-8 col-md-8 propValue">
							<span class="" id="project-classByProfession">
								${project.classByProfession}
								&nbsp;
							</span>
						</div>
				</div>
				<div class="col-lg-4 col-md-4">
						<div class="col-lg-4 col-md-4 propName">
							<span class="" >
								设计阶段：
							</span>
						</div>
						<div class="col-lg-8 col-md-8 propValue" >
							<span class="" id="project-classByPhase">
								${project.classByPhase}
								&nbsp;
							</span>
						</div>
				</div>
				<div class="col-lg-4 col-md-4">
						<div class="col-lg-4 col-md-4 propName">
							<span class="">
								项目分类：
							</span>
						</div>
						<div class="col-lg-8 col-md-8 propValue">
							<span class=""  id="project-classByImportance">
								${project.classByImportance}
								&nbsp;
							</span>
						</div>
				</div>								
			</div>
			
			<div class="row">
				<div class="col-lg-4 col-md-4">
						<div class="col-lg-4 col-md-4 propName">
							<span class="">
								承担部门：
							</span>
						</div>
						<div class="col-lg-8 col-md-8 propValue">
							<input type="hidden" id="project-departmentIds" value="${project.departmentIds}">
							<span class="" id="project-departmentNames">
								${project.departmentNames}
								&nbsp;
							</span>
						</div>
				</div>
				<div class="col-lg-4 col-md-4">
						<div class="col-lg-4 col-md-4 propName">
							<span class="">
								设计负责：
							</span>
						</div>
						<div class="col-lg-8 col-md-8 propValue" >
							<input type="hidden" id="project-designerIds" value="${project.designerIds}">
							<span class="" id="project-designerNames">
								${project.designerNames}
								&nbsp;
							</span>
						</div>
				</div>
				<div class="col-lg-4 col-md-4">
						<div class="col-lg-4 col-md-4 propName">
							<span class="">
								负责人电话：
							</span>
						</div>
						<div class="col-lg-8 col-md-8 propValue">
							<span class="" id="project-designerPhones">
								${project.designerPhones}
								&nbsp;
							</span>
						</div>
				</div>								
			</div>
			
			<div class="row">
				<div class="col-lg-4 col-md-4">
						<div class="col-lg-4 col-md-4 propName">
							<span class="">
								委托单位：
							</span>
						</div>
						<div class="col-lg-8 col-md-8 propValue">
							<span class="" id="project-partA">
								${project.partA}
								&nbsp;
							</span>
						</div>
				</div>
				<div class="col-lg-4 col-md-4">
						<div class="col-lg-4 col-md-4 propName">
							<span class="">
								委托人：
							</span>
						</div>
						<div class="col-lg-8 col-md-8 propValue" >
							<input type="hidden" id="project-contactId" value="${project.contactId}">
							<span class="" id="project-contactName" >
								${project.contactName}
								&nbsp;
							</span>
						</div>
				</div>
				<div class="col-lg-4 col-md-4">
						<div class="col-lg-4 col-md-4 propName">
							<span class="">
								委托人电话：
							</span>
						</div>
						<div class="col-lg-8 col-md-8 propValue">
							<span class="" id="project-contactPhone">
								${project.contactPhone}
								&nbsp;
							</span>
						</div>
				</div>								
			</div>
			
			<div class="row">
				<div class="col-lg-4 col-md-4">
						<div class="col-lg-4 col-md-4 propName">
							<span class="">
								合同编号：
							</span>
						</div>
						<div class="col-lg-8 col-md-8 propValue">
							<input type="hidden" id="project-contractIds" value="${project.contractIds}">
							<span class="" id="project-contractSns">
								${project.contractSns}
								&nbsp;
							</span>
						</div>
				</div>
				<div class="col-lg-8 col-md-8">
						<div class="col-lg-2 col-md-2 propName">
							<span class="" >
								合同名称：
							</span>
						</div>
						<div class="col-lg-10 col-md-10 propValue" >
							<span class="" id="project-contractNames">
								${project.contractNames}
								&nbsp;
							</span>
						</div>
				</div>
			</div>
		</div>
	
		<div id="detail-edit" class="editBody">
			<form class="form-horizontal" id="detail-form" method="get">
				<input type="hidden" id="form-project-id" name="id" value="${project.id}">
				<div class="row">
					<div class="col-lg-4 col-md-4">
						<div class="form-group">
					   		<label for="inputEmail3" class="control-label propName col-lg-4 col-md-4 " >项目时间：</label>
						    <div class="col-lg-8 col-md-8 propValue">
						     	<select class="form-control" name='beginDate' id="form-project-beginDate">
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
						      <input type="text" class="form-control " id="form-project-designCode" name='designCode' value="${project.designCode}">
						      <input type="hidden"  name="achievementCode" value="${project.achievementCode}">
						    </div>
					  	</div>
					</div>
					<div class="col-lg-4 col-md-4">
						<div class="form-group">
					   		<label for="inputEmail3" class="control-label col-lg-4 col-md-4 propName">投资金额：</label>
						    <div class="col-lg-8 col-md-8 propValue">
						    	<div class="input-group">
									  <input type="text" class="form-control" id="form-project-investAmount" name='investAmount' value="${project.investAmount}" aria-label="Amount (to the nearest dollar)">
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
						      	<input type="text" class="form-control " id="form-project-classByProfession" name='classByProfession' value="${project.classByProfession}">										    	
						    </div>
					  	</div>
					</div>
					<div class="col-lg-4 col-md-4">
						<div class="form-group">
					   		<label for="inputEmail3" class="control-label col-lg-4 col-md-4 propName" >设计阶段：</label>
						    <div class="col-lg-8 col-md-8 propValue">
						      <input type="text" class="form-control " id="form-project-classByPhase"  name='classByPhase' value="${project.classByPhase}">
						    </div>
					  	</div>
					</div>
					<div class="col-lg-4 col-md-4">
						<div class="form-group">
					   		<label for="inputEmail3" class="control-label col-lg-4 col-md-4 propName">项目分类：</label>
						    <div class="col-lg-8 col-md-8 propValue">
						      	<input type="text" class="form-control " id="form-project-classByImportance"   name='classByImportance' value="${project.classByImportance}">										    
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
								      <input type="text" class="form-control" id="form-project-departmentNames" name="departmentNames" value="${project.departmentNames}" readonly >
								   	  <input type="hidden" id="form-project-departmentIds" name="departmentIds" value="${project.departmentIds}">
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
							      <input type="text" class="form-control" id="form-project-designerNames" name="designerNames" value="${project.designerNames}" readonly >
							   	  <input type="hidden" id="form-project-designerIds" name="designerIds" value="${project.designerIds}">
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
						      	<input type="text" class="form-control " id="form-project-designerPhones"   name='designerPhones' value="${project.designerPhones}" readonly>										    
						    </div>
					  	</div>
					</div>
				</div>
				
				<div class="row">
					<div class="col-lg-4 col-md-4">
						<div class="form-group">
					   		<label for="inputEmail3" class="control-label propName col-lg-4 col-md-4 " >委托单位：</label>
						    <div class="col-lg-8 col-md-8 propValue">
						      	<input type="text" class="form-control " id="form-project-partA"   name='partA' value="${project.partA}">										    	
						    </div>
					  	</div>
					</div>
					<div class="col-lg-4 col-md-4">
						<div class="form-group">
					   		<label for="inputEmail3" class="control-label col-lg-4 col-md-4 propName" >委托人：</label>
						    <div class="col-lg-8 col-md-8 propValue">
						      <input type="hidden" id="form-project-contactId" name="contactId" value="${project.contactId}">
						      <input type="text" class="form-control " id="form-project-contactName"   name='contactName' value="${project.contactName}">
						    </div>
					  	</div>
					</div>
					<div class="col-lg-4 col-md-4">
						<div class="form-group">
					   		<label for="inputEmail3" class="control-label col-lg-4 col-md-4 propName">委托人电话：</label>
						    <div class="col-lg-8 col-md-8 propValue">
						      	<input type="text" class="form-control " id="form-project-contactPhone"   name='contactPhone' value="${project.contactPhone}">										    
						    </div>
					  	</div>
					</div>
				</div>
				
				<div class="row">
					<div class="col-lg-4 col-md-4">
						<div class="form-group">
					   		<label for="inputEmail3" class="control-label propName col-lg-4 col-md-4 " >合同编号：</label>
						    <div class="col-lg-8 col-md-8 propValue">
						    	<input type="hidden" id="form-project-contractIds" name="contractIds" value="${project.contractIds}">
						      	<input type="text" class="form-control " id="form-project-contractSns"   name='contractSns' value="${project.contractSns}">										    	
						    </div>
					  	</div>
					</div>
					<div class="col-lg-8 col-md-8">
						<div class="form-group">
					   		<label for="inputEmail3" class="control-label col-lg-2 col-md-2 propName" >合同名称：</label>
						    <div class="col-lg-10 col-md-10 propValue">
						      <input type="text" class="form-control " id="contractNames"   name='contractNames' value="${project.contractNames}">
						    </div>
					  	</div>
					</div>
				</div>
			</form>	
		</div>	
		
		
	</section>
					
					
	<section class="info">
		<div class="infoHead">
			<div class="row">
				<div class="col-lg-12 col-md-12 ">
					<h3>项目描述&nbsp;&nbsp;
						<small>
							项目背景&nbsp;&nbsp;|&nbsp;&nbsp;工程规模&nbsp;&nbsp;|&nbsp;&nbsp;人员分工
						</small>
					</h3>
				</div>
			</div>
		</div>
			
		<div class="infoBody">
			<div class="row" id="description-part">
				<div class="col-lg-12 col-md-12 ">
					<div class="description" id="description">
						<div class="panel panel-default">
							<div class="panel-body" id="description-text">
								${project.description}	
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
			
		<div id="description-edit-part">
			<div class="row">
					<div class="col-lg-12 col-md-12 ">
						<div class="editDescription" style="margin-top:10px;margin-bottom:-5px">
							<div class="form-group">
						   		<label for="inputEmail3" class="sr-only" >Description</label>
						   		<textarea class="form-control" id="description-textarea" rows='6' style="line-height:2.5;resize: none;">${project.description}</textarea>
						  	</div>
					  	</div>
					  <%-- 	<div style="text-align:center;" >
					  		<button type="button" class="btn  btn-info" id='description-edit-cancle' data-id="${project.id }" style="width:120px">取消</button>
							<button type="button" class="btn  btn-success" id='description-edit-save' data-id="${project.id }" style="width:120px;margin-left: 40px;">保存</button>
					  	</div> --%>
					</div>
			</div>
		</div>
	</section>

							
						
						
