package services.utils;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Vérification {

	
	public static Boolean estConnecté(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//On récupère la session
		HttpSession session = request.getSession( true );
				
		//On récupère la variable de session estConnecté si elle existe
		Boolean estConnecte = null;
		estConnecte = (Boolean) session.getAttribute("estConnecté");
		
		//System.out.println("Variable estConnecte: " + estConnecte);
		
		if(estConnecte != null) {
			return true;
		}else {
			//Si elle existe pas on redirige vers le login
			response.sendRedirect("login");
			return false;
		}
		
		
	}


}
