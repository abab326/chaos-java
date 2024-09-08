package org.liushuxue.chaos.config;


import org.liushuxue.chaos.securiity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {
    public static final String LOGIN_PATH =  "/user/login";
    private static final String LOGOUT_PATH = "/user/logout";
    private static final String[] URL_WHITELIST = {"/",LOGIN_PATH, "/user/register", "/swagger-ui/**","/v3/api-docs/**"};

    @Autowired
    private LoginSuccessHandler loginSuccessHandler;
    @Autowired
    private LoginFailureHandler loginFailureHandler;
    @Autowired
    private JwtLogoutHandler jwtLogoutHandler;
    @Autowired
    private JwtLogoutSuccessHandler jwtLogoutSuccessHandler;
    @Autowired
    private JwtTokenFilter jwtTokenFilter;
    @Autowired
    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    @Autowired
    private JwtAccessDeniedHandler jwtAccessDeniedHandler;

    @Bean
    public DefaultSecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // 设置路由规则
        http.authorizeHttpRequests(auth -> auth.requestMatchers(URL_WHITELIST).permitAll().anyRequest().authenticated());
        // 设置登录页面
        http.formLogin(form -> form.loginPage(LOGIN_PATH).successHandler(loginSuccessHandler).failureHandler(loginFailureHandler));
        // 设置注销页面
        http.logout(logout -> logout.logoutUrl(LOGOUT_PATH).addLogoutHandler(jwtLogoutHandler).logoutSuccessHandler(jwtLogoutSuccessHandler));
        // 关闭csrf
        http.csrf(AbstractHttpConfigurer::disable);
        // 开启跨域
        http.cors(AbstractHttpConfigurer::disable);
        // 异常处理
        http.exceptionHandling(exceptions -> exceptions.authenticationEntryPoint(jwtAuthenticationEntryPoint).accessDeniedHandler(jwtAccessDeniedHandler));
        // 开启session
        http.sessionManagement(sessions -> sessions.sessionCreationPolicy(SessionCreationPolicy.STATELESS).maximumSessions(1));
        // 添加jwt过滤器
        http.addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
   @Bean
    public AuthenticationManager authorizationManager(DaoUserDetailsService userDetailsService, PasswordEncoder passwordEncoder) {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService);
        authenticationProvider.setPasswordEncoder(passwordEncoder);

        return new ProviderManager(authenticationProvider);
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
}
