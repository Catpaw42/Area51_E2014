package servlets;

import java.io.IOException;

import helperClasses.Const;



import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.spoledge.audao.db.dao.DaoException;

import database.dto.Bruger;
import database.dto.Modalitet;
import database.dto.RekvisitionExtended;
import database.dto.RekvisitionExtended.Status;
import database.interfaces.IDatabaseController;

/**@author Rúni
 * Servlet implementation class BookingServlet
 */
@WebServlet("/BookingServlet")
public class BookingServlet extends ParentServlet {
	private static final long serialVersionUID = 1L;

	// used for the search boxes in rekvisitionPage.jsp
	public Modalitet[] modList = null;
	public Status[] statusList = null;		
	////////////////////////////////////////////////////
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public BookingServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		IDatabaseController databaseController =(IDatabaseController) request.getSession().getAttribute(Const.DATABASE_CONTROLLER);


		Bruger activeUser = (Bruger) request.getSession().getAttribute(Const.ACTIVE_USER);
		// forwards to mainServlet with LoginPage as parameter
		if(activeUser == null || databaseController == null){ 
			response.sendRedirect(Const.MAIN_SERVLET + "?page=" + Const.LOGIN_PAGE);
		}else{
			initSession(request, new Status[]{Status.APPROVED, Status.BOOKED}, databaseController);
			String action = request.getParameter(Const.PARAM_ACTION);
			int rekvisitionId = -1;
			String id = request.getParameter(Const.BOOKING_ACTION_ID);
			if(id != null){
				// getting id of rekvisition wished to book or send back to visitation
				rekvisitionId = Integer.valueOf(id);
				// getting wanted rekvisition
				RekvisitionExtended r = databaseController.getRekvisitionDao().findByPrimaryKey(rekvisitionId);
				// if action is booking rekvisition
				if(Const.BOOKING_ACTION.equals(action)){
					r.setStatus(Status.BOOKED);
				}
				// if action is to send back og visitation
				else if(Const.REVISIT_ACTION.equals(action)){
					r.setStatus(Status.PENDING);

				}
				try {
					// trying to update in database
					databaseController.getRekvisitionDao().update(rekvisitionId, r);
				} catch (DaoException e) {
					System.err.println("could not save changes to rekvisition");
				}
			}
			request.getRequestDispatcher(Const.BOOKING_PAGE).forward(request, response);
		}


	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		IDatabaseController databaseController =(IDatabaseController) request.getSession().getAttribute(Const.DATABASE_CONTROLLER);
		Bruger activeUser = (Bruger) request.getSession().getAttribute(Const.ACTIVE_USER);


		if(activeUser == null || databaseController == null){
			response.sendRedirect(Const.MAIN_SERVLET + "?page=" + Const.LOGIN_PAGE);		
		}else{
			initSession(request, new Status[] {Status.APPROVED, Status.BOOKED}, databaseController);
			searchRekvisition(request, databaseController);
			request.getRequestDispatcher(Const.BOOKING_PAGE).forward(request, response);
		}


	}





	
}
