<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="s" uri="/struts-tags" %>

<html>
<head>
<title>Tableau de bord - Gestionnaire</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="/_00_ASBank2023/style/style.css" />
</head>
<body>
	<div class="btnLogout">
		<s:form name="myForm" action="logout" method="POST">
			<s:submit name="Retour" value="Logout" />
		</s:form>
	</div>
	<h1>Tableau de bord - Gestionnaire</h1>
	
	<p>Bienvenue <b><s:property value="connectedUser.prenom" /> <s:property value="connectedUser.nom" /></b> !</p>
	<p>Que voulez vous faire ?</p>
	<p>
		<s:url action="listeCompteManager" var="urlListeCompteManager" >
			<s:param name="aDecouvert">false</s:param>
		</s:url>
		<s:a href="%{urlListeCompteManager}">Liste des comptes de la banque</s:a>
	</p>
	<p>
		<s:url action="listeCompteManager" var="urlListeCompteManager" >
			<s:param name="aDecouvert">true</s:param>
		</s:url>
		<s:a href="%{urlListeCompteManager}">Liste des comptes à découvert de la banque</s:a>
	</p>
	<p>
		<s:url action="urlAjoutUtilisateur" var="urlAjoutUtilisateur" >
		</s:url>
		<s:a href="%{urlAjoutUtilisateur}">Ajout d'un utilisateur</s:a>
	</p>
</body>
<jsp:include page="/JSP/Footer.jsp" />
</html>


