package org.wenhu.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wenhu.admin.service.ClassifyService;
import org.wenhu.common.pojo.DO.ClassifyDO;
import org.wenhu.common.util.Result;
import org.wenhu.common.util.SnowflakeUtils;
import org.wenhu.database.dao.ClassifyDao;

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
public class ClassifyServiceImpl implements ClassifyService {
    @Autowired
    private ClassifyDao classifyDao;

    @GlobalTransactional
    @Override
    public HashMap<String, Object> listClassify(Map<String, Object> objectMap) {
        Integer page = (Integer) objectMap.get("page");
        QueryWrapper<ClassifyDO> queryWrapper = new QueryWrapper<>();
        queryWrapper
                .orderByDesc("update_time");
        List<ClassifyDO> listClassify = listClassifyByPage(page, queryWrapper);
        //重置条件
        queryWrapper = new QueryWrapper<>();
        Integer countClassify = countClassify(queryWrapper);
        HashMap<String, Object> hashMap = new HashMap<>(2);
        hashMap.put("listClassify", listClassify);
        hashMap.put("countClassify", countClassify);
        return hashMap;
    }

    @GlobalTransactional
    @Override
    public HashMap<String, Object> listClassifySearch(Map<String, Object> objectMap) {

        Integer page = (Integer) objectMap.get("page");
        String id = (String) objectMap.get("id");
        String classifyName = (String) objectMap.get("classifyName");
        QueryWrapper<ClassifyDO> queryWrapper = new QueryWrapper<>();
        queryWrapper
                .orderByDesc("update_time")
                .like(id != null, "id", id)
                .like(classifyName != null, "classify_name", classifyName);
        //分页查询
        List<ClassifyDO> listClassifySearch = listClassifyByPage(page, queryWrapper);
        //查询总页数
        Integer listClassifySearchCount = countClassify(queryWrapper);
        HashMap<String, Object> hashMap = new HashMap<>(2);
        hashMap.put("listClassifySearch", listClassifySearch);
        hashMap.put("listClassifySearchCount", listClassifySearchCount);
        return hashMap;
    }

    @GlobalTransactional
    @Override
    public HashMap<String, Object> updateClassify(ClassifyDO classifyDO) {
        classifyDO.setUpdateTime(LocalDateTime.now());
        int i = classifyDao.updateById(classifyDO);
        HashMap<String, Object> hashMap = new HashMap<>(1);
        if (i == 1) {
            hashMap.put("updateClassifyResult", true);
        } else {
            hashMap.put("updateClassifyResult", false);
        }
        return hashMap;
    }

    @GlobalTransactional
    @Override
    public Result<Object> saveClassify(ClassifyDO classifyDO) {
        classifyDO
                .setId(String.valueOf(SnowflakeUtils.genId()))
                .setUpdateTime(LocalDateTime.now())
                .setIsDeleted(0);
        int insert = classifyDao.insert(classifyDO);
        if (insert == 1) {
            return Result.succeed();
        } else {
            return Result.failed();
        }
    }

    private List<ClassifyDO> listClassifyByPage(Integer page, QueryWrapper<ClassifyDO> queryWrapper) {
        //分页查询
        Page<ClassifyDO> doPage = new Page<>(page, 10);
        Page<ClassifyDO> selectPage = classifyDao.selectPage(doPage, queryWrapper);
        return selectPage.getRecords();
    }

    private Integer countClassify(QueryWrapper<ClassifyDO> queryWrapper) {
        //查询总数
        return classifyDao.selectCount(queryWrapper);
    }

}
