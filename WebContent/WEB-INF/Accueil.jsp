<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="mediatek2021.Document" %>
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
		
		<table>
		    <thead>
		        <tr>
		            <th>Code barre</th>
		          	<th>Auteur</th>
		          	<th>Titre</th>
		          <th>Type</th>
		        </tr>
		    </thead>
		    <tbody>
		    
				<% 
				ArrayList<Document> list = (ArrayList<Document>) request.getAttribute("Document");
				for (int j=0; j<list.size(); j++){ %>
					<tr>
				    	<td><a href="doccument/<%= list.get(j).data()[2] %>"><%= list.get(j).data()[2] %></a></td>
				    	<td><%= list.get(j).data()[1] %></td>
				    	<td><%= list.get(j).data()[0] %></td>
				    	<td><%= list.get(j).data()[4] %></td>
				    </tr>
				 <% } %>
		 
		 
		    </tbody>
		</table>
		
		<hr>
		<form action="accueil" method="post">
			<input type="submit" name="btnDisconnect" value="Déconnexion" />
		</form>
		
	</body>
</html>