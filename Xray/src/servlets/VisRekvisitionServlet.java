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

import database.dto.Bruger;
import database.dto.RekvisitionExtended;
import database.dto.RekvisitionExtended.Status;
import database.interfaces.IDatabaseController;


/**
 * Servlet implementation class VisRekvisitionServlet
 */
@WebServlet("/VisRekvisitionServlet")
public class VisRekvisitionServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public VisRekvisitionServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		IDatabaseController databaseController =(IDatabaseController) request.getSession().getAttribute(Const.DATABASE_CONTROLLER);

		Bruger activeUser = (Bruger) request.getSession().getAttribute(Const.ACTIVE_USER);
		// forwards to mainServlet with LoginPage as parameter
		if(activeUser == null){ 
			response.sendRedirect(Const.MAIN_SERVLET + "?page=" + Const.LOGIN_PAGE);
		}else{
			int rekvisitionId = -1;
			try{
				rekvisitionId = Integer.valueOf(request.getParameter("rekvisition_Id"));
				RekvisitionExtended rek = databaseController.getRekvisitionDao().findByPrimaryKey(rekvisitionId);
				request.getSession().setAttribute(Const.REKVISITION_SELECTED, rek);
				request.getRequestDispatcher(Const.SHOW_REKVISITION_PAGE).forward(request, response);
			} catch(Exception e){
				System.err.println("Rekvisition id not found");
			}
		}
		

	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		
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
		request.getRequestDispatcher(Const.REKVISITION_PAGE).forward(request, response);

	}
	
	private void setDefaultTable(Bruger activeUser, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		IDatabaseController databaseController =(IDatabaseController) request.getSession().getAttribute(Const.DATABASE_CONTROLLER);
				//Rekvisition list to show user.
				RekvisitionExtended[] rekvlist = null;
				// gets list of the active user - default behavior
				if(activeUser != null){
					rekvlist = databaseController.getRekvisitionDao().findByAdvSearch(null, null, null, null, null, null, activeUser.getBrugerId());
//				rekvlist = rekvisitionDao.findDynamic(Const.REKVIRENT_ID_COND, 0, -1, activeUser.getBrugerId());
				}
				//Stitch rekvisition[] to request object.
				request.getSession().setAttribute(Const.REKVISITION_LIST, rekvlist);	
				request.getRequestDispatcher(Const.REKVISITION_PAGE).forward(request, response);
	}




}
