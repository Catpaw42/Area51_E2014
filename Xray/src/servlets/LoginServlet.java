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

/**@author Christian, Martin, Magnus
 * refactor og update: Rúni
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
		IDatabaseController databaseController =(IDatabaseController) request.getSession().getAttribute(Const.DATABASE_CONTROLLER);
		//Check for login
		if(databaseController == null){
			response.sendRedirect(Const.MAIN_SERVLET);
		}else if (request.getSession().getAttribute(Const.ACTIVE_USER)!= null)
			response.sendRedirect(Const.MAIN_SERVLET + "?page=" + "loggingIn");
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
		if(databaseController == null){
			response.sendRedirect(Const.MAIN_SERVLET);
		}
		else if (username != null && password != null)
		{
			boolean loginSuccess = false;
		
			loginSuccess = databaseController.getBrugerDao().validate(username, password);
			System.out.println(loginSuccess);
			if (loginSuccess)
			{
					Bruger loggedInUser= databaseController.getBrugerDao().findByUserName(username, password);
					
					if(loggedInUser.getErAktiv())
					{
						request.getSession().setAttribute(Const.ACTIVE_USER, loggedInUser );		
						response.sendRedirect(Const.MAIN_SERVLET + "?page=loggingIn");
					}
					else
					{
						request.setAttribute(Const.LOGIN_FAILED, true);
						request.getRequestDispatcher(Const.LOGIN_PAGE).forward(request, response); 
					}
			}
			else
			{
				request.setAttribute(Const.LOGIN_FAILED, true);
				request.getRequestDispatcher(Const.LOGIN_PAGE).forward(request, response);
			}
				
			
		}
		else
		{
			if (request.getParameter(Const.USERNAME) != null)
				request.setAttribute(Const.LOGIN_FAILED, true);
			request.getRequestDispatcher(Const.LOGIN_PAGE).forward(request, response);
		}
	}
}
