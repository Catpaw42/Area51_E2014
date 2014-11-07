package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.dto.Rekvisition;

/**
 * Servlet implementation class NyRekvisitionServlet
 */
@SuppressWarnings("serial")
@WebServlet("/NyRekvisitionServlet")
public class NyRekvisitionServlet extends HttpServlet
{       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NyRekvisitionServlet()
    {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		//Til at videredelegere til viewet
		request.getRequestDispatcher("nyRekvisitionPage.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		Rekvisition req = new Rekvisition();
		PrintWriter out = response.getWriter();
		out.println("Tak for din henvendelse - du kan følge med i status for din rekvisition i oversigten");
		
	}

}
