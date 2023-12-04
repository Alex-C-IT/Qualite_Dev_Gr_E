package com.iut.banque.test.modele;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import com.iut.banque.exceptions.IllegalFormatException;
import com.iut.banque.modele.Gestionnaire;
import org.junit.Test;

import com.iut.banque.modele.Client;
import com.iut.banque.modele.CompteAvecDecouvert;
import com.iut.banque.modele.CompteSansDecouvert;

import java.util.HashMap;
import java.util.Map;

public class TestsClient {

	/**
	 * Tests successifs de la méthode de vérification du format de numéro de
	 * client
	 */
	@Test
	public void testMethodeCheckFormatUserIdClientCorrect() {
		String strClient = "a.utilisateur928";
		if (!Client.checkFormatUserIdClient(strClient)) {
			fail("String " + strClient + " refusé dans le test");
		}
	}

	@Test
	public void testMethodeCheckFormatUserIdClientCommencantParUnChiffre() {
		String strClient = "32a.abc1";
		if (Client.checkFormatUserIdClient(strClient)) {
			fail("String " + strClient + " validé dans le test");
		}
	}

	@Test
	public void testMethodeCheckFormatUserIdClientCommencantParPlusieursLettres() {
		String strClient = "aaa.abc1";
		if (Client.checkFormatUserIdClient(strClient)) {
			fail("String " + strClient + " validé dans le test");
		}
	}

	@Test
	public void testMethodeCheckFormatUserIdClientSansPointSeparateur() {
		String strClient = "abc1";
		if (Client.checkFormatUserIdClient(strClient)) {
			fail("String " + strClient + " validé dans le test");
		}
	}

	@Test
	public void testMethodeCheckFormatUserIdClientChaineVide() {
		String strClient = "";
		if (Client.checkFormatUserIdClient(strClient)) {
			fail("String " + strClient + " validé dans le test");
		}
	}

	@Test
	public void testMethodeCheckFormatUserIdClientSansLettresApresLePointSeparateur() {
		String strClient = "a.138";
		if (Client.checkFormatUserIdClient(strClient)) {
			fail("String " + strClient + " validé dans le test");
		}
	}

	@Test
	public void testMethodeCheckFormatUserIdClientAvecUneSeuleLettreApresLePointSeparateur() {
		String strClient = "a.a1";
		if (!Client.checkFormatUserIdClient(strClient)) {
			fail("String " + strClient + " refusé dans le test");
		}
	}

	@Test
	public void testMethodeCheckFormatUserIdClientAvecCaractereSpecial() {
		String strClient = "a.bcdé1";
		if (Client.checkFormatUserIdClient(strClient)) {
			fail("String " + strClient + " validé dans le test");
		}
	}

	@Test
	public void testMethodeCheckFormatUserIdClientAvecTrailingZeros() {
		String strClient = "a.abc01";
		if (Client.checkFormatUserIdClient(strClient)) {
			fail("String " + strClient + " validé dans le test");
		}
	}

	@Test
	public void testMethodeCheckFormatUserIdClientAvecPlusieursPointsSeparateurs() {
		String strClient = "a.ab.c1";
		if (Client.checkFormatUserIdClient(strClient)) {
			fail("String " + strClient + " validé dans le test");
		}
	}

	/**
	 * Tests successifs de la méthode de vérification du format du numéro de
	 * client
	 */
	@Test
	public void testMethodeCheckFormatNumeroClientCorrect() {
		String strClient = "1234567890";
		if (!Client.checkFormatNumeroClient(strClient)) {
			fail("String " + strClient + " refusé dans le test");
		}
	}

	@Test
	public void testMethodeCheckFormatNumeroClientAvecLettre() {
		String strClient = "12a456789";
		if (Client.checkFormatNumeroClient(strClient)) {
			fail("String " + strClient + " validé dans le test");
		}
	}

	@Test
	public void testMethodeCheckFormatNumeroClientAvecCaractereSpecial() {
		String strClient = "12#456789";
		if (Client.checkFormatNumeroClient(strClient)) {
			fail("String " + strClient + " validé dans le test");
		}
	}

	@Test
	public void testMethodeCheckFormatNumeroClientAvecMoinsDeNeufChiffres() {
		String strClient = "12345678";
		if (Client.checkFormatNumeroClient(strClient)) {
			fail("String " + strClient + " validé dans le test");
		}
	}

	@Test
	public void testMethodeCheckFormatNumeroClientAvecPlusDeDixChiffres() {
		String strClient = "12345678901";
		if (Client.checkFormatNumeroClient(strClient)) {
			fail("String " + strClient + " validé dans le test");
		}
	}

