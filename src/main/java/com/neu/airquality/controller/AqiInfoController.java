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
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/aqi-info")
public class AqiInfoController {
    @Resource
    private AqiInfoService aqiInfoService;
    @Resource
    private AirExceptionService airExceptionService;

    /**
     * @param id info_id
     * @return 返回AqiInfo型对象
     */
    @ApiOperation(value = "空气质量信息表-通过id查询")
    @GetMapping("{id}")
    public BaseResult<AqiInfo> queryById(@ApiParam(name = "id", value = "info_id") @PathVariable String id){
        try {
            AqiInfo aqiInfo = aqiInfoService.getById(id);
            return BaseResult.ok("空气质量-查询成功!", aqiInfo);
        } catch (Exception e) {
            e.printStackTrace();
            return BaseResult.fail(e.getMessage());
        }
    }

    /**
     * 上传空气质量信息
     *
     * @return 返回空气质量信息表-上传结果
     */
    @SaCheckRole(value = {"aqi", "admin"}, mode = SaMode.OR)
    @PostMapping
    public BaseResult<AqiInfo> add(@RequestBody AqiInfo aqiInfo) {
        if (StpUtil.hasRole("admin")) {
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
     * 获取所有空气质量信息
     *
     */
    //ToDo 未确定是否分页
    @PostMapping("/list")
    public BaseResult<List<AqiInfo>> query() {
        List<AqiInfo> aqiInfos = aqiInfoService.list();
        if (aqiInfos == null) return BaseResult.fail("无信息");
        return BaseResult.ok(aqiInfos);
    }

    /**
     * 处理异常信息-返回Aqi等级
     */
    @SaCheckLogin
    @GetMapping("/level")
    public BaseResult<String> handleLevel(@RequestBody AqiReq aqiReq){

        try {
            int level = AqiHelper.calculateAQI(aqiReq.getPm25(), aqiReq.getCo(), aqiReq.getSo2());
            return BaseResult.ok("空气质量信息-处理成功", level+" ");
        } catch (Exception e) {
            e.printStackTrace();
            return BaseResult.fail(e.getMessage());
        }
    }
    /**
     * 处理异常信息-返回Aqi等级
     */
    @SaCheckLogin
    @GetMapping("/level/{pm25}/{co}/{so2}/")
    public BaseResult<String> handleLevel(@ApiParam(name = "pm25", value = "pm25") @PathVariable Double pm25,
                                          @ApiParam(name = "co", value = "co") @PathVariable Double co,
                                          @ApiParam(name = "so2", value = "so2") @PathVariable Double so2){
        try {
            int level = AqiHelper.calculateAQI(pm25, co, so2);
            return BaseResult.ok("空气质量信息-处理成功", level+" ");
        } catch (Exception e) {
            throw new RuntimeException("查询", e);
        }
    }
    @SaCheckRole("admin")
    @GetMapping("/countRank")
    public BaseResult<String> countAQIByRank(@RequestBody Integer level){
        try {
            Integer aqiNum = aqiInfoService.countAQIByRank(level);
            return  BaseResult.ok("等级数量-成功", aqiNum + " ");
        } catch (Exception e) {
            e.printStackTrace();
            return BaseResult.fail(e.getMessage());
        }
    }
    @SaCheckRole("admin")
    @GetMapping("/countMonth")
    public BaseResult<String> countAQIByMonth(@RequestBody Integer level){
        try {
            Integer aqiNum = aqiInfoService.countAQIByMonth();
            return  BaseResult.ok("过去10月超标数量-统计成功", aqiNum + " ");
        } catch (Exception e) {
            e.printStackTrace();
            return BaseResult.fail(e.getMessage());
        }
    }
    }

