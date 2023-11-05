package com.iut.banque.test.modele;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import com.iut.banque.exceptions.IllegalFormatException;
import com.iut.banque.modele.Client;
import com.iut.banque.modele.Compte;
import com.iut.banque.modele.CompteSansDecouvert;

/**
 * Tests en rapport avec la méthode "créditer" de la classe Banque
 */
public class TestsCompte {

	private Compte compte;

	@Before
	public void setUp() throws IllegalFormatException {
		compte = new CompteSansDecouvert("WU1234567890", 0, new Client());
	}

	/**
	 * Test de la métode crediter
	 * 
	 * @throws IllegalFormatException
	 */
	@Test
	public void testCrediterCompte() throws IllegalFormatException {
		/*
		 * Méthode qui va tester la méthode crediter pour un compte quelconque
		 * (pas important si avec ou sans découvert vu que les deux vont hériter
		 * de la même méthode).
		 */
		compte.crediter(100);
		assertEquals(100.0, compte.getSolde(), 0.001);
	}

	/**
	 * Test de la métode crediter avec un montant négatif
	 */
	@Test
	public void testCrediterCompteMontantNegatif() {
		/*
		 * Méthode qui va tester la méthode crediter pour un compte quelconque
		 * (pas important si avec ou sans découvert vu que les deux vont hériter
		 * de la même méthode) avec un montant négatif, auquel cas il devrait
		 * attraper un IllegalFormatExcepion
		 */
		try {
			compte.crediter(-100);
			fail("La méthode n'a pas renvoyé d'exception!");
		} catch (IllegalFormatException ife) {
		} catch (Exception e) {
			fail("Exception de type " + e.getClass().getSimpleName()
					+ " récupérée alors qu'un IllegalFormatException était attendu");
		}
	}

	/**
	 * Test du constructeur avec un format de compte volontairement faux pour
	 * tester si une exception est renvoyée. Le détail des tests de conformité
	 * du format de compte se font après
	 */
	@Test
	public void testConstruireCompteAvecFormatNumeroCompteIncorrect() {
		try {
			compte = new CompteSansDecouvert("&éþ_ëüú¤", 0, new Client());
			fail("Exception non renvoyée par le constructeur avec un format de numéro de compte incorrect");
		} catch (IllegalFormatException ife) {
		} catch (Exception e) {
			fail("Exception de type " + e.getClass().getSimpleName()
					+ " récupérée à la place d'une de type IllegalFormatException");
		}
	}

	@Test
	public void testMethodeCheckFormatNumeroCompteCorrect() {
		String strNumCompte = "FR0123456789";
		if (!Compte.checkFormatNumeroCompte(strNumCompte)) {
			fail("String " + strNumCompte + " refusée dans le test");
		}
	}

	@Test
	public void testMethodeCheckFormatNumeroCompteAvecUneSeuleLettreAuDebut() {
		String strNumCompte = "F0123456789";
		if (Compte.checkFormatNumeroCompte(strNumCompte)) {
			fail("String " + strNumCompte + " validée dans le test");
		}
	}

	@Test
	public void testMethodeCheckFormatNumeroCompteAucuneLettreAuDebut() {
		String strNumCompte = "0123456789";
		if (Compte.checkFormatNumeroCompte(strNumCompte)) {
			fail("String " + strNumCompte + " validée dans le test");
		}
	}

	@Test
	public void testMethodeCheckFormatNumeroCompteAvecTroisLettresAuDebut() {
		String strNumCompte = "FRA0123456789";
		if (Compte.checkFormatNumeroCompte(strNumCompte)) {
			fail("String " + strNumCompte + " validée dans le test");
		}
	}

	@Test
	public void testMethodeCheckFormatNumeroCompteAvecLettresAuMillieu() {
		String strNumCompte = "FR0123A456789";
		if (Compte.checkFormatNumeroCompte(strNumCompte)) {
			fail("String " + strNumCompte + " validée dans le test");
		}
	}

	@Test
	public void testMethodeCheckFormatNumeroCompteAvecPlusDeChiffresQueAttendu() {
		String strNumCompte = "FR00123456789";
		if (Compte.checkFormatNumeroCompte(strNumCompte)) {
			fail("String " + strNumCompte + " validée dans le test");
		}
	}

	@Test
	public void testMethodeCheckFormatNumeroCompteAvecMoinsDeChiffresQueAttendu() {
		String strNumCompte = "FR123456789";
		if (Compte.checkFormatNumeroCompte(strNumCompte)) {
			fail("String " + strNumCompte + " validée dans le test");
		}
	}

	@Test
	public void testMethodeCheckFormatNumeroCompteAvecLettresALaFin() {
		String strNumCompte = "FR0123456789A";
		if (Compte.checkFormatNumeroCompte(strNumCompte)) {
			fail("String " + strNumCompte + " validée dans le test");
		}
	}
}
