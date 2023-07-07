package com.neu.airquality.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.neu.airquality.pojo.AirException;
import com.neu.airquality.mapper.AirExceptionMapper;
import com.neu.airquality.req.AirExceptionReq;
import com.neu.airquality.service.AirExceptionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
public class AirExceptionServiceImpl extends ServiceImpl<AirExceptionMapper, AirException> implements AirExceptionService {

        @Override
        public List<AirException> queryExceptionInfo(String id) {
            LambdaQueryWrapper<AirException> queryWrapper = new LambdaQueryWrapper<>();
            //查看异常任务
            queryWrapper.eq(AirException::getUser, id);
            return this.list(queryWrapper);
        }


        @SneakyThrows
        @Override
        public void addAirExceptionForUser(AirExceptionReq airExceptionReq) {
            if (airExceptionReq == null) throw new IllegalAccessException("请求信息为空");

            Long user = airExceptionReq.getUser();
            String address = airExceptionReq.getAddress();
            Long district = airExceptionReq.getDistrict();
            String picture = airExceptionReq.getPicture();
            String description = airExceptionReq.getDescription();
            Integer level = airExceptionReq.getLevel();
            AirException airException = new AirException();
            airException.setUser(user);
            airException.setAddress(address);
            airException.setDistrict(district);
            airException.setPicture(picture);
            airException.setDescription(description);
            airException.setLevel(level);
            boolean save = save(airException);
            if (!save) throw new IllegalAccessException("保存失败");

        }

        @SneakyThrows
        @Override
        @Transactional
        public boolean setHandlers(String handlers, String id) {
            AirException airException = getById(id);
            if (airException == null)
                throw new IllegalAccessException("没有该异常信息");
            // 设置处理人
            if (airException.getStatus() == 1 && airException.getHandlers() == null) {
                airException.setHandlers(Long.parseLong(handlers));
                int affectedRows = this.baseMapper.updateById(airException);
                return affectedRows > 0; // 如果成功更新了对象，返回 true；否则返回 false
            }

            return false; // 如果不满足条件，返回 false
        }

        @SneakyThrows
        @Override
        @Transactional
        public void handleResult(String id) {
            LambdaQueryWrapper<AirException> wrapper = new LambdaQueryWrapper<>();
            wrapper.eq(AirException::getInfoId, id);
            AirException airException = getOne(wrapper);

            if (airException == null)
                throw new IllegalAccessException("没有该用户");

            airException.setStatus(0);
            this.baseMapper.updateById(airException);

        }

    }

