package org.wenhu.database.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import org.wenhu.common.pojo.DO.ClassifyDO;

/**
 * @author NIRLIN
 * @version 1.0
 * @date 2021/4/29
 */

@Mapper
@Repository
public interface ClassifyDao extends BaseMapper<ClassifyDO> {
}
