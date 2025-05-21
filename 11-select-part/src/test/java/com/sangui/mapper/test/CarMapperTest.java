package com.sangui.mapper.test;


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
    public void testSelectCount() {
        CarMapper mapper = SqlSessionUtil.openSession().getMapper(CarMapper.class);
        int count = mapper.selectCount();
        System.out.println(count);
    }

    @Test
    public void testSelectAllReturnBigMaps() {
        CarMapper mapper = SqlSessionUtil.openSession().getMapper(CarMapper.class);
        Map<Long,Map<String, Object>> maps = mapper.selectAllReturnBigMaps();
        System.out.println(maps);
    }


    @Test
    public void testSelectAllReturnMaps() {
        CarMapper mapper = SqlSessionUtil.openSession().getMapper(CarMapper.class);
        List<Map<String, Object>> maps = mapper.selectAllReturnMaps();
        System.out.println(maps);
    }

    @Test
    public void testSelectByIdReturnMap() {
        CarMapper mapper = SqlSessionUtil.openSession().getMapper(CarMapper.class);
        Long id = 1L;
        Map<String, Object> map = mapper.selectByIdReturnMap(id);
        System.out.println(map);
    }

    @Test
    public void testSelectByBrandLike() {
        CarMapper mapper = SqlSessionUtil.openSession().getMapper(CarMapper.class);
        String brand = "比亚迪";
        // Car car  = mapper.selectByBrandLike(brand);
        List<Car> cars = mapper.selectByBrandLike(brand);
        System.out.println(cars);
    }

    @Test
    public void testSelectALL() {
        CarMapper mapper = SqlSessionUtil.openSession().getMapper(CarMapper.class);
        List<Car> cars = mapper.selectAll();
        System.out.println(cars);
    }

    @Test
    public void testSelectById() {
        CarMapper mapper = SqlSessionUtil.openSession().getMapper(CarMapper.class);
        Long id = 2L;
        Car car = mapper.selectById(id);
        System.out.println(car);
    }
}
