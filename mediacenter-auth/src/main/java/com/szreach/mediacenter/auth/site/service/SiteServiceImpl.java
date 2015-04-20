/**
 * Copyright (c) @2015-2-4. All Rights Reserved.
 * AUTHOR: LIZHIWEI</a>
 */
package com.szreach.mediacenter.auth.site.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.szreach.mediacenter.auth.site.bean.SiteBean;
import com.szreach.mediacenter.auth.site.dao.SiteDao;
import com.szreach.mediacenter.common.base.AbstractBaseServiceImpl;
import com.szreach.mediacenter.common.base.PageBean;

/**
 * @Description:
 * @author lizhiwei
 * @Date: 2015-2-4
 * @Version: 1.0
 */
@Service("siteService")
@Scope("prototype")
@Transactional
public class SiteServiceImpl extends AbstractBaseServiceImpl implements SiteService {

	@Autowired
	private SiteDao siteDao;
	/* (non-Javadoc)
	 * @see com.szreach.mediacenter.auth.site.service.SiteService#insertSite(com.szreach.mediacenter.auth.site.bean.SiteBean)
	 */
	@Override
	public void insertSite(SiteBean site) {
		siteDao.insertSite(site);

	}

	/* (non-Javadoc)
	 * @see com.szreach.mediacenter.auth.site.service.SiteService#getSiteByID(java.lang.Integer)
	 */
	@Override
	public SiteBean getSiteByID(Integer siteId) {
		return siteDao.getSiteByID(siteId);
	}

	/* (non-Javadoc)
	 * @see com.szreach.mediacenter.auth.site.service.SiteService#updateSite(com.szreach.mediacenter.auth.site.bean.SiteBean)
	 */
	@Override
	public void updateSite(SiteBean site) {
		siteDao.updateSite(site);

	}

	/* (non-Javadoc)
	 * @see com.szreach.mediacenter.auth.site.service.SiteService#count()
	 */
	@Override
	public int count() {
		// TODO Auto-generated method stub
		return siteDao.count();
	}

	/* (non-Javadoc)
	 * @see com.szreach.mediacenter.auth.site.service.SiteService#query(com.szreach.mediacenter.auth.site.bean.SiteBean)
	 */
	@Override
	public List<SiteBean> query(SiteBean query) {
		int total = siteDao.count();
		return siteDao.query(query);
	}
	@Override
	public List<SiteBean> query(SiteBean query, PageBean page) {
		int total = siteDao.count();
		page.setTotal(total);
		return siteDao.query(query);
	}

	/* (non-Javadoc)
	 * @see com.szreach.mediacenter.auth.site.service.SiteService#delete(java.lang.String)
	 */
	@Override
	public void delete(Integer siteId) {
		siteDao.delete(siteId);

	}

}
