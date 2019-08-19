package com.thoughtmechanix.zuulsvr.filters;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

@Component
public class TrackingFilter extends ZuulFilter {
    
    private static final boolean SHOULD_FILTER = true;
    
    private static final Logger LOGGER = LoggerFactory.getLogger(TrackingFilter.class);
    
    @Override
    public boolean shouldFilter() {
        
        return SHOULD_FILTER;
    }

    @Override
    public Object run() throws ZuulException {
        if(isCorrelationIdPresent()) {
            LOGGER.info("tmx-correlation-id found in tracking filter: {}.", FilterUtils.getCorrelationId());
        }
        else {
            FilterUtils.setCorrelationId(generateCorrelationId());
            LOGGER.info("tmx-correlation-id generated in tracking filter: {}.", FilterUtils.getCorrelationId());
        }
        LOGGER.info("Processing incoming request for {}.", RequestContext.getCurrentContext().getRequest().getRequestURI());
        return null;
    }

    @Override
    public String filterType() {
        
        return FilterConstants.PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        
        return FilterConstants.DEBUG_FILTER_ORDER;
    }

    private boolean isCorrelationIdPresent(){
        if(FilterUtils.getCorrelationId() != null) {
            return true;
        }
        return false;
    }
    
    private String generateCorrelationId(){
        return UUID.randomUUID().toString();
    }
}
