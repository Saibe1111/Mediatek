package services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mediatek2021.Document;
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
		
		/*int numeroPage;
		
		if(request.getParameter( "page" ) == null) {
			numeroPage = 1;
		}else {
			numeroPage = Integer.parseInt(request.getParameter( "page" ));
		}
		
		
		System.out.println(numeroPage);*/
		
		ArrayList<Document> list = new ArrayList<Document>(); 
		
		Document test = new Document() {
			
			@Override
			public Object[] data() {
				Object[] document = new Object[5];
		        document[0] = "titre";
		        document[1] = "auteur";
		        document[2] = "codeBarre";
		        document[3] = "adulte";
		        document[4] = "type";
		        return document;
			}
		};
		
		for (int i = 0; i < 20; i++) {
			list.add(test);
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
		
        //Si on a pas cliqué on rentourne vers un simple http get
        doGet(request,response);
        
		
	}

}