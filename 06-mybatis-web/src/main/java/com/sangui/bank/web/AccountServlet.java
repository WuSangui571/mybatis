package com.sangui.bank.web;


import com.sangui.bank.dao.exceptions.MoneyNotEnoughException;
import com.sangui.bank.dao.exceptions.TransferException;
import com.sangui.bank.service.AccountService;
import com.sangui.bank.service.impl.AccountServiceImpl;
import lombok.SneakyThrows;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author: sangui
 * @CreateTime: 2025-05-18
 * @Description:
 * @Version: 1.0
 */
@WebServlet("/transfer")
public class AccountServlet extends HttpServlet {
    AccountService accountService = new AccountServiceImpl();;
    @SneakyThrows
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String fromActno = request.getParameter("fromActno");
        String toActno = request.getParameter("toActno");
        Double money = Double.valueOf(request.getParameter("money"));
        try {
            accountService.transfer(fromActno, toActno, money);
            response.sendRedirect(request.getContextPath() + "/success.html");
        }catch (MoneyNotEnoughException e) {
            response.sendRedirect(request.getContextPath() + "/error1.html");
        }catch (TransferException e) {
            response.sendRedirect(request.getContextPath() + "/error2.html");
        }

        //response.sendRedirect("account");
    }
}
