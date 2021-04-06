package org.wenhu.common.pojo.DTO;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author NIRLIN
 * @version 1.0
 * @date 2021/3/25
 */
@Data
@Accessors(chain = true)

public class CollectDTO {
    /**
     * 收藏id
     */

    private String id;

    /**
     * 用户id
     */
    private String userId;

    /**
     * 收藏夹id
     */
    private String favoriteId;

    /**
     * 内容id
     */
    private String contentId;

}
