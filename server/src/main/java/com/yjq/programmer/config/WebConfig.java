package com.yjq.programmer.config;

import com.yjq.programmer.constant.RuntimeConstant;
import com.yjq.programmer.interceptor.LoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author 17259
 * @version 1.0
 * @date 2024/6/5 16:23
 */

/**
 * Web配置类，实现了WebMvcConfigurer接口，配置拦截器。
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private LoginInterceptor loginInterceptor;

    /**
     * 添加拦截器配置。
     *
     * @param registry 拦截器注册对象
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //搜寻/**所有链接除了RuntimeConstant.loginExcludePathPatterns中的链接
        registry.addInterceptor(loginInterceptor).addPathPatterns("/**").excludePathPatterns(RuntimeConstant.loginExcludePathPatterns);
    }

}
