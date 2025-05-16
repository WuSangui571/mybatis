package com.sangui.sanguibatis.core;


/**
 * @Author: sangui
 * @CreateTime: 2025-05-16
 * @Description: 一个数据库一般对应一个 SqlSessionFactory 对象。通过 SqlSessionFactory 对象可以获取SqlSession对象（开启会话），
 * 一个 SqlSessionFactory 对象，可以开启多个 SqlSession 对象
 * @Version: 1.0
 */
public class SqlSessionFactory {
    /**
     * 事务管理器属性
     */
    private Transaction transaction;

    public SqlSessionFactory() {
    }
    public SqlSession openSession(SqlSessionFactory sqlSessionFactory) {
        return null;
    }
}
