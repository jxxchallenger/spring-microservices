package com.thoughtmechanix.licenses.config;

import java.util.Collections;
import java.util.List;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.web.client.RestTemplate;

import com.thoughtmechanix.licenses.utils.UserContextFilter;
import com.thoughtmechanix.licenses.utils.UserContextInterceptor;

@Configuration
public class WebConfig {

    @LoadBalanced
    @Bean
    public RestTemplate getRestTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        List<ClientHttpRequestInterceptor> interceptors = restTemplate.getInterceptors();
        if(interceptors == null) {
            interceptors = Collections.singletonList(new UserContextInterceptor());
        }
        else {
            interceptors.add(new UserContextInterceptor());
        }
        //只适用于discovery，rest方式原生Ribbon包装RestTemplate, Feign不生效
        restTemplate.setInterceptors(interceptors);
        return restTemplate;
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
