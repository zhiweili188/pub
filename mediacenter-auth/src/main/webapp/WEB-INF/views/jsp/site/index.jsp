<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <%@ include file="/WEB-INF/views/jsp/common.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Insert title here</title>
	  <link href="${ctx }/js/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css" /> 
	  <link href="${ctx }/js/ligerUI/skins/ligerui-icons.css" rel="stylesheet" type="text/css" /> 
	  <link href="${ctx }/js/ligerUI/skins/Gray/css/all.css" rel="stylesheet" type="text/css" /> 
	  <link href="${ctx }/css/good.css" rel="stylesheet" type="text/css" /> 

	    <script src="${ctx }/js/jquery/jquery-1.3.2.min.js" type="text/javascript"></script>    
	   <script src="${ctx }/js/ligerUI/js/core/base.js" type="text/javascript"></script>
  			 <script src="${ctx }/js/ligerUI/js/plugins/ligerDrag.js" type="text/javascript"></script> 
	    <script src="${ctx }/js/ligerUI/js/plugins/ligerToolBar.js" type="text/javascript"></script>
	    <script src="${ctx }/js/ligerUI/js/plugins/ligerGrid.js"></script>
	     <script src="${ctx }/js/ligerUI/js/plugins/ligerDialog.js" type="text/javascript"></script>
	   
	    <script src="${ctx }/js/jquery.cookie.js"></script>
	    <script src="${ctx }/js/json2.js"></script>
	    <script src="${ctx }/js/myfun.js"></script>
	    <script src="${ctx }/js/biz.js"></script>
		    
  <script type="text/javascript">
        var grid = null;
        $(function () {
            grid = $("#dataArea").ligerGrid({
            	checkbox: true,
                columns: [

                { display: '站点名', name: 'siteName', minWidth: 60 },
                { display: 'URL', name: 'siteUrl', minWidth: 140 }
                ], 
                params : [],
                url: "${ctx }/site/list.do", 
                toolbar: { 
                	items: [
                	        { text: '新增', click: add, icon: 'add'},
                	        { text: '修改', click: update, icon: 'modify'},
                	        { text: '删除', click: deleteSelectedData, icon: 'search2'}
                	]
                },
                pageParmName: 'currPage',               //页索引参数名，(提交给服务器)
                pagesizeParmName: 'pageSize',        //页记录数参数名，(提交给服务器)
                width: '100%',height:'100%'
            });


            $("#pageloading").hide();
        });
        
        function add()
        {
        	window.location='${ctx }/site/init_add.do';
        /*	$.ligerDialog.open({  url: '${ctx }/site/init_add.do', height: 300,width: 500, 
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
        	*/
        }
        
        function update()
        {
        	 var row = grid.getSelectedRow();
             if (!row) { alert('请选择行'); return; }
             alert(JSON.stringify(row));
             
        	var ids = getCheckBoxValuesArray(".dataCheckBox");alert(ids.length);
        	if(ids.length != 1) {
        		 $.ligerDialog.warn('请选择一条数据后进行修改。');
        		 return;
        	}
        	$.ligerDialog.open({  url: '${ctx }/site/init_update.do?id='+ids, height: 300,width: 500, 
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
			if(ids == ""){
				$.ligerDialog.warn('请选择');
				return;
			}
        	var action =new  Action({
        		url: "${ctx }/site/del.do",
        		params: "id="+ids,
        		dataType:"json",
        		callback:function(result){
        		//	grid.reload();
        			$.ligerDialog.success('删除成功');
        		}
        	});
        	action.ajaxPostData();
        }
        function f_search()
        {
        	  var gridparms = [];
              gridparms.push({ name: "siteName", value: $("#siteName").val() });
             // gridparms.push({ name: "currPage", value:  grid.options.page });
              //gridparms.push({ name: "pagesize", value: grid.options.pageSize });
              grid.loadServerData(gridparms);
        }

    </script>
    <style type="text/css">
    
    </style>
</head>
<body style="padding:6px; overflow:hidden;">
<div id="searchbar">
		<table>
			<tr>
				<td> 站点名：<input id="siteName" type="text" /></td>
				<td> URL：<input id="siteUrl" type="text" /></td>
				<td> <input id="btnOK" type="button" value="查询" onclick="f_search()" /></td>
			</tr>
		</table>
</div>
    <div id="dataArea" style="margin:0; padding:0"></div>
   

  <div style="display:none;">
  <!-- g data total ttt -->
</div>
 
</body>
</html>