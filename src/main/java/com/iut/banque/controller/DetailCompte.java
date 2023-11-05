package com.iut.banque.controller;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.iut.banque.exceptions.IllegalFormatException;
import com.iut.banque.exceptions.InsufficientFundsException;
import com.iut.banque.facade.BanqueFacade;
import com.iut.banque.modele.Client;
import com.iut.banque.modele.Compte;
import com.iut.banque.modele.Gestionnaire;
import com.opensymphony.xwork2.ActionSupport;

public class DetailCompte extends ActionSupport {

	private static final long serialVersionUID = 1L;
	protected BanqueFacade banque;
	private String montant;
	private String error;
	protected Compte compte;

	/**
	 * Constructeur du controlleur DetailCompte
	 * 
	 * Récupère l'ApplicationContext
	 * 
	 * @return un nouvel objet DetailCompte avec une BanqueFacade provenant de
	 *         la factory
	 */
	public DetailCompte() {
		System.out.println("In Constructor from DetailCompte class ");
		ApplicationContext context = WebApplicationContextUtils
				.getRequiredWebApplicationContext(ServletActionContext.getServletContext());
		this.banque = (BanqueFacade) context.getBean("banqueFacade");
	}

	/**
	 * Retourne sous forme de string le message d'erreur basé sur le champ
	 * "error" actuellement défini dans la classe
	 * 
	 * @return String, le string avec le détail du message d'erreur
	 */
	public String getError() {
		switch (error) {
		case "TECHNICAL":
			return "Erreur interne. Verifiez votre saisie puis réessayer. Contactez votre conseiller si le problème persiste.";
		case "BUSINESS":
			return "Fonds insuffisants.";
		case "NEGATIVEAMOUNT":
			return "Veuillez rentrer un montant positif.";
		case "NEGATIVEOVERDRAFT":
			return "Veuillez rentrer un découvert positif.";
		case "INCOMPATIBLEOVERDRAFT":
			return "Le nouveau découvert est incompatible avec le solde actuel.";
		default:
			return "";
		}
	}

	/**
	 * Permet de définir le champ error de la classe avec le string passé en
	 * paramètre. Si jamais on passe un objet null, on adapte le string
	 * automatiquement en "EMPTY"
	 * 
	 * @param error
	 *            : Un String correspondant à celui qu'on veut définir dans le
	 *            champ error
	 */
	public void setError(String error) {
		if (error == null) {
			this.error = "EMPTY";
		} else {
			this.error = error;
		}
	}

	/**
	 * Getter du champ montant
	 * 
	 * @return String : valeur du champ montant
	 */
	public String getMontant() {
		return montant;
	}

	/**
	 * Setter du champ montant
	 * 
	 * @param montant
	 *            un String correspondant au montant à définir
	 */
	public void setMontant(String montant) {
		this.montant = montant;
	}

	/**
	 * Getter du compte actuellement sélectionné. Récupère la liste des comptes
	 * de l'utilisateur connecté dans un premier temps. Récupère ensuite dans la
	 * HashMap la clé qui comporte le string provenant de idCompte. Renvoie donc
	 * null si le compte n'appartient pas à l'utilisateur
	 * 
	 * @return Compte : l'objet compte après s'être assuré qu'il appartient à
	 *         l'utilisateur
	 */
	public Compte getCompte() {
		if (banque.getConnectedUser() instanceof Gestionnaire) {
			return compte;
		} else if (banque.getConnectedUser() instanceof Client) {
			if (((Client) banque.getConnectedUser()).getAccounts().containsKey(compte.getNumeroCompte())) {
				return compte;
			}
		}
		return null;
	}

	public void setCompte(Compte compte) {
		this.compte = compte;
	}

	/**
	 * Méthode débit pour débter le compte considéré en cours
	 * 
	 * @return String : Message correspondant à l'état du débit (si il a réussi
	 *         ou pas)
	 */
	public String debit() {
		Compte compte = getCompte();
		try {
			banque.debiter(compte, Double.parseDouble(montant.trim()));
			return "SUCCESS";
		} catch (NumberFormatException e) {
			e.printStackTrace();
			return "ERROR";
		} catch (InsufficientFundsException ife) {
			ife.printStackTrace();
			return "NOTENOUGHFUNDS";
		} catch (IllegalFormatException e) {
			e.printStackTrace();
			return "NEGATIVEAMOUNT";
		}
	}

	/**
	 * Méthode crédit pour créditer le compte considéré en cours
	 * 
	 * @return String : Message correspondant à l'état du crédit (si il a réussi
	 *         ou pas)
	 */
	public String credit() {
		Compte compte = getCompte();
		try {
			banque.crediter(compte, Double.parseDouble(montant.trim()));
			return "SUCCESS";
		} catch (NumberFormatException nfe) {
			nfe.printStackTrace();
			return "ERROR";
		} catch (IllegalFormatException e) {
			e.printStackTrace();
			return "NEGATIVEAMOUNT";
		}
	}
}
