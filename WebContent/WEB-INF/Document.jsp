<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.HashMap" %>
<%@ page import="mediatek2021.Document" %>

<%@ include file="Header.jsp" %> 
	
	<body>
		<h3 class="item-title"><%= request.getParameter( "code-barre" ) %></h3>
		<div class="item">
		
			<a>Code barre: <%= request.getParameter( "codeBarre" ) %></a> <br>
			<a>Type: <%= request.getParameter( "type" ) %></a><br>
			<a>Titre: <%= request.getParameter( "titre" ) %></a><br>
			<a>Auteur: <%= request.getParameter( "auteur" ) %></a><br>
			<a>Status: <%= request.getParameter( "emprunt" ) %></a><br>
		</div>
		
		
	</body>
	
	<%@ include file="Footer.jsp" %> 
</html>