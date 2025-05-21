package com.sangui.utils;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

/**
 * @Author: sangui
 * @CreateTime: 2025-05-21
 * @Description: MyBatis 工具类，线程安全获取 SqlSession、事务管理、Mapper 获取
 * @Version: 2.0
 */
public class SqlSessionUtil {
    // 防止实例化
    private SqlSessionUtil() {
    }

    private static SqlSessionFactory sqlSessionFactory;
    private static final ThreadLocal<SqlSession> THREAD_LOCAL = new ThreadLocal<>();

    static {
        init("mybatis-config.xml");
    }

    /**
     * 初始化 SqlSessionFactory
     *
     * @param configLocation MyBatis 配置文件路径
     */
    public static void init(String configLocation) {
        try (InputStream in = Resources.getResourceAsStream(configLocation)) {
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(in);
        } catch (IOException e) {
            throw new ExceptionInInitializerError("初始化 SqlSessionFactory 失败: " + e.getMessage());
        }
    }

    /**
     * 获取默认事务（手动提交）的 SqlSession
     */
    public static SqlSession openSession() {
        return openSession(false);
    }

    /**
     * 获取 SqlSession
     *
     * @param autoCommit 是否自动提交事务
     */
    public static SqlSession openSession(boolean autoCommit) {
        SqlSession session = THREAD_LOCAL.get();
        if (session == null) {
            session = sqlSessionFactory.openSession(autoCommit);
            THREAD_LOCAL.set(session);
        }
        return session;
    }

    /**
     * 提交当前线程的事务
     */
    public static void commit() {
        SqlSession session = THREAD_LOCAL.get();
        if (session != null) {
            session.commit();
        }
    }

    /**
     * 回滚当前线程的事务
     */
    public static void rollback() {
        SqlSession session = THREAD_LOCAL.get();
        if (session != null) {
            session.rollback();
        }
    }

    /**
     * 关闭 SqlSession 并移除线程绑定
     */
    public static void closeSession() {
        SqlSession session = THREAD_LOCAL.get();
        if (session != null) {
            session.close();
            THREAD_LOCAL.remove();
        }
    }

    /**
     * 快速获取 Mapper
     *
     * @param type Mapper 接口类型
     * @param <T>  Mapper 类型
     * @return Mapper 实例
     */
    public static <T> T getMapper(Class<T> type) {
        return openSession().getMapper(type);
    }
}
