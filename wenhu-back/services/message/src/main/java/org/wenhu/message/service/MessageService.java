package org.wenhu.message.service;

import java.util.HashMap;

/**
 * @author NIRLIN
 * @version 1.0
 * @date 2021/5/13
 */
public interface MessageService {
    Object userMessageList(String userId);

    Object saveMessage(HashMap<String, Object> hashMap);
}
