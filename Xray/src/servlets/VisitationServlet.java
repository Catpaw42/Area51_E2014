package servlets;

import helperClasses.Const;

import java.io.IOException;
import java.util.Enumeration;

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
			request.setAttribute(Const.PAGEHEADING, Const.VISITATION_TITLE);
			request.getRequestDispatcher(Const.VISITATION_PAGE).forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//String id=request.getParameter("rekIDSubmit").toString();
		//String value = request.getParameter("visiterAction").toString();
		System.out.println("Post");
		
		Enumeration parameterList = request.getParameterNames();
		System.out.println(parameterList.toString());
		while( parameterList.hasMoreElements() )
		  {
		    String sName = parameterList.nextElement().toString();
		      String[] sMultiple = request.getParameterValues( sName );
		      if( 1 >= sMultiple.length )
		        // parameter has a single value. print it.
		        System.out.println( sName + " = " + request.getParameter( sName ));
		      else
		        for( int i=0; i<sMultiple.length; i++ )
		          // if a paramater contains multiple values, print all of them
		          System.out.println( sName + "[" + i + "] = " + sMultiple[i] );
		    
		  }
		
		//request.getRequestDispatcher(Const.VISITATION_PAGE).forward(request, response);
		//System.out.println("id:"+id);
		//System.out.println("Action:"+value);
		
		
	}

}
