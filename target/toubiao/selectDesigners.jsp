<div id="cc" class="easyui-layout" data-options="fit:true">   
	<div data-options="region:'west',iconCls:'icon-reload',title:'人员分组' , collapsible:false " style="width:350px;">
		<div  style="vertical-align: middle;border-bottom: 1px solid #E3E3E3;margin-bottom: 10px;">
		   	<label for="exampleInputName2" style="margin-left: 10px;">姓名</label>
		    <input id="name" type="text" style="width: 70px;margin: 0 5px 0 5px;" placeholder="" >
			<button type="button" class="" id="filter">筛选</button>
			<button type="button" class="" id="reset">清除</button>
		</div>
		<div id="p" class="easyui-panel" data-options="border:false,noheader:true,fit:true">
			<ul id="designerTree"></ul>
		</div>
	</div>   	    
    <div data-options="region:'center'" style="background:#eee">
    	<div class="operators">
	    	<ul style="list-style: none">
	    		<li style="margin-bottom:20px,margin-left: 0px;">
	    			<input type="button" id="right" value="  >  " />
	    		</li>
	    		<li>
	    			<input type="button" id="left" value="  <  " />
	    		</li>
	    		<li>
	    			<input type="button" id="right_all" value=" >> " />
	    		</li>
	    		<li>
	    			<input type="button" id="left_all" value=" << " />
	    		</li>
	    	</ul>
    	</div>
    </div>   
    <div data-options="region:'east',iconCls:'icon-reload',title:'已选择人员' , collapsible:false,doSize:true" style="width:350px;">
    	<div id="grid_table" class="easyui-panel" data-options="border:false,noheader:true,fit:true,">
    		<table id="dg"></table>
    	</div>
    	
    </div> 
</div>

