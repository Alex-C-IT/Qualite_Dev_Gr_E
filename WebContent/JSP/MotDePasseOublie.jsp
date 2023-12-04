<%--
  Created by IntelliJ IDEA.
  User: Alexandre
  Date: 03/12/2023
  Time: 18:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html lang="fr">
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Mot de passe oublié</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/style/style.css" />
  </head>
  <body>
  <h1>Mot de passe oublié</h1>
  <s:form action="motDePasseOublie" method="POST">
    <s:textfield name="userCde" label="Nom d'utilisateur" />
    <s:textfield name="email" label="Email" />
    <s:submit value="Envoyer" />
  </s:form>

  <p>
    <a href=" _00_ASBank2023/redirectionLogin.action">Retour à la page de connexion</a>
  </p>

  </body>
</html>

