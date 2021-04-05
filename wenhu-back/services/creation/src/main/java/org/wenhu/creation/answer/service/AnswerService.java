package org.wenhu.creation.answer.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.wenhu.common.pojo.DO.AnswerArticleDO;
import org.wenhu.common.pojo.DTO.AnswerArticleDTO;
import org.wenhu.common.util.Result;

import java.util.HashMap;
import java.util.List;

/**
 * @author NIRLIN
 * @version 1.0
 * @date 2021/3/25
 */
public interface AnswerService {

    Result<Integer> countAnswerByQuestionId(String questionId);

    Result<String> saveAnswer(String userId, String questionId, String content);

    Result<List<AnswerArticleDTO>> listAnswerByHeat(String questionId, String page);

    Result<List<AnswerArticleDTO>> listAnswerByTime(String questionId, String page);

    Result<List<AnswerArticleDTO>> listAnswer(String page, QueryWrapper<AnswerArticleDO> queryWrapper);

    Result<List<AnswerArticleDTO>> listAnswerByUserId(String userId, String type);

    Result<HashMap<String, Object>> getUserAgreeAndCollectAnswer(String userId, String answerId);

    Result<HashMap<String, Object>> userAgreeAnswer(String userId, String answerId);

    Result<HashMap<String, Object>> userOpposeAnswer(String userId, String answerId);

    Result<HashMap<String, Object>> getAnswerByAnswerId(String answerId);
}
