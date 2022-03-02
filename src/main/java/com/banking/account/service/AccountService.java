package com.banking.account.service;

import com.banking.account.model.Amount;
import com.banking.account.model.OperationType;
import com.banking.account.model.StatementPrinting;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.isNull;

public class AccountService {
    private Amount balance;
    private List<StatementPrinting> statementPrintings;

    public AccountService() {
        balance = new Amount();
        statementPrintings = new ArrayList<>();
    }

    public Amount deposit(BigDecimal amount) {
        if(!isNull(amount) && isAmountPositive(amount)){
            balance.add(amount);
            statementPrintings.add(new StatementPrinting()
                    .setAmount(new Amount(amount))
                    .setBalance(new Amount(getCurrentAmount()))
                    .setDate(LocalDateTime.now())
                    .setOperation(OperationType.DEPOSIT));
            System.out.println("Deposit with success");
        }else {
            System.out.println("Your amount is null or negative");
        }
        return balance;
    }

    public Amount withdrawal(BigDecimal amount){
        if( !isNull(amount) && isAmountPositive(amount)
                && validateWithdrawal(getCurrentAmount(),amount)){
            balance.subtract(amount);
            statementPrintings.add(new StatementPrinting()
                    .setAmount(new Amount(amount))
                    .setBalance(new Amount(getCurrentAmount()))
                    .setDate(LocalDateTime.now())
                    .setOperation(OperationType.WITHDRAWAL));
            System.out.println("withdrawal with success");
        }else {
            System.out.println("Your withdrawal amount is to big to balance, is null or is negative");
        }
        return balance;
    }

    private boolean validateWithdrawal(BigDecimal balance, BigDecimal amount){
       return balance.compareTo(amount) >= 0;
    }

    public BigDecimal getCurrentAmount() {
        return this.balance.getCurrentAmount();
    }

    public void getStatementsHistory(){
        if(null == statementPrintings || statementPrintings.isEmpty()) {
            System.out.println("History is empty");
        }
        statementPrintings.forEach(System.out::println);
    }

    private boolean isAmountPositive(BigDecimal amount){
        return amount.compareTo(BigDecimal.ZERO) > 0;
    }
}
