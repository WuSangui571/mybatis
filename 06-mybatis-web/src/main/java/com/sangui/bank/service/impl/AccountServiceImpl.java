package com.sangui.bank.service.impl;


import com.sangui.bank.dao.AccountDao;
import com.sangui.bank.exceptions.MoneyNotEnoughException;
import com.sangui.bank.exceptions.TransferException;
import com.sangui.bank.dao.impl.AccountDaoImpl;
import com.sangui.bank.pojo.Account;
import com.sangui.bank.service.AccountService;
import com.sangui.bank.utils.GenerateDaoProxy;
import com.sangui.bank.utils.SqlSessionUtil;
import org.apache.ibatis.session.SqlSession;

/**
 * @Author: sangui
 * @CreateTime: 2025-05-18
 * @Description: 账户业务类实现
 * @Version: 1.0
 */
public class AccountServiceImpl implements AccountService {
    //AccountDao accountDao = new AccountDaoImpl();
    AccountDao accountDao = (AccountDao) GenerateDaoProxy.generate(SqlSessionUtil.openSession(), AccountDao.class);
    @Override
    public void transfer(String fromActno, String toActno, double money) throws MoneyNotEnoughException,TransferException {
        SqlSession sqlSession = SqlSessionUtil.openSession();
        int count1 = 0;
        int count2 = 0;

        // 1.判断转出账户的余额是否充足（select）
        Account fromAct = accountDao.selectByActno(fromActno);
        Double fromActBalance = fromAct.getBalance();
        if (fromActBalance < money) {
            // 2.不足？提示用户
            throw new MoneyNotEnoughException("对不起，余额不足");
        }
        fromAct.setBalance(fromActBalance - money);
        Account toAct = accountDao.selectByActno(toActno);
        Double toActBalance = toAct.getBalance();
        toAct.setBalance(toActBalance + money);

        // 3.足，更新转出账户余额（update）
        count1 = accountDao.updateByAccount(fromAct);
        // 3.足，更新转入账户余额（update）
        count2 = accountDao.updateByAccount(toAct);
        if (count1 + count2 != 2) {
            sqlSession.rollback();
            throw new TransferException("交易异常！");
        }

        sqlSession.commit();
        SqlSessionUtil.close(sqlSession);
        System.out.println("转出账号修改条数：" + count1 + "转入账号修改条数：" + count2);

    }
}
