package org.wenhu.creation.answer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.wenhu.common.pojo.DO.AnswerDO;
import org.wenhu.common.pojo.DTO.UserDTO;
import org.wenhu.creation.answer.service.impl.AnswerServiceImpl;

import java.util.List;

/**
 * @author NIRLIN
 * @version 1.0
 * @date 2021/3/25
 */

@RestController
@RequestMapping(name = "answer", value = "answer")
public class AnswerController {
    @Autowired
    private AnswerServiceImpl answerService;

    @PostMapping(name = "listAnswerByUserId", value = "listAnswerByUserId")
    List<AnswerDO> listAnswerByUserId(@RequestBody UserDTO userDTO) {
        return answerService.listAnswerByUserId(userDTO);
    }
}
