package com.iut.banque.test.modele;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import com.iut.banque.exceptions.IllegalFormatException;
import com.iut.banque.exceptions.InsufficientFundsException;
import com.iut.banque.modele.Client;
import com.iut.banque.modele.CompteSansDecouvert;

public class TestsCompteSansDecouvert {

	private CompteSansDecouvert compte;

	@Before
	public void setUp() throws IllegalFormatException {
		compte = new CompteSansDecouvert("FR0123456789", 100, new Client());
	}

	/**
	 * Test de la classe getClassName() pour les CompteSansDecouvert
	 */
	@Test
	public void testGetClassNameSansDecouvert() {
		assertEquals("CompteSansDecouvert", compte.getClassName());
	}

	/**
	 * Test de la métode debiter avec un montant négatif
	 */
	@Test
	public void testCrediterCompteMontantNegatif() {
		/*
		 * Méthode qui va tester la méthode debiter avec un montant négatif,
		 * auquel cas il devrait attraper un IllegalFormatExcepion
		 */
		try {
			compte.debiter(-100);
			fail("La méthode n'a pas renvoyé d'exception!");
		} catch (IllegalFormatException ife) {
		} catch (Exception e) {
			fail("Exception de type " + e.getClass().getSimpleName()
					+ " récupérée alors qu'un IllegalFormatException était attendu");
		}
	}

	/**
	 * Tests en rapport avec la méthode "Debiter" de la classe
	 * CompteAvecDecouvert
	 * 
	 * @throws IllegalFormatException
	 */
	@Test
	public void testDebiterCompteAvecDecouvertValeurPossible() throws IllegalFormatException {
		/*
		 * Méthode qui va tester la méthode debiter pour un compte sans
		 * découvert avec un montant réalisable (en fonction du montant retiré
		 * et du seuil maximal du compte avec découvert)
		 */
		try {
			compte.debiter(50);
			assertEquals(50.0, compte.getSolde(), 0.001);
		} catch (InsufficientFundsException e) {
			fail("Il ne devrait pas avoir d'exception ici.");
		}
	}

	@Test
	public void testDebiterCompteAvecDecouvertValeurImpossible() throws IllegalFormatException {
		/*
		 * Méthode qui va tester la méthode retrait pour un compte sans
		 * découvert avec un montant irréalisable (un retrait qui irait au delà
		 * du seuil pour le compte avec découvert). La fonction devrait renvoyer
		 * une exception en cas de problême
		 */
		try {
			compte.debiter(200);
			fail("Il devrait avoir une InsufficientFundsException ici.");
		} catch (InsufficientFundsException e) {
		}
	}

}
