package services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mediatek2021.Document;
import mediatek2021.Mediatek;
import services.utils.Vérification;

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
		
		if (!Vérification.estConnecté(request, response)) { 
			return;
		}
		
		int type = Integer.parseInt(request.getParameter( "doc-select" ) );
		List<Document> list = new ArrayList<Document>(); 
		Mediatek pm = Mediatek.getInstance();
		
		if ( type == 0) {
			list.addAll(pm.catalogue(1));
			list.addAll(pm.catalogue(2));
			list.addAll(pm.catalogue(3));
		}else {
			list = pm.catalogue(type);
		}
		
		request.setAttribute( "Document", list );
		
		request.getRequestDispatcher( "/WEB-INF/Accueil.jsp" ).forward( request, response );
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if (!Vérification.estConnecté(request, response)) {
			return;
		}
		
		HttpSession session = request.getSession( true );
		
		if (request.getParameter("btnDisconnect") != null) {
			//On détruit la session
			session.invalidate();
			//On renvoie vers le login
			response.sendRedirect("login");
			return;
        }
		
        //Si on a pas cliqué on rentourne vers un simple http get
        doGet(request,response);
        
		
	}

}