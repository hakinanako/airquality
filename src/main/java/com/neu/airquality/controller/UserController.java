package com.neu.airquality.controller;


import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.annotation.SaCheckRole;
import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.neu.airquality.common.BaseResult;
import com.neu.airquality.pojo.User;
import com.neu.airquality.req.LoginReq;
import com.neu.airquality.req.UserReq;
import com.neu.airquality.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 
 * @since 2023-07-01
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    /**
     * 用户登录
     */
    @PostMapping("/login")
    public BaseResult<SaTokenInfo> login(
            @RequestBody LoginReq loginReq) {
        String account = loginReq.getAccount();
        String password = loginReq.getPassword();
        //md5加密
        password = DigestUtils.md5DigestAsHex(password.getBytes());
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getAccount, account);
        User one = userService.getOne(wrapper);
        if (one == null) {
            return BaseResult.fail("用户不存在");
        } else if (!one.getPassword().equals(password)) {
            return BaseResult.fail("用户名或密码错误");
        } else {
            StpUtil.login(account);
            SaTokenInfo tokenInfo = StpUtil.getTokenInfo();
            return BaseResult.ok(tokenInfo);
        }
    }

    /**
     * 用户注销
     */
    @SaCheckLogin
    @RequestMapping("/logout")
    public BaseResult<String> logout() {
        StpUtil.logout();
        return BaseResult.ok("注销成功");
    }

    /**
     * 获取当前会话的token信息
     */
    @SaCheckLogin
    @RequestMapping("tokenInfo")
    public BaseResult<SaTokenInfo> tokenInfo() {
        return BaseResult.ok(StpUtil.getTokenInfo());
    }

    /**
     * 查询登录状态
     */
    @SaCheckLogin
    @RequestMapping("isLogin")
    public BaseResult<String> isLogin() {
        return BaseResult.ok("是否登录：" + StpUtil.isLogin());
    }

    /**
     * 用户注册
     */
    @PostMapping("/register")
    public BaseResult<String> register(
            @RequestBody UserReq userReq) {
        String account = userReq.getAccount();
        String password = userReq.getPassword();
        //md5加密
        password = DigestUtils.md5DigestAsHex(password.getBytes());
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getAccount, account);
        User one = userService.getOne(wrapper);
        if (one != null) {
            return BaseResult.fail("用户已存在");
        } else {
            User user = new User();
            user.setAccount(account);
            user.setPassword(password);
            user.setPhone(userReq.getPhone());
            user.setName(userReq.getName());
            userService.save(user);
            return BaseResult.ok("注册成功");
        }
    }

    /**
     * 注销账号
     */
    @SaCheckLogin
    @PostMapping("/delete")
    public BaseResult<String> delete(
            @RequestBody UserReq userReq) {
        String account = userReq.getAccount();
        String password = userReq.getPassword();
        //md5加密
        password = DigestUtils.md5DigestAsHex(password.getBytes());
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getAccount, account);
        User one = userService.getOne(wrapper);
        if (one == null) {
            return BaseResult.fail("用户不存在");
        } else if (!one.getPassword().equals(password)) {
            return BaseResult.fail("用户名或密码错误");
        } else {
            StpUtil.logout();
            userService.remove(wrapper);
            return BaseResult.ok("注销成功");
        }
    }

    /**
     * 密码校验
     */
    @PostMapping("/check")
    public BaseResult<String> check(
            @RequestBody UserReq userReq) {
        String account = userReq.getAccount();
        String password = userReq.getPassword();
        //md5加密
        password = DigestUtils.md5DigestAsHex(password.getBytes());
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getAccount, account);
        User one = userService.getOne(wrapper);
        if (!one.getPassword().equals(password)) {
            return BaseResult.fail("密码错误");
        } else {
            return BaseResult.ok("密码正确");
        }
    }

    /**
     * 修改密码
     */
    @PostMapping("/update")
    public BaseResult<String> update(
            @RequestBody UserReq userReq) {
        String account = userReq.getAccount();
        String password = userReq.getPassword();
        //md5加密
        password = DigestUtils.md5DigestAsHex(password.getBytes());
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getAccount, account);
        User one = userService.getOne(wrapper);
        if (one == null) {
            return BaseResult.fail("用户不存在");
        } else {
            one.setPassword(password);
            userService.updateById(one);
            return BaseResult.ok("修改成功");
        }
    }

    /**
     * 修改信息
     */
    @PostMapping("/updateInfo")
    public BaseResult<String> updateInfo(
            @RequestBody UserReq userReq) {
        String account = userReq.getAccount();
        String name = userReq.getName();
        String phone = userReq.getPhone();
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getAccount, account);
        User one = userService.getOne(wrapper);
        if (one == null) {
            return BaseResult.fail("用户不存在");
        } else {
            one.setName(name);
            one.setPhone(phone);
            userService.updateById(one);
            return BaseResult.ok("修改成功");
        }
    }

    /**
     * 获取用户信息
     */
    @PostMapping("/info")
    public BaseResult<User> info(
            @RequestBody UserReq userReq) {
        String account = userReq.getAccount();
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getAccount, account);
        User one = userService.getOne(wrapper);
        if (one == null) {
            return BaseResult.fail("用户不存在");
        } else {
            one.setPassword("");
            return BaseResult.ok(one);
        }
    }

    /**
     * 获取用户列表
     */
    @PostMapping("/list")
    public BaseResult<List<User>> list() {
        List<User> list = userService.list();
        return BaseResult.ok(list);
    }

    /**
     * 获取普通用户列表
     */
    @PostMapping("/listUser")
    public BaseResult<List<User>> listUser() {
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getType, 1);
        List<User> list = userService.list(wrapper);
        return BaseResult.ok(list);
    }

    /**
     * 获取aqi列表
     */
    @PostMapping("/listAqi")
    public BaseResult<List<User>> listAdmin() {
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getType, 2);
        List<User> list = userService.list(wrapper);
        return BaseResult.ok(list);
    }

    /**
     * 获取管理员列表
     */
    @PostMapping("/listAdmin")
    public BaseResult<List<User>> listAqi() {
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getType, 3);
        List<User> list = userService.list(wrapper);
        return BaseResult.ok(list);
    }

    /**
     * 移除用户
     */
    @PostMapping("/remove")
    public BaseResult<String> remove(
            @RequestBody Long id) {
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getId, id);
        User one = userService.getOne(wrapper);
        if (one == null) {
            return BaseResult.fail("用户不存在");
        } else if (one.getType() == 3) {
            return BaseResult.fail("不支持删除管理员");
        } else {
            boolean remove = userService.remove(wrapper);
            if (remove) {
                return BaseResult.ok("删除成功");
            }
        }
        return BaseResult.fail("删除失败");
    }


    /**
     * 添加aqi用户
     */
    @SaCheckRole("admin")
    @PostMapping("/addAqi")
    public BaseResult<String> addAqi(
            @RequestBody UserReq userReq) {
        String account = userReq.getAccount();
        String password = userReq.getPassword();
        String name = userReq.getName();
        String phone = userReq.getPhone();
        Long district = userReq.getDistrict();
        //md5加密
        password = DigestUtils.md5DigestAsHex(password.getBytes());
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getAccount, account);
        User one = userService.getOne(wrapper);
        if (one != null) {
            return BaseResult.fail("用户已存在");
        } else {
            User user = new User();
            user.setAccount(account);
            user.setPassword(password);
            user.setName(name);
            user.setPhone(phone);
            user.setType(2);
            user.setArea(district);
            userService.save(user);
            return BaseResult.ok("添加成功");
        }
    }
}