<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html lang="fr">
	<head>
		<meta charset="UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<title>Application IUT Bank 2023</title>
		<link rel="stylesheet" href="/_00_ASBank2023/style/login.css">
		<link href='https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css' rel='stylesheet'>
	</head>
	<body>
	<div class="wrapper">
		<h1>Bienvenue sur l'application IUT Bank 2023</h1>
		<div class="btn-login">
			<p>
				<s:url action="redirectionLogin" var="redirectionLogin" ></s:url>
				<s:a href="%{redirectionLogin}">Accéder à la banque</s:a>
			</p>
		</div>
	</div>
	</body>
</html>