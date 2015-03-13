/**
 * Copyright (c) @2015-3-11. All Rights Reserved.
 * AUTHOR: LIZHIWEI</a>
 */
package com.szreach.mediacenter.auth.user.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.szreach.mediacenter.auth.user.bean.LoginUser;
import com.szreach.mediacenter.auth.user.dao.LoginUserDao;
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
public class LoginUserServiceImpl extends AbstractBaseServiceImpl  implements LoginUserService {

	@Autowired
	private LoginUserDao loginUserDao;
	@Override
	public void insertLoginUser(LoginUser user) {
		loginUserDao.insert(user);

	}

	@Override
	public LoginUser getLoginUserByID(Integer loginUserId) {
		return loginUserDao.get(loginUserId);
	}

	@Override
	public void updateLoginUser(LoginUser user) {
		loginUserDao.update(user);

	}

	@Override
	public int count() {
		return loginUserDao.countAll();
	}
	@Override
	public List<LoginUser> query(LoginUser query) {
		return loginUserDao.findAll();
	}

	@Override
	public List<LoginUser> query(LoginUser query, PageBean page) {
		int total = loginUserDao.countAll();
		page.setTotal(total);
		return loginUserDao.query(query, page);
	}

	@Override
	public void delete(Integer loginUserId) {
		loginUserDao.delete(loginUserId);

	}

}
