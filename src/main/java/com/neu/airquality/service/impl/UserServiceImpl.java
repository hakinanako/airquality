package com.neu.airquality.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.neu.airquality.common.Regex;
import com.neu.airquality.pojo.User;
import com.neu.airquality.mapper.UserMapper;
import com.neu.airquality.req.UserReq;
import com.neu.airquality.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {


    public void register(UserReq userReq) {
        if (userReq == null || userReq.getAccount() == null || userReq.getPassword() == null
                || userReq.getPhone() == null || userReq.getName() == null) {
            throw new IllegalArgumentException("用户信息不能为空");
        }

        String account = userReq.getAccount();
        String password = userReq.getPassword();
        String phone = userReq.getPhone();
        String name = userReq.getName();
        validateUserReq(account, password, phone, name);

        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getAccount, account);
        User one = getOne(wrapper);
        if (one != null) {
            throw new IllegalArgumentException("用户已存在");
        }

        //md5加密
        password = DigestUtils.md5DigestAsHex(password.getBytes());

        User user = new User();
        user.setAccount(account);
        user.setPassword(password);
        user.setPhone(phone);
        user.setName(name);
        user.setType(1);
        save(user);
    }

    private void validateUserReq(String account, String password, String phone, String name) {
        if (!account.matches(Regex.ACCOUNT_REGEX)) {
            throw new IllegalArgumentException("账号格式错误");
        }
        if (!password.matches(Regex.PASSWORD_REGEX)) {
            throw new IllegalArgumentException("密码格式错误");
        }
        if (!phone.matches(Regex.PHONE_REGEX)) {
            throw new IllegalArgumentException("手机号格式错误");
        }
        if (!name.matches(Regex.NAME_REGEX)) {
            throw new IllegalArgumentException("姓名格式错误");
        }
    }

}
