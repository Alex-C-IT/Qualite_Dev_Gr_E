<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Formulaire de création d'un compte</title>
<link rel="stylesheet" href="/_00_ASBank2023/style/style.css" />
<script src="/_00_ASBank2023/js/jquery.js"></script>
<script src="/_00_ASBank2023/js/jsCreerCompte.js"></script>
</head>

<body>
	<h1>Créer un nouveau compte</h1>

	<p>
		Client choisi :
		<s:property value="client" />
	</p>
	<s:form id="myForm" name="myForm" action="addAccount" method="POST">
		<input type="hidden" name="client"
			value="<s:property value='client.userId' />">
		<s:textfield label="Numéro de compte" name="numeroCompte" />
		<s:radio label="Type" name="avecDecouvert"
			list="#{false:'Compte sans découvert',true:'Compte avec découvert'}"
			value="false" />
		<s:textfield label="Découvert autorisé" name="decouvertAutorise" />
		<s:submit name="submit" />
	</s:form>
	<s:form name="myForm" action="listeCompteManager" method="POST">
		<s:submit name="Retour" value="Retour" />
	</s:form>

	<s:if test="result"> 
		<s:if test="!error">
			<div class="success">
				<s:property value="message" />
			</div>
		</s:if>
		<s:else>
			<div class="failure">
				<s:property value="message" />
			</div>
		</s:else>
	</s:if>
</body>
<jsp:include page="/JSP/Footer.jsp" />
</html>