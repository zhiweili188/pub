package com.szreach.mediacenter.auth.usergroup.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.szreach.mediacenter.auth.usergroup.bean.UserGroupBean;
import com.szreach.mediacenter.auth.usergroup.dao.UserGroupDao;
import com.szreach.mediacenter.common.util.DateUtil;
import com.szreach.mediacenter.common.util.M;
@Service("userGroupService")
@Scope("prototype")
public class UserGroupService implements IUserGroupService {
	@Autowired
	UserGroupDao userGroupDao;
	@Override
	public int count() {
		return userGroupDao.count();
	}

	@Override
	public void insertUserGroup(UserGroupBean bean) {
		String id =M.getID();
		bean.setId(id);
		bean.setCreateTime(DateUtil.getCurrentDateTimeStr());
		userGroupDao.insert(bean);
		
	}

	@Override
	public void updateUserGroup(UserGroupBean bean) {
		userGroupDao.update(bean);
		
	}

	@Override
	public void delete(String userGroupId) {
		if(userGroupId != null && !"".equals(userGroupId)) {
			String[] ids = userGroupId.split(",");
			for(String id: ids) {
				
				userGroupDao.delete(id);
			}
		}
		
	}

	@Override
	public UserGroupBean getUserGroupByID(String id) {
		UserGroupBean bean = userGroupDao.getByID(id);
		return bean;
	}

	@Override
	public List<UserGroupBean> queryUserGroup(UserGroupBean query) {
		int total = userGroupDao.count();
		query.setTotal(total);
		List<UserGroupBean> list = userGroupDao.query(query);
		return list;
	}

}
