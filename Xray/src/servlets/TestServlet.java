package servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

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
		try {
			// Get DataSource from context.xml/web.xml file - look there...
			Context initContext  = new InitialContext();
			Context envContext  = (Context)initContext.lookup("java:/comp/env");
			dataSource = (DataSource)envContext.lookup("jdbc/s134000");
			//Initialize
			ResultSet resultSet = null;
			Connection connection = null;
			Statement statement = null;
			try {
				// Get Connection and Statement
				connection = dataSource.getConnection();
				statement = connection.createStatement();
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

		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
