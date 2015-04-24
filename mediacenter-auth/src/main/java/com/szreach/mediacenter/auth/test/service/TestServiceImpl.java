/**
 * Copyright (c) @2014-12-29. All Rights Reserved.
 * AUTHOR: LIZHIWEI</a>
 */
package com.szreach.mediacenter.auth.test.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.szreach.mediacenter.auth.test.dao.TestDao;
import com.szreach.mediacenter.exception.BusinessException;
import com.szreach.mediacenter.exception.ParameterException;

/**
 * @Description:
 * @author lizhiwei
 * @Date: 2014-12-29
 * @Version: 1.0
 */
@Service("testService")  
public class TestServiceImpl implements TestService {  
    @Resource  
    private TestDao testDao;  
      
    public void exception(Integer id) throws Exception {  
        switch(id) {  
        case 1:  
            throw new BusinessException("11", "service11");  
        case 2:  
            throw new BusinessException("21", "service21");  
        case 3:  
            throw new BusinessException("31", "service31");  
        case 4:  
            throw new BusinessException("41", "service41");  
        case 5:  
            throw new BusinessException("51", "service51");  
        default:  
            throw new ParameterException("Service Parameter Error");  
        }  
    }  
  
    @Override  
    public void dao(Integer id) throws Exception {  
        testDao.exception(id);  
    }  
}  
