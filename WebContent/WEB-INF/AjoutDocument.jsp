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
		<h3>Ajouter un document</h3>
		<form method="post" action="ajouter-document">
		
			<label for="doc-select">Choisir  un type de document:</label>
			<select name="doc-select" id="doc-select">
			    <option value="">--Choisir  un type de document--</option>
			    <option value="1">Livre</option>
			    <option value="2">CD</option>
			    <option value="3">DVD</option>
			</select><br>
			<label for='txtCodeBarre'>Code Barre :</label>
            <input id='txtCodeBarre' name='txtCodeBarre' type='text' value='' autofocus /> <br/>
            <label for='txtAuteur'>Auteur :</label>
            <input id='txtAuteur' name='txtAuteur' type='text' value='' autofocus /> <br/>
            <label for='txtTitre'>Titre :</label>
            <input id='txtTitre' name='txtTitre' type='text' value='' /> <br/>
            <input type="checkbox" id="checkboxAdulte" name="checkboxAdulte">
			<label for="scales">Adulte uniquement (Film seulement)</label>
            <br/>
            <button name='btnConnect' type='submit'>Ajouter</button>
        </form> 
		
	</body>
</html>