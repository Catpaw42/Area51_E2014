package servlets;

import helperClasses.Const;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.DataSourceConnector;
import database.dao.BrugerDao;
import database.dao.mysql.BrugerDaoImpl;
import database.dto.Bruger;
import database.dto.CtKontrastKontrolskema;
import database.dto.RekvisitionExtended;
import database.interfaces.IDataSourceConnector.ConnectionException;

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
			request.getRequestDispatcher(Const.MAIN_SERVLET).forward(request, response);
		}
	}

	private void checkRekvisition(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		RekvisitionExtended rekv = (RekvisitionExtended) request.getSession().getAttribute(Const.ACTIVE_REKVISITION);
		if (rekv !=null) {
			processKontrolSkema(rekv, request, response);
		} else {
			request.getRequestDispatcher(Const.MAIN_SERVLET).forward(request, response);
		}

	}

	private void processKontrolSkema(RekvisitionExtended rekv, HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		//Getting a database connection....
		Connection conn = null;
		try {
			conn = DataSourceConnector.getConnection();
		} catch (ConnectionException e1) {
			e1.printStackTrace();
		}
		//TODO remove
				if (request.getSession().getAttribute(Const.ACTIVE_USER) == null) {
					BrugerDao bDao = new BrugerDaoImpl(conn);
					Bruger abruger = bDao.findByPrimaryKey(1);
					request.getSession().setAttribute(Const.ACTIVE_USER, abruger);
				}
		Bruger activeBruger = (Bruger) request.getSession().getAttribute(Const.ACTIVE_USER);
		//	Making CTTKontrolskema dto
		CtKontrastKontrolskema ctk = new CtKontrastKontrolskema();
		ctk.setDiabetes(Boolean.valueOf(request.getParameter("diabetes")));
		ctk.setNyrefunktion(Boolean.valueOf(request.getParameter("nyrefunktion")));
		ctk.setNyreopereret(Boolean.valueOf(request.getParameter("nyreopereret")));
		ctk.setHjertesygdom(Boolean.valueOf(request.getParameter("hjertesygdom")));
		ctk.setMyokardieinfarkt(Boolean.valueOf(request.getParameter("myokardieinfarkt")));
		ctk.setProteinuri(Boolean.valueOf(request.getParameter("proteinuri")));
		ctk.setUrinsyregigt(Boolean.valueOf(request.getParameter("urinsyregigt")));
		ctk.setOver70(Boolean.valueOf(request.getParameter("over70")));
		ctk.setHypertension(Boolean.valueOf(request.getParameter("hypertension")));
		ctk.setNsaidPraeparat(Boolean.valueOf(request.getParameter("NSAIDpræparat")));
		ctk.setAllergi(Boolean.valueOf(request.getParameter("alergi")));
		ctk.setKontraststofreaktion(Boolean.valueOf(request.getParameter("konstrastofreaktion")));
		ctk.setAstma(Boolean.valueOf(request.getParameter("astma")));
		ctk.setHyperthyreoidisme(Boolean.valueOf(request.getParameter("hyperthyreoidisme")));
		ctk.setMetformin(Boolean.valueOf(request.getParameter("metformin")));
		ctk.setInterleukin2(Boolean.valueOf(request.getParameter("interleukin")));
		ctk.setBetaBlokkere(Boolean.valueOf(request.getParameter("betaBlokkere")));
		ctk.setPKreatininVaerdi(request.getParameter("Værdi"));
		//	setPKreatininTimestamp???
		ctk.setPtHoejde(Integer.valueOf(request.getParameter("Højde")));
		ctk.setPtVaegt(Integer.valueOf(request.getParameter("Vægt")));
		
		
		
		
	}
}


