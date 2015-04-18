<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <%@ include file="/WEB-INF/views/jsp/common.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Insert title here</title>
 		<script src="/auth/js/easyui/jquery.min.js" type="text/javascript"></script>
		<script src="/auth/js/easyui/jquery.easyui.min.js" type="text/javascript"></script>
		<script src="/auth/js/easyui/locale/easyui-lang-zh_CN.js" type="text/javascript"></script>
		<script src="/auth/js/jquery.serializeJson.js" type="text/javascript"></script>
		<link href="/auth/js/easyui/themes/default/easyui.css" rel="stylesheet" type="text/css" />
		<link href="/auth/js/easyui/themes/icon.css" rel="stylesheet" type="text/css" />
		
		<script src="/auth/js/plupload-2.1.2/js/plupload.full.min.js" type="text/javascript"></script>
		    
  <script type="text/javascript">
	var datagrid;
	var rowEditor=undefined;
	$(function(){
		datagrid=$("#dg").datagrid({
			url:"/auth/menu/list.do",//加载的URL
		    isField:"id",
			pagination:true,//显示分页
			pageSize:10,//分页大小
			pageList:[10,15,20],//每页的个数
			fit:true,//自动补全
			fitColumns:true,
			iconCls:"icon-save",//图标
			
			columns:[[      //每个列具体内容
		              {field:'id', hidden:true },   
		              {field:'menuName', title:'菜单名',  width:100, align:'center' },   
		              {field:'menuOrder',title:'次序',width:100, align:'center'},   
		              {field:'menuAction',title:'URL',width:100, align:'center'}   
		          ]],
			toolbar:[              //工具条
			        {text:"增加",iconCls:"icon-add",handler:function(){//回调函数
			        	
			        	 $('#dd').dialog('open');
			        }},
			        {text:"删除",iconCls:"icon-remove",handler:function(){
			        	var rows=datagrid.datagrid('getSelections');
			  
			        	if(rows.length<=0)
			        	{
			        		$.messager.alert('警告','您没有选择','error');
			        	}
			        	else
			        	{
			        		$.messager.confirm('确定','您确定要删除吗',function(t){
			        			if(t)
			        			{
			        				var ids = [];
			        				var rows = datagrid.datagrid('getSelections');
			        				for(var i=0; i<rows.length; i++){
			        					ids.push(rows[i].id);
			        				}
			        				//alert(ids.join(','));
			        			
			        				$.ajax({
			        					url : '/auth/menu/delete.do',
			        					data : 'ids='+ids.join(','),
			        					method: 'POST',
			        					dataType : 'json',
			        					success : function(r) {
			        						if (r.success) {
			        							$.messager.show({
			        								msg : r.msg,
			        								title : '成功'
			        							});
			        							datagrid.datagrid('reload');
			        						} else {
			        							$.messager.alert('错误', r.msg, 'error');
			        						}
			        						datagrid.datagrid('unselectAll');
			        					}
			        				});
			        			
			        			}
			        		})
			        	}
			        	
			        	
			        }},
			        {text:"修改",iconCls:"icon-edit",handler:function(){
			        	var rows=datagrid.datagrid('getSelections');
			        	if(rows.length==1)
			        	{
			        		//alert(rows[0].id);	
			        		$.ajax({
	        					url : '/auth/menu/id'+rows[0].id+'.do',
	        					data : [],
	        					dataType : 'json',
	        					success : function(r) {
	        						if (r.id) {
	        							 $('#dd').dialog('open');
	        							$("#menuName").textbox("setValue", r.menuName);
	        							$("#menuAction").textbox("setValue", r.menuAction);
	        							$("#menuOrder").textbox("setValue", r.menuOrder);
	        							 $('#menuLevel').combobox("setValue", r.menuLevel);
	        							 
	        							$("#id").val(r.id);
	        							$("#imghead").attr("src", "/auth/"+r.menuIcon);
	        							
	        							//设置上级菜单
	        							 $('#parentID').combobox({
												 url: "/auth/menu/combox.do?menuLevel="+( r.menuLevel-1),
												 valueField:'id',
												 textField:'menuName',
												   onLoadSuccess: function (data) {
													  
											             if (data&&data.length>0) {
											            		 $("#parentID").combobox('setValue',r.parentID);
											               
											             } else {
											            	 
											            	 var defaultValue =[{"menuName": '请选择', "id": '-1'}];
											            	 $("#parentID").combobox('loadData',defaultValue);
											            	 $('#parentID').combobox("setValue", -1);
											             }
											         }
											 });
	        							
	        						} else {
	        							
	        							$.messager.alert('错误', r.msg, 'error');
	        						}
	        					}
	        				});
			        	} else {
			        		$.messager.alert('错误', "只能选择一个数据进行修改", 'error');
			        	}
			        }},
			        ],
			onAfterEdit:function(rowIndex, rowData, changes){
				var inserted = datagrid.datagrid('getChanges', 'inserted');
				var updated = datagrid.datagrid('getChanges', 'updated');
				if (inserted.length < 1 && updated.length < 1) {
					editRow = undefined;
					datagrid.datagrid('unselectAll');
					return;
				}

				var url = '';
				if (inserted.length > 0) {
					url = '/Test3/ModuleBeanController/addcustomer.do';
				}
				if (updated.length > 0) {
					url = '/Test3/ModuleBeanController/addcustomer.do';
				}

				$.ajax({
					url : url,
					data : rowData,
					dataType : 'json',
					success : function(r) {
						if (r.success) {
							datagrid.datagrid('acceptChanges');
							$.messager.show({
								msg : r.msg,
								title : '成功'
							});
							editRow = undefined;
							datagrid.datagrid('reload');
						} else {
							/*datagrid.datagrid('rejectChanges');*/
							datagrid.datagrid('beginEdit', editRow);
							$.messager.alert('错误', r.msg, 'error');
						}
						datagrid.datagrid('unselectAll');
					}
				});
				
			},
			onDblClickCell:function(rowIndex, field, value){
				if(rowEditor==undefined)
				{
		        	datagrid.datagrid('beginEdit',rowIndex);
		        	rowEditor=rowIndex;
				}
				
			}
		});
		
		 $('#dd').dialog({
             title: "My Dialog",
             closed:true,
             modal: true, //dialog继承自window，而window里面有modal属性，所以dialog也可以使用
             toolbar: [{
					text:'Ok',
				iconCls:'icon-ok',
				handler:function(){
					$('#ff').form('submit',{
						onSubmit:function(){
							var isValid = $(this).form('validate');
							return isValid;
						},
						success: function(){
							$('#ff').form('clear');
							$("#imghead").attr("src","");
							$('#dd').dialog('close');
						}
					});
				
				}
			},{
				text:'Cancel',
				handler:function(){
					$('#dd').dialog('close');
					$('#ff').form('clear');
				}
			}],
			onOpen: function() {
				 $('#menuLevel').combobox("setValue", 1);
				 var defaultValue =[{"menuName": '请选择', "id": '-1'}];
            	 $("#parentID").combobox('loadData',defaultValue);
            	 $('#parentID').combobox("setValue", -1);
			},
			onClose: function() {
				$('#ff').form('clear');
				$("#imghead").attr("src","");
				datagrid.datagrid('unselectAll');
				datagrid.datagrid('reload');
			}
         });
		 
		 initCombox();
	    
		$("#btn_search").click(function(){
			var param = $("#searchForm").serializeJson();
			datagrid.datagrid('load', param);

		});
		});
	
	function initCombox() {
		 $('#menuLevel').combobox({
			 data: [
				{"id":1, 
				"text":"第一级" 
				},{ 
				"id":2, 
				"text":"第二级" 
				}
				,{ 
				"id":3, 
				"text":"第三级" 
				}
				,{ 
				"id":4, 
				"text":"第四级" 
				}
			        ],
			 valueField:'id', 
			 textField:'text',

			 onSelect: function(){
				 var value = $('#menuLevel').combobox('getValue');
				 //选择第一级时，没有上级菜单
				// if(value == 1){
					// $('#parentID').combobox("clear");
					// return;
			  //  }
				 $('#parentID').combobox({
					 url: "/auth/menu/combox.do?menuLevel="+(value-1),
					 valueField:'id',
					 textField:'menuName',
					   onLoadSuccess: function (data) {
						  
				             if (data&&data.length>0) {
				            		  $("#parentID").combobox('select',data[0].id);
				               
				             } else {
				            	 
				            	 var defaultValue =[{"menuName": '请选择', "id": '-1'}];
				            	 $("#parentID").combobox('loadData',defaultValue);
				             }
				         }
				 })
			 }
		 });
		 
		 $('#parentID').combobox({
			 data: [{"menuName": '请选择', "id": '-1'}],
			 valueField:'id',
			 textField:'menuName',
			 value:'-1'
		 });
	}
		
	 function previewImage(file)
     {
       var MAXWIDTH  = 36; 
       var MAXHEIGHT = 36;
       var div = document.getElementById('preview');
       if (file.files && file.files[0])
       {
           div.innerHTML ='<img id=imghead>';
           var img = document.getElementById('imghead');
           img.onload = function(){
             var rect = clacImgZoomParam(MAXWIDTH, MAXHEIGHT, img.offsetWidth, img.offsetHeight);
             img.width  =  rect.width;
             img.height =  rect.height;
//              img.style.marginLeft = rect.left+'px';
             img.style.marginTop = rect.top+'px';
           }
           var reader = new FileReader();
           reader.onload = function(evt){img.src = evt.target.result;}
           reader.readAsDataURL(file.files[0]);
       }
       else //IE
       {
         var sFilter='filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale,src="';
         file.select();
         var src = document.selection.createRange().text;
        
         div.innerHTML = '<img id=imghead>';
         var img = document.getElementById('imghead');
         img.filters.item('DXImageTransform.Microsoft.AlphaImageLoader').src = src;
         //var rect = clacImgZoomParam(MAXWIDTH, MAXHEIGHT, img.offsetWidth, img.offsetHeight);
         var rect = clacImgZoomParam(MAXWIDTH, MAXHEIGHT, 36, 36);
         //status =('rect:'+rect.top+','+rect.left+','+rect.width+','+rect.height);
         div.innerHTML = "<div id=divhead style='width:"+rect.width+"px;height:"+rect.height+"px;margin: 0px;"+sFilter+src+"\"'></div>";
       }
     }
     function clacImgZoomParam( maxWidth, maxHeight, width, height ){
         var param = {top:0, left:0, width:width, height:height};
         if( width>maxWidth || height>maxHeight )
         {
             rateWidth = width / maxWidth;
             rateHeight = height / maxHeight;
             
             if( rateWidth > rateHeight )
             {
                 param.width =  maxWidth;
                 param.height = Math.round(height / rateWidth);
             }else
             {
                 param.width = Math.round(width / rateHeight);
                 param.height = maxHeight;
             }
         }
         
         param.left = Math.round((maxWidth - param.width) / 2);
         param.top = Math.round((maxHeight - param.height) / 2);
         return param;
     }
	
    </script>
    <style type="text/css">
    
    </style>
