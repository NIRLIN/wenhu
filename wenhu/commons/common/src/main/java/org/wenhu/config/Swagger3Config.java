package org.wenhu.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.ArrayList;

/**
 * @author NIRLIN
 * @version 1.0
 * @date 2021/3/15
 */

@Configuration
@EnableOpenApi
public class Swagger3Config {
    @Bean
    public Docket docket() {
        return new Docket(DocumentationType.OAS_30)
                .apiInfo(apiInfo());
    }

    /**
     * 页面基础信息
     */
    private ApiInfo apiInfo() {
        // 作者信息
        Contact contact = new Contact("NIRLIN", "https://www.wenhu.space", "2767075474@qq.com");
        return new ApiInfo(
                "闻乎的接口文档",
                "闻乎项目",
                "1.0",
                "https://www.wenhu.space/",
                contact,
                "MIT",
                "https://gitee.com/NIRLIN/wenhu/blob/master/LICENSE",
                new ArrayList<>());
    }

}
