package com.bank;

public class SavingsAccount extends Account {
    private boolean isActive;

    public SavingsAccount(float balance, float annualInterestRate) {
        super(balance, annualInterestRate);
        updateAccountStatus();
    }

    private void updateAccountStatus() {
        isActive = balance >= 10000;
    }

    @Override
    public void deposit(float amount) {
        if (isActive) {
            super.deposit(amount);
            updateAccountStatus();
        }
    }

    @Override
    public boolean withdraw(float amount) {
        if (isActive && super.withdraw(amount)) {
            updateAccountStatus();
            return true;
        }
        return false;
    }

    @Override
    public void monthlyStatement() {
        if (withdrawalCount > 4) {
            monthlyCommission += (withdrawalCount - 4) * 1000;
        }
        super.monthlyStatement();
        updateAccountStatus();
    }

    @Override
    public String getDetails() {
        return super.getDetails() + "\nAccount Status: " + (isActive ? "Active" : "Inactive");
    }
}

