package com.sangui.bank.pojo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: sangui
 * @CreateTime: 2025-05-18
 * @Description: 普通 Java 类
 * @Version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Account {
    private Long id;
    private String actno;
    private Double balance;
}
