package servlets;
import helperClasses.Const;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.dto.Bruger;
import database.interfaces.IDatabaseController;

/**
 * Servlet implementation class LoginServlet
 * 
 */
@SuppressWarnings("serial")
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet
{
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
		// if databaseController null requests to get back to loginServlet, but this time with a databaseController
		if(request.getSession().getAttribute(Const.DATABASE_CONTROLLER) == null){
			response.sendRedirect(Const.MAIN_SERVLET + "?page=" + Const.LOGIN_SERVLET);
		}
		//Check for login
	else if (request.getSession().getAttribute(Const.ACTIVE_USER)!= null)
			response.sendRedirect(Const.MAIN_SERVLET + "?page=" + Const.LOGIN_USER);
		else
			request.getRequestDispatcher(Const.LOGIN_PAGE).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		IDatabaseController databaseController =(IDatabaseController) request.getSession().getAttribute(Const.DATABASE_CONTROLLER);
		//User posts login data
		String username = request.getParameter(Const.USERNAME);
		String password = request.getParameter(Const.PASSWORD);
		
		if (username != null && password != null)
		{
			boolean loginSuccess = false;
			
			loginSuccess = databaseController.getBrugerDao().validate(username, password);
			if (loginSuccess)
			{
					Bruger loggedInUser= databaseController.getBrugerDao().findByUserName(username, password);
					
					if(loggedInUser.getErAktiv())
					{
						System.out.println("Login success, forwarding");
						request.getSession().setAttribute(Const.ACTIVE_USER, loggedInUser );
					
//						request.getSession().setAttribute(Const.DATABASE, dbctrl);
						
						response.sendRedirect(Const.MAIN_SERVLET + "?page=loggingIn");
						System.out.println("forward finished");
					}
					else
					{
						request.setAttribute(Const.LOGIN_FAILED, true);
						request.getRequestDispatcher(Const.LOGIN_PAGE).forward(request, response); // "WEB-INF/userlogin.jsp" -- stod som argument, men må være forkert??
					}
			}
			else
			{
				request.setAttribute(Const.LOGIN_FAILED, true);
				request.getRequestDispatcher(Const.LOGIN_PAGE).forward(request, response); // "WEB-INF/loginScreen.jsp" -- må også være forkert??
			}
				
			
		}
		else
		{
			if (request.getParameter(Const.USERNAME) != null)
				request.setAttribute(Const.LOGIN_FAILED, true);
			request.getRequestDispatcher(Const.LOGIN_PAGE).forward(request, response); // "WEB-INF/userlogin.jsp" -- må være forkert??
		}
	}
}
