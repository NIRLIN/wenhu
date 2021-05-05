package org.wenhu.people.collect.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wenhu.common.pojo.DO.CollectDO;
import org.wenhu.common.pojo.DTO.AnswerDTO;
import org.wenhu.common.util.Result;
import org.wenhu.common.util.SnowflakeUtils;
import org.wenhu.database.dao.CollectDao;
import org.wenhu.people.collect.service.CollectService;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;

/**
 * @author NIRLIN
 * @version 1.0
 * @date 2021/3/25
 */

@Service
public class CollectServiceImpl implements CollectService {
    @Autowired
    private CollectDao collectDao;

    @GlobalTransactional
    @Override
    public Result<HashMap<String, Object>> collectService(String userId) {
        List<AnswerDTO> collectAnswer = collectDao.listCollect(userId);
        HashMap<String, Object> hashMap = new HashMap<>(5);
        hashMap.put("answer", collectAnswer);
        return Result.succeed(hashMap);
    }

    @GlobalTransactional
    @Override
    public Result<HashMap<String, Object>> userCollectAnswer(String userId, String answerId) {
        QueryWrapper<CollectDO> collectQueryWrapper = new QueryWrapper<>();
        //查询是否逻辑删除，避免数据重复插入
        collectQueryWrapper
                .eq("user_id", userId)
                .eq("answer_id", answerId);
        CollectDO collectDO = collectDao.selectOne(collectQueryWrapper);
        HashMap<String, Object> hashMap = new HashMap<>(2);
        //如果不存在则增加记录
        if (collectDO == null) {
            collectDO = new CollectDO();
            collectDO.setId(String.valueOf(SnowflakeUtils.genId()));
            collectDO.setAnswerId(answerId);
            collectDO.setUserId(userId);
            collectDO.setCreateTime(LocalDateTime.now());
            collectDO.setUpdateTime(LocalDateTime.now());
            collectDO.setIsDeleted(0);
            int insert = collectDao.insert(collectDO);
            if (insert == 1) {
                hashMap.put("collectResult", true);
            } else {
                hashMap.put("collectResult", false);
            }
        } else {
            //如果存在则修改
            collectDO.setUpdateTime(LocalDateTime.now());
            if (collectDO.getIsDeleted() == 1) {
                collectDO.setIsDeleted(0);
                hashMap.put("collectResult", true);

            } else if (collectDO.getIsDeleted() == 0) {
                collectDO.setIsDeleted(1);
                hashMap.put("collectResult", false);
            }
            int insert = collectDao.updateById(collectDO);

        }
        return Result.succeed(hashMap);
    }

}
