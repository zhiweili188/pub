/**
 * Copyright (c) @2015-4-2. All Rights Reserved.
 * AUTHOR: LIZHIWEI</a>
 */
package com.szreach.mediacenter.auth.room.action;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.szreach.mediacenter.auth.role.bean.Role;
import com.szreach.mediacenter.auth.room.bean.Room;
import com.szreach.mediacenter.auth.room.service.RoomService;
import com.szreach.mediacenter.common.base.BaseAction;
import com.szreach.mediacenter.common.base.BaseService;
import com.szreach.mediacenter.common.base.PageBean;

/**
 * @Description:
 * @author lizhiwei
 * @Date: 2015-4-2
 * @Version: 1.0
 */
@Controller
@RequestMapping("/room")
@Scope("prototype")
public class RoomAction extends BaseAction<Room> {
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private RoomService roomService;
	
	@Override
	public BaseService getService() {
		return roomService;
	}

	@Override
	protected String getPrefix() {
		return "/room";
	}
	
}
