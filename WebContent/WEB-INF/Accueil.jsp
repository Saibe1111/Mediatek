<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Accueil</title>
	</head>
	<body>
		<h1>Bonjour, ${prénom}</h1>
		<br>
		<a href="ajouter-document">Ajouter un doccument</a><br>
		<a href="supprimer-document">Supprimer un doccument</a>
		<hr>
		<form action="accueil" method="post">
			<input type="submit" name="btnDisconnect" value="Déconnexion" />
		</form>
		
	</body>
</html>