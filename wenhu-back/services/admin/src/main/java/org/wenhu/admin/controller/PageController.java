package org.wenhu.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author NIRLIN
 * @version 1.0
 * @date 2021/4/8
 */
@Controller
public class PageController {
    @RequestMapping("login")
    public String login() {
        return "view/login";
    }

    @GetMapping("userList.html")
    public String userList() {
        return "view/userList";
    }

}
