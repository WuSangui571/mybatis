package com.sangui.test;


import com.sangui.mapper.CarMapper;
import com.sangui.pojo.Car;
import com.sangui.utils.SqlSessionUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;

/**
 * @Author: sangui
 * @CreateTime: 2025-05-21
 * @Description:
 * @Version: 1.0
 */
public class CarMapperTest {
    @Test
    public void selectByBrandLike() {
        SqlSession sqlSession = SqlSessionUtil.openSession();
        CarMapper mapper = sqlSession.getMapper(CarMapper.class);
        String brand = "比亚迪";
        //List<Car> cars = mapper.selectByBrandLike("%" + brand + "%");
        List<Car> cars = mapper.selectByBrandLike(brand);
        for (Car car : cars) {
            System.out.println(car);
        }
    }

    @Test
    public void testDeleteBatch(){
        SqlSession sqlSession = SqlSessionUtil.openSession();
        CarMapper mapper = sqlSession.getMapper(CarMapper.class);
        String ids = "45,46";
        int count = mapper.deleteBatch(ids);
        System.out.println("数据库改变条数：" + count);
        sqlSession.commit();
        sqlSession.close();
    }
    @Test
    public void testSelectGuidePriceByAscOrDesc(){
        SqlSession sqlSession = SqlSessionUtil.openSession();
        CarMapper mapper = sqlSession.getMapper(CarMapper.class);
        // ASC | DESC
        List<Car> cars = mapper.selectGuidePriceByAscOrDesc("ASC");
        for (Car car : cars) {
            System.out.println(car);
        }

    }

    @Test
    public void testSelectByCarType() {
        SqlSession sqlSession = SqlSessionUtil.openSession();
        CarMapper mapper = sqlSession.getMapper(CarMapper.class);

        // 使用 ${}
        List<Car> cars = mapper.selectByCarType("\"新能源\"");

        // 使用 ${}，存在SQL注入风险
        // List<Car> cars = mapper.selectByCarType("\"新能源\" or 1 = 1");

        // 使用 #{}
        // List<Car> cars = mapper.selectByCarType("新能源");
        for (Car car : cars) {
            System.out.println(car);
        }
    }
}
