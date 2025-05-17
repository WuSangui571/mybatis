package com.sangui.sanguibatis.core.sqlsession;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: sangui
 * @CreateTime: 2025-05-16
 * @Description: 专门负责执行 sql 语句的 sql 对象
 * @Version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SqlSession {
    private SqlSessionFactory sqlSessionFactory;

    /**
     * insert()
     * @param sqlId sql 语句的 id
     * @param pojo 实体类
     * @return 数据库改变条数
     */
    public int insert(String sqlId,Object pojo){
        int count = 0;
        try {
            Connection connection = sqlSessionFactory.getTransaction().getConnection();
            String sanguibatisSql = sqlSessionFactory.getMappedStatementMap().get(sqlId).getSql();
            String sql = sanguibatisSql.replaceAll("#\\{[0-9A-Za-z_$]*}", "?");
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            int curIndex = 0;
            int index = 1;
            while (true){
                int jinIndex = sanguibatisSql.indexOf("#", curIndex);
                if (jinIndex < 0){
                    break;
                }
                int youIndex = sanguibatisSql.indexOf("}",curIndex);
                String propertyName = sanguibatisSql.substring(jinIndex + 2, youIndex).trim();
                String getMethodName = "get" + propertyName.toUpperCase().charAt(0) + propertyName.substring(1);
                Method getMethod = pojo.getClass().getDeclaredMethod(getMethodName);
                Object propertyValue = getMethod.invoke(pojo);
                preparedStatement.setObject(index++, propertyValue);
                curIndex = youIndex + 1;
            }
            count = preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 给问号传值
        return count;
    }

    public static void main(String[] args) {
        String sanguibatisSql = "insert into user (id,name,age) values (#{id},#{name},#{age}))";
        int curIndex = 0;
        Map<Integer,String> map = new HashMap<Integer,String>();
        int index = 1;
        while (true){
            int jinIndex = sanguibatisSql.indexOf("#", curIndex);
            if (jinIndex < 0){
                break;
            }

            int youIndex = sanguibatisSql.indexOf("}",curIndex);
            String propertyName = sanguibatisSql.substring(jinIndex + 2, youIndex).trim();
            map.put(index++,propertyName);
            curIndex = youIndex + 1;
        }
        System.out.println(map);
    }

    /**
     * selectOne()
     * @param sqlId sql 语句的 id
     * @param pojo 实体类
     * @return 查询的一个结果
     */
    public Object selectOne(String sqlId, Object pojo) {
        //sqlSessionFactory
        return null;
    }

    /**
     * 提交事务
     */
    public void commit(){
        sqlSessionFactory.getTransaction().commit();
    }

    /**
     * 回滚事务
     */
    public void rollback(){
        sqlSessionFactory.getTransaction().rollback();
    }

    /**
     * 关闭事务
     */
    public void close(){
        sqlSessionFactory.getTransaction().close();
    }
}
