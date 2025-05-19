package com.sangui.bank.dao.impl;


import com.sangui.bank.dao.AccountDao;
import com.sangui.bank.pojo.Account;
import com.sangui.bank.utils.SqlSessionUtil;
import org.apache.ibatis.session.SqlSession;

import java.io.IOException;

/**
 * @Author: sangui
 * @CreateTime: 2025-05-18
 * @Description: 账户 DAO 实现类
 * @Version: 1.0
 */
public class AccountDaoImpl implements AccountDao {
    @Override
    public Account selectByActno(String actno) {
        SqlSession sqlSession = SqlSessionUtil.openSession();
        return sqlSession.selectOne("AccountMapper.selectByActno", actno);
    }

    @Override
    public int updateByAccount(Account account) {
        SqlSession sqlSession = SqlSessionUtil.openSession();
        return sqlSession.update("AccountMapper.updateByAccount", account);
    }
}
