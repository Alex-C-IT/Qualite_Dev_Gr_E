<%--
  Created by IntelliJ IDEA.
  User: Alexandre
  Date: 05/12/2023
  Time: 00:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="fr">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>IUT Bank - Compte bloqué</title>
        <link rel="stylesheet" href="/ASBank203/style/style.css" />
    </head>
    <body>
        <p><h2>Compte bloqué !</h2></p>
        <p>Vous avez dépassé le nombre de tentatives autorisées (3) pour vous connecter à votre compte.</p>
        <p>Pour débloquer votre compte, demandez un nouveau mot de passe : </p>
        <p>
            <s:url action="urlMotDePasseOublie" var="urlMotDePasseOublie" ></s:url>
            <s:a href="%{urlMotDePasseOublie}">Cliquez ici</s:a> pour renvoyer un nouveau mot de passe.
        </p>
        <p>
            <s:url action="redirectionLogin" var="redirectionLogin" ></s:url>
            <s:a href="%{redirectionLogin}">Cliquez ici</s:a> pour revenir à l'écran de login</p>
    </body>
<jsp:include page="/JSP/Footer.jsp" />
</html>
