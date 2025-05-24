package com.sangui.mapper;


import com.sangui.pojo.Clazz;

/**
 * @Author: sangui
 * @CreateTime: 2025-05-23
 * @Description:
 * @Version: 1.0
 */
public interface ClazzMapper {
    Clazz selectByCidWithStep(Integer cid);

    Clazz selectByCid(Integer cid);

    Clazz selectBySidStep2(Integer cid);
}
