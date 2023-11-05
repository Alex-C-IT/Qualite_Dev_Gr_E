<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="/_00_ASBank2023/style/style.css" />

<title>Détail du Compte <s:property value="compte" /></title>
</head>
<body>
	<div class="btnLogout">
		<s:form name="myForm" action="logout" method="POST">
			<s:submit name="Retour" value="Logout" />
		</s:form>
	</div>
	<h1>
		Détail du Compte
		<s:property value="compte" />
	</h1>
	<br />
	<div class="btnBack">
		<s:form name="myForm" action="listeCompteManager" method="POST">
			<s:submit name="Retour" value="Retour" />
		</s:form>
	</div>
	<p>
		Type de compte :
		<s:if test="%{compte.className == \"CompteAvecDecouvert\"}">
			Découvert possible
		</s:if>
		<s:else>
			Simple
		</s:else>
		<br />
		<s:if test="%{compte.solde >= 0}">
			Solde : <s:property value="compte.solde" />
			<br />
		</s:if>
		<s:else>
			Solde : <span class="soldeNegatif"><s:property
					value="compte.solde" /></span>
			<br />
		</s:else>
		<s:if test="%{compte.className == \"CompteAvecDecouvert\"}">
			Découvert maximal autorisé : <s:property
				value="compte.decouvertAutorise" />
		</s:if>
		<br />
	</p>
	<s:form name="formOperation" action="creditActionEdit" method="post">
		<s:textfield label="Montant" name="montant" />
		<input type="hidden" name="compte"
			value="<s:property value='compte.numeroCompte' />">
		<s:submit value="Crediter" />
		<s:submit value="Debiter" action="debitActionEdit" />
	</s:form>

	<s:if test="%{compte.className == \"CompteAvecDecouvert\"}">
		<s:form name="formChangeDecouvertAutorise"
			action="changerDecouvertAutoriseAction" method="post">
			<input type="hidden" name="compte"
				value="<s:property value='compte.numeroCompte' />">
			<s:textfield label="Découvert autorisé" name="decouvertAutorise"
				value="%{compte.decouvertAutorise}" />
			<s:submit value="Mettre à jour" />
		</s:form>
	</s:if>

	<s:url action="urlDetail" var="urlDetail">
		<s:param name="idCompte">
			<s:property value="key" />
		</s:param>
	</s:url>
	<s:if test="%{error != \"\"}">
		<div class="failure">
			Erreur :
			<s:property value="error" />
		</div>
	</s:if>
</body>
<jsp:include page="/JSP/Footer.jsp" />
</html>