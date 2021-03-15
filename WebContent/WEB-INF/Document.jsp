<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="mediatek2021.Document" %>

<%@ include file="Header.jsp" %> 
	
	<body>
		<h3 class="item-title"><%= request.getParameter( "code-barre" ) %></h3>
		<div class="item">
		
			<a><%= request.getParameter( "code-barre" ) %></a>
			<a><%= request.getParameter( "code-test" ) %></a>
		</div>
		
		
	</body>
	
	<%@ include file="Footer.jsp" %> 
</html>