package org.liushuxue.chaos.securiity;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;
import org.liushuxue.chaos.common.BaseResult;
import org.liushuxue.chaos.manager.RedisService;
import org.liushuxue.chaos.util.HttpResponseUtils;
import org.liushuxue.chaos.util.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

@Log4j2
@Component
public class LoginSuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    private RedisService redisService;
    @Autowired
    private JwtUtils jwtUtils;
    /**
     * 有效期
     */
    @Value("${jwt.expire}")
    private int expire;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        String token = jwtUtils.getToken(loginUser.getUserPo().getId()+"");
        redisService.setValueWithExpiry("userToken:"  + token,"1",expire, TimeUnit.MINUTES);
        BaseResult<String> baseResultDTO = BaseResult.success(token);
        HttpResponseUtils.send(response, baseResultDTO);
    }
}
