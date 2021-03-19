<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.HashMap" %>
<%@ page import="mediatek2021.Document" %>

<%@ include file="Header.jsp" %> 
	
	<body>
		<h3 class="item-title">Mediatek</h3>
		<div class="item">
		
			<b>Code barre: </b> <%= request.getAttribute( "codeBarre" ) %> <br><br>
			<b>Type: </b> <%= request.getAttribute( "type" ) %><br><br>
			<b>Titre: </b> <%= request.getAttribute( "titre" ) %><br><br>
			<b>Auteur: </b> <%= request.getAttribute( "auteur" ) %><br><br>
			<b>Status: </b> <%= request.getAttribute( "emprunt" ) %><br><br>
			<% if( request.getAttribute( "type" ) == "DVD"){ %>
				<b>Adulte: </b> <%= request.getAttribute( "adulte" ) %><br><br>
			<% } %>
		</div>
		
		
	</body>
	
	<%@ include file="Footer.jsp" %> 
</html>