package org.wenhu.creation.answer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.wenhu.common.pojo.DO.AnswerDO;
import org.wenhu.common.pojo.DTO.UserDTO;
import org.wenhu.common.pojo.VO.AnswerVO;
import org.wenhu.common.util.Result;
import org.wenhu.creation.answer.service.impl.AnswerServiceImpl;

import java.util.List;
import java.util.Map;

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

    @PostMapping(name = "countAnswerByQuestionId", value = "countAnswerByQuestionId")
    Result<Integer> countAnswerByQuestionId(@RequestBody Map<String, Object> objectMap) {
        String questionId= (String) objectMap.get("id");
        return answerService.countAnswerByQuestionId(questionId);
    }
    @PostMapping(name = "listAnswerByUserId", value = "listAnswerByUserId")
    List<AnswerDO> listAnswerByUserId(@RequestBody UserDTO userDTO) {
        return answerService.listAnswerByUserId(userDTO);
    }

    @PostMapping(name = "listAnswerByHeat", value = "listAnswerByHeat")
    Result<List<AnswerVO>> listAnswerByHeat(@RequestBody Map<String, Object> objectMap) {
        String questionId= (String) objectMap.get("questionId");
        Integer page = (Integer) objectMap.get("page");
        return answerService.listAnswerByHeat(questionId, String.valueOf(page));
    }
    @PostMapping(name = "listAnswerByTime", value = "listAnswerByTime")
    Result<List<AnswerVO>> listAnswerByTime(@RequestBody Map<String, Object> objectMap) {
        String questionId= (String) objectMap.get("questionId");
        Integer page = (Integer) objectMap.get("page");
        return answerService.listAnswerByTime(questionId, String.valueOf(page));
    }
    @PostMapping(name = "saveAnswer", value = "saveAnswer")
    Result<String> saveAnswer(@RequestBody Map<String, Object> objectMap) {
        String userId= (String) objectMap.get("userId");
        String questionId= (String) objectMap.get("questionId");
        String content= (String) objectMap.get("content");
        return answerService.saveAnswer(userId, questionId, content);
    }


}
