package services;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mediatek2021.Mediatek;
import mediatek2021.NewDocException;
import services.utils.Vérification;

/**
 * @version 2.0 - 19/02/2021
 * @author Sébastien CUVELLIER / Manil RICHARD
 * Gestion de l'ajout de document
 */
@WebServlet("/ajouter-document")
public class AjouterDocument extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if (!Vérification.estConnecté(request, response)) {
			return;
		}
		request.getRequestDispatcher( "/WEB-INF/AjoutDocument.jsp" ).forward( request, response );
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if (!Vérification.estConnecté(request, response)) {
			return;
		}
		
		//On récupère les information du formulaire
		String type = request.getParameter( "doc-select" );
	    String codeBarre = request.getParameter( "txtCodeBarre" );
	    String titre = request.getParameter( "txtTitre" );
	    String auteur = request.getParameter( "txtAuteur" );
	    Boolean adulte = false;
	    
	    String valueCheckbox = request.getParameter( "checkboxAdulte" );
	    
	    adulte = Boolean.valueOf(valueCheckbox);

	    //On créer un objet
	    Object[] document = new Object[4];
	    document[0] = titre;
	    document[1] = auteur;
	    document[2] = codeBarre;
	    document[3] = adulte;
	    
	     Mediatek pm = Mediatek.getInstance();
	     
	   //On créer un variable session si on n'arrive pas à en récupérer une.
	     HttpSession session = request.getSession( true );
	     
	     if(type != "") {
		     try {
				pm.newDocument(Integer.parseInt(type), document);
			} catch (NewDocException e) {
				session.setAttribute("erreur",e.getMessage());
				doGet(request, response);
				return;
			} 
	     }
	     
	     session.setAttribute("succès","Le document à bien été ajouté");
	     response.sendRedirect("accueil");
		
		//doGet(request, response);
	}

}
