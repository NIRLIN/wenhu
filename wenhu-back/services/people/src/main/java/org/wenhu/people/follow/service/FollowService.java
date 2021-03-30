package org.wenhu.people.follow.service;

import org.wenhu.common.pojo.DO.FollowUserDO;
import org.wenhu.common.pojo.DTO.UserDTO;

import java.util.HashMap;
import java.util.List;

/**
 * @author NIRLIN
 * @version 1.0
 * @date 2021/3/25
 */
public interface FollowService {
    HashMap<String, Object> listFollowByUserId(String userId);
}
