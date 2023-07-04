package com.neu.airquality.controller;


import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.annotation.SaCheckRole;
import com.neu.airquality.common.BaseResult;
import com.neu.airquality.pojo.AirException;
import com.neu.airquality.req.AirExceptionReq;
import com.neu.airquality.service.AirExceptionService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;


@RestController
@RequestMapping("/air-exception")
public class AirExceptionController {

    @Resource
    private  AirExceptionService airExceptionService;



    /**
     * user上传空气异常信息
     * @param airExceptionReq
     * @return  返回空气异常信息表-上传结果
     */
    @ApiOperation("空气异常信息表-添加")
    @SaCheckLogin
    @PostMapping
   public  BaseResult<AirExceptionReq> add(@RequestBody AirExceptionReq airExceptionReq){
       if (airExceptionReq != null){
           airExceptionService.addAirExceptionForUser(airExceptionReq);
           return BaseResult.ok("空气异常信息-上传成功", airExceptionReq);
       }
       return BaseResult.fail("错误:空气异常信息-上传失败", airExceptionReq);
   }

    /**
     *  查看
     * @param id   处理员id or userid
     * @return  返回airException对象
     */
    @ApiOperation("空气异常信息表-查看")
    @SaCheckLogin
    @GetMapping("/{id}")
   public BaseResult<List<AirException>> queryById(@ApiParam(name = "handlersOrUser", value = "唯一ID")@PathVariable String id){
            if (id != null){
                List<AirException> airExceptions = airExceptionService.queryExceptionInfo(id);
                return BaseResult.ok("查看成功", airExceptions);
            }
            return BaseResult.fail("查看失败");

   }

    /**
     * 获取所有异常表信息
     * @return
     */
    //ToDo 未确定是否分页
    @SaCheckRole("admin")
    @PostMapping("/list")
    public BaseResult<List<AirException>> query(){
        List<AirException> airExceptions = airExceptionService.list();
        return BaseResult.ok(airExceptions);
    }



}

