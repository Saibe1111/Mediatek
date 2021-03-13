<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Mediatek</title>
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