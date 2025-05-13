package com.sangui.mybatis;

import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

/**
 * @Author: sangui
 * @CreateTime: 2025-05-13
 * @Description:
 * @Version: 1.0
 */
public class TestConfig {
    @Test
    public void testEnvironment(){
        // 获取SqlSessionFactory对象
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build()
    }
}
