<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@  page isELIgnored="false"%> 
<%@ include file="/WEB-INF/views/jsp/common.jsp"%>
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
        var eee;
        $(function () {
            $.metadata.setType("attr", "validate");
            var v = $("form").validate({
                //调试状态，不会提交数据的
                debug: true,
                errorLabelContainer: "#errorLabelContainer", wrapper: "li",
                invalidHandler: function (form, validator) {
                    $("#errorLabelContainer").show();
                    setTimeout(function () {
                        $.ligerDialog.tip({ content: $("#errorLabelContainer").html() });
                    }, 10);
                },
                submitHandler: function () {
                    $("#errorLabelContainer").hide();
                    alert("Submitted!");
                }
            });
            $("form").ligerForm();
            $(".l-button-test").click(function () {
                alert(v.element($("#txtName")));
            });
        });  
    </script>
    <style type="text/css">
           body{ font-size:12px;}
        .l-table-edit {}
        .l-table-edit-td{ padding:4px;}
        .l-button-submit,.l-button-test{width:80px; float:left; margin-left:10px; padding-bottom:2px;}
        .l-verify-tip{ left:230px; top:120px;}
        #errorLabelContainer{ padding:10px; width:300px; border:1px solid #FF4466; display:none; background:#FFEEEE; color:Red;}
    </style>

</head>
<body style="padding:6px; overflow:hidden;">

    <form name="form1" method="post"  id="form1" action="login.do">
<div id="errorLabelContainer">
</div>
        <table cellpadding="0" cellspacing="0" class="l-table-edit" >
            <tr>
                <td align="right" class="l-table-edit-td">用户名:</td>
                <td align="left" class="l-table-edit-td" style="width:160px"><input name="txtName" type="text" id="txtName" ltype="text" validate="{required:true,minlength:3,maxlength:10}" /></td>
                <td align="left"></td>
            </tr>
           
             <tr>
                <td align="right" class="l-table-edit-td">密码:</td>
                <td align="left" class="l-table-edit-td" style="width:160px"><input name="txtEmail" type="text" id="txtEmail" ltype="text" validate="{required:true,email:true}" /></td>
                <td align="left"></td>
            </tr>
            <tr>
          
        </table>
 <br />
<input type="submit" value="登录" id="Button1" class="l-button l-button-submit" /> 
    </form>

    
</body>

</html>