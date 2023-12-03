package com.iut.banque.utils;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;

/**
 * Classe EmailSender
 *
 * Classe permettant d'envoyer un email
 */
public class EmailSender {
    /**
     * Méthode pour envoyer un email
     * @param session : une Session correspondant à l'authentification pour envoyer l'email
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
            System.out.println("Le message est prêt");
            Transport.send(msg);

            System.out.println("Email envoyé avec succès.");
            return true;
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
