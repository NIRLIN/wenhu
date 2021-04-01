package org.wenhu.people.follow.service;

import java.util.HashMap;

/**
 * @author NIRLIN
 * @version 1.0
 * @date 2021/3/25
 */
public interface FollowService {
    HashMap<String, Object> listFollowByUserId(String userId);
}
