package com.sangui.sanguicp;


import javax.sql.DataSource;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.*;
import java.util.LinkedList;
import java.util.Properties;
import java.util.logging.Logger;

/**
 * @Author: sangui
 * @CreateTime: 2025-05-24
 * @Description: 我的数据源
 * 使用 getConnection() 方法以获取连接对象 Connection
 * @Version: 1.0
 */
public class SanguiDataSource implements DataSource {
    /**
     * 存放连接对象的集合
     */
    private LinkedList<Connection> pool = new LinkedList<>();

    private static final String DRIVER_CLASS_NAME = "driver";
    private static final String URL = "url";
    private static final String USER = "user";
    private static final String PASSWORD = "password";
    private static final String MAX_TOTAL = "maxTotal";
    private static final String WAIT_TIME = "waitTime";
    private static final String MAX_WAIT_TIME = "maxWaitTime";
    /**
     * 默认连接池最大连接数为 8
     */
    private static final Integer DEFAULT_MAX_TOTAL = 8;
    /**
     * 若池为空，默认等待单轮时间为 100ms，即0.1s
     */
    private static final Integer DEFAULT_WAIT_TIME = 100;
    /**
     * 若池为空，默认最长等待时间为 3000ms，即3s
     */
    private static final Integer DEFAULT_MAX_WAIT_TIME = 3000;

    private static Integer waitTime;
    private static Integer maxWaitTime;
    private static Integer countWaitTime = 0;


    /**
     * 构造数据源对象
     */
    public SanguiDataSource() {
        try {
            // 初始化数据库连接池
            InputStream inputStream = SanguiDataSource.class.getClassLoader().getResourceAsStream("sanguicp.properties");
            Properties properties = new Properties();
            properties.load(inputStream);
            String driver = properties.getProperty(DRIVER_CLASS_NAME);
            String url = properties.getProperty(URL);
            String user = properties.getProperty(USER);
            String password = properties.getProperty(PASSWORD);
            int maxTotal = properties.getProperty(MAX_TOTAL) == null ? DEFAULT_MAX_TOTAL : Integer.parseInt(properties.getProperty(MAX_TOTAL));
            waitTime = properties.getProperty(WAIT_TIME) == null ? DEFAULT_WAIT_TIME : Integer.parseInt(properties.getProperty(WAIT_TIME));
            maxWaitTime = properties.getProperty(MAX_WAIT_TIME) == null ? DEFAULT_MAX_WAIT_TIME : Integer.parseInt(properties.getProperty(MAX_WAIT_TIME));
            Class.forName(driver);
            //System.out.println("池中的 sanguiConnection 连接对象：---------------------------");
            for (int i = 0; i < maxTotal; i++) {
                Connection con = DriverManager.getConnection(url, user, password);
                SanguiConnection sanguiConnection = new SanguiConnection(con,pool);

                //System.out.println(sanguiConnection);
                pool.add(sanguiConnection);
            }
            //System.out.println("-----------------------------------------");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Connection getConnection() throws SQLException {
        // 如果池不空且池中有连接
        if (pool != null && !pool.isEmpty()) {
            return pool.removeFirst();
        }
        // 走到这里说明池为空，
        try {
            // 计算已等待时间有没有超过最长等待时间
            if (countWaitTime < maxWaitTime){
                countWaitTime += waitTime;
                // 要等待，不急
                Thread.sleep(waitTime);
            }else {
                // 已等待时间超过最长等待时间，抛异常
                throw new SQLException("max wait time exceeded,please close database first");
            }
            return getConnection();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Connection getConnection(String username, String password) throws SQLException {
        return null;
    }

    @Override
    public PrintWriter getLogWriter() throws SQLException {
        return null;
    }

    @Override
    public void setLogWriter(PrintWriter out) throws SQLException {

    }

    @Override
    public void setLoginTimeout(int seconds) throws SQLException {

    }

    @Override
    public int getLoginTimeout() throws SQLException {
        return 0;
    }

    @Override
    public Logger getParentLogger() throws SQLFeatureNotSupportedException {
        return null;
    }

    @Override
    public <T> T unwrap(Class<T> iface) throws SQLException {
        return null;
    }

    @Override
    public boolean isWrapperFor(Class<?> iface) throws SQLException {
        return false;
    }
}
