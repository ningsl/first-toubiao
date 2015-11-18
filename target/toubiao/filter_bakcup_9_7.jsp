<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>

<jsp:include page="inc.jsp"></jsp:include>

 <link href="jslib/filter/filter.css" rel="stylesheet" type="text/css"/> 


<!-- <style type="text/css">

/************   filter whole css ************/
.filter_first_row ,.filter_row ,.filter_crumb{
	font: 12px/1.5 Arial;
	-webkit-text-size-adjust: none;
}

/************            hover on  ************/

.filter_button_hover_on{
/* background-color: #f60; */
color: #f60;
}

/************          单组样式     ************/
.filter_group{
border: 1px solid #e8e8e8;

}

/************          单筛选行样式     ************/
.filter_first_row{

position: relative;
border-top: none;
margin: 0 8px;
overflow:hidden;
}
.filter_row{

position: relative;
border-top: 1px dashed #dedede;
margin: 0 8px;

}

/************         筛选行head样式     ************/
.filter_row_head{
position: absolute;
left: 11px;
top: 9px;
display:block;
}

/************         筛选行body样式     ************/
.filter_row_body{
padding: 0 100px 0 112px;
height: 36px;
overflow:hidden;
}

.filter_row_body .inner_items{

}

.filter_row_body .inner_items_show2line{

}

.filter_row_body .btns{
display: none;
text-align: center;
}

.filter_row_body .item {
display:inline-block;
margin: 9px 40px 9px 0;
height: 18px;
text-decoration: none;
padding: 0 3px;
color:#252525; 
}


.icon-btn-uncheck-small {
position:relative;
top:1px;
display: none;
background: url(icons.png);
background-repeat: no-repeat;
background-position: -300px -169px;
width: 11px;
height: 11px;
}

.icon-btn-check-small {
background-position: -5px -411px;
}

/************          提交、 取消按钮样式     ************/
.filter_row_body .submit_selected {
border: 1px solid #e8e8e8;
/* color: #fff;
background: #F94700;
border: 1px solid #F94700; */
display: inline-block;
margin: 5px;
border-radius: 2px;
height: 20px;
width: 42px;
line-height: 20px;
text-align: center;
text-decoration: none;
cursor: pointer;
}


.filter_row_body .cancel_selected {
border: 1px solid #e8e8e8;
display: inline-block;
margin: 5px;
border-radius: 2px;
height: 20px;
width: 42px;
line-height: 20px;
text-align: center;
text-decoration: none;
cursor: pointer;
}


/************          展开时的样式,加到row_body上      ************/
.items_expand_mode{
height: auto;
max-height: 102px;
overflow: auto;
}

/************         筛选行foot样式     ************/
.filter_row_foot{
position: absolute;
right: 0;
top: 8px;
}

.filter_row_foot .switch-multi {
position: absolute;
right: 70px;
height: 16px;
padding: 0 3px;
line-height: 16px;
border: solid 1px #dad9d9;
cursor: pointer;
white-space: nowrap;
}
.filter_row_foot .show-more {
position: absolute;
right: 20px;
height: 18px;
line-height: 18px;
padding: 0 3px;
cursor: pointer;
white-space: nowrap;
border: solid 1px #dad9d9;
}

.filter_item_hover_on{
background-color: #f60;
color: #fff;
}

/***********   crumb css ************/
.filter_selected_item:hover{
position:relative;
display: inline-block;
margin: 5px 10px 0 0;
padding: 0 19px 0 5px;
height: 22px;
line-height: 22px;
overflow: hidden;
border: 1px solid #f60; 
text-decoration:none;
color: #f60;
}

.filter_crumb{
position: relative;
display:none;
padding: 0 100px 0 112px;
zoom: 1;
overflow: hidden;
/* border-bottom: 1px dashed #e6e6e6; */
}
.filter_crumb_title{
position: absolute;
left: 20px;
top: 9px;
width: 90px;
height: 18px;
text-align: left;
font-weight: bold;
}

