<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>SyPro</title>
<jsp:include page="inc.jsp"></jsp:include>
<script type="text/javascript">
	var index_tabs;
	var index_tabsMenu;
	var index_layout;
	var webRootPath=nsl.getContextPath();
	
	//添加edit project  detail tabl
	function addProjectDetailTab(projectId,designCode) {
		var url=webRootPath+"/projectController/projectDetail?projectId="+projectId;
		var iframe = '<iframe src="' + url + '" frameborder="0" style="border:0;width:100%;height:98%;"></iframe>';
		var t = $('#index_tabs');
		console.log(t);
		var opts = {
			title : "项目:"+designCode,
			closable : true,
			content :iframe ,
			border : false,
			fit : true
		};
		t.tabs('add', opts);
	} 
	
	//添加 new project detail tabl
	function addNewProjectTab() {
		var url=webRootPath+"/projectController/addProject";
		var iframe = '<iframe src="' + url + '" frameborder="0" style="border:0;width:100%;height:98%;"></iframe>';
		var t = $('#index_tabs');
		console.log(t);
		var opts = {
			title : "项目:新增",
			closable : true,
			content :iframe ,
			border : false,
			fit : true
		};
		t.tabs('add', opts);
	} 
	
	//添加edit designer  detail tabl
	function addDesignerDetailTab(designerId,name) {
		var url=webRootPath+"/designerController/designerDetail?projectId="+designerId;
		var iframe = '<iframe src="' + url + '" frameborder="0" style="border:0;width:100%;height:98%;"></iframe>';
		var t = $('#index_tabs');
		console.log(t);
		var opts = {
			title : "设计人员:"+name,
			closable : true,
			content :iframe ,
			border : false,
			fit : true
		};
		t.tabs('add', opts);
	} 
	
	//添加 new designer detail tabl
	function addNewProjectTab() {
		var url=webRootPath+"/projectController/addProject";
		var iframe = '<iframe src="' + url + '" frameborder="0" style="border:0;width:100%;height:98%;"></iframe>';
		var t = $('#index_tabs');
		console.log(t);
		var opts = {
			title : "项目:新增",
			closable : true,
			content :iframe ,
			border : false,
			fit : true
		};
		t.tabs('add', opts);
	} 
	
	$(function() {
		index_layout = $('#index_layout').layout({
			fit : true
		});
		/*index_layout.layout('collapse', 'east');*/

		index_tabs = $('#index_tabs').tabs({
			fit : true,
			border : false,
			onContextMenu : function(e, title) {
				e.preventDefault();
				index_tabsMenu.menu('show', {
					left : e.pageX,
					top : e.pageY
				}).data('tabTitle', title);
			},
			tools : [ {
				iconCls : 'database_refresh',
				handler : function() {
					var href = index_tabs.tabs('getSelected').panel('options').href;
					if (href) {/*说明tab是以href方式引入的目标页面*/
						var index = index_tabs.tabs('getTabIndex', index_tabs.tabs('getSelected'));
						index_tabs.tabs('getTab', index).panel('refresh');
					} else {/*说明tab是以content方式引入的目标页面*/
						var panel = index_tabs.tabs('getSelected').panel('panel');
						var frame = panel.find('iframe');
						try {
							if (frame.length > 0) {
								for ( var i = 0; i < frame.length; i++) {
									frame[i].contentWindow.document.write('');
									frame[i].contentWindow.close();
									frame[i].src = frame[i].src;
								}
								if (navigator.userAgent.indexOf("MSIE") > 0) {// IE特有回收内存方法
									try {
										CollectGarbage();
									} catch (e) {
									}
								}
							}
						} catch (e) {
						}
					}
				}
			}, {
				iconCls : 'delete',
				handler : function() {
					var index = index_tabs.tabs('getTabIndex', index_tabs.tabs('getSelected'));
					var tab = index_tabs.tabs('getTab', index);
					if (tab.panel('options').closable) {
						index_tabs.tabs('close', index);
					} else {
						$.messager.alert('提示', '[' + tab.panel('options').title + ']不可以被关闭！', 'error');
					}
				}
			} ,{
				iconCls : 'cancel',
				handler : function() {
					$.messager.confirm('确认对话框', '您想要退出该系统吗？', function(r){
						if (r){
						    // 退出操作;
							$.getJSON('${pageContext.request.contextPath}/userController/logout', {
								t : new Date()
							}, function(data) {
								console.log(data);
								
								if (data.success) {
									console.log("in logout funciton");
									location.replace('${pageContext.request.contextPath}/login.jsp');
								} else {
									//
									console.log("后台注销失败.");
									location.replace('${pageContext.request.contextPath}/login.jsp');
								}
							});
						}
					});
				}
			}]
		});

		index_tabsMenu = $('#index_tabsMenu').menu({
			onClick : function(item) {
				var curTabTitle = $(this).data('tabTitle');
				var type = $(item.target).attr('title');

				if (type === 'refresh') {
					index_tabs.tabs('getTab', curTabTitle).panel('refresh');
					return;
				}

				if (type === 'close') {
					var t = index_tabs.tabs('getTab', curTabTitle);
					if (t.panel('options').closable) {
						index_tabs.tabs('close', curTabTitle);
					}
					return;
				}

				var allTabs = index_tabs.tabs('tabs');
				var closeTabsTitle = [];

				$.each(allTabs, function() {
					var opt = $(this).panel('options');
					if (opt.closable && opt.title != curTabTitle && type === 'closeOther') {
						closeTabsTitle.push(opt.title);
					} else if (opt.closable && type === 'closeAll') {
						closeTabsTitle.push(opt.title);
					}
				});

				for ( var i = 0; i < closeTabsTitle.length; i++) {
					index_tabs.tabs('close', closeTabsTitle[i]);
				}
			}
		});
		
	});
</script>
</head>
<body>

	<%-- <jsp:include page="user/login.jsp"></jsp:include>
	<jsp:include page="user/reg.jsp"></jsp:include>   --%>

	<div id="index_layout">
		<%-- <div data-options="region:'north',href:'${pageContext.request.contextPath}/layout/north.jsp'" style="height: 70px; overflow: hidden;" class="logo"></div> --%>
		<div data-options="region:'west',href:'${pageContext.request.contextPath}/layout/west1.jsp',split:true"  style="width: 200px; overflow: hidden;"></div> 
		<div data-options="region:'center'" title="欢迎使用本投标平台" style="overflow: hidden;">
			<div id="index_tabs" style="overflow: hidden;">
				<div title="首页" data-options="border:false" style="overflow: hidden;">
					<iframe src="${pageContext.request.contextPath}/portal/index.jsp" frameborder="0" style="border: 0; width: 100%; height: 98%;"></iframe>
				</div>
			</div>
		</div>
		
		<div data-options="region:'south',href:'${pageContext.request.contextPath}/layout/south.jsp',border:false" style="height: 50px; overflow: hidden;"></div>
	</div>

	<div id="index_tabsMenu" style="width: 120px; display: none;">
		<div title="refresh" data-options="iconCls:'transmit'">刷新</div>
		<div class="menu-sep"></div>
		<div title="close" data-options="iconCls:'delete'">关闭</div>
		<div title="closeOther" data-options="iconCls:'delete'">关闭其他</div>
		<div title="closeAll" data-options="iconCls:'delete'">关闭所有</div>
	</div>

</body>
</html>