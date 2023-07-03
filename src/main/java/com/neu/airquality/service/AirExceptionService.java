package com.neu.airquality.service;

import com.neu.airquality.pojo.AirException;
import com.baomidou.mybatisplus.extension.service.IService;
import com.neu.airquality.pojo.AqiInfo;
import com.neu.airquality.req.AirExceptionReq;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 
 * @since 2023-07-01
 */
public interface AirExceptionService extends IService<AirException> {


    /**
     * user查看 or api查看
     * @param  id
     * @return
     */
    List<AirException>queryExceptionInfo(String id);

    /**
     * 用户上传异常信息
     * @param airExceptionReq
     * @return
     */
    boolean addAirExceptionForUser(AirExceptionReq airExceptionReq);

    /**
     * 管理员设置处理人
     * @param handlers 处理人id
     * @param id 异常表id
     * @return
     */
     boolean setHandlers(String handlers, String id);

    /**
     * 设置处理状态
     * @param id
     * @return
     */
     void handleResult(String id);
}
