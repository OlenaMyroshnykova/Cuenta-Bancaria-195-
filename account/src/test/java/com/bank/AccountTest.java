package com.bank;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AccountTest {

    @Test
    void savingsAccountDepositAndWithdraw() {
        SavingsAccount account = new SavingsAccount(12000, 5);
        account.deposit(2000);
        assertEquals(14000, account.balance);

        assertTrue(account.withdraw(5000));
        assertEquals(9000, account.balance);
    }

    @Test
    void savingsAccountInactiveBelowThreshold() {
        SavingsAccount account = new SavingsAccount(8000, 5);
        assertFalse(account.withdraw(1000)); // Счет неактивен
    }

    @Test
    void checkingAccountOverdraft() {
        CheckingAccount account = new CheckingAccount(5000, 5);
        assertTrue(account.withdraw(7000));
        assertEquals(0, account.balance);
        assertEquals(2000, account.overdraft);
    }

    @Test
    void checkingAccountOverdraftRepayment() {
        CheckingAccount account = new CheckingAccount(5000, 5);
        account.withdraw(7000);
        account.deposit(3000);
        assertEquals(0, account.overdraft);
        assertEquals(1000, account.balance);
    }
}

