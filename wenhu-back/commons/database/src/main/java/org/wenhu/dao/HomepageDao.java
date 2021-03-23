package org.wenhu.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.wenhu.pojo.DO.HomepageDO;

/**
 * @author NIRLIN
 * @version 1.0
 * @date 2021/3/16
 */

@Mapper
@Repository
public interface HomepageDao extends BaseMapper<HomepageDO> {
}
