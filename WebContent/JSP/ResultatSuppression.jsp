<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="s" uri="/struts-tags"%>

<html>
<head>
<title>Résultat de la suppression</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="/_00_ASBank2023/style/style.css" />
</head>
<body>

	<h1>Résultat de la suppression</h1>

	<s:if test="!error">
		<div class="success">
			<s:if test="isAccount()">
				Le compte <s:property value="compteInfo"/> du client <s:property value="client" /> a bien été supprimé.
			</s:if>
			<s:else>
				Le client <s:property value="userInfo"/> a bien été supprimé.
			</s:else>
		</div>
	</s:if>
	<s:else>
		<div class="failure">
			<s:if test="%{errorMessage == \"NONEMPTYACCOUNT\"}">
				<s:if test="isAccount()">
					Une erreur est parvenue lors de la suppression du compte <s:property value="compte" /> du client <s:property value="client" />.
					<br />Le compte a un solde différent de zéro (<s:property value="compte.solde" />).
				</s:if>
				<s:else>
					Une erreur est parvenue lors de la suppression du client <s:property value="client" />.
					<br />Les comptes suivants ont un solde différent de zéro : <br />
					<ul>
						<s:iterator value="client.comptesAvecSoldeNonNul">
							<li><s:property value="value" /> (<s:property value="value.solde" />)</li>	
						</s:iterator>
					</ul>	
				</s:else>
			</s:if>
			<s:else>
				<s:if test="isAccount()">
					Une erreur est parvenue lors de la suppression du compte <s:property value="compte" /> du client <s:property value="client" />.
					Veuillez réessayer.
				</s:if>
				<s:else>
					Une erreur est parvenue lors de la suppression du client <s:property value="client" />.
					Veuillez réessayer.
				</s:else>
			</s:else>
		</div>
	</s:else>
	<s:form name="myForm" action="listeCompteManager" method="POST">
		<s:submit name="Retour" value="Retour" />
	</s:form>
</body>
<jsp:include page="/JSP/Footer.jsp" />
</html>