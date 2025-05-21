package com.sangui.test;


import com.sangui.mapper.LogMapper;
import com.sangui.pojo.Log;
import com.sangui.utils.SqlSessionUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;

/**
 * @Author: sangui
 * @CreateTime: 2025-05-21
 * @Description:
 * @Version: 1.0
 */
public class LogMapperTest {
    @Test
    public void testSelectLogByDate(){
        SqlSession sqlSession = SqlSessionUtil.openSession();
        LogMapper mapper = sqlSession.getMapper(LogMapper.class);
        List<Log> logs = mapper.selectAllTable("20250521");
        for (Log log : logs) {
            System.out.println(log);
        }
    }
}
