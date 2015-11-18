<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
							
							<div class="row">
								<div class="col-lg-4 col-md-4">
										<div class="col-lg-4 col-md-4 propName">
											<span class="">
												项目时间：
											</span>
										</div>
										<div class="col-lg-8 col-md-8 propValue">
											<span class="">
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
											<span class="">
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
											<span class="">
												${project.investAmount}万
												&nbsp;
											</span>
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
											<span class="">
												${project.classByProfession}
												&nbsp;
											</span>
										</div>
								</div>
								<div class="col-lg-4 col-md-4">
										<div class="col-lg-4 col-md-4 propName">
											<span class="">
												设计阶段：
											</span>
										</div>
										<div class="col-lg-8 col-md-8 propValue" >
											<span class="">
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
											<span class="">
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
											<span class="">
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
											<span class="">
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
											<span class="">
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
											<span class="">
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
											<span class="">
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
											<span class="">
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
											<span class="">
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
											<span class="">
												${project.contractNames}
												&nbsp;
											</span>
										</div>
								</div>
							</div>
							
						
						
