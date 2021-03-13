<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="mediatek2021.Document" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Accueil</title>
		<link rel="icon" href="favicon.ico" />
		<link href="styles/default.css" rel="stylesheet" type="text/css">
	</head>
	<body>
		<h1>Bonjour, ${prénom}</h1>
		<br>
		<a href="ajouter-document">Ajouter un doccument</a><br>
		<a href="supprimer-document">Supprimer un doccument</a>
		<form action="accueil" method="post">
			<input type="submit" name="btnDisconnect" value="Déconnexion" />
		</form>
		<hr>
		<form action="accueil" method="post">
			<select name="doc-select" id="doc-select">
				<option value="0">--Choisir  un type de document--</option>
			    <option value="1">Livre</option>
			    <option value="2">CD</option>
			    <option value="3">DVD</option>
			</select>
			<input type="submit" name="btnDocType" value="Changer" />
		</form>
		
		<table>
		    <thead>
		        <tr>
		        	<th>Type</th>
		            <th>Code barre</th>
		          	<th>Titre</th>
		          	<th>Auteur</th>
		        </tr>
		    </thead>
		    <tbody>
		    
				<% 
				ArrayList<Document> list = (ArrayList<Document>) request.getAttribute("Document");
				for (int j=0; j<list.size(); j++){ %>
					<tr>
						<td><%= list.get(j).data()[0] %></td>
				    	<td><a href="doccument?code-barre=<%= list.get(j).data()[3] %>"><%= list.get(j).data()[2] %></a></td>
				    	<td><%= list.get(j).data()[1] %></td>
				    	<td><%= list.get(j).data()[2] %></td>
				    </tr>
				 <% } %>
		 
		 
		    </tbody>
		</table>
		
		<hr>
		
		
	</body>
</html>