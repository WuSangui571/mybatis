package com.sangui.mapper;


import com.sangui.pojo.Car;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author: sangui
 * @CreateTime: 2025-05-22
 * @Description:
 * @Version: 1.0
 */
public interface CarMapper {
    /**
     * 根据 id 批量删除
     * delete from t_car where id = 1 or id = 2 or id = 3
     * @param ids
     * @return
     */
    int deleteByIds2(@Param("ids") Long[] ids);

    /**
     * 批量插入 Car
     * @param list
     * @return
     */
    int insertByList(@Param("list") List<Car> list);

    /**
     * 根据 id 批量删除
     * delete from t_car where id in (1,2,3)
     * @param ids
     * @return
     */
    int deleteByIds(@Param("ids") Long[] ids);

    /**
     * 使用 choose 标签查询
     * @param brand
     * @param guidePrice
     * @param carType
     * @return
     */
    List<Car> selectByChoose(@Param("brand") String brand,@Param("guidePrice") Double guidePrice,@Param("carType") String carType);

    /**
     * 使用 set 标签进行 update
     * 可以实现数据库中一条数据的局部改，字段值为 null 的字段不修改
     * @param car
     * @return
     */
    int updateCarWithSet(Car car);

    /**
     * 不使用 set 标签进行 update
     * @param car
     * @return
     */
    int updateCar(Car car);

    /**
     * 使用 Trim 标签进行多条件查询
     * @param brand 汽车品牌
     * @param guidePrice 指导价格
     * @param carType 汽车类型
     * @return 查询到的集合
     */
    List<Car> selectByMultiConditionWithTrim(
            @Param("brand") String brand,
            @Param("guidePrice") Double guidePrice,
            @Param("carType") String carType);

    /**
     * 使用 where 标签进行多条件查询
     * @param brand 汽车品牌
     * @param guidePrice 指导价格
     * @param carType 汽车类型
     * @return 查询到的集合
     */
    List<Car> selectByMultiConditionWithWhere(
            @Param("brand") String brand,
            @Param("guidePrice") Double guidePrice,
            @Param("carType") String carType);

    /**
     * 多条件查询
     * @param brand 汽车品牌
     * @param guidePrice 指导价格
     * @param carType 汽车类型
     * @return 查询到的集合
     */
    List<Car> selectByMultiCondition(
            @Param("brand") String brand,
            @Param("guidePrice") Double guidePrice,
            @Param("carType") String carType);
}