<script type="text/javascript">


	// EasyUI tree 全选和反选
	function treeChecked(selected, treeMenu) {
	var roots = $('#' + treeMenu).tree('getRoots');//返回tree的所有根节点数组
	if (selected.checked) {
		for ( var i = 0; i < roots.length; i++) {
			var node = $('#' + treeMenu).tree('find', roots[i].id);//查找节点
			$('#' + treeMenu).tree('check', node.target);//将得到的节点选中
		}
	} else {
		for ( var i = 0; i < roots.length; i++) {
			var node = $('#' + treeMenu).tree('find', roots[i].id);
			$('#' + treeMenu).tree('uncheck', node.target);
		}
	}
}



	var resourceTree;
	var gridData=new Array();
	var designerArray=[{"attributes":{},"checked":false,"iconCls":"plugin","id":"xtgl","state":"closed","text":"系统管理"},{"attributes":{"url":"/bugController/dataGrid"},"checked":false,"iconCls":"bug_link","id":"bugglDateGrid","pid":"buggl","state":"open","text":"BUG表格"},{"attributes":{"url":"/resourceController/manager"},"checked":false,"iconCls":"database_gear","id":"zygl","pid":"xtgl","state":"open","text":"资源管理"},{"attributes":{"url":"/resourceController/treeGrid"},"checked":false,"iconCls":"wrench","id":"zyglTreeGrid","pid":"zygl","state":"open","text":"资源表格"},{"attributes":{"url":"/userController/dataGrid"},"checked":false,"iconCls":"wrench","id":"yhglDateGrid","pid":"yhgl","state":"open","text":"用户表格"},{"attributes":{"url":"/roleController/treeGrid"},"checked":false,"iconCls":"wrench","id":"jsglTreeGrid","pid":"jsgl","state":"open","text":"角色表格"},{"attributes":{"url":"/fileController/fileManage"},"checked":false,"iconCls":"server_database","id":"wjglView","pid":"wjgl","state":"open","text":"浏览服务器文件"},{"attributes":{"url":"/chartController/userCreateDatetimeChart"},"checked":false,"iconCls":"chart_curve","id":"userCreateDatetimeChart","pid":"chart","state":"open","text":"用户图表"},{"attributes":{"url":"/roleController/addPage"},"checked":false,"iconCls":"wrench","id":"jsglAddPage","pid":"jsgl","state":"open","text":"添加角色页面"},{"attributes":{"url":"/userController/addPage"},"checked":false,"iconCls":"wrench","id":"yhglAddPage","pid":"yhgl","state":"open","text":"添加用户页面"},{"attributes":{"url":"/roleController/manager"},"checked":false,"iconCls":"tux","id":"jsgl","pid":"xtgl","state":"open","text":"角色管理"},{"attributes":{"url":"/bugController/addPage"},"checked":false,"iconCls":"bug_add","id":"bugglAddPage","pid":"buggl","state":"open","text":"添加BUG页面"},{"attributes":{"url":"/resourceController/tree"},"checked":false,"iconCls":"wrench","id":"zyglMenu","pid":"zygl","state":"open","text":"功能菜单"},{"attributes":{"url":"/fileController/upload"},"checked":false,"iconCls":"server_database","id":"wjglUpload","pid":"wjgl","state":"open","text":"上传文件"},{"attributes":{"url":"/resourceController/addPage"},"checked":false,"iconCls":"wrench","id":"zyglAddPage","pid":"zygl","state":"open","text":"添加资源页面"},{"attributes":{"url":"/bugController/add"},"checked":false,"iconCls":"bug_add","id":"bugglAdd","pid":"buggl","state":"open","text":"添加BUG"},{"attributes":{"url":"/userController/manager"},"checked":false,"iconCls":"status_online","id":"yhgl","pid":"xtgl","state":"open","text":"用户管理"},{"attributes":{"url":"/roleController/add"},"checked":false,"iconCls":"wrench","id":"jsglAdd","pid":"jsgl","state":"open","text":"添加角色"},{"attributes":{"url":"/userController/add"},"checked":false,"iconCls":"wrench","id":"yhglAdd","pid":"yhgl","state":"open","text":"添加用户"},{"attributes":{"url":"/userController/editPage"},"checked":false,"iconCls":"wrench","id":"yhglEditPage","pid":"yhgl","state":"open","text":"编辑用户页面"},{"attributes":{"url":"/bugController/manager"},"checked":false,"iconCls":"bug","id":"buggl","pid":"xtgl","state":"open","text":"BUG管理"},{"attributes":{"url":"/resourceController/add"},"checked":false,"iconCls":"wrench","id":"zyglAdd","pid":"zygl","state":"open","text":"添加资源"},{"attributes":{"url":"/bugController/editPage"},"checked":false,"iconCls":"bug_edit","id":"bugglEditPage","pid":"buggl","state":"open","text":"编辑BUG页面"},{"attributes":{"url":"/roleController/editPage"},"checked":false,"iconCls":"wrench","id":"jsglEditPage","pid":"jsgl","state":"open","text":"编辑角色页面"},{"attributes":{"url":"/bugController/edit"},"checked":false,"iconCls":"bug_edit","id":"bugglEdit","pid":"buggl","state":"open","text":"编辑BUG"},{"attributes":{"url":"/druidController/druid"},"checked":false,"iconCls":"server_database","id":"sjygl","pid":"xtgl","state":"open","text":"数据源管理"},{"attributes":{"url":"/resourceController/editPage"},"checked":false,"iconCls":"wrench","id":"zyglEditPage","pid":"zygl","state":"open","text":"编辑资源页面"},{"attributes":{"url":"/roleController/edit"},"checked":false,"iconCls":"wrench","id":"jsglEdit","pid":"jsgl","state":"open","text":"编辑角色"},{"attributes":{"url":"/userController/edit"},"checked":false,"iconCls":"wrench","id":"yhglEdit","pid":"yhgl","state":"open","text":"编辑用户"},{"attributes":{"url":"/bugController/delete"},"checked":false,"iconCls":"bug_delete","id":"bugglDelete","pid":"buggl","state":"open","text":"删除BUG"},{"attributes":{"url":"/roleController/delete"},"checked":false,"iconCls":"wrench","id":"jsglDelete","pid":"jsgl","state":"open","text":"删除角色"},{"attributes":{"url":"/resourceController/edit"},"checked":false,"iconCls":"wrench","id":"zyglEdit","pid":"zygl","state":"open","text":"编辑资源"},{"attributes":{"url":"/userController/delete"},"checked":false,"iconCls":"wrench","id":"yhglDelete","pid":"yhgl","state":"open","text":"删除用户"},{"attributes":{"url":""},"checked":false,"iconCls":"server_database","id":"wjgl","pid":"xtgl","state":"open","text":"文件管理"},{"attributes":{"url":"/resourceController/delete"},"checked":false,"iconCls":"wrench","id":"zyglDelete","pid":"zygl","state":"open","text":"删除资源"},{"attributes":{"url":"/bugController/view"},"checked":false,"iconCls":"bug_link","id":"bugglView","pid":"buggl","state":"open","text":"查看BUG"},{"attributes":{"url":"/roleController/grantPage"},"checked":false,"iconCls":"wrench","id":"jsglGrantPage","pid":"jsgl","state":"open","text":"角色授权页面"},{"attributes":{"url":"/userController/batchDelete"},"checked":false,"iconCls":"wrench","id":"yhglBatchDelete","pid":"yhgl","state":"open","text":"批量删除用户"},{"attributes":{},"checked":false,"iconCls":"chart_bar","id":"chart","state":"open","text":"图表管理"},{"attributes":{"url":"/userController/grantPage"},"checked":false,"iconCls":"wrench","id":"yhglGrantPage","pid":"yhgl","state":"open","text":"用户授权页面"},{"attributes":{"url":"/roleController/grant"},"checked":false,"iconCls":"wrench","id":"jsglGrant","pid":"jsgl","state":"open","text":"角色授权"},{"attributes":{"url":"/userController/grant"},"checked":false,"iconCls":"wrench","id":"yhglGrant","pid":"yhgl","state":"open","text":"用户授权"},{"attributes":{"url":"/userController/editPwdPage"},"checked":false,"iconCls":"wrench","id":"yhglEditPwdPage","pid":"yhgl","state":"open","text":"用户修改密码页面"},{"attributes":{"url":"/userController/editPwd"},"checked":false,"iconCls":"wrench","id":"yhglEditPwd","pid":"yhgl","state":"open","text":"用户修改密码"},{"attributes":{"url":"http://jeasyui.com/documentation"},"checked":false,"iconCls":"book","id":"jeasyuiApi","state":"open","text":"EasyUI API"}];
	$(function() {
		$.messager.progress('close');
		
		//注册 文本框回车事件，回车直接筛选
		$('#name').on('keydown',function(e){
			if(e.keyCode==13){
			  $("#filter").click();
			}
		});
		
		//加载designer Tree
		$('#designerTree').tree({    
			    data:  designerArray,
			    parentField : 'pid',
				//lines : true,
				checkbox : true
			});  
			
			
		//注册筛选按钮事件
		$("#filter").on("click",function(){
			var rootNodes=$('#designerTree').tree('getRoots');
			$.each(rootNodes, function() {
				$('#designerTree').tree('expandAll',this.target);
			});
			$('#designerTree').tree('doFilter', $("#name").val());
		});
		
		
		//清除筛选按钮事件
		$("#reset").on("click",function(){
			console.log("clear");
			$("#name").val("");
			
			$('#designerTree').tree('doFilter', $("#name").val());
		});
		
		//注册 右选事件
		$("#right").on("click",function(){
			var nodes = $('#designerTree').tree('getChecked');
			gridData.length=0;
			$.each(nodes, function() {
				if($('#designerTree').tree('isLeaf',this.target)){
					console.log(this);
					var rowData=this.attributes;
					rowData.id=this.id;
					console.log(rowData);
					gridData.push(this.attributes);
					}
			});
			console.log(gridData);
			$('#dg').datagrid('loadData',gridData);
		});
		
		////注册 左选事件
		$("#left").on("click",function(){
			var selectionRows=$('#dg').datagrid('getSelections');
			console.log(selectionRows);
			$.each(selectionRows, function() {
				console.log(this.id);
				var node = $('#designerTree').tree('find', this.id);
				console.log(node);
				$('#designerTree').tree('uncheck', node.target);
				var rowIndex=$('#dg').datagrid('getRowIndex',this);
				$('#dg').datagrid('deleteRow',rowIndex);
			});
		});
		
		//注册 全右选事件
		$("#right_all").on("click",function(){
			var flag=new Object();
			flag.checked=true;
			treeChecked(flag,"designerTree");
			var nodes = $('#designerTree').tree('getChecked');
			gridData.length=0;
			$.each(nodes, function() {
				if($('#designerTree').tree('isLeaf',this.target)){
					console.log(this);
					var rowData=this.attributes;
					rowData.id=this.id;
					console.log(rowData);
					gridData.push(this.attributes);
					}
			});
			console.log(gridData);
			$('#dg').datagrid('loadData',gridData);
		});
		
		
		//注册 全左选事件
		$("#left_all").on("click",function(){
			var flag=new Object();
			flag.checked=false;
			treeChecked(flag,"designerTree");
			gridData.length=0;
			$('#dg').datagrid('loadData',gridData);
		});


		$('#dg').datagrid({    
				pagination:false,
				fit:true,
			  	fitColumns:true,
			    columns:[[    
			   		 {field:'id',title:'id',hidden:true},
			        {field:'url',title:'url',width:60},    
			        {field:'url',title:'url'},    
			    ]],
			    data:gridData
			});  
		
		
	});
	

</script>


















