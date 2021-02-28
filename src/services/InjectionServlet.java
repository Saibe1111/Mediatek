package services;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

/**
 * @version 1.0 - 19/02/2021
 * @author Sébastien CUVELLIER / Manil RICHARD
 * Injection de dépendance
 */
@WebServlet(value="/initializeResources", loadOnStartup=1)
public class InjectionServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
    
	/**
     * Permet de faire l'injection de dépendance.
     * @see HttpServlet#init ()
     * @throws ServletException - Si il y a une erreur avec la servlet.
     */
	
	@Override
	public void init() throws ServletException {
		super.init();
		try {
			String nom = "persistantdata.MediatekData";
			Class.forName(nom);
			System.out.println("Injection de la dépendance: " + nom);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

}
