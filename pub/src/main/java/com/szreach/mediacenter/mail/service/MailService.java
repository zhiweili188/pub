/**
 * Copyright (c) @2015-4-24. All Rights Reserved.
 * AUTHOR: LIZHIWEI</a>
 */
package com.szreach.mediacenter.mail.service;

import java.util.Map;

import org.springframework.mail.SimpleMailMessage;

/**
 * @Description:
 * @author lizhiwei
 * @Date: 2015-4-24
 * @Version: 1.0
 */
public interface MailService {
	public void send(SimpleMailMessage msg, String templateName, Map<String, Object> model);
}
