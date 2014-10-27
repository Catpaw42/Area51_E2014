package servlets;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.DataBaseController;
import database.interfaces.IDataBaseController;
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
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		//User posts login data
		System.out.println("UserLogin - Post");
		String username = request.getParameter("username");
		if (username != null){
			boolean loginSuccess = false;
			String password = request.getParameter("password");
			System.out.println("username: " + username);
			System.out.println("password: " + password);
			DTOUser loginUser = new DTOUser(username, 0, password);
			System.out.println(loginUser);
			//Validating user
			try {loginSuccess = dbctrl.validateUser(loginUser);
			} catch (DataBaseException e) {		e.printStackTrace();	
				System.err.println("failed to validateUser in login servlet");
			}
			System.out.println(loginSuccess);
			if (loginSuccess) {
				try {
				DTOUser log=dbctrl.getUserFromString(username);
				if(log.isActive()){
				System.out.println("Login success forwarding");
				
					request.getSession().setAttribute("user", log );
				
				request.getSession().setAttribute("database", dbctrl);
				response.sendRedirect("MenuServlet");
				System.out.println("forward finished");
				}
				else{
					request.setAttribute("loginFail", true);
					request.getRequestDispatcher("WEB-INF/userlogin.jsp").forward(request, response);
				}
				
			} catch (DataBaseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (UserNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{}
				}
			else{
				request.setAttribute("loginFail", true);
				request.getRequestDispatcher("WEB-INF/userlogin.jsp").forward(request, response);
			}
				
			
		} else {
			if (request.getParameter("username")  != null) 
				request.setAttribute("loginFail", true);
			request.getRequestDispatcher("WEB-INF/userlogin.jsp").forward(request, response);
		}
	}


}
