/**
 * Copyright (c) @2015-3-11. All Rights Reserved.
 * AUTHOR: LIZHIWEI</a>
 */
package com.szreach.mediacenter.auth.user.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.szreach.mediacenter.auth.role.bean.Role;
import com.szreach.mediacenter.auth.user.bean.LoginUser;
import com.szreach.mediacenter.auth.user.dao.LoginUserDao;
import com.szreach.mediacenter.auth.user.dao.UserRoleDao;
import com.szreach.mediacenter.common.base.AbstractBaseServiceImpl;
import com.szreach.mediacenter.common.base.PageBean;

/**
 * @Description:
 * @author lizhiwei
 * @Date: 2015-3-11
 * @Version: 1.0
 */
@Service("loginUserService")
@Scope("prototype")
@Transactional
public class LoginUserServiceImpl extends AbstractBaseServiceImpl<LoginUser>  implements LoginUserService {

	@Autowired
	private LoginUserDao loginUserDao;
	@Autowired
	private UserRoleDao userRoleDao;
	
	@Override
	public void insert(LoginUser user) {
		user.setPassword("123456");
		user.setRoleType(0);
		user.setUserType(0);
		user.setLevel(0);
		loginUserDao.insert(user);

		saveUserRole(user);
	}
	
	private void saveUserRole(LoginUser user) {
		if( !StringUtils.isEmpty(user.getRoleIds())) {
			 List<Integer> roleIdList = new ArrayList<Integer>();
				String[] ids = user.getRoleIds().split(",");
				for(String id : ids) {
					roleIdList.add(Integer.valueOf(id));
				}
				userRoleDao.insertUserRole(user.getId(), roleIdList);
		}
	}

	@Override
	public LoginUser getByID(Integer loginUserId) {
		return loginUserDao.get(loginUserId);
	}
	
	public LoginUser getByUserName(String userName) {
		return loginUserDao.getByUserName(userName);
	}

	@Override
	public void update(LoginUser user) {
		loginUserDao.update(user);
		userRoleDao.delete(user.getId());
		saveUserRole(user);

	}
	
	public void updatePwd(LoginUser user) {
		loginUserDao.updatePwd(user);
	}

	@Override
	public int count(LoginUser query) {
		return loginUserDao.countAll(query);
	}
	@Override
	public List<LoginUser> query(LoginUser query) {
		return loginUserDao.findAll();
	}

	@Override
	public List<LoginUser> query(LoginUser query, PageBean page) {
		int total = loginUserDao.countAll( query);
		page.setTotal(total);
		return loginUserDao.query(query, page);
	}

	@Override
	public void deleteMore(String loginUserIds) {
		String[] ids = loginUserIds.split(",");
		if(ids.length == 1) {
			loginUserDao.delete(Integer.valueOf(ids[0]));
		} else {
			List<Integer> list = new ArrayList<Integer>(ids.length);
			for(String id : ids) {
				list.add(Integer.valueOf(id));
			}
			loginUserDao.batchDel(list);
		}
	}

}
