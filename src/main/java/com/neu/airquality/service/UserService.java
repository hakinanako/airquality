package com.neu.airquality.service;

import com.neu.airquality.pojo.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.neu.airquality.req.UserReq;

public interface UserService extends IService<User> {

    void register(UserReq userReq);
}
