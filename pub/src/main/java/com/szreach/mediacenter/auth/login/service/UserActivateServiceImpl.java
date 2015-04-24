/**
 * Copyright (c) @2015-4-24. All Rights Reserved.
 * AUTHOR: LIZHIWEI</a>
 */
package com.szreach.mediacenter.auth.login.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.szreach.mediacenter.auth.login.bean.LoginUser;
import com.szreach.mediacenter.auth.login.bean.UserActivate;
import com.szreach.mediacenter.auth.login.dao.LoginUserDao;
import com.szreach.mediacenter.auth.login.dao.UserActivateDao;
import com.szreach.mediacenter.common.base.AbstractBaseServiceImpl;
import com.szreach.mediacenter.common.base.BaseDao;
import com.szreach.mediacenter.common.util.DateUtil;
import com.szreach.mediacenter.st.ReturnCode;

/**
 * @Description:
 * @author lizhiwei
 * @Date: 2015-4-24
 * @Version: 1.0
 */
@Service("userActivateService")
@Scope("prototype")
@Transactional
public class UserActivateServiceImpl extends AbstractBaseServiceImpl<UserActivate> implements UserActivateService {
	@Autowired
	private UserActivateDao userActivateDao;
	@Autowired
	private LoginUserDao loginUserDao;
	
	@Override
	public BaseDao<UserActivate> getBaseDao() {
		return userActivateDao;
	}
	@Override
	public int activateUser(UserActivate param) {
		UserActivate ac = userActivateDao.getByUserId(param.getUserId());
		if(ac == null) {
			return ReturnCode.ERR_ACTIVATE_CODE_INVALID;
		}
		
		if(StringUtils.hasText(param.getValidCode())) {
			Date validDate = DateUtil.toDateTime(ac.getValidDate());
			if(DateUtil.getCurrentDateTime().after(validDate)) {
				//已经过了激活时间
				return ReturnCode.ERR_ACTIVATE_DATE_EXPIRED;
			} else {
				if(param.getValidCode().equals(ac.getValidCode())) {
					//更新用户的激活状态
					LoginUser user = new LoginUser();
					user.setUserId(param.getUserId());
					user.setActivateStatus(1);
					loginUserDao.updateActivateStatus(user);
				} else {
					return ReturnCode.ERR_ACTIVATE_CODE_INVALID;
				}
			}
		}
		return ReturnCode.USER_ACTIVATE_SUCCESS;
	}
	
	public UserActivate getByID(String userId) {
		return userActivateDao.getByUserId(userId);
	}

}
