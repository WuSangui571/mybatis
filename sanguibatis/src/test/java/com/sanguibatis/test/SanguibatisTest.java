package com.sanguibatis.test;


import com.sangui.sanguibatis.core.sqlsession.SqlSession;
import com.sangui.sanguibatis.core.sqlsession.SqlSessionFactory;
import com.sangui.sanguibatis.core.sqlsession.SqlSessionFactoryBuilder;
import com.sangui.sanguibatis.utils.Resources;
import org.junit.Test;

/**
 * @Author: sangui
 * @CreateTime: 2025-05-17
 * @Description: 测试程序
 * @Version: 1.0
 */
public class SanguibatisTest {
    @Test
    public void testInsert() {
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
        SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBuilder.build(Resources.getResourceAsStream("sanguibatis-config.xml"));
        SqlSession sqlSession = sqlSessionFactory.openSession(sqlSessionFactory);
    }
    @Test
    public void testSqlSessionFactory() {
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
        SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBuilder.build(Resources.getResourceAsStream("sanguibatis-config.xml"));
        System.out.println(sqlSessionFactory);
    }
}
