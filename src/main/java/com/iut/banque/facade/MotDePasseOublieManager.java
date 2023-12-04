package com.iut.banque.facade;

import com.iut.banque.interfaces.IDao;
import com.iut.banque.modele.Utilisateur;
import com.iut.banque.utils.BcryptHashing;
import com.iut.banque.utils.EmailSender;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import java.util.Properties;
import java.util.Base64;

public class MotDePasseOublieManager {


    private IDao dao;
    private Utilisateur utilisateur;


    // SMTP Informations (constantes)
    private static final String HOST = "smtp.gmail.com";
    private static final String PORT = "587";
    private static final String AUTH_EMAIL = "qualitedeviut@gmail.com";
    private static final String A_P = "ZHl4aCBqbmh5IHVxb28gc3hqbA==";

    /**
     * Setter pour la DAO.
     *
     * Utilisé par Spring par Injection de Dependence
     *
     * @param dao
     *            : la dao nécessaire pour le MotDePasseOublieManager
     */
    public void setDao(IDao dao) {
        this.dao = dao;
    }

    /**
     * @param userCode: un String correspondant au code de l'utilisateur qui cherche à récupérer son mot de passe
     * @param userMail: un String correspondant à l'email de l'utilisateur qui cherche à récupérer son mot de passe
     * @return boolean : true si l'email a été envoyé, false sinon
     */
    public boolean motDePasseOublie(String userCode, String userMail) {
        // Récupération de l'utilisateur
        utilisateur = dao.getUserById(userCode);

        // Si l'utilisateur existe
        if (utilisateur != null) {
            // Si l'email de l'utilisateur correspond à celui renseigné
            if (utilisateur.getMail().equals(userMail)) {
                // Génération d'un nouveau mot de passe
                String nouveauMotDePasse = BcryptHashing.genererMotDePasseAleatoire();

                // Modification du mot de passe de l'utilisateur
                utilisateur.setUserPwd(BcryptHashing.hashPassword(nouveauMotDePasse));
                // Réinitialisation du nombre de tentatives de connexion
                utilisateur.setNbTentativesConnect(0);
                // Mise à jour de l'utilisateur dans la base de données
                dao.updateUser(utilisateur);

                // Envoi de l'email
                return envoyerEmail(utilisateur, nouveauMotDePasse);
            }
        }
        return false;
    }

    /**
     * Méthode pour envoyer un email
     * @param utilisateur : un Utilisateur correspondant à l'utilisateur qui cherche à récupérer son mot de passe
     * @param nouveauMotDePasse : un String correspondant au nouveau mot de passe de l'utilisateur
     * @return boolean : true si l'email a été envoyé, false sinon
     */
    private boolean envoyerEmail(Utilisateur utilisateur, String nouveauMotDePasse) {
        // Création de la session
        Properties props = new Properties();
        props.put("mail.smtp.host", HOST);
        props.put("mail.smtp.port", PORT);
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        // Création de l'authentification
        Authenticator auth = new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(AUTH_EMAIL, new String(Base64.getDecoder().decode(A_P)));
            }
        };

        // Création de la session
        Session session = Session.getInstance(props, auth);

        // Envoi de l'email
        return EmailSender.sendEmail(
                session,
                AUTH_EMAIL,
                utilisateur.getMail(),
                "IUTBank - Mot de passe oublié",
                "Bonjour " + utilisateur.getNom() + " " + utilisateur.getPrenom() + ".<br><br>Votre nouveau mot de passe est : " + nouveauMotDePasse + " .<br><br>Cordialement,<br>L'équipe IUTBank.");
    }
}
