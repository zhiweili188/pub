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
                        <h3   class ="panel-title"><strong > 用户信息</strong ></h3 > 
                    </div > 
                    <div   class ="panel-body">  
						<div   class ="alert alert-danger alert-dismissable hidden">   
					      <button   type ="button"   class ="close"   data-dismiss ="alert"   aria-hidden ="true"> &times; </button >   
					      <strong> </strong >
					
					  </div >
					   <form    id ="registerform" method="post" class="form-horizontal">
					   		<input type="hidden" name="token" value="${token}" />  
					   		<input type="hidden" name="id" value="${userInfo.id}" />  
						  
						  <div class="form-group">
							    <label for="fullName" class="col-sm-3 control-label">姓名</label>
							     <div class="col-sm-5">
							    	<input type="text" class="form-control "  id="fullName" name="fullName" placeholder="张三" check-type="required" required-message="请输入真实姓名。">
							    </div>
						  	</div> 
						  	
						  	<div class="form-group">
						  		 <label class="col-sm-3 control-label">性别</label>
							  <label  class="radio-inline ">
							    <input type="radio" name="sex" id="sex1" value="0" checked>
							    男
							  </label>

							  <label  class="radio-inline">
							    <input type="radio" name="sex" id="sex2" value="1">
							    女
							  </label>
							</div>
						  	
						  	<div class="form-group">
							    <label for="exampleInputName2" class="col-sm-3 control-label">出生年月</label>
							      <div class="col-sm-5">
								     <div class="input-group date form_date" data-date="2014-03-20" data-date-format="yyyy-mm-dd" data-link-field="birthday" data-link-format="yyyy-mm-dd">
					                    <input class="form-control"  id="dateInput" size="16" type="text" value="" readonly>
										<span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
					                </div>
									<input type="hidden" id="birthday" name="birthday" value=""  check-type="required" required-message="请选择出生年月。"/>
				                </div>
						  	</div> 
						  	
							<div class="form-group">
							    <label for="company" class="col-sm-3 control-label">所在单位</label>
							    <div class="col-sm-5">
							      <input type="text" class="form-control" id="company" name="company" placeholder="所在单位" check-type="required" required-message="请输入工作单位。">
							    </div>
						  </div>
							<div class="form-group">
							    <label for="department" class="col-sm-3 control-label">所在部门/科室</label>
							    <div class="col-sm-5">
							      <input type="text" class="form-control" id="department"  name="department" placeholder="所在部门/科室" check-type="required" required-message="请输入所在部门或科室。">
							    </div>
						  </div>
							<div class="form-group">
							    <label for="educationBackground" class="col-sm-3 control-label">学历</label>
							    <div class="col-sm-4">
							      <select class="form-control" id="educationBackground" name="educationBackground" check-type="required" required-message="请选择学历。">
								  <option>1</option>
								  <option>2</option>
								  <option>3</option>
								  <option>4</option>
								  <option>5</option>
								</select>
							    </div>
						  </div>
						<div   class ="form-group">   
				            <label   for ="IDCard" class="col-sm-3 control-label"> 身份证号码 </label >   
				            <div class="col-sm-4">
				                <input   type ="text"   class ="form-control"   id ="IDCard"   name ="idCardNo"   placeholder ="身份证号码"   check-type="required isCardNo" required-message="请输入身份证号码。"  isCardNo-message="身份证输入不合法。">   
				            </div >   
				        </div >   
							<div class="form-group">
							    <label for="mobile" class="col-sm-3 control-label">手机</label>
							    <div class="col-sm-4">
							      <input type="email" class="form-control" id="mobile" name="mobile" placeholder="手机" check-type="required mobile" required-message="请输入手机号码。" mobile-message="格式不正确。">
							    </div>
						  </div>
							<div class="form-group">
							    <label for="email" class="col-sm-3 control-label">Email</label>
							    <div class="col-sm-4">
							      <input type="email" class="form-control" id="email" name="email" placeholder="Email" check-type="required mail" required-message="请输入电子邮箱。"  mail-message="邮箱格式不正确。">
							    </div>
						  </div>
							<div class="form-group">
							    <label for="type" class="col-sm-3 control-label">类别</label>
							     <div class="col-sm-4">
							      <select class="form-control" id="type" name="type" check-type="required" required-message="请选择类别。">
								
								</select>
							    </div>
						  </div>
						<div class="form-group">
							    <label for="title" class="col-sm-3 control-label">职称</label>
							     <div class="col-sm-4">
							      <select class="form-control" id="title" name="title" check-type="required" required-message="请选择职称。">
								
								</select>
							    </div>
						 </div>
						<div class="form-group">
						  		 <label class="col-sm-3 control-label">身份</label>
							  <label  class="radio-inline ">
							    <input type="radio" name="identityRadios" id="identity1" value="0" checked>
							   本院职工
							  </label>

							  <label  class="radio-inline">
							    <input type="radio" name="identityRadios" id="identity2" value="1">
							    院外人员
							  </label>
						</div>
				      <div class="form-group" style="display: none;" id="identity-div">
				      			<label class="col-sm-3 control-label"></label>
							     <div class="col-sm-4">
							      <select class="form-control" id="identity" name="identity" >
								
								</select>
							    </div>
						  </div>
				     <div class="form-group">
					    <div class="col-sm-offset-2 col-sm-10">
					      <button type="button" class="btn btn-primary">确&nbsp;&nbsp;认</button>
					      <button type="button" class="btn btn-default btn-cancle">取&nbsp;&nbsp;消</button>
					    </div>
					  </div>

				       
				    </form >   
				    <div   class ="alert alert-danger alert-dismissable hidden">   
				        <button   type ="button"   class ="close"   data-dismiss ="alert"   aria-hidden ="true"> &times; </button >   
				        <strong></strong> 
				   </div>    

                    </div >   
                </div >   
	 	</div>
	 </div>
