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

    <!-- Bootstrap -->
    <link href="${ctx }/bootstrap/css/bootstrap.min.css" rel="stylesheet">

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
      <script src="http://cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="http://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
    <style type="text/css">
    
    	
    </style>
  </head>
<body > 
    <div   class ="container "> 

     	<nav class="navbar navbar-default" role="navigation">
		   <div class="navbar-header">
		      <a class="navbar-brand" href="#">课程报名系统</a>
		   </div>
		   <div>
		      <ul class="nav navbar-nav">
		         <li class="active" url="http://www.baidu.com"><a href="#" >报名</a></li>
		         <li url="http://www.baidu.com"><a href="#">注册</a></li>
		         <li class="dropdown">
		            <a href="#" class="dropdown-toggle" data-toggle="dropdown">
		               Java 
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
		   </div>
		</nav>
    </div>   

    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="${ctx }/js/jquery/jquery-1.11.2.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="${ctx }/bootstrap/js/bootstrap.min.js"></script>
    <script type="text/javascript">
    	
    	$(document).ready(function(){
    		$(".navbar li a").click(function(e){
    			window.location.href = $(this).parent().attr("url");
    		});
    		
    
    		});
    </script>
  </body>
</html>
