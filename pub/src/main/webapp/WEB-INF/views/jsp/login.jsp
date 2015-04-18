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
		*{margin:0;padding: 0;}
      .loginBox{width:420px;height:230px;padding:0 20px;border:1px solid #fff; color:#000; margin-top:40px; border-radius:8px;background: white;box-shadow:0 0 15px #222; background: -moz-linear-gradient(top, #fff, #efefef 8%);background: -webkit-gradient(linear, 0 0, 0 100%, from(#f6f6f6), to(#f4f4f4));font:11px/1.5em 'Microsoft YaHei' ;position: absolute;left:50%;top:50%;margin-left:-210px;margin-top:-115px;}
      .loginBox h2{height:45px;font-size:20px;font-weight:normal;}
      .loginBox .left{border-right:1px solid #ccc;height:100%;padding-right: 20px; }
	</style>
  </head>
  <body>
    <div class="container"  style="">
		<div class="row">
        <div class="loginpanel">
			<i id="loading" class="hidden fa fa-spinner fa-spin bigicon"></i>
            <h2>
				<span class="fa fa-quote-left "></span> 登录 <span class="fa fa-quote-right "></span>
			</h2>
            <div>
                <input id="username" type="text" placeholder="登录账号" onkeypress="check_values();">
                <input id="password" type="password" placeholder="输入密码" onkeypress="check_values();">

				<div class="buttonwrapper">
					<button id="loginbtn" class="btn btn-warning loginbutton">
						<span class="fa fa-check"></span>
					</button>
					<span id="lockbtn" class="fa fa-lock lockbutton redborder"></span>
				</div>
            </div>
        </div>
    </div>
    
    </div> <!-- /container -->
  </body>
</html>

