package com.sangui.bank.service;


import com.sangui.bank.exceptions.MoneyNotEnoughException;
import com.sangui.bank.exceptions.TransferException;

/**
 * @Author: sangui
 * @CreateTime: 2025-05-18
 * @Description: 账户业务类接口
 * @Version: 1.0
 */
public interface AccountService {
    /**
     * 完成银行账户转账业务
     * @param fromActno 转出账号
     * @param toActno 转入账号
     * @param money 转账金额
     */
    void transfer(String fromActno, String toActno, double money) throws MoneyNotEnoughException, TransferException;
}
