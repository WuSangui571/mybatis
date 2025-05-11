package com.sangui.mybatis.test;


import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

/**
 * @Author: sangui
 * @CreateTime: 2025-05-11
 * @Description: MyBatis入门程序
 * @Version: 1.0
 */
public class MyBatisIntroduceTest {
    public static void main(String[] args) throws IOException {
        // 获取SqlSessionFactoryBuilder对象
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();

        // 获取 SqlSessionFactory 对象
        // getResourceAsStream默认从类的根路径下开始查找资源
        //InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
        InputStream inputStream = ClassLoader.getSystemClassLoader().getResourceAsStream("mybatis-config.xml");
        // 一般情况下都是一个数据库对应一个 SqlSessionFactory 对象
        SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBuilder.build(inputStream);

        // 获取SqlSession对象
        SqlSession sqlSession = sqlSessionFactory.openSession();
        // 这种方式实际上是不建议的，因为没有开启事务。
        //SqlSession sqlSession = sqlSessionFactory.openSession(true);

        // 执行SQL语句
        // count是返回数据库中影响的条数
        int count = sqlSession.insert("insertCar");

        // 手动提交
        sqlSession.commit();

        System.out.println("插入了几条记录：" + count);
    }
}
