package services;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @version 1.0 - 28/02/2021
 * @author Sébastien CUVELLIER / Manil RICHARD
 * Gestion de l'accueil et de la destruction de la session
 */
@WebServlet("/accueil")
public class Accueil extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//On récupère la session
		HttpSession session = request.getSession( true );
		
		//On récupère la variable de session estConnecté si elle existe
		Boolean estConnecté = null;
		estConnecté = (Boolean) session.getAttribute("estConnecté");
		
		if(estConnecté == null) {
			//Si elle existe pas on redirige vers le login
			response.sendRedirect("login");
		}else {
			//si elle existe on rend la page accueil
			request.getRequestDispatcher( "/WEB-INF/Accueil.jsp" ).forward( request, response );
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//On récupère la session
		HttpSession session = request.getSession( true );
		//Si on a cliqué sur le bouton 
		if (request.getParameter("btnDisconnect") != null) {
			//On détruit la session
			session.invalidate();
			//On renvoie vers le login
			response.sendRedirect("login");
        }else {
        	//Si on a pas cliqué on rentourne vers un simple http get
        	doGet(request,response);
        }
		
	}

}