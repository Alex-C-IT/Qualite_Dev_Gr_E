package com.iut.banque.modele;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import com.iut.banque.exceptions.IllegalFormatException;

/**
 * Cette classe représente un gestionnaire de comptes qui doit pouvoir accéder à
 * tous les comptes de sa banque.
 */
@Entity
@DiscriminatorValue("MANAGER")
public class Gestionnaire extends Utilisateur {

	/**
	 * Constructeur de Gestionnaire avec tous les champs de la classe comme
	 * paramètres.
	 * 
	 * Il est préférable d'utiliser une classe implémentant IDao pour créer un
	 * objet au lieu d'appeler ce constructeur.
	 * @throws IllegalFormatException 
	 */
	public Gestionnaire(String nom, String prenom, String adresse, boolean homme, String usrId, String usrPwd) throws IllegalFormatException {
		super(nom, prenom, adresse, homme, usrId, usrPwd);
		if ("".equals(usrId)) {
			throw new IllegalArgumentException("L'identifiant ne peux être vide.");
		}
	}

	/**
	 * Constructeur sans paramètre de Gestionnaire.
	 * 
	 * Nécessaire pour Hibernate.
	 * 
	 * Il est préférable d'utiliser une classe implémentant IDao pour créer un
	 * objet au lieu d'appeler ce constructeur.
	 */
	public Gestionnaire() {
		super();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Gestionnaire [nom=" + getNom() + ", prenom=" + getPrenom() + ", adresse=" + getAdresse() + ", male="
				+ isMale() + ", userId=" + getUserId() + ", userPwd=" + getUserPwd() + "]";
	}
}