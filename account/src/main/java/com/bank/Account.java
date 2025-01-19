package com.bank;

public abstract class Account {
    protected float balance;
    protected int depositCount;
    protected int withdrawalCount;
    protected float annualInterestRate;
    protected float monthlyCommission;

    public Account(float balance, float annualInterestRate) {
        this.balance = balance;
        this.annualInterestRate = annualInterestRate;
        this.depositCount = 0;
        this.withdrawalCount = 0;
        this.monthlyCommission = 0;
    }

    public void deposit(float amount) {
        if (amount > 0) {
            balance += amount;
            depositCount++;
        }
    }

    public boolean withdraw(float amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            withdrawalCount++;
            return true;
        }
        return false;
    }

    public void calculateMonthlyInterest() {
        float monthlyInterest = balance * (annualInterestRate / 12 / 100);
        balance += monthlyInterest;
    }

    public void monthlyStatement() {
        balance -= monthlyCommission;
        calculateMonthlyInterest();
    }

    public String getDetails() {
        return "Balance: " + balance + "\nMonthly Commission: " + monthlyCommission +
               "\nTotal Transactions: " + (depositCount + withdrawalCount);
    }
}

