package com.goldbao.bankroll.interceptor;

import com.goldbao.utils.CommonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 接口参数校验拦截器
 * @author shuyu.fang
 *
 */
public class ParameterValidateInterceptor implements HandlerInterceptor {

    private final Logger logger = LoggerFactory.getLogger(ParameterValidateInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String context = request.getContextPath();
        context = request.getServletPath();
        String p = CommonUtil.serializeJSON(request.getParameterMap());
        logger.debug("请求方法：{}， 参数{}", context, p);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
//        logger.info("sign interceptor post handle");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
//        logger.info("sign interceptor after handle");
    }
}
