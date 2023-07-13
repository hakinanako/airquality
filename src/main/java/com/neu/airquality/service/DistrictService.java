package com.neu.airquality.service;

import com.neu.airquality.pojo.District;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface DistrictService extends IService<District> {

    String getStringBaseResult(Long id);

    List<District> getCity(Long id);
}
