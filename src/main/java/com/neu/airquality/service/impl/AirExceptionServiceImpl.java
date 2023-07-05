package com.neu.airquality.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.neu.airquality.pojo.AirException;
import com.neu.airquality.mapper.AirExceptionMapper;
import com.neu.airquality.pojo.AqiInfo;
import com.neu.airquality.pojo.User;
import com.neu.airquality.req.AirExceptionReq;
import com.neu.airquality.service.AirExceptionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.neu.airquality.service.AqiInfoService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 
 * @since 2023-07-01
 */
@Service
public class AirExceptionServiceImpl extends ServiceImpl<AirExceptionMapper, AirException> implements AirExceptionService {


    @Override
    public List<AirException> queryExceptionInfo(String id) {
        LambdaQueryWrapper<AirException> queryWrapper = new LambdaQueryWrapper<>();
        //查看异常任务
        queryWrapper.eq(AirException::getUser, id);
        List<AirException>list = this.list(queryWrapper);
        return list;
    }

    @Override
    public boolean addAirExceptionForUser(AirExceptionReq airExceptionReq) {
        if (airExceptionReq != null){
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
        save(airException);
          return true;
        }
        return false;
    }


    @Override
    @Transactional
    public boolean setHandlers(String handlers, String id) {
            AirException airException = getById(id);
            // 设置处理人
            if (airException.getStatus() == 1 && airException.getHandlers() == null) {
                airException.setHandlers(Long.parseLong(handlers));
                int affectedRows = this.baseMapper.updateById(airException);
                return affectedRows > 0; // 如果成功更新了对象，返回 true；否则返回 false
            }
            return false; // 如果不满足条件，返回 false
        }

    @Override
    @Transactional
    public void handleResult(String id) {
        LambdaQueryWrapper<AirException> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(AirException::getInfoId, id);
        AirException airException = getOne(wrapper);
        if (airException!= null){
            airException.setStatus(0);
           this.baseMapper.updateById(airException);
        }
    }

}
