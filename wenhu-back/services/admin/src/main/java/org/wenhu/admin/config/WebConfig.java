package org.wenhu.admin.config;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author NIRLIN
 * @version 1.0
 * @date 2021/4/7
 */

@Component
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //静态资源过滤器
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
    }
}
