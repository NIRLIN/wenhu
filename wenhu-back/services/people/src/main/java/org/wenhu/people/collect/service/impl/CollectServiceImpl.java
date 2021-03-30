package org.wenhu.people.collect.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wenhu.common.pojo.DO.CollectAnswerDO;
import org.wenhu.common.pojo.DO.CollectArticleDO;
import org.wenhu.common.pojo.DTO.UserDTO;
import org.wenhu.common.util.Result;
import org.wenhu.database.dao.CollectAnswerDao;
import org.wenhu.database.dao.CollectArticleDao;
import org.wenhu.people.collect.service.CollectService;

import javax.print.attribute.standard.NumberUp;
import java.util.HashMap;
import java.util.List;

/**
 * @author NIRLIN
 * @version 1.0
 * @date 2021/3/25
 */

@Service
public class CollectServiceImpl implements CollectService {
    @Autowired
    private CollectAnswerDao collectAnswerDao;
    @Autowired
    private CollectArticleDao collectArticleDao;

    @Override
    public Result<HashMap<String, Object>> collectService(String userId) {
        QueryWrapper<CollectAnswerDO> collectAnswerDoQueryWrapper = new QueryWrapper<>();
        collectAnswerDoQueryWrapper.eq(userId!= null,"user_id",userId);
        List<CollectAnswerDO> collectAnswerDoS = collectAnswerDao.selectList(collectAnswerDoQueryWrapper);
        
        QueryWrapper<CollectArticleDO> collectArticleDoQueryWrapper = new QueryWrapper<>();
        collectArticleDoQueryWrapper.eq(userId!= null,"user_id",userId);
        List<CollectArticleDO> collectArticleDoS = collectArticleDao.selectList(collectArticleDoQueryWrapper);
        HashMap<String,Object> hashMap=new HashMap<>();
        hashMap.put("answer",collectAnswerDoS);
        hashMap.put("article",collectArticleDoS);
        return Result.succeed(hashMap);
     }
}
