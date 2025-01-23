package com.bank;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CheckingAccountTest {
    private CheckingAccount account;

    @BeforeEach
    void setUp() {
        account = new CheckingAccount(5000, 5);
    }

    @Test
    void testInitialBalanceAndOverdraft() {
        assertEquals(5000, account.balance, 0.01);
        assertEquals(0, account.getOverdraft(), 0.01);
    }

    @Test
    void testDepositPositiveAmount() {
        account.deposit(2000);
        assertEquals(7000, account.balance, 0.01);
        assertEquals(0, account.getOverdraft(), 0.01);
    }

    @Test
    void testDepositNegativeAmount() {
        account.deposit(-1000);
        assertEquals(5000, account.balance, 0.01);
        assertEquals(0, account.getOverdraft(), 0.01);
    }

    @Test
    void testWithdrawWithinBalance() {
        assertTrue(account.withdraw(3000));
        assertEquals(2000, account.balance, 0.01);
        assertEquals(0, account.getOverdraft(), 0.01);
    }

    @Test
    void testWithdrawExceedingBalance() {
        assertTrue(account.withdraw(7000)); 
        assertEquals(0, account.balance, 0.01);
        assertEquals(2000, account.getOverdraft(), 0.01);
    }

    @Test
    void testWithdrawNegativeAmount() {
        assertFalse(account.withdraw(-500));
        assertEquals(5000, account.balance, 0.01);
        assertEquals(0, account.getOverdraft(), 0.01);
    }

    @Test
    void testDepositReducesOverdraft() {
        account.withdraw(7000); 
        assertEquals(2000, account.getOverdraft(), 0.01);

        account.deposit(1500); 
        assertEquals(500, account.getOverdraft(), 0.01);
        assertEquals(0, account.balance, 0.01); 

        account.deposit(1000); 
        assertEquals(0, account.getOverdraft(), 0.01);
        assertEquals(500, account.balance, 0.01);
    }

    @Test
    void testMonthlyStatement() {
        account.withdraw(6000); 
        account.monthlyStatement(); 

        float expectedBalance = 0 + (0 * 5 / 12 / 100);
        assertEquals(expectedBalance, account.balance, 0.01);
        assertEquals(1000, account.getOverdraft(), 0.01);
    }

    @Test
    void testGetDetails() {
        String details = account.getDetails();
        assertTrue(details.contains("Balance: 5000"));
        assertTrue(details.contains("Monthly Commission: 0.0"));
        assertTrue(details.contains("Total Transactions: 0"));
        assertTrue(details.contains("Overdraft: 0.0"));
    }
}