	/**
	 * Tests de la méthode possedeComptesADecouvert
	 */
	@Test
	public void testMethodePossedeComptesADecouvertPourClientAvecQueDesComptesSansDecouvert() {
		try {
			Client c = new Client("John", "Doe", "20 rue Bouvier", true, "mail@mail.com", "j.doe1", "password", "1234567890");
			c.addAccount(new CompteSansDecouvert("FR1234567890", 42, c));
			c.addAccount(new CompteSansDecouvert("FR1234567891", 0, c));
			if (c.possedeComptesADecouvert()) {
				fail("La méthode aurait du renvoyer faux");
			}
		} catch (Exception e) {
			fail("Exception récupérée -> " + e.getStackTrace().toString());
		}
	}

	@Test
	public void testMethodePossedeComptesADecouvertPourClientSansComptes() {
		try {
			Client c = new Client("John", "Doe", "20 rue Bouvier", true, "mail@mail.com","j.doe1", "password", "1234567890");
			if (c.possedeComptesADecouvert()) {
				fail("La méthode aurait du renvoyer faux");
			}
		} catch (Exception e) {
			fail("Exception récupérée -> " + e.getStackTrace().toString());
		}
	}

	@Test
	public void testMethodePossedeComptesADecouvertPourClientAvecUnCompteADecouvertParmisPlusieursTypesDeComptes() {
		try {
			Client c = new Client("John", "Doe", "20 rue Bouvier", true, "mail@mail.com","j.doe1", "password", "1234567890");
			c.addAccount(new CompteSansDecouvert("FR1234567890", 42, c));
			c.addAccount(new CompteSansDecouvert("FR1234567891", 0, c));
			c.addAccount(new CompteAvecDecouvert("FR1234567892", -42, 100, c));
			c.addAccount(new CompteAvecDecouvert("FR1234567893", 1000, 100, c));
			if (!c.possedeComptesADecouvert()) {
				fail("La méthode aurait du renvoyer vrai");
			}
		} catch (Exception e) {
			fail("Exception récupérée -> " + e.getStackTrace().toString());
		}
	}

	@Test
	public void testMethodePossedeComptesADecouvertPourClientAvecPlusieursComptesADecouvertParmisPlusieursTypesDeComptes() {
		try {
			Client c = new Client("John", "Doe", "20 rue Bouvier", true, "mail@mail.com", "j.doe1", "password", "1234567890");
			c.addAccount(new CompteSansDecouvert("FR1234567890", 42, c));
			c.addAccount(new CompteSansDecouvert("FR1234567891", 0, c));
			c.addAccount(new CompteAvecDecouvert("FR1234567892", -42, 100, c));
			c.addAccount(new CompteAvecDecouvert("FR1234567893", 1000, 100, c));
			c.addAccount(new CompteAvecDecouvert("FR1234567893", -4242, 5000, c));
			c.addAccount(new CompteSansDecouvert("FR1234567891", 1000.01, c));
			if (!c.possedeComptesADecouvert()) {
				fail("La méthode aurait du renvoyer vrai");
			}
		} catch (Exception e) {
			fail("Exception récupérée -> " + e.getStackTrace().toString());
		}
	}

	@Test
	public void testMethodePossedeComptesADecouvertPourClientAvecUnUniqueCompteADecouvert() {
		try {
			Client c = new Client("John", "Doe", "20 rue Bouvier", true, "mail@mail.com", "j.doe1", "password", "1234567890");
			c.addAccount(new CompteAvecDecouvert("FR1234567892", -42, 100, c));
			if (!c.possedeComptesADecouvert()) {
				fail("La méthode aurait du renvoyer vrai");
			}
		} catch (Exception e) {
			e.printStackTrace();
			fail("Exception récupérée -> " + e.getStackTrace().toString());
		}
	}
	
	//Tests pour la méthode getCompteAvecSoldeNonNul()

