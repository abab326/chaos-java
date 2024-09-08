package org.liushuxue.chaos.securiity;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;
import org.liushuxue.chaos.common.BaseResult;
import org.liushuxue.chaos.enums.ResultStatusEnum;
import org.liushuxue.chaos.manager.RedisService;
import org.liushuxue.chaos.util.HttpResponseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.IOException;

@Log4j2
@Component
public class JwtLogoutHandler implements LogoutHandler {
    @Autowired
    RedisService redisService;
    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
         String token = request.getHeader("Authorization");
         if (StringUtils.hasLength(token)) {
             String redisToken = (String)redisService.getValue("userToken:"  + token);
             if (!StringUtils.hasLength(redisToken)) {
                 try {
                     HttpResponseUtils.send(response, BaseResult.error(ResultStatusEnum.UNAUTHORIZED, "token 不存在"));
                 } catch (IOException e) {
                     throw new RuntimeException(e);
                 }
             }
         }
    }

}
