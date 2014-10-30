package dto;

import java.sql.Timestamp;
import java.util.ArrayList;


public class RequisitionTemplate {
	



	public static void main(String[] args) {
		RequisitionTemplate t = new RequisitionTemplate();
		t.test3();
	}

	public enum HenvistTil{radiologisk, klinisk}
	public enum HospitalOenske{hillerød, frederikssund};
	public enum Prioritering{haste, rutine, fremskyndet, pakkeforloeb};
	public enum AmbulantKoersel{ingen, siddende, liggende};
	public enum IndlaeggelseTransport{gaaMedPortoer, gaaUdenPortoer, koerestol, seng};
	public enum Undersoegelsemodalitet{PET_CT, CT, CTKontrast, ultralyd, mr, other};
	public enum Status{PENDING, CANCELED, APPROVED, DECLINED, BOOKED}
	

	private HenvistTil henvistTil;
	private HospitalOenske hospitalOenske;
	private Prioritering prioriteringOenske;
	private boolean samtykke;
	private boolean ambulant;
	private String datoForslag; // skal typecheckes for korrekt dato input
	private AmbulantKoersel ambulantKoersel;
	private boolean indlaeggelse;
	private IndlaeggelseTransport indlagtTransport;
	private boolean pregnancy; 
	private String cave;
	//private ArrayList<SaerligeForhold> saerligeForhold;
	private boolean hoerehaemmet;
	private boolean synshaemment;
	private boolean amputeret;
	private boolean kanikkestaa;
	private int iltliterprmin;
	private String tolksprog;
	private boolean dement;
	private boolean afasi;
	private String isolation;
	private String cytostatikaDato;
	private Undersoegelsemodalitet undersoegelsesmodalitet;
	private String undersoegelsestype;
	private String tidlBilleddiagnostik;
	private String kliniskProblemstilling;
	private String triage;
	private String henvlaege;
	private String henvafd;
	private String kontakttlf;
	
	private String visitatorPrioritering;
	private Status status;
	private Timestamp afsendtDato; 
	
	
	
	
	
	
	
	interface test{
		public void setHenvistTil(HenvistTil value);
		public HenvistTil getHenvistTil();
	}
	
	
	public void test3(){
		Prioritering test = null;
		
		int a = ambulantKoersel.values().length;
		System.out.println(a);
	}
}
