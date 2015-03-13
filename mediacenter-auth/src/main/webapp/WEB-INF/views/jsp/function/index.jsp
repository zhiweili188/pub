<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@  page isELIgnored="false"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
  <link href="/auth/js/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css" /> 

		    <script src="/auth/js/jquery/jquery-1.3.2.min.js" type="text/javascript"></script>    
		     
		      <script src="/auth/js/ligerUI/js/core/base.js" type="text/javascript"></script>
			   <script src="/auth/js/ligerUI/js/plugins/ligerForm.js" type="text/javascript"></script>
			   <script src="/auth/js/ligerUI/js/plugins/ligerDateEditor.js" type="text/javascript"></script>
			   <script src="/auth/js/ligerUI/js/plugins/ligerComboBox.js" type="text/javascript"></script>
			   <script src="/auth/js/ligerUI/js/plugins/ligerCheckBox.js" type="text/javascript"></script>
			   <script src="/auth/js/ligerUI/js/plugins/ligerButton.js" type="text/javascript"></script>
			   <script src="/auth/js/ligerUI/js/plugins/ligerDialog.js" type="text/javascript"></script>
			   <script src="/auth/js/ligerUI/js/plugins/ligerRadio.js" type="text/javascript"></script>
			   <script src="/auth/js/ligerUI/js/plugins/ligerSpinner.js" type="text/javascript"></script>
			   <script src="/auth/js/ligerUI/js/plugins/ligerTextBox.js" type="text/javascript"></script>
			   
			    <script src="/auth/js/ligerUI/js/plugins/ligerGrid.js" type="text/javascript"></script>
			    <script src="/auth/js/ligerUI/js/plugins/ligerTab.js" type="text/javascript"></script>
			    <script src="/auth/js/ligerUI/js/plugins/ligerDrag.js" type="text/javascript"></script>
    
		    <script src="/auth/js/jquery.cookie.js"></script>
		    <script src="/auth/js/json2.js"></script>
		    <script src="/auth/js/myfun.js"></script>
		    <script src="/auth/js/biz.js"></script>
		    
		   <script type="text/javascript">
        $(function ()
        {
        	 $("#navtab1").ligerTab();
            $("form").ligerForm();
        }); 
        
        function startReq(){
        	$.ajax({
  			  type: 'get',
  			  url: "/auth/func/setup.do",
  			data:"id=10",
  			  success: function(result){
  				 $.ligerDialog.success('提示内容');
  				  
  			  }
  			});
        	
        }
        function startReq2(){
        	$.ajax({
  			  type: 'get',
  			  url: "/auth/func/getParam.do",
  			data:"id=10&name=jack",
  			  success: function(result){
  				 $.ligerDialog.success('提示内容');
  				  
  			  }
  			});
        	
        }
    </script>
    <style type="text/css">
        .l-table-edit {}
        .l-table-edit-td{ padding:4px;}
        .l-button-submit,.l-button-reset{width:80px; float:left; margin-left:10px; padding-bottom:2px;}
    </style>
    
</head>
<body style="padding:6px; overflow:hidden;">
<div id="navtab1" style="width: 600px;overflow: auto; border:1px solid #A3C0E8; ">
	<div tabid="home" title="我的主页" lselected="true"  style="height:300px; overflow: auto;" >
	 	<form name="form1" method="post" action="" id="form1">

        <table cellpadding="0" cellspacing="0" class="l-table-edit" >
            <tr>
                <td align="right" class="l-table-edit-td">名字:</td>
                <td align="left" class="l-table-edit-td"><input name="txtName" type="text" id="txtName" ltype="text" /></td>
                <td align="left"></td>
            </tr>
            <tr>
                <td align="right" class="l-table-edit-td" valign="top">性别:</td>
                <td align="left" class="l-table-edit-td">
                    <input id="rbtnl_0" type="radio" name="rbtnl" value="1" checked="checked" /><label for="rbtnl_0">男</label> <input id="rbtnl_1" type="radio" name="rbtnl" value="2" /><label for="rbtnl_1">女</label>
                </td><td align="left"></td>
            </tr>   
            
            <tr>
                <td align="right" class="l-table-edit-td" valign="top">爱好:</td>
                <td align="left" class="l-table-edit-td">
                     <input id="CheckBoxList1_0" type="checkbox" name="CheckBoxList1$0" checked="checked" /><label for="CheckBoxList1_0">篮球</label><br /><input id="CheckBoxList1_1" type="checkbox" name="CheckBoxList1$1" /><label for="CheckBoxList1_1">网球</label> <br /><input id="CheckBox1" type="checkbox" name="CheckBoxList1$1" /><label for="CheckBoxList1_1">足球</label>      
                </td><td align="left"></td>
            </tr>  
                 
            <tr>
                <td align="right" class="l-table-edit-td">入职日期:</td>
                <td align="left" class="l-table-edit-td">
                    <input name="txtDate" type="text" id="txtDate" ltype="date" />
                </td><td align="left"></td>
            </tr>
            <tr>
                <td align="right" class="l-table-edit-td">年龄:</td>
                <td align="left" class="l-table-edit-td">
                    <input name="txtAge" type="text" id="txtAge" ltype='spinner' ligerui="{type:'int'}" value="20" />
                </td><td align="left"></td>
            </tr>
            <tr>
                <td align="right" class="l-table-edit-td">部门:</td>
                <td align="left" class="l-table-edit-td">
	                <select name="ddlDepart" id="ddlDepart" ltype="select">
					<option value="1">主席</option>
					<option value="2">研发中心</option>
					<option value="3">销售部</option>
					<option value="4">市场部</option>
					<option value="5">顾问组</option>
				</select>
                </td>
            </tr>
            <tr>
                <td align="right" class="l-table-edit-td">地址:</td>
                <td align="left" class="l-table-edit-td"> 
                <textarea cols="100" rows="4" class="l-textarea" style="width:400px"></textarea>
                </td><td align="left"></td>
            </tr>
        </table>
			 <br />
			<input type="submit" value="提交" id="Button1" class="l-button l-button-submit" /> 
			<input type="reset" value="重置" class="l-button l-button-reset"/>
	    </form>
	</div>
	<div  title="我的主页2" showClose="true">
		<div id="maingrid2" style="margin:10px; height:300px;">
			<input type="button" value="@RequestParam" id="Button1" class="l-button l-button-submit"  onclick="startReq();"/> 
			<input type="button" value=" Param map" id="Button2" class="l-button l-button-submit"  onclick="startReq2();"/> 
		</div>
	</div>
	<div  title="我的主页3"  showClose="true">
		<div id="Div1" style="margin:10px;height:300px;">我的主页3</div>
	</div>
	<div  title="我的主页4" showClose="true">
		<div id="Div2" style="margin:10px;height:300px;">我的主页4</div>
	</div>
</div>

 
    <div style="display:none">
    <!--  数据统计代码 --></div>
 
</body>
</html>