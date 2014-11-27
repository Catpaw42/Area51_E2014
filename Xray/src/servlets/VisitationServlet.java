package servlets;

import helperClasses.Const;
import helperClasses.Validator;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;

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

/**
 * Servlet implementation class VisitationServlet
 */
@WebServlet("/VisitationServlet")
public class VisitationServlet extends HttpServlet {
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
		request.setCharacterEncoding("UTF-8");
		createSearchDropdowns(request);
		Bruger activeUser = (Bruger) request.getSession().getAttribute(Const.ACTIVE_USER);
		// forwards to mainServlet with LoginPage as parameter
		if(activeUser == null){ 
			response.sendRedirect(Const.MAIN_SERVLET + "?page=" + Const.LOGIN_PAGE);
		}else{
			setDefaultTable(activeUser, request, response);
			request.getRequestDispatcher(Const.VISITATION_PAGE).forward(request, response);
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String visiterAction = request.getParameter("visiterAction");
		int id=Integer.valueOf(request.getParameter("rekIDSubmit"));
		System.out.println("post: action: " + visiterAction + " id: " + id);
		
		
		if("Godkend".equals(visiterAction)){
			approveRekvisition(request);
			setDefaultTable((Bruger)request.getSession().getAttribute(Const.ACTIVE_USER), request, response);
			request.getRequestDispatcher(Const.VISITATION_PAGE).forward(request, response);
//			response.sendRedirect(Const.VISITATION_PAGE);
		}
		else if("Afvis".equals(visiterAction)){
			declineRekvisition(request);
			setDefaultTable((Bruger)request.getSession().getAttribute(Const.ACTIVE_USER), request, response);
			request.getRequestDispatcher(Const.VISITATION_PAGE).forward(request, response);
		}else{
			searchRekvisition(request, response);
		}


	}

	private void setDefaultTable(Bruger activeUser, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		IDatabaseController databaseController =(IDatabaseController) request.getSession().getAttribute(Const.DATABASE_CONTROLLER);
		//Rekvisition list to show user.
		RekvisitionExtended[] rekvlist = null;
		// gets list of the active user - default behavior
		if(activeUser != null){
			rekvlist = databaseController.getRekvisitionDao().findByAdvSearch(null, null, null, Status.PENDING, null, null);
		}
		//Stitch rekvisition[] to request object.
		request.getSession().setAttribute(Const.REKVISITION_LIST, rekvlist);	
	}

	/**
	 * have to be called so the dropdowns in rekvisitionPage are filled
	 * @param request
	 */
	private void createSearchDropdowns(HttpServletRequest request){
		IDatabaseController databaseController =(IDatabaseController) request.getSession().getAttribute(Const.DATABASE_CONTROLLER);
		request.getSession().setAttribute(Const.MODALITY_LIST, databaseController.getModalitetDao().findDynamic(null, 0, -1, new Object[]{}));
		request.getSession().setAttribute(Const.STATUS_LIST, Status.values());
	}

	private void searchRekvisition(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

		IDatabaseController databaseController =(IDatabaseController) request.getSession().getAttribute(Const.DATABASE_CONTROLLER);
		// parameters
		String cpr = request.getParameter(Const.PARAM_CPR);
		String name = request.getParameter(Const.PARAM_NAME);
		String modality = request.getParameter(Const.PARAM_MODALITY);
		String department = request.getParameter(Const.PARAM_DEPARTMENT);
		department = (department.equals("-1") ? null : department);
		String date = request.getParameter(Const.PARAM_DATE).replace(" ", "");

		Timestamp timestamp = null;
		timestamp = (Validator.validateDate(date) ? Validator.stringToTimestamp(date) : null);

		String status = request.getParameter(Const.PARAM_STATUS);


		Status statusObj = null;

		for(int i = 0; i < Status.values().length; i++){
			if(status.equalsIgnoreCase(Status.values()[i].name())){
				statusObj = Status.values()[i];
				System.out.println("Selected status in search: " + statusObj.name());
			}
		}

		System.out.println("############SEARCH###############");
		System.out.println("cpr: " + cpr);
		System.out.println("name: " + name);
		System.out.println("modality: " + modality);
		System.out.println("department: " + department);
		System.out.println("date: " + timestamp);
		System.out.println("status: " + status);

		// objekter
		RekvisitionExtended[] rekvDto;
		//TODO dao'er. skal ændres til almindeligt interface senere
		Date d1 = new Date();
		rekvDto = databaseController.getRekvisitionDao().findByAdvSearch(cpr, name, modality, statusObj, timestamp, department);
		Date d2 = new Date();

		System.out.println("##found " + (rekvDto != null ? rekvDto.length : 0) + " rekvisitions");
		System.out.println("in " + (d2.getTime()- d1.getTime()) + " ms");
		System.out.println("##################################");

		request.getSession().setAttribute(Const.REKVISITION_LIST, rekvDto);
		request.getRequestDispatcher(Const.VISITATION_PAGE).forward(request, response);

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
