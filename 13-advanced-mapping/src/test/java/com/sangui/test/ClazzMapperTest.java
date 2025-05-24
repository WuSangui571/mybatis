package com.sangui.test;


import com.sangui.mapper.ClazzMapper;
import com.sangui.pojo.Clazz;
import com.sangui.utils.SqlSessionUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

/**
 * @Author: sangui
 * @CreateTime: 2025-05-23
 * @Description:
 * @Version: 1.0
 */
public class ClazzMapperTest {
    @Test
    public void testSelectByCidWithStep(){
        SqlSession sqlSession = SqlSessionUtil.openSession();
        ClazzMapper mapper = sqlSession.getMapper(ClazzMapper.class);
        Integer cid = 1;
        Clazz clazz = mapper.selectByCidWithStep(cid);
        System.out.println(clazz.getCid());
        System.out.println(clazz);
    }

    @Test
    public void testSelectByCid(){
        SqlSession sqlSession = SqlSessionUtil.openSession();
        ClazzMapper mapper = sqlSession.getMapper(ClazzMapper.class);
        Integer cid = 1;
        Clazz clazz = mapper.selectByCid(cid);
        //Clazz clazz = mapper.selectBySidStep2(cid);
        System.out.println(clazz);
    }
}
