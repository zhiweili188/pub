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
<body > 
    <div   class ="container "> 
		<jsp:include page="../nav.jsp"></jsp:include>
     	
		<div class="panel panel-default">
		   <div class="panel-heading">
		      <h3 class="panel-title">
		         课程详情
		      </h3>
		   </div>
		   <div class="panel-body">
       
       <table class="table table-bordered">
		   <tbody>
		    	<tr>
		    		<th width="30%">课程类别</th>
		    		<td width="70%">${course.courseTypeName }</td>
		    	</tr>
		    	<tr>
		    		<th>课程形式</th>
		    		<td>${course.courseMethod }</td>
		    	</tr>
		    	<tr>
		    		<th>课程名称</th>
		    		<td>${course.courseName }</td>
		    	</tr>
		    	<tr>
		    		<th>课程地点</th>
		    		<td>${course.courseAddress }</td>
		    	</tr>
		    	<tr>
		    		<th>联系人</th>
		    		<td>${course.contactPerson }</td>
		    	</tr>
		    	<tr>
		    		<th>联系电话</th>
		    		<td>${course.contactTel }</td>
		    	</tr>
		    	<tr>
		    		<th>主办单位</th>
		    		<td>${course.hostEntity }</td>
		    	</tr>
		    	<tr>
		    		<th>报名开始时间</th>
		    		<td>${course.applyStartTime }</td>
		    	</tr>
		    	<tr>
		    		<th>报名截止时间</th>
		    		<td>${course.applyEndTime }</td>
		    	</tr>
		    	<tr>
		    		<th>名额</th>
		    		<td>${course.applyQuota }</td>
		    	</tr>
		    	<tr>
		    		<th>剩余名额</th>
		    		<td>${course.remainApplyQuota }</td>
		    	</tr>
		     <tr>
		     	<td></td>
		     	<td>
		     		<button type="button" class="btn btn-primary btn-default btn-submit" refid="${course.tid }">报名</button>
		     	</td>
		     </tr>
		   </tbody>
		</table>
		   </div>
		</div>
    </div>   


    <script type="text/javascript">
    	
    	$(document).ready(function(){
    		$(".btn-submit").click(function(){
    			var refid = $(this).attr("refid");
    			window.location.href ="${ctx}/crs/apply/"+refid+".do";
    		});
       });
    </script>
  </body>
</html>
