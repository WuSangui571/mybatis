package com.sangui.bank.dao;


import com.sangui.bank.pojo.Account;

/**
 * @Author: sangui
 * @CreateTime: 2025-05-18
 * @Description: 账户 DAO 接口
 * @Version: 1.0
 */
public interface AccountDao {
    /**
     * 根据账户no查询账户信息
     * @param actno 账户no
     * @return 账户实体
     */
    Account selectByActno(String actno);

    /**
     * 根据账户更改账户
     * @param account 账户
     * @return 账户
     */
    int updateByAccount(Account account);
}
