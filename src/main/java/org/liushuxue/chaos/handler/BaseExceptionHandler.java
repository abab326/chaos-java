package org.liushuxue.chaos.handler;

import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;
import org.liushuxue.chaos.common.BaseResult;
import org.liushuxue.chaos.enums.ResultStatusEnum;
import org.liushuxue.chaos.exception.BaseException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Log4j2
public class BaseExceptionHandler {

    /**
     * 处理BaseException
     *
     */
    @ExceptionHandler(BaseException.class)
    public BaseResult<String> handlerGlobalException(HttpServletResponse response, BaseException e) {
        log.error("请求异常 BaseException：", e);
        return BaseResult.error(e.getResponseCode().getCode(), e.getMessage());
    }
    /**
     * 处理BindException
     *
     * @param e
     * @return
     */
    @ExceptionHandler(BindException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public BaseResult<String> handlerBindException(BindException e) {
        log.error("请求异常 BindException：", e);
        BindingResult bindingResult = e.getBindingResult();
        FieldError fieldError = bindingResult.getFieldError();
        assert fieldError != null;
        String defaultMessage = fieldError.getDefaultMessage();

        return BaseResult.error(ResultStatusEnum.BAD_REQUEST, defaultMessage);
    }
    /**
     * 处理Exception
     *
     * @param e
     * @return
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public BaseResult<String> handlerException(Exception e) {
        log.error("请求异常 Exception：", e);

        return BaseResult.error(ResultStatusEnum.ERROR, e.getMessage());
    }
}
