package org.wenhu.creation.answer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.wenhu.common.pojo.DTO.AnswerArticleDTO;
import org.wenhu.common.util.Result;
import org.wenhu.creation.answer.service.impl.AnswerServiceImpl;

import java.util.HashMap;
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
        String questionId = (String) objectMap.get("id");
        return answerService.countAnswerByQuestionId(questionId);
    }


    @PostMapping(name = "listAnswerByHeat", value = "listAnswerByHeat")
    Result<List<AnswerArticleDTO>> listAnswerByHeat(@RequestBody Map<String, Object> objectMap) {
        String questionId = (String) objectMap.get("questionId");
        Integer page = (Integer) objectMap.get("page");
        return answerService.listAnswerByHeat(questionId, String.valueOf(page));
    }
    @PostMapping(name = "listAnswerByTime", value = "listAnswerByTime")
    Result<List<AnswerArticleDTO>> listAnswerByTime(@RequestBody Map<String, Object> objectMap) {
        String questionId = (String) objectMap.get("questionId");
        Integer page = (Integer) objectMap.get("page");
        return answerService.listAnswerByTime(questionId, String.valueOf(page));
    }


    @PostMapping(name = "saveAnswer", value = "saveAnswer")
    Result<String> saveAnswer(@RequestBody Map<String, Object> objectMap) {
        String userId = (String) objectMap.get("userId");
        String questionId = (String) objectMap.get("questionId");
        String content = (String) objectMap.get("content");
        return answerService.saveAnswer(userId, questionId, content);
    }

    @PostMapping(name = "getAnswerByAnswerId", value = "getAnswerByAnswerId")
    Result<HashMap<String,Object>> getAnswerByAnswerId(@RequestBody Map<String, Object> objectMap) {
        String answerId = (String) objectMap.get("answerId");
        return answerService.getAnswerByAnswerId(answerId);
    }



    @PostMapping(name = "listAnswerByUserId", value = "listAnswerByUserId")
    Result<List<AnswerArticleDTO>> listAnswerByUserId(@RequestParam String userId, @RequestParam String type) {
        return answerService.listAnswerByUserId(userId, type);
    }

    @PostMapping(name = "getUserAgreeAndCollectAnswer", value = "getUserAgreeAndCollectAnswer")
    Result<HashMap<String, Object>> getUserAgreeAndCollectAnswer(@RequestBody Map<String, Object> objectMap) {
        String userId = (String) objectMap.get("userId");
        String answerId = (String) objectMap.get("answerId");
        return answerService.getUserAgreeAndCollectAnswer(userId, answerId);
    }

    @PostMapping(name = "userAgreeAnswer", value = "userAgreeAnswer")
    Result<HashMap<String, Object>> saveAgreeAnswerByUserId(@RequestBody Map<String, Object> objectMap) {
        String userId = (String) objectMap.get("userId");
        String answerId = (String) objectMap.get("answerId");
        return answerService.userAgreeAnswer(userId, answerId);
    }
    @PostMapping(name = "userOpposeAnswer", value = "userOpposeAnswer")
    Result<HashMap<String, Object>> userOpposeAnswer(@RequestBody Map<String, Object> objectMap) {
        String userId = (String) objectMap.get("userId");
        String answerId = (String) objectMap.get("answerId");
        return answerService.userOpposeAnswer(userId, answerId);
    }

}
