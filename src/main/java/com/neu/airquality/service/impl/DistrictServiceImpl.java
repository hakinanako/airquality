package com.neu.airquality.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.neu.airquality.pojo.District;
import com.neu.airquality.mapper.DistrictMapper;
import com.neu.airquality.service.DistrictService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DistrictServiceImpl extends ServiceImpl<DistrictMapper, District> implements DistrictService {

    @Override
    public String getStringBaseResult(Long id) {
        LambdaQueryWrapper<District> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(District::getId, id);
        District one = getOne(wrapper);
        if (one == null) {
            throw new IllegalArgumentException("区域不存在");
        }else return one.getName();
    }

    @Override
    public List<District> getCity(Long id) {

        LambdaQueryWrapper<District> wrapper = new LambdaQueryWrapper<>();
        wrapper.select(District::getName).eq(District::getPid, id);
        try {
            return this.list(wrapper);

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }
}
