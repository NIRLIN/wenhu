package org.wenhu.admin.config;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author NIRLIN
 * @version 1.0
 * @date 2021/4/7
 */

@Component
public class LoginHandlerInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Object userId = request.getSession().getAttribute("userId");
        Object username = request.getSession().getAttribute("username");
        if (userId == null || username == null) {
            request.setAttribute("loginInfo", "无权限，请登录！");
            request.getRequestDispatcher("/login.html").forward(request, response);
            return false;
        } else {
            request.removeAttribute("loginInfo");
            return true;
        }
    }
}
