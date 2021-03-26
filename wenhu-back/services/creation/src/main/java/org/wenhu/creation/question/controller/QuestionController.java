package org.wenhu.creation.question.controller;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.wenhu.common.pojo.DO.QuestionDO;
import org.wenhu.common.pojo.DTO.QuestionDTO;
import org.wenhu.common.pojo.DTO.UserDTO;
import org.wenhu.common.util.AliyunOss;
import org.wenhu.common.util.Result;
import org.wenhu.creation.question.service.impl.QuestionServiceImpl;

import java.io.File;
import java.io.IOException;
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

    @PostMapping(name = "getQuestionById", value = "getQuestionById")
    public Result<QuestionDTO> getQuestionById(@RequestBody Map<String, Object> objectMap) {
        String id = (String) objectMap.get("id");
        return questionService.getQuestionById(id);
    }

    @PostMapping(name = "listQuestionByUserId", value = "listQuestionByUserId")
    public List<QuestionDO> listQuestionByUserId(@RequestBody UserDTO userDTO) {
        return questionService.listQuestionByUserId(userDTO);
    }


}
