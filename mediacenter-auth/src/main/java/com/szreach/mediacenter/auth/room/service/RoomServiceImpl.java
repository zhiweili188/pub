/**
 * Copyright (c) @2015-4-2. All Rights Reserved.
 * AUTHOR: LIZHIWEI</a>
 */
package com.szreach.mediacenter.auth.room.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.szreach.mediacenter.auth.room.bean.Room;
import com.szreach.mediacenter.auth.room.dao.RoomDao;
import com.szreach.mediacenter.common.base.AbstractBaseServiceImpl;
import com.szreach.mediacenter.common.base.PageBean;

/**
 * @Description:
 * @author lizhiwei
 * @Date: 2015-4-2
 * @Version: 1.0
 */
@Service("roomService")
@Scope("prototype")
@Transactional
public class RoomServiceImpl extends AbstractBaseServiceImpl<Room> implements RoomService {

	@Autowired
	private RoomDao roomDao;
	
	@Override
	public void insert(Room room) {
		roomDao.insert(room);

	}

	@Override
	public Room getByID(Integer roomId) {
		return roomDao.get(roomId);
	}

	@Override
	public void update(Room room) {
		roomDao.update(room);

	}

	@Override
	public int count(Room query) {
		return roomDao.countAll(query);
	}

	@Override
	public List<Room> query(Room query, PageBean page) {
		int total = roomDao.countAll( query);
		page.setTotal(total);
		return roomDao.query(query, page);
	}

}
