package org.wenhu.creation.classify.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wenhu.common.pojo.DO.ClassifyDO;
import org.wenhu.creation.classify.service.ClassifyService;
import org.wenhu.database.dao.ClassifyDao;

import java.util.List;

/**
 * @author NIRLIN
 * @version 1.0
 * @date 2021/5/2
 */

@Service
public class ClassifyServiceImpl implements ClassifyService {
    @Autowired
    private ClassifyDao classifyDao;

    @Override
    public List<ClassifyDO> listClassify() {
        QueryWrapper<ClassifyDO> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("is_deleted", 0);
        return classifyDao.selectList(queryWrapper);
    }
}
