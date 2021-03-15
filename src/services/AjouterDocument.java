package services;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mediatek2021.Mediatek;
import services.utils.Vérification;

/**
 * @version 1.0 - 19/02/2021
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
	    
	    if (request.getParameter( "checkboxAdulte" ) == "on") {
	    	adulte = true;
	    }
        
	    //On créer un objet
	    Object[] document = new Object[5];
	    document[0] = type;
	    document[1] = titre;
	    document[2] = auteur;
	    document[3] = codeBarre;
	    document[4] = adulte;
	    
	     Mediatek pm = Mediatek.getInstance();
	     
	     if(type != "") {
		     try {
				pm.newDocument(Integer.parseInt(type), document);
			} catch (Exception e) {
				e.printStackTrace();
			} 
	     }
	     
		
		doGet(request, response);
	}

}
