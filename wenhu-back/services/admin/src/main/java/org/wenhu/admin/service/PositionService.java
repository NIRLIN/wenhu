package org.wenhu.admin.service;

import org.wenhu.common.pojo.DO.PositionDO;

import java.util.HashMap;

/**
 * @author NIRLIN
 * @version 1.0
 * @date 2021/4/17
 */
public interface PositionService {
    HashMap<String, Object> listPosition();

    HashMap<String, Object> updatePosition(PositionDO positionDO);
}
