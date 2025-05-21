package com.sangui.mapper;


import com.sangui.pojo.Student;
import org.apache.ibatis.annotations.Param;

import java.sql.Date;
import java.util.List;
import java.util.Map;

/**
 *@Author: sangui
 *@CreateTime: 2025-05-21
 *@Description: 
 *@Version: 1.0
 */
public interface StudentMapper {
    // 输入参数类型之——简单类型
    List<Student> selectById(Long id);
    List<Student> selectByName(String name);
    List<Student> selectByBirth(Date birth);
    List<Student> selectBySex(Character sex);

    // 输入参数类型之——Map集合
    int insertStudentByMap(Map<String,Object> map);

    // 输入参数类型之——pojo实体
    int insertStudentByPojo(Student student);

    // 输入参数类型之——多参数
//    List<Student> selectByNameAndSex(String name,Character sex);
    // 其中value可以省略不写
    //List<Student> selectByNameAndSex(@Param(value = "name") String name, @Param(value = "sex")Character sex);
    List<Student> selectByNameAndSex(@Param("name") String name, @Param("sex")Character sex);
}
