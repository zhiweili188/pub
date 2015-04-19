<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@  page isELIgnored="false"%> 
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
 <%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
 <c:set var="ctx" value="${pageContext.request.contextPath}" ></c:set>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>课程报名系统</title>

 <!-- Bootstrap -->
    <link href="${ctx }/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="${ctx }/datetimepicker/bootstrap-datetimepicker.min.css" rel="stylesheet" media="screen">

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
      <script src="http://cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="http://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
    
   <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="${ctx }/js/jquery/jquery-1.11.2.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="${ctx }/bootstrap/js/bootstrap.min.js"></script>
</head>
<body>
<input type="hidden" id="ctx" value="${ctx }">
</body>
</html>