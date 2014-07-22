<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8" />
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <link rel="stylesheet" type="text/css"  href="${ctx}/skins/${skin}/menu.css"/>
    <script type="text/javascript" src="${ctx}/js/jquery.min.js"></script>
    <script type="text/javascript" src="${ctx}/js/reach/jquery.reach.js"></script>
    <script type="text/javascript" src="${ctx}/js/menu.js"></script>
  </head>
  <body>
    <div class="menu_root"></div>
    
    <script type="text/javascript">
      $.onLoad(function() {
	  initMenu({
	      divE : ".menu_root",
	      data : [
	      {
		      parent:"",
		      id: "",
		      text:""
		  }
		  
		  // 课表和计划任务管理
		  <c:if test="${auths.menu_lessonList != null || auths.menu_schedule != null}">
		  ,
		  {
		      parent	: "root",
		      id	: "menu_curriculumPlanManager",
		      text	: "<fmt:message key="menu_curriculumManager" />"
		      //text	: "<fmt:message key="menu_curriculumPlanManager" />"
		  }
		  
		  </c:if>
		  
		  <c:if test="${auths.menu_lessonList != null}">
		  ,
		  {
		      parent	: "menu_curriculumPlanManager",
		      id	: "menu_curriculumManager",
		      icon	: "${ctx}/skins/${skin}/images/menu_curriculumManager.png",
		      text	: "<fmt:message key="menu_curriculumManager" />",
		      initName	: "curriculumManager",
		      action	: "${ctx}/backstage/Lesson.action"
		  }
		</c:if>
		  
		  <c:if test="${auths.menu_classRoom != null}">
		  ,
		  {
		      parent	: "menu_curriculumPlanManager",
		      id	: "menu_curriculumManager",
		      icon	: "${ctx}/skins/${skin}/images/menu_curriculumManager.png",
		      text	: "<fmt:message key="auth_room" />",
		      initName	: "auth_room",
		      action	: "${ctx}/backstage/ClassRoom.action"
		  }
		  </c:if>

		  <c:if test="${auths.menu_schedule != null}">
		  /*,
		  {
		      parent	: "menu_curriculumPlanManager",
		      id	: "menu_taskPlan",
		      icon	: "${ctx}/skins/${skin}/images/menu_taskPlan.png",
		      //text	: "<fmt:message key="menu_taskPlan" />",
		      text	: "<fmt:message key="menu_curriculumPlanManager" />",
		      initName	: "taskPlan",
		      action	: "${ctx}/backstage/ScheduleRecord.action"
		  }*/
		  </c:if>
		  
		  //学科
		  <c:if test="${auths.lesson_approve != null}">
		  ,
		  {
		      parent	: "menu_curriculumPlanManager",
		      id	: "menu_subject",
		      icon	: "${ctx}/skins/${skin}/images/menu_subjectManager.png",
		      //text	: "<fmt:message key="menu_taskPlan" />",
		      text	: "<fmt:message key="menu_subject" />",
		      initName	: "subject",
		      action	: "${ctx}/backstage/Subject.action"
		  }
		  </c:if>
		  
		  //课程类型
		  <c:if test="${auths.lesson_approve != null}">
		  ,
		  {
		      parent	: "menu_curriculumPlanManager",
		      id	: "menu_courseType",
		      icon	: "${ctx}/skins/${skin}/images/menu_courseTypeManager.png",
		      //text	: "<fmt:message key="menu_taskPlan" />",
		      text	: "<fmt:message key="menu_courseType" />",
		      initName	: "courseType",
		      action	: "${ctx}/backstage/CourseType.action"
		  }
		  </c:if>
		  
		  //课程名称
		  <c:if test="${auths.lesson_approve != null}">
		  /* ,
		  {
		      parent	: "menu_curriculumPlanManager",
		      id	: "menu_courseName",
		      icon	: "${ctx}/skins/${skin}/images/menu_courseNameManager.png",
		      //text	: "<fmt:message key="menu_taskPlan" />",
		      text	: "<fmt:message key="menu_courseName" />",
		      initName	: "courseName",
		      action	: "${ctx}/backstage/CourseName.action"
		  } */
		  </c:if>
		  
		  //审核不通过原因
		  <c:if test="${auths.lesson_approve != null}">
		  ,
		  {
		      parent	: "menu_curriculumPlanManager",
		      id	: "menu_courseType",
		      icon	: "${ctx}/skins/${skin}/images/menu_courseNameManager.png",
		      //text	: "<fmt:message key="menu_taskPlan" />",
		      text	: "<fmt:message key="menu_lessonReason" />",
		      initName	: "courseType",
		      action	: "${ctx}/backstage/LessonReason.action"
		  }
		  </c:if>
	      
		  // 用户&权限管理
		  <c:if test="${auths.menu_user != null || auths.menu_userGroup != null || auths.menu_userReview != null || auths.menu_ldap != null}">
		  ,
		  {
		      parent	: "root",
		      id	: "menu_userAuthManager",
		      text	: "<fmt:message key="menu_userAuthManager" />"
		  }
		  </c:if>
		  
		  <c:if test="${auths.menu_user != null}">
		  ,
		  {
		      parent	: "menu_userAuthManager",
		      id	: "menu_userManager",
		      icon	: "${ctx}/skins/${skin}/images/menu_userManager.png",
		      text	: "<fmt:message key="menu_userManager" />",
		      initName	: "userManager",
		      action	: "${ctx}/backstage/User.action"
		  }
		  </c:if>
		  
		  <c:if test="${auths.menu_userGroup != null}">
		  ,
		  {
		      parent	: "menu_userAuthManager",
		      id	: "menu_userGroupManager",
		      icon	: "${ctx}/skins/${skin}/images/menu_userGroupManager.png",
		      text	: "<fmt:message key="menu_userGroupManager" />",
		      initName	: "userGroupManager",
		      action	: "${ctx}/backstage/Usergroup.action"
		  }
		  </c:if>
		  
		  <c:if test="${auths.menu_role != null}">
		  ,
		  {
		      parent	: "menu_userAuthManager",
		      id	: "menu_roleManager",
		      icon	: "${ctx}/skins/${skin}/images/menu_roleManager.png",
		      text	: "<fmt:message key="menu_roleManager" />",
		      initName	: "userGroupManager",
		      action	: "${ctx}/backstage/Role.action"
		  }
		  </c:if>
		  
		  <c:if test="${auths.menu_userImport != null}">
/*		  ,
		  {
		      parent	: "menu_userAuthManager",
		      id	: "menu_userImportManager",
		      icon	: "${ctx}/skins/${skin}/images/menu_userGroupManager.png",
		      text	: "<fmt:message key="menu_userImportManager" />",
		      initName	: "userGroupManager",
		      action	: "${ctx}/backstage/User.action"
		  }*/
		  </c:if>		  
		  
		  <c:if test="${auths.menu_userReview != null}">
/* 		  ,
		  {
		      parent	: "menu_userAuthManager",
		      id	: "menu_userReview",
		      icon	: "${ctx}/skins/${skin}/images/menu_userReview.png",
		      text	: "<fmt:message key="menu_userReview" />",
		      initName	: "userReview",
		      action	: "${ctx}/backstage/UserReview.action"
		  } */
		  </c:if>
		  
		  <c:if test="${auths.menu_ldap != null}">
/* 		  ,
		  {
		      parent	: "menu_userAuthManager",
		      id	: "menu_LDAPManager",
		      icon	: "${ctx}/skins/${skin}/images/menu_userReview.png",
		      text	: "<fmt:message key="ldap_manage" />",
		      initName	: "LDAPManager",
		      action	: "${ctx}/backstage/User.action?userType=1"
		  } */
		  </c:if>  
		  
		  // 课件管理
		  <c:if test="${auths.menu_fileGroup != null || auths.menu_file != null || auths.file_look != null || auths.menu_fileReview != null || auths.upload_thirdpartyvideo != null || auths.menu_fileComment != null}">
		  ,
		  {
		      parent	: "root",
		      id	: "menu_coursewareManager",
		      text	: "<fmt:message key="menu_coursewareManager" />"
		  }
		  </c:if>
		  
		  
		  <c:if test="${auths.menu_fileGroup != null}">
		  ,
		  {
		      parent	: "menu_coursewareManager",
		      id	: "menu_coursewareTypeManager",
		      icon	: "${ctx}/skins/${skin}/images/menu_coursewareTypeManager.png",
		      text	: "<fmt:message key="menu_coursewareTypeManager" />",
		      initName	: "coursewareTypeManager",
		      action	: "${ctx}/backstage/FileGroup.action"
		  }
		  </c:if>
		  
		  /*
		  <c:if test="${auths.menu_UNQFile != null}">
		  {
		      parent	: "menu_coursewareManager",
		      id	: "menu_fineCoursewareManager",
		      icon	: "${ctx}/skins/${skin}/images/menu_fineCoursewareManager.png",
		      text	: "<fmt:message key="menu_fileGroup_unique" />",
		      initName	: "fineCoursewareManager",
		      action	: "${ctx}/backstage/File.action?cmd=16006"
		  },
		  </c:if>
		  */
		  
		  <c:if test="${auths.menu_file != null}">
		  ,
		  {
		      parent	: "menu_coursewareManager",
		      id	: "menu_coursewareMaintain",
		      icon	: "${ctx}/skins/${skin}/images/fileMaintain.png",
		      text	: "<fmt:message key="menu_coursewareMaintain" />",
		      initName	: "coursewareMaintain",
		      action	: "${ctx}/backstage/File.action"
		  }
		  </c:if>
		  
		  <c:if test="${auths.file_look != null}">
		  ,
		  {
		   parent	: "menu_coursewareManager",
		      id	: "menu_myFileManager",
		      icon	: "${ctx}/skins/${skin}/images/myfile.png",
		      text	: "<fmt:message key="my_file_list" />",
		      initName	: "myFileManager",
		      action	: "${ctx}/backstage/File.action?flag=0"
		  }
		  </c:if>
		  
		  
		  <c:if test="${auths.menu_fileReview != null}">
		  ,
		  {
		      parent	: "menu_coursewareManager",
		      id	: "menu_fileReviewManager",
		      icon	: "${ctx}/skins/${skin}/images/menu_fineCoursewareManager.png",
		      text	: "<fmt:message key="menu_file_review" />",
		      initName	: "fileReviewManager",
		      action	: "${ctx}/backstage/File.action?cmd=16008"
		  }
		  </c:if>
		  <c:if test="${auths.upload_thirdpartyvideo != null}">
		  ,
		  {
		      parent	: "menu_coursewareManager",
		      id	: "menu_videoFileManager",
		      icon	: "${ctx}/skins/${skin}/images/menu_file.png",
		      text	: "<fmt:message key="media_upload" />",
		      initName	: "videoFileManager",
		      action	: "${ctx}/backstage/upload.action"
		  }
		  </c:if>
		  <c:if test="${auths.menu_fileComment != null}">
		  ,
		  {
		      parent	: "menu_coursewareManager",
		      id	: "menu_coursewareCommentManager",
		      icon	: "${ctx}/skins/${skin}/images/menu_coursewareCommentManager.png",
		      text	: "<fmt:message key="menu_coursewareCommentManager" />",
		      initName	: "fileCommentManager",
		      action	: "${ctx}/backstage/FileComment.action"
		  }
		  </c:if>
		  <c:if test="${auths.menu_fileReview != null}">
		  ,
		  {
		      parent	: "menu_coursewareManager",
		      id	: "menu_vod_record",
		      icon	: "${ctx}/skins/${skin}/images/menu_coursewareCommentManager.png",
		      text	: "<fmt:message key="menu_vod_record" />",
		      initName	: "fileCommentManager",
		      action	: "${ctx}/backstage/VodManage.action?cmd=10000"
		  }
		  </c:if>
		  // 没有2级菜单的统一放到这里
		  <c:if test="${auths.menu_liveRecord != null || auths.log_manage != null || auths.menu_system != null || auths.menu_info != null || auths.menu_warn != null || auths.menu_notice != null}">
		  ,
		  {
		      parent	: "root",
		      id	: "menu_system",
		      text	: "<fmt:message key="menu_system" />"
		  }
		  </c:if>
		  
		  <c:if test="${auths.menu_liveRecord != null}">
		  ,
		  {
		      parent	: "menu_system",
		      id	: "menu_liveRecordManager",
		      icon	: "${ctx}/skins/${skin}/images/menu_liveRecord.png",
		      text	: "<fmt:message key="menu_liveRecordManager" />",
		      initName	: "liveRecordManager",
		      action	: "${ctx}/backstage/LiveRecord.action"
		  }
		  </c:if>
		  
		<c:if test="${auths.menu_notice != null}">
		  ,
		  {
		      parent	: "menu_system",
		      id	: "menu_systemLog",
		      icon	: "${ctx}/skins/${skin}/images/menu_notice.png",
		      text	: "<fmt:message key="menu_noticeManager" />",
		      initName	: "noticeManager",
		      action	: "${ctx}/backstage/Notice.action"
		  }
		</c:if>
		  
		  <c:if test="${auths.menu_info != null}">
		  ,
		  {
		      parent	: "menu_system",
		      id	: "menu_systemInfo",
		      icon	: "${ctx}/skins/${skin}/images/menu_systemInfo.png",
		      text	: "<fmt:message key="menu_systemInfo" />",
		      initName	: "systemInfo",
		      action	: "${ctx}/backstage/System.action?cmd=15001"
		  }
		  </c:if>
		  
		  <c:if test="${auths.menu_system != null}">
		  ,
		  {
		      parent	: "menu_system",
		      id	: "menu_systemManager",
		      icon	: "${ctx}/skins/${skin}/images/menu_system.png",
		      text	: "<fmt:message key="menu_systemManager" />",
		      initName	: "systemManager",
		      action	: "${ctx}/backstage/System.action"
		  }
		  </c:if>
		  
		  <c:if test="${auths.log_manage != null}">
		  ,
		  {
		      parent	: "menu_system",
		      id	: "menu_systemLog",
		      icon	: "${ctx}/skins/${skin}/images/menu_systemLog.png",
		      text	: "<fmt:message key="menu_systemLog" />",
		      initName	: "systemLog",
		      action	: "${ctx}/backstage/SystemLog.action"
		  }
		  </c:if>
		  
		  <c:if test="${auths.menu_warn != null}">
		  /*,
		  {
		      parent	: "menu_system",
		      id	: "menu_systemWarn",
		      icon	: "${ctx}/skins/${skin}/images/menu_warn.png",
		      text	: "<fmt:message key="auth_warn_info" />",
		      initName	: "systemWarn",
		      action	: "${ctx}/backstage/Warn.action"
		  }*/
		  </c:if>
		  
		  // 录播配置管理
		  <c:if test="${auths.menu_position != null || auths.menu_reachServer != null || auths.menu_classRoom != null || auths.menu_node != null}">
		  /*,
		  {
		      parent	: "root",
		      id	: "menu_RABConfigManager",
		      text	: "<fmt:message key="menu_RABConfigManager" />"
		  }*/
		  </c:if>
		  
		  <c:if test="${auths.menu_position != null}">
/* 		  ,
		  {
		      parent	: "menu_RABConfigManager",
		      id	: "menu_belongInfoManager",
		      icon	: "${ctx}/skins/${skin}/images/menu_belongInfo.png",
		      text	: "<fmt:message key="menu_belongInfoManager" />",
		      initName	: "belongInfoManager",
		      action	: "${ctx}/backstage/Position.action"
		  } */
		  </c:if>
		  
		  <c:if test="${auths.menu_reachServer != null}">
/* 		  ,
		  {
		      parent	: "menu_RABConfigManager",
		      id	: "menu_RABServerManager",
		      icon	: "${ctx}/skins/${skin}/images/menu_RABServerManager.png",
		      text	: "<fmt:message key="menu_RABServerManager" />",
		      initName	: "RABServerManager",
		      action	: "${ctx}/backstage/RecServer.action"
		  } */
		  </c:if>
		  
		  <c:if test="${auths.menu_classRoom != null}">
		  /*,
		  {
		      parent	: "menu_RABConfigManager",
		      id	: "menu_classManager",
		      icon	: "${ctx}/skins/${skin}/images/menu_classManager.png",
		      text	: "<fmt:message key="menu_classManager" />",
		      initName	: "classManager",
		      action	: "${ctx}/backstage/Room.action"
		  }*/
		  </c:if>
		  
		  <c:if test="${auths.menu_node != null}">
 		  /*,
		  {
		      parent	: "menu_RABConfigManager",
		      id	: "menu_nodeManager",
		      icon	: "${ctx}/skins/${skin}/images/menu_nodeManager.png",
		      text	: "<fmt:message key="menu_nodeManager" />",
		      initName	: "nodeManager",
		      action	: "${ctx}/backstage/NodeServer.action"
		  } */
		  </c:if>
		  
		  
		  //系统管理，首页配置界面
		   
		  <c:if test="${auths.front_conf != null}">
		  ,
		  {
		      parent	: "root",
		      id	: "menu_formdefine",
		      text	: "<fmt:message key="cms_setting" />"
		  }
		  ,
		  {
		      parent	: "menu_formdefine",
		      id	: "menu_moduleSet",
		      icon	: "${ctx}/skins/${skin}/images/templete.png",
		      text	: "<fmt:message key="Module_settings" />",
		      initName	: "moduleSet",
		      action	: "${ctx}/backstage/ftlConfig.action"
		   }
	       ,
		   {
			      parent	: "menu_formdefine",
			      id	: "menu_pageSet",
			      icon	: "${ctx}/skins/${skin}/images/page_section.png",
			      text	: "<fmt:message key="page_or_position" />",
			      initName	: "pageSet",
			      action	: "${ctx}/backstage/FormDefineLayout.action"
			  },
	       {
			      parent	: "menu_formdefine",
			      id	: "menu_menuSet",
			      icon	: "${ctx}/skins/${skin}/images/menuformdefine.png",
			      text	: "<fmt:message key="Menu_settings" />",
			      initName	: "menuSet",
			      action	: "${ctx}/backstage/menu.action"
			  },
	     
		   {
			      parent	: "menu_formdefine",
			      id	: "menu_applyTemplete",
			      icon	: "${ctx}/skins/${skin}/images/home.png",
			      text	: "<fmt:message key="apply_templetes" />",
			      initName	: "applyTemplete",
			      action	: "${ctx}/backstage/deploy.action"
			  }
		
		</c:if>
		  
	      ],
	      initMenu : top.initMenu,
	      click : function(action) {
		  	top.refreshMainFrame(action);
	      }
	  });
      });
    </script>
  </body>
</html>
