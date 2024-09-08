package org.liushuxue.chaos.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.liushuxue.chaos.entity.UserPo;
import org.liushuxue.chaos.service.UserService;
import org.liushuxue.chaos.mapper.UserMapper;
import org.springframework.stereotype.Service;

/**
 * @author Administrator
 * @description 针对表【t_user】的数据库操作Service实现
 * @createDate 2024-09-05 20:33:46
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, UserPo> implements UserService {

}




