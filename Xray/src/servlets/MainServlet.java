package servlets;
import helperClasses.Const;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.DatabaseController;
import database.dto.Bruger;
import database.dto.Rettigheder;
import database.dto.Rettigheder.Rettighed;
import database.interfaces.IDatabaseController;

/**@author Christian, Rúni
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
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		processReq(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		processReq(request, response);
	}

	private void processReq(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		IDatabaseController databaseController =(IDatabaseController) request.getSession().getAttribute(Const.DATABASE_CONTROLLER);
		if(databaseController == null){
			request.getSession().setAttribute(Const.DATABASE_CONTROLLER, new DatabaseController());
		}
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
		boolean logout = checkAction(request, response);
		String primaryPage = request.getParameter("page");
		if (primaryPage==null)primaryPage="";
		//Check if user is logging in
		
		if(primaryPage.equals("loggingIn")){
			Bruger activeUser = (Bruger) request.getSession().getAttribute(Const.ACTIVE_USER);
			//Getting users permissions and redirecting to relevant page.
			Rettigheder[] rettigheder = activeUser.getRettigheder();
			int prioritet = 0;
			for (Rettigheder r : rettigheder) {
				if(r.getRettighed().equals(Rettighed.ASSESSOR) && prioritet < 3){
					primaryPage=Const.VISITATION_SERVLET;
					prioritet=3;
				}
				if(r.getRettighed().equals(Rettighed.REQUEST) && prioritet < 3){
					primaryPage=Const.REKVISITION_SERVLET;
					prioritet=2;
				}
				if(r.getRettighed().equals(Rettighed.BOOKING) && prioritet < 2){
					primaryPage=Const.BOOKING_SERVLET;
					prioritet=1;
				}
				if(r.getRettighed().equals(Rettighed.ADMIN) && prioritet < 1){
					primaryPage=Const.ADMIN_SERVLET;
					prioritet=0;
				}
			}
		}
		System.out.println("primary page: " + primaryPage);
		
		if (logout || Const.LOGIN_SERVLET.equals(primaryPage) || Const.LOGIN_PAGE.equals(primaryPage)) {
			
			forward("/" + Const.LOGIN_SERVLET,request,response);
			
		} else if (Const.REKVISITION_SERVLET.equals(primaryPage) || Const.REKVISITION_PAGE.equals(primaryPage)) {
			
			request.getSession().setAttribute(Const.PAGEHEADING, Const.REKVISITION_TITLE);
			response.sendRedirect(Const.REKVISITION_SERVLET);
			
		} else if (Const.VISITATION_SERVLET.equals(primaryPage) || Const.VISITATION_PAGE.equals(primaryPage)) {
			
			request.getSession().setAttribute(Const.PAGEHEADING, Const.VISITATION_TITLE);
			response.sendRedirect(Const.VISITATION_SERVLET);
			
		} else if (Const.BOOKING_SERVLET.equals(primaryPage) || Const.BOOKING_PAGE.equals(primaryPage)) {
			
			request.getSession().setAttribute(Const.PAGEHEADING, Const.BOOKING_TITLE);
			response.sendRedirect(Const.BOOKING_SERVLET);
			
		} else if (Const.ADMIN_SERVLET.equals(primaryPage) || Const.ADMIN_PAGE.equals(primaryPage)) {
			
			request.getSession().setAttribute(Const.PAGEHEADING, Const.REKVISITION_SERVLET); // while admin is not implemented
//			request.getSession().setAttribute(Const.PAGEHEADING, Const.ADMINISTRER_TITLE); // TODO incomment when implementation finished
			forward("/" + Const.REKVISITION_SERVLET, request, response);
			
		} else {
			response.sendRedirect("/" + Const.LOGIN_SERVLET);
		}		
	}
	private boolean checkAction(HttpServletRequest request,HttpServletResponse response) throws IOException {
		String action = request.getParameter("action");
		if (action == null) action = "";
		if ("logout".equals(action)) {
			request.getSession().invalidate();
			return true;
		}
		return false;
		
	}

	//Utility method to forward
	private void forward(String string, HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher(string).forward(request, response);
		
	}


}
