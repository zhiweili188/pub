<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<meta http-equiv="content-type" content="text/html; charset=UTF-8" />
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<title></title>
	    <script src="/auth/js/easyui/jquery.min.js" type="text/javascript"></script>
		<script src="/auth/js/easyui/jquery.easyui.min.js" type="text/javascript"></script>
		<link href="/auth/js/easyui/themes/default/easyui.css" rel="stylesheet" type="text/css" />
		<link href="/auth/js/easyui/themes/icon.css" rel="stylesheet" type="text/css" />
<script type="text/javascript">
$(document).ready(function () {
	 $(".easyui-accordion li").click(function(){
		 if($(this).hasClass("clicked")) {
			 
		 } else {
			 $(".easyui-accordion .clicked").removeClass("clicked");
			 $(this).addClass("clicked");
			 addTab($(this).text(),$(this).attr("href"),null);
	
		 }
	 });
});

function addTab(title, href,icon){
	var tt = $('.easyui-tabs');
	if (tt.tabs('exists', title)){//如果tab已经存在,则选中并刷新该tab    	
        tt.tabs('select', title);
        refreshTab({tabTitle:title,url:href});
	} else {
    	if (href){
	    	var content = '<iframe scrolling="no" frameborder="0"  src="'+href+'" style="width:100%;height:100%;"></iframe>';
    	} else {
	    	var content = '未实现';
    	}
    	tt.tabs('add',{
	    	title:title,
	    	closable:true,
	    	content:content,
	    	iconCls:icon||'icon-default'
    	});
	}
}
/**    
 * 刷新tab
 * @cfg 
 *example: {tabTitle:'tabTitle',url:'refreshUrl'}
 *如果tabTitle为空，则默认刷新当前选中的tab
 *如果url为空，则默认以原来的url进行reload
 */
function refreshTab(cfg){
	var refresh_tab = cfg.tabTitle?$('.easyui-tabs').tabs('getTab',cfg.tabTitle):$('.easyui-tabs').tabs('getSelected');
	if(refresh_tab && refresh_tab.find('iframe').length > 0){
	var _refresh_ifram = refresh_tab.find('iframe')[0];
	var refresh_url = cfg.url?cfg.url:_refresh_ifram.src;
	//_refresh_ifram.src = refresh_url;
	_refresh_ifram.contentWindow.location.href=refresh_url;
	}
}
</script> 
<style type="text/css"> 
.easyui-accordion li {
width: 100%; height: 30px;
line-height: 30px;
}
.clicked{
 background: #ececec;
 border: 1px solid #eeccdd;
}
.footer {
        width: 100%;
        text-align: center;
        line-height: 35px;
    }

.top-bg {
    background-color: #d8e4fe;
    height: 80px;
}
 </style>
</head>
<body class="easyui-layout">
    <div region="north" border="true" split="true" style="overflow: hidden; height: 80px;">
        <div class="top-bg"></div>
    </div>
    <div region="south" border="true" split="true" style="overflow: hidden; height: 40px;">
        <div class="footer">版权所有：<a href="#">XXXXXX</a></div>
    </div>
    <div region="west" split="true" title="导航菜单" style="width: 200px;">
    	<div id="aa" class="easyui-accordion" style="position: absolute; top: 27px; left: 0px; right: 0px; bottom: 0px;">
    		<div title="博文管理" iconcls="icon-save" style="overflow: auto; padding: 10px; border: 1px solid #000;">
    			<ul style="list-style: none; margin: 0; width: 100%; padding: 0; text-align: center; vertical-align: middle;">
    				<li href="http://www.baidu.com" >第一个
    				<li href="http://www.126.com">第二个
    				<li href="http://www.sina.com">第三个
    			</ul>
    		</div>
    		 <div title="新闻管理" iconcls="icon-reload" selected="true" style="padding: 10px;">
                content2 
            </div>
            <div title="资源管理">
                content3 
            </div>
    	</div>
    </div>
    <div id="mainPanle" region="center" style="overflow: hidden;">
    	<div class="easyui-tabs" data-options="tabWidth:112,tools:'#tab-tools'" style="width:100%;height:100%;">
			<div title="Home" style="padding:10px" style="width:100%;height:100%;">
				<p>References Content.</p>
			</div>
			<div title="Maps" style="padding:10px">
				<iframe src="http://www.baidu.com" width="100%" height="100%"></iframe>
			</div>
			<div title="Journal" >
				<iframe scrolling="yes" frameborder="0"  src="http://www.baidu.com" width="100%" height="100%"></iframe>
			</div>
			<div title="Iframe" data-options="closable:true" style="overflow:hidden">
				<iframe scrolling="yes" frameborder="0"  src="http://www.jeasyui.com/forum/index.php" style="width:100%;height:100%;"></iframe>
			</div>
		</div>
    </div>
</body>
</html>
