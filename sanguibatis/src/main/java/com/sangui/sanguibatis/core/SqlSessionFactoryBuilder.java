package com.sangui.sanguibatis.core;


import java.io.InputStream;

/**
 * @Author: sangui
 * @CreateTime: 2025-05-16
 * @Description: SqlSessionFactory 构建器对象，通过 SqlSessionFactoryBuilder 的 build 方法来解析 sanguibatis-config.xml 文件，
 * 然后创建 SqlSessionFactory 对象
 * @Version: 1.0
 */
public class SqlSessionFactoryBuilder {
    public SqlSessionFactoryBuilder() {
    }

    /**
     * 解析 sanguibatis-config.xml 文件，来构建 SqlSessionFactory 对象
     * @param inputStream 指向 sanguibatis-config.xml 文件的输入流
     * @return SqlSessionFactory 对象
     */
    public SqlSessionFactory build (InputStream inputStream){
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactory();

        return sqlSessionFactory;
    }
}
