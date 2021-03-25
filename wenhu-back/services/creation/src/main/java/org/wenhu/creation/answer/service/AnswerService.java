package org.wenhu.creation.answer.service;

import org.wenhu.common.pojo.DO.AnswerDO;
import org.wenhu.common.pojo.DTO.UserDTO;

import java.util.List;

/**
 * @author NIRLIN
 * @version 1.0
 * @date 2021/3/25
 */
public interface AnswerService {
    List<AnswerDO> listAnswerByUserId(UserDTO userDTO);
}
