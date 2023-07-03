package com.neu.airquality.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.neu.airquality.pojo.AirException;
import com.neu.airquality.pojo.AqiInfo;
import com.neu.airquality.mapper.AqiInfoMapper;
import com.neu.airquality.service.AirExceptionService;
import com.neu.airquality.service.AqiInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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
public class AqiInfoServiceImpl extends ServiceImpl<AqiInfoMapper, AqiInfo> implements AqiInfoService {


}
