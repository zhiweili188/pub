<%@ page contentType="text/html; charset=UTF-8"%>
   <%@ include file="/WEB-INF/views/jsp/common.jsp" %>
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
var lastTabs = new Array(); 
var currentTabTitle = "Home";
$(document).ready(function () {
	 $(".easyui-accordion li").click(function(){
		 if($(this).hasClass("clicked")) {
			 
		 } else {
			 $(".easyui-accordion .clicked").removeClass("clicked");
			 $(this).addClass("clicked");
			 addTab($(this).attr("title"),$(this).attr("href"),null);
	
		 }
	 });
	 
	 $('.easyui-tabs').tabs({
		 onSelect: function(title) { 
			//移除 tt 
			lastTabs = $.grep(lastTabs, function(n, i) { return n != title; }); 
			//重新压入，保证 最新的在最上面 
			lastTabs.push(title); 
			//更新当前 
			currentTabTitle = title; 
			var clickedMenu =  $(".easyui-accordion .clicked");
			 if(clickedMenu) {
				 clickedMenu.removeClass("clicked");
				 $(".easyui-accordion li[title='"+title+"']").addClass("clicked");
				 
				 //展开tab所属的一级菜单
				var menuGroupTitle = $(".easyui-accordion li[title='"+title+"']").parent().parent().attr("atitle");
				  if(clickedMenu.parent().parent().attr("atitle") !=menuGroupTitle  ) {
					  $("#aa").accordion('select', menuGroupTitle);
				 }
				 
			 }
			 
			 
			}, 
		 onClose:function(title){   
			 var tt = $('.easyui-tabs');
			//移除 tt 
			 lastTabs = $.grep(lastTabs, function(n, i) { return n != title; }); 
			 //重新选择 
			 tt.tabs('select', lastTabs[lastTabs.length - 1]); 
			 
			 
			 var clickedMenu =  $(".easyui-accordion .clicked");
			 if(clickedMenu) {
				 if(clickedMenu.attr("title") == title) {
					 clickedMenu.removeClass("clicked");
				 }
			 }
	      }   
	  });
});

function removePanel(){
	 var tt = $('.easyui-tabs');
    var tab = tt.tabs('getSelected');
    if(tab){
        var index = tt.tabs('getTabIndex',tab);
        alert(index);
        tt.tabs('close',index);
    }
}

function addTab(title, href,icon){
	var tt = $('.easyui-tabs');
	if (tt.tabs('exists', title)){//如果tab已经存在,则选中并刷新该tab    	
        tt.tabs('select', title);
        refreshTab({tabTitle:title,url:href});
	} else {
		var content = '未实现';
    	if (href){
	    	 content = '<iframe scrolling="no" frameborder="0"  src="'+href+'" style="width:100%;height:100%;"></iframe>';
    	} else {
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
<body class="easyui-layout" data-options="fit:true">
    <div region="north" border="true" split="true" style="overflow: hidden; height: 80px;">
        <div class="top-bg"></div>
    </div>
    <div region="south" border="true" split="true" style="overflow: hidden; height: 40px;">
        <div class="footer">版权所有：<a href="#">XXXXXX</a></div>
    </div>
    <div region="west" split="true" title="导航菜单" style="width: 200px;">
    	<div id="aa" class="easyui-accordion" >
    		<div title="开发者"  atitle="博文管理" iconcls="icon-save" style="overflow: auto; padding: 10px; border: 1px solid #000;">
    			<ul style="list-style: none; margin: 0; width: 100%; padding: 0; text-align: center; vertical-align: middle;">
    				<li href="/auth/menu/index.do"  title="菜单管理">菜单管理
    			</ul>
    		</div>
    		<!-- <div title="博文管理"  atitle="博文管理" iconcls="icon-save" style="overflow: auto; padding: 10px; border: 1px solid #000;">
    			<ul style="list-style: none; margin: 0; width: 100%; padding: 0; text-align: center; vertical-align: middle;">
    				<li href="/auth/user/index.do"  title="用户管理">用户管理
    				<li href="/auth/menu/index.do"  title="菜单管理">菜单管理
    				<li href="/auth/role/index.do"  title="角色管理">角色管理
    			</ul>
    		</div> -->

            <c:forEach items="${menuList}" var="m">
            	<div title="${m.menuName }"  atitle="博文管理" iconcls="icon-save" style="overflow: auto; padding: 10px; border: 1px solid #000;">
    			<ul style="list-style: none; margin: 0; width: 100%; padding: 0; text-align: center; vertical-align: middle;">
    				<c:forEach items="${m.children}" var="child">
    					<li href="${child.menuAction }"  title="${child.menuName }">${child.menuName }
    				</c:forEach>
    			</ul>
    		</div>
            </c:forEach>
    	</div>
    </div>
    <div id="mainPanle" region="center" style="overflow: hidden;">
    	<div class="easyui-tabs" data-options="tabWidth:112,tools:'#tab-tools', fit:true" style="width:100%;height:100%;">
			<div title="Home" style="padding:10px" style="width:100%;height:100%;">
				<p>References Content.</p>
			</div>
			
		</div>
    </div>
</body>
</html>
