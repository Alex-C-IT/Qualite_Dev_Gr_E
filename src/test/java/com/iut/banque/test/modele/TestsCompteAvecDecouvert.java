package com.iut.banque.test.modele;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import com.iut.banque.exceptions.IllegalFormatException;
import com.iut.banque.exceptions.IllegalOperationException;
import com.iut.banque.exceptions.InsufficientFundsException;
import com.iut.banque.modele.Client;
import com.iut.banque.modele.CompteAvecDecouvert;

public class TestsCompteAvecDecouvert {

	private CompteAvecDecouvert compte;

	@Before
	public void setUp() throws IllegalFormatException, IllegalOperationException {
		compte = new CompteAvecDecouvert("FR0123456789", 100, 100, new Client());
	}

	/**
	 * Test de la classe getClassName() pour les CompteAvecDecouvert
	 */
	@Test
	public void testGetClassNameAvecDecouvert() {
		assertEquals("CompteAvecDecouvert", compte.getClass().getSimpleName());
	}

	/**
	 * Tests en rapport avec la méthode "Debiter" de la classe
	 * CompteAvecDecouvert
	 * 
	 * @throws IllegalFormatException
	 */

	/**
	 * Test de la métode debiter avec un montant négatif
	 */
	@Test
	public void testCrediterCompteMontantNegatif() {
		/*
		 * Méthode qui va tester la méthode debiteravec un montant négatif,
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

	@Test
	public void testDebiterCompteAvecDecouvertValeurPossible() throws IllegalFormatException {
		/*
		 * Méthode qui va tester la méthode debiter pour un compte avec
		 * découvert avec un montant réalisable (en fonction du montant retiré
		 * et du seuil maximal du compte avec découvert)
		 */
		try {
			compte.debiter(150);
			assertEquals(-50.0, compte.getSolde(), 0.0001);
		} catch (InsufficientFundsException e) {
			fail("Il ne devrait pas avoir d'exception ici.");
		}
	}

	@Test
	public void testDebiterCompteAvecDecouvertValeurImpossible() throws IllegalFormatException {
		/*
		 * Méthode qui va tester la méthode retrait pour un compte avec
		 * découvert avec un montant irréalisable (un retrait qui irait au delà
		 * du seuil pour le compte avec découvert). La fonction devrait renvoyer
		 * une exception en cas de problème
		 */
		try {
			compte.debiter(250);
			fail("Il devrait avoir une InsufficientFundsException ici.");
		} catch (InsufficientFundsException e) {
		}
	}

}
