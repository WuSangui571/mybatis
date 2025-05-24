package com.sangui.mapper;


import com.sangui.pojo.Stu;

/**
 * @Author: sangui
 * @CreateTime: 2025-05-23
 * @Description:
 * @Version: 1.0
 */
public interface StuMapper {
    Stu selectByCidForClass(Integer sid);

    Stu selectBySidStep1(Integer sid);

    // 一条SQL语句，association
    Stu selectBySidAssociation(Integer sid);

    // 一条SQL语句，级联属性映射
    Stu selectBySid(Integer sid);
}
