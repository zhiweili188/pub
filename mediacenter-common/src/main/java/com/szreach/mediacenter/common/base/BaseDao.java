/**
 * Copyright (c) @2015-2-5. All Rights Reserved.
 * AUTHOR: LIZHIWEI</a>
 */
package com.szreach.mediacenter.common.base;

import java.util.List;

import org.apache.ibatis.annotations.Param;

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
	
	List<T> findAll();
	List<T> query(T model);
	
	List<Integer> findAllIds();
	
	public int countAll(@Param("query") T query);
	public List<T> query(@Param("query") T query, @Param("page")  PageBean page);
}
