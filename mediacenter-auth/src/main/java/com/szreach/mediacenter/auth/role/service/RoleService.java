package com.szreach.mediacenter.auth.role.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.szreach.mediacenter.auth.role.bean.RoleBean;
import com.szreach.mediacenter.auth.role.dao.RoleDao;
import com.szreach.mediacenter.common.util.DateUtil;
import com.szreach.mediacenter.common.util.M;
@Service("roleService")
@Scope("prototype")
public class RoleService implements IRoleService{
	@Autowired
	RoleDao roleDao;
	@Override
	public int count() {
		return roleDao.count();
	}

	@Override
	public void insertRole(RoleBean bean) {
		String id =M.getID();
		bean.setId(id);
		bean.setCreateTime(DateUtil.getCurrentDateTimeStr());
		roleDao.insert(bean);
		
	}

	@Override
	public void updateRole(RoleBean bean) {
		roleDao.update(bean);
		
	}

	@Override
	public void delete(String roleId) {
		if(roleId != null && !"".equals(roleId)) {
			String[] ids = roleId.split(",");
			for(String id: ids) {
				
				roleDao.delete(id);
			}
		}
		
	}

	@Override
	public RoleBean getRoleByID(String roleId) {
		RoleBean bean = roleDao.getByID(roleId);
		return bean;
	}

	@Override
	public List<RoleBean> queryRole(RoleBean query) {
		int total = roleDao.count();
		query.setTotal(total);
		List<RoleBean> list = roleDao.query(query);
		return list;
	}

}
