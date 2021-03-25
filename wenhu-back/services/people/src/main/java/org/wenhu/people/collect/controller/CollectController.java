package org.wenhu.people.collect.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.wenhu.common.pojo.DO.CollectDO;
import org.wenhu.common.pojo.DTO.UserDTO;
import org.wenhu.people.collect.service.impl.CollectServiceImpl;

import java.util.List;

/**
 * @author NIRLIN
 * @version 1.0
 * @date 2021/3/25
 */
@RestController
@RequestMapping(name = "collect", value = "collect")
public class CollectController {
    @Autowired
    private CollectServiceImpl collectService;

    @PostMapping(name = "listFavoriteByUserId", value = "listFavoriteByUserId")
    List<CollectDO> listFavoriteByUserId(@RequestBody UserDTO userDTO) {
        return collectService.listFavoriteByUserId(userDTO);
    }

}