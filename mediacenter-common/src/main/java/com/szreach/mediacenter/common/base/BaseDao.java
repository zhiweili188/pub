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
public interface BaseDao<T extends Persistentable> extends MapperMarker {

	Integer insert(T model);
	void delete(T model);
	void delete(Integer id);
	T get(Integer id);
	void update(T model);
	int countAll();
	
	List<T> findAll();
	List<T> query(T model);
	
	List<Integer> findAllIds();
}
