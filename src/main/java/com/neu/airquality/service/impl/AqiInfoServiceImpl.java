package com.neu.airquality.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.neu.airquality.pojo.AqiInfo;
import com.neu.airquality.mapper.AqiInfoMapper;
import com.neu.airquality.service.AqiInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AqiInfoServiceImpl extends ServiceImpl<AqiInfoMapper, AqiInfo> implements AqiInfoService {

    /**
     * 按等级划分统计数量
     */

    @Override
    public Integer countAQIByRank(Integer level) {

        LambdaQueryWrapper<AqiInfo> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(AqiInfo::getLevel, level);
        List<AqiInfo> list;
        try {
            list = this.list(wrapper);
            return list.size();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }

    /**
     * 过去10月的超标数量统计
     */
    @Override
    public Integer countAQIByMonth() {

        LocalDateTime now = LocalDateTime.now();
        //过去10月
        LocalDateTime localDateTime = now.minusMonths(10);
        LambdaQueryWrapper<AqiInfo> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(AqiInfo::getCreateTime, localDateTime).ge(AqiInfo::getLevel,  3);
        List<AqiInfo> list;
        try {
            list = this.list(wrapper);
            return list.size();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }
}
