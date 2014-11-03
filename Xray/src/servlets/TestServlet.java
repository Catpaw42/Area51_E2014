package servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Date;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import com.spoledge.audao.db.dao.DaoException;

import database.DataSourceConnector;
import database.dao.DaoFactory;
import database.dao.RequisitionDao;
import database.dto.Requisition;
import database.dto.Requisition.AmbulantKoersel;
import database.dto.Requisition.HenvistTil;
import database.dto.Requisition.HospitalOenske;
import database.dto.Requisition.IndlaeggelseTransport;
import database.dto.Requisition.Pririotering;
import database.dto.Requisition.Status;
import database.dto.Requisition.UndersoegelseModalitet;
import database.interfaces.IDataSourceConnector.ConnectionException;
//import dto.DTOexRequest;
//import dto.DTOexRequest.Status;
import dto.RequisitionTemplate.Prioritering;
import dto.MrKontrol;

/**
 * Servlet implementation class TestServlet
 */
@WebServlet("/TestServlet")
public class TestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DataSource dataSource;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public TestServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Statement statement = null;
		ResultSet resultSet = null;
		Connection connection = null;
		
		// Get Connection and Statement
		try {
			connection = DataSourceConnector.getConnection();
			statement = connection.createStatement();
		} catch (SQLException e){
			e.printStackTrace();
		}catch (ConnectionException e1) {
			e1.printStackTrace();
		}
		
//		testUser(statement, connection);
		
//		testMrKontrol(statement, connection);
		
		testExRequest(connection);

	}

	private void testMrKontrol(Statement statement, Connection connection) {
		MrKontrol m = new MrKontrol();
		m.setAllergi(true);
		m.setAminoglykosider(false);
		m.setAstma(true);
		m.setBetaBlokkere(false);
		m.setCtKontrastKontrolskemaId(-1);
		m.setDiabetes(null);
		m.setHjertesygdom(false);
		m.setHypertension(true);
		m.setHyperthyreoidisme(false);
		m.setInterleukin2(true);
		m.setKontraststofreaktion(false);
		m.setMetformin(null);
		m.setMyokarieinfarkt(false);
		m.setNsaidPraeparat(true);
//		m.setNyrefunktion(_val);
		m.setNyreopereret(false);
		m.setOver70(true);
		m.setPKreatinTimestamp(new Date());
		m.setPKreatinVaerdi("meget høj");
		m.setProteinuri(true);
		m.setPtHoejde(198);
		m.setPtVaegt(32);
		m.setUrinsyregigt(true);
		
		
		
		
	}
	
	private void testExRequest(Connection conn){
		Long primaryKey = new Long(2);
//		DTOexRequest dtod = new DTOexRequest(-1, 2, 1, 0, Status.PENDING, new Timestamp(new Date().getTime()), null, new Timestamp(new Date(0).getTime()), null);
//		DaoFactory f = new DaoFactory();
		System.err.println("trying to get dao \n");
		RequisitionDao dao = DaoFactory.createRequisitionDao(conn);
		System.err.println("dao aquired");
		Requisition dto = new Requisition();
		dto.setAmbulant(true);
		dto.setAmbulantKoersel(AmbulantKoersel.LIGGENDE);
		dto.setCave("utrolig farligt altså");
		dto.setDatoForslag("198-123-1");
		dto.setGraviditet(true);
		dto.setHenvistTil(HenvistTil.RADIOLOGISK);
		dto.setHospitalOenske(HospitalOenske.FREDERIKSSUND);
		dto.setIndlaeggelseTransport(IndlaeggelseTransport.GAA_MED_PORTOER);
		dto.setPririotering(Pririotering.PAKKEFORLOEB);
		dto.setRekvisitionId(primaryKey); // primary key
		dto.setSamtykke(true);
		dto.setStatus(Status.APPROVED);
		dto.setUdfIndlagt(false);
		dto.setUndersoegelseModalitet(UndersoegelseModalitet.CT_KONTRAST);
		
		try {

			System.out.println("insert dto: pr-key: " + dto.getRekvisitionId() + "...");
			dao.insert(dto);
			System.out.println("dto inserted");
			
			System.out.println("searching for inserted dto pr-key: " + dto.getRekvisitionId() + "...");
			Requisition r =  dao.findByPrimaryKey(dto.getRekvisitionId());
			System.out.println("objects primary key: " + r.getRekvisitionId());
			
			System.out.println("updating status...");
			System.out.println("current status: " + dto.getStatus().toString() + " vs " + r.getStatus() );
			boolean success = dao.updateStatus(dto.getRekvisitionId(), Status.CANCELED);
			System.out.println("update was a success: " + success);
			r = dao.findByPrimaryKey(primaryKey);
			System.out.println("new status vs old: " + r.getStatus() + " vs " + dto.getStatus());
			
			
		} catch (DaoException e) {
			System.err.println("failed to insert row");
			e.printStackTrace();
		}
		
	}

	private void testUser(Statement statement, Connection connection) {
		ResultSet resultSet = null;
		try {
			String query = "SELECT * FROM users";
			resultSet = statement.executeQuery(query);
			while (resultSet.next()) {
				System.out.println(resultSet.getString(1) + resultSet.getString(2) + resultSet.getString(3));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try { if(null!=resultSet)resultSet.close();} catch (SQLException e) 
			{e.printStackTrace();}
			try { if(null!=statement)statement.close();} catch (SQLException e) 
			{e.printStackTrace();}
			try { if(null!=connection)connection.close();} catch (SQLException e) 
			{e.printStackTrace();}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
