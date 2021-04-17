package org.wenhu.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wenhu.admin.service.PositionService;
import org.wenhu.common.pojo.DO.PositionDO;
import org.wenhu.database.dao.PositionDao;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;

/**
 * @author NIRLIN
 * @version 1.0
 * @date 2021/4/17
 */

@Service
public class PositionServiceImpl implements PositionService {
    @Autowired
    private PositionDao positionDao;


    @Override
    public HashMap<String, Object> listPosition() {
        QueryWrapper<PositionDO> queryWrapper = new QueryWrapper<>();
        queryWrapper
                .orderByDesc("update_time");
        List<PositionDO> listPosition = positionDao.selectList(queryWrapper);
        Integer listPositionCount = positionDao.selectCount(queryWrapper);
        HashMap<String, Object> hashMap = new HashMap<>(2);
        hashMap.put("listPosition", listPosition);
        hashMap.put("listPositionCount", listPositionCount);
        return hashMap;
    }

    @Override
    public HashMap<String, Object> savePosition(PositionDO positionDO) {
        positionDO.setUpdateTime(LocalDateTime.now());
        int i = positionDao.updateById(positionDO);
        HashMap<String, Object> hashMap = new HashMap<>(1);
        if (i == 1) {
            hashMap.put("savePositionResult", true);
        } else {
            hashMap.put("savePositionResult", false);
        }
        return hashMap;
    }
}
