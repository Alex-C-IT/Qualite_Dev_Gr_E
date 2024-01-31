package com.iut.banque.test.facade;

import com.iut.banque.constants.LoginConstants;
import com.iut.banque.exceptions.IllegalFormatException;
import com.iut.banque.exceptions.InsufficientFundsException;
import com.iut.banque.facade.BanqueFacade;
import com.iut.banque.facade.BanqueManager;
import com.iut.banque.facade.LoginManager;
import com.iut.banque.modele.Client;
import com.iut.banque.modele.Compte;
import com.iut.banque.modele.Gestionnaire;
import com.iut.banque.modele.Utilisateur;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class TestsBanqueFacade {

    private BanqueFacade banqueFacade;
    private BanqueManager banqueManagerMock;
    private LoginManager loginManagerMock;

    @Before
    public void setUp() {
        banqueManagerMock = mock(BanqueManager.class);
        loginManagerMock = mock(LoginManager.class);
        banqueFacade = new BanqueFacade(loginManagerMock, banqueManagerMock);
    }

    @Test
    public void testGetConnectedUser() {
        // Arrange
        when(loginManagerMock.getConnectedUser()).thenReturn(new Gestionnaire());

        // Act
        Utilisateur connectedUser = banqueFacade.getConnectedUser();

        // Assert
        assertNotNull(connectedUser);
        assertTrue(connectedUser instanceof Gestionnaire);
    }

    @Test
    public void testTryLogin() {
        // Arrange
        when(loginManagerMock.tryLogin("user", "password")).thenReturn(LoginConstants.USER_IS_CONNECTED);

        // Act
        int result = banqueFacade.tryLogin("user", "password");

        // Assert
        assertEquals(LoginConstants.USER_IS_CONNECTED, result);
        verify(banqueManagerMock, never()).loadAllClients();
    }

    @Test
    public void testTryLoginManagerConnected() {
        // Arrange
        when(loginManagerMock.tryLogin("manager", "password")).thenReturn(LoginConstants.MANAGER_IS_CONNECTED);

        // Act
        int result = banqueFacade.tryLogin("manager", "password");

        // Assert
        assertEquals(LoginConstants.MANAGER_IS_CONNECTED, result);
        verify(banqueManagerMock, times(1)).loadAllClients();
    }

    @Test
    public void testCrediter() throws IllegalFormatException {
        // Arrange
        Compte compteMock = mock(Compte.class);

        // Act
        banqueFacade.crediter(compteMock, 100.0);

        // Assert
        verify(banqueManagerMock, times(1)).crediter(compteMock, 100.0);
    }

    @Test(expected = InsufficientFundsException.class)
    public void testDebiterInsufficientFunds() throws InsufficientFundsException, IllegalFormatException {
        // Arrange
        Compte compteMock = mock(Compte.class);
        doThrow(new InsufficientFundsException()).when(banqueManagerMock).debiter(compteMock, 100.0);

        // Act & Assert
        banqueFacade.debiter(compteMock, 100.0);

        // Verify (exception should be thrown)
    }
    @Test
    public void testDebiterSufficientFunds() throws InsufficientFundsException, IllegalFormatException {
        // Arrange
        Compte compteMock = mock(Compte.class);

        // Act
        banqueFacade.debiter(compteMock, 50.0);

        // Verify
        verify(banqueManagerMock, times(1)).debiter(compteMock, 50.0);
    }

    @Test
    public void testGetAllClients() {
        // Arrange
        Map<String, Client> clientsMap = new HashMap<>();
        when(banqueManagerMock.getAllClients()).thenReturn(clientsMap);

        // Act
        Map<String, Client> result = banqueFacade.getAllClients();

        // Assert
        assertEquals(clientsMap, result);
    }
}