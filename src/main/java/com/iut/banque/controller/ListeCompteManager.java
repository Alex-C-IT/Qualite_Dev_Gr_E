package com.iut.banque.controller;

import java.util.Map;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.opensymphony.xwork2.ActionSupport;

import com.iut.banque.exceptions.IllegalOperationException;
import com.iut.banque.exceptions.TechnicalException;
import com.iut.banque.facade.BanqueFacade;
import com.iut.banque.modele.Client;
import com.iut.banque.modele.Compte;

public class ListeCompteManager extends ActionSupport {

	private static final long serialVersionUID = 1L;
	private BanqueFacade banque;
	private boolean aDecouvert;
	private Compte compte;
	private Client client;
	private String userInfo;
	private String compteInfo;

	/**
	 * Constructeur de la classe Connect
	 * 
	 * @return Un objet de type Connect avec façade BanqueFacade provenant de sa
	 *         factory
	 */
	public ListeCompteManager() {
		System.out.println("In Constructor from ListeCompteManager class ");
		ApplicationContext context = WebApplicationContextUtils
				.getRequiredWebApplicationContext(ServletActionContext.getServletContext());
		this.banque = (BanqueFacade) context.getBean("banqueFacade");

	}

	/**
	 * Méthode qui va renvoer la liste de tous les clients sous forme de hashmap
	 * 
	 * @return Map<String,Client> : la hashmap correspondant au résultat
	 */
	public Map<String, Client> getAllClients() {
		banque.loadClients();
		return banque.getAllClients();
	}

	/**
	 * Getter pour le champ aDecouvert.
	 * 
	 * @return boolean : la valeur du champ aDecouvert
	 */
	public boolean isaDecouvert() {
		return aDecouvert;
	}

	/**
	 * Setter pour le champ aDecouvert.
	 * 
	 * @param aDecouvert
	 *            : la valeur de ce qu'on veut définir
	 */
	public void setaDecouvert(boolean aDecouvert) {
		this.aDecouvert = aDecouvert;
	}

	/**
	 * @return the compte
	 */
	public Compte getCompte() {
		return compte;
	}

	/**
	 * @param compte
	 *            the compte to set
	 */
	public void setCompte(Compte compte) {
		this.compte = compte;
	}

	/**
	 * @return the client
	 */
	public Client getClient() {
		return client;
	}

	/**
	 * @param client
	 *            the client to set
	 */
	public void setClient(Client client) {
		this.client = client;
	}

	/**
	 * @return the userInfo
	 */
	public String getUserInfo() {
		return userInfo;
	}

	/**
	 * @param userInfo
	 *            the userInfo to set
	 */
	private void setUserInfo(String userInfo) {
		this.userInfo = userInfo;
	}

	/**
	 * @return the userInfo
	 */
	public String getCompteInfo() {
		return compteInfo;
	}

	/**
	 * @param compteInfo
	 *            the compteInfo to set
	 */
	private void setCompteInfo(String compteInfo) {
		this.compteInfo = compteInfo;
	}

	/**
	 * Action appelée pour supprimer un utilisateur
	 * 
	 * @return String, le status de l'opération
	 */
	public String deleteUser() {
		try {
			setUserInfo(client.getIdentity());
			banque.deleteUser(client);
			return "SUCCESS";
		} catch (TechnicalException e) {
			e.printStackTrace();
			return "ERROR";
		} catch (IllegalOperationException ioe) {
			ioe.printStackTrace();
			return "NONEMPTYACCOUNT";
		}
	}

	/**
	 * Action appelée pour supprimer un compte
	 * 
	 * @return String, le status de l'opération
	 */
	public String deleteAccount() {
		try {
			setCompteInfo(compte.getNumeroCompte());
			banque.deleteAccount(compte);
			return "SUCCESS";
		} catch (IllegalOperationException e) {
			e.printStackTrace();
			return "NONEMPTYACCOUNT";
		} catch (TechnicalException e) {
			e.printStackTrace();
			return "ERROR";
		}
	}
}
