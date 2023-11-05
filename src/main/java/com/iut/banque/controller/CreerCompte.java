package com.iut.banque.controller;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.opensymphony.xwork2.ActionSupport;

import com.iut.banque.exceptions.IllegalFormatException;
import com.iut.banque.exceptions.IllegalOperationException;
import com.iut.banque.exceptions.TechnicalException;
import com.iut.banque.facade.BanqueFacade;
import com.iut.banque.modele.Client;
import com.iut.banque.modele.Compte;

public class CreerCompte extends ActionSupport {

	private static final long serialVersionUID = 1L;
	private String numeroCompte;
	private boolean avecDecouvert;
	private double decouvertAutorise;
	private Client client;
	private String message;
	private boolean error;
	private boolean result;
	private BanqueFacade banque;
	private Compte compte;

	/**
	 * @param compte
	 *            the compte to set
	 */
	public void setCompte(Compte compte) {
		this.compte = compte;
	}

	/**
	 * @return the compte
	 */
	public Compte getCompte() {
		return compte;
	}

	/**
	 * @return the client
	 */
	public Client getClient() {
		return client;
	}

	/**
	 * Indique si le résultat de l'action précedente avait réussi
	 * 
	 * @return le status de l'action précédente
	 */
	public boolean isError() {
		return error;
	}

	/**
	 * Setter de l'action précédente
	 * 
	 * @param error
	 */
	public void setError(boolean error) {
		this.error = error;
	}

	/**
	 * @param client
	 *            the client to set
	 */
	public void setClient(Client client) {
		this.client = client;
	}

	/**
	 * Constructeur sans paramêtre de CreerCompte
	 */
	public CreerCompte() {
		System.out.println("In Constructor from CreerCompte class ");
		ApplicationContext context = WebApplicationContextUtils
				.getRequiredWebApplicationContext(ServletActionContext.getServletContext());
		this.banque = (BanqueFacade) context.getBean("banqueFacade");
	}

	/**
	 * @return the numeroCompte
	 */
	public String getNumeroCompte() {
		return numeroCompte;
	}

	/**
	 * @param numeroCompte
	 *            the numeroCompte to set
	 */
	public void setNumeroCompte(String numeroCompte) {
		this.numeroCompte = numeroCompte;
	}

	/**
	 * @return the avecDecouvert
	 */
	public boolean isAvecDecouvert() {
		return avecDecouvert;
	}

	/**
	 * @param avecDecouvert
	 *            the avecDecouvert to set
	 */
	public void setAvecDecouvert(boolean avecDecouvert) {
		this.avecDecouvert = avecDecouvert;
	}

	/**
	 * @return the decouvertAutorise
	 */
	public double getDecouvertAutorise() {
		return decouvertAutorise;
	}

	/**
	 * @param decouvertAutorise
	 *            the decouvertAutorise to set
	 */
	public void setDecouvertAutorise(double decouvertAutorise) {
		this.decouvertAutorise = decouvertAutorise;
	}

	/**
	 * Getter du message résultant de l'action précédente.
	 * 
	 * @return le message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * Choisi le message à enregistrer en fonction du message reçu en paramêtre.
	 * 
	 * @param message
	 *            : le message indiquant le status de l'action précédente.
	 */
	public void setMessage(String message) {
		switch (message) {
		case "NONUNIQUEID":
			this.message = "Ce numéro de compte existe déjà !";
			break;
		case "INVALIDFORMAT":
			this.message = "Ce numéro de compte n'est pas dans un format valide !";
			break;
		case "SUCCESS":
			this.message = "Le compte " + compte.getNumeroCompte() + " a bien été créé.";
			break;
		}
	}

	/**
	 * Getter du status de l'action précédente. Si vrai, indique qu'une création
	 * de compte a déjà été essayée (elle peut avoir réussi ou non). Sinon, le
	 * client vient d'arriver sur la page.
	 * 
	 * @return le status de l'action précédente
	 */
	public boolean isResult() {
		return result;
	}

	/**
	 * Setter du status de l'action précédente.
	 * 
	 * @param result
	 *            : le status
	 */
	public void setResult(boolean result) {
		this.result = result;
	}

	/**
	 * Action créant un compte client ou gestionnaire.
	 * 
	 * @return une chaine déterminant le résultat de l'action
	 */
	public String creationCompte() {
		try {
			if (avecDecouvert) {
				try {
					banque.createAccount(numeroCompte, client, decouvertAutorise);
				} catch (IllegalOperationException e) {
					e.printStackTrace();
				}
			} else {
				banque.createAccount(numeroCompte, client);
			}
			this.compte = banque.getCompte(numeroCompte);
			return "SUCCESS";
		} catch (TechnicalException e) {
			return "NONUNIQUEID";
		} catch (IllegalFormatException e) {
			return "INVALIDFORMAT";
		}

	}
}
