<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="mediatek2021.Document" %>

<%@ include file="Header.jsp" %> 
	
	<body>
		<h3 class="item-title">Bonjour, ${prénom}</h3>
		
		<form class="item" action="accueil" method="post">
			<select name="doc-select" id="doc-select">
				<option value="0">--Choisir  un type de document--</option>
			    <option value="1">Livre</option>
			    <option value="2">CD</option>
			    <option value="3">DVD</option>
			</select><br><br>
			<input class="item-task" type="submit" name="btnDocType" value="Changer" />
		</form>
		<div class="item">
		<table>
		    <thead>
		        <tr >
		        	<th  class="type">Type</th>
		            <th class="code-barre">Code barre</th>
		          	<th class="titre">Titre</th>
		          	<th class="auteur">Auteur</th>
		        </tr>
		    </thead>
		    <tbody>
		    
				<% 
				ArrayList<Document> list = (ArrayList<Document>) request.getAttribute("Document");
				for (int j=0; j<list.size(); j++){ %>
					<tr>
						<td class="type"><%= list.get(j).data()[0] %></td>
				    	<td class="code-barre"><a href="document?code-barre=<%= list.get(j).data()[3] %>"><%= list.get(j).data()[3] %></a></td>
				    	<td class="titre"><%= list.get(j).data()[1] %></td>
				    	<td class="auteur"><%= list.get(j).data()[2] %></td>
				    </tr>
				 <% } %>
		 
		 
		    </tbody>
		</table>
		</div>
		
	</body>
	
	<%@ include file="Footer.jsp" %> 
</html>