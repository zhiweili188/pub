/**
 * Copyright (c) @2014-12-29. All Rights Reserved.
 * AUTHOR: LIZHIWEI</a>
 */
package com.szreach.mediacenter.auth.test.dao;

import org.springframework.stereotype.Repository;

import com.szreach.mediacenter.auth.test.ex.BusinessException;
import com.szreach.mediacenter.auth.test.ex.ParameterException;

/**
 * @Description:
 * @author lizhiwei
 * @Date: 2014-12-29
 * @Version: 1.0
 */
@Repository("testDao")
public class TestDao {
	public void exception(Integer id) throws Exception {  
        switch(id) {  
        case 1:  
            throw new BusinessException("12", "dao12");  
        case 2:  
            throw new BusinessException("22", "dao22");  
        case 3:  
            throw new BusinessException("32", "dao32");  
        case 4:  
            throw new BusinessException("42", "dao42");  
        case 5:  
            throw new BusinessException("52", "dao52");  
        default:  
            throw new ParameterException("Dao Parameter Error");  
        }  
    }  
}
