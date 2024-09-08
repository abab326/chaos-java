package org.liushuxue.chaos.exception;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.liushuxue.chaos.enums.ResultStatusEnum;
import org.springframework.security.core.AuthenticationException;


@Getter
@EqualsAndHashCode(callSuper = true)
public class BaseException extends AuthenticationException {

    private final ResultStatusEnum responseCode;

    public BaseException(ResultStatusEnum responseCode, String message) {
        super(message);
        this.responseCode = responseCode;
    }

}