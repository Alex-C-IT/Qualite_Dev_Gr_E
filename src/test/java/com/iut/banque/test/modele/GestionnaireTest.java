package com.iut.banque.test.modele;

import org.junit.Test;
import static org.junit.Assert.*;

import com.iut.banque.exceptions.IllegalFormatException;
import com.iut.banque.modele.Gestionnaire;

public class GestionnaireTest {

    @Test
    public void testConstructeurAvecParametres() throws IllegalFormatException {
        // Initialisation
        Gestionnaire gestionnaire = new Gestionnaire("Nom", "Prenom", "Adresse", true, "email@example.com", "userId", "userPwd");

        // Vérification des attributs
        assertEquals("Nom", gestionnaire.getNom());
        assertEquals("Prenom", gestionnaire.getPrenom());
        assertEquals("Adresse", gestionnaire.getAdresse());
        assertTrue(gestionnaire.isMale());
        assertEquals("email@example.com", gestionnaire.getMail());
        assertEquals("userId", gestionnaire.getUserId());
        assertEquals("userPwd", gestionnaire.getUserPwd());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructeurAvecParametresIdentifiantVide() throws IllegalFormatException {
        // Tentative de création avec identifiant vide, doit lever une exception IllegalArgumentException
        new Gestionnaire("Nom", "Prenom", "Adresse", true, "email@example.com", "", "userPwd");
    }

    @Test
    public void testConstructeurSansParametre() {
        // Initialisation
        Gestionnaire gestionnaire = new Gestionnaire();

        // Vérification des attributs (peut dépendre de votre implémentation par défaut)
        assertNull(gestionnaire.getNom());
        assertNull(gestionnaire.getPrenom());
        assertNull(gestionnaire.getAdresse());
        assertFalse(gestionnaire.isMale());
        assertNull(gestionnaire.getMail());
        assertNull(gestionnaire.getUserId());
        assertNull(gestionnaire.getUserPwd());
    }

    @Test
    public void testToString() throws IllegalFormatException {
        // Initialisation
        Gestionnaire gestionnaire = new Gestionnaire("Nom", "Prenom", "Adresse", true, "email@example.com", "userId", "userPwd");

        // Vérification du format de la chaîne générée
        assertEquals("Gestionnaire [nom=Nom, prenom=Prenom, adresse=Adresse, male=true, email = email@example.com, userId=userId, userPwd=userPwd]", gestionnaire.toString());
    }
}
