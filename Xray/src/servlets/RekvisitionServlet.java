package servlets;

import java.io.IOException;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;







import database.DataSourceConnector;
import database.dao.ModalitetDao;
import database.dao.mysql.ModalitetDaoImpl;
import database.dao.mysql.RekvisitionDaoImplExt;
import database.dto.Bruger;
import database.dto.Modalitet;
import database.dto.RekvisitionExtended.Status;
import database.dto.RekvisitionExtended;
import database.interfaces.IDataSourceConnector.ConnectionException;
import helperClasses.Const;
import helperClasses.Validator;


/**
 * Servlet implementation class RekvisitionServlet
 */
@WebServlet("/RekvisitionServlet")
public class RekvisitionServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private java.sql.Connection conn = null;

	// used for the search boxes in rekvisitionPage.jsp
	public Modalitet[] modList = null;
	public Status[] statusList = null;		
	////////////////////////////////////////////////////	

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
		this.rekvisitionDao = new RekvisitionDaoImplExt(conn);
		this.modDao = new ModalitetDaoImpl(conn);

		
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		createSearchDropdowns(request);


		Bruger activeUser = (Bruger) request.getSession().getAttribute(Const.ACTIVE_USER);
		// forwards to mainServlet with LoginPage as parameter
		if(activeUser == null){ 
			response.sendRedirect(Const.MAIN_SERVLET + "?page=" + Const.LOGIN_PAGE);
//			request.getRequestDispatcher(Const.MAIN_SERVLET + "?page=" + Const.LOGIN_PAGE).forward(request, response);
		}else{
			setDefaultTable(activeUser, request, response);
		}
		

	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		createSearchDropdowns(request);
		Bruger activeUser = (Bruger) request.getSession().getAttribute(Const.ACTIVE_USER);
		
		
		//Getting Dummy user
		if(activeUser == null){
			response.sendRedirect(Const.MAIN_SERVLET + "?page=" + Const.LOGIN_PAGE);
//			request.getRequestDispatcher(Const.MAIN_SERVLET + "?page=" + Const.LOGIN_PAGE).forward(request, response);		
		}else{
			searchRekvisition(request, response);
		}
		
		
	}

	/**
	 * have to be called so the dropdowns in rekvisitionPage are filled
	 * @param request
	 */
	private void createSearchDropdowns(HttpServletRequest request){
		request.getSession().setAttribute(Const.MODALITY_LIST, modDao.findDynamic(null, 0, -1, new Object[]{}));
		request.getSession().setAttribute(Const.STATUS_LIST, Status.values());
	}
	
	private void searchRekvisition(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
	
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
		RekvisitionDaoImplExt rekvDao = new RekvisitionDaoImplExt(conn);
		rekvDto = rekvDao.findByAdvSearch(cpr, name, modality, statusObj, timestamp, department);


		System.out.println("##found " + (rekvDto != null ? rekvDto.length : 0) + " rekvisitions");
		System.out.println("##################################");
		
		request.getSession().setAttribute(Const.REKVISITION_LIST, rekvDto);
		request.getRequestDispatcher(Const.REKVISITION_PAGE).forward(request, response);

	}
	
	private void setDefaultTable(Bruger activeUser, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
				
				//Rekvisition list to show user.
				RekvisitionExtended[] rekvlist = null;
				// gets list of the active user - default behavior
				if(activeUser != null){
				rekvlist = rekvisitionDao.findDynamic(Const.REKVIRENT_ID_COND, 0, -1, activeUser.getBrugerId());
				}
				//Stitch rekvisition[] to request object.
				request.getSession().setAttribute(Const.REKVISITION_LIST, rekvlist);	
				request.getRequestDispatcher(Const.REKVISITION_PAGE).forward(request, response);
	}

	


}