.filter_selected_items{
padding: 1px 0 6px 4px;
position: relative;

}

.filter_selected_item{
position:relative;
display: inline-block;
margin: 5px 10px 0 0;
padding: 0 19px 0 5px;
height: 22px;
line-height: 22px;
overflow: hidden;
border: 1px solid #dad9d9;  
text-decoration:none;
color: #252525;
}
.filter_cancle_all{
display: inline-block;
position: absolute;
right: 25px;
top: 9px;
padding: 0 3px;
height: 18px;
font: 12px/18px arial;
/* color: #666; */
text-align: right;
border: 1px solid #dad9d9; 
cursor: pointer;
}

.filter_cancle_all_hover{
color: #f60;
}

.filter_selected_item .icon-btn-x{
background:url(icons.png);
background-repeat: no-repeat;
background-position: -211px -411px;
width: 7px;
height: 7px;
display: inline-block;
font-size: 0;
position: absolute;
right: 6px;
top: 8px;
}

.filter_selected_item:hover .icon-btn-x{
background: url(icons.png);
background-repeat: no-repeat;
background-position: -228px -411px;
width: 7px;
height: 7px;
display: inline-block;
font-size: 0;
position: absolute;
right: 6px;
top: 8px;
}
</style> -->



<!-------------------  crumb js -------------------->
<script type="text/javascript">
function FilterCrumb(divId,callFunction){	
	
	this.createComponents=function(){
		var J_filterCrumb=$("#"+divId);
		var J_components=$('<span class="filter_crumb_title">已选条件：</span> <div class="filter_selected_items J_filterSelectedItems"></div> <span class="filter_cancle_all J_filterCrumbCancleAll">全部撤销</span>');
		J_filterCrumb.append(J_components);
		J_filterCrumb.addClass("filter_crumb J_filterCrumb");
	}
	
	/************         创建crumb时，每次都是重新创建;并根据key,value生成查询url	    ************/	
	this.createFilterSelectedItem=function() {
		console.log("in createFilterSelectedItem ");
		var queryParameter="";
		var J_filterCrumb=$("#"+divId);
		var J_filterSelectedItems=$("#"+divId).children(".J_filterSelectedItems");
		console.log(J_filterCrumb);
		console.log(J_filterSelectedItems.attr("class"));
		var testValue=this.testValue;
		$("#"+divId).show();
		J_filterSelectedItems.empty();
		$(".J_filterRow").each(function(){
			
			var hqlArrays=new Array();
			var checkedNameArray=new Array();
			var rowName=$(this).attr("data-rowName");
			var rowId=$(this).attr("id");
			var rowKey=$(this).attr("data-rowKey");
			$(this).find(".J_filterItemChecked").each(function(){
				console.log(rowId);
				hqlArrays.push($(this).attr("data-value"));
				checkedNameArray.push($(this).attr("title"));
			});
			if(hqlArrays.length!=0){
				
				var hqlString="("+hqlArrays.join(" or ")+")";
				var selectedContent=rowName+"："+checkedNameArray.join(",");
				
				var J_selectedItem=$("<a href='#'>test</a>");
				
				console.log(selectedContent);
				J_selectedItem.attr({class:"filter_selected_item J_selectedItem",title:selectedContent});
				J_selectedItem.attr("data-rowKey",rowKey);
				J_selectedItem.attr("data-rowId",rowId);
				J_selectedItem.attr("data-rowValue",hqlString);
				J_selectedItem.text(selectedContent);
				$("<span class='icon-btn-x'></span>").appendTo(J_selectedItem);
				J_selectedItem.appendTo(J_filterSelectedItems);
				$("#"+rowId).hide();
				
				//注册更改css的事件
				/* J_selectedItem.on({
	                "mouseenter": function() {
	                	$(this).removeClass("filter_selected_item");
	                	$(this).addClass("filter_selected_item_hover_on");
	                },
	                "mouseleave": function() {
	                	$(this).removeClass("filter_selected_item_hover_on");
	                	$(this).addClass("filter_selected_item");
	                }
	            }); */
				
				
				//根据查询条件，生成url
				if(queryParameter==""){
					queryParameter=hqlString;
				}else{
					queryParameter=queryParameter+" and "+hqlString;
				}
			}
			
		});	
		console.log(queryParameter);
		
		callFunction(queryParameter);
		
		/*  if(queryParameter==""){
			table.ajax.url("/bid/employee/listAll.do").load();
		}else{
			table.ajax.url("/bid/employee/queryByHql.do?condition="+encodeURI(encodeURI(queryParameter))).load();
		}  */
		
		//change row class
		if($(".J_filterRow").length>0){
			var indexOfFirstRow=-1;
			/* alert("beore each="+indexOfFirstRow); */
			$(".J_filterRow").each(function(index,element){
				if(indexOfFirstRow==-1){
					if($(this).css('display')!='none'){
						$(this).removeClass('filter_row');
						$(this).addClass('filter_first_row');
						indexOfFirstRow=index;
						/* alert("in eache="+indexOfFirstRow); */
					}
				}else{
					$(this).removeClass('filter_first_row');
					$(this).addClass('filter_row');
				}
			});
		}
	};


/************         去掉指定选项，然后重新生成crumb	    ************/
this.removeSelectedItem=function(){
	var createFilterSelectedItem=this.createFilterSelectedItem;
	var J_filterSeletedItems=$("#"+divId).find(".J_filterSelectedItems");
	J_filterSeletedItems.on("click",function(event){
		var J_filterSeletedItem;
		console.log(event.target);
		if($(event.target).is('span')){
			J_filterSeletedItem=$(event.target).parent();
		}else{
			J_filterSeletedItem=$(event.target);
		}
		J_filterSeletedItems=J_filterSeletedItem.parent();
		var rowId=J_filterSeletedItem.attr("data-rowId");
		console.log(rowId);
		$("#"+rowId).show();
		$("#"+rowId).find(".J_CloseMulti").click();		
		$("#"+rowId).find(".J_filterItem").removeClass("J_filterItemChecked");
		console.log($("#"+rowId).find(".J_filterItem").attr("class"));
		console.log("hit removeSelectedItem");
		createFilterSelectedItem();
		
		//if no children selectedItem ,then hide row
		if(J_filterSeletedItems.children().size()==0){
			console.log(J_filterSeletedItems.children().size());
			J_filterSeletedItems.parent().hide();
		}	
	});
}; 
/*************        end      *************************/


/************         remove all button	    ************/
$("#"+divId).find(".J_filterCrumbCancleAll").on("click",function(){
	
	var J_filterSeletedItems=$("#"+divId).find(".J_filterSelectedItems");
	$("#"+divId).find(".J_selectedItem").each(function(){
		var rowId=$(this).attr("data-rowId");
		console.log(rowId);
		$("#"+rowId).show();
		$("#"+rowId).find(".J_CloseMulti").click();		
		$("#"+rowId).find(".J_filterItem").removeClass("J_filterItemChecked");					
	});
	J_filterSeletedItems.empty();
	J_filterSeletedItems.parent().hide();
	/* table.ajax.url("/bid/listAll.do").load(); */
	
	//change row class
	if($(".J_filterRow").length>0){
		var indexOfFirstRow=-1;
		/* alert("beore each="+indexOfFirstRow); */
		$(".J_filterRow").each(function(index,element){
			if(indexOfFirstRow==-1){
				if($(this).css('display')!='none'){
					$(this).removeClass('filter_row');
					$(this).addClass('filter_first_row');
					indexOfFirstRow=index;
					/* alert("in eache="+indexOfFirstRow); */
				}
			}else{
				$(this).removeClass('filter_first_row');
				$(this).addClass('filter_row');
			}
		});
	}
});
/*************        end      *************************/

/*************        remove all buttoon change css      *************************/
$("#"+divId).find(".J_filterCrumbCancleAll").on({
	                "mouseenter": function() {
	                	$(this).addClass("filter_cancle_all_hover");
	                },
	                "mouseleave": function() {
	                	$(this).removeClass("filter_cancle_all_hover");
	                }
	            });
/*************        end      *************************/


/************         initialize    ************/
this.createComponents();
this.removeSelectedItem();
this.createFilterSelectedItem
/*************        end      *************************/

/*************       function end      *************************/
};

