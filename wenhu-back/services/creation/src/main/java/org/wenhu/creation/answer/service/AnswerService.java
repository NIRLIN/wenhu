package org.wenhu.creation.answer.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.wenhu.common.pojo.DO.AnswerDO;
import org.wenhu.common.pojo.DTO.UserDTO;
import org.wenhu.common.pojo.VO.AnswerVO;
import org.wenhu.common.util.Result;

import java.util.List;

/**
 * @author NIRLIN
 * @version 1.0
 * @date 2021/3/25
 */
public interface AnswerService {

    Result<Integer> countAnswerByQuestionId(String questionId);

    Result<String> saveAnswer(String userId, String questionId, String content);

    Result<List<AnswerVO>> listAnswerByHeat(String questionId, String page);

    Result<List<AnswerVO>> listAnswerByTime(String questionId, String page);

    Result<List<AnswerVO>> listAnswer(String page, QueryWrapper<AnswerDO> queryWrapper);


    Result<List<AnswerVO>> listAnswerByUserId(String userId, String type);
}
