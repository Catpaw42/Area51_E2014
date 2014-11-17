package servlets;

import java.io.IOException;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.dto.MRKontrolskema;
import database.dto.PETCTKontrolskema;
import database.dto.PETCTKontrolskema.Formaal;
import database.dto.PETCTKontrolskema.KemoOgStraale;
import database.dto.RekvisitionExtended;
import database.dto.MRKontrolskema.MRVoksen;

/**
 * Servlet implementation class PETCTKontrolSkemaServlet
 */
@WebServlet("/PETCTKontrolSkemaServlet")
public class PETCTKontrolSkemaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PETCTKontrolSkemaServlet() {
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
		// TODO Auto-generated method stub
	}

	private void processKontrolskema(RekvisitionExtended rekv, HttpServletRequest request) throws ServletException, IOException{

		PETCTKontrolskema pck = new PETCTKontrolskema();
		pck.setFormaal(formaalMetode(request));
		pck.setFormaalTekst(request.getParameter("formaal_text"));
		pck.setKanPtLiggeStille30(Boolean.valueOf(request.getParameter("kanPtLiggeStille30")));
		pck.setPtTaalerFaste(Boolean.valueOf(request.getParameter("ptTaalerFaste")));
		pck.setDiabetes(Boolean.valueOf(request.getParameter("diabetes")));
		pck.setDMBeh(request.getParameter("DM_Beh"));
		pck.setSmerter(Boolean.valueOf(request.getParameter("smerter")));
		pck.setRespInsuff(Boolean.valueOf(request.getParameter("respInsuff")));
		pck.setKlaustrofobi(Boolean.valueOf(request.getParameter("klaustrofobi")));
		pck.setAllergi(Boolean.valueOf(request.getParameter("allergi")));
		pck.setAllergiTekst(request.getParameter("allergi_tekst"));
		pck.setFedme(Boolean.valueOf(request.getParameter("fedme")));
		pck.setVaegt(Integer.valueOf(request.getParameter("vaegt")));
		pck.setBiopsi(Boolean.valueOf(request.getParameter("biopsi")));
		pck.setBiopsiTekst(request.getParameter("biopsi_tekst"));
		pck.setOperation(Boolean.valueOf(request.getParameter("operation")));
		pck.setOperationTekst(request.getParameter("operation_tekst"));
		pck.setKemoOgStraale(kemostraale(request));
		pck.setSidstePKreatTimestamp(Timestamp.valueOf(request.getParameter("straaleDato")));
		pck.setNedsatNyreFkt(Boolean.valueOf(request.getParameter("nedsatNyreFkt")));
		pck.setSidstePKreatinin(Integer.valueOf(request.getParameter("sidstePKreatinin")));
		pck.setSidstePKreatTimestamp(Timestamp.valueOf(request.getParameter("sidstePKreatTimestamp")));

	}
	
private KemoOgStraale kemostraale(HttpServletRequest request){
		
		KemoOgStraale pck;
		String pckString = request.getParameter("aldrigGivetKemo");
		if (pckString == null) return null;
		switch (pckString) {
		case "aldrigGivetKemoJa":
			pck = PETCTKontrolskema.KemoOgStraale.ALDRIGGIVET;
			break;
		case "kemoterapiJa":
		case "stråleterapiNej":
			pck = PETCTKontrolskema.KemoOgStraale.KEMOTERAPI;
			break;
		case "kemoterapiNej":
		case "stråleterapiJa":
			pck = PETCTKontrolskema.KemoOgStraale.STRAALETERAPI;
			break;
		default:
			pck = PETCTKontrolskema.KemoOgStraale.KEMO_OG_STRAALE;
			break;
		}
		
		return pck;
	}

	private Formaal formaalMetode(HttpServletRequest request){

		Formaal formaal = null;
		String formaalString = request.getParameter("formaal");
		if (formaalString == null) return null;
		switch (formaalString) {
		case "primardiag":
			formaal = PETCTKontrolskema.Formaal.PRIMAERDIAG;
			break;
		case "kontrolbeh":
			formaal = PETCTKontrolskema.Formaal.KONTROLBEH;
			break;
		case "kontrolremission":
			formaal = PETCTKontrolskema.Formaal.KONTROLREMISSION;
			break;
		case "kontrolrecidiv":
			formaal = PETCTKontrolskema.Formaal.KONTROLRECIDIV;
			break;
		}

		return formaal;
	}

}
