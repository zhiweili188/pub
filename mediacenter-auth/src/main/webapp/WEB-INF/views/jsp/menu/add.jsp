<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@  page isELIgnored="false"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>add</title>
    <link href="/auth/js/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css" /> 
    <link href="/auth/js/ligerUI/skins/Gray/css/all.css" rel="stylesheet" type="text/css" /> 
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
      <script src="/auth/js/ligerUI/js/plugins/ligerTip.js" type="text/javascript"></script>
    <script src="/auth/js/jquery-validation/jquery.validate.min.js" type="text/javascript"></script> 
    <script src="/auth/js/jquery-validation/jquery.metadata.js" type="text/javascript"></script>
    <script src="/auth/js/jquery-validation/messages_cn.js" type="text/javascript"></script>
    <script src="/auth/js/jquery/jquery.form.js" type="text/javascript"></script>
   
     <script type="text/javascript">
        var eee;
        $(function ()
        {
            $.metadata.setType("attr", "validate");
            var v = $("form").validate({
                debug: true,
                errorPlacement: function (lable, element)
                {
                    if (element.hasClass("l-textarea"))
                    {
                        element.ligerTip({ content: lable.html(), target: element[0] }); 
                    }
                    else if (element.hasClass("l-text-field"))
                    {
                        element.parent().ligerTip({ content: lable.html(), target: element[0] });
                    }
                    else
                    {
                        lable.appendTo(element.parents("td:first").next("td"));
                    }
                },
                success: function (lable)
                {
                    lable.ligerHideTip();
                    lable.remove();
                },
                submitHandler: function (form)
                {
                    $("form .l-text,.l-textarea").ligerHideTip();
                    $(form).ajaxSubmit(); 
                    grid.reload();
                }
            });
            $("form").ligerForm();
            $(".l-button-test").click(function ()
            {
                
            });
        });  
    </script>
    <style type="text/css">
       
    </style>

</head>
<body>
<form id="form2" action="/auth/menu/save.do">
<input type="hidden" id="id" name="id" value="${menu.id }">
<div >
</div>
	<table>
		<tr>
			<td>菜单名称</td>
			<td><input id="menuName" name="menuName" type="text"  value="${menu.menuName }" ltype="text"  validate="{required:true,minlength:3,maxlength:10}"/></td>
			 <td align="left"></td>
		</tr>
		<tr>
			<td>访问路径</td>
			<td><input id="menuAction" name="menuAction" type="text"  value="${menu.menuAction }"   ltype="text"  validate="{required:true,minlength:3,maxlength:100}"/></td>
			 <td align="left"></td>
		</tr>
		<tr>
			<td>显示顺序</td>
			<td><input id="menuOrder" name="menuOrder" type="text"  value="${menu.menuOrder }"  ltype="text"  validate="{required:true,minlength:1,maxlength:2}"/></td>
			 <td align="left"></td>
		</tr>
		
		<tr>
			<td>父菜单</td>
			<td>
				<select id="parentID" name="parentID" >
					<option value="">请选择</option>
				</select>
			</td>
			 <td align="left"></td>
		</tr>
		<tr style="display: none;">
			<td colspan="4" style="text-align: center;"> <input id="submit"  type="submit" value="提交" class="l-button"/></td>
		</tr>
	</table>
</form>

</body>
</html>