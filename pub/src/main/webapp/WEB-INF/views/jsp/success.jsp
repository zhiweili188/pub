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
<jsp:include page="nav.jsp"></jsp:include>
	<div id="myAlert" class="alert alert-success">
	   <a href="#" class="close" data-dismiss="alert">&times;</a>
	   <c:if test="${redirectUrl != null }">
	   <strong>操作成功！<a href="${ctx }${redirectUrl }">点击这里返回</a></strong>
	   </c:if>
	   <c:if test="${redirectUrl == null }">
	   <spring:message code="${msg}"></spring:message>
	    </c:if>
	</div>
</div>   

  </body>
</html>
