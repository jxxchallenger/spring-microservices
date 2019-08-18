package com.thoughtmechanix.licenses.utils;

import org.springframework.util.Assert;

public class UserContextHolder {

    private static final ThreadLocal<UserContext> USERCONTEXT_THREAD_LOCAL = new ThreadLocal<UserContext>() {

        @Override
        protected UserContext initialValue() {
            return new UserContext();
        }
        
    };
    
    public static final UserContext getContext() {
        return USERCONTEXT_THREAD_LOCAL.get();
    }
    
    public static final void setContext(UserContext userContext) {
        Assert.notNull(userContext, () -> {return "Only non-null UserContext instances are permitted";});
        USERCONTEXT_THREAD_LOCAL.set(userContext);
    }
}