</script>

<!-------------------  filter js -------------------->
<script type="text/javascript">
function FilterRow(divId,crumb){
		
		var defaultHeight=36;//默认行高
		
		var J_filterHead=$("#"+divId).find(".J_filterRowHead");
		var J_filterBody=$("#"+divId).find(".J_filterRowBody");
		var J_filterBody=$("#"+divId).find(".J_filterRowBody");
		var J_filterFoot=$("#"+divId).find(".J_filterRowFoot");
		
		//body内的对象
		var J_filterItems=J_filterBody.find(".J_filterItems");
		var J_filterItem=J_filterItems.find(".J_filterItem");
		var J_filterBtns=J_filterBody.find(".J_btns");
		var J_filterSubmitMulti=J_filterBtns.find(".J_SubmitMulti");
		var J_filterCloseMulti=J_filterBtns.find(".J_CloseMulti");
		var J_iconBlock=J_filterBody.find(".icon-btn-uncheck-small");
		
		//foot内对象
		var J_filterOpenMulti=J_filterFoot.find(".J_OpenMulti");
		var J_filterToggleItems=J_filterFoot.find(".J_ToggleItems");
		
		//初始化，显示按钮，注册事件
		var initFunction=function(){
			//console.log(crumb);
			showViewMoreButton();
			viewMore();
			viewMulti();
			handleItem();
			handleCancle();
			handleSubmit();
			calculateFirstRow();
			
		};

		
		/************          是否显示更多按钮      ************/	
		var showViewMoreButton=function() {		 
			//获取filter_body 内的filterItems  outheight(注:outheight含隐藏元素高度)
			var bodyHeight = J_filterItems.outerHeight();
			console.log(bodyHeight); 
			if(bodyHeight>defaultHeight){
					J_filterToggleItems.css({visibility:"visible"});
					console.log(J_filterToggleItems);
				}else{
					J_filterToggleItems.css({visibility:"hidden"});
					console.log(J_filterToggleItems);
				}
		};	
		
		/************      展开、收缩按钮事件    1.展开、收缩  2.多选状态下，去掉多选，恢复初始      ************/	
		var viewMore=function(){
			/* console.log( $(".J_ToggleItems").parent().parent().attr("class")); */
				/* var J_filterBody=this.filterBody; */
				
				J_filterToggleItems.on("click",function(){
					if($(this).text()=="更多"){
						$(this).text("收起");
						J_filterBody.addClass("items_expand_mode");
					}else{
						$(this).text("更多");
						J_filterBody.removeClass("items_expand_mode");
						//如果多选状态的话，显示多选按钮，去掉多选状态即隐藏icon,并去掉.icon-btn-check-small,check=0
						if(J_iconBlock.css("display")=="inline-block"){
							J_iconBlock.css({display:"none"});
							J_iconBlock.removeClass("icon-btn-check-small");
							J_filterBtns.hide();
							J_filterOpenMulti.show();
							J_filterItem.data("check",0);
						}
					}
				});
			};
					
			/************     多选按钮事件      ************/	
			var viewMulti=function(){
					J_filterOpenMulti.on("click",function(){
					J_iconBlock.css({display:"inline-block"});
					J_filterBtns.css({display:"block"});
					$(this).hide();
					//如果 更多 按钮有效，显示更多按钮为“收起"
					J_filterToggleItems.text("收起");
					J_filterBody.addClass("items_expand_mode");
			
				});
			};
						
			/************         处理item事件:区分单选 多选     ************/	
			var handleItem=function(){
					
					J_filterItem.on("click",function(event){
					event.preventDefault();
					var J_item=$(this);
					var className=J_item.attr("class");
					var J_iconBlock=J_item.find(".icon-btn-uncheck-small");
					console.log(className); 
					
					//是否多选状态
					if(J_iconBlock.css("display")=="inline-block"){
						if(J_iconBlock.attr("class")=="icon-btn-uncheck-small"){
								J_iconBlock.addClass("icon-btn-check-small");
								J_item.data("check",1);
								J_item.addClass("J_filterItemChecked");
								console.log("多选状态下选择了："+J_item.attr("title"));
						}
						else{
								J_iconBlock.removeClass("icon-btn-check-small");
								console.log("多选状态下取消了："+J_item.attr("title"));
								J_item.removeClass("J_filterItemChecked");
								J_item.data("check",0);
						}
					
					}else{
						//单选
						console.log("单选状态下选择了："+J_item.attr("title"));
						J_item.addClass("J_filterItemChecked");
						console.log(crumb);
						crumb.createFilterSelectedItem();
					}
					
				});
			};
			
			
			/************         处理btn取消事件,1.收起 2 去掉多选状态,恢复原始状态 ，3.隐藏btns     ************/	
			var handleCancle=function(){
					J_filterCloseMulti.on("click",function(){
						console.log("click J_filterCloseMulti");
						J_filterBody.removeClass("items_expand_mode");
						J_filterBody.scrollTop(0);
						J_filterToggleItems.text("更多");
						J_filterOpenMulti.show();
						J_filterItem.removeClass("J_filterItemChecked");
						J_iconBlock.removeClass("icon-btn-check-small");
						J_iconBlock.css({display:"none"});
						
						J_filterBtns.hide();			
				});
			};
						
			/************         多选提交按钮    ************/	
			var handleSubmit=function(){
				$(".filter_row_body .J_SubmitMulti").on("click",function(){					
					var key=J_filterBody.find(".inner_items").data("key");
					console.log(key);
					$.each(J_filterItem,function(){
							if($(this).data("check")==1){
							/* console.log($(this).attr("class")); */
							console.log($(this).data("value"));
							}
							crumb.createFilterSelectedItem();
					});
					
				});
			};
			
			var calculateFirstRow=function(){
			if($(".J_filterRow").length>0){
				var indexOfFirstRow=-1;
				/* alert("beore each="+indexOfFirstRow); */
				$(".J_filterRow").each(function(index,element){
					if(indexOfFirstRow==-1){
						if($(this).css('display')!='none'){
							$(this).removeClass('filter_row');
							$(this).addClass('filter_first_row');
							indexOfFirstRow=index;
							/* alert("in eache="+indexOfFirstRow); */
						}
					}else{
						$(this).removeClass('filter_first_row');
						$(this).addClass('filter_row');
					}
				});
			}
			};
			
					
			/************      when mouse hover on,then change CSS     ************/	
			J_filterToggleItems.on({
                "mouseenter": function() {
                	J_filterToggleItems.addClass("filter_button_hover_on");
                },
                "mouseleave": function() {
                	J_filterToggleItems.removeClass("filter_button_hover_on");
                }
            });
			
			J_filterOpenMulti.on({
                "mouseenter": function() {
                	J_filterOpenMulti.addClass("filter_button_hover_on");
                },
                "mouseleave": function() {
                	J_filterOpenMulti.removeClass("filter_button_hover_on");
                }
            });
			
			J_filterSubmitMulti.on({
                "mouseenter": function() {
                	J_filterSubmitMulti.addClass("filter_button_hover_on");
                },
                "mouseleave": function() {
                	J_filterSubmitMulti.removeClass("filter_button_hover_on");
                }
            });
			
			J_filterCloseMulti.on({
                "mouseenter": function() {
                	J_filterCloseMulti.addClass("filter_button_hover_on");
                },
                "mouseleave": function() {
                	J_filterCloseMulti.removeClass("filter_button_hover_on");
                }
            });
			
			
			J_filterItem.on({
                "mouseenter": function() {
                	$(this).addClass("filter_item_hover_on");
                	$(this).css({color:'#fff'});
                },
                "mouseleave": function() {
                	$(this).removeClass("filter_item_hover_on");
                	$(this).css({color:'#252525'});
                }
            });
			
			/************         初始化调用	    ************/	
			initFunction();			
	};	
	
