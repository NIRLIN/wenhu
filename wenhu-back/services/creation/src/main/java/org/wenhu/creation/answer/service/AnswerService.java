package org.wenhu.creation.answer.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.wenhu.common.pojo.DO.AnswerDO;
import org.wenhu.common.pojo.DTO.AnswerDTO;
import org.wenhu.common.util.Result;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author NIRLIN
 * @version 1.0
 * @date 2021/3/25
 */
public interface AnswerService {

    /**
     * 统计回答数量
     *
     * @param questionId 回答id
     * @return 数量
     */
    Result<Integer> countAnswerByQuestionId(String questionId);

    /**
     * 保存回答
     *
     * @param userId     用户id
     * @param questionId 问题id
     * @param content    回答内容
     * @return 操作结果
     */
    Result<String> saveAnswer(String userId, String questionId, String content);

    /**
     * 回答列表，排序查询
     *
     * @param questionId 问题id
     * @param page       分页
     * @return 操作结果
     */
    Result<List<AnswerDTO>> listAnswerByHeat(String questionId, String page);

    /**
     * 回答列表，排序查询
     *
     * @param questionId 问题id
     * @param page       分页
     * @return 操作结果
     */
    Result<List<AnswerDTO>> listAnswerByTime(String questionId, String page);

    /**
     * 回答列表，排序查询
     *
     * @param queryWrapper 条件构造
     * @param page         分页
     * @return 操作结果
     */
    Result<List<AnswerDTO>> listAnswer(String page, QueryWrapper<AnswerDO> queryWrapper);

    /**
     * 通过用户id查询回答
     *
     * @param userId 用户id
     * @param type   回答
     * @return 操作结果
     */
    Result<List<AnswerDTO>> listAnswerByUserId(String userId, String type);

    /**
     * 查询用户是否赞同或收藏某回答
     *
     * @param userId   用户id
     * @param answerId 回答id
     * @return 操作结果
     */
    Result<HashMap<String, Object>> getUserAgreeAndCollectAnswer(String userId, String answerId);

    /**
     * 用户赞同回答
     *
     * @param userId   用户id
     * @param answerId 回答id
     * @return 操作结果
     */
    Result<HashMap<String, Object>> userAgreeAnswer(String userId, String answerId);

    /**
     * 用户取消赞同回答
     *
     * @param userId   用户id
     * @param answerId 回答id
     * @return 操作结果
     */
    Result<HashMap<String, Object>> userOpposeAnswer(String userId, String answerId);

    /**
     * 通过id获取回答内容
     *
     * @param answerId 回答id
     * @return 操作结果
     */
    Result<HashMap<String, Object>> getAnswerByAnswerId(String answerId);

    /**
     * 修改回答信息
     *
     * @param objectMap 传入数据
     * @return 操作结果
     */
    Result<Integer> updateAnswer(Map<String, Object> objectMap);
}
