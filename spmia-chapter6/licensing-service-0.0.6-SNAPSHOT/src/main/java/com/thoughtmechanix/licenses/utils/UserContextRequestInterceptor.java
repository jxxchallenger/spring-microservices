package com.thoughtmechanix.licenses.utils;

import org.springframework.cloud.openfeign.encoding.BaseRequestInterceptor;
import org.springframework.cloud.openfeign.encoding.FeignClientEncodingProperties;

import feign.RequestTemplate;

public class UserContextRequestInterceptor extends BaseRequestInterceptor {

    public UserContextRequestInterceptor() {
        super(new FeignClientEncodingProperties());
    }

    @Override
    public void apply(RequestTemplate template) {
        this.addHeader(template, UserContext.CORRELATION_ID, UserContextHolder.getContext().getCorrelationId());
        this.addHeader(template, UserContext.AUTH_TOKEN, UserContextHolder.getContext().getAuthToken());
    }
    

}
