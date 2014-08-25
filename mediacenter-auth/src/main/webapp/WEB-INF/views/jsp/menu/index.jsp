<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@  page isELIgnored="false"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
  <link href="/auth/js/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css" /> 
   <link href="/auth/js/ligerUI/skins/Gray/css/all.css" rel="stylesheet" type="text/css" />
   <link href="/auth/js/ligerUI/skins/ligerui-icons.css" rel="stylesheet" type="text/css" />
		    <script src="/auth/js/jquery/jquery-1.3.2.min.js" type="text/javascript"></script>    
		    <script src="/auth/js/ligerUI/js/ligerui.all.js" type="text/javascript"></script> 
		    <script src="/auth/js/ligerUI/js/plugins/ligerToolBar.js" type="text/javascript"></script>
		    <script src="/auth/js/ligerUI/js/plugins/ligerGrid.js"></script>
		     <script src="/auth/js/ligerUI/js/plugins/ligerDialog.js" type="text/javascript"></script>
		    <script src="/auth/js/jquery.cookie.js"></script>
		    <script src="/auth/js/json2.js"></script>
		    <script src="/auth/js/myfun.js"></script>
		    <script src="/auth/js/biz.js"></script>
		    
		     <script type="text/javascript">
        var grid = null;
        $(function () {
            grid = $("#maingrid4").ligerGrid({
            	checkbox: true,
                columns: [

                { display: '公司名', name: 'menuName', minWidth: 60 },
                { display: '联系名', name: 'menuOrder', width: 50,align:'left' }, { display: '联系名', name: 'menuAction', minWidth: 140 }
                ], 
                params : [],
                url: "/auth/menu/list.do", 
                toolbar: { 
                	items: [
                	        { text: '新增', click: add, icon: 'search2'},
                	        { text: '修改', click: update, icon: 'search2'},
                	        { text: '删除', click: deleteSelectedData, icon: 'search2'}
                	]
                },
                width: '100%',height:'100%',
                pageSize: 10,                        
                sortName : null,

                sortOrder:null,      

                root :'Rows',                       //数据源字段名
                record:'Total',                     //数据源记录数字段名
                pageParmName :'currPage',               //页索引参数名，(提交给服务器)
                pagesizeParmName:'pageSize',        //页记录数参数名，(提交给服务器)
                sortnameParmName:'sortname',        //页排序列名(提交给服务器)
                sortorderParmName:'sortorder',      //页排序方向(提交给服务器)
            });


            $("#pageloading").hide();
        });
        
        function add()
        {
        	$.ligerDialog.open({  url: '/auth/menu/init_add.do', height: 300,width: 500, 
        		id: "editDailog",
        		name: "editFrame",
        		title: '编辑',     
        		buttons: [ 
        		           { text: '确定', onclick: function (item, dialog) {
        		       			 $(window.frames["editFrame"].document).find(":submit").click();
        		        	   
        		           } }, 
        		           { text: '取消', onclick: function (item, dialog) {
        		        	   dialog.close(); 
        		           } } ] 
        	
        	});
        	
        }
        
        function update()
        {
        	var ids = getCheckedData(grid);
        	if(ids.length==0) {
        		$.ligerDialog.alert("请选择","警告");
        		return;
        	}
        	var arr = ids.split(",");
        	if(arr.length > 1) {
        		$.ligerDialog.alert("只能选择一个","警告");
        		return;
        	}
        	$.ligerDialog.open({  url: '/auth/menu/init_update.do?id='+ids, height: 300,width: 500, 
        		id: "editDailog",
        		name: "editFrame",
        		title: '编辑',     
        		buttons: [ 
        		           { text: '确定', onclick: function (item, dialog) {
        		       			 $(window.frames["editFrame"].document).find(":submit").click();
        		        	   
        		           } }, 
        		           { text: '取消', onclick: function (item, dialog) {
        		        	   dialog.close(); 
        		           } } ] 
        	
        	});
        	
        }
        
        function deleteSelectedData()
        {
        	var ids = getCheckedData(grid);
        	//alert(ids);
        	if(ids.length==0) {
        		$.ligerDialog.alert("请选择","警告");
        		return;
        	}
        	var action =new  Action({
        		url: "/auth/menu/del.do",
        		params: "id="+ids,
        		dataType:"json",
        		callback:function(result){alert(result)}
        	});
        	
        	$.ligerDialog.confirm("确定要删除吗？",function(flag){
        		if(flag){
        		  	$.ajax({
              		  type: 'POST',
              		  url: "/auth/menu/del.do",
              		  data: "id="+ids,
              		  success: function(result){f_search()},
              		  dataType: "text"
              		});
        		}
        	});
        	//action.ajaxPostData();
      
        }
        function f_search()
        {
        	  var gridparms = [];
              gridparms.push({ name: "menuName", value: $("#menuName").val() });
              gridparms.push({ name: "currPage", value: 1 });
              gridparms.push({ name: "pageSize", value: grid.options.pageSize });
              grid.loadServerData(gridparms);
        }

    </script>
</head>
<body style="padding:6px; overflow:hidden;">
<div id="searchbar">
		<table>
			<tr>
				<td> 主键：<input id="menuName" type="text" /></td>
				<td> <input id="btnOK" type="button" value="button" onclick="f_search()" /></td>
			</tr>
		</table>
</div>
    <div id="maingrid4" style="margin:0; padding:0"></div>

  <div style="display:none;">
  <!-- g data total ttt -->
</div>
 
</body>
</html>