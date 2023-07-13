package com.neu.airquality.service;

import com.neu.airquality.pojo.AqiInfo;
import com.baomidou.mybatisplus.extension.service.IService;

public interface AqiInfoService extends IService<AqiInfo> {
    Integer countAQIByMonth();

    Integer countAQIByRank(Integer level);
}
