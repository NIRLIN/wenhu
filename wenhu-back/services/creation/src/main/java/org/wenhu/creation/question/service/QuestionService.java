package org.wenhu.creation.question.service;

import org.springframework.web.bind.annotation.RequestBody;
import org.wenhu.common.pojo.DO.QuestionDO;
import org.wenhu.common.pojo.DTO.QuestionLogDTO;
import org.wenhu.common.pojo.DTO.UserDTO;
import org.wenhu.common.util.Result;

import java.util.List;
import java.util.Map;

/**
 * @author NIRLIN
 * @version 1.0
 * @date 2021/3/22
 */

public interface QuestionService {
    /**
     * 保存问题
     *
     * @param questionDO questionDO
     * @param menderId   保存者id
     * @return 操作结果
     */
    Result<String> saveQuestion(QuestionDO questionDO, String menderId);

    /**
     * 通过id获得问题
     *
     * @param id id
     * @return 操作结果
     */
    Result<QuestionDO> getQuestionById(String id);

    /**
     * 通过用户id查询问题列表
     *
     * @param userDTO 用户信息
     * @return 操作结果
     */
    Result<List<QuestionDO>> listQuestionByUserId(@RequestBody UserDTO userDTO);

    /**
     * 用户关注的问题列表
     *
     * @param objectMap 传入数据
     * @return 操作结果
     */
    Result<Integer> userFollowQuestion(Map<String, Object> objectMap);

    /**
     * 关注问题
     *
     * @param objectMap 传入数据
     * @return 操作结果
     */
    Result<Integer> followQuestion(Map<String, Object> objectMap);

    /**
     * 问题日志列表
     *
     * @param objectMap 传入数据
     * @return 操作结果
     */
    Result<List<QuestionLogDTO>> listQuestionLog(Map<String, Object> objectMap);

    /**
     * 修改问题
     *
     * @param objectMap 传入数据
     * @return 操作结果
     */
    Result<Integer> updateQuestion(Map<String, Object> objectMap);
}
