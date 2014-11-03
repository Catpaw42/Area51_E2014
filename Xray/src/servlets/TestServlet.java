package servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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

import database.DataSourceConnector;
import database.interfaces.IDataSourceConnector.ConnectionException;
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
		
		testUser(statement, connection);
		
		testMrKontrol(statement, connection);

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
