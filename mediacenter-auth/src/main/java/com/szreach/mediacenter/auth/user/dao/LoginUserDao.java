/**
 * Copyright (c) @2015-3-11. All Rights Reserved.
 * AUTHOR: LIZHIWEI</a>
 */
package com.szreach.mediacenter.auth.user.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.szreach.mediacenter.auth.user.bean.LoginUser;
import com.szreach.mediacenter.common.base.BaseDao;
import com.szreach.mediacenter.common.base.PageBean;

/**
 * @Description:
 * @author lizhiwei
 * @Date: 2015-3-11
 * @Version: 1.0
 */
public interface LoginUserDao extends BaseDao<LoginUser> {

public List<LoginUser> query(@Param("query") LoginUser query, @Param("page")  PageBean page);
}
