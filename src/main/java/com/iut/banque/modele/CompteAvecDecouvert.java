package com.iut.banque.modele;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import com.iut.banque.exceptions.IllegalFormatException;
import com.iut.banque.exceptions.IllegalOperationException;
import com.iut.banque.exceptions.InsufficientFundsException;
import com.iut.banque.exceptions.TechnicalException;

/**
 * Cette classe représente un compte qui a un découvert autorisé.
 */
@Entity
@DiscriminatorValue("AVEC")
public class CompteAvecDecouvert extends Compte {

	/**
	 * Le découvert autorisé sur le compte. Il doit rester positif.
	 * 
	 * Le solde ne peut pas aller en dessous de l'opposé de ce nombre. C'est à
	 * dire que la somme du decouvert et du solde doit être positive.
	 */
	@Column(name = "decouvertAutorise")
	private double decouvertAutorise;

	/**
	 * Getter du découvert autorisé du compte.
	 * 
	 * @return double, le découvert autorisé du compte
	 */
	public double getDecouvertAutorise() {
		return decouvertAutorise;
	}

	/**
	 * Setter du découvert autorisé du compte.
	 * 
	 * @return decouvertAutorise : le découvert à changer
	 * @throws IllegalFormatException
	 *             : si le découvert est inférieur à 0
	 * @throws IllegalOperationException 
	 */
	public void setDecouverAutorise(double decouvertAutorise) throws IllegalFormatException, IllegalOperationException {
		if (decouvertAutorise < 0) {
			throw new IllegalFormatException("Un découvert doit forcement être positif.");
		} else if (this.solde < 0 && decouvertAutorise < Math.abs(this.solde)) {
			throw new IllegalOperationException("Le nouveau découvert est incompatible avec le solde actuel");
		}
		this.decouvertAutorise = decouvertAutorise;
	}

	/**
	 * Constructeur de CompteAvecDecouvert avec tous les champs de la classe
	 * comme paramètres.
	 * 
	 * Il est préférable d'utiliser une classe implémentant IDao pour créer un
	 * objet au lieu d'appeler ce constructeur.
	 * 
	 * @throws TechnicalException
	 *             : si le découvert autorisé est inférieur à 0
	 * @throws IllegalFormatException
	 *             : si le numero de compte ne respecte pas le format
	 * @throws IllegalOperationException 
	 */
	public CompteAvecDecouvert(String numeroCompte, double solde, double decouvertAutorise, Client client)
			throws IllegalFormatException, IllegalOperationException {
		super(numeroCompte, solde, client);
		this.setDecouverAutorise(decouvertAutorise);
	}

	/**
	 * Constructeur de CompteAvecDecouvert sans parametres.
	 * 
	 * Nécessaire pour Hibernate.
	 * 
	 * Il est préférable d'utiliser une classe implémentant IDao pour créer un
	 * objet au lieu d'appeler ce constructeur.
	 */
	public CompteAvecDecouvert() {
		super();
	}

	/**
	 * {@inheritDoc}
	 * 
	 * Cette méthode tient en compte du découvert possible.
	 */
	@Override
	public void debiter(double montant) throws InsufficientFundsException, IllegalFormatException {
		if (montant < 0) {
			throw new IllegalFormatException("Le montant ne peux être négatif");
		} else if (montant > this.solde + this.decouvertAutorise) {
			throw new InsufficientFundsException("Le solde du compte " + this.numeroCompte + " est insuffisant.");
		} else {
			this.solde -= montant;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "CompteAvecDecouvert [decouvertAutorise=" + decouvertAutorise + ", numeroCompte=" + numeroCompte
				+ ", solde=" + solde + ", owner=" + owner + "]";
	}
}
