package com.iut.banque.modele;

import java.util.Map;

import com.iut.banque.exceptions.IllegalFormatException;
import com.iut.banque.exceptions.IllegalOperationException;
import com.iut.banque.exceptions.InsufficientFundsException;

public class Banque {

	private Map<String, Client> clients;
	private Map<String, Gestionnaire> gestionnaires;
	private Map<String, Compte> accounts;

	public Banque() {
	}

	/**
	 * Getter du map de clients
	 * 
	 * @return Map<String, Client> : correspondant au champ clients
	 */
	public Map<String, Client> getClients() {
		return clients;
	}

	/**
	 * Setter du map de clients
	 * 
	 * @param accounts
	 *            : Map<String,Client> correpondant au Map qu'on veut assigner
	 */
	public void setClients(Map<String, Client> clients) {
		this.clients = clients;
	}

	/**
	 * Getter du map de clients
	 * 
	 * @return Map<String, Gestionnaire> : correspondant au champ gestionnaires
	 */
	public Map<String, Gestionnaire> getGestionnaires() {
		return gestionnaires;
	}

	/**
	 * Setter du map de gestionnaires
	 * 
	 * @param gestionnaires
	 *            : Map<String,Gestionnaire> correpondant au Map qu'on veut
	 *            assigner
	 */
	public void setGestionnaires(Map<String, Gestionnaire> gestionnaires) {
		this.gestionnaires = gestionnaires;
	}

	/**
	 * Getter du map de comptes
	 * 
	 * @return Map<String, Compte> : correspondant au champ accounts
	 */
	public Map<String, Compte> getAccounts() {
		return accounts;
	}

	/**
	 * Setter du map de comptes
	 * 
	 * @param accounts
	 *            : Map<String,Compte> correpondant au Map qu'on veut assigner
	 */
	public void setAccounts(Map<String, Compte> accounts) {
		this.accounts = accounts;
	}

	public void debiter(Compte compte, double montant) throws InsufficientFundsException, IllegalFormatException {
		compte.debiter(montant);
	}

	public void crediter(Compte compte, double montant) throws IllegalFormatException {
		compte.crediter(montant);
	}

	public void deleteUser(String userId) {
		clients.remove(userId);
	}

	public void changeDecouvert(CompteAvecDecouvert compte, double nouveauDecouvert) throws IllegalFormatException, IllegalOperationException {
		compte.setDecouverAutorise(nouveauDecouvert);
	}
}
