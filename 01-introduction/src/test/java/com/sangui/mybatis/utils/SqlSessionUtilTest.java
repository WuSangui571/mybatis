package com.sangui.mybatis.utils;


import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.io.IOException;

/**
 * @Author: sangui
 * @CreateTime: 2025-05-12
 * @Description: SqlSessionUtilTest
 * @Version: 1.0
 */
public class SqlSessionUtilTest {
    @Test
    public void sqlSessionUtilTest() throws IOException {
        SqlSession sqlSession = SqlSessionUtil.openSession();

        int count = sqlSession.insert("insertCar");
        System.out.println("改变条数：" + count);

        sqlSession.commit();
        sqlSession.close();
    }
}
