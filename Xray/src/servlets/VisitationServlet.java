package servlets;

import helperClasses.Const;
import helperClasses.Validator;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.spoledge.audao.db.dao.DaoException;

import database.DataSourceConnector;
import database.dao.ModalitetDao;
import database.dao.mysql.ModalitetDaoImpl;
import database.dao.mysql.RekvisitionDaoImplExt;
import database.dto.Bruger;
import database.dto.Modalitet;
import database.dto.RekvisitionExtended;
import database.dto.RekvisitionExtended.Status;
import database.interfaces.IDataSourceConnector.ConnectionException;

/**
 * Servlet implementation class VisitationServlet
 */
@WebServlet("/VisitationServlet")
public class VisitationServlet extends HttpServlet {
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
    public VisitationServlet() {
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
		Bruger activeUser = (Bruger) request.getSession().getAttribute(Const.ACTIVE_USER);
		// forwards to mainServlet with LoginPage as parameter
		if(activeUser == null){ 
			response.sendRedirect(Const.MAIN_SERVLET + "?page=" + Const.LOGIN_PAGE);
		}else{
			request.getRequestDispatcher(Const.VISITATION_PAGE).forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String visiterAction = request.getParameter("visiterAction");
		if("Godkend".equals(visiterAction)){
			approveRekvisition(request);
		}
		else if("Afvis".equals(visiterAction)){
			declineRekvisition(request);
		}else{
			searchRekvisition(request, response);
		}
			
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
		Date d1 = new Date();
		RekvisitionDaoImplExt rekvDao = new RekvisitionDaoImplExt(conn);
		rekvDto = rekvDao.findByAdvSearch(cpr, name, modality, statusObj, timestamp, department);
		Date d2 = new Date();

		System.out.println("##found " + (rekvDto != null ? rekvDto.length : 0) + " rekvisitions");
		System.out.println("in " + (d2.getTime()- d1.getTime()) + " ms");
		System.out.println("##################################");
		
		request.getSession().setAttribute(Const.REKVISITION_LIST, rekvDto);
		request.getRequestDispatcher(Const.VISITATION_PAGE).forward(request, response);

	}
	
	private void declineRekvisition(HttpServletRequest request){
		RekvisitionDaoImplExt rekDao = null;
		int id=Integer.valueOf(request.getParameter("rekIDSubmit"));
		try {
			rekDao = new RekvisitionDaoImplExt(DataSourceConnector.getConnection());
			RekvisitionExtended rek = rekDao.findByPrimaryKey(id);
			rek.setStatus(Status.DECLINED);
			try {
				System.out.println("decline");
				System.out.println(rekDao.update(id, rek));
			} catch (DaoException e) {
				e.printStackTrace();
			}
		} catch (ConnectionException e) {
			e.printStackTrace();
		}
	}
	
	private void approveRekvisition(HttpServletRequest request){
		RekvisitionDaoImplExt rekDao = null;
		int id=Integer.valueOf(request.getParameter("rekIDSubmit"));
		try {
			rekDao = new RekvisitionDaoImplExt(DataSourceConnector.getConnection());
			RekvisitionExtended rek = rekDao.findByPrimaryKey(id);
			rek.setStatus(Status.APPROVED);
			try {
				System.out.println("approve");
				System.out.println(rekDao.update(id, rek));
			} catch (DaoException e) {
				e.printStackTrace();
			}
		} catch (ConnectionException e) {
			e.printStackTrace();
		}
	}

}
