package com.iut.banque.test.converter;

import com.iut.banque.converter.AccountConverter;
import com.iut.banque.interfaces.IDao;
import com.iut.banque.modele.Compte;
import com.opensymphony.xwork2.conversion.TypeConversionException;
import org.junit.Before;
import org.junit.Test;
import java.util.HashMap;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class TestAccountConverter {

    private AccountConverter accountConverter;
    private IDao daoMock;

    @Before
    public void setUp() {
        daoMock = mock(IDao.class);
        accountConverter = new AccountConverter(daoMock);
    }

    @Test
    public void testConvertFromString() {
        // Arrange
        String[] values = {"123456"};
        Compte compteMock = mock(Compte.class);
        when(daoMock.getAccountById("123456")).thenReturn(compteMock);

        // Act
        Object result = accountConverter.convertFromString(new HashMap<>(), values, null);

        // Assert
        assertNotNull(result);
        assertTrue(result instanceof Compte);
        assertEquals(compteMock, result);

        // Verify
        verify(daoMock, times(1)).getAccountById("123456");
    }

    @Test(expected = TypeConversionException.class)
    public void testConvertFromStringInvalid() {
        // Arrange
        String[] values = {"invalidId"};
        when(daoMock.getAccountById("invalidId")).thenReturn(null);

        // Act & Assert
        accountConverter.convertFromString(new HashMap<>(), values, null);

        // Verify (exception should be thrown)
    }

    @Test
    public void testConvertToString() {
        // Arrange
        Compte compteMock = mock(Compte.class);
        when(compteMock.getNumeroCompte()).thenReturn("987654");

        // Act
        String result = accountConverter.convertToString(new HashMap<>(), compteMock);

        // Assert
        assertEquals("987654", result);
    }

    @Test
    public void testConvertToStringNull() {
        // Act
        String result = accountConverter.convertToString(new HashMap<>(), null);

        // Assert
        assertNull(result);
    }
}