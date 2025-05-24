package com.sangui.test;


import com.sangui.mapper.CarMapper;
import com.sangui.pojo.Car;
import com.sangui.utils.SqlSessionUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

/**
 * @Author: sangui
 * @CreateTime: 2025-05-24
 * @Description:
 * @Version: 1.0
 */
public class CarMapperTest {
    @Test
    public void testSelectById(){
        SqlSession sqlSession = SqlSessionUtil.openSession();
        CarMapper mapper = sqlSession.getMapper(CarMapper.class);
        CarMapper mapper2 = sqlSession.getMapper(CarMapper.class);
        Long id = 1L;
        Car car = mapper.selectById(id);
        Car car2 = mapper2.selectById(id);
        System.out.println(car);
        System.out.println(car2);
    }
}
