package org.liushuxue.chaos.controller;

import com.alibaba.fastjson2.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;
import org.liushuxue.chaos.bean.user.LoginVo;
import org.liushuxue.chaos.bean.user.RegisterVo;
import org.liushuxue.chaos.bean.user.UserVo;
import org.liushuxue.chaos.entity.UserPo;
import org.liushuxue.chaos.service.UserService;
import org.liushuxue.chaos.bean.user.UserStructMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.*;

@Log4j2
@Tag(name = "用户管理")
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private RedisTemplate<Object, Object> redisTemplate;


    @Operation(summary = "登录")
    @PostMapping("/login")
    public void login(@RequestParam LoginVo user) {
        log.info("用户名 login:   " + JSON.toJSONString(user));
    }

    @Operation(summary = "获取用户详情")
    @GetMapping("/detail")
    public UserVo getDetail() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userId = (String) authentication.getPrincipal();
        LambdaQueryWrapper<UserPo> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(UserPo::getId, Integer.valueOf(userId));
        UserPo userPo = userService.getOne(queryWrapper);
        return UserStructMapper.INSTANCES.toUserVo(userPo);
    }
    @Operation(summary = "获取用户详情")
    @GetMapping("/detailById")
    public UserVo getDetailById(@RequestParam(name = "id") @Parameter(description = "用户id") Integer id) {

        LambdaQueryWrapper<UserPo> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(UserPo::getId, id);
        UserPo userPo = userService.getOne(queryWrapper);
        return UserStructMapper.INSTANCES.toUserVo(userPo);
    }

    @Operation(summary = "用户注销")
    @PostMapping("/logout")
    public void logout(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        log.info("用户名 logout:   " + JSON.toJSONString(auth));
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
    }

    @Operation(summary = "用户注册")
    @PostMapping("/register")
    public void registerUser(@RequestBody @Parameter(description = "用户注册实体") RegisterVo registerVO,
                                               HttpServletRequest request) {
        String id = request.getSession().getId();
        String code = (String) redisTemplate.opsForValue().get("register:code:" + id);

//        if (!registerVO.getCode().equals(code)) {
//            return BaseResult.error(ResultStatusEnum.BAD_REQUEST,"验证码错误");
//        }
        UserPo userPo = new UserPo();
        userPo.setName(registerVO.getUsername());
        userPo.setPassword(PasswordEncoderFactories.createDelegatingPasswordEncoder().encode(registerVO.getPassword()));
        userService.save(userPo);
    }
}
