package com.iut.banque.facade;

import com.iut.banque.constants.LoginConstants;
import com.iut.banque.interfaces.IDao;
import com.iut.banque.modele.Gestionnaire;
import com.iut.banque.modele.Utilisateur;

public class LoginManager {

	private IDao dao;
	private Utilisateur user;

	/**
	 * Setter pour la DAO.
	 * 
	 * Utilisé par Spring par Injection de Dependence
	 * 
	 * @param dao
	 *            : la dao nécessaire pour le LoginManager
	 */
	public void setDao(IDao dao) {
		this.dao = dao;
	}

	/**
	 * Méthode pour permettre la connection de l'utilisateur via un login en
	 * confrontant le mdp d'un utilisateur de la base de données avec le mot de
	 * passe donné en paramètre
	 * 
	 * @param userCde
	 *            : un String correspondant au userID de l'utilisateur qui
	 *            cherche à se connecter
	 * @param userPwd
	 *            : un String correspondant au mot de passe qui doit être
	 *            confronté avec celui de la base de données
	 * @return int correspondant aux constantes LoginConstants pour inforer de
	 *         l'état du login
	 */
	public int tryLogin(String userCde, String userPwd) {
		if (dao.isUserAllowed(userCde, userPwd)) {
			user = dao.getUserById(userCde);
			if (user instanceof Gestionnaire) {
				return LoginConstants.MANAGER_IS_CONNECTED;
			} else {
				return LoginConstants.USER_IS_CONNECTED;
			}
		} else {
			return LoginConstants.LOGIN_FAILED;
		}
	}

	/**
	 * Getter pour avoir l'objet Utilisateur de celui qui est actuellement
	 * connecté à l'application
	 * 
	 * @return Utilisateur : l'objet Utilisateur de celui qui est connecté
	 */
	public Utilisateur getConnectedUser() {
		return user;
	}

	/**
	 * Setter pour changer l'utilisateur actuellement connecté à l'application
	 * 
	 * @param user
	 *            : un objet de type Utilisateur (Client ou Gestionnaire) que
	 *            l'on veut définir comme utilisateur actuellement connecté à
	 *            l'application
	 */
	public void setCurrentUser(Utilisateur user) {
		this.user = user;
	}

	/**
	 * Remet l'utilisateur à null et déconnecte la DAO.
	 */
	public void logout() {
		this.user = null;
		dao.disconnect();
	}
}
