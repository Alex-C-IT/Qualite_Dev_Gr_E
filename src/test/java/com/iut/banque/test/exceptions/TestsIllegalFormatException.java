package com.iut.banque.test.exceptions;

import com.iut.banque.exceptions.IllegalFormatException;
import org.junit.Test;
import static org.junit.Assert.*;

public class TestsIllegalFormatException {

    @Test
    public void testDefaultConstructor() {
        IllegalFormatException exception = new IllegalFormatException();
        assertNull(exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    public void testConstructorWithMessage() {
        String message = "Test Message";
        IllegalFormatException exception = new IllegalFormatException(message);
        assertEquals(message, exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    public void testConstructorWithCause() {
        Throwable cause = new RuntimeException("Test Cause");
        IllegalFormatException exception = new IllegalFormatException("Dummy Message", cause);
        assertEquals("Dummy Message", exception.getMessage());
        assertEquals(cause, exception.getCause());
    }

    @Test
    public void testFullConstructor() {
        String message = "Test Message";
        Throwable cause = new RuntimeException("Test Cause");
        IllegalFormatException exception = new IllegalFormatException(message, cause);
        assertEquals(message, exception.getMessage());
        assertEquals(cause, exception.getCause());
    }

    @Test
    public void testToString() {
        String message = "Test Message";
        Throwable cause = new RuntimeException("Test Cause");
        IllegalFormatException exception = new IllegalFormatException(message, cause);

        String expectedToString = "com.iut.banque.exceptions.IllegalFormatException: Test Message";
        assertEquals(expectedToString, exception.toString());
    }
}