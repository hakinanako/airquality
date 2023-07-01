package com.neu.airquality.service.impl;

import com.neu.airquality.pojo.User;
import com.neu.airquality.mapper.UserMapper;
import com.neu.airquality.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 
 * @since 2023-07-01
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