</head>
<body style="padding:0 4px; margin:0;  overflow: hidden; ">
<div class="easyui-layout" style="width:100%;height:100%;" data-options="fit:true">
		<div title="菜单管理" data-options="region:'north'" style="height:100px">
		 <form id="searchForm" >
			<table cellpadding="5">
				    		<tr>
				    			<td>菜单名:</td>
				    			<td><input class="easyui-textbox" type="text" id="qmenuName"  name="menuName" ></input></td>
				    		</tr>
				    		<tr>
				    			<td>URL:</td>
				    			<td><input class="easyui-textbox" type="text" id="qmenuAction"  name="menuAction" ></input></td>
				    			<td rowspan="2" colspan="2"><a href="#" id="btn_search" class="easyui-linkbutton" data-options="iconCls:'icon-search'" style="width:80px">Search</a></td>
				    		</tr>
				    		<tr>
				    		</tr>
				    	</table>
				  </form>
		</div>
		<div data-options="region:'center'" >
			<table id="dg" >
			</table>
		</div>

			
	<div id="dd" title="My Dialog"  style="width:600px;height:450px; text-align: center; " data-options="closed:true"> 
				    <form id="ff" method="post" action="/auth/menu/save.do" enctype="multipart/form-data">
				    		<input type="hidden" id="id" name="id">
				    	<table cellpadding="5">
				    		<tr>
				    			<td>菜单名:</td>
				    			<td><input class="easyui-textbox" type="text" id="menuName"  name="menuName" data-options="required:true"></input></td>
				    		</tr>
				    		<tr>
				    			<td>URL:</td>
				    			<td><input class="easyui-textbox" type="text" id="menuAction"  name="menuAction" data-options="required:true"></input></td>
				    		</tr>
				    		<tr>
				    			<td>显示次序:</td>
				    			<td><input class="easyui-numberbox"  id="menuOrder"  name="menuOrder"  data-options="required:true" precision="0" max="999" min="1" size="8" maxlength="3"></input></td>
				    		</tr>
				    		<tr>
				    			<td>层级:</td>
				    			<td>
				    				<select class="easyui-combobox" id="menuLevel" name="menuLevel"  style="width: 100px" data-options="value:'1'">
				    						
				    				</select>
				    			</td>
				    		</tr>
				    		<tr>
				    			<td>上级菜单:</td>
				    			<td>
				    				<select style="width: 200px" class="easyui-combobox" id="parentID" name="parentID" >
				    				</select>
				    			</td>
				    		</tr>
				    		<tr>
				    			<td>图标:</td>
				    			<td>
				    				<input id="fb" type="text" name="file"  style="width:300px"> 
				    				<script type="text/javascript">
					    				$('#fb').filebox({ 
					    					 buttonText: 'Choose File', 
					    					 buttonAlign: 'left',
					    					 onChange: function(newValue,oldValue){
					    						 if(newValue == "")return false;
					    						 previewImage(document.getElementsByName("file")[0]);
					    					 }
					    					});
				    				</script>
				    			</td>
				    		</tr>
				    		<tr>
				    		<td>预览:</td>
								<td >
								<div id="preview">
									<img  alt="照片预览"   id="imghead"  border=0  src="" width="20" height="20"/>
									</div>
								</td>
							</tr>
				    	</table>
				    </form>
				    
			</div>
			
			
		</div>
</body>
</html>