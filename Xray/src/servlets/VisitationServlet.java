package servlets;

import helperClasses.Const;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.dto.Bruger;

/**
 * Servlet implementation class VisitationServlet
 */
@WebServlet("/VisitationServlet")
public class VisitationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VisitationServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Bruger activeUser = (Bruger) request.getSession().getAttribute(Const.ACTIVE_USER);
		// forwards to mainServlet with LoginPage as parameter
		if(activeUser == null){ 
			response.sendRedirect(Const.MAIN_SERVLET + "?page=" + Const.LOGIN_PAGE);
//			request.getRequestDispatcher(Const.MAIN_SERVLET + "?page=" + Const.LOGIN_PAGE).forward(request, response);
		}else{
			request.getRequestDispatcher(Const.VISITATION_PAGE).forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
