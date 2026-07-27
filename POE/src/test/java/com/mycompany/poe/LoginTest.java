package com.mycompany.poe;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class LoginTest {

    @Test
    public void testLoginSuccessful() {
        Login login = new Login("kyl_1", "Ch&&sec@ke99!");
        assertTrue(login.login("kyl_1", "Ch&&sec@ke99!"));
    }

    @Test
    public void testLoginFailed() {
        Login login = new Login("kyl_1", "Ch&&sec@ke99!");
        assertFalse(login.login("kyl_1", "wrongPassword"));
    }
}