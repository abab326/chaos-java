package org.liushuxue.chaos.securiity;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;
import org.liushuxue.chaos.common.BaseResult;
import org.liushuxue.chaos.enums.ResultStatusEnum;
import org.liushuxue.chaos.util.HttpResponseUtils;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Log4j2
@Component
public class JwtAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {

        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        BaseResult<String> baseResultDTO = BaseResult.error(ResultStatusEnum.FORBIDDEN);
        HttpResponseUtils.send(response, baseResultDTO);
    }
}
