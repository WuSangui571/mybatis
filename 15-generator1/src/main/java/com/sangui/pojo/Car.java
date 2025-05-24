package com.sangui.pojo;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * t_car
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Car implements Serializable {
    /**
     * 主键
     */
    private Long id;

    /**
     * 汽车编号
     */
    private String carNum;

    /**
     * 汽车品牌
     */
    private String brand;

    /**
     * 厂商指导价格
     */
    private Long guidePrice;

    /**
     * 生成日期
     */
    private String produceTime;

    /**
     * 汽车类型：燃油车 新能源 氢能源
     */
    private String carType;

    private static final long serialVersionUID = 1L;
}