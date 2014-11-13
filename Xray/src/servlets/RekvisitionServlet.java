package servlets;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



import database.DataSourceConnector;
import database.dao.ModalitetDao;
import database.dao.PatientDao;
import database.dao.mysql.BrugerDaoImplExtended;
import database.dao.mysql.ModalitetDaoImpl;
import database.dao.mysql.PatientDaoImpl;
import database.dao.mysql.RekvisitionDaoImplExt;
import database.dto.Bruger;
import database.dto.Modalitet;
import database.dto.Patient;
import database.dto.RekvisitionExtended.Status;
import database.dto.RekvisitionExtended;
import database.interfaces.IDataSourceConnector.ConnectionException;
import helperClasses.Const;
import helperClasses.Const.*;


/**
 * Servlet implementation class RekvisitionServlet
 */
@WebServlet("/RekvisitionServlet")
public class RekvisitionServlet extends HttpServlet {
	private java.sql.Connection conn = null;

	// used for the search boxes in rekvisitionPage.jsp
	public Modalitet[] modList = null;
	public Status[] statusList = null;		
	////////////////////////////////////////////////////	

	private BrugerDaoImplExtended  userDao;
	private RekvisitionDaoImplExt rekvisitionDao;
	private ModalitetDao modDao;
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RekvisitionServlet() {
		super();
		try {
			this.conn = DataSourceConnector.getConnection();
		} catch (ConnectionException e) {
			e.printStackTrace();
		}
		this.userDao = new BrugerDaoImplExtended(conn);
		this.rekvisitionDao = new RekvisitionDaoImplExt(conn);
		this.modDao = new ModalitetDaoImpl(conn);

		
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		createSearchDropdowns(request);
		//Strings for ?
		String condStatus = "status=?";
		String condRekvUserName = "bruger_navn=?";
		String condRekvirentId = "rekvirent_id=?";
		//Testing user.

		Bruger activeUser = (Bruger) request.getSession().getAttribute(Const.ACTIVE_USER);
		
		
		//Getting Dummy user
		if(activeUser == null){ // kun af test grunde
			response.sendRedirect(Const.LOGIN_PAGE);
			activeUser = userDao.findByPrimaryKey(1);//TODO remove or fix!
			request.getSession().setAttribute(Const.ACTIVE_USER, activeUser);
		}
		//Rekvisition list to show user.
		RekvisitionExtended[] rekvlist;
		// gets list of the active user - default behavior
		rekvlist = rekvisitionDao.findDynamic(condRekvirentId, 0, -1, activeUser.getBrugerId());
		//Stitch rekvisition[] to request object.
		request.getSession().setAttribute(Const.REKVISITION_LIST, rekvlist);	
		request.getRequestDispatcher(Const.REKVISITION_PAGE).forward(request, response);

	}
	/**
	 * have to be called so the dropdowns in rekvisitionPage are filled
	 * @param request
	 */
	private void createSearchDropdowns(HttpServletRequest request){
		request.getSession().setAttribute(Const.MODALITY_LIST, modDao.findDynamic(null, 0, -1, null));
		request.getSession().setAttribute(Const.STATUS_LIST, Status.values());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		createSearchDropdowns(request);
		ArrayList<Object> params = new ArrayList<>();
		String cond = "";
		// parameters
		String cpr = request.getParameter(Const.PARAM_CPR);
		String name = request.getParameter(Const.PARAM_NAME);
		String modality = request.getParameter(Const.PARAM_MODALITY);
		String department = request.getParameter(Const.PARAM_DEPARTMENT);
		department = (department.equals("-1") ? null : department);
		String date = request.getParameter(Const.PARAM_DATE).replace(" ", "");
		Timestamp timestamp = null;

		timestamp = (validateDate(date) ? stringToTimestamp(date) : null);
		

		String status = request.getParameter("status");
		Status statusObj = null;

		for(int i = 0; i < Status.values().length; i++){
			if(status.equalsIgnoreCase(Status.values()[i].name())){
				statusObj = Status.values()[i];
				System.out.println("Selected status in search: " + statusObj.name());
			}
		}

		// objekter
		RekvisitionExtended[] rekvDto;
		//TODO dao'er. skal ændres til almindeligt interface senere
		RekvisitionDaoImplExt rekvDao = new RekvisitionDaoImplExt(conn);
		rekvDto = rekvDao.findByAdvSearch(cpr, name, modality, statusObj, timestamp, department);





		// sammensætter select statement til at finde patient

		if(cpr != null){
			cond = "patient_cpr=?";
			params.add(cpr);
		}
		if(name != null){
			cond = cond + (cpr != null ? "AND patient_navn=?": "patient_navn=?");
			params.add(name);
		}
		if(department != null){
			cond = cond + (name != null || cpr != null ? "AND stamafdeling=?" : "stamafdeling=?");
			params.add(department);
		}


		// sammensætter select statement til at finde rekvisition
		if(modality != null){
			//			rekvCond = "modalitet=?"
		}
		
		request.getSession().setAttribute(Const.REKVISITION_LIST, rekvDto);
		request.getRequestDispatcher(Const.REKVISITION_PAGE).forward(request, response);

	}

	private boolean validateCpr(String cpr){
		return cpr.matches("(\\d{6}-\\w{4})");
	}
	/**
	 * 
	 * @param date formats accepted are: yyyy-mm-dd or yyyymmdd.
	 * constraints have been set so it is not possible to hava mm > 12 and dd > 39
	 * @return true if string matches format
	 */
	private boolean validateDate(String date){
		return date.matches("(\\d{4}-[0-1][0-2]-[0-3]\\d)");
//		return date.matches("([0-3]\\d-[0-1][0-2]-\\d{4})|(\\d{8})");
	}
	/**
	 * 
	 * @param timestamp String which can be transformed to timestamp. Should pass the validator method to make sure it passes 
	 * @return if format is not correct null will be returned
	 * Date can have two formats therefore check is made to find out which one
	 */
	private Timestamp stringToTimestamp(String timestamp){
		if(!validateDate(timestamp)) return null;

		DateFormat dateFormat = new SimpleDateFormat((timestamp.contains("-") ? "yyyy-MM-dd" : "yyyyMMdd"));

		//		if(!timestamp.contains("-")){
		//			dateFormat = new SimpleDateFormat("ddMMyyyy");
		//		}
		Date date = null;
		try {
			date = dateFormat.parse(timestamp);
		} catch (ParseException e) {
			System.err.println("failed to parse timestamp");
		}

		return new Timestamp(date.getTime());

	}

}
