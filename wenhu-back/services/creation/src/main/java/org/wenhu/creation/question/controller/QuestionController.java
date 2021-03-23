package org.wenhu.creation.question.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.wenhu.creation.question.service.impl.QuestionServiceImpl;
import org.wenhu.pojo.DTO.QuestionDTO;
import org.wenhu.util.Result;

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
        String title = (String) objectMap.get("title");
        String description = (String) objectMap.get("description");
        String menderId = (String) objectMap.get("mender_id");
        QuestionDTO questionDTO = new QuestionDTO();
        questionDTO.setTitle(title);
        questionDTO.setDescription(description);
        Result<String> stringResult = questionService.saveQuestion(questionDTO, menderId);
        System.out.println(stringResult);
        return stringResult;
    }
}
