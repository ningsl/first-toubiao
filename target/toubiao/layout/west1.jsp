<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script type="text/javascript">
	var achievementMenvTree;
	var achievementMenvTree_url='';
	var layout_west_tree;
	var layout_west_tree_url = '';
	var layout_west_accordion;
	
	var sessionInfo_userId = '${sessionInfo.id}';
	console.log(sessionInfo_userId);
	
	/* if (sessionInfo_userId) { */
		var layout_west_menuTreeUrl = '${pageContext.request.contextPath}/resourceController/menuTree';
	/* } */
	
	console.log(layout_west_menuTreeUrl);
	
	function createModuleDiv(moduleList){
		layout_west_accordion=$('#layout_west_accordion');
		$.each(moduleList,function(n,module){
			var moduleDiv=$('<div></div>');
			moduleDiv.attr({		
			'id':module.id, 
			'title':module.name,
			'data-options':"border:false,iconCls:'anchor'"
			});
			var menuTreeId="layout_west_"+module.id+"Menu";
			var treeDiv=$('<div class="well well-small"><ul id='+menuTreeId+'></ul></div>');
			moduleDiv.append(treeDiv);

			console.log($("#"+menuTreeId));
			console.log(module);
			layout_west_accordion.append(moduleDiv);

			var layout_west_menuTree= $("#"+menuTreeId).tree({
				 	url : layout_west_menuTreeUrl+'?moduleId='+module.id,
					parentField : 'pid',
					onClick : function(node) {
						if (node.attributes && node.attributes.url) {
							var url;
							if (node.attributes.url.indexOf('/') == 0) {/*如果url第一位字符是"/"，那么代表打开的是本地的资源*/
								url = '${pageContext.request.contextPath}' + node.attributes.url;
								if (url.indexOf('/druidController') == -1) {/*如果不是druid相关的控制器连接，那么进行遮罩层屏蔽*/
									parent.$.messager.progress({
										title : '提示',
										text : '数据处理中，请稍后....'
									});
									
								}
							} else {/*打开跨域资源*/
								url = node.attributes.url;
							}
							addTab({
								url : url,
								title : node.text,
								iconCls : node.iconCls
							});
						}
					},
					onBeforeLoad : function(node, param) {
						if (layout_west_menuTreeUrl) {//只有刷新页面才会执行这个方法
							parent.$.messager.progress({
								title : '提示',
								text : '数据处理中，请稍后....'
							});
							
						}
					},
					onLoadSuccess : function(node, data) {
						parent.$.messager.progress('close');
					}
			  });
		});
		$('#layout_west_accordion').accordion({    
		    animate:true   
		});  
		
		/*  $("#layout_west_achievementMenu").tree({
			 	url : layout_west_menuTreeUrl,
				parentField : 'pid'
		  });
		 */
	}
	
	$(function() {
		
		//获得模块数据 
		var moduleArray;
		$.ajax({ 
		   url: '${pageContext.request.contextPath}/resourceController/module', 
		   beforeSend:function() {
						parent.$.messager.progress({
							title : '提示',
							text : '数据处理中，请稍后!!!!'
						});
		 	},
			complete:function(data) {
				parent.$.messager.progress('close');
				 
		 	},
		   success: function(moduleList) {
			   moduleArray=$.parseJSON(moduleList);
			   parent.$.messager.progress('close');
			   console.log("in fun");
			   console.log(moduleArray);
			   createModuleDiv(moduleArray);
			  
		 	},
		   error: function(data) {
			         alert("模块读取异常！");
					}
			});
		
		 /*   console.log(moduleArray); */
		 console.log("end fun");
		 
		 
		 
	});

	function addTab(params) {
		var iframe = '<iframe src="' + params.url + '" frameborder="0" style="border:0;width:100%;height:98%;"></iframe>';
		var t = $('#index_tabs');
		var opts = {
			title : params.title,
			closable : true,
			iconCls : params.iconCls,
			content : iframe,
			border : false,
			fit : true
		};
		if (t.tabs('exists', opts.title)) {
			t.tabs('select', opts.title);
			parent.$.messager.progress('close');
		} else {
			t.tabs('add', opts);
		}
	}
</script>
<div id='layout_west_accordion'  data-options="fit:true,border:false">
	<!-- <div title="系统菜单" style="padding: 5px;" data-options="border:false,isonCls:'anchor',tools : [ {
				iconCls : 'database_refresh',
				handler : function() {
					$('#layout_west_tree').tree('reload');
				}
			}, {
				iconCls : 'resultset_next',
				handler : function() {
					var node = $('#layout_west_tree').tree('getSelected');
					if (node) {
						$('#layout_west_tree').tree('expandAll', node.target);
					} else {
						$('#layout_west_tree').tree('expandAll');
					}
				}
			}, {
				iconCls : 'resultset_previous',
				handler : function() {
					var node = $('#layout_west_tree').tree('getSelected');
					if (node) {
						$('#layout_west_tree').tree('collapseAll', node.target);
					} else {
						$('#layout_west_tree').tree('collapseAll');
					}
				}
			} ]">
		<div class="well well-small">
			<ul id="layout_west_tree"></ul>
		</div>
	</div>
	<div title="其他示例" data-options="border:false,iconCls:'anchor'">
		<ul>
			<li>菜单</li>
			<li>菜单</li>
			<li>菜单</li>
		</ul>
	</div>
	<div title="业绩模块" data-options="border:false,iconCls:'anchor'">
		<div class="well well-small">
			<ul id="layout_west_achievementMenvTree"></ul>
		</div>
	</div>
	 -->
	
</div>