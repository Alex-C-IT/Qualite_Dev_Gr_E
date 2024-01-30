package com.iut.banque.controller;

import com.iut.banque.facade.MotDePasseOublieManager;
import com.iut.banque.interfaces.IDao;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;


import java.util.logging.Logger;

/**
 * Classe MotDePasseOublie
 *
 * Classe permettant de gérer la récupération d'un mot de passe oublié
 */
public class MotDePasseOublie {
    private String userCde;
    private String email;
    private String message;
    private String result;
    Logger logger = Logger.getLogger(getClass().getName());
    private MotDePasseOublieManager motDePasseOublieManager;

    static final String ERROR = "ERROR";
    IDao dao;

    /**
     * Constructeur de la classe MotDePasseOublie
     *
     * Utilisé par Spring par Injection de Dépendence
     */
    public MotDePasseOublie() {
        logger.info("In Constructor from MotDePasseOublie class ");
        ApplicationContext context = WebApplicationContextUtils
                .getRequiredWebApplicationContext(ServletActionContext.getServletContext());
        this.motDePasseOublieManager = (MotDePasseOublieManager) context.getBean("mdpOublieManager");
    }

    /**
     * Méthode pour verifier si l'utilisateur est autorisé à récupérer son mot de passe et lui envoyer un email avec son nouveau mot de passe
     * @return String : "SUCCESS" si l'email a été envoyé, "ERROR" sinon
     */
    public String execute() {
        this.result = ERROR;
        if (this.userCde == null || this.email == null) {
            return this.result;
        }

        this.userCde = this.userCde.trim();

        try {
            if(this.motDePasseOublieManager.motDePasseOublie(this.userCde, this.email)) {
                this.message = "Un email contenant votre nouveau mot de passe vous a été envoyé.";
                this.result = "SUCCESS";
                return this.result;
            } else {
                this.message = "Erreur lors de l'envoi de l'email.";
                this.result = ERROR;
                return this.result;
            }
        } catch (Exception e) {
            logger.info("Erreur lors de l'envoi de l'email : " + e);
            this.message = "Erreur lors de l'envoi de l'email.";
            this.result = ERROR;
            return this.result;
        }
    }

    public String getUserCde() {
        return userCde;
    }

    public void setUserCde(String userCde) {
        this.userCde = userCde;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getResult() {
        return this.result;
    }

    public void setResult(String result) {
        this.result = result;
    }

}