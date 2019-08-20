package com.thoughtmechanix.zuulsvr.filters;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

@Component
public class ResponseFilter extends ZuulFilter {

    private static final Logger LOGGER = LoggerFactory.getLogger(ResponseFilter.class);
    
    @Override
    public boolean shouldFilter() {
        
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext ctx = RequestContext.getCurrentContext();

        LOGGER.info("Adding the correlation id to the outbound headers. {}", FilterUtils.getCorrelationId());
        ctx.getResponse().addHeader(FilterUtils.CORRELATION_ID, FilterUtils.getCorrelationId());
        LOGGER.info("Completing outgoing request for {}.", ctx.getRequest().getRequestURI());
        return null;
    }

    @Override
    public String filterType() {
        
        return FilterConstants.POST_TYPE;
    }

    @Override
    public int filterOrder() {
        
        return FilterConstants.DEBUG_FILTER_ORDER;
    }

}
