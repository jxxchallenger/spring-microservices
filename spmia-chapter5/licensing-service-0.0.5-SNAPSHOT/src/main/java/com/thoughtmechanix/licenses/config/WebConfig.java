package com.thoughtmechanix.licenses.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.web.client.RestTemplate;

import com.thoughtmechanix.licenses.utils.UserContextFilter;

@Configuration
public class WebConfig {

    @LoadBalanced
    @Bean
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }
    
    @Bean
    @Order
    public FilterRegistrationBean<UserContextFilter> userContextFilter() {
        FilterRegistrationBean<UserContextFilter> filterRegistrationBean = new FilterRegistrationBean<UserContextFilter>();
        filterRegistrationBean.setFilter(new UserContextFilter());
        //filterRegistrationBean.addUrlPatterns("/v1/organizations/*/licenses");
        
        return filterRegistrationBean;
    }
}
