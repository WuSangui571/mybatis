package com.sangui.mapper;

import com.sangui.pojo.Car;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CarMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Car record);

    int insertSelective(Car record);

    Car selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Car record);

    int updateByPrimaryKey(Car record);

    List<Car> selectAll(@Param("startIndex") int startIndex,@Param("pageSize") int pageSize);

    List<Car> selectAllWithPageHelper();


}