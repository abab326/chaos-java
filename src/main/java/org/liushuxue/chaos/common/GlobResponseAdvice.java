package org.liushuxue.chaos.common;

import com.alibaba.fastjson2.JSON;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import static com.alibaba.fastjson2.JSONWriter.Feature.WriteMapNullValue;

@ControllerAdvice(basePackages = "org.liushuxue.chaos.controller")
public class GlobResponseAdvice implements ResponseBodyAdvice<Object> {

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType,
                                  Class<? extends HttpMessageConverter<?>> selectedConverterType,
                                  ServerHttpRequest request, ServerHttpResponse response) {
        //返回结果更加灵活
        if (body instanceof BaseResult){
            return body;
        }
        if (returnType.getParameterType().isAssignableFrom(String.class)) {
            return JSON.toJSONString(BaseResult.success(body), WriteMapNullValue);
        }


        return BaseResult.success(body);
    }
}
