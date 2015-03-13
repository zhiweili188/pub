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
		<link href="/auth/js/easyui/themes/default/easyui.css" rel="stylesheet" type="text/css" />
		<link href="/auth/js/easyui/themes/icon.css" rel="stylesheet" type="text/css" />
		    
  <script type="text/javascript">
	var datagrid;
	var rowEditor=undefined;
	$(function(){
		datagrid=$("#dg").datagrid({
			url:"/auth/user/list.do",//加载的URL
		    isField:"id",
			pagination:true,//显示分页
			pageSize:5,//分页大小
			pageList:[5,10,15,20],//每页的个数
			fit:true,//自动补全
			fitColumns:true,
			iconCls:"icon-save",//图标
			
			columns:[[      //每个列具体内容
		              {field:'id', hidden:true },   
		              {field:'userName', title:'用户名',  width:100 },   
		              {field:'fullName',title:'姓名',width:100},   
		              {field:'text',title:'用户组',width:100}   
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
			        	else if(rows.length>1)
			        	{
			        		$.messager.alert('警告','不支持批量删除','error');
			        	}
			        	else
			        	{
			        		$.messager.confirm('确定','您确定要删除吗',function(t){
			        			if(t)
			        			{
			        				
			        				$.ajax({
			        					url : '/Test3/ModuleBeanController/deletecustomer.do',
			        					data : rows[0],
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
	        					url : '/auth/user/id'+rows[0].id+'.do',
	        					data : [],
	        					dataType : 'json',
	        					success : function(r) {
	        						alert(r);
	        						if (r.success) {
	        							
	        							$.messager.show({
	        								msg : r.msg,
	        								title : '成功'
	        							});
	        							
	        						} else {
	        							
	        							$.messager.alert('错误', r.msg, 'error');
	        						}
	        						datagrid.datagrid('unselectAll');
	        					}
	        				});
			        	} else {
			        		$.messager.alert('错误', "只能选择一个数据进行修改", 'error');
			        	}
			        }},
			        {text:"查询",iconCls:"icon-search",handler:function(){}},
			        {text:"保存",iconCls:"icon-save",handler:function(){
			        	
			        	datagrid.datagrid('endEdit',rowEditor);
			        	rowEditor=undefined;
			        }},
			        {text:"取消编辑",iconCls:"icon-redo",handler:function(){
			        	rowEditor=undefined;
			        	datagrid.datagrid('rejectChanges')
			        }}
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
					$('#ff').form('submit');
					$('#ff').form('clear');
				}
			},{
				text:'Cancel',
				handler:function(){
					$('#dd').dialog('close');
					$('#ff').form('clear');
				}
			}] 
         });

	    
		$("#search").click(function(){
			datagrid.datagrid('load',{
				text:$("#text").val()
			});

		});
		
	});
    </script>
    <style type="text/css">
    
    </style>
</head>
<body style="padding:0 4px; margin:0;  overflow: hidden; ">
<div class="easyui-layout" style="width:100%;height:100%;">
		<div title="用户管理" data-options="region:'north'" style="height:100px">这里放查询条件</div>
		<div data-options="region:'center'" >
			<table id="dg" >
			</table>
		</div>

			
<div id="dd" title="My Dialog"  style="width:600px;height:450px; text-align: center; " data-options="closed:true"> 
				    <form id="ff" method="post" action="/auth/user/save.do">
				    	<table cellpadding="5">
				    		<tr>
				    			<td>Name:</td>
				    			<td><input class="easyui-textbox" type="text" name="userName" data-options="required:true"></input></td>
				    		</tr>
				    		<tr>
				    			<td>fullName:</td>
				    			<td><input class="easyui-textbox" type="text" name="fullName" data-options="required:true"></input></td>
				    		</tr>
				    		<tr>
				    			<td>password:</td>
				    			<td><input class="easyui-textbox" type="password" name="password" data-options="required:true"></input></td>
				    		</tr>
				    		<tr>
				    			<td>Message:</td>
				    			<td><input class="easyui-textbox" name="message" data-options="multiline:true" style="height:60px"></input></td>
				    		</tr>
				    		<tr>
				    			<td>Language:</td>
				    			<td>
				    				<select class="easyui-combobox" name="language"><option value="ar">Arabic</option><option value="bg">Bulgarian</option><option value="ca">Catalan</option><option value="zh-cht">Chinese Traditional</option><option value="cs">Czech</option><option value="da">Danish</option><option value="nl">Dutch</option><option value="en" selected="selected">English</option><option value="et">Estonian</option><option value="fi">Finnish</option><option value="fr">French</option><option value="de">German</option><option value="el">Greek</option><option value="ht">Haitian Creole</option><option value="he">Hebrew</option><option value="hi">Hindi</option><option value="mww">Hmong Daw</option><option value="hu">Hungarian</option><option value="id">Indonesian</option><option value="it">Italian</option><option value="ja">Japanese</option><option value="ko">Korean</option><option value="lv">Latvian</option><option value="lt">Lithuanian</option><option value="no">Norwegian</option><option value="fa">Persian</option><option value="pl">Polish</option><option value="pt">Portuguese</option><option value="ro">Romanian</option><option value="ru">Russian</option><option value="sk">Slovak</option><option value="sl">Slovenian</option><option value="es">Spanish</option><option value="sv">Swedish</option><option value="th">Thai</option><option value="tr">Turkish</option><option value="uk">Ukrainian</option><option value="vi">Vietnamese</option></select>
				    			</td>
				    		</tr>
				    	</table>
				    </form>
				    
			</div>
		</div>
</body>
</html>