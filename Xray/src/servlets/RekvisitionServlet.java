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
import database.dto.Patient;
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
		String cond = null;
		Rekvisition[] rekvlist;
		RekvisitionDao rekvisitionDao = new RekvisitionDaoImpl(conn);
		rekvlist = rekvisitionDao.findDynamic(cond, 0, -1, null);
		
		
		request.setAttribute("rekvisitionlist", rekvlist);
		request.getRequestDispatcher("rekvisitionPage.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<Object> params = new ArrayList<>();
		String cond = "";

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

		RekvisitionDao rkdao= new RekvisitionDaoImpl(conn);

		String cpr = request.getParameter("cpr");
		String name = request.getParameter("name");
		String modality = request.getParameter("modality");
		String department = request.getParameter("department");
		String date = request.getParameter("date");
		//		String status = request.getParameter("status");
		PrintWriter out = response.getWriter();
		out.println(cpr);
		out.println(name);


		if(cpr != null){
			cond = "patient_cpr=?";
			params.add(cpr);
		}
		if(name != null){
			cond = cond + (cpr != null ? "AND patient_navn=?": "patient_navn=?");
			params.add(name);
		}
		if(department != null){
			//			cond = cond + (name != null || cpr != null ? "AND stamafdeling=?" : "stamafdeling=?");
			//			params.add(department);
		}

		out.print(cond);
		for(Object pa : params){
			out.println(pa.toString());
		}

		//		cond = cond + modality
		//		cond = cond + department
		//		cond = cond + date
		//		cond = 

		PatientDao dao = new PatientDaoImpl(conn);
		Patient[] p = dao.findDynamic(cond, 0, -1, params.toArray());
		Patient[] p2 = dao.findDynamic(null, 0, -1, null);
		out.println("p2 length: " + p2.length);
		out.println("p2 test: " + p2[0].getPatientNavn() + "##########");
		out.println("yay");
		if(p != null){
			out.println(p.length);
			//			out.println(p[0].getPatientAdresse());
			out.println("lnyay2");
			for (Patient patient : p) {
				out.println("yay3");
				out.println(patient.getPatientNavn());
			}
		}

	}

}
