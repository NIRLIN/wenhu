package org.wenhu.creation.question.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.wenhu.common.pojo.DO.QuestionDO;
import org.wenhu.common.pojo.DTO.QuestionLogDTO;
import org.wenhu.common.pojo.DTO.UserDTO;
import org.wenhu.common.util.Result;
import org.wenhu.creation.question.service.impl.QuestionServiceImpl;

import java.util.List;
import java.util.Map;

/**
 * @author NIRLIN
 * @version 1.0
 * @date 2021/3/23
 */

@RestController
@RequestMapping(name = "question", value = "question")
public class QuestionController {
    @Autowired
    private QuestionServiceImpl questionService;


    @PostMapping(name = "saveQuestion", value = "saveQuestion")
    public Result<String> saveQuestion(@RequestBody Map<String, Object> objectMap) {
        String classifyId = (String) objectMap.get("classifyId");
        String title = (String) objectMap.get("title");
        String description = (String) objectMap.get("description");
        String menderId = (String) objectMap.get("menderId");
        QuestionDO questionDO = new QuestionDO()
                .setClassifyId(classifyId)
                .setTitle(title)
                .setDescription(description);
        Result<String> stringResult = questionService.saveQuestion(questionDO, menderId);
        System.out.println(stringResult);
        return stringResult;
    }

    @PostMapping(name = "updateQuestion", value = "updateQuestion")
    public Result<Integer> updateQuestion(@RequestBody Map<String, Object> objectMap) {
        return questionService.updateQuestion(objectMap);
    }

    @PostMapping(name = "getQuestionById", value = "getQuestionById")
    public Result<QuestionDO> getQuestionById(@RequestBody Map<String, Object> objectMap) {
        String id = (String) objectMap.get("id");
        return questionService.getQuestionById(id);
    }

    @PostMapping(name = "listQuestionByUserId", value = "listQuestionByUserId")
    public Result<List<QuestionDO>> listQuestionByUserId(@RequestBody UserDTO userDTO) {
        return questionService.listQuestionByUserId(userDTO);
    }

    @PostMapping(name = "listQuestionLog", value = "listQuestionLog")
    public Result<List<QuestionLogDTO>> listQuestionLog(@RequestBody Map<String, Object> objectMap) {
        return questionService.listQuestionLog(objectMap);
    }

    @PostMapping(name = "userFollowQuestion", value = "userFollowQuestion")
    public Result<Integer> userFollowQuestion(@RequestBody Map<String, Object> objectMap) {
        return questionService.userFollowQuestion(objectMap);
    }

    @PostMapping(name = "followQuestion", value = "followQuestion")
    public Result<Integer> followQuestion(@RequestBody Map<String, Object> objectMap) {
        return questionService.followQuestion(objectMap);
    }


}
