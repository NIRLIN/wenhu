package org.wenhu.people.follow.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.wenhu.common.pojo.DO.FollowUserDO;
import org.wenhu.common.pojo.DO.QuestionDO;
import org.wenhu.common.pojo.DTO.UserDTO;
import org.wenhu.common.util.Result;
import org.wenhu.people.follow.service.impl.FollowServiceImpl;

import java.util.HashMap;
import java.util.List;

/**
 * @author NIRLIN
 * @version 1.0
 * @date 2021/3/25
 */
@RestController
@RequestMapping(name = "follow", value = "follow")
public class FollowController {
    @Autowired
    private FollowServiceImpl followService;

    public Result<HashMap<String, Object>> listFollowByUserId(String userId) {
        HashMap<String, Object> hashMap = followService.listFollowByUserId(userId);
        return Result.succeed(hashMap);
    }
}
