<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="Header.jsp" %> 
	<body>
		<h3  class="item-title">Ajouter un document</h3>
		<form class="item" method="post" action="ajouter-document">
			<label for="doc-select">Choisir  un type de document:</label><br>
			<select name="doc-select" id="doc-select">
			    <option value="0">--Choisir  un type de document--</option>
			    <option value="1">Livre</option>
			    <option value="2">CD</option>
			    <option value="3">DVD</option>
			</select><br><br>
			<label for='txtCodeBarre'>Code Barre :</label><br>
            <input id='txtCodeBarre' name='txtCodeBarre' type='number' value='' autofocus /> <br/><br>
            <label for='txtAuteur'>Auteur :</label><br>
            <input id='txtAuteur' name='txtAuteur' type='text' value='' autofocus /> <br/><br>
            <label for='txtTitre'>Titre :</label><br>
            <input id='txtTitre' name='txtTitre' type='text' value='' /> <br/><br>
            <input type="checkbox" value="true" id="checkboxAdulte" name="checkboxAdulte">
			<label for="scales">Adulte uniquement (Film seulement)</label>
            <br/><br>
            <button class="item-task" name='btnConnect' type='submit'>Ajouter</button>
        </form> 
		
	</body>
	<%@ include file="Footer.jsp" %> 
</html>