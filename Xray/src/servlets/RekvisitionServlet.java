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

import database.dao.RekvisitionDao;
import database.dto.Bruger;
import database.dto.Modalitet;
import database.dto.RekvisitionExtended;
import database.dto.RekvisitionExtended.Status;
import database.interfaces.IDatabaseController;


/**@author Rúni
 * Servlet implementation class RekvisitionServlet
 */
@WebServlet("/RekvisitionServlet")
public class RekvisitionServlet extends ParentServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
			

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RekvisitionServlet() {
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
			
			if("cancel".equalsIgnoreCase(request.getParameter("action"))){
				int rekvisitionId = -1;
				try{
					rekvisitionId = Integer.valueOf(request.getParameter("cancelId"));
					RekvisitionExtended r = databaseController.getRekvisitionDao().findByPrimaryKey(rekvisitionId);
					r.setStatus(Status.CANCELED);
					try {
						System.out.println("rekvisition id: " + rekvisitionId);
						System.out.println("test id: " + r.getRekvisitionId());
						System.out.println("update rekvisition: " + r);
						databaseController.getRekvisitionDao().update(rekvisitionId, r);
					} catch (DaoException e) {
						System.err.println(e.getMessage());
						System.err.println("could not save changes to rekvisition");
					}
				} catch(NumberFormatException nfe){
					System.err.println("no id on cancel rekvisition");
				}

			}
			initSession(request, new Status[]{Status.PENDING, Status.APPROVED, Status.BOOKED}, databaseController);
			request.getRequestDispatcher(Const.REKVISITION_PAGE).forward(request, response);
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
			initSession(request, null, databaseController);
			searchRekvisition(request, databaseController);
			request.getRequestDispatcher(Const.REKVISITION_PAGE).forward(request, response);
		}
		
		
	}
	
	/**
	 * have to be called so the dropdowns in rekvisitionPage are filled
	 * @param request
	 * @param databaseController 
	 */
	
	
//	private void searchRekvisition(HttpServletRequest request, HttpServletResponse response, IDatabaseController databaseController) throws ServletException, IOException{
//		// parameters
//		String cpr = request.getParameter(Const.PARAM_CPR);
//		String name = request.getParameter(Const.PARAM_NAME);
//		String modality = request.getParameter(Const.PARAM_MODALITY);
//		String department = request.getParameter(Const.PARAM_DEPARTMENT);
//		department = (department.equals("-1") ? null : department);
//		String date = request.getParameter(Const.PARAM_DATE).replace(" ", "");
//		
//		Timestamp timestamp = null;
//		timestamp = (Validator.validateDate(date) ? Validator.stringToTimestamp(date) : null);
//	
//		String status = request.getParameter(Const.PARAM_STATUS);
//	
//
//		Status statusObj = null;
//
//		for(int i = 0; i < Status.values().length; i++){
//			if(status.equalsIgnoreCase(Status.values()[i].name())){
//				statusObj = Status.values()[i];
//				System.out.println("Selected status in search: " + statusObj.name());
//			}
//		}
//
//		System.out.println("############SEARCH###############");
//		System.out.println("cpr: " + cpr);
//		System.out.println("name: " + name);
//		System.out.println("modality: " + modality);
//		System.out.println("department: " + department);
//		System.out.println("date: " + timestamp);
//		System.out.println("status: " + status);
//		
//		// objekter
//		RekvisitionExtended[] rekvDto;
//		//TODO dao'er. skal ændres til almindeligt interface senere
//		Date d1 = new Date();
//
//		rekvDto = databaseController.getRekvisitionDao().findByAdvSearch(cpr, name, modality, statusObj, timestamp, department);
//		Date d2 = new Date();
//
//		System.out.println("##found " + (rekvDto != null ? rekvDto.length : 0) + " rekvisitions");
//		System.out.println("in " + (d2.getTime()- d1.getTime()) + " ms");
//		System.out.println("##################################");
//		
//		request.getSession().setAttribute(Const.REKVISITION_LIST, rekvDto);
//		request.getRequestDispatcher(Const.REKVISITION_PAGE).forward(request, response);
//
//	}
//	
//	private void setDefaultTable(HttpServletRequest request, HttpServletResponse response, Bruger activeUser, IDatabaseController databaseController) throws ServletException, IOException{
//				//Rekvisition list to show user.
//				RekvisitionExtended[] rekvlist = null;
//				// gets list of the active user - default behavior
//				if(activeUser != null){
//					rekvlist = databaseController.getRekvisitionDao().findByAdvSearch(null, null, null, null, null, null, activeUser.getBrugerId());
////				rekvlist = rekvisitionDao.findDynamic(Const.REKVIRENT_ID_COND, 0, -1, activeUser.getBrugerId());
//				}
//				//Stitch rekvisition[] to request object.
//				request.getSession().setAttribute(Const.REKVISITION_LIST, rekvlist);	
//				request.getRequestDispatcher(Const.REKVISITION_PAGE).forward(request, response);
//	}
	
	
	
	
	
	




}
