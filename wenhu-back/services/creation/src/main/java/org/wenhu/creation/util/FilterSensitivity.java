package org.wenhu.creation.util;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.wenhu.common.pojo.DO.SensitivityDO;
import org.wenhu.database.dao.SensitivityDao;

import java.util.List;

/**
 * @author NIRLIN
 * @version 1.0
 * @date 2021/4/26
 */

@Component
public class FilterSensitivity {
    @Autowired
    private SensitivityDao sensitivityDao;

    public String filterSensitiveWord(String checkContent){
        List<SensitivityDO> sensitivityList = sensitivityDao.selectList(new QueryWrapper<>());
        for (SensitivityDO sensitivityDO : sensitivityList) {
            checkContent=checkContent.replaceAll(sensitivityDO.getValue(),sensitivityDO.getSubstitute());
        }
        return checkContent;
    }

}
