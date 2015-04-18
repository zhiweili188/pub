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
public interface BaseService<T extends Persistentable> {
	public void insert(T t);
	public T getByID(Integer id);
	public void update(T t);
	
	public int count(T query);
	
	public List<T> query(T query, PageBean page);
	public List<T> getAll(T query);
	
	
	public void delete(Integer id);
}
