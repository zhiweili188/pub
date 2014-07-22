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
		    
		     <script type="text/javascript">
        var grid = null;
        var CustomersData ={};
        $(function () {
            grid = $("#maingrid4").ligerGrid({
                columns: [
                { display: '主键', name: 'id', align: 'left', width: 120 },
                { display: '公司名', name: 'menuName', minWidth: 60 },
                { display: '联系名', name: 'menuOrder', width: 50,align:'left' }, { display: '联系名', name: 'menuAction', minWidth: 140 }
                ], 
                checkbox :true,
                params : [],
                url: "/auth/menu/list.do", 
                toolbar: { 
                	items: [{ text: '新增', click: add, icon: 'search2'}]
                },
                width: '100%',height:'100%'
            });


            $("#pageloading").hide();
        });
        
        function add()
        {
        	 $.ligerDialog.open({ url: '/auth/menu/init_add.do', height: 300, width: 500,   isResize: true
        	                                                                                });
        }
        function f_search()
        {
        	  var gridparms = [];
              gridparms.push({ name: "menuName", value: $("#menuName").val() });
              gridparms.push({ name: "page", value: 1 });
              gridparms.push({ name: "pagesize", value: grid.options.pageSize });
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