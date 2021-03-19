package services;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mediatek2021.Mediatek;
import mediatek2021.SuppressException;
import services.utils.Vérification;

/**
 * @version 1.0 - 13/02/2021
 * @author Sébastien CUVELLIER / Manil RICHARD
 * Gestion de l'ajout de document
 */
@WebServlet("/supprimer-document")
public class SupprimerDocument extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if (!Vérification.estConnecté(request, response)) {
			return;
		}
		request.getRequestDispatcher( "/WEB-INF/SuppDocument.jsp" ).forward( request, response );
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String codeBarre = request.getParameter( "txtCodeBarre" );
		
		Mediatek pm = Mediatek.getInstance();
		
		HttpSession session = request.getSession( true );
		
		try {
			
			if(codeBarre == "") {
				pm.suppressDoc(0);
			}
			pm.suppressDoc(Integer.parseInt(codeBarre));
			
		} catch (SuppressException e) {
			//On créer un variable session si on n'arrive pas à en récupérer une.
			session.setAttribute("erreur",e.getMessage());
			doGet(request, response);
			return;
		}
		
		session.setAttribute("succès","Le document à bien été supprimé");
	    response.sendRedirect("accueil");
		
		//doGet(request, response);
	}

}
