package org.wenhu.creation.question.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wenhu.dao.QuestionDao;
import org.wenhu.dao.UserDao;

/**
 * @author NIRLIN
 * @version 1.0
 * @date 2021/3/23
 */

@Service
public class QuestionController {
    @Autowired
    QuestionDao questionDao;
    @Autowired
    UserDao userDao;
}
