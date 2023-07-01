package com.neu.airquality;

import com.neu.airquality.pojo.AirException;
import com.neu.airquality.service.AirExceptionService;
import com.neu.airquality.service.impl.AirExceptionServiceImpl;
import javafx.application.Application;
import org.apache.catalina.Store;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = AirQualityApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AirqualityApplicationTests {

    @Autowired
    AirExceptionService airExceptionService;
    @Test
    public void add(){
        AirException airException = new AirException();
        airException.setId(6L);
        airExceptionService.save(airException);
    }

    @Test
    public void update(){
        AirException byId = airExceptionService.getById(3L);
        byId.setInfoId(2L);
        airExceptionService.updateById(byId);

    }

    @Test
    public void delete(){
        airExceptionService.removeById(2L);
    }

}
