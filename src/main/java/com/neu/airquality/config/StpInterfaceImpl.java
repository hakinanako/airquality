package com.neu.airquality.config;

import cn.dev33.satoken.stp.StpInterface;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.neu.airquality.pojo.User;
import com.neu.airquality.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 自定义权限加载接口实现类
 */
@Component    // 保证此类被 SpringBoot 扫描，完成 Sa-Token 的自定义权限验证扩展
public class StpInterfaceImpl implements StpInterface {

    @Resource
    private UserService userService;

    @Override
    public List<String> getPermissionList(Object o, String s) {
        return null;
    }
    @Override
    public List<String> getRoleList(Object loginId, String loginType) {
        if (loginId == null) {
            return null;
        }
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getAccount, loginId);
        User one = userService.getOne(wrapper);
        if (one == null) {
            return null;
        } else if (one.getType() == 1) {
            List<String> list = new ArrayList<>();
            list.add("user");
            return list;
        } else if (one.getType() == 2) {
            List<String> list = new ArrayList<>();
            list.add("aqi");
            return list;
        } else if (one.getType() == 3) {
            List<String> list = new ArrayList<>();
            list.add("admin");
            return list;
        }
    return null;}
}

