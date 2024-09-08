package org.liushuxue.chaos.securiity;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;
import org.liushuxue.chaos.common.BaseResult;
import org.liushuxue.chaos.enums.ResultStatusEnum;
import org.liushuxue.chaos.exception.BaseException;
import org.liushuxue.chaos.manager.RedisService;
import org.liushuxue.chaos.util.HttpResponseUtils;
import org.liushuxue.chaos.util.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;

@Log4j2
@Component
public class JwtTokenFilter extends OncePerRequestFilter {
    @Autowired
    private JwtUtils jwtUtils;
    @Autowired
    private RedisService redisService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = request.getHeader("Authorization");
        if (!StringUtils.hasLength(token)) {
            filterChain.doFilter(request, response);
            return;
        }
        String userId = null;
        try {
            userId = jwtUtils.getUserFromToken(token);
        } catch (BaseException e) {
            HttpResponseUtils.send(response, BaseResult.error(e.getResponseCode(), e.getMessage()));
        }
        if (!StringUtils.hasLength(userId)) {
            HttpResponseUtils.send(response, BaseResult.error(ResultStatusEnum.UNAUTHORIZED, "token异常"));
            return;
        }
        String redisToken = (String) redisService.getValue("userToken:" + token);
        if (!StringUtils.hasLength(redisToken)) {
            HttpResponseUtils.send(response, BaseResult.error(ResultStatusEnum.UNAUTHORIZED, "token已失效"));
            return;
        }
        log.info("user:  {}", userId);

        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userId, null, new ArrayList<>());
        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        filterChain.doFilter(request, response);
    }
}
