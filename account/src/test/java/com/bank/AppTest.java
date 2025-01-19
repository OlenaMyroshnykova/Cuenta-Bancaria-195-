package com.bank;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class AppTest {
    
    @Test
    public void testAppConstructorInit() {
        App app = new App(); 
        assertNotNull(app); 
    }
}
