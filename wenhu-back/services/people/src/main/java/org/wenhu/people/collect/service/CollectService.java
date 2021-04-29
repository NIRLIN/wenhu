package org.wenhu.people.collect.service;

import org.wenhu.common.util.Result;

import java.util.HashMap;

/**
 * @author NIRLIN
 * @version 1.0
 * @date 2021/3/25
 */
public interface CollectService {

    Result<HashMap<String, Object>> collectService(String userId);

    Result<HashMap<String, Object>> userCollectAnswer(String userId, String answerId);

}
