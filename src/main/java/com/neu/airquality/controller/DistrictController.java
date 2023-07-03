package com.neu.airquality.controller;


import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.annotation.SaCheckRole;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.neu.airquality.common.BaseResult;
import com.neu.airquality.pojo.District;
import com.neu.airquality.pojo.User;
import com.neu.airquality.service.DistrictService;
import com.neu.airquality.vo.DistrictVO;
import com.neu.airquality.vo.UserVO;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 
 * @since 2023-07-01
 */
@RestController
@RequestMapping("/district")
public class DistrictController {
    @Autowired
    private DistrictService districtService;

    /**
     * 区域列表
     */
    @SaCheckLogin
    @GetMapping("/list/{name}/{pageSize}/{pageNum}")
    public BaseResult<PageInfo<DistrictVO>> listAdmin(@PathVariable(value = "pageSize") Integer pageSize,
                                                      @PathVariable(value = "pageNum") Integer pageNum,
                                                      @PathVariable(value = "name") String name
                                        ) {
        String orderByColumn = "id"+" asc";
        PageHelper.startPage(pageNum,pageSize,orderByColumn);
        LambdaQueryWrapper<District> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(District::getName, name);
        List<District> list = districtService.list(wrapper);
        List<DistrictVO> districtVOs = new ArrayList<>();
        for (District district : list) {
            DistrictVO districtVO = new DistrictVO();
            BeanUtils.copyProperties(district, districtVO);
            districtVOs.add(districtVO);
        }
        PageInfo<DistrictVO> pageInfo = new PageInfo<>(districtVOs);
        return BaseResult.ok(pageInfo);
    }
    /**
     * id转name
     */
    @SaCheckLogin
    @GetMapping("/idToName/{id}")
    public BaseResult<String> idToName(@PathVariable(value = "id") Long id) {
        String stringBaseResult = districtService.getStringBaseResult(id);
        if(Objects.equals(stringBaseResult, "error")){
            return BaseResult.fail("无此地区");
        }else{
            return BaseResult.ok(stringBaseResult);
        }

    }
}

