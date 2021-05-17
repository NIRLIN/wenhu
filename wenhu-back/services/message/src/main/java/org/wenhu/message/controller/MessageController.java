package org.wenhu.message.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.wenhu.message.service.impl.MessageServiceImpl;

import java.util.HashMap;

/**
 * @author NIRLIN
 * @version 1.0
 * @date 2021/5/13
 */
@RestController
@RequestMapping("message")
public class MessageController {
    @Autowired
    private MessageServiceImpl messageService;

    @GetMapping("userMessageList")
    public Object userMessageList(String userId) {
        return messageService.userMessageList(userId);
    }

    @PostMapping("saveMessage")
    public Object saveMessage(@RequestBody HashMap<String, Object> hashMap) {
        return messageService.saveMessage(hashMap);
    }
}
