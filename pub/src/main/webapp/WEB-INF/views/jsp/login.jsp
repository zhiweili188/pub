<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
       <%@ include file="/WEB-INF/views/jsp/common.jsp" %>
<!DOCTYPE html>
<html lang="zh-CN">
  <head>
    <meta charset="utf-8">
    <title>用户登录</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
    <!--[if lt IE 9]>
      <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->
	
	<style type="text/css">
	
	</style>
  </head>
  <body style="background: url(${ctx}/images/login_background.jpg)">
    <div class="container"  >
    <div class="row" style="height: 200px;">
    	<div class="col-sm-3">
    		
    	</div>
    </div>
    <div class="row">
    	<div class="col-sm-3" style="padding: 0;">
    	</div>
    	<div class="col-sm-6" style="padding: 0;">
	    	<div class="row panel" style="padding: 0; margin: 0;">
		    	<div class="col-sm-12" style="padding: 0; margin: 0;">
		    	<div class="panel panel-default" style="padding: 0; margin: 0;">
				   <div class="panel-body"  style="background-color: transparent;">
				    <h2 style="text-align: center; ">课程报名系统</h2>
				     <div class="row">
				    	<div class="col-sm-12 well" >
				    		<form id="loginForm"  role="form" >
							   <div class="form-group" >
							      <label for="name">用户名</label>
							      <input type="text" class="form-control" id="name"  name="userName"
							         placeholder="请输入用户名" check-type="required" required-message="请输入用户名。">
							   </div>
							   <div class="form-group">
							      <label for="pwd">密码</label>
							      <input type="password" class="form-control" id="pwd"  name="passwd"
							         placeholder="请输入密码" check-type="required" required-message="请输入密码。">
							   </div>
							   <div class="form-group">
							      <button type="button" class="btn btn-primary btn-lg btn-block">登录</button>
							  </div>
							   <div class="form-group" style="text-align: right;">
							      <a href="${ctx }/usrreg/start-register.do" style="color: red; text-decoration: underline;">没有用户，先去注册</a>
							  </div>
							</form>
							 <div id="alertDiv"  class ="alert alert-danger alert-dismissable hide">   
						        <button   type ="button"   class ="close"   data-dismiss ="alert"   aria-hidden ="true"> &times; </button >   
						        <strong> 错误! </strong><span id="messageSpan"></span> 
						   </div>   
				    	</div>
				    </div>
				   </div>
				</div>
		    		
		    	</div>
		    </div>
    	
    	
    	</div>
    	<div class="col-sm-3" style="padding: 0;">
    	</div>
    </div>
    	
		
    </div> <!-- /container -->
  </body>
  <script type="text/javascript" src="${ctx }/js/bootstrap3-validation.js" charset="UTF-8"></script>
  <script type="text/javascript" src="${ctx }/js/jquery.serializeJson.js" charset="UTF-8"></script>
  <script type="text/javascript">
  $(document).ready(function(){
	  $("#loginForm").validation();
	   $("#loginForm").attr("action", "${ctx}/login/ajaxLogin.do");
	   $("button[type='button']").on('click',function(event){
	     // 2.最后要调用 valid()方法。
	     if ($("#loginForm").valid(this,"error!")==false){
	       //$("#error-text").text("error!"); 1.0.4版本已将提示直接内置掉，简化前端。
	       return false;
	     }
	    // $("form").submit();
	    submitForm();
	   }); 
	 
  });
  
	function submitForm(){
		$.ajax({
		    type: 'post',
		    url: '${ctx}/login/ajaxLogin.do',
		    data: $("#loginForm").serializeJson(),
		    dataType: "json", 
		    success: function(data) {
		    	
		    	  if(data.code == 0) {
		    		 window.location="${ctx}"+data.returnToUrl;
			       } else {
			    	   $("#messageSpan").html(data.code+":"+data.message);
			    	   $("#alertDiv").removeClass("hide");
			       }
		    }
		});
	}

  </script>
</html>

