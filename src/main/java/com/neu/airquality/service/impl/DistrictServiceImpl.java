package com.neu.airquality.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.neu.airquality.pojo.District;
import com.neu.airquality.mapper.DistrictMapper;
import com.neu.airquality.service.DistrictService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class DistrictServiceImpl extends ServiceImpl<DistrictMapper, District> implements DistrictService {

    @Override
    public String getStringBaseResult(Long id) {
        LambdaQueryWrapper<District> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(District::getId, id);
        District one = getOne(wrapper);
        if (one == null) {
            return "error";
        }else return one.getName();
    }
}
