package servlets;
import helperClasses.Const;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.dto.Bruger;
import database.dto.Rettigheder;
import database.dto.Rettigheder.Rettighed;

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
			response.sendRedirect(Const.LOGIN_SERVLET);
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
		String page = request.getParameter("page");
		
		if(page.equals("loggingIn")){
			Bruger activeUser = (Bruger) request.getSession().getAttribute(Const.ACTIVE_USER);
			Rettigheder[] rettigheder = activeUser.getRettigheder();
			int prioritet = 0;
			for (Rettigheder r : rettigheder) {
				if(r.getRettighed().equals(Rettighed.ASSESSOR) && prioritet < 3){
					page=Const.VISITATION_SERVLET;
					prioritet=3;
				}
				if(r.getRettighed().equals(Rettighed.REQUEST) && prioritet < 3){
					page=Const.REKVISITION_SERVLET;
					prioritet=2;
				}
				if(r.getRettighed().equals(Rettighed.BOOKING) && prioritet < 2){
					page=Const.BOOKING_SERVLET;
					prioritet=1;
				}
				if(r.getRettighed().equals(Rettighed.ADMIN) && prioritet < 1){
					page=Const.ADMIN_SERVLET;
					prioritet=0;
				}
			}
		}
		switch (page) {
		case Const.LOGIN_SERVLET:
			forward("/LoginServlet",request,response);
			break;
		case Const.REKVISITION_SERVLET:
			forward("/RekvisitionServlet",request,response);
			break;
		case Const.VISITATION_SERVLET:
			forward("/VisitationServlet", request, response);
			break;
		case Const.BOOKING_SERVLET:
			forward("/BookingServlet", request, response);
			break;
		case Const.ADMIN_SERVLET:
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
