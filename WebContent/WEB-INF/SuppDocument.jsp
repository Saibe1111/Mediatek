<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="Header.jsp" %> 

	<body>
		<h3  class="item-title">Supprimer un document</h3>
		<form class="item" method="post" action="ajouter-document">
			<label for='txtCodeBarre'>Code Barre :</label><br>
            <input id='txtCodeBarre' name='txtCodeBarre' type='text' value='' autofocus /> <br/><br>
            
            <button class="item-task" name='btnConnect' type='submit'>Supprimer</button>
        </form> 
		
	</body>
	<%@ include file="Footer.jsp" %> 
</html>