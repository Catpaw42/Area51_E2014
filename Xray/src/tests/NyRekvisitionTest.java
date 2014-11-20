package tests;

import static org.junit.Assert.*;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.Connection;

import helperClasses.Const;

import javax.servlet.ServletConfig;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Before;
import org.junit.Test;














import servlets.MainServlet;
import servlets.NyRekvisitionServlet;
import servlets.RekvisitionServlet;
import database.DataSourceConnector;
import database.dao.BrugerDao;
import database.dao.RekvisitionDao;
import database.dao.mysql.BrugerDaoImplExtended;
import database.dao.mysql.RekvisitionDaoImplExt;
import database.dto.Bruger;
import database.dto.RekvisitionExtended;
import database.interfaces.IDataSourceConnector.ConnectionException;



public class NyRekvisitionTest{
	 HttpServletRequest request;
	 HttpServletResponse response;
	 RekvisitionExtended rekvisition;
	 NyRekvisitionServlet rekvServlet;
	 MainServlet mainServlet;
	 
	
	@Before
	public void setup() throws Exception{
		
		

		
		
		
		
		Connection conn = new SQLConnector().getConnection();
		BrugerDao BDao = new BrugerDaoImplExtended(conn);
		RekvisitionDao RDao = new RekvisitionDaoImplExt(conn);
		this.rekvisition = new RekvisitionExtended();
		Bruger activeUser = BDao.findByPrimaryKey(1);
		System.out.println("active user: " + activeUser);
		
//		
//		
//        this.request = mock(HttpServletRequest.class);       
//        this. response = mock(HttpServletResponse.class);
//        
//		when(request.getPathInfo()).thenReturn("/lineup/world.xml");
//		final StubServletOutputStream servletOutputStream = new StubServletOutputStream();
//		when(response.getOutputStream()).thenReturn(servletOutputStream);
//		final ServletConfig servletConfig = mock(ServletConfig.class);
//		when(servletConfig.getInitParameter("defaultPool")).thenReturn("testpool1");
//		
		request.getPathInfo();
		
        this.rekvServlet = new NyRekvisitionServlet();
        this.mainServlet = new MainServlet();
        this.request.setAttribute(Const.ACTIVE_USER, activeUser);
        System.out.println("test user: " + this.request.getAttribute(Const.ACTIVE_USER));
        
        

	}
	
	@Test
	public void nyRekvisitionUdenKontrolskema() throws Exception{
		System.out.println(request.getAttribute(Const.ACTIVE_USER));
		System.out.println(response.toString());
		System.out.println(request.toString());
		response.sendRedirect(Const.MAIN_SERVLET + "?page=" + "loggingIn");
		System.out.println(response.toString());
		System.out.println(request.toString());
//		rekvServlet.doGet(request, response);
		assertEquals("text/html", request.getContentType());
		
	}
	
	private class StubServletOutputStream extends ServletOutputStream {
		 public ByteArrayOutputStream baos = new ByteArrayOutputStream();
		   public void write(int i) throws IOException {
		    baos.write(i);
		 }
		}
}
