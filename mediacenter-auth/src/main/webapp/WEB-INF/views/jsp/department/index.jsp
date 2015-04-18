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
			url:"/auth/dpt/list.do",//加载的URL
		    isField:"id",
			pagination:true,//显示分页
			pageSize:10,//分页大小
			pageList:[10,15,20],//每页的个数
			fit:true,//自动补全
			fitColumns:true,
			iconCls:"icon-save",//图标
			
			columns:[[      //每个列具体内容
		              {field:'id', hidden:true },   
		              {field:'departmentCode', title:'编号',  width:100, align:'center' },   
		              {field:'departmentName',title:'名称',width:100, align:'center'},   
		              {field:'level',title:'等级',width:100, align:'center'}   
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
			        					url : '/auth/dpt/delete.do',
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
	        					url : '/auth/dpt/id'+rows[0].id+'.do',
	        					data : [],
	        					dataType : 'json',
	        					success : function(r) {
	        						if (r.id) {
	        							 $('#dd').dialog('open');
	        							$("#departmentCode").textbox("setValue", r.departmentCode);
	        							$("#departmentName").textbox("setValue", r.departmentName);
	        							 $('#level').combobox("setValue", r.level);
	        							 
	        							$("#id").val(r.id);
	        							
	        							//设置上级菜单
	        							 $('#parentID').combobox({
												 url: "/auth/dpt/combox.do?level="+( r.level-1),
												 valueField:'id',
												 textField:'departmentName',
												   onLoadSuccess: function (data) {
													  
											             if (data&&data.length>0) {
											            		 $("#parentId").combobox('setValue',r.parentId);
											               
											             } else {
											            	 
											            	 var defaultValue =[{"departmentName": '请选择', "id": '-1'}];
											            	 $("#parentId").combobox('loadData',defaultValue);
											            	 $('#parentId').combobox("setValue", -1);
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
				 $('#level').combobox("setValue", 1);
				 var defaultValue =[{"departmentName": '请选择', "id": '-1'}];
            	 $("#parentId").combobox('loadData',defaultValue);
            	 $('#parentId').combobox("setValue", -1);
			},
			onClose: function() {
				$('#ff').form('clear');
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
		 $('#level').combobox({
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
				 var value = $('#level').combobox('getValue');
				 //选择第一级时，没有上级菜单
				// if(value == 1){
					// $('#parentID').combobox("clear");
					// return;
			  //  }
				 $('#parentId').combobox({
					 url: "/auth/dpt/combox.do?level="+(value-1),
					 valueField:'id',
					 textField:'departmentName',
					   onLoadSuccess: function (data) {
						  
				             if (data&&data.length>0) {
				            		  $("#parentId").combobox('select',data[0].id);
				               
				             } else {
				            	 
				            	 var defaultValue =[{"departmentName": '请选择', "id": '-1'}];
				            	 $("#parentId").combobox('loadData',defaultValue);
				             }
				         }
				 })
			 }
		 });
		 
		 $('#parentId').combobox({
			 data: [{"departmentName": '请选择', "id": '-1'}],
			 valueField:'id',
			 textField:'departmentName',
			 value:'-1'
		 });
	}
		
	
	
    </script>
    <style type="text/css">
    
    </style>
</head>
<body style="padding:0 4px; margin:0;  overflow: hidden; ">
<div class="easyui-layout" style="width:100%;height:100%;" data-options="fit:true">
		<div title="科室管理" data-options="region:'north'" style="height:100px">
		 <form id="searchForm" >
			<table cellpadding="5">
				    		<tr>
				    			<td>科室名:</td>
				    			<td><input class="easyui-textbox" type="text" id="qdepartmentName"  name="departmentName" ></input></td>
				    		</tr>
				    		<tr>
				    			
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
				    <form id="ff" method="post" action="/auth/dpt/save.do" >
				    		<input type="hidden" id="id" name="id">
				    	<table cellpadding="5">
				    		<tr>
				    			<td>科室编号:</td>
				    			<td><input class="easyui-textbox" type="text" id="departmentCode"  name="departmentCode" data-options="required:true"></input></td>
				    		</tr>
				    		<tr>
				    			<td>科室名称:</td>
				    			<td><input class="easyui-textbox" type="text" id="departmentName"  name="departmentName" data-options="required:true"></input></td>
				    		</tr>
				    		<tr>
				    			<td>层级:</td>
				    			<td>
				    				<select class="easyui-combobox" id="level" name="level"  style="width: 100px" data-options="value:'1'">
				    						
				    				</select>
				    			</td>
				    		</tr>
				    		<tr>
				    			<td>上级科室:</td>
				    			<td>
				    				<select style="width: 200px" class="easyui-combobox" id="parentId" name="parentId" >
				    				</select>
				    			</td>
				    		</tr>
				    		
				    	</table>
				    </form>
				    
			</div>
		</div>
</body>
</html>