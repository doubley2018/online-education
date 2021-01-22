package com.angi.config;

import com.baomidou.mybatisplus.extension.plugins.OptimisticLockerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author angi
 * @date 2021/1/22 19:12
 */
@Configuration
public class OptimisticLockerInterceptorConfig {
    /**
     * 乐观锁拦截器
     * @return OptimisticLockerInterceptor
     */
    @Bean
    public OptimisticLockerInterceptor optimisticLockerInterceptor() {
        return new OptimisticLockerInterceptor();
    }
}
