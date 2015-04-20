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
			url:"/auth/user/list.do",//加载的URL
		    isField:"id",
			pagination:true,//显示分页
			pageSize:15,//分页大小
			pageList:[15,30,60,100],//每页的个数
			fit:true,//自动补全
			fitColumns:true,
			iconCls:"icon-save",//图标
			
			columns:[[      //每个列具体内容
		              {field:'id', hidden:true },   
		              {field:'userName', title:'用户名',  width:100, align:'center' },   
		              {field:'fullName',title:'姓名',width:100, align:'center'},   
		              {field:'userType',title:'类型',width:100, align:'center',
		            	  formatter: function(val,row, index) {
			            		if (val == 0){
			        				return '<span>普通用户('+val+')</span>';
			        			} else if (val == 1) {
			        				return '<span style="color:red;">系统用户('+val+')</span>';;
			        			}
			            	}    
		            	  
		              },
		              {field:'status', title:'状态',  width:100, align:'center',
			            	formatter: function(val,row, index) {
			            		if (val == 0){
			        				return '<span>正常('+val+')</span>';
			        			} else if (val == 9) {
			        				return '<span style="color:red;">停用('+val+')</span>';;
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
			        		var rows = datagrid.datagrid('getSelections');
			        		for(var i=0; i<rows.length; i++){
	        					if(rows[i].userType == 1){
	        						$.messager.alert('错误', "不能删除系统用户", 'error');
				        			return;
	        					}
	        				}
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
			        					url : '/auth/user/deleteMore.do',
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
			        		if(rows[0].userType == 1) {
			        		//	$.messager.alert('错误', "不能修改系统用户", 'error');
			        		//	return;
			        		}
			        		//alert(rows[0].id);	
			        		$.ajax({
	        					url : '/auth/user/id'+rows[0].id+'.do',
	        					data : [],
	        					dataType : 'json',
	        					success : function(r) {
	        						if (r.id) {
	        							 $('#dd').dialog('open');
	        							$("#userName").textbox("setValue", r.userName);
	        							$("#fullName").textbox("setValue", r.fullName);
	        							$("#id").val(r.id);
	        							
	        						} else {
	        							
	        							$.messager.alert('错误', r.msg, 'error');
	        						}
	        					}
	        				});
			        		
			        		$.ajax({
			        			url : "/auth/userrole/"+rows[0].id+'.do',
			        			dataType : 'json',
			        			success : function(r) {
			        				 var html = "";
			        	         	 $.each(r, function(index, json) {
			        	         			html += "<option value='" + json.id + "'>" ;
			        	                  	html += json.roleName + "</option>";
			        	               	
			        	            });
			        	              $("#s2").html(html);
			        			}
			        		});
			        	} else {
			        		$.messager.alert('错误', "只能选择一个数据进行修改", 'error');
			        	}
			        }},
			        {text:"导入",iconCls:"icon-search",handler:function(){
			        	
			        	 $('#ddUpload').dialog('open');
			        }},
			        {text:"新建用户",iconCls:"icon-save",handler:function(){
			        	
			        }},
			        {text:"修改密码",iconCls:"icon-redo",handler:function(){
			        	 $('#ddPwd').dialog("open");
			        	
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
					$('#ff').form('submit',{
						onSubmit:function(){
							var isValid = $(this).form('validate');
							 var array = new Array(); //定义数组
							    $("#s2 option").each(function(){ //遍历全部option
							        var txt = $(this).val(); //获取option的内容
							         array.push(txt); //添加到数组中
							    });
							 $("#roleIds").val(array.join(","));
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
				var rows=datagrid.datagrid('getSelections');
				getRole(rows[0].id);
			},
			onClose: function() {
				datagrid.datagrid('unselectAll');
			}
         });
		 
		 $('#ddPwd').dialog({
             title: "修改密码",
             closed:true,
             modal: true, //dialog继承自window，而window里面有modal属性，所以dialog也可以使用
             toolbar: [{
					text:'Ok',
				iconCls:'icon-ok',
				handler:function(){
					$('#setPwdForm').form('submit');
					$('#setPwdForm').form('clear');
					$('#ddPwd').dialog('close');
					
				}
			},{
				text:'Cancel',
				handler:function(){
					$('#ddPwd').dialog('close');
					$('#setPwdForm').form('clear');
				}
			}],
			onBeforeOpen: function() {
				var rows=datagrid.datagrid('getSelections');
	        	if(rows.length==1)
	        	{
	        		$("#idPwd").val(rows[0].id);
	        		
	        	} else {
	        		$.messager.alert('错误', "请选择一个用户", 'error');
	        		return false;
	        	}
			},
			onClose: function() {
				datagrid.datagrid('unselectAll');
			}
         });

		 $('#ddUpload').dialog({
             title: "上传",
             closed:true,
             modal: true, //dialog继承自window，而window里面有modal属性，所以dialog也可以使用

			onClose: function() {
				datagrid.datagrid('unselectAll');
			}
         });
	    
		$("#btn_search").click(function(){
			var param = $("#searchForm").serializeJson();
			datagrid.datagrid('load', param);

		});
		
		var uploader = new plupload.Uploader({
    		runtimes : 'html5,flash,silverlight,html4',
    		browse_button : 'pickfiles', // you can pass in id...
    		container: document.getElementById('container'), // ... or DOM Element itself
    		url : '/auth/user/upload.do',
    		flash_swf_url : '/auth/js/plupload-2.1.2/js/Moxie.swf',
    		silverlight_xap_url : '/auth/js/plupload-2.1.2/js/Moxie.xap',
    		
    		multi_selection: false,
    		multiple_queues: false,
    		filters : {
    			max_file_size : '10mb',
    			mime_types: [
    				{title : "Image files", extensions : "jpg,gif,png"},
    				{title : "Zip files", extensions : "zip"}
    			]
    		},
    		// PreInit events, bound before the internal events
		preinit : {
			Init: function(up, info) {
				log('[Init]', 'Info:', info, 'Features:', up.features);
			},
			UploadFile: function(up, file) {
				log('[UploadFile]', file);
				// You can override settings before the file is uploaded
				// up.setOption('url', 'upload.php?id=' + file.id);
				// up.setOption('multipart_params', {param1 : 'value1', param2 : 'value2'});
			}
		},
		
    		init: {
    			PostInit: function() {
    				
    				log('[PostInit]');
    				document.getElementById('filelist').innerHTML = '';

    				document.getElementById('uploadfiles').onclick = function() {
    					uploader.start();
    					return false;
    				};
    			},

    			FilesAdded: function(up, files) {
    				if(up.files.length > 1) {
    					log('up.files.length'+up.files.length);
    					return false;
    				}
    				log('[FilesAdded]');
    				plupload.each(files, function(file) {
    					log('  File:', file);
    					document.getElementById('filelist').innerHTML += '<div id="' + file.id + '">' + file.name + ' (' + plupload.formatSize(file.size) + ') <b></b></div>';
    				});
    			},

    			UploadProgress: function(up, file) {
    				log('[UploadProgress]', 'File:', file, "Total:", up.total);
    				document.getElementById(file.id).getElementsByTagName('b')[0].innerHTML = '<span>' + file.percent + "%</span>";
    			},
    			
    			Browse: function(up) {
					// Called when file picker is clicked
					log('[Browse]');
    			},
    			StateChanged: function(up) {
					// Called when the state of the queue is changed
					log('[StateChanged]', up.state == plupload.STARTED ? "STARTED" : "STOPPED");
    			},
    			
    			QueueChanged: function(up) {
						// Called when queue is changed by adding or removing files
						log('[QueueChanged]');
    			},
    			
    			OptionChanged: function(up, name, value, oldValue) {
    				// Called when one of the configuration options is changed
    				log('[OptionChanged]', 'Option Name: ', name, 'Value: ', value, 'Old Value: ', oldValue);
    			},
    			
    			BeforeUpload: function(up, file) {
    				// Called right before the upload for a given file starts, can be used to cancel it if required
    				log('[BeforeUpload]', 'File: ', file);
    			},
    			
    			FileFiltered: function(up, file) {
    				// Called when file successfully files all the filters
					log('[FileFiltered]', 'File:', file);
    			},
    			FileUploaded: function(up, file, info) {
					// Called when file has finished uploading
					log('[FileUploaded] File:', file, "Info:", info);
				},
    			ChunkUploaded: function(up, file, info) {
					// Called when file chunk has finished uploading
					log('[ChunkUploaded] File:', file, "Info:", info);
					},

					UploadComplete: function(up, files) {
						// Called when all files are either uploaded or failed
					log('[UploadComplete]');
					},

					Destroy: function(up) {
						// Called when uploader is destroyed
					log('[Destroy] ');
					},

    			Error: function(up, err) {
    				document.getElementById('console').innerHTML += "\nError #" + err.code + ": " + err.message;
    			}
    		}
    	});

    	uploader.init();
    	
		
	});

	function log() {
		var str = "";

		plupload.each(arguments, function(arg) {
		var row = "";
		
		if (typeof(arg) != "string") {
			plupload.each(arg, function(value, key) {
				// Convert items in File objects to human readable form
				if (arg instanceof plupload.File) {
						// Convert status to human readable
						switch (value) {
							case plupload.QUEUED:
								value = 'QUEUED';
								break;

							case plupload.UPLOADING:
								value = 'UPLOADING';
								break;

							case plupload.FAILED:
								value = 'FAILED';
								break;
							
							case plupload.DONE:
								value = 'DONE';
								break;
						}
					}
		
				if (typeof(value) != "function") {
					row += (row ? ', ' : '') + key + '=' + value;
				}
			});
					
							str += row + " ";
			} else {
				str += arg + " ";
				}
		});
		
var log = document.getElementById('console');
		log.innerHTML += str + "\n";
}
    </script>
    <style type="text/css">
    
    </style>
</head>
<body style="padding:0 4px; margin:0;  overflow: hidden; ">
<div class="easyui-layout" style="width:100%;height:100%;" data-options="fit:true">
		<div title="用户管理" data-options="region:'north'" style="height:100px">
		 <form id="searchForm" >
			<table cellpadding="5">
				    		<tr>
				    			<td>Name:</td>
				    			<td><input class="easyui-textbox" type="text" id="qUserName"  name="userName" ></input></td>
				    		</tr>
				    		<tr>
				    			<td>fullName:</td>
				    			<td><input class="easyui-textbox" type="text" id="qFullName"  name="fullName" ></input></td>
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
				    <form id="ff" method="post" action="/auth/user/save.do">
				    		<input type="hidden" id="id" name="id">
				    		<input type="hidden" id="roleIds" name="roleIds">
				    	<table cellpadding="5">
				    		<tr>
				    			<td>Name:</td>
				    			<td><input class="easyui-textbox" type="text" id="userName"  name="userName" data-options="required:true"></input></td>
				    		</tr>
				    		<tr>
				    			<td>fullName:</td>
				    			<td><input class="easyui-textbox" type="text" id="fullName"  name="fullName" data-options="required:true"></input></td>
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
				    		<tr>
				    			<td>角色:</td>
				    			<td>
				    				<table width="288" border="0" cellpadding="0" cellspacing="0">  
									  <tr>  
									    <td width="100">
									    <select size="10" multiple="multiple" id="s1"  style="width:100px; border: 1px solid #95b8e7;">  
									    </select>
									    </td>  
									    <td width="37" align="center">
									    <input type="button" name="addall" id="addall" value=">|" /><br />
									    <input type="button" name="add" id="add" value=">>" /><br />
									    <input type="button" name="remove" id="remove" value="&lt;&lt;" /><br />
									    <input type="button" name="removeall" id="removeall" value="|&lt;" />
									    </td>  
									    <td width="100">
									    <select  size="10" multiple="multiple" id="s2" style="width:100px; border: 1px solid #95b8e7;" >  
									    </select>
									    </td>  
									  </tr>  
									</table>  
				    			</td>
				    		</tr>
				    	</table>
				    </form>
				    
			</div>
			<div id="ddPwd" title="My Dialog"  style="width:600px;height:450px; text-align: center; " data-options="closed:true"> 
				    <form id="setPwdForm" method="post" action="/auth/user/updatePwd.do">
				    	<input type="hidden" id="idPwd" name="id">
				    	<table cellpadding="5">
				    		<tr>
				    			<td>password:</td>
				    			<td><input class="easyui-textbox" type="password" name="password" data-options="required:true"></input></td>
				    		</tr>
				    	</table>
				    </form>
				    
			</div>
			<div id="ddUpload" title="My Dialog"  style="width:600px;height:450px; text-align: center; " data-options="closed:true"> 
					
					<div id="container">
						<a id="pickfiles"  href="#" class="easyui-linkbutton" data-options="iconCls:'icon-add'">选择文件</a>
					    <a id="uploadfiles" href="javascript:;" class="easyui-linkbutton" data-options="iconCls:'icon-save'">上传文件</a>
					</div>
					 <div id="filelist">Your browser doesn't have Flash, Silverlight or HTML5 support.</div>
					<br />
					<pre id="console"></pre>
				    
			</div>
		</div>
</body>
<script type="text/javascript">
$(function(){
	  
	$("#s1").dblclick(function(){  
	  $("option:selected",this).clone().appendTo("#s2");  
	  $("option:selected",this).remove();  
	//  $("#s1 option:first,#s2 option:first").attr("selected",true);  
	});  
	  
	$("#s2").dblclick(function(){  
	  $("option:selected",this).clone().appendTo("#s1");  
	  $("option:selected",this).remove();  
	 // $("#s1 option:first,#s2 option:first").attr("selected",true);  
	});  
	  
	$("#add").click(function(){  
	  $("#s1 option:selected").clone().appendTo("#s2");  
	  $("#s1 option:selected").remove();  
	  //$("#s1 option:first,#s2 option:first").attr("selected",true);  
	});  
	  
	$("#remove").click(function(){  
	  $("#s2 option:selected").clone().appendTo("#s1");  
	  $("#s2 option:selected").remove();  
	//  $("#s1 option:first,#s2 option:first").attr("selected",true);  
	});  
	  
	$("#addall").click(function(){  
	  $("#s1 option").clone().appendTo("#s2");  
	  $("#s1 option").remove();  
	 // $("#s1 option:first,#s2 option:first").attr("selected",true);  
	});  
	  
	$("#removeall").click(function(){  
	  $("#s2 option").clone().appendTo("#s1");  
	  $("#s2 option").remove();  
	 // $("#s1 option:first,#s2 option:first").attr("selected",true);  
	});  
	  

});

function getRole(userId){
	$.ajax({
		url : "/auth/role/choose"+userId+".do",
		dataType : 'json',
		success : function(r) {
			 var html = "";
         	 $.each(r, function(index, json) {
         			html += "<option value='" + json.id + "'>" ;
                  	html += json.roleName + "</option>";
               	
            });
              $("#s1").html(html);
		}
	});
}
</script>
</html>