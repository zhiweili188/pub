/**
 * Copyright (c) @2015-4-2. All Rights Reserved.
 * AUTHOR: LIZHIWEI</a>
 */
package com.szreach.mediacenter.auth.department.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.szreach.mediacenter.auth.department.bean.Department;
import com.szreach.mediacenter.auth.department.dao.DepartmentDao;
import com.szreach.mediacenter.common.base.AbstractBaseServiceImpl;
import com.szreach.mediacenter.common.base.BaseDao;

/**
 * @Description:
 * @author lizhiwei
 * @Date: 2015-4-2
 * @Version: 1.0
 */
@Service("departmentService")
@Scope("prototype")
@Transactional
public class DepartmentServiceImpl extends AbstractBaseServiceImpl<Department> implements DepartmentService {

	@Autowired
	private DepartmentDao departmentDao;

	@Override
	public BaseDao<Department> getBaseDao() {
		return departmentDao;
	}

}
