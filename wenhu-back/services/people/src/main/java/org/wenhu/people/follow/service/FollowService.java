package org.wenhu.people.follow.service;

import org.wenhu.common.pojo.DO.FollowUserDO;
import org.wenhu.common.pojo.DTO.UserDTO;

import java.util.List;

/**
 * @author NIRLIN
 * @version 1.0
 * @date 2021/3/25
 */
public interface FollowService {
    List<FollowUserDO> listUserFollowByUserId(UserDTO userDTO);

    List<FollowUserDO> listUserFansByUserId(UserDTO userDTO);
}
