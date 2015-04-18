/**
 * Copyright (c) @2015-2-5. All Rights Reserved.
 * AUTHOR: LIZHIWEI</a>
 */
package com.szreach.mediacenter.common.base;

import java.util.List;

/**
 * @Description:
 * @author lizhiwei
 * @Date: 2015-2-5
 * @Version: 1.0
 */
public abstract class AbstractBaseServiceImpl<T extends Persistentable> implements BaseService<T> {

	public  BaseDao<T> getBaseDao() {
		// TODO sub class need to override this method
		return null;
	}
	
	@Override
	public void insert(T t) {
		getBaseDao().insert(t);
		
	}

	@Override
	public T getByID(Integer id) {
		return getBaseDao().get(id);
	}

	@Override
	public void update(T t) {
		getBaseDao().update(t);
		
	}

	@Override
	public int count(T query) {
		return getBaseDao().countAll(query);
	}

	@Override
	public List<T> query(T query, PageBean page) {
		if(page != null) {
			
			int total = getBaseDao().countAll(query);
			page.setTotal(total);
		}
		List<T> list = getBaseDao().query(query, page);
		return list;
	}

	@Override
	public List<T> getAll(T query) {
		return getBaseDao().query(query);
	}

	@Override
	public void delete(Integer id) {
		getBaseDao().delete(id);
		
	}

}
