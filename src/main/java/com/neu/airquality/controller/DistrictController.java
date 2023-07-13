package com.neu.airquality.controller;
import cn.dev33.satoken.annotation.SaCheckLogin;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.neu.airquality.common.BaseResult;
import com.neu.airquality.pojo.District;
import com.neu.airquality.service.DistrictService;
import com.neu.airquality.vo.DistrictVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
@RestController
@CrossOrigin
@RequestMapping("/district")
public class DistrictController {
    @Resource
    private DistrictService districtService;

    /**
     * 区域列表-搜索
     */
    @SaCheckLogin
    @GetMapping("/list/{name}/{pageSize}/{pageNum}")
    public BaseResult<PageInfo<DistrictVO>> listAdmin(@PathVariable(value = "pageSize") Integer pageSize,
                                                      @PathVariable(value = "pageNum") Integer pageNum,
                                                      @PathVariable(value = "name") String name
                                        ) {
        try {
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
        } catch (BeansException e) {
            e.printStackTrace();
            throw new RuntimeException("查询失败 " + e.getMessage());
        }
    }
    /**
     * 区域列表
     */
    @SaCheckLogin
    @GetMapping("/list/{pageSize}/{pageNum}")
    public BaseResult<PageInfo<DistrictVO>> listAdmin(@PathVariable(value = "pageSize") Integer pageSize,
                                                      @PathVariable(value = "pageNum") Integer pageNum) {
        try {
            String orderByColumn = "id"+" asc";
            PageHelper.startPage(pageNum,pageSize,orderByColumn);
            LambdaQueryWrapper<District> wrapper = new LambdaQueryWrapper<>();
            List<District> list = districtService.list(wrapper);
            List<DistrictVO> districtVOs = new ArrayList<>();
            for (District district : list) {
                DistrictVO districtVO = new DistrictVO();
                BeanUtils.copyProperties(district, districtVO);
                districtVOs.add(districtVO);
            }
            PageInfo<DistrictVO> pageInfo = new PageInfo<>(districtVOs);
            return BaseResult.ok(pageInfo);
        } catch (BeansException e) {
            e.printStackTrace();
            throw new RuntimeException("查询失败 " + e.getMessage());
        }
    }

    /**
     * id转name
     */
    @SaCheckLogin
    @GetMapping("/idToName/{id}")
    public BaseResult<String> idToName(@PathVariable(value = "id") Long id) {
        try {
            String stringBaseResult = districtService.getStringBaseResult(id);
                return BaseResult.ok(stringBaseResult);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("查询失败 " + e.getMessage());
        }
    }
    @SaCheckLogin
    @GetMapping
    public BaseResult<List<District>>getCity(@RequestBody Long id){
        try {
            List<District> city = districtService.getCity(id);
            return BaseResult.ok("城市-获取成功", city);
        } catch (Exception e) {
            return BaseResult.fail(e.getMessage());

        }
    }
}

