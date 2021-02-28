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
 * Servlet implementation class Login
 */
@WebServlet("/login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String login = request.getParameter( "txtLogin" );
        if ( login == null ) login = "";
		
		request.getRequestDispatcher( "/Login.jsp" ).forward( request, response );
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		 String login = request.getParameter( "txtLogin" );
	     String password = request.getParameter( "txtPassword" );

	     HttpSession session = request.getSession( true );
	     session.setAttribute( "login", login );
	     
	     Mediatek pm = Mediatek.getInstance();
	     Utilisateur u = pm.getUser(login, password);
	     
	     if(u == null) {
	    	 System.out.println("Utilisateur inconnu :/");
	     }else {
	    	 System.out.println("Utilisataeur : " + u.login());
	     }
	     
		request.getRequestDispatcher( "/Login.jsp" ).forward( request, response );
	}

}
