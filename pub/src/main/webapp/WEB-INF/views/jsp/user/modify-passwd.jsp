<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
       <%@ include file="/WEB-INF/views/jsp/common.jsp" %>
<!DOCTYPE html>
<html lang="zh-CN">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>Bootstrap 101 Template</title>


    <style type="text/css">
    
    	
    </style>
  </head>
<body> 
<div   class ="container"> 
<jsp:include page="/WEB-INF/views/jsp/nav.jsp"></jsp:include>

	 <div   class ="row"> 
	 <div   class ="col-md-2"> </div>
	 	<div   class ="col-md-8"> 
	 		<div   class ="panel panel-primary"> 
                    <div   class ="panel-heading"> 
                        <h3   class ="panel-title"><strong > 修改密码</strong ></h3 > 
                    </div > 
                    <div   class ="panel-body">  
						<div   class ="alert alert-danger alert-dismissable hidden">   
					      <button   type ="button"   class ="close"   data-dismiss ="alert"   aria-hidden ="true"> &times; </button >   
					      <strong> </strong >
					
					  </div >
					   <form    id ="registerform" method="post" class="form-horizontal">
					   		<input type="hidden" name="token" value="${token}" />  
					   		<input type="hidden" name="id" value="${loginUser.id}" />  
					
					  	  <div class="form-group">
							    <label for="oldPasswd" class="col-sm-3 control-label">原密码</label>
							    <div class="col-sm-5">
							      <input type="password" class="form-control" id="oldPasswd" name="oldpasswd" placeholder="密码" check-type="required" required-message="请输入密码。">
							    </div>
						  </div>
					  	  <div class="form-group">
							    <label for="inputPassword1" class="col-sm-3 control-label">密码</label>
							    <div class="col-sm-5">
							      <input type="password" class="form-control" id="inputPassword1" name="passwd" placeholder="密码" check-type="required passwd" required-message="请输入密码。">
							    </div>
						  </div>
					  	  <div class="form-group">
							    <label for="inputPassword2" class="col-sm-3 control-label">密码确认</label>
							    <div class="col-sm-5">
							      <input type="password" class="form-control" id="inputPassword2" placeholder="密码确认" check-type="required" required-message="请再次确认密码。">
							    </div>
						  </div>
						  
						  
				     <div class="form-group">
					    <div class="col-sm-offset-2 col-sm-10">
					      <button type="button" class="btn btn-primary">确&nbsp;&nbsp;认</button>
					      <button type="button" class="btn btn-default btn-cancle">取&nbsp;&nbsp;消</button>
					    </div>
					  </div>

				       
				    </form >   
				    <c:if test="${param.msg != null}">
				    <div   class ="alert alert-danger alert-dismissable">   
				        <button   type ="button"   class ="close"   data-dismiss ="alert"   aria-hidden ="true"> &times; </button >   
				        <strong><spring:message code="${param.msg}" ></spring:message>  </strong>
				   </div>    
					</c:if>
                    </div >   
                </div >   
	 	</div>
	 </div>
</div>   

	<script type="text/javascript" src="${ctx }/js/bootstrap3-validation.js" charset="UTF-8"></script>
	<script type="text/javascript">
	 $(function(){
		   //1. 简单写法：
		   $("#registerform").validation(ajaxValidateFormfunction);
		   $("#registerform").attr("action", "${ctx}/usrreg/modifyPwd.do");
		   $("#registerform .btn-primary").on('click',function(event){
		     // 2.最后要调用 valid()方法。
		     if ($("#registerform").valid(this,"error!")==false){
		       //$("#error-text").text("error!"); 1.0.4版本已将提示直接内置掉，简化前端。
		       return false;
		     }
		   
		     $("#registerform").submit();
		   }); 
		   $("#registerform .btn-cancle").on('click',function(event){
		     window.location="${ctx}";
		   }); 
		   
   });
	 
	 function ajaxValidateFormfunction(obj,params){
	     if (obj.id=='userName'){
	         $.post("${ctx}/usrreg/validate.do",{userName :$(obj).val()},function(data){
	        	 if(data.code == 0) {
	        		 params.err = false;
	  	           	params.msg = "";
	        	 } else {
	        		 params.err = true;
	  	           	params.msg = data.message;
	        	 }
	           
	          });
	       } else   if (obj.id=='inputPassword2'){
	    	   if($("#inputPassword1").val() != $("#inputPassword2").val()) {
	    		   	params.err = true;
	  	           	params.msg = "两次输入的密码不一致";
			     }
	       }
		 
	 }
</script>
  </body>
</html>
