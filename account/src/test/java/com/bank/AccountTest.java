package com.bank;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AccountTest {
    private Account account;

    @BeforeEach
    void setUp() {
        account = new Account(10000, 5) {}; 
    }

    @Test
    void testInitialBalance() {
        assertEquals(10000, account.balance, 0.01);
    }

    @Test
    void testDepositPositiveAmount() {
        account.deposit(5000);
        assertEquals(15000, account.balance, 0.01);
        assertEquals(1, account.depositCount);
    }

    @Test
    void testDepositNegativeAmount() {
        account.deposit(-1000);
        assertEquals(10000, account.balance, 0.01); 
        assertEquals(0, account.depositCount);
    }

    @Test
    void testWithdrawValidAmount() {
        assertTrue(account.withdraw(3000));
        assertEquals(7000, account.balance, 0.01);
        assertEquals(1, account.withdrawalCount);
    }

    @Test
    void testWithdrawInvalidAmount() {
        assertFalse(account.withdraw(11000));
        assertEquals(10000, account.balance, 0.01);
        assertEquals(0, account.withdrawalCount);
    }

    @Test
    void testWithdrawNegativeAmount() {
        assertFalse(account.withdraw(-500));
        assertEquals(10000, account.balance, 0.01);
        assertEquals(0, account.withdrawalCount);
    }

    @Test
    void testGetDetails() {
        String details = account.getDetails();
        assertTrue(details.contains("Balance: 10000"));
        assertTrue(details.contains("Monthly Commission: 0.0"));
        assertTrue(details.contains("Total Transactions: 0"));
    }
}


