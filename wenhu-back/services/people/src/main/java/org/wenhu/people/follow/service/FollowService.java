package org.wenhu.people.follow.service;

import org.wenhu.common.util.Result;

import java.util.HashMap;

/**
 * @author NIRLIN
 * @version 1.0
 * @date 2021/3/25
 */
public interface FollowService {
    HashMap<String, Object> listFollowByUserId(String userId);

    Result<HashMap<String, Object>> followUserOperation(String userId, String byFollowerId);

    Result<HashMap<String, Object>> getUserFollow(String userId, String byFollowerId);
}
