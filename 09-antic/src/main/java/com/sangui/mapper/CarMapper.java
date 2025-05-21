package com.sangui.mapper;


import com.sangui.pojo.Car;

import java.util.List;

/**
 * @Author: sangui
 * @CreateTime: 2025-05-21
 * @Description:
 * @Version: 1.0
 */
public interface CarMapper {
    int insert(Car car);
    int deleteById(Long id);
    int update(Car car);
    Car selectById(Long id);
    List<Car> selectAll();
    List<Car> selectByCarType(String carType);
    List<Car> selectGuidePriceByAscOrDesc(String descOrEsc);
    int deleteBatch(String ids);
    List<Car> selectByBrandLike(String brand);
}
