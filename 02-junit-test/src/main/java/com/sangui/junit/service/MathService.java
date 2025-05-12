package com.sangui.junit.service;


/**
 * @Author: sangui
 * @CreateTime: 2025-05-12
 * @Description: 数学业务类
 * @Version: 1.0
 */
public class MathService {
    /**
     * 求和业务方法
     * @param a 被加数
     * @param b 加数
     * @return 求和结果
     */
    public int sum(int a, int b) {
        return a + b;
    }

    /**
     * 相减业务方法
      * @param a 被减数
     * @param b 减数
     * @return 相减结果
     */
    public int sub(int a, int b) {
        return a - b;
    }
}
