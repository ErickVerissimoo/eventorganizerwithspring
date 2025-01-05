package com.eventorganizerspring.eventorganizer.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.filter.CharacterEncodingFilter;

@Configuration
@EnableAspectJAutoProxy
public class WebConfigs {
@Bean
public ThreadPoolTaskExecutor executor(){
    ThreadPoolTaskExecutor thread = new ThreadPoolTaskExecutor();
    thread.initialize();
    return thread;
}
@Bean
public CharacterEncodingFilter characterEncodingFilter(){
    return new CharacterEncodingFilter("UTF-8", true, true);
}
}
