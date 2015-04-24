/**
 * Copyright (c) @2015-4-15. All Rights Reserved.
 * AUTHOR: LIZHIWEI</a>
 */
package com.szreach.mediacenter.course.apply.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.szreach.mediacenter.auth.login.bean.LoginUser;
import com.szreach.mediacenter.auth.login.bean.UserActivate;
import com.szreach.mediacenter.auth.login.dao.LoginUserDao;
import com.szreach.mediacenter.auth.login.dao.UserActivateDao;
import com.szreach.mediacenter.common.base.AbstractBaseServiceImpl;
import com.szreach.mediacenter.common.base.BaseDao;
import com.szreach.mediacenter.common.base.PageBean;
import com.szreach.mediacenter.common.util.CommonTools;
import com.szreach.mediacenter.common.util.DateUtil;
import com.szreach.mediacenter.course.apply.bean.UserRegister;
import com.szreach.mediacenter.course.apply.dao.UserRegisterDao;

/**
 * @Description:
 * @author lizhiwei
 * @Date: 2015-4-15
 * @Version: 1.0
 */
@Service("userRegisterService")
@Scope("prototype")
@Transactional
public class UserRegisterServiceImpl extends AbstractBaseServiceImpl<UserRegister> implements UserRegisterService {

	@Autowired
	private UserRegisterDao userRegisterDao;
	@Autowired
	private LoginUserDao loginUserDao;
	@Autowired
	private UserActivateDao userActivateDao;
	@Value("${activate_code_valid_date}")
	private String activateCodeValidDate;
	
	@Override
	public BaseDao<UserRegister> getBaseDao() {
		return userRegisterDao;
	}

	/* (non-Javadoc)
	 * @see com.szreach.mediacenter.common.base.BaseService#count(com.szreach.mediacenter.common.base.Persistentable)
	 */
	@Override
	public int count(UserRegister query) {
		return userRegisterDao.countAll(query);
	}

	/* (non-Javadoc)
	 * @see com.szreach.mediacenter.common.base.BaseService#delete(java.lang.Integer)
	 */
	@Override
	public void delete(Integer arg0) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see com.szreach.mediacenter.common.base.BaseService#getAll(com.szreach.mediacenter.common.base.Persistentable)
	 */
	@Override
	public List<UserRegister> getAll(UserRegister arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.szreach.mediacenter.common.base.BaseService#getByID(java.lang.Integer)
	 */
	@Override
	public UserRegister getByID(Integer arg0) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void saveRegisterInfo(UserRegister entity) {

		entity.setCreateTime(DateUtil.getCurrentDateTimeStr());
		if(entity.getIdentity() == null) {
			entity.setIdentity(0);
		}
		
		String userId = CommonTools.getGUID();
		entity.setUserId(userId);
		userRegisterDao.insert(entity);
		
		LoginUser loginUser = new LoginUser();
		loginUser.setUserId(userId);
		loginUser.setUserName(entity.getUserName());
		loginUser.setPasswd(CommonTools.getMD5(entity.getPasswd()));
		loginUserDao.insert(loginUser);
		
		UserActivate activate = new UserActivate();
		activate.setUserId(userId);
		Date validDate = DateUtil.addDay(DateUtil.getCurrentDateTime(), Integer.valueOf(activateCodeValidDate));
		activate.setValidDate(DateUtil.formatDateTime(validDate));
		activate.setValidCode(CommonTools.getMD5(userId+activate.getValidDate()));
		userActivateDao.insert(activate);
	
	}

	@Override
	public void insert(UserRegister entity) {
		entity.setCreateTime(DateUtil.getCurrentDateTimeStr());
		if(entity.getIdentity() == null) {
			entity.setIdentity(0);
		}
		
		userRegisterDao.insert(entity);
		
	}

	@Override
	public List<UserRegister> query(UserRegister query, PageBean page) {
		int total = userRegisterDao.countAll( query);
		page.setTotal(total);
		return userRegisterDao.query(query, page);
	}


	@Override
	public void update(UserRegister entity) {
		userRegisterDao.update(entity);

	}
	
	public UserRegister getByUserId(String userId) {
		return userRegisterDao.getByUserId(userId);
	}
	
	public UserRegister getByIdCardNo(String idCardNo) {
		return userRegisterDao.getByIdCardNo(idCardNo);
	}
	
	public UserRegister getByEmail(String email) {
		return userRegisterDao.getByEmail(email);
	}

}
