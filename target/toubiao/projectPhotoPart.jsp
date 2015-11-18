<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<%-- <c:set var="size"><c:out value="${fn:length(project.projectPhotoSet)}"></c:out> </c:set>
	<c:choose>
    	<c:when test="${size>0}"> --%>
			<c:forEach items="${projectPhotoList}" var="photo">
		       <div class="col-lg-4 col-md-4" id="project-photo-${photo.id}">
				    <div class="thumbnail" >
					      <img id="project-photo-${photo.id}-img" src="${pageContext.request.contextPath}${photo.webPath}" alt="" style="height: 270px;">
					      <div class="caption">
						        <h5 id="project-photo-${photo.id}-title" style="font:normal normal bold 1.1em/1.1em '微软雅黑';text-align: center; white-space:nowrap;overflow:hidden;text-overflow:ellipsis;">${photo.title}</h5>
						        <div id="project-photo-${photo.id}-description" class="handle-dot" style="height: 60px;">
						        	${photo.description}
						        </div>
						        <div class="project-photo-btns" style="margin: auto;text-align: center;padding-top: 20px;">
						        	<button class="btn btn-primary btn-project-photo-edit" data-id="${photo.id}" style="width: 70px; margin-right: 20px;">编辑</button>
						        	<button class="btn btn-warning btn-project-photo-delete" data-id="${photo.id}" style="width: 70px; ">删除</button>
							     </div>
						   </div>
				 	 </div>
				</div>
		    </c:forEach>
		    <script>
			  
		    </script>
		    
		    
		    
<%-- 	 </c:when>
	<c:otherwise>   --%>
		
<%-- 	</c:otherwise>  
	</c:choose> --%>
	<%--  以此为标准，用foreach 生成多个
	<div class="col-lg-4 col-md-4">
	    <div class="thumbnail" >
		      <img src="d:\1.jpg" alt="" style="height: 270px;">
		      <div class="caption">
			        <h5 style="font:normal normal bold 1.1em/1.1em '微软雅黑';text-align: center;">2012年度部级优质通信工程三等奖</h5>
			        <div class="handle-dot" style="height: 60px;">
			        	获奖名单:获奖名单：段筱兰 朱光军 张开进 曹曙光 张超 郑少锋 邱巍 谭林果 赵宇 樊景 何胜永 韩冰心 张九丽 李红伟 曹修铭
			        	获奖名单：段筱兰 朱光军 张开进 曹曙光 张超 郑少锋 邱巍 谭林果 赵宇 樊景 何胜永 韩冰心 张九丽 李红伟 曹修铭
			        	获奖名单：段筱兰 朱光军 张开进 曹曙光 张超 郑少锋 邱巍 谭林果 赵宇 樊景 何胜永 韩冰心 张九丽 李红伟 曹修铭
			        </div>
			        <div class="pic-btn" style="margin: auto;text-align: center;padding-top: 20px;">
			        	<button class="btn btn-primary" style="width: 70px; margin-right: 20px;">编辑</button>
			        	<button class="btn btn-warning" style="width: 70px; ">删除</button>
				     </div>
			   </div>
	 	 </div>
	</div> --%>
	<%-- <div class="col-lg-4 col-md-4">
	    <div class="thumbnail">
	      <img src="${pageContext.request.contextPath}/image/3.jpg" alt="">
	      <div class="caption">
	        <h5 style="font:normal normal bold 1.1em/1.1em '微软雅黑';text-align: center;">2012年度部级优质通信工程三等奖</h5>
	        <p>获奖名单：段筱兰 朱光军 张开进 曹曙光 张超 郑少锋 邱巍 谭林果 赵宇 樊景 何胜永 韩冰心 张九丽 李红伟 曹修铭 </p>
	        <div class="pic-btn" style="margin: auto;text-align: center;padding-top: 10px;">
	        	<button class="btn btn-primary" style="width: 70px; margin-right: 20px;">编辑</button>
	        	<button class="btn btn-warning" style="width: 70px; ">删除</button>
	        </div>
	        	
	      </div>
	   </div>
	</div>
	<div class="col-lg-4 col-md-4">
	   <div class="thumbnail">
	     <img src="${pageContext.request.contextPath}/image/2.jpg" alt="" style="height: 270px;">
	      <div class="caption">
	        <h5 style="font:normal normal bold 1.1em/1.1em '微软雅黑';text-align: center;">2012年度部级优质通信工程三等奖</h5>
	        <p>获奖名单：段筱兰 朱光军 张开进 曹曙光 张超 郑少锋 邱巍 谭林果 赵宇 樊景 何胜永 韩冰心 张九丽 李红伟 曹修铭 </p>
	        <div class="pic-btn" style="margin: auto;text-align: center;padding-top: 10px;">
	        	<button class="btn btn-primary" style="width: 70px; margin-right: 20px;">编辑</button>
	        	<button class="btn btn-warning" style="width: 70px; ">删除</button>
	        </div>
	        	
	      </div>
	   </div>
	</div> --%>
						
						
						
