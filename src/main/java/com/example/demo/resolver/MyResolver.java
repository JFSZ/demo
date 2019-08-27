package com.example.demo.resolver;

import com.example.demo.annotation.UserBody;
import com.example.demo.model.User;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

/**
 * @author chenxue
 * @Description 自定义参数解析器 用于绑定@UserBody
 * @Date 2019/8/27 14:42
 */

public class MyResolver implements HandlerMethodArgumentResolver {
    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        if(methodParameter.getParameterType().isAssignableFrom(User.class) && methodParameter.hasParameterAnnotation(UserBody.class)){
            return true;
        }
        return false;
    }

    @Override
    public Object resolveArgument(MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer, NativeWebRequest nativeWebRequest, WebDataBinderFactory webDataBinderFactory) throws Exception {
        UserBody userBody = methodParameter.getParameterAnnotation(UserBody.class);
        //session中获取用户信息
        Object object = nativeWebRequest.getAttribute(userBody.value(),NativeWebRequest.SCOPE_SESSION);
        if (object == null) {
            String token = nativeWebRequest.getHeader("Authorization");
            if (token == null) {
                token = nativeWebRequest.getParameter("accessToken");
            }
            //为了测试先写死用户名
            return new User("admin",22,"123@qq.com");
        }
        return object;
    }
}
