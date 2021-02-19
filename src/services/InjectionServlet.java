package services;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

/**
 * Servlet implementation class InjectionServlet
 */
@WebServlet(value="/initializeResources", loadOnStartup=1)
public class InjectionServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
       	
	@Override
	public void init() throws ServletException {
		super.init();
		try {
			Class.forName("persistantdata.MediatekData");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

}
