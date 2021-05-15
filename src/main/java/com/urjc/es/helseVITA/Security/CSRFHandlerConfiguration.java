package com.urjc.es.helseVITA.Security;

import org.jetbrains.annotations.NotNull;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Configuration
public class CSRFHandlerConfiguration implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new CSRFHandlerInterceptor());
    }
}
class CSRFHandlerInterceptor extends HandlerInterceptorAdapter {
    @Override
    public void postHandle(final HttpServletRequest request,
                           final @NotNull HttpServletResponse response, final @NotNull Object handler,
                           final ModelAndView modelAndView) throws Exception {
        CsrfToken token = (CsrfToken) request.getAttribute("_csrf");
        if (modelAndView != null){
            modelAndView.addObject("token", token.getToken());
            modelAndView.addObject("headerName",token.getHeaderName());
        }

    }
}
