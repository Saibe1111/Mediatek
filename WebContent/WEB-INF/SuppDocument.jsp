<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Mediatek</title>
		<link rel="icon" href="favicon.ico" />
		<link href="styles/default.css" rel="stylesheet" type="text/css">
	</head>
	<body>
		<h1>Mediatek</h1>
		<h3>Supprimer un document</h3>
		<form method="post" action="ajouter-document">
			<label for='txtCodeBarre'>Code Barre :</label>
            <input id='txtCodeBarre' name='txtCodeBarre' type='text' value='' autofocus /> <br/>
            
            <button name='btnConnect' type='submit'>Supprimer</button>
        </form> 
		
	</body>
</html>