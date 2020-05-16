package com.yanchong.springcloud.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;

@Configuration
public class FreemakrConfig {

    @Bean
    public FreeMarkerConfigurer freeMarkerConfigurer(){
        FreeMarkerConfigurer freeMarkerConfigurer = new FreeMarkerConfigurer();
        freeMarkerConfigurer.setTemplateLoaderPath("classpath:/freemarkModel/");

        return freeMarkerConfigurer;
    }

    @Bean
    public FreeMarkerViewResolver freeMarkerViewResolver(){

        FreeMarkerViewResolver freeMarkerViewResolver = new FreeMarkerViewResolver();

        freeMarkerViewResolver.setPrefix("");
        freeMarkerViewResolver.setSuffix(".ftl");
        freeMarkerViewResolver.setContentType("text/html; charset=UTF-8");
        return freeMarkerViewResolver;
    }

}
