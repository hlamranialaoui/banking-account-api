package com.banking.account.model;

import java.math.BigDecimal;

public class Amount {
    public BigDecimal amount;

    public Amount() {
        amount = new BigDecimal(0);
    }
    public Amount(BigDecimal amount) {
        this.amount = amount;
    }
    public BigDecimal add(BigDecimal amount) {
        return this.amount =this.amount.add(amount);
    }

    public BigDecimal subtract(BigDecimal amount) {
        return this.amount =this.amount.subtract(amount);
    }

    public BigDecimal getCurrentAmount() {
        return this.amount;
    }
}
