package org.wenhu.admin.service;

import org.wenhu.common.pojo.DO.PositionDO;

import java.util.HashMap;

/**
 * @author NIRLIN
 * @version 1.0
 * @date 2021/4/17
 */
public interface PositionService {
    /**
     * 查询职位
     *
     * @return 操作结果
     */
    HashMap<String, Object> listPosition();

    /**
     * 修改职位
     *
     * @param positionDO 传入数据
     * @return 操作结果
     */
    HashMap<String, Object> updatePosition(PositionDO positionDO);

    Object savePosition(PositionDO positionDO);
}
