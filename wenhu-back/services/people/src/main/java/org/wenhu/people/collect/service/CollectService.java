package org.wenhu.people.collect.service;

import org.springframework.web.bind.annotation.RequestBody;
import org.wenhu.common.pojo.DTO.UserDTO;
import org.wenhu.common.util.Result;

import java.util.HashMap;
import java.util.List;

/**
 * @author NIRLIN
 * @version 1.0
 * @date 2021/3/25
 */
public interface CollectService {

    Result<HashMap<String, Object>> collectService(String userId);
}
