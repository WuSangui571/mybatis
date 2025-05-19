package com.sangui.bank.exceptions;


/**
 * @Author: sangui
 * @CreateTime: 2025-05-18
 * @Description: 余额不足异常
 * @Version: 1.0
 */
public class MoneyNotEnoughException extends Exception{
    public MoneyNotEnoughException(){}
    public MoneyNotEnoughException(String message){
        super(message);
    }
}
