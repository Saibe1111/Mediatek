package services;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mediatek2021.Document;
import mediatek2021.Mediatek;
import services.utils.Vérification;

/**
 * @version 1.0 - 19/02/2021
 * @author Manil RICHARD / Sébastien CUVELLIER
 * Login permet la gestion de la connexion d'un utilisateur
 */
@WebServlet("/document")
public class DocumentServlet extends HttpServlet {
	
	
	private static final long serialVersionUID = 1L;


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if (!Vérification.estConnecté(request, response)) {
			return;
		}
		
		int codeBarre;
		
		try {
			codeBarre = Integer.parseInt(request.getParameter( "code-barre" ));
		} catch (NumberFormatException e) {
			response.sendRedirect("accueil");
			return;
		}
		
		
		Mediatek pm = Mediatek.getInstance();
		
		Document doc = (Document) pm.getDocument(codeBarre);
		
			request.setAttribute("type", doc.data()[0]);
			request.setAttribute("titre", doc.data()[1]);
			request.setAttribute("auteur", doc.data()[2]);
			request.setAttribute("codeBarre", doc.data()[3]);
			request.setAttribute("emprunt", doc.data()[4]);
			if ((boolean) doc.data()[4]) {
				request.setAttribute("emprunt", "Emprunté");
			}else {
				request.setAttribute("emprunt", "Disponible");
			}
			
			if (doc.data().length>5)  {
				if ((boolean) doc.data()[5]) {
					request.setAttribute("adulte", "Oui");
				}else {
					request.setAttribute("adulte", "Non");
				}
			}
		
		
		
		//On lui renvoie la JSP
		request.getRequestDispatcher( "/WEB-INF/Document.jsp" ).forward( request, response );
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 
		doGet(request, response);
	}

}
