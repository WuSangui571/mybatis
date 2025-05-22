package com.sangui.mapper.test;


import com.sangui.mapper.CarMapper;
import com.sangui.pojo.Car;
import com.sangui.utils.SqlSessionUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: sangui
 * @CreateTime: 2025-05-22
 * @Description:
 * @Version: 1.0
 */
public class CarMapperTest {
    @Test
    public void testDeleteByIds2(){
        SqlSession sqlSession = SqlSessionUtil.openSession();
        CarMapper mapper = sqlSession.getMapper(CarMapper.class);
        Long ids[] = new Long[]{131L,132L,133L};
        int count = mapper.deleteByIds2(ids);
        System.out.println("数据库该表条数：" + count);
        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void testInsertByList() {
        SqlSession sqlSession = SqlSessionUtil.openSession();
        CarMapper mapper = sqlSession.getMapper(CarMapper.class);
        List<Car> list = new ArrayList<>();
        Car car1 = new Car(null,"10001","比亚迪秦",30.1,"2025-01-02","新能源");
        Car car2 = new Car(null,"10002","比亚迪汉",31.1,"2024-01-02","新能源1");
        Car car3 = new Car(null,"10003","比亚迪送",34.1,"2023-01-02","新能源3");
        list.add(car1);
        list.add(car2);
        list.add(car3);
        int count = mapper.insertByList(list);
        System.out.println("数据库该表条数：" + count);
        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void testDeleteByIds(){
        Long ids[] = new Long[]{117L,118L,119L};
        SqlSession sqlSession = SqlSessionUtil.openSession();
        CarMapper mapper = sqlSession.getMapper(CarMapper.class);
        int count = mapper.deleteByIds(ids);
        System.out.println(count);
        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void testSelectByChoose() {
        String brand = null;
        //String brand = "比亚迪";
        Double guidePrice = null;
        //Double guidePrice = 4.5;
        String carType = null;
        //String carType = "燃油车";
        SqlSession sqlSession = SqlSessionUtil.openSession();
        CarMapper mapper = sqlSession.getMapper(CarMapper.class);
        List<Car> cars = mapper.selectByChoose(brand,guidePrice,carType);
        System.out.println(cars);
    }

    @Test
    public void testUpdateCarWithSet() {
        SqlSession sqlSession = SqlSessionUtil.openSession();
        CarMapper mapper = sqlSession.getMapper(CarMapper.class);
        Car car = new Car(1L,null,"宝马520Li888",10888.8,"2088-88-88",null);
        int count = mapper.updateCarWithSet(car);
        System.out.println(count);
        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void testUpdateCar() {
        SqlSession sqlSession = SqlSessionUtil.openSession();
        CarMapper mapper = sqlSession.getMapper(CarMapper.class);
        Car car = new Car(1L,"100999","宝马520Li999",10999.9,"2099-99-99","新能源999");
        //Car car = new Car(1L,null,"宝马520Li999",null,"2099-99-99","新能源999");
        int count = mapper.updateCar(car);
        System.out.println(count);
        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void testSelectByMultiConditionWithTrim(){
        String brand = null;
        //String brand = "比亚迪";
        //Double guidePrice = null;
        Double guidePrice = 80.0;
        //String carType = null;
        String carType = "燃油车";
        SqlSession sqlSession = SqlSessionUtil.openSession();
        CarMapper mapper = sqlSession.getMapper(CarMapper.class);
        List<Car> cars = mapper.selectByMultiConditionWithTrim(brand,guidePrice,carType);
        System.out.println(cars);
    }

    @Test
    public void testSelectByMultiConditionWithWhere(){
        String brand = null;
        //String brand = "比亚迪";
        //Double guidePrice = null;
        Double guidePrice = 80.0;
        //String carType = null;
        String carType = "燃油车";
        SqlSession sqlSession = SqlSessionUtil.openSession();
        CarMapper mapper = sqlSession.getMapper(CarMapper.class);
        List<Car> cars = mapper.selectByMultiConditionWithWhere(brand,guidePrice,carType);
        System.out.println(cars);
    }

    @Test
    public void testSelectByMultiCondition(){
        String brand = null;
        //String brand = "比亚迪";
        Double guidePrice = null;
        //Double guidePrice = 80.0;
        String carType = null;
        //String carType = "燃油车";
        SqlSession sqlSession = SqlSessionUtil.openSession();
        CarMapper mapper = sqlSession.getMapper(CarMapper.class);
        List<Car> cars = mapper.selectByMultiCondition(brand,guidePrice,carType);
        System.out.println(cars);
    }
}
