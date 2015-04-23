<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <%@ include file="/WEB-INF/views/jsp/common.jsp" %>


     	<nav class="navbar navbar-default" role="navigation">
		   <div class="navbar-header" url="${ctx }/toIndex.do">
		      <a class="navbar-brand clickMenu" href="#">首页</a>
		   </div>
		   <div>
		      <ul class="nav navbar-nav navbar-left">
		         <li class="" url="${ctx }/crs/courselist.do"><a class="clickMenu" href="#" >课程报名</a></li>
		         
		      </ul>
		      <ul class="nav navbar-nav navbar-right" style="margin-right: 0;">
		        <c:if test="${_login_user == null }">
		         <li class="dropdown">
		            <a href="#" class="dropdown-toggle" data-toggle="dropdown">
		               登录
		               <b class="caret"></b>
		            </a>
		            <ul class="dropdown-menu" style=" width: 300px; padding: 0;">
		               <li>
		               		<div class="row" style="margin: 0; padding: 0;">
						    	<div class="col-sm-12 well"  style="margin: 0; ">
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
									</form>
									 <div id="alertDiv"  class ="alert alert-danger alert-dismissable hide">   
								        <button   type ="button"   class ="close"   data-dismiss ="alert"   aria-hidden ="true"> &times; </button >   
								        <strong> 错误! </strong><span id="messageSpan"></span> 
								   </div>   
						    	</div>
						    </div>
		               </li>
		              
		            </ul>
		         </li>
		          <li url="${ctx }/usrreg/start-register.do"><a class="clickMenu"  href="#">注册</a></li>
		           </c:if>
		          <c:if test="${_login_user != null }">
		          
		          <li class="dropdown">
		            <a href="#" class="dropdown-toggle" data-toggle="dropdown">
		                ${_login_user.userName }
		               <b class="caret"></b>
		            </a>
		            <ul class="dropdown-menu">
		               <li url="${ctx }/usrreg/registerDetail.do"><a class="clickMenu"  href="#">个人资料</a></li>
		                <li class="divider"></li>
		               <li url="${ctx }/crs/myCourselist.do"><a class="clickMenu"  href="#">我的课程</a></li>
		               <li class="divider"></li>
		               <li url="${ctx }/usrreg/toModifyPwd.do"><a class="clickMenu"  href="#">修改密码</a></li>
		               <li class="divider"></li>
		               <li class="" url="${ctx }/login/ajaxLogout.do"><a class="logout"  href="#" >注销</a></li>
		            </ul>
		         </li>
		          </c:if>
		      </ul>
		   </div>
		</nav>
<script type="text/javascript" src="${ctx }/js/bootstrap3-validation.js" charset="UTF-8"></script>
  <script type="text/javascript" src="${ctx }/js/jquery.serializeJson.js" charset="UTF-8"></script>
  <script src="${ctx }/js/login.js"></script>
    <script type="text/javascript">
    	
    	$(document).ready(function(){
    		$(".navbar .clickMenu").click(function(e){
    		
    			window.location.href = $(this).parent().attr("url");
    			
    		});
    		$(".navbar .logout").click(function(e){
    		
    			 logout();
    			
    		});
    		
    		$(".navbar .dropdown-toggle").dropdown();
    		
    
    		});
    </script>

