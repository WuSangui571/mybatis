package com.sangui.bank.dao;


/**
 * @Author: sangui
 * @CreateTime: 2025-05-20
 * @Description:
 * @Version: 1.0
 */
public interface AccountDao {
    void delete();

    int insert(String actno);

    int update(String actno,Double balance);

    String selectByActno(String actno);
}
