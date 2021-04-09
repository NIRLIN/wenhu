package org.wenhu.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author NIRLIN
 * @version 1.0
 * @date 2021/4/6
 */
@Controller
public class TestController {
    @RequestMapping("/")
    public String index(){
        return "view/index";
    }
    @RequestMapping("test")
    public String test(){
        return "view/index";
    }
}
