package org.wenhu.feign.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.wenhu.common.pojo.DO.FollowUserDO;
import org.wenhu.common.pojo.DO.QuestionDO;
import org.wenhu.common.pojo.DTO.UserDTO;

import java.util.List;

/**
 * @author NIRLIN
 * @version 1.0
 * @date 2021/3/16
 */

@Component
@FeignClient(name = "people")
public interface PeopleFeignClient {
    /**
     * 服务调用
     *
     * @param userDTO 用户信息
     * @return List<QuestionDO>
     */
    @PostMapping(name = "collect/listFavoriteByUserId", value = "collect/listFavoriteByUserId")
    List<QuestionDO> listFavoriteByUserId(@RequestBody UserDTO userDTO);

    /**
     * 服务调用
     *
     * @param userDTO 用户信息
     * @return List<FollowUserDO>
     */
    @PostMapping(name = "follow/listUserFollowByUserId", value = "follow/listUserFollowByUserId")
    List<FollowUserDO> listUserFollowByUserId(@RequestBody UserDTO userDTO);

    /**
     * 服务调用
     *
     * @param userDTO 用户信息
     * @return List<FollowUserDO>
     */
    @PostMapping(name = "follow/listUserFansByUserId", value = "follow/listUserFansByUserId")
    List<FollowUserDO> listUserFansByUserId(@RequestBody UserDTO userDTO);


}
