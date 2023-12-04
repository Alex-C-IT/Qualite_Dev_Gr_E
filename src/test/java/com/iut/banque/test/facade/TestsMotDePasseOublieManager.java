package com.iut.banque.test.facade;

import com.iut.banque.facade.MotDePasseOublieManager;
import com.iut.banque.interfaces.IDao;
import com.iut.banque.modele.Utilisateur;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.*;

public class TestsMotDePasseOublieManager {

    private MotDePasseOublieManager motDePasseOublieManager;
    private IDao dao;
    private Utilisateur utilisateur;

    @Before
    public void setUp() {
        dao = mock(IDao.class);
        utilisateur = mock(Utilisateur.class);
        motDePasseOublieManager = new MotDePasseOublieManager();
        motDePasseOublieManager.setDao(dao);
    }

    @Test
    public void testMotDePasseOublieUserNotFound() {
        // Arrange
        String userCode = "user123";
        String userEmail = "user@example.com";

        when(dao.getUserById(userCode)).thenReturn(null);

        // Act
        boolean result = motDePasseOublieManager.motDePasseOublie(userCode, userEmail);

        // Assert
        assertFalse(result);
        verify(dao, times(1)).getUserById(userCode);
        verifyNoMoreInteractions(dao, utilisateur);
    }

    @Test
    public void testMotDePasseOublieEmailMismatch() {
        // Arrange
        String userCode = "user123";
        String userEmail = "user@example.com";

        when(dao.getUserById(userCode)).thenReturn(utilisateur);
        when(utilisateur.getMail()).thenReturn("different@example.com");

        // Act
        boolean result = motDePasseOublieManager.motDePasseOublie(userCode, userEmail);

        // Assert
        assertFalse(result);
        verify(dao, times(1)).getUserById(userCode);
        verify(utilisateur, times(1)).getMail();
        verifyNoMoreInteractions(dao, utilisateur);
    }
}
