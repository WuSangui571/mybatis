package com.sangui.mybatis;

import com.sangui.pojo.Car;
import com.sangui.pojo.Student;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;

/**
 * @Author: sangui
 * @CreateTime: 2025-05-13
 * @Description:
 * @Version: 1.0
 */
public class TestConfig {
    @Test
    public void testPoolMaximumActivateConnections() throws IOException {
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
        // 一个数据库一个 sqlSessionFactory 对象，不要频繁创建这个对象，没有意义
        SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBuilder.build(Resources.getResourceAsStream("mybatis-config.xml"));
        for (int i = 0; i < 4; i++) {
            SqlSession sqlSession = sqlSessionFactory.openSession();
            Car car = new Car();
            car.setBrand("wj");
            car.setCarNum("1001");
            car.setCarType("sb");
            car.setProduceTime("2025-01-01");
            car.setGuidePrice(99.1);
            int count = sqlSession.insert("CarMapper.insertCar",car);
            System.out.println("默认数据库改变条数：" + count);
            sqlSession.commit();

        }
    }
    @Test
    public void testDataSource() throws Exception {
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
        // 一个数据库一个 sqlSessionFactory 对象，不要频繁创建这个对象，没有意义
        SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBuilder.build(Resources.getResourceAsStream("mybatis-config.xml"));

        // 会话1
        SqlSession sqlSession = sqlSessionFactory.openSession();
        Car car = new Car();
        car.setBrand("wj");
        car.setCarNum("1001");
        car.setCarType("sb");
        car.setProduceTime("2025-01-01");
        car.setGuidePrice(99.1);
        int count = sqlSession.insert("CarMapper.insertCar",car);
        System.out.println("默认数据库改变条数：" + count);
        sqlSession.commit();
        sqlSession.close();

        // 会话2、
        SqlSession sqlSession2 = sqlSessionFactory.openSession();
        Car car2 = new Car();
        car2.setBrand("wj2");
        car2.setCarNum("10012");
        car2.setCarType("sb2");
        car2.setProduceTime("2025-01-02");
        car2.setGuidePrice(92.1);
        int count2 = sqlSession2.insert("CarMapper.insertCar",car2);
        System.out.println("默认数据库改变条数：" + count2);
        sqlSession2.commit();
        sqlSession.close();


    }
    @Test
    public void testEnvironment() throws Exception {
        // 获取SqlSessionFactory对象
        SqlSessionFactory sqlSessionFactory1 = new SqlSessionFactoryBuilder()
                .build(Resources.getResourceAsStream("mybatis-config.xml"));
        SqlSessionFactory sqlSessionFactory2 = new SqlSessionFactoryBuilder()
                .build(Resources.getResourceAsStream("mybatis-config.xml"),"test");

        SqlSession sqlSession1 = sqlSessionFactory1.openSession();
        Car car = new Car();
        car.setBrand("wj");
        car.setCarNum("1001");
        car.setCarType("sb");
        car.setGuidePrice(99.1);
        int count = sqlSession1.insert("insertCar",car);
        System.out.println("默认数据库改变条数：" + count);
        sqlSession1.commit();
        sqlSession1.close();
        SqlSession sqlSession2 = sqlSessionFactory2.openSession();
        Student student = new Student();
        student.setAge(10);
        student.setEmail("email");
        student.setName("zhangsan");
        int count2 = sqlSession2.insert("StudentMapper.insertStudent", student);
        System.out.println("非默认数据库改变条数：" + count2);
        sqlSession2.commit();
        sqlSession2.close();

    }

}
