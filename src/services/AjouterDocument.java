package services;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mediatek2021.Mediatek;
import mediatek2021.NewDocException;

/**
 * Servlet implementation class AjouterDocument
 */
@WebServlet("/ajouter-document")
public class AjouterDocument extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher( "/WEB-INF/AjoutDocument.jsp" ).forward( request, response );
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String type = request.getParameter( "doc-select" );
	    String codeBarre = request.getParameter( "txtCodeBarre" );
	    String titre = request.getParameter( "txtTitre" );
	    String auteur = request.getParameter( "txtAuteur" );
	    
	    Object[] document = new Object[3];
	    document[0] = titre;
	    document[1] = auteur;
	    document[2] = codeBarre;
	    
	    
	     Mediatek pm = Mediatek.getInstance();
	     
	     try {
			pm.newDocument(Integer.parseInt(type), document);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (NewDocException e) {
			e.printStackTrace();
		}
		
		
		doGet(request, response);
	}

}
