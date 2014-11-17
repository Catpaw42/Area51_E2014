package servlets;

import helperClasses.Const;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.dto.RekvisitionExtended;

/**
 * Servlet implementation class CTKKontrolSkemaServlet
 */
@WebServlet("/CTKKontrolSkemaServlet")
public class CTKKontrolSkemaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CTKKontrolSkemaServlet() {
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
		if (request.getSession().getAttribute(Const.ACTIVE_USER)!=null){
			checkRekvisition(request, response);
		}else{
			request.getRequestDispatcher(Const.MAIN_SERVLET).forward(request, response);;	
		}
	}

	private void checkRekvisition(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		RekvisitionExtended rekv = (RekvisitionExtended) request.getSession().getAttribute(Const.ACTIVE_REKVISITION);
		if (rekv !=null) {
			processKontrolSkema(request, response);
		} else {
			request.getRequestDispatcher(Const.MAIN_SERVLET).forward(request, response);
		}

	}

	private void processKontrolSkema(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO store rekvisition and kontrolskema
		
	}
}


