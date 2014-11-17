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
		String primaryPage = request.getParameter("page");
		
		if(primaryPage.equals("loggingIn")){
			Bruger activeUser = (Bruger) request.getSession().getAttribute(Const.ACTIVE_USER);
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
		switch (primaryPage) {
		case Const.LOGIN_SERVLET:
			forward("/" + Const.LOGIN_SERVLET,request,response);
			break;
		case Const.REKVISITION_SERVLET:
			forward("/" + Const.REKVISITION_SERVLET,request,response);
			break;
		case Const.VISITATION_SERVLET:
			forward("/" + Const.VISITATION_SERVLET, request, response);
			break;
		case Const.BOOKING_SERVLET:
			forward("/" + Const.BOOKING_SERVLET, request, response);
			break;
		case Const.ADMIN_SERVLET:
			forward("/" + Const.ADMIN_SERVLET, request, response);
			break;
		case Const.CT_KONTROLSKEMA_SERVLET:
			response.sendRedirect(Const.CT_KONTROLSKEMA_SERVLET);;
//			forward("/" + Const.CT_KONTROLSKEMA_SERVLET, request, response);
			break;
		case Const.PET_CT_KONTROLSKEMA_SERVLET:
			response.sendRedirect(Const.PET_CT_KONTROLSKEMA_SERVLET);
//			forward("/"+Const.PET_CT_KONTROLSKEMA_SERVLET, request, response);
			break;
		case Const.MR_KONTROLSKEMA_SERVLET:
			response.sendRedirect(Const.MR_KONTROLSKEMA_SERVLET);
//			forward("/"+Const.MR_KONTROLSKEMA_SERVLET, request, response);
			break;
		case Const.UL_INV_KONTROLSKEMA_SERVLET:
			response.sendRedirect(Const.UL_INV_KONTROLSKEMA_SERVLET);
//			forward("/"+Const.UL_INV_KONTROLSKEMA_SERVLET, request, response);
			break;
		default:
			forward("/"+Const.LOGIN_SERVLET, request, response);
			break;
		}		
	}
	//Utility method to forward
	private void forward(String string, HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher(string).forward(request, response);
		
	}


}
