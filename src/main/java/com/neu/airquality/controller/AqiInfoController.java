package com.neu.airquality.controller;


import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.annotation.SaCheckRole;
import cn.dev33.satoken.annotation.SaMode;
import cn.dev33.satoken.stp.StpUtil;
import com.neu.airquality.common.BaseResult;
import com.neu.airquality.pojo.AqiInfo;
import com.neu.airquality.req.AqiReq;
import com.neu.airquality.service.AirExceptionService;
import com.neu.airquality.service.AqiInfoService;
import com.neu.airquality.util.AqiHelper;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;

@RestController
@RequestMapping("/aqi-info")
public class AqiInfoController {
     @Resource
      private AqiInfoService aqiInfoService;
    @Resource
    private AirExceptionService airExceptionService;

    /**
     *
     * @param id   info_id
     * @return  返回AqiInfo型对象
     */
    @ApiOperation(value = "空气质量信息表-通过id查询")
    @GetMapping("{id}")
    public BaseResult<AqiInfo> queryById(@ApiParam(name = "id", value = "info_id") @PathVariable String id){
      AqiInfo aqiInfo = aqiInfoService.getById(id);
      return BaseResult.ok("空气质量-查询成功!", aqiInfo);
    }

    /**
     * 上传空气质量信息
     * @param aqiInfo
     * @return  返回空气质量信息表-上传结果
     */
    @SaCheckRole(value = {"aqi", "admin"}, mode = SaMode.OR)
    @PostMapping
    public  BaseResult<AqiInfo> add(@RequestBody AqiInfo aqiInfo){
        if (StpUtil.hasRole("admin")){
            aqiInfoService.save(aqiInfo);
            return BaseResult.ok("空气质量信息-管理员上传成功", aqiInfo);
        }
        else if (StpUtil.hasRole("api")){
                airExceptionService.handleResult(aqiInfo.getId().toString());
            return BaseResult.ok("空气质量信息-api上传成功", aqiInfo);
        }
        return BaseResult.fail("错误:空气质量信息-上传失败", aqiInfo);
    }

    /**
     * 处理异常信息-返回Aqi等级
     */
    @SaCheckLogin
    @GetMapping("/level")
    public BaseResult<String> handleLevel(@RequestBody AqiReq aqiReq){
        int level = AqiHelper.calculateAQI(aqiReq.getPm25(), aqiReq.getCo(), aqiReq.getSo2());
        return BaseResult.ok("空气质量信息-处理成功", level+" ");
    }
}

