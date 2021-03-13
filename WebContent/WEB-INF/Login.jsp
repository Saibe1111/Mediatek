<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Connexion</title>
		<link rel="icon" href="favicon.ico" />
		<link href="styles/default.css" rel="stylesheet" type="text/css">
	</head>
	<body>
		<h1>Connexion à Mediatek</h1>
		
		<form method="post" action="login">
            <label for='txtLogin'>Login :</label>
            <input id='txtLogin' name='txtLogin' type='text' value='${login}' autofocus /> <br/>
            <label for='txtPassword'>Password :</label>
            <input name='txtPassword' type='password' value='' /> <br/>
            <br/>
            <button name='btnConnect' type='submit'>Connexion</button>
        </form> 
		
	</body>
</html>