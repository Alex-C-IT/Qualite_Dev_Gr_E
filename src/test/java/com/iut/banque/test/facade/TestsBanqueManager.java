package com.iut.banque.test.facade;

import static org.junit.Assert.fail;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.iut.banque.exceptions.IllegalOperationException;
import com.iut.banque.facade.BanqueManager;

//@RunWith indique à JUnit de prendre le class runner de Spirng
@RunWith(SpringJUnit4ClassRunner.class)
// @ContextConfiguration permet de charger le context utilisé pendant les tests.
// Par défault (si aucun argument n'est précisé), cherche le fichier
/// src/com/iut/banque/test/TestsDaoHibernate-context.xml
@ContextConfiguration("/test/resources/TestsBanqueManager-context.xml")
@Transactional("transactionManager")
public class TestsBanqueManager {

	@Autowired
	private BanqueManager bm;

	// Tests de par rapport à l'ajout d'un client
	@Test
	public void TestCreationDunClient() {
		try {
			bm.loadAllClients();
			bm.createClient("t.test1", "password", "test1nom", "test1prenom", "test town", true, "4242424242");
		} catch (IllegalOperationException e) {
			e.printStackTrace();
			fail("IllegalOperationException récupérée : " + e.getStackTrace());
		} catch (Exception te) {
			te.printStackTrace();
			fail("Une Exception " + te.getClass().getSimpleName() + " a été récupérée");
		}
	}

	@Test
	public void TestCreationDunClientAvecDeuxNumerosDeCompteIdentiques() {
		try {
			bm.loadAllClients();
			bm.createClient("t.test1", "password", "test1nom", "test1prenom", "test town", true, "0101010101");
			fail();
		} catch (IllegalOperationException e) {
		} catch (Exception te) {
			te.printStackTrace();
			fail("Une Exception " + te.getClass().getSimpleName() + " a été récupérée");
		}
	}

	// Tests par rapport à la suppression de comptes
	@Test
	public void TestSuppressionDunCompteAvecDecouvertAvecSoldeZero() {
		try {

			bm.deleteAccount(bm.getAccountById("CADV000000"));
		} catch (IllegalOperationException e) {
			e.printStackTrace();
			fail("IllegalOperationException récupérée : " + e.getStackTrace());
		} catch (Exception te) {
			fail("Une Exception " + te.getClass().getSimpleName() + " a été récupérée");
		}
	}

	@Test
	public void TestSuppressionDunCompteAvecDecouvertAvecSoldeDifferentDeZero() {
		try {
			bm.deleteAccount(bm.getAccountById("CADNV00000"));
			fail("Une IllegalOperationException aurait dû être récupérée");
		} catch (IllegalOperationException e) {
		} catch (Exception te) {
			fail("Une Exception " + te.getClass().getSimpleName() + " a été récupérée");
		}
	}

	@Test
	public void TestSuppressionDunCompteSansDecouvertAvecSoldeZero() {
		try {
			bm.deleteAccount(bm.getAccountById("CSDV000000"));
		} catch (IllegalOperationException e) {
			e.printStackTrace();
			fail("IllegalOperationException récupérée : " + e.getStackTrace());
		} catch (Exception te) {
			fail("Une Exception " + te.getClass().getSimpleName() + " a été récupérée");
		}
	}

	@Test
	public void TestSuppressionDunCompteSansDecouvertAvecSoldeDifferentDeZero() {
		try {
			bm.deleteAccount(bm.getAccountById("CSDNV00000"));
			fail("Une IllegalOperationException aurait dû être récupérée");
		} catch (IllegalOperationException e) {
		} catch (Exception te) {
			fail("Une Exception " + te.getClass().getSimpleName() + " a été récupérée");
		}
	}

	// Tests en rapport avec la suppression d'utilisateurs
	@Test
	public void TestSuppressionDunUtilisateurSansCompte() {
		try {
			bm.loadAllClients();
			bm.deleteUser(bm.getUserById("g.pasdecompte"));
		} catch (IllegalOperationException e) {
			e.printStackTrace();
			fail("IllegalOperationException récupérée : " + e.getStackTrace());
		} catch (Exception te) {
			te.printStackTrace();
			fail("Une Exception " + te.getClass().getSimpleName() + " a été récupérée");
		}
	}

	@Test
	public void TestSuppressionDuDernierManagerDeLaBaseDeDonnees() {
		bm.loadAllGestionnaires();
		try {
			bm.deleteUser(bm.getUserById("admin"));
			fail("Une IllegalOperationException aurait dû être récupérée");
		} catch (IllegalOperationException e) {
		} catch (Exception te) {
			te.printStackTrace();
			fail("Une Exception " + te.getClass().getSimpleName() + " a été récupérée");
		}
	}

	@Test
	public void TestSuppressionDunClientAvecComptesDeSoldeZero() {
		try {
			bm.loadAllClients();
			bm.deleteUser(bm.getUserById("g.descomptesvides"));
			if (bm.getAccountById("KL4589219196") != null || bm.getAccountById("KO7845154956") != null) {
				fail("Les comptes de l'utilisateur sont encore présents dans la base de données");
			}
		} catch (IllegalOperationException e) {
			e.printStackTrace();
			fail("IllegalOperationException récupérée : " + e.getStackTrace());
		} catch (Exception te) {
			te.printStackTrace();
			fail("Une Exception " + te.getClass().getSimpleName() + " a été récupérée");
		}
	}

	@Test
	public void TestSuppressionDunClientAvecUnCompteDeSoldePositif() {
		try {
			bm.deleteUser(bm.getUserById("j.doe1"));
			fail("Une IllegalOperationException aurait dû être récupérée");
		} catch (IllegalOperationException e) {
		} catch (Exception te) {
			fail("Une Exception " + te.getClass().getSimpleName() + " a été récupérée");
		}
	}

	@Test
	public void TestSuppressionDunClientAvecUnCompteAvecDecouvertDeSoldeNegatif() {
		try {
			bm.deleteUser(bm.getUserById("j.doe1"));
			fail("Une IllegalOperationException aurait dû être récupérée");
		} catch (IllegalOperationException e) {
		} catch (Exception te) {
			fail("Une Exception " + te.getClass().getSimpleName() + " a été récupérée");
		}
	}

}
