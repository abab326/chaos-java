package org.liushuxue.chaos.securiity;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.liushuxue.chaos.entity.UserPo;
import org.liushuxue.chaos.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class DaoUserDetailsService implements UserDetailsService {
    @Autowired
    private UserService userService;
    @Override
    public LoginUser loadUserByUsername(String username) throws UsernameNotFoundException {
        LambdaQueryWrapper<UserPo> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(UserPo::getName, username);
        UserPo userPo = userService.getOne(queryWrapper);
        if (userPo == null) {
            throw new UsernameNotFoundException("用户名或密码错误");
        }
        return new LoginUser(userPo);
    }
}
