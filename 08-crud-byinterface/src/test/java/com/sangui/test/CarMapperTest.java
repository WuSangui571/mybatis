package com.sangui.test;


import com.sangui.mapper.CarMapper;
import com.sangui.pojo.Car;
import com.sangui.utils.SqlSessionUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;
import java.util.Map;

/**
 * @Author: sangui
 * @CreateTime: 2025-05-21
 * @Description:
 * @Version: 1.0
 */
public class CarMapperTest {
    @Test
    public void testInsert() {
        SqlSession sqlSession = SqlSessionUtil.openSession();
        CarMapper mapper = sqlSession.getMapper(CarMapper.class);
        Car car = new Car(null, "1100","bmw",45.0,"2023-11-11","燃油车");
        int count = mapper.insert(car);
        System.out.println("改变条数：" + count);
        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void testDelete() {
        SqlSession sqlSession = SqlSessionUtil.openSession();
        CarMapper mapper = sqlSession.getMapper(CarMapper.class);
        int count = mapper.deleteById(116L);
        System.out.println("改变条数：" + count);
        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void testUpdate() {
        SqlSession sqlSession = SqlSessionUtil.openSession();
        CarMapper mapper = sqlSession.getMapper(CarMapper.class);
        Car car = new Car(46L, "1005055","bbbbb",55555.0,"2055-11-11","燃油车555");
        int count = mapper.update(car);
        System.out.println("改变条数：" + count);
        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void testSelectOne() {
        SqlSession sqlSession = SqlSessionUtil.openSession();
        CarMapper mapper = sqlSession.getMapper(CarMapper.class);
        Car car = mapper.selectById(44L);
        System.out.println(car);
    }

    @Test
    public void testSelectAll() {
        SqlSession sqlSession = SqlSessionUtil.openSession();
        List<Car> cars = sqlSession.getMapper(CarMapper.class).selectAll();
        for (Car car : cars) {
            System.out.println(car);
        }

    }
}
