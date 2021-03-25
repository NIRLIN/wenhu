package org.wenhu.database.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import org.wenhu.common.pojo.DO.ReportArticleDO;

/**
 * @author NIRLIN
 * @version 1.0
 * @date 2021/3/25
 */

@Mapper
@Repository
public interface ReportArticleDao extends BaseMapper<ReportArticleDO> {
}
