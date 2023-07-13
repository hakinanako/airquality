package com.neu.airquality.controller;


import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.annotation.SaCheckRole;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.neu.airquality.common.BaseResult;
import com.neu.airquality.pojo.AirException;
import com.neu.airquality.pojo.AqiInfo;
import com.neu.airquality.pojo.District;
import com.neu.airquality.req.AirExceptionReq;
import com.neu.airquality.service.AirExceptionService;
import com.neu.airquality.service.AqiInfoService;
import com.neu.airquality.service.DistrictService;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;


@RestController
@CrossOrigin
@RequestMapping("/air-exception")
public class AirExceptionController {

    @Resource
    private AirExceptionService airExceptionService;
    @Resource
    private DistrictService districtService;
    @Resource
    private AqiInfoService aqiInfoService;

    @ApiModelProperty("查询省份等级超标数量")
    @SaCheckLogin
    @GetMapping("/district/{id}")
    public BaseResult<Integer> getNum(@PathVariable int id){
        try {
            LambdaQueryWrapper<District> wrapper = new LambdaQueryWrapper<>();
            wrapper.eq(District::getPid,id);
            List<Object> list = new ArrayList<>();
            for (District district : districtService.list(wrapper)) {
                list.add(district.getId());
            }
            LambdaQueryWrapper<AqiInfo> aqiWrapper = new LambdaQueryWrapper<>();
            aqiWrapper.ge(AqiInfo::getLevel,3)
                    .in(AqiInfo::getDistrict,list);
            List<AqiInfo> aqiList = aqiInfoService.list(aqiWrapper);
            return BaseResult.ok("成功", aqiList.size());
        } catch (Exception e) {
            throw new RuntimeException("查询失败", e);
        }
    }


    /**
     * user上传空气异常信息
     *
     * @return 返回空气异常信息表-上传结果
     */
    @ApiOperation("空气异常信息表-添加")
    @SaCheckLogin
    @PostMapping
    public BaseResult<AirExceptionReq> add(@RequestBody AirExceptionReq airExceptionReq) {
        try {
            airExceptionService.addAirExceptionForUser(airExceptionReq);
            return BaseResult.ok("空气异常信息-上传成功", airExceptionReq);
        }catch (IllegalArgumentException e){
            return BaseResult.fail(e.getMessage());
        }
    }

    /**
     * 查看
     *
     * @param id 处理员id or userid
     * @return 返回airException对象
     */
    @ApiOperation("空气异常信息表-查看")
    @SaCheckLogin
    @GetMapping("/{id}")
    public BaseResult<List<AirException>> queryById(@ApiParam(name = "handlersOrUser", value = "唯一ID") @PathVariable String id) {

        try {
            List<AirException> airExceptions = airExceptionService.queryExceptionInfo(id);
            return BaseResult.ok("查看成功", airExceptions);
        } catch (Exception e) {
            e.printStackTrace();
            return BaseResult.fail(e.getMessage());
        }

    }

    /**
     * 获取所有异常表信息
     *
     */
    //ToDo 未确定是否分页
    @SaCheckRole("admin")
    @GetMapping("/list")
    public BaseResult<List<AirException>> query() {

        try {
            List<AirException> airExceptions = airExceptionService.list();
            return BaseResult.ok("查询成功",airExceptions);
        } catch (Exception e) {
            e.printStackTrace();
            return BaseResult.fail(e.getMessage());
        }
    }

    /**
     * 删除处理过异常表
     */
    @SaCheckRole("admin")
    @PostMapping("/remove")
    public BaseResult<String> remove(
            @RequestBody Long id) {

        LambdaQueryWrapper<AirException> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(AirException::getId, id);
        AirException one = airExceptionService.getOne(wrapper);
        if (one == null) {
            return BaseResult.fail("用户不存在");
        }else if(one.getStatus() == 0){
            boolean remove = airExceptionService.remove(wrapper);
            if (remove ) {
                return BaseResult.ok("删除成功");
            }
        }
        return BaseResult.fail("删除失败");
    }
}

