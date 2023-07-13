package com.neu.airquality;

import com.neu.airquality.pojo.AirException;
import com.neu.airquality.pojo.District;
import com.neu.airquality.service.AirExceptionService;
import com.neu.airquality.service.DistrictService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = AirQualityApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AirqualityApplicationTests {

    @Autowired
    AirExceptionService airExceptionService;
    @Autowired
    DistrictService districtService;

    @Test
    public void add2(){
        District district = new District();
        district.setName("上海");
        districtService.save(district);
    }

    @Test
            public void add(){
    ArrayList<String> objects = new ArrayList<>(
            Arrays.asList("上海", "北京", "深圳", "重庆", "广州", "成都", "天津", "武汉",
                    "武汉", "东莞", "西安", "杭州", "佛山", "南京", "沈阳", "青岛", "济南", "长沙", "哈尔滨", "郑州", "昆明", "大连",
                    "南宁", "石家庄", "厦门", "太原", "苏州", "贵阳", "合肥", "乌鲁木齐", "宁波", "无锡", "福州", "长春", "南昌", "常州",
                    "兰州", "中山", "惠州", "汕头", "临沂", "淄博", "温州", "呼和浩特", "绍兴", "唐山", "海口", "柳州", "徐州", "烟台", "洛阳", "邯郸", "珠海", "包头", "保定", "廊坊", "大同", "江门", "赣州", "西宁", "南通", "银川", "扬州", "遵义", "襄阳", "鞍山", "昆山", "莆田", "绵阳", "盐城", "泉州", "咸阳", "台州", "芜湖", "株洲", "淮安", "济宁", "吉林", "大庆", "桂林", "秦皇岛", "湛江", "宜昌", "齐齐哈尔", "抚顺", "上饶", "南充", "义乌", "邢台", "泰安", "开封", "张家口", "新乡", "聊城", "淮南", "十堰", "宜宾", "枣庄", "岳阳", "慈溪", "衡阳", "长治", "连云港", "赤峰", "晋江", "泸州"
            )
    );

    // 将字符串列表转换为UTF-8编码
    ArrayList<String> utf8Objects = new ArrayList<>();
for(
    String object :objects)

    {
        byte[] utf8Bytes = object.getBytes(StandardCharsets.UTF_8);
        String utf8String = new String(utf8Bytes, StandardCharsets.UTF_8);
        utf8Objects.add(utf8String);
    }

// 将UTF-8编码的字符串列表插入到数据库中
for(
    String object :utf8Objects)

    {
        District district = new District();
        district.setName(object);
        districtService.save(district);
    }

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
