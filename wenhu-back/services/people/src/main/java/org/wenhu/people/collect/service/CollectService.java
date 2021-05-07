package org.wenhu.people.collect.service;

import org.wenhu.common.util.Result;

import java.util.HashMap;

/**
 * @author NIRLIN
 * @version 1.0
 * @date 2021/3/25
 */
public interface CollectService {

    /**
     * 收藏操作
     *
     * @param userId 用户id
     * @return 操作结果
     */
    Result<HashMap<String, Object>> collectService(String userId);

    /**
     * 收藏操作
     *
     * @param userId   用户id
     * @param answerId 回答id
     * @return 操作结果
     */
    Result<HashMap<String, Object>> userCollectAnswer(String userId, String answerId);

}
