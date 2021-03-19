package services;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mediatek2021.Mediatek;
import mediatek2021.Utilisateur;

/**
 * @version 1.0 - 19/02/2021
 * @author Manil RICHARD / Sébastien CUVELLIER Login permet la gestion de la
 *         connexion d'un utilisateur
 */
@WebServlet("/login")
public class Login extends HttpServlet {

	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// On recupère le login d'un utilisateur qui se serai trompé de mot de passe
		String login = request.getParameter("txtLogin");
		if (login == null)
			login = "";
		// On lui renvoie la JSP
		request.getRequestDispatcher("/WEB-INF/Login.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// On récupère les paramètres de notre formulaire.
		String login = request.getParameter("txtLogin");
		String password = request.getParameter("txtPassword");

		// On créer un variable session si on n'arrive pas à en récupérer une.
		HttpSession session = request.getSession(true);
		session.setAttribute("login", login);
		// On récupère l'instance de Mediatek
		Mediatek pm = Mediatek.getInstance();

		// On récupère l'utilisateur avec le mot de passe et le login qu'on luit donne
		Utilisateur u = pm.getUser(login, password);
		// Si l'utilisateur est null, y a un erreur on renvoie le formulaire de
		// connexion
		if (u == null) {
			request.getRequestDispatcher("/WEB-INF/Login.jsp").forward(request, response);
			// Si l'utilisateur est =! null, on le redirige vers la page d'accueil
		} else {
			// On défini plusiers attributs
			session.setAttribute("prénom", u.data()[0]);
			session.setAttribute("estConnecté", true);
			// On redirige vers l'accueil du site
			response.sendRedirect("accueil");
		}

	}

}
