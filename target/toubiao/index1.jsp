<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>SyPro</title>
<jsp:include page="inc.jsp"></jsp:include>
<script type="text/javascript">
/**
* jQuery EasyUI 1.3.6
* Copyright (c) 2009-2013 www.jeasyui.com. All rights reserved.
*
* Licensed under the GPL or commercial licenses
* To use it on other terms please contact us: jeasyui@gmail.com
* http://www.gnu.org/licenses/gpl.txt
* http://www.jeasyui.com/license_commercial.php
*
* jQuery EasyUI layout 组件扩展
* jeasyui.extensions.layout.self.js
* 开发 李溪林
* 最近更新：2015-05-08
*
* 依赖项：
*   1、jquery.jdirk.js v1.0 beta late
*   2、jeasyui.extensions.js v1.0 beta late
*
* Copyright (c) 2015 Lixilin personal All rights reserved.
* http://www.chenjianwei.org
*/

/*
功能说明：
*/




 (function ($, undefined) {

    var _onCollapse = $.fn.layout.paneldefaults.onCollapse;
    var showTitleOnCollapse = function () {
        if ($.isFunction) { _onCollapse.call(this); }

        var layout = $(this).parents(".layout"), layoutOpts = layout.data("layout").options;
        if (!layoutOpts.showTitleOnCollapse) { return; }

        //获取当前region的配置属性
        var opts = $(this).panel("options");
        //获取key
        var expandKey = "expand" + opts.region.substring(0, 1).toUpperCase() + opts.region.substring(1);
        //从layout的缓存对象中取得对应的收缩对象
        var expandPanel = layout.data("layout").panels[expandKey];
        if (expandPanel) {
            //存在，说明是调用Collapse方法触发onCollapse

            var title = opts.collapseTitle == null ? opts.title : opts.collapseTitle;
            //针对横向和竖向的不同处理方式
            if (opts.region == "west" || opts.region == "east") {
                //竖向的文字打竖,其实就是切割文字加br
                var split = [];
                for (var i = 0; i < title.length; i++) {
                    split.push(title.substring(i, i + 1));
                }
                expandPanel.panel("body").addClass("panel-title").css("text-align", "center").html(split.join("<br />"));
            } else {
                expandPanel.panel("setTitle", title);
            }
        }
        else {
            //不存在，说明初始化的options中已设定了Collapse属性为false，暂不处理
            //alert(expandKey);
        }
    };

    var defaults = {
        //  增加 easyui-layout 控件的自定义属性，该属性表示当 easyui-layout 的 panel 折叠时是否显示其 collapseTitle ，默认为 false 。
        showTitleOnCollapse: false
    };

    var paneldefaults = {
        //  增加 easyui-layout 控件 的 panel 的自定义属性，该属性表示在 easyui-layout 的 panel 折叠时要显示的面板标题。
        //  该属性为 null 时表示使用 easyui-layout 的 panel 的 title 作为其值。
        collapseTitle: null,
        //  重写 easyui-layout 控件的 panel 配置中的原生事件 onCollapse，以支持相应扩展功能。
        onCollapse: showTitleOnCollapse
    };

    if ($.fn.layout.extensions != null && $.fn.layout.extensions.defaults != null) {
        $.extend($.fn.layout.extensions.defaults, defaults);
    }
    $.extend($.fn.layout.defaults, defaults);

  /*   if ($.fn.layout.paneldefaults != null && $.fn.layout.extensions.paneldefaults != null) {
        $.extend($.fn.layout.extensions.paneldefaults, paneldefaults);
    } */
    $.extend($.fn.layout.paneldefaults, paneldefaults);

})(jQuery);

</script>
<script type="text/javascript">
	var index_tabs;
	var index_tabsMenu;
	var index_layout;
	$(function() {
		index_layout = $('#index_layout').layout({
			fit : true,
			showTitleOnCollapse:true
		});
		/*index_layout.layout('collapse', 'east');*/
		
		console.info(index_layout);
		
		
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
			} ]
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
		<div data-options="region:'north'" style="height: 70px; overflow: hidden;" class="logo">hello js</div>
		<div data-options="region:'west',split:true,title:'test'"  style="width: 200px; overflow: hidden;">
			<div class="easyui-panel" title="basic"  style="height: 0px;" data-options="border:false,maxHeight:0,fit:true"></div>
			<div class="easyui-accordion" data-options="fit:'true'">
				<div title="Title2">a</div>
				<div title="Title2">b</div>
			</div>
		</div> 
		<div data-options="region:'center'" title="欢迎使用本投标平台" style="overflow: hidden;">
			<div id="index_tabs" style="overflow: hidden;">
				<div title="首页" data-options="border:false" style="overflow: hidden;">
					<iframe src="${pageContext.request.contextPath}/portal/index.jsp" frameborder="0" style="border: 0; width: 100%; height: 98%;"></iframe>
				</div>
			</div>
		</div>
		
		<div data-options="region:'south',title:'foot',href:'${pageContext.request.contextPath}/layout/south.jsp',border:false,collapsible:true" style="height: 130px; overflow: hidden;"></div>
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