package org.liushuxue.chaos.securiity;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.liushuxue.chaos.common.BaseResult;
import org.liushuxue.chaos.manager.RedisService;
import org.liushuxue.chaos.util.HttpResponseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class JwtLogoutSuccessHandler implements LogoutSuccessHandler {
    @Autowired
    private RedisService redisService;
    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        String token = request.getHeader("Authorization");
        SecurityContextHolder.clearContext();
        if (token != null) {
            redisService.deleteValue("userToken:"  + token);
        }
        BaseResult<String> baseResultDTO = BaseResult.success();
        HttpResponseUtils.send(response, baseResultDTO);
    }


}
