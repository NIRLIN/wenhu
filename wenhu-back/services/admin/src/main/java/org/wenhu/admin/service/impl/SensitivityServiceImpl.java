package org.wenhu.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wenhu.admin.service.SensitivityService;
import org.wenhu.common.pojo.DO.SensitivityDO;
import org.wenhu.common.util.Result;
import org.wenhu.common.util.SnowflakeUtils;
import org.wenhu.database.dao.SensitivityDao;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author NIRLIN
 * @version 1.0
 * @date 2021/4/17
 */

@Service
public class SensitivityServiceImpl implements SensitivityService {
    @Autowired
    private SensitivityDao sensitivityDao;

    @Override
    public HashMap<String, Object> listSensitivity(Map<String, Object> objectMap) {
        Integer page = (Integer) objectMap.get("page");
        QueryWrapper<SensitivityDO> queryWrapper = new QueryWrapper<>();
        queryWrapper
                .orderByDesc("update_time");
        List<SensitivityDO> listSensitivity = listSensitivityByPage(page, queryWrapper);
        //重置条件
        queryWrapper = new QueryWrapper<>();
        Integer countSensitivity = countSensitivity(queryWrapper);
        HashMap<String, Object> hashMap = new HashMap<>(2);
        hashMap.put("listSensitivity", listSensitivity);
        hashMap.put("countSensitivity", countSensitivity);
        return hashMap;
    }

    @Override
    public HashMap<String, Object> listSensitivitySearch(Map<String, Object> objectMap) {

        Integer page = (Integer) objectMap.get("page");
        String sensitivityId = (String) objectMap.get("sensitivityId");
        String value = (String) objectMap.get("value");
        String substitute = (String) objectMap.get("substitute");
        QueryWrapper<SensitivityDO> queryWrapper = new QueryWrapper<>();
        queryWrapper
                .orderByDesc("update_time")
                .like(sensitivityId != null, "id", sensitivityId)
                .like(value != null, "value", value)
                .like(substitute != null, "substitute", substitute);
        //分页查询
        List<SensitivityDO> listSensitivitySearch = listSensitivityByPage(page, queryWrapper);
        //查询总页数
        Integer listSensitivitySearchCount = countSensitivity(queryWrapper);
        HashMap<String, Object> hashMap = new HashMap<>(2);
        hashMap.put("listSensitivitySearch", listSensitivitySearch);
        hashMap.put("listSensitivitySearchCount", listSensitivitySearchCount);
        return hashMap;
    }

    @Override
    public HashMap<String, Object> updateSensitivity(SensitivityDO sensitivityDO) {
        sensitivityDO.setUpdateTime(LocalDateTime.now());
        int i = sensitivityDao.updateById(sensitivityDO);
        HashMap<String, Object> hashMap = new HashMap<>(1);
        if (i == 1) {
            hashMap.put("updateSensitivityResult", true);
        } else {
            hashMap.put("updateSensitivityResult", false);
        }
        return hashMap;
    }

    @Override
    public Result<Object> saveSensitivity(SensitivityDO sensitivityDO) {
        sensitivityDO
                .setId(String.valueOf(SnowflakeUtils.genId()))
                .setUpdateTime(LocalDateTime.now())
                .setIsDeleted(0);
        int insert = sensitivityDao.insert(sensitivityDO);
        if (insert == 1) {
            return Result.succeed();
        } else {
            return Result.failed();
        }
    }

    private List<SensitivityDO> listSensitivityByPage(Integer page, QueryWrapper<SensitivityDO> queryWrapper) {
        //分页查询
        Page<SensitivityDO> doPage = new Page<>(page, 10);
        Page<SensitivityDO> selectPage = sensitivityDao.selectPage(doPage, queryWrapper);
        return selectPage.getRecords();
    }

    private Integer countSensitivity(QueryWrapper<SensitivityDO> queryWrapper) {
        //查询总数
        return sensitivityDao.selectCount(queryWrapper);
    }

}
