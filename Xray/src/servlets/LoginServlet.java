package servlets;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.DataBaseController;
import database.interfaces.IDataBaseController;
import database.interfaces.IDataBaseController.DatabaseException;
import database.interfaces.IDataBaseController.UserNotFoundException;
import dto.DTOUser;

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
			response.sendRedirect("MenuServlet");
		else
			request.getRequestDispatcher("WEB-INF/loginScreen.jsp").forward(request, response);
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
				
			DTOUser user = new DTOUser(username, password, null, null);		
			System.out.println(user);
			
			//Validating user
			try 
			{
				loginSuccess = dbctrl.validateUser(user);
			}
			catch (DatabaseException e)
			{	
				e.printStackTrace();
				System.err.println(e.getMessage());
				System.err.println("failed to validate User in loginServlet");
			}
			System.out.println(loginSuccess);
			if (loginSuccess)
			{
				try
				{
					DTOUser loggedInUser=dbctrl.getUserFromUsername(username);
					
					if(loggedInUser.isActive())
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
