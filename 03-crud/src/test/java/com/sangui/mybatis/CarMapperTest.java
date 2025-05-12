package com.sangui.mybatis;


import com.sangui.pojo.Car;
import com.sangui.utils.SqlSessionUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: sangui
 * @CreateTime: 2025-05-12
 * @Description: CarMapperTest
 * @Version: 1.0
 */
public class CarMapperTest {
    @Test
    public void TestInsertCar() throws IOException {
        SqlSession sqlSession = SqlSessionUtil.openSession();
        // 这个对象先使用Map集合进行封装
        Map<String,Object> map = new HashMap<>();
        map.put("car_num","1005");
        map.put("brand","BMW");
        map.put("guide_price",30.0);
        map.put("produce_time","2020-11-11");
        map.put("car_type","燃油车");

        // 使用pojo进行封装
        Car car = new Car();
        car.setCarNum("1006");
        car.setBrand("BYD");
        car.setGuidePrice(7.98);
        car.setProduceTime("2021-11-11");
        car.setCarType("电车");

        int count = sqlSession.insert("insertCar", car);
        System.out.println("改变条数：" + count);
        sqlSession.commit();
        sqlSession.close();
    }
}
