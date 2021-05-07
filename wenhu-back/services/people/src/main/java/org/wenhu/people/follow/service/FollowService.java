package org.wenhu.people.follow.service;


import org.wenhu.common.util.Result;

import java.util.HashMap;

/**
 * @author NIRLIN
 * @version 1.0
 * @date 2021/3/25
 */
public interface FollowService {
    /**
     * 关注列表
     *
     * @param userId 用户id
     * @return 操作结果
     */
    HashMap<String, Object> listFollowByUserId(String userId);

    /**
     * 关注操作
     *
     * @param userId       用户id
     * @param byFollowerId 被关注用户id
     * @return 操作结果
     */
    Result<HashMap<String, Object>> followUserOperation(String userId, String byFollowerId);

    /**
     * 用户是否关注
     *
     * @param userId       用户id
     * @param byFollowerId 被关注用户id
     * @return 操作结果
     */
    Result<HashMap<String, Object>> getUserFollow(String userId, String byFollowerId);

    /**
     * 共同关注
     *
     * @param homeUserid  被查询用户id
     * @param loginUserId 查询用户id
     * @return操作结果
     */
    Result<HashMap<String, Object>> listCommonFollow(String homeUserid, String loginUserId);
}
