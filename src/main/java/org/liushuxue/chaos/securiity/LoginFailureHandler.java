package org.liushuxue.chaos.securiity;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;
import org.liushuxue.chaos.common.BaseResult;
import org.liushuxue.chaos.enums.ResultStatusEnum;
import org.liushuxue.chaos.util.HttpResponseUtils;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Log4j2
@Component
public class LoginFailureHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        BaseResult<String> baseResultDTO = BaseResult.error(ResultStatusEnum.FORBIDDEN, exception.getMessage());
        HttpResponseUtils.send(response, baseResultDTO);
    }
}