	@Test
	public void testMethodeGetCompteAvecSoldeNonNulAvecDeuxComptesAvecSoldeNul(){
		try {
			Client c = new Client("John", "Doe", "20 rue Bouvier", true, "mail@mail.com", "j.doe1", "password", "1234567890");
			c.addAccount(new CompteAvecDecouvert("FR1234567890",0,42,c));
			c.addAccount(new CompteSansDecouvert("FR1234567891", 0, c));
			if (c.getComptesAvecSoldeNonNul().size()!=0){
				fail("La méthode a renvoyé un ou plusieurs comptes aveec un solde nul");
			}
		} catch (Exception e) {
			fail("Exception récupérée -> " + e.getStackTrace().toString());
		}
	}
	@Test
	public void testMethodeGetCompteAvecSoldeNonNulAvecUnCompteSansDecouvertAvecSoldeNonNul(){
		try {
			Client c = new Client("John", "Doe", "20 rue Bouvier", true, "mail@mail.com", "j.doe1", "password", "1234567890");
			c.addAccount(new CompteAvecDecouvert("FR1234567890",0,42,c));
			c.addAccount(new CompteSansDecouvert("FR1234567891", 1, c));
			if (c.getComptesAvecSoldeNonNul().get("FR1234567891")==null){
				fail("La méthode n'a pas renvoyé dans le map le compte avec solde non nul");
			}
		} catch (Exception e) {
			fail("Exception récupérée -> " + e.getStackTrace().toString());
		}
	}
	@Test
	public void testMethodeGetCompteAvecSoldeNonNulAvecUnCompteAvecDecouvertAvecSoldeNonNul(){
		try {
			Client c = new Client("John", "Doe", "20 rue Bouvier", true, "mail@mail.com", "j.doe1", "password", "1234567890");
			c.addAccount(new CompteAvecDecouvert("FR1234567890",1,42,c));
			c.addAccount(new CompteSansDecouvert("FR1234567891", 0, c));
			if (c.getComptesAvecSoldeNonNul().get("FR1234567890")==null){
				fail("La méthode n'a pas renvoyé dans le map le compte avec solde non nul");
			}
		} catch (Exception e) {
			fail("Exception récupérée -> " + e.getStackTrace().toString());
		}
	}

	//test pour la méthode ToString()
	@Test
	public void testToString() throws IllegalFormatException {
		// Création d'un client pour le test
		Client c = new Client("John", "Doe", "20 rue Bouvier", true, "mail@mail.com", "j.doe1", "password", "1234567890");

		// Définition de la chaîne attendue
		String expectedToString = "Client [userId=j.doe1, nom=John, prenom=Doe, adresse=20 rue Bouvier, male=true, email=mail@mail.com, userPwd=password, numeroClient=1234567890, accounts=0]";

		// Appel de la méthode toString() et vérification
		assertEquals(expectedToString, c.toString());
	}

	@Test(expected = IllegalFormatException.class)
	public void testSetUserIdIllegalFormat() throws IllegalFormatException {
		// Création d'un client
		Client client = new Client();

		// Appel de la méthode setUserId avec un identifiant non conforme au format
		client.setUserId("invalidUserId");
	}

	@Test(expected = IllegalFormatException.class)
	public void testSetUserIdValidFormat() throws IllegalFormatException {
		// Création d'un client
		Client client = new Client();

		// Appel de la méthode setUserId avec un identifiant conforme au format
		client.setUserId("validUserId");

		// Aucune exception ne devrait être levée si l'identifiant est conforme
	}

	@Test
	public void testGetIdentity() throws IllegalFormatException {
		// Création d'un client
		Client client = new Client();
		client.setPrenom("John");
		client.setNom("Doe");
		client.setNumeroClient("1234567890");

		// Appel de la méthode getIdentity
		String identity = client.getIdentity();

		// Vérification que la chaîne retournée est conforme aux attentes
		assertEquals("John Doe (1234567890)", identity);
	}

	@Test
	public void testSetNumeroClient() throws IllegalFormatException
	{
		// Création d'un client
		Client client = new Client();
		// Numéro de client valide
		String validNumeroClient = "1234567890";
		// Numéro de client vide
		String emptyNumeroClient = "";
		// Numéro de client non conforme au format attendu
		String invalidFormatNumeroClient = "invalid";

		try {
			// Tentative d'appel de la méthode avec un numéro de client vide
			client.setNumeroClient(emptyNumeroClient);

			// Si aucune exception n'est lancée pour le cas invalide, le test échoue
			fail("IllegalFormatException non détectée pour un numéro de client vide.");

		} catch (IllegalFormatException e) {
			// Succès pour le cas où une exception IllegalFormatException est lancée pour un numéro de client vide
		}

		try {
			// Tentative d'appel de la méthode avec un numéro de client non conforme au format attendu
			client.setNumeroClient(invalidFormatNumeroClient);

			// Si aucune exception n'est lancée pour le cas invalide, le test échoue
			fail("IllegalFormatException non détectée pour un numéro de client non conforme au format attendu.");

		} catch (IllegalFormatException e) {
			// Succès pour le cas où une exception IllegalFormatException est lancée pour un numéro de client non conforme
		}

		// Appel de la méthode avec un numéro de client valide
		client.setNumeroClient(validNumeroClient);

		// Vérification que la propriété numeroClient a été correctement assignée
		assertEquals(validNumeroClient, client.getNumeroClient());
	}
}
