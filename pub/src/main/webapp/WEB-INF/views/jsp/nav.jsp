<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <%@ include file="/WEB-INF/views/jsp/common.jsp" %>


     	<nav class="navbar navbar-default" role="navigation">
		   <div class="navbar-header">
		      <a class="navbar-brand" href="#">首页</a>
		   </div>
		   <div>
		      <ul class="nav navbar-nav">
		         <li class="active" url="http://www.baidu.com"><a class="clickMenu" href="#" >课程列表</a></li>
		         <li class="dropdown">
		            <a href="#" class="dropdown-toggle" data-toggle="dropdown">
		                测试
		               <b class="caret"></b>
		            </a>
		            <ul class="dropdown-menu">
		               <li><a href="#">jmeter</a></li>
		               <li><a href="#">EJB</a></li>
		               <li><a href="#">Jasper Report</a></li>
		               <li class="divider"></li>
		               <li><a href="#">分离的链接</a></li>
		               <li class="divider"></li>
		               <li><a href="#">另一个分离的链接</a></li>
		            </ul>
		         </li>
		      </ul>
		      <ul class="nav navbar-nav" style="float: right;">
		        <c:if test="${_login_user == null }">
		         <li class="dropdown">
		            <a href="#" class="dropdown-toggle" data-toggle="dropdown">
		               登录
		               <b class="caret"></b>
		            </a>
		            <ul class="dropdown-menu" style="left: -100px; width: 300px; padding: 0;">
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
									      <input type="text" class="form-control" id="pwd"  name="passwd"
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
		          <li class="" url="${ctx }/login/ajaxLogout.do"><a class="logout"  href="#" >注销</a></li>
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

