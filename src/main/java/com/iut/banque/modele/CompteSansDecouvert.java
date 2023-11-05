package com.iut.banque.modele;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import com.iut.banque.exceptions.IllegalFormatException;
import com.iut.banque.exceptions.InsufficientFundsException;

/**
 * Cette classe représente un compte simple, sans découvert autorisé.
 */
@Entity
@DiscriminatorValue("SANS")
public class CompteSansDecouvert extends Compte {
	
	/**
	 * Constructeur de CompteSansDecouvert avec tous les champs de la classe
	 * comme paramètres.
	 * 
	 * Il est préférable d'utiliser une classe implémentant IDao pour créer un
	 * objet au lieu d'appeler ce constructeur.
	 */
	public CompteSansDecouvert(String numeroCompte, double solde, Client client) throws IllegalFormatException {
		super(numeroCompte, solde, client);
	}
	
	/**
	 * Constructeur de CompteSansDecouvert sans parametres.
	 * 
	 * Nécessaire pour Hibernate.
	 * 
	 * Il est préférable d'utiliser une classe implémentant IDao pour crér un
	 * objet au lieu d'appeler ce constructeur.
	 */
	public CompteSansDecouvert() {
		super();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void debiter(double montant) throws InsufficientFundsException, IllegalFormatException{
		if (montant < 0) {
			throw new IllegalFormatException("Le montant ne peux être négatif");
		} else if (montant > this.solde) {
			throw new InsufficientFundsException("Le solde du compte " + this.numeroCompte + " est insuffisant.");
		} else {
			this.solde -= montant;
		}
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "CompteSansDecouvert [numeroCompte=" + numeroCompte + ", solde=" + solde + ", owner=" + owner + "]";
	}
}
