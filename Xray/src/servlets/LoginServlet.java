package servlets;
import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.DataBaseController;
import database.DataSourceConnector;
import database.dao.BrugerDao;
import database.dao.mysql.BrugerDaoImpl;
import database.dao.mysql.BrugerDaoImplExtended;
import database.dto.Bruger;
import database.interfaces.IDataBaseController;
import database.interfaces.IDataBaseController.DatabaseException;
import database.interfaces.IDataBaseController.UserNotFoundException;
import database.interfaces.IDataSourceConnector.ConnectionException;

/**
 * Servlet implementation class LoginServlet
 * 
 */
@SuppressWarnings("serial")
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet
{
	IDataBaseController dbctrl = new DataBaseController();

	public LoginServlet()
	{
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		//Check for login
		if (request.getSession().getAttribute("user")!= null)
			//TODO determine main user role and redirect to relevant page
			response.sendRedirect("MainServlet?page=rekvirer");
		else
			request.getRequestDispatcher("loginPage.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		//User posts login data
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		System.out.println("username: " + username);
		System.out.println("password: " + password);
		
		if (username != null && password != null)
		{
			boolean loginSuccess = false;
				
			Connection conn = null;
			try {
				conn = DataSourceConnector.getConnection();
			} catch (ConnectionException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			//TODO skifte til interface når DAO og DTO er rigtige
			BrugerDaoImplExtended b = new BrugerDaoImplExtended(conn);
			
			loginSuccess = b.validate(username, password);
			System.out.println(loginSuccess);
			if (loginSuccess)
			{
				try
				{
					Bruger loggedInUser=dbctrl.getUserFromUsername(username);
					
					if(loggedInUser.getErAktiv())
					{
						System.out.println("Login success, forwarding");
						request.getSession().setAttribute("user", loggedInUser );
					
						request.getSession().setAttribute("database", dbctrl);
						response.sendRedirect("MenuServlet");
						System.out.println("forward finished");
					}
					else
					{
						request.setAttribute("loginFail", true);
						request.getRequestDispatcher("WEB-INF/userlogin.jsp").forward(request, response);
					}
				
				}
				catch (DatabaseException e)
				{
					e.printStackTrace();
				}
				catch (UserNotFoundException e)
				{
					e.printStackTrace();
				}
			}
			else
			{
				request.setAttribute("loginFail", true);
				request.getRequestDispatcher("WEB-INF/loginScreen.jsp").forward(request, response);
			}
				
			
		}
		else
		{
			if (request.getParameter("username") != null)
				request.setAttribute("loginFail", true);
			request.getRequestDispatcher("WEB-INF/userlogin.jsp").forward(request, response);
		}
	}
}
