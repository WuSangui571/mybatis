package com.sangui.jdbc;


import java.sql.*;

/**
 * @Author: sangui
 * @CreateTime: 2025-05-24
 * @Description:
 * @Version: 1.0
 */
public class JDBCTest {
    public static void main(String[] args) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            // 1.注册驱动
            Class.forName("com.mysql.cj.jdbc.Driver");
            // 2.获取连接
            String url = "jdbc:mysql://localhost:3306/xxxxxxx";
            String user = "root";
            String password = "xxxxxxx";
            conn = DriverManager.getConnection(url, user, password);
            // 3.获取数据库操纵对象
            String sql = "SELECT * FROM t_car";
            ps = conn.prepareStatement(sql);
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
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            // 6.释放资源
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
