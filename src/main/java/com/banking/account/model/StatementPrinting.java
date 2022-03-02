package com.banking.account.model;

import java.time.LocalDateTime;

public class StatementPrinting {
    private OperationType operation;
    private LocalDateTime date;
    private Amount amount;
    private Amount balance;

    public StatementPrinting() {
    }

    public StatementPrinting(OperationType operation, LocalDateTime date, Amount amount, Amount balance) {
        super();
        this.operation = operation;
        this.date = date;
        this.amount = amount;
        this.balance = balance;
    }

    public StatementPrinting setOperation(OperationType operation) {
        this.operation = operation;
        return this;
    }

    public StatementPrinting setDate(LocalDateTime date) {
        this.date = date;
        return this;
    }

    public StatementPrinting setAmount(Amount amount) {
        this.amount = amount;
        return this;
    }

    public StatementPrinting setBalance(Amount balance) {
        this.balance = balance;
        return this;
    }

    @Override
    public String toString() {
        return "Statement [operation=" + operation.getName() + ", date=" + date + ", amount=" + amount + ", balance=" + balance
                + "]";
    }
}
