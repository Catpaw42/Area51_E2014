package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import com.spoledge.audao.db.dao.DaoException;

import database.DataBaseController;
import database.DataSourceConnector;
import database.dao.BrugerDao;
import database.dao.PatientDao;
import database.dao.RekvisitionDao;
import database.dao.mysql.BrugerDaoImpl;
import database.dao.mysql.PatientDaoImpl;
import database.dao.mysql.RekvisitionDaoImpl;
import database.dao.mysql.RekvisitionDaoImplExt;
import database.dto.Patient;
import database.dto.Rekvisition.Status;
import database.interfaces.IDataSourceConnector.ConnectionException;
import database.DataSourceConnector;
import database.dao.RekvisitionDao;
import database.dao.mysql.RekvisitionDaoImpl;
import database.dto.Rekvisition;

/**
 * Servlet implementation class RekvisitionServlet
 */
@WebServlet("/RekvisitionServlet")
public class RekvisitionServlet extends HttpServlet {
	private java.sql.Connection conn = null;
	private static final long serialVersionUID = 1L;

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
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cond = "status=?";
		Rekvisition[] rekvlist;
		RekvisitionDaoImplExt rekvisitionDao = new RekvisitionDaoImplExt(conn);
		System.out.println("status options: " + Status.PENDING.ordinal());
		rekvlist = rekvisitionDao.findByAdvSearch(null, null, null, Status.PENDING, null, null); //(cond, 0, -1, new Object[]{Status.PENDING});
		System.out.println("length: " + rekvlist.length);
		for (Rekvisition rekvisition : rekvlist) {
			
			System.out.println("id: " + rekvisition.getRekvisitionId());
			System.out.println("status: " + rekvisition.getStatus());
		}
		request.setAttribute("rekvisitionlist", null);
		request.setAttribute("rekvisitionlist", rekvlist);
		request.getRequestDispatcher("rekvisitionPage.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<Object> params = new ArrayList<>();
		String cond = "";
		// parameters
		String cpr = request.getParameter("cpr");
		String name = request.getParameter("name");
		String modality = request.getParameter("modality");
		String department = request.getParameter("department");
		String date = request.getParameter("date");
		String status = request.getParameter("status");
		
		// objekter
		Rekvisition[] rekvDto;
		//TODO dao'er. skal ændres til almindeligt interface senere
		RekvisitionDaoImplExt rekvDao = new RekvisitionDaoImplExt(conn);
		rekvDto = rekvDao.findByAdvSearch(cpr, name, modality, null, null, department);

		//##############TEST######################
		//		Patient p1 = new Patient();
		//		p1.setPatientId(1);
		//		p1.setPatientNavn("nansy");
		//		p1.setPatientCpr("1234");
		//		p1.setStamafdeling("gsdf");
		//		p1.setPatientAdresse("test");
		//		p1.setPatientTlf("sdfsgf");
		//		p1.setFoedselsdag(new Date());
		//		
		//		PatientDao dao1 = new PatientDaoImpl(conn);
		//		try {
		//			dao1.insert(p1);
		//		} catch (DaoException e) {
		//			// TODO Auto-generated catch block
		//			e.printStackTrace();
		//		}


		//#########################################
//		Rekvisition[] rekv;
//		String modalitet = request.getParameter("modality");
//		String afdeling = request.getParameter("department");
//		String dato = request.getParameter("date");
//		String status = request.getParameter("status");
		


		
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

		
		System.out.print(cond);
		for(Object pa : params){
			System.out.println(pa.toString());
		}

		//		cond = cond + modality
		//		cond = cond + department
		//		cond = cond + date
		//		cond = 

		PatientDao dao = new PatientDaoImpl(conn);
		Patient[] p = dao.findDynamic(cond, 0, -1, params.toArray());
		Patient[] p2 = dao.findDynamic(null, 0, -1, null);
		System.out.println("p2 length: " + p2.length);
		System.out.println("p2 test: " + p2[0].getPatientNavn() + "##########");
		System.out.println("yay");
		if(p != null){
			System.out.println(p.length);
			//			System.out.println(p[0].getPatientAdresse());
			System.out.println("lnyay2");
			for (Patient patient : p) {
				System.out.println("yay3");
				System.out.println(patient.getPatientNavn());
			}
		}

	}

}
