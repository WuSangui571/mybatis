package com.sangui.mybatis.test;


import org.apache.ibatis.io.Resources;
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

        // 获取SqlSessionFactory对象
        // getResourceAsStream默认从类的根路径下开始查找资源
        InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
        // 一般情况下都是一个数据库对应一个SqlSessionFactory对象
        SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBuilder.build(inputStream);

        // 获取SqlSession对象
        SqlSession sqlSession = sqlSessionFactory.openSession();

        // 执行SQL语句
        // count是返回数据库中影响的条数
        int count = sqlSession.insert("insertCar");

        sqlSession.commit();

        System.out.println("插入了几条记录：" + count);


    }
}
