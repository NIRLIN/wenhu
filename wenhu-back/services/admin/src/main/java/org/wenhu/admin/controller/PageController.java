package org.wenhu.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author NIRLIN
 * @version 1.0
 * @date 2021/4/8
 */
@Controller
public class PageController {

    @RequestMapping({"login", "login.html"})
    public String login(Model model) {
        return "view/login";
    }

    @RequestMapping({"index", "index.html"})
    public String index() {
        return "view/index";
    }

    @GetMapping("/admin/people.html")
    public String people() {
        return "view/people";
    }

    @RequestMapping({"changePassword.html"})
    public String changePassword() {
        return "view/changePassword";
    }

    @GetMapping("listUserNoBanned.html")
    public String listUserNoBanned() {
        return "view/listUserNoBanned";
    }

    @GetMapping("listUserIsBanned.html")
    public String listUserIsBanned() {
        return "view/listUserIsBanned";
    }

    @GetMapping("listQuestion.html")
    public String listQuestion() {
        return "view/listQuestion";
    }

    @GetMapping("listAnswer.html")
    public String listAnswer() {
        return "view/listAnswer";
    }


    @GetMapping("listSensitivity.html")
    public String listSensitivity() {
        return "view/listSensitivity";
    }

    @GetMapping("listAdminIsBaned.html")
    public String listAdminIsBaned() {
        return "view/listAdminIsBaned";
    }

    @GetMapping("listAdminNoBaned.html")
    public String listAdminNoBaned() {
        return "view/listAdminNoBaned";
    }

    @GetMapping("listPosition.html")
    public String listPosition() {
        return "view/listPosition";
    }


}
