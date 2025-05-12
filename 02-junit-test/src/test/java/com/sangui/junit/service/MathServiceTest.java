package com.sangui.junit.service;


import org.junit.Assert;
import org.junit.Test;

/**
 * @Author: sangui
 * @CreateTime: 2025-05-12
 * @Description: 单元测试类
 * @Version: 1.0
 */
public class MathServiceTest {
    @Test
    public void testAdd(){
        MathService mathService = new MathService();
        // 实际值
        int actual = mathService.sum(1,2);
        // 期望值
        int expected = 3;
        // 加断言进行测试
        Assert.assertEquals(expected,actual);
    }

    @Test
    public void testSub(){
        MathService mathService = new MathService();
        int actual = mathService.sub(2, 1);
        int expected = 1;
        Assert.assertEquals(expected,actual);
    }
}
