package com.sangui.test;


import com.sangui.mapper.StudentMapper;
import com.sangui.pojo.Student;
import com.sangui.utils.SqlSessionUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: sangui
 * @CreateTime: 2025-05-21
 * @Description:
 * @Version: 1.0
 */
public class StudentMapperTest {
    @Test
    public void testSelectByNameAndSex(){
        SqlSession sqlSession = SqlSessionUtil.openSession();
        StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
        String name = "张三";
        Character sex = '男';
        List<Student> students = mapper.selectByNameAndSex(name,sex);
        for (Student student : students) {
            System.out.println(student);
        }
    }

    @Test
    public void testInsertStudentByPojo(){
        SqlSession sqlSession = SqlSessionUtil.openSession();
        StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
        Student student = new Student(null,"李易峰",33,1.77,Date.valueOf("1980-06-09"),'男');
        int count = mapper.insertStudentByPojo(student);
        sqlSession.commit();
        System.out.println("数据库改变条数：" + count);
        sqlSession.close();
    }

    @Test
    public void testInsertStudentByMap(){
        SqlSession sqlSession = SqlSessionUtil.openSession();
        StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
        Map<String, Object> map = new HashMap<>();
        map.put("name", "吴亦凡");
        map.put("age", 23);
        map.put("height",1.88);
        map.put("birth",Date.valueOf("2000-06-06"));
        map.put("sex", '男');
        int count = mapper.insertStudentByMap(map);
        sqlSession.commit();
        System.out.println("数据库改变条数：" + count);
        sqlSession.close();
    }

    @Test
    public void testSelectBySex(){
        SqlSession sqlSession = SqlSessionUtil.openSession();
        StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
        Character sex = '女';
        List<Student> students = mapper.selectBySex(sex);
        for (Student student : students) {
            System.out.println(student);
        }
    }

    @Test
    public void testSelectByBirth(){
        SqlSession sqlSession = SqlSessionUtil.openSession();
        StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
        Date birth = Date.valueOf("1999-11-11");
        List<Student> students = mapper.selectByBirth(birth);
        for (Student student : students) {
            System.out.println(student);
        }
    }

    @Test
    public void testSelectByName(){
        SqlSession sqlSession = SqlSessionUtil.openSession();
        StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
        String name = "张三";
        List<Student> students = mapper.selectByName(name);
        for (Student student : students) {
            System.out.println(student);
        }
    }

    @Test
    public void testSelectById(){
        SqlSession sqlSession = SqlSessionUtil.openSession();
        StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
        Long id = 2L;
        List<Student> students = mapper.selectById(id);
        for (Student student : students) {
            System.out.println(student);
        }
    }
}
