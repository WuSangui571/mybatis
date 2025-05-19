package com.sangui.bank.exceptions;


/**
 * @Author: sangui
 * @CreateTime: 2025-05-18
 * @Description: 转账异常
 * @Version: 1.0
 */
public class TransferException extends Exception{
    public TransferException(){}
    public TransferException(String message){
        super(message);
    }
}
