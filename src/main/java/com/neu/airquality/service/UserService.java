package com.neu.airquality.service;

import com.neu.airquality.pojo.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.neu.airquality.req.UserReq;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 
 * @since 2023-07-01
 */
public interface UserService extends IService<User> {

    void register(UserReq userReq);
}
