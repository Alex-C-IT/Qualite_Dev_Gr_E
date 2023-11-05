<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Erreur de Connexion</title>
<link rel="stylesheet" href="/ASBank203/style/style.css" />
</head>
<body>
	<p><b>Erreur de connection !</b> Vous avez probablement entré de mauvais identifiants</p>
	<p>
		<s:url action="redirectionLogin" var="redirectionLogin" ></s:url>
		<s:a href="%{redirectionLogin}">Cliquez ici</s:a> pour revenir à l'écran de login</p>
	<p>Si le problème persiste, veuillez contacter votre conseiller</p>
</body>
<jsp:include page="/JSP/Footer.jsp" />
</html>