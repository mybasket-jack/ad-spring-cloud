package com.jack006.ad.fliter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;

/**
 * 访问日志的过滤器
 *
 * @Author jack
 * @Since 1.0 2020/2/12 22:54
 */
@Component
@Slf4j
public class PreRequestFilter extends ZuulFilter {

    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return 0; // 数字越小越先执行
    }

    @Override
    public boolean shouldFilter() {
        return true; // 永远执行
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext context = RequestContext.getCurrentContext();
        context.set("startTime",System.currentTimeMillis());
        return null;
    }
}
