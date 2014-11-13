package servlets;
import helperClasses.Const;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Main Servlet - delegates responsibilities to relevant sub Servlet
 */
@WebServlet("/MainServlet")
//DefaultController - redirects to relevant controller - might be omitted
public class MainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor. 
	 */
	public MainServlet() {
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processReq(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processReq(request, response);
	}

	private void processReq(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//Checks whether user is logged in
		if (request.getSession().getAttribute(Const.ACTIVE_USER) != null){ 
			delegate(request, response);
		} else { //user not logged in
			response.sendRedirect("LoginServlet");
		}
	}

	/**
	 * Delegates responsibility for request to relevant Servlet.
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws ServletException
	 */
	private void delegate(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		switch ( request.getParameter("page")) {
		case "loggingIn":
			forward("/RekvisitionServlet",request,response);
			break;
		case "login":
			forward("/LoginServlet",request,response);
			break;
		case "rekvirer":
			forward("/RekvisitionServlet",request,response);
			break;
		case "visiter":
			forward("/VisitationServlet", request, response);
			break;
		case "book":
			forward("/BookingServlet", request, response);
			break;
		case "admin":
			forward("/AdminServlet",request,response);
			break;
		default:
			//If no corresponding Servlet is found tries to redirect to Servlet
			forward("/"+ request.getParameter("page"),request,response);
			break;
		}		
	}
	//Utility method to forward
	private void forward(String string, HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher(string).forward(request, response);
		
	}


}
