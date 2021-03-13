<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="Header.jsp" %> 

	<body>
		<h2 class="item-title">Connexion</h2>
		
		<form class="item"  method="post" action="login">
            <label for='txtLogin'>Login :</label>
            <input id='txtLogin' name='txtLogin' type='text' value='${login}' autofocus /> <br/>
            <label for='txtPassword'>Password :</label>
            <input name='txtPassword' type='password' value='' /> <br/>
            <br/>
            <button name='btnConnect' type='submit'>Connexion</button>
        </form> 
		
	</body>
	<%@ include file="Footer.jsp" %> 
</html>