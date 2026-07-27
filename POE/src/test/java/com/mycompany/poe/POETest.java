package com.mycompany.poe;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class POETest {

    @Test
    public void testUsernameCorrectlyFormatted() {
        String expected = "Username successfully captured.";
        String actual = POE.checkUsername("kyl_1");
        assertEquals(expected, actual);
    }

    @Test
    public void testUsernameIncorrectlyFormatted() {
        String expected = "Username is not correctly formatted; please ensure that your username contains an underscore and is no more than five characters in length.";
        String actual = POE.checkUsername("kyle!!!!!!");
        assertEquals(expected, actual);
    }

    @Test
    public void testUsernameCorrectlyFormattedBoolean() {
        assertTrue(POE.validateUsername("kyl_1"));
    }

    @Test
    public void testUsernameIncorrectlyFormattedBoolean() {
        assertFalse(POE.validateUsername("kyle!!!!!!"));
    }

    @Test
    public void testPasswordMeetsComplexity() {
        assertTrue(POE.validatePassword("Ch&&sec@ke99!"));
    }

    @Test
    public void testPasswordDoesNotMeetComplexity() {
        assertFalse(POE.validatePassword("password"));
    }

    @Test
    public void testCellPhoneCorrectlyFormatted() {
        assertTrue(POE.validatePhoneNumber("+27838968976"));
    }

    @Test
    public void testCellPhoneIncorrectlyFormatted() {
        assertFalse(POE.validatePhoneNumber("08966553"));
    }
}
