package com.banking.account.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.isNull;

public class Account {
    private Amount balance;
    private List<StatementPrinting> statementPrintings;
    private OperationType operation;

    public Account() {
        balance = new Amount();
        statementPrintings = new ArrayList<>();
    }

    public Amount deposit(BigDecimal amount) {
        if(!isNull(amount) && operation.getName().equals(OperationType.DEPOSIT)){
            balance.add(amount);
            statementPrintings.add(new StatementPrinting()
                    .setAmount(new Amount(amount))
                    .setBalance(new Amount(this.balance.getCurrentAmount()))
                    .setDate(LocalDateTime.now())
                    .setOperation(OperationType.DEPOSIT));
            System.out.println("Deposit with success");
        }else {
            System.out.println("Your amount is null");
        }
        return balance;
    }

    public Amount withdrawal(BigDecimal amount){
        if( !isNull(amount)
                && operation.getName().equals(OperationType.WITHDRAWAL)
                && validateWithdrawal(this.balance.getCurrentAmount(),amount)){
            balance.subtract(amount);
            statementPrintings.add(new StatementPrinting()
                    .setAmount(new Amount(amount))
                    .setBalance(new Amount(this.balance.getCurrentAmount()))
                    .setDate(LocalDateTime.now())
                    .setOperation(OperationType.WITHDRAWAL));
            System.out.println("withdrawal with success");
        }else {
            System.out.println("Your withdarwal amount is to big to balance or is null");
        }
        return balance;
    }

    private boolean validateWithdrawal(BigDecimal balance, BigDecimal amount){
       return balance.compareTo(amount) >= 0;
    }

    public BigDecimal getCurrentAmount() {
        return this.balance.getCurrentAmount();
    }

    public void getHistory(){
        if(null == statementPrintings || statementPrintings.isEmpty()) {
            System.out.println("History is empty");
        }
        statementPrintings.forEach(System.out::println);
    }
}
