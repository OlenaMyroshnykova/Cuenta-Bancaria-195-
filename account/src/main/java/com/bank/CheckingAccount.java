package com.bank;

public class CheckingAccount extends Account {
    public float overdraft;

    public CheckingAccount(float balance, float annualInterestRate) {
        super(balance, annualInterestRate);
        this.overdraft = 0;
    }

    @Override
    public boolean withdraw(float amount) {
        if (amount > 0) {
            if (amount <= balance) {
                balance -= amount;
            } else {
                overdraft += (amount - balance);
                balance = 0;
            }
            withdrawalCount++;
            return true;
        }
        return false;
    }

    @Override
    public void deposit(float amount) {
        if (amount > 0) {
            if (overdraft > 0) {
                if (amount >= overdraft) {
                    amount -= overdraft;
                    overdraft = 0;
                } else {
                    overdraft -= amount;
                    amount = 0;
                }
            }
            super.deposit(amount);
        }
    }

    @Override
    public String getDetails() {
        return super.getDetails() + "\nOverdraft: " + overdraft;
    }

}

