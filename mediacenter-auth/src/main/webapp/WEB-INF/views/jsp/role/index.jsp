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
			url:"/auth/role/list.do",//加载的URL
		    isField:"id",
			pagination:true,//显示分页
			pageSize:10,//分页大小
			pageList:[10,15,20],//每页的个数
			fit:true,//自动补全
			fitColumns:true,
			iconCls:"icon-save",//图标
			
			columns:[[      //每个列具体内容
		              {field:'id', hidden:true },   
		              {field:'roleName', title:'角色名',  width:100,align:'center' },
		              {field:'roleStatus', title:'状态',  width:100, align:'center',
		            	formatter: function(val,row, index) {
		            		if (val == 0){
		        				return '<span>正常('+val+')</span>';
		        			} else if (val == 9) {
		        				return '<span style="color:red;">停用('+val+')</span>';;
		        			}
		            	}  
		              },
		              {field:'isSys', title:'是否系统角色',  width:100, align:'center',
		            	  formatter: function(val,row, index) {
			            		if (val == 0){
			        				return '<span>用户角色('+val+')</span>';
			        			} else if (val == 1) {
			        				return '<span style="color:red;">系统角色('+val+')</span>';;
			        			}
			            	}    
		              }
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
			        					url : '/auth/role/delete.do',
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
			        		/*if(rows[0].isSys == 1) {
			        			$.messager.alert('错误', "不能修改系统角色", 'error');
			        			return;
			        		}*/
			        		//alert(rows[0].id);	
			        		$.ajax({
	        					url : '/auth/role/id'+rows[0].id+'.do',
	        					data : [],
	        					dataType : 'json',
	        					success : function(r) {
	        						if (r.id) {
	        							 $('#dd').dialog('open');
	        							$("#roleName").textbox("setValue", r.roleName);
	        							$("#id").val(r.id);
	        							
	        							var array = r.privileges.split(',');
	        	                           for(var i=0;i<array.length;i++)  
	        	                           {
	        	                        	   if(array[i] != "") {
	        	                             	  var node = $('#privilege-tree').tree('find',array[i]);  
	        	                             	 $('#privilege-tree').tree('check',node.target);  
	        	                        	   }
	        	                           }  
	        							
	        						
	        						} else {
	        							
	        							$.messager.alert('错误', r.msg, 'error');
	        						}
	        					}
	        				});
			        	} else {
			        		$.messager.alert('错误', "只能选择一个数据进行修改", 'error');
			        	}
			        }},
			        ]

		});
		
		 $('#dd').dialog({
             title: "My Dialog",
             closed:true,
             modal: true, //dialog继承自window，而window里面有modal属性，所以dialog也可以使用
             toolbar: [{
					text:'Ok',
				iconCls:'icon-ok',
				handler:function(){
					var pnodes= GetNode('fnode'), cnodes=GetNode('child');
					$('#ff').form('submit',{
						onSubmit:function(){
							var isValid = $(this).form('validate');
							if(isValid) {
								$("#privileges").val(cnodes);
							}
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
				
			},
			onClose: function() {
				$('#ff').form('clear');
				datagrid.datagrid('unselectAll');
				datagrid.datagrid('reload');
				 $('#privilege-tree').tree('reload');
			}
         });
		 
		 $('#privilege-tree').tree({
			    checkbox: true,
			    url: '/auth/menu/tree.do',
			    onClick:function(node){
				     alert('you click '+node.id);
			    } 
		   });
		 
	    
		$("#btn_search").click(function(){
			var param = $("#searchForm").serializeJson();
			datagrid.datagrid('load', param);

		});
		});
	
	
	function GetNode(type){
        var node = $('#privilege-tree').tree('getChecked');
        var cnodes='';
        var pnodes='';
        
         var prevNode=''; //保存上一步所选父节点
        for(var i=0;i<node.length;i++){
           
            if($('#privilege-tree').tree('isLeaf',node[i].target)){
                cnodes+=node[i].id+',';  
                
                var pnode = $('#privilege-tree').tree('getParent',node[i].target); //获取当前节点的父节点
               if(prevNode!=pnode.id) //保证当前父节点与上一次父节点不同
               {
                    pnodes+=pnode.id+',';
                    prevNode = pnode.id; //保存当前节点
               }
            }
        }
        cnodes = cnodes.substring(0,cnodes.length-1);
        pnodes = pnodes.substring(0,pnodes.length-1);
       
        if(type=='child'){return cnodes;}
        else{return pnodes;};
    };		
	
	
    </script>
    <style type="text/css">
    
    </style>
</head>
<body style="padding:0 4px; margin:0;  overflow: hidden; ">
<div class="easyui-layout" style="width:100%;height:100%;" data-options="fit:true">
		<div title="角色管理" data-options="region:'north'" style="height:100px">
		 <form id="searchForm" >
			<table cellpadding="5">
				    		<tr>
				    			<td>角色名:</td>
				    			<td><input class="easyui-textbox" type="text" id="qRoleName"  name="roleName" ></input></td>
				
				    			<td ><a href="#" id="btn_search" class="easyui-linkbutton" data-options="iconCls:'icon-search'" style="width:80px">Search</a></td>
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
				    <form id="ff" method="post" action="/auth/role/save.do" >
				    		<input type="hidden" id="id" name="id">
				    		<input type="hidden" id="privileges" name="privileges">
				    	<div style="margin:10px 0">
								角色名:<input class="easyui-textbox" type="text" id="roleName"  name="roleName" data-options="required:true"></input>
							</div>
							<div class="easyui-panel" style="padding:5px">
								<ul id="privilege-tree" class="easyui-tree" data-options=""></ul>
							</div>
				    </form>
				    
			</div>
			
			
		</div>
</body>
</html>