</div>   

 
    <script type="text/javascript" src="${ctx }/datetimepicker/bootstrap-datetimepicker.min.js" charset="UTF-8"></script>
	<script type="text/javascript" src="${ctx }/datetimepicker/bootstrap-datetimepicker.zh-CN.js" charset="UTF-8"></script>
	<script type="text/javascript" src="${ctx }/js/bootstrap3-validation.js" charset="UTF-8"></script>
	<script type="text/javascript">
	var init = true;
	$('.form_date').datetimepicker({
        language:  'zh-CN',
        weekStart: 1,
        todayBtn:  1,
		autoclose: 1,
		todayHighlight: 1,
		startView: 2,
		minView: 2,
		forceParse: 0
    });
	 $(function(){
		   //1. 简单写法：
		   $("#registerform").validation(ajaxValidateFormfunction);
		   $("#registerform").attr("action", "${ctx}/usrreg/modify.do");
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
		   
		   //设置学历下拉框的内容
		   $.ajax({
	            type: "get",//使用get方法访问后台
	            dataType: "json",//返回json格式的数据
	            url: "${ctx}/edu/json.do",//要访问的后台地址
	            data: null,//要发送的数据
	            complete :function(){},//AJAX请求完成时隐藏loading提示
	            success: function(data){//msg为返回的数据，在这里做数据绑定
	            	var html="<option value=''>请选择</option>";
                          $.each(data, function(index, json) {
                            	html += "<option value='" + json.key + "'>" + json.value + "</option>";
                         });
                          $("#educationBackground").html(html);
                          $("#educationBackground").val("${userInfo.educationBackground}");
	            }
	       });
		   //设置类别下拉框的内容
		   $.ajax({
	            type: "get",//使用get方法访问后台
	            dataType: "json",//返回json格式的数据
	            url: "${ctx}/utt/json.do",//要访问的后台地址
	            data: null,//要发送的数据
	            complete :function(){},//AJAX请求完成时隐藏loading提示
	            success: function(data){//msg为返回的数据，在这里做数据绑定
	            	var html="<option value=''>请选择</option>";
                          $.each(data, function(index, json) {
                            	html += "<option value='" + json.key + "'>" + json.value + "</option>";
                         });
                          $("#type").html(html);
                          $("#type").val("${userInfo.type}");
                          $("#type").change();
	            }
	       });
		   
		   //改变类别的时候，要改变职称和身份下拉框的内容
		   $("#type").change(function(){
			   var selectValue = $("#type").val();
			   var param = "type="+selectValue;
			   $.ajax({
		            type: "get",//使用get方法访问后台
		            dataType: "json",//返回json格式的数据
		            url: "${ctx}/ut/json.do",//要访问的后台地址
		            data: param,//要发送的数据
		            complete :function(){},//AJAX请求完成时隐藏loading提示
		            success: function(data){//msg为返回的数据，在这里做数据绑定
		            	var html="<option value=''>请选择</option>";
	                          $.each(data, function(index, json) {
	                            	html += "<option value='" + json.key + "'>" + json.value + "</option>";
	                         });
	                          $("#title").html(html);
	                          if(init) {
	                        	  init = false;
	                        	  $("#title").val("${userInfo.title}");
	                          }
		            }
		       });
		
				   setIdentityOption();
			   
			   
		   });
		   
		   //身份单选框点击事件
		   $("input[name='identityRadios']").click(function(){
			   if($(this).val() == 0){
				   var html="<option value=''>请选择</option>";
				   $("#identity").html(html);
				   $("#identity-div").css("display","none");
			   } else {
				   
			   var selectValue = $("#type").val();
			   if(selectValue == "-1") {
				   var html="<option value=''>请选择</option>";
				   $("#identity").html(html);
				   $("#identity-div").css("display","");
				   return true;
			   }
			   var param = "type="+selectValue;
			   $.ajax({
		            type: "get",//使用get方法访问后台
		            dataType: "json",//返回json格式的数据
		            url: "${ctx}/ui/json.do",//要访问的后台地址
		            data: param,//要发送的数据
		            complete :function(){},//AJAX请求完成时隐藏loading提示
		            success: function(data){//msg为返回的数据，在这里做数据绑定
		            	var html="<option value=''>请选择</option>";
	                          $.each(data, function(index, json) {
	                            	html += "<option value='" + json.key + "'>" + json.value + "</option>";
	                         });
	                          $("#identity").html(html);
	                         if(init){
	                        	 init = false;
	                        	 $("#identity").val("${userInfo.identity}");
	                         }
		            }
		       });
			   
			   $("#identity-div").css("display","");
			   }
		   });
		   
		   $("#fullName").val("${userInfo.fullName}");
		   $("#birthday").val("${userInfo.birthday}");
		   $("#dateInput").val("${userInfo.birthday}");
		   $('.form_date').datetimepicker('update');
		   
		   $("#company").val("${userInfo.company}");
		   $("#department").val("${userInfo.department}");
		  
		   $("#IDCard").val("${userInfo.idCardNo}");
		   $("#mobile").val("${userInfo.mobile}");
		   $("#email").val("${userInfo.email}");
		   $("input[name='sex'][value='${userInfo.sex}']").attr("checked","checked");
		   if("${userInfo.identity}" != "0") {
		  	 //$("input[name='identityRadios'][value='1']").attr("checked","checked");
		  	$("input[name='identityRadios'][value='1']").click();
		   }
		  
   });
	 
	 function setIdentityOption() {
		 if($("input[name='identityRadios']:checked").val() == 0){
			   var html="<option value=''>请选择</option>";
			   $("#identity").html(html);
			   $("#identity-div").css("display","none");
		   } else {
			   var selectValue = $("#type").val();
			   if(selectValue == "-1") {
				   var html="<option value=''>请选择</option>";
				   $("#identity").html(html);
				   $("#identity-div").css("display","");
				   return true;
			   }
			   var param = "type="+selectValue;
			   $.ajax({
		            type: "get",//使用get方法访问后台
		            dataType: "json",//返回json格式的数据
		            url: "${ctx}/ui/json.do",//要访问的后台地址
		            data: param,//要发送的数据
		            complete :function(){},//AJAX请求完成时隐藏loading提示
		            success: function(data){//msg为返回的数据，在这里做数据绑定
		            	var html="<option value=''>请选择</option>";
	                          $.each(data, function(index, json) {
	                            	html += "<option value='" + json.key + "'>" + json.value + "</option>";
	                         });
	                          $("#identity").html(html);
	                          if(init){
	                        	  init = false;
	                        	  $("#identity").val("${userInfo.identity}");
	                          }
		            }
		       });
			   
			   $("#identity-div").css("display","");
			   }
	 }
	 
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
