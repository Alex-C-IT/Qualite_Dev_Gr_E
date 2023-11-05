package com.iut.banque.facade;

import java.util.Map;

import com.iut.banque.constants.LoginConstants;
import com.iut.banque.exceptions.IllegalFormatException;
import com.iut.banque.exceptions.IllegalOperationException;
import com.iut.banque.exceptions.InsufficientFundsException;
import com.iut.banque.exceptions.TechnicalException;
import com.iut.banque.modele.Client;
import com.iut.banque.modele.Compte;
import com.iut.banque.modele.CompteAvecDecouvert;
import com.iut.banque.modele.Gestionnaire;
import com.iut.banque.modele.Utilisateur;

public class BanqueFacade {

	private BanqueManager banqueManager;
	private LoginManager loginManager;

	/**
	 * Constructeur de la facade sans paramètre
	 * 
	 * @return BanqueFacade : avec un nouveau BanqueManager et un nouveau
	 *         LoginManager
	 */
	public BanqueFacade(LoginManager loginMng, BanqueManager banqueMng) {
		this.banqueManager = banqueMng;
		this.loginManager = loginMng;
	}

	/**
	 * Getter de l'utilisateur actuellement connecté à l'application
	 * 
	 * @return Utilisateur : celui qui est connecté
	 */
	public Utilisateur getConnectedUser() {
		return loginManager.getConnectedUser();
	}

	/**
	 * Tentative de connection.
	 * 
	 * @param userCde
	 *            : le String de l'user qui tente de s'identifier
	 * @param userPwd
	 *            : le String du mot de passe qui doit être vérifié
	 * @return int : correspondant à une constante issue de LoginConstants qui
	 *         prévient de l'état du login
	 */
	public int tryLogin(String userCde, String userPwd) {
		int result = loginManager.tryLogin(userCde, userPwd);
		if (result == LoginConstants.MANAGER_IS_CONNECTED) {
			banqueManager.loadAllClients();
		}
		return result;
	}

	/**
	 * Méthode pour créditer un compte d'un montant donné en paramètre
	 * 
	 * @param compte
	 *            : un objet de type Compte correspondant au compte qu'on veut
	 *            créditer
	 * @param montant
	 *            : un double correspondant au montant qu'on veut créditer
	 * @throws IllegalFormatException
	 *             si le param montant est négatif
	 */
	public void crediter(Compte compte, double montant) throws IllegalFormatException {
		this.banqueManager.crediter(compte, montant);
	}

	/**
	 * Méthode pour débiter un compte d'un montant donné en paramètre
	 * 
	 * @param compte
	 *            : un objet de type Compte correspondant au compte qu'on veut
	 *            débiter
	 * @param montant
	 *            : un double correspondant au montant qu'on veut débiter
	 * @throws InsufficientFundsException
	 *             dans le cas où le retrait n'est pas autorisé (dépassement de
	 *             découvert autorisé par exemple)
	 * @throws IllegalFormatException
	 *             : si le param montant est négatif
	 */
	public void debiter(Compte compte, double montant) throws InsufficientFundsException, IllegalFormatException {
		this.banqueManager.debiter(compte, montant);
	}

	/**
	 * Méthode pour récupérer une HashMap avec tous les clients
	 * 
	 * @return Map<String,Client> : la hashmap correspondant au résultat de la
	 *         demande
	 */
	public Map<String, Client> getAllClients() {
		return banqueManager.getAllClients();
	}

	/**
	 * Méthode pour déconnecter l'utilisateur.
	 */
	public void logout() {
		loginManager.logout();
	}

	/**
	 * Créer un compte sans découvert avec un solde de 0. L'utilisateur connecté
	 * doit être un gestionnaire.
	 * 
	 * @param numeroCompte
	 *            : le numéro du compte à créer
	 * @param client
	 *            : le client à qui appartient le compte
	 * @throws TechnicalException
	 *             : Si l'id fourni en param�tre est déjà assigné à un autre
	 *             compte de la base
	 * @throws IllegalFormatException
	 *             : si le numeroCompte n'est pas du bon format
	 */
	public void createAccount(String numeroCompte, Client client) throws TechnicalException, IllegalFormatException {
		if (loginManager.getConnectedUser() instanceof Gestionnaire) {
			banqueManager.createAccount(numeroCompte, client);
		}
	}

	/**
	 * Créer un compte avec découvert avec un solde de 0. L'utilisateur connecté
	 * doit être un gestionnaire.
	 * 
	 * @param numeroCompte
	 *            : le numéro du compte à créer
	 * @param client
	 *            : le client à qui appartient le compte
	 * @param decouvertAutorise
	 *            : le decouvert autorise sur ce compte
	 * @throws TechnicalException
	 *             : Si l'id fourni en param�tre est déjà assigné à un autre
	 *             compte de la base
	 * @throws IllegalFormatException
	 *             : si le numeroCompte n'est pas du bon format
	 * @throws IllegalOperationException 
	 */
	public void createAccount(String numeroCompte, Client client, double decouvertAutorise)
			throws TechnicalException, IllegalFormatException, IllegalOperationException {
		if (loginManager.getConnectedUser() instanceof Gestionnaire) {
			banqueManager.createAccount(numeroCompte, client, decouvertAutorise);
		}
	}

