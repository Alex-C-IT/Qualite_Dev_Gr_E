package com.iut.banque.modele;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.MapKey;
import javax.persistence.OneToMany;

import com.iut.banque.exceptions.IllegalFormatException;

/**
 * Cette classe représente un client quelconque de l'entreprise.
 */
@Entity
@DiscriminatorValue("CLIENT")
public class Client extends Utilisateur {

	/**
	 * Numéro du client.
	 * 
	 * Celui est unique mais ce n'est pas la clé primaire utilisée par la BDD.
	 */
	@Column(name = "numClient", unique = true)
	private String numeroClient;

	/**
	 * Map des comptes que le client possède. La clé de la Map est le numéro de
	 * compte.
	 * 
	 * L'association "one-to-many" signifie que chaque client possède plusieurs
	 * comptes mais que chaque compte a un unique propriétaire.
	 * 
	 * FetchType.EAGER signifie que le chargment des comptes n'est pas
	 * paresseux. Ce n'est pas optimal si il y a beaucoup de comptes, mais cela
	 * permet de travailler avec les objets Clients en dehors d'une session.
	 */
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "owner")
	@MapKey(name = "numeroCompte")
	private Map<String, Compte> accounts;

	/**
	 * {@inheritDoc}
	 * 
	 * @throws IllegalFormatException
	 */
	public void setUserId(String userId) throws IllegalFormatException {
		if (!Client.checkFormatUserIdClient(userId)) {
			throw new IllegalFormatException("L'identifiant n'est pas au bon format.");
		}
		super.setUserId(userId);
	}

	/**
	 * Le numéro du client est unique mais ne correspond pas à l'identifiant de
	 * l'objet dans la base de données.
	 * 
	 * @return String, le numéro du client
	 */
	public String getNumeroClient() {
		return numeroClient;
	}

	/**
	 * Le numéro du client est unique mais ne correspond pas à l'identifiant de
	 * l'objet dans la base de données.
	 * 
	 * @param numeroClient
	 *            : le numéro du client
	 * @throws IllegalArgumentException
	 *             , dans le cas où numéro passé en argument est vide
	 * @throws IllegalFormatException
	 */
	public void setNumeroClient(String numeroClient) throws IllegalArgumentException, IllegalFormatException {
		if (numeroClient == null) {
			throw new IllegalArgumentException("Le numéro de client ne peut pas être nul");
		} else if (!checkFormatNumeroClient(numeroClient)) {
			throw new IllegalFormatException("Le numéro de client n'est pas au bon format.");
		} else {
			this.numeroClient = numeroClient;
		}
	}

	/**
	 * Constructeur de Client avec tous les champs de la classe comme
	 * paramètres.
	 * 
	 * Il est préférable d'utiliser une classe implémentant IDao pour créer un
	 * objet au lieu d'appeler ce constructeur.
	 * 
	 * @throws IllegalFormatException
	 * @throws IllegalArgumentException
	 */
	public Client(String nom, String prenom, String adresse, boolean homme, String usrId, String usrPwd,
			String numeroClient) throws IllegalArgumentException, IllegalFormatException {
		super(nom, prenom, adresse, homme, null, usrPwd);
		setUserId(usrId);
		setNumeroClient(numeroClient);
		this.accounts = new HashMap<String, Compte>();
	}

	/**
	 * Constructeur sans paramètre de Client.
	 * 
	 * Nécessaire pour Hibernate.
	 * 
	 * Il est préférable d'utiliser une classe implémentant IDao pour créer un
	 * objet au lieu d'appeler ce constructeur.
	 */
	public Client() {
		super();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return "Client [userId=" + getUserId() + ", nom=" + getNom() + ", prenom=" + getPrenom() + ", adresse="
				+ getAdresse() + ", male=" + isMale() + ", userPwd=" + getUserPwd() + ", numeroClient=" + numeroClient
				+ ", accounts=" + accounts.size() + "]";
	}

	/**
	 * Cette méthode est une version plus présentable du toString()
	 * 
	 * @return String, l'identité du client
	 */
	public String getIdentity() {
		return this.getPrenom() + " " + this.getNom() + " (" + this.getNumeroClient() + ")";
	}

	/**
	 * Getter pour l'ensemble des comptes possedés par le client.
	 * 
	 * @return Map<String, Compte>, la map retournée a pour clés les numéro de
	 *         compte et pour valeurs les objets Comptes.
	 */
	public Map<String, Compte> getAccounts() {
		return accounts;
	}

	/**
	 * Setter pour l'ensemble des comptes possedés par le client.
	 * 
	 * @param accounts
	 *            : la map passée en argument doit avoir pour clés les numéro de
	 *            compte et pour valeurs les objets Comptes.
	 */
	public void setAccounts(Map<String, Compte> accounts) {
		this.accounts = accounts;
	}

	/**
	 * Ajoute un compte parmis la liste de ceux du client.
	 * 
	 * @param compte
	 *            : le compte à ajouter
	 */
	public void addAccount(Compte compte) {
		this.accounts.put(compte.getNumeroCompte(), compte);
	}

	/**
	 * Fonction qui va vérifier le string d'entrée si il correspond au format
	 * attendu pour un identifiant de compte client. à savoir, une lettre, un
	 * point, une lettre au moins, chiffre entre 1 et 9, et éventuellement une
	 * succession de chiffres entre 0 et 9 supplémentaires (Par exemple :
	 * d.dupont123)
	 * 
	 * @param s
	 *            : String d'entrée qu'on veut comparer au format attendu
	 * @return boolean : résultat de la comparaison. True si le format est
	 *         correct, false sinon
	 */
	public static boolean checkFormatUserIdClient(String s) {
		return Pattern.matches("^[a-z]\\.[a-z]+[1-9][0-9]*$", s);
	}

	/**
	 * Fonction qui va vérifier le string d'entrée s'il correspond au format
	 * attendu pour un numéro de client, à savoir, 9 chiffres successifs (Par
	 * exemple 1234567890)
	 * 
	 * @param s
	 *            : String d'entrée qu'on veut comparer au format attendu
	 * @return boolean : résultat de la comparaison. True si le format est
	 *         correct, false sinon
	 */
	public static boolean checkFormatNumeroClient(String s) {
		return Pattern.matches("[0-9]{10}", s);
	}

	/**
	 * Fonction qui va vérifier si le client a au moins un compte à découvert.
	 * Renvoie true si un ou plus, false sinon
	 * 
	 * @return boolean : résultat de l'interrogation
	 */
	public boolean possedeComptesADecouvert() {
		boolean result = false;
		for (Compte value : accounts.values()) {
			if (!result && value.getSolde() < 0) {
				result = true;
				break;
			}
		}
		return result;
	}

	/**
	 * Renvoie tous les comptes avec un solde différent de zéro
	 * 
	 * @return Map<String, Compte> une map des comptes (key = id du compte,
	 *         value = compte)
	 */
	public Map<String, Compte> getComptesAvecSoldeNonNul() {
		Map<String, Compte> comptes = this.getAccounts();
		Map<String, Compte> res = new HashMap<String, Compte>();
		for (Map.Entry<String, Compte> entry : comptes.entrySet()) {
			if (entry.getValue().getSolde() != 0) {
				res.put(entry.getKey(), entry.getValue());
			}
		}
		return res;
	}

}
