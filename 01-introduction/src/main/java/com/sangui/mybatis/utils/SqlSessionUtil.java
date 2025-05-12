package com.sangui.mybatis.utils;


import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;

/**
 * @Author: sangui
 * @CreateTime: 2025-05-12
 * @Description: MyBatis工具类
 * @Version: 1.0
 */
public class SqlSessionUtil {
    // 私有，防止new对象
    private SqlSessionUtil(){
    }

    public static SqlSession openSession(String configFile){
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
        SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBuilder.build(Resources.getResourceAsStream(configFile));
        return sqlSessionFactory.openSession();

}
