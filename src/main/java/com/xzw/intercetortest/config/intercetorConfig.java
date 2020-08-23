package com.xzw.intercetortest.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class intercetorConfig {

    @Bean
    public DynamicDSInterceptor dynamicDSInterceptor() {
        return new DynamicDSInterceptor();
    }
}