</script>

<!-------------------  create filter function js -------------------->
<script type="text/javascript">
function createFilterRow(rowId,rowKey,rowName,items,isFirstRow,crumb,parentDivId){
	
	//alert("in createFilterRow begin");
	var J_parentDiv=$("#"+parentDivId);
	J_parentDiv.addClass("filter_group");
	
	
	var J_filterRow=$("<div></div>");
	
	J_filterRow.attr({
		'class':'J_filterRow',
		'data-rowKey':rowKey,
		'data-rowName':rowName,
		'id':rowId
	});
	
	//head part
	var J_rowHead=$("<div class='filter_row_head J_filterRowHead'><span>"+rowName+"：</span></div>");
	
	
	//body part contents of J_innerItems and J_btns
	var J_rowBody=$("<div class='filter_row_body  J_filterRowBody'>");
	var J_innerItems=$("<div class='inner_items  J_filterItems'>");
	
	//将item 加入J_innerItems
	for(var i=0;i<items.length;i++){
		var J_item=$("<a class='item  J_filterItem' href='javascript:;'></a>");
		J_item.attr({
			'title':items[i].title,
			'data-value':items[i].value,
			'data-check':0
		});
		$("<span class='icon-btn-uncheck-small'></span>").appendTo(J_item);
		$("<span class='text'> "+items[i].text+"</span>").appendTo(J_item);
		$(J_item).appendTo(J_innerItems);
	}
	J_innerItems.appendTo(J_rowBody);
	
	J_btns=$("<div class='btns J_btns'></div>");
	$("<span class='submit_selected J_SubmitMulti'>提交</span>").appendTo(J_btns);
	$("<span class='cancel_selected J_CloseMulti'>取消</span>").appendTo(J_btns);
	
	J_btns.appendTo(J_rowBody);
	
	//foot part
	var J_rowFoot=$("<div class='filter_row_foot J_filterRowFoot'>");
	$("<span class='switch-multi J_OpenMulti'>多选</span>").appendTo(J_rowFoot);
	$("<span class='show-more J_ToggleItems' >更多<span class='icon-btn-arrow-down-2'></span></span>").appendTo(J_rowFoot);
	
	
	
	
	$(J_rowHead).appendTo(J_filterRow);
	$(J_rowBody).appendTo(J_filterRow);
	$(J_rowFoot).appendTo(J_filterRow);
	
	
	
	//alert("in createFilterRow end");
	//console.log(J_filterRow);
	
	$(J_filterRow).appendTo(J_parentDiv);  
	var filterRow2=new FilterRow(rowId,crumb);
	
}
</script>


<!-------------------  instance filter  js -------------------->
<script type="text/javascript">
$(document).ready(function() {

	var crumb1=new FilterCrumb("crumbId1",function(queryParameter){
			if(queryParameter==""){
					table.ajax.url("/bid/employee/listAll.do").load();
			 }else{
				table.ajax.url("/bid/employee/queryByHql.do?condition="+encodeURI(encodeURI(queryParameter))).load();
			 }  
	});

	
	/* var filterRow=new FilterRow("row1",crumb1);
	var filterRow=new FilterRow("row2",crumb1); */
	
	var rowKey="deparment";
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
					value:"department like '三分'" ,
					text:'三分'
				},
				{
					title:'市场部',
					value:"department like '市场部'" ,
					text:'市场部'
				},
				];
	var rowKey1="education";
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
 	var filterTest=createFilterRow(rowId,rowKey,rowName,items,isFirstRow,crumb1,"filter_group");
 	var filterTest2=createFilterRow(rowId1,rowKey1,rowName1,items1,isFirstRow,crumb1,"filter_group");
 	
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