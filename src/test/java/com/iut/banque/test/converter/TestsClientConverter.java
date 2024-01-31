package com.iut.banque.test.converter;

import com.iut.banque.converter.ClientConverter;
import com.iut.banque.interfaces.IDao;
import com.iut.banque.modele.Client;
import com.opensymphony.xwork2.conversion.TypeConversionException;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class TestsClientConverter {

    private ClientConverter clientConverter;
    private IDao daoMock;

    @Before
    public void setUp() {
        daoMock = mock(IDao.class);
        clientConverter = new ClientConverter(daoMock);
    }

    @Test
    public void testConvertFromString() {
        // Arrange
        String clientId = "123";
        Client expectedClient = new Client();
        expectedClient.setIdentity(clientId);

        when(daoMock.getUserById(clientId)).thenReturn(expectedClient);

        // Act
        Object result = clientConverter.convertFromString(null, new String[]{clientId}, null);

        // Assert
        assertNotNull(result);
        assertTrue(result instanceof Client);
        assertEquals(expectedClient, result);
    }

    @Test(expected = TypeConversionException.class)
    public void testConvertFromStringInvalidClient() {
        // Arrange
        String clientId = "456";

        when(daoMock.getUserById(clientId)).thenReturn(null);

        // Act & Assert
        clientConverter.convertFromString(null, new String[]{clientId}, null);
    }

    @Test
    public void testConvertToStringNullClient() {
        // Act
        String result = clientConverter.convertToString(null, null);

        // Assert
        assertNull(result);
    }
}