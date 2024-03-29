package com.iut.banque.utils;

import com.iut.banque.dao.DaoHibernate;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.logging.Logger;

/**
 * Classe EmailSender
 *
 * Classe permettant d'envoyer un email
 */
public class EmailSender {
    static final Logger loggerEmail = Logger.getLogger(EmailSender.class.getName());
    /**
     * Méthode pour envoyer un email
     * @param fromEmail: un String correspondant à l'email de l'emetteur
     * @param toEmail : un String correspondant à l'email de l'utilisateur qui cherche à récupérer son mot de passe
     * @param subject : un String correspondant au sujet de l'email
     * @param body : un String correspondant au corps de l'email
     * @return boolean : true si l'email a été envoyé, false sinon
     */
    public static boolean sendEmail(Session session, String fromEmail, String toEmail, String subject, String body){
        try
        {
            MimeMessage msg = new MimeMessage(session);
            //set message headers
            msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
            msg.addHeader("format", "flowed");
            msg.addHeader("Content-Transfer-Encoding", "8bit");

            msg.setFrom(new InternetAddress(fromEmail, "IUTBank"));

            msg.setReplyTo(InternetAddress.parse(toEmail, false));

            msg.setSubject(subject, "UTF-8");

            msg.setContent(body, "text/html; charset=utf-8");

            msg.setSentDate(new Date());

            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail, false));
            loggerEmail.info("Le message est prêt");
            Transport.send(msg);

            loggerEmail.info("Email envoyé avec succès.");
            return true;
        }
        catch (Exception e) {
            loggerEmail.info("Erreur lors de l'envoi de l'email : " + e);
        }
        return false;
    }
}
