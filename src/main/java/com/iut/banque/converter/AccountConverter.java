package com.iut.banque.converter;

import java.util.Map;

import org.apache.struts2.util.StrutsTypeConverter;

import com.opensymphony.xwork2.conversion.TypeConversionException;

import com.iut.banque.interfaces.IDao;
import com.iut.banque.modele.Compte;

/**
 * Cette classe contient des méthodes permettant de convertir un compte en
 * string et vice-versa. Elle est déclarée dans
 * «src/main/webapp/WEB-INF/classes/xwork-conversion.properties.
 * 
 * Grâce à cette classe il est possible de passer en paramêtre d'une action
 * Struts le numéro d'un compte (une string) et le contrôleur qui va
 * recevoir le paramêtre n'a besoin que d'un setter prenant un objet de type
 * Compte.
 */
public class AccountConverter extends StrutsTypeConverter {

	/**
	 * DAO utilisée pour récuperer les objets correspondants à l'id passé en
	 * paramêtre de convertFromString.
	 * 
	 * Note : Ce champ est static car pour une raison qui nous échappe, le scope
	 * « singleton » du bean Spring utilisé pour l'injection n'est pas respecté.
	 * Ainsi, au chargement de l'application, trois objets de cette classe sont
	 * instanciés et seulement le premier a une DAO injectée correctement.
	 */
	private static IDao dao;

	/**
	 * Constructeur avec paramêtre pour le AccountConverter.
	 * 
	 * Utilisé pour l'injection de dépendance.
	 * 
	 * @param dao
	 */
	public AccountConverter(IDao dao) {
		System.out.println("=========================");
		System.out.println("Création du convertisseur de compte");
		AccountConverter.dao = dao;
		//System.out.println("DAO injectée : " + dao);
	}

	/**
	 * 	Constructeur sans paramêtre pour le AccountConverter
	 */
	public AccountConverter() {
		System.out.println("=========================");
		System.out.println("Création du convertisseur de compte");
		//System.out.println("DAO : " + dao);
	}

	/**
	 * Permet la conversion automatique par Struts d'un tableau de chaine vers
	 * un Compte
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public Object convertFromString(Map context, String[] values, Class classe) {
		Compte compte = (Compte) dao.getAccountById(values[0]);
		if (compte == null) {
			throw new TypeConversionException("Impossible de convertir la chaine suivante : " + values[0]);
		}
		return compte;
	}

	/**
	 * Permet la conversion automatique par Struts d'un compte vers une chaine.
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public String convertToString(Map context, Object value) {
		Compte compte = (Compte) value;
		return compte == null ? null : compte.getNumeroCompte();
	}

}
