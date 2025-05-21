package com.sangui.mapper;


import com.sangui.pojo.Car;
import org.apache.ibatis.annotations.MapKey;

import java.util.List;
import java.util.Map;

/**
 * @Author: sangui
 * @CreateTime: 2025-05-21
 * @Description:
 * @Version: 1.0
 */
public interface CarMapper {
    int selectCount();

    @MapKey("id")
    Map<Long,Map<String, Object>> selectAllReturnBigMaps();

    List<Map<String, Object>> selectAllReturnMaps();

    Map<String, Object> selectByIdReturnMap(Long id);

    Car selectById(Long id);

    List<Car> selectAll();

    // 我故意使用 Car对象返回，而不用 List<Car> ，看看会发生什么
    // 若模糊查询的结果集只有一条，则不会报错，若结果集有多个，则会报以下错：
    // org.apache.ibatis.exceptions.TooManyResultsException: Expected one result (or null) to be returned by selectOne(), but found: 6
    // Car selectByBrandLike(String brand);
    List<Car> selectByBrandLike(String brand);

}
