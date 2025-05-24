package com.sangui.sanguicp.test;


import com.sangui.sanguicp.SanguiDataSource;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @Author: sangui
 * @CreateTime: 2025-05-24
 * @Description: 主要传达的是 连接池的思想(缓存技术的思想)和装饰器的设计模式
 * @Version: 1.0
 */
public class MyDataSourceTest {
    @Test
    public void testConnection() throws Exception {
        PreparedStatement ps;
        ResultSet rs;

        SanguiDataSource sanguiDataSource = new SanguiDataSource();

        Connection connection = sanguiDataSource.getConnection();

        String sql = "select * from t_car";
        ps = connection.prepareStatement(sql);
        // 4.执行SQL语句
        rs = ps.executeQuery();
        // 5.处理结果集
        while (rs.next()) {
            System.out.println();
            String id = rs.getString("id");
            System.out.print(id + "    ");
            String carNum = rs.getString("car_num");
            System.out.print(carNum + "    ");
            String brand = rs.getString("brand");
            System.out.print(brand + "    ");
            String guidePrice = rs.getString("guide_price");
            System.out.print(guidePrice + "    ");
            String produceTime = rs.getString("produce_time");
            System.out.print(produceTime + "    ");
            String carType = rs.getString("car_type");
            System.out.print(carType);
        }
    }
    @Test
    public void testSanguiConnection() throws Exception {
        SanguiDataSource sanguiDataSource = new SanguiDataSource();

        Connection connection1 = sanguiDataSource.getConnection();
        System.out.println("connection1：" + connection1);

        Connection connection2 = sanguiDataSource.getConnection();
        System.out.println("connection2：" + connection2);

        Connection connection3 = sanguiDataSource.getConnection();
        System.out.println("connection3：" + connection3);


        //sanguiDataSource.close(connection3);
        connection3.close();

        Connection connection4 = sanguiDataSource.getConnection();
        System.out.println("connection4：" + connection4);
    }
    @Test
    public void testSanguiDataSource() throws Exception {
        SanguiDataSource sanguiDataSource = new SanguiDataSource();

        Connection connection1 = sanguiDataSource.getConnection();
        System.out.println("connection1：" + connection1);

        Connection connection2 = sanguiDataSource.getConnection();
        System.out.println("connection2：" + connection2);

        Connection connection3 = sanguiDataSource.getConnection();
        System.out.println("connection3：" + connection3);


        connection3.close();

        Connection connection4 = sanguiDataSource.getConnection();
        System.out.println("connection4：" + connection4);
    }
}
