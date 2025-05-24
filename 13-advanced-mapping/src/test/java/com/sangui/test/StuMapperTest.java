package com.sangui.test;


import com.sangui.mapper.StuMapper;
import com.sangui.pojo.Stu;
import com.sangui.utils.SqlSessionUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

/**
 * @Author: sangui
 * @CreateTime: 2025-05-23
 * @Description:
 * @Version: 1.0
 */
public class StuMapperTest {
    @Test
    public void testSelectBySidStep(){
        SqlSession sqlSession = SqlSessionUtil.openSession();
        StuMapper mapper = sqlSession.getMapper(StuMapper.class);
        Integer sid = 5;
        Stu stu = mapper.selectBySidStep1(sid);
        System.out.println(stu.getSid());
        System.out.println(stu);
    }

    @Test
    public void testSelectBySidAssociation(){
        SqlSession sqlSession = SqlSessionUtil.openSession();
        StuMapper mapper = sqlSession.getMapper(StuMapper.class);
        Integer sid = 4;
        Stu stu = mapper.selectBySidAssociation(sid);
        System.out.println(stu);
    }

    @Test
    public void testSelectBySid(){
        SqlSession sqlSession = SqlSessionUtil.openSession();
        StuMapper mapper = sqlSession.getMapper(StuMapper.class);
        Integer sid = 2;
        Stu stu = mapper.selectBySid(sid);
        System.out.println(stu);
    }
}
