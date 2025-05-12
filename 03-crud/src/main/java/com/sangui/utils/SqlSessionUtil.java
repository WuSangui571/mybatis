package com.sangui.utils;


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
    private SqlSessionUtil(){}

    private static SqlSessionFactory sqlSessionFactory;

    // 类加载时执行
    // SqlSessionUtil工具类在第一次加载的时候，解析mybatis-config.xml文件。创建SqlSessionFactory对象。
    static {
        try {
            SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
            sqlSessionFactory = sqlSessionFactoryBuilder.build(Resources.getResourceAsStream("mybatis-config.xml"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取会话对象
     * @return Session对象
     */
    public static SqlSession openSession() throws IOException {
        return sqlSessionFactory.openSession();
    }
}
