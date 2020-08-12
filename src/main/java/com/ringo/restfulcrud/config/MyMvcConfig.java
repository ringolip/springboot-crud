package com.ringo.restfulcrud.config;

import com.ringo.restfulcrud.component.LoginHandlerInterceptor;
import com.ringo.restfulcrud.component.MyLocaleResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @author ringo
 * @version 1.0
 * @date 2020/8/10 18:19
 */

// WebMvcConfigurerAdapter扩展SpringMVC的功能
@Configuration
public class MyMvcConfig extends WebMvcConfigurerAdapter {
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        // 浏览器发送 /ringo 请求来到 success
        registry.addViewController("/ringo").setViewName(("success"));
    }

    @Bean // 将组建注册在容器
    public WebMvcConfigurerAdapter webMvcConfigurerAdapter() {
        WebMvcConfigurerAdapter adapter = new WebMvcConfigurerAdapter() {
            @Override
            public void addViewControllers(ViewControllerRegistry registry) {
                registry.addViewController("/").setViewName("login");
                registry.addViewController("/index.html").setViewName("login");
                registry.addViewController("/main.html").setViewName("dashboard");
            }

            // 注册拦截器
            @Override
            public void addInterceptors(InterceptorRegistry registry) {
//                super.addInterceptors(registry);
                // 拦截请求
                registry.addInterceptor(new LoginHandlerInterceptor()).addPathPatterns("/**").
                        // 不拦截的请求
                        excludePathPatterns("/index.html", "/", "/user/login");
            }
        };
        return adapter;
    }

    /**
     * 将自定义国际化信息解析器加入容器
     * @return
     */
    @Bean
    public LocaleResolver localeResolver(){
        return new MyLocaleResolver();
    }
}
