<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Formulaire de création d'utilisateur</title>
<link rel="stylesheet" href="/_00_ASBank2023/style/style.css" />
<script src="/_00_ASBank2023/js/jquery.js"></script>
<script src="/_00_ASBank2023/js/jsCreerUtilisateur.js"></script>
</head>

<body>

	<div class="btnLogout">
		<s:form name="myForm" action="logout" method="POST">
			<s:submit name="Retour" value="Logout" />
		</s:form>
	</div>
	<h1>Créer un nouvel utilisateur</h1>
	<s:form id="myForm" name="myForm" action="ajoutUtilisateur"
		method="POST">
		<s:textfield label="Code utilisateur" name="userId" />
		<s:textfield label="Nom" name="nom" />
		<s:textfield label="Prenom" name="prenom" />
		<s:textfield label="Adresse" name="adresse" />
		<s:password label="Password" name="userPwd" />
		<s:radio label="Sexe" name="male" list="#{true:'Homme',false:'Femme'}"
			value="true" />
		<s:radio label="Type" name="client"
			list="#{true:'Client',false:'Manager'}" value="true" />
		<s:textfield label="Numéro de client" name="numClient" />
		<s:submit name="submit" />
	</s:form>
	<s:form name="myForm" action="retourTableauDeBordManager" method="POST">
		<s:submit name="Retour" value="Retour" />
	</s:form>

	<s:if test="(result == \"SUCCESS\")">
		<div class="success">
			<s:property value="message" />
		</div>
	</s:if>
	<s:else>
		<div class="failure">
			<s:property value="message" />
		</div>
	</s:else>
</body>
<jsp:include page="/JSP/Footer.jsp" />
</html>