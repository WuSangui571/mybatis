package com.sangui.mapper;


import com.sangui.pojo.Car;

/**
 * @Author: sangui
 * @CreateTime: 2025-05-24
 * @Description:
 * @Version: 1.0
 */
public interface CarMapper {
    Car selectById(Long id);
}
