package com.iut.banque.utils;

import java.security.SecureRandom;
import at.favre.lib.crypto.bcrypt.BCrypt;


/**
 * Classe utilitaire pour le hashage des mots de passe avec BCrypt.
 */
public class BcryptHashing {

    /**
     * Hashage d'un mot de passe avec BCrypt.
     *
     * @param password
     *            : le mot de passe à hasher
     * @return le mot de passe hashé
     */
    public static String hashPassword(String password) {
        return BCrypt.withDefaults().hashToString(12, password.toCharArray());
    }

    /**
     * Vérification d'un mot de passe avec BCrypt.
     *
     * @param password
     *            : le mot de passe à vérifier
     * @param hashedPassword
     *            : le mot de passe hashé
     * @return true si le mot de passe correspond, false sinon
     */
    public static boolean checkPassword(String password, String hashedPassword) {
        return BCrypt.verifyer().verify(password.toCharArray(), hashedPassword).verified;
    }

    /**
     * Génération d'un mot de passe aléatoire.
     *
     * @return un mot de passe aléatoire
     */
    public static String genererMotDePasseAleatoire() {
        SecureRandom random = new SecureRandom();
        return Long.toString(Math.abs(random.nextLong()), 36);
    }
}
