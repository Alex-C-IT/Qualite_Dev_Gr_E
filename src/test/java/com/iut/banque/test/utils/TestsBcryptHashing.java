package com.iut.banque.test.utils;

import com.iut.banque.utils.BcryptHashing;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

public class TestsBcryptHashing {

    @Test
    public void testHashPassword() {
        // Arrange
        String password = "mySecretPassword";

        // Act
        String hashedPassword = BcryptHashing.hashPassword(password);

        // Assert
        assertNotNull(hashedPassword);
        assertTrue(hashedPassword.length() > 0);
    }

    @Test
    public void testCheckPasswordMatches() {
        // Arrange
        String password = "mySecretPassword";
        String hashedPassword = BcryptHashing.hashPassword(password);

        // Act
        boolean result = BcryptHashing.checkPassword(password, hashedPassword);

        // Assert
        assertTrue(result);
    }

    @Test
    public void testCheckPasswordNotMatches() {
        // Arrange
        String password = "mySecretPassword";
        String incorrectPassword = "incorrectPassword";
        String hashedPassword = BcryptHashing.hashPassword(password);

        // Act
        boolean result = BcryptHashing.checkPassword(incorrectPassword, hashedPassword);

        // Assert
        assertFalse(result);
    }

    @Test
    public void testGenererMotDePasseAleatoire() {
        // Act
        String generatedPassword = BcryptHashing.genererMotDePasseAleatoire();

        // Assert
        assertNotNull(generatedPassword);
        assertTrue(generatedPassword.length() > 0);
    }
}
