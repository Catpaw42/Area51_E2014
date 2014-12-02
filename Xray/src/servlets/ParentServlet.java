package servlets;

import helperClasses.Const;
import helperClasses.Validator;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.dao.RekvisitionDao;
import database.dao.mysql.RekvisitionDaoImpl;
import database.dto.Bruger;
import database.dto.Modalitet;
import database.dto.RekvisitionExtended;
import database.dto.RekvisitionExtended.Status;
import database.interfaces.IDatabaseController;

/**
 * Servlet implementation class ParentServlet
 */
@WebServlet("/ParentServlet")
public class ParentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ParentServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}
	protected void initSession(HttpServletRequest request, Status[] defaultTableStatus, IDatabaseController databaseController) throws ServletException, IOException{
		request.setCharacterEncoding("UTF-8");
		createSearchDropdowns(request, databaseController);
		setDefaultTable(request, defaultTableStatus);


	}

	/**
	 * have to be called so the dropdowns in rekvisitionPage are filled
	 * @param request
	 * @param databaseController 
	 */
	private void createSearchDropdowns(HttpServletRequest request, IDatabaseController databaseController){
		Modalitet[] modality  = databaseController.getModalitetDao().findDynamic(null, 0, -1, new Object[]{});
		Modalitet[] allModality = new Modalitet[modality.length+1];
		Modalitet all = new Modalitet();
		all.setModalitetNavn("alle");
		all.setModalitetId(0); // the value to be returned if "alle" is chosen
		allModality[0] = all;
		for(int i = 0; i < modality.length; i++){
			allModality[i+1] = modality[i];
		}
		request.getSession().setAttribute(Const.MODALITY_LIST, allModality);
		request.getSession().setAttribute(Const.STATUS_LIST, Status.values());
	}

	protected void searchRekvisition(HttpServletRequest request, IDatabaseController databaseController) throws ServletException, IOException{

		// parameters
		String cpr = request.getParameter(Const.PARAM_CPR);
		String name = request.getParameter(Const.PARAM_NAME);
		String modality = request.getParameter(Const.PARAM_MODALITY);
		String department = request.getParameter(Const.PARAM_DEPARTMENT);
		department = (department.equals("-1") ? null : department);
		String fromDate = request.getParameter(Const.PARAM_FROM_DATE).replace(" ", "");
		String toDate = request.getParameter(Const.PARAM_TO_DATE).replace(" ", "");

		Timestamp fromDateTimestamp = null;
		Timestamp toDateTimestamp = null;
		fromDateTimestamp = (Validator.validateDate(fromDate) ? Validator.stringToTimestamp(fromDate) : null);
		toDateTimestamp = (Validator.validateDate(toDate) ? Validator.stringToTimestamp(toDate) : null);
		String status = request.getParameter(Const.PARAM_STATUS);


		Status statusObj = null;

		for(int i = 0; i < Status.values().length; i++){
			if(status.equalsIgnoreCase(Status.values()[i].name())){
				statusObj = Status.values()[i];
			}
		}


		// objekter
		RekvisitionExtended[] rekvDto;
		//TODO dao'er. skal ændres til almindeligt interface senere
		RekvisitionDao rekvDao = databaseController.getRekvisitionDao();
		rekvDto = rekvDao.findByAdvSearch(cpr, name, modality, statusObj, fromDateTimestamp, toDateTimestamp, department);

		request.getSession().setAttribute(Const.REKVISITION_LIST, rekvDto);
	}
	/**
	 * 
	 * @param request
	 * @param status if set to null the default table will not have any elements. If you want all elements use empty array
	 * @throws ServletException
	 * @throws IOException
	 */
	private void setDefaultTable(HttpServletRequest request, Status[] status) throws ServletException, IOException{
		IDatabaseController databaseController =(IDatabaseController) request.getSession().getAttribute(Const.DATABASE_CONTROLLER);
		Bruger activeUser = (Bruger) request.getSession().getAttribute(Const.ACTIVE_USER);
		//Rekvisition list to show user.
		RekvisitionExtended[][] rekvlist = (status == null ? null : new RekvisitionExtended[(status.length != 0 ? status.length : 1)][]);
		RekvisitionExtended[] rekvlistFull = null;
		// gets list of the active user - default behavior
		if(activeUser != null && rekvlist != null){
			for(int i = 0; i < rekvlist.length; i++){
				rekvlist[i] = databaseController.getRekvisitionDao().findByAdvSearch(null, null, null, status != null ? status[i] : null, null, null, null);
			}

		}
		int size = 0;
		// finding size of all list together
		if(rekvlist != null){
			for(int i = 0; i < rekvlist.length; i++){
				if(rekvlist[i] != null){
					size = size + rekvlist[i].length;
				}
			}

			// using size to create single list of rekvisitions
			rekvlistFull = new RekvisitionExtended[size];
			int fullListIndex = 0;
			for(int j = 0; j < rekvlist.length; j++){
				for(int i = 0; i< rekvlist[j].length; i++){
					rekvlistFull[fullListIndex] = rekvlist[j][i]; // add all lists to one list
					fullListIndex++;
				}
			}



		}


		//Stitch rekvisition[] to request object.
		if(rekvlistFull != null && rekvlistFull.length != 0){
			request.getSession().setAttribute(Const.REKVISITION_LIST, rekvlistFull);	
		}
		else{
			request.getSession().setAttribute(Const.REKVISITION_LIST, null);
		}
	}

}
