package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.dto.MRKontrolskema;
import database.dto.MRKontrolskema.MRBoern;
import database.dto.MRKontrolskema.MRVoksen;
import database.dto.RekvisitionExtended;

/**
 * Servlet implementation class MRKontrolSkemaServlet
 */
@WebServlet("/MRKontrolSkemaServlet")
public class MRKontrolSkemaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MRKontrolSkemaServlet() {
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

	private void processKontrolSkema(RekvisitionExtended rekv, HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		MRKontrolskema mrk = new MRKontrolskema();
		mrk.setPacemaker(Boolean.valueOf(request.getParameter("pacemaker")));
		mrk.setMetalImplantater(Boolean.valueOf(request.getParameter("metal_implantater")));
		mrk.setMetalImplantaterBeskrivelse(request.getParameter("metal_implantater_beskrivelse"));
		mrk.setAndetMetalisk(Boolean.valueOf(request.getParameter("andet_metalisk")));
		mrk.setAndetMetaliskBeskrivelse(request.getParameter("andet_metalisk_beskrivelse"));
		mrk.setNyresygdom(Boolean.valueOf(request.getParameter("nyresygdom")));
		mrk.setNyresygdomKreatinin(Integer.valueOf(request.getParameter("nyresygdom_kreatinin")));
		mrk.setGraviditet(Boolean.valueOf(request.getParameter("graviditet")));
		mrk.setGraviditetUge(Integer.valueOf(request.getParameter("graviditet_uge")));
		mrk.setKlaustrofobi(Boolean.valueOf(request.getParameter("klaustrofobi")));
		mrk.setHoejde(Integer.valueOf(request.getParameter("hoejde")));
		mrk.setVaegt(Integer.valueOf(request.getParameter("vaegt")));
		mrk.setMRBoern(sederingBoern(request));
		mrk.setMRVoksen(sederingVoksen(request));
		mrk.setPraepForsyn(request.getParameter("praep_forsyn"));
		
		
		

	}
	
	private MRBoern sederingBoern(HttpServletRequest request){
		
		MRBoern mrboern;
		String mrboernString = request.getParameter("sederingBoern");
		if (mrboernString == null) return null;
		switch (mrboernString) {
		case "uden_sedering":
			mrboern = MRKontrolskema.MRBoern.UDEN_SEDERING;
			break;
		case "i_generel_anaestesi":
			mrboern = MRKontrolskema.MRBoern.I_GENEREL_ANAESTESI;
			break;
		default:
			mrboern = MRKontrolskema.MRBoern.UDEN_SEDERING;
			break;
		}
		
		return mrboern;
	}
	
	private MRVoksen sederingVoksen(HttpServletRequest request){
		
		MRVoksen mrvoksen;
		String mrvoksenString = request.getParameter("sederingVoksne");
		if (mrvoksenString == null) return null;
		switch (mrvoksenString) {
		case "uden_sedering":
			mrvoksen = MRKontrolskema.MRVoksen.UDEN_SEDERING;
			break;
		case "i_generel_anaestesi":
			mrvoksen = MRKontrolskema.MRVoksen.I_GENEREL_ANAESTESI;
			break;
		default:
			mrvoksen = MRKontrolskema.MRVoksen.UDEN_SEDERING;
			break;
		}
		
		return mrvoksen;
	}
	
}