	/**
	 * Supprime un compte. L'utilisateur connect doit être un gestionnaire.
	 * 
	 * @param compte
	 *            : le compte à supprimer
	 * @throws IllegalOperationException
	 *             : si le compte n'a pas un solde de 0
	 * @throws TechnicalException
	 *             : si le compte est null ou si le compte n'est pas un compte
	 *             persistant.
	 */
	public void deleteAccount(Compte compte) throws IllegalOperationException, TechnicalException {
		if (loginManager.getConnectedUser() instanceof Gestionnaire) {
			banqueManager.deleteAccount(compte);
		}
	}

	/**
	 * Cr�er un manager. L'utilisateur connecté doit être un gestionnaire.
	 * 
	 * @param userId
	 * @param userPwd
	 * @param nom
	 * @param prenom
	 * @param adresse
	 * @param male
	 * @throws TechnicalException
	 *             : Si l'id fourni en param�tre est déjà assigné à un autre
	 *             utilisateur de la base
	 * @throws IllegalFormatException
	 * @throws IllegalArgumentException
	 */
	public void createManager(String userId, String userPwd, String nom, String prenom, String adresse, boolean male)
			throws TechnicalException, IllegalArgumentException, IllegalFormatException {
		if (loginManager.getConnectedUser() instanceof Gestionnaire) {
			banqueManager.createManager(userId, userPwd, nom, prenom, adresse, male);
			;
		}
	}

	/**
	 * 
	 * L'utilisateur connecté doit être un gestionnaire.
	 * 
	 * @param userId
	 * @param userPwd
	 * @param nom
	 * @param prenom
	 * @param adresse
	 * @param male
	 * @param numeroClient
	 * @throws IllegalOperationException
	 *             : si le numeroClient founri en paramètre est déjà assigné à
	 *             un autre utilisateur de la base
	 * @throws TechnicalException
	 *             : Si l'id fourni en paramètre est déjà assigné à un autre
	 *             utilisateur de la base
	 * @throws IllegalFormatException
	 * @throws IllegalArgumentException
	 */
	public void createClient(String userId, String userPwd, String nom, String prenom, String adresse, boolean male,
			String numeroClient)
			throws IllegalOperationException, TechnicalException, IllegalArgumentException, IllegalFormatException {
		if (loginManager.getConnectedUser() instanceof Gestionnaire) {
			banqueManager.createClient(userId, userPwd, nom, prenom, adresse, male, numeroClient);
		}
	}

	/**
	 * 
	 * L'utilisateur connecté doit être un gestionnaire.
	 * 
	 * @param u
	 * @throws IllegalOperationException
	 *             si l'user est null ou si l'utilisateur n'est pas un
	 *             utilisateur persistant.
	 * @throws TechnicalException
	 *             si le manager à supprimer est le dernier dans la base
	 */
	public void deleteUser(Utilisateur u) throws IllegalOperationException, TechnicalException {
		if (loginManager.getConnectedUser() instanceof Gestionnaire) {
			banqueManager.deleteUser(u);
		}
	}

	/**
	 * L'utilisateur connecté doit être un getstionnaire
	 * 
	 * Charge la banqueManager avec une map de tous les clients
	 */
	public void loadClients() {
		if (loginManager.getConnectedUser() instanceof Gestionnaire) {
			banqueManager.loadAllClients();
		}
	}

	/**
	 * Méthode pour récupérer un objet compte basé sur son String identidiant
	 * 
	 * @param idCompte
	 *            : String correspondant à l'ID du compte qu'on veut récupérer
	 * @return Compte : objet correspondant à celui demandé
	 */
	public Compte getCompte(String idCompte) {
		return banqueManager.getAccountById(idCompte);
	}

	/**
	 * L'utilisateur connecté doit être un gestionnaire
	 * 
	 * Méthode pour changer le découvert autorisé d'un compte
	 * 
	 * @param compte
	 *            : CompteAvecDecouvert correspondant au compte qu'on veut
	 *            modifier
	 * @param nouveauDecouvert
	 *            : double correspondant au nouveau montant de découvert qu'on
	 *            veut assigner
	 * @throws IllegalFormatException
	 * @throws IllegalOperationException 
	 */
	public void changeDecouvert(CompteAvecDecouvert compte, double nouveauDecouvert) throws IllegalFormatException, IllegalOperationException {
		if (loginManager.getConnectedUser() instanceof Gestionnaire) {
			banqueManager.changeDecouvert(compte, nouveauDecouvert);
		}
	}
}
