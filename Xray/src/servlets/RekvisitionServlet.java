package servlets;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import database.DataSourceConnector;
import database.dao.RekvisitionDao;
import database.dao.mysql.RekvisitionDaoImpl;
import database.dto.Rekvisition;

/**
 * Servlet implementation class RekvisitionServlet
 */
@WebServlet("/RekvisitionServlet")
public class RekvisitionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RekvisitionServlet() {
        super();
        // TODO Auto-generated constructor stub
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
		Connection conn;
		conn = DataSourceConnector.getConnection();
		
		Rekvisition[] rekv;
		String modalitet = request.getParameter("modality");
		RekvisitionDao rkdao= new RekvisitionDaoImpl(conn);
		rkdao.findDynamic(rekvisitionId);
		
		//send data back
		request.setAttribute("rekvisitionliste", rekv );
		request.getRequestDispatcher("rekvisitionPage.jsp").forward(request, response);
	
	}

}
