package servlets;

import helperClasses.Const;

import java.io.IOException;
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

/**@author Morten, Rúni
 * Servlet implementation class VisitationServlet
 */
@WebServlet("/VisitationServlet")
public class VisitationServlet extends ParentServlet {
	private static final long serialVersionUID = 1L;

	// used for the search boxes in rekvisitionPage.jsp
	public Modalitet[] modList = null;
	public Status[] statusList = null;		
	////////////////////////////////////////////////////	

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public VisitationServlet() {
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
			initSession(request, new Status[]{Status.PENDING, Status.APPROVED}, databaseController);
			request.getRequestDispatcher(Const.VISITATION_PAGE).forward(request, response);
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		IDatabaseController databaseController =(IDatabaseController) request.getSession().getAttribute(Const.DATABASE_CONTROLLER);
		String visiterAction = request.getParameter("visiterAction");
		if(databaseController == null || request.getSession().getAttribute(Const.ACTIVE_USER) == null){
			response.sendRedirect(Const.MAIN_SERVLET + "?page=" + Const.LOGIN_SERVLET);
		}else{
			initSession(request, null, databaseController);
			if("Godkend".equals(visiterAction)){
				approveRekvisition(request);
			}else if("Afvis".equals(visiterAction)){
				declineRekvisition(request);
			}else{
				searchRekvisition(request, databaseController);
			}
			request.getRequestDispatcher(Const.VISITATION_PAGE).forward(request, response);
		}

	}





	private void declineRekvisition(HttpServletRequest request){
		IDatabaseController databaseController =(IDatabaseController) request.getSession().getAttribute(Const.DATABASE_CONTROLLER);
		int id=Integer.valueOf(request.getParameter("rekIDSubmit"));
		Bruger activeBruger = (Bruger) request.getSession().getAttribute(Const.ACTIVE_USER);

		RekvisitionExtended rek = databaseController.getRekvisitionDao().findByPrimaryKey(id);
		rek.setStatus(Status.DECLINED);
		rek.setVisitatorId(activeBruger.getBrugerId());
		System.out.println("id hey: " + activeBruger.getBrugerId());
		rek.setVisitator(activeBruger); // is not necessary at the moment
		rek.setVisitatorBemaerkning(request.getParameter("bemaerkninger").toString());
		try {
			System.out.println("decline");
			boolean success = databaseController.getRekvisitionDao().update(id, rek);
			System.out.println("it rek successfully: " + success);
		} catch (DaoException e) {
			e.printStackTrace();
		}
	}

	private void approveRekvisition(HttpServletRequest request){
		IDatabaseController databaseController =(IDatabaseController) request.getSession().getAttribute(Const.DATABASE_CONTROLLER);
		// gets selected rekvisition id
		int id=Integer.valueOf(request.getParameter("rekIDSubmit"));
		Bruger activeBruger = (Bruger) request.getSession().getAttribute(Const.ACTIVE_USER);

		RekvisitionExtended rek = databaseController.getRekvisitionDao().findByPrimaryKey(id);
		rek.setStatus(Status.APPROVED);
		rek.setVisitatorId(activeBruger.getBrugerId());
		System.out.println("id hey: " + activeBruger.getBrugerId());
		rek.setVisitator(activeBruger); // is not necessary at the moment
		rek.setVisitatorPrioritering(request.getParameter("prioritet").toString());
		rek.setVisitatorBemaerkning(request.getParameter("bemaerkninger").toString());
		try {
			System.out.println("approve");
			boolean success = databaseController.getRekvisitionDao().update(id, rek);
			System.out.println("it rek successfully: " + success);
		} catch (DaoException e) {
			e.printStackTrace();
		}
	}

}
