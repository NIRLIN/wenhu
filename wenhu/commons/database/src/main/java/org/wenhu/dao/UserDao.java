package org.wenhu.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;
import org.wenhu.pojo.DO.UserDO;

/**
 * @author NIRLIN
 * @version 1.0
 * @date 2021/3/16
 */

@Mapper
@Component
public interface UserDao extends BaseMapper<UserDO> {
}
