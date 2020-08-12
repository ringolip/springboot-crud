package com.ringo.restfulcrud.component;

import org.springframework.util.StringUtils;
import org.springframework.web.servlet.LocaleResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

/**
 * 自定义国际化区域信息解析器
 * @author ringo
 * @version 1.0
 * @date 2020/8/11 18:55
 */
public class MyLocaleResolver implements LocaleResolver {

    /**
     * 解析区域信息
     * @param request
     * @return
     */
    @Override
    public Locale resolveLocale(HttpServletRequest request) {
        // 从请求头获取国际化参数
        String l = request.getParameter("l");
        Locale locale = Locale.getDefault(); // 没有国际化参数时使用默认设置
        // 查看是否携带国际化参数
        if (!StringUtils.isEmpty(l)){
            String[] split = l.split("_");
            locale  = new Locale(split[0], split[1]);
        }
        return locale;
    }

    @Override
    public void setLocale(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Locale locale) {

    }
}
