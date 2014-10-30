package dto;

import java.util.ArrayList;


public class CheckListData {
	
	public static void main(String[] args) {
		CheckListData t = new CheckListData();
		t.test3();
	}

	public enum HenvistTil{radiologisk, klinisk}
	public enum HospitalOenske{hillerød, frederikssund};
	public enum Prioritering{haste, rutine, fremskyndet, pakkeforloeb};
	public enum AmbulantKoersel{ingen, siddende, liggende};
	public enum IndlaeggelseTransport{gaaMedPortoer, gaaUdenPortoer, koerestol, seng};
	public enum SaerligeForhold{hoerehaemmet, synshaemmet, amputeret, kanIkkeStaa, dement, afasi, isolation, ilt, tolk, sytostatikaDato};
	public enum Undersoegelse{PET_CT, CT, CTKontrast, ultralyd, mr, other};
	
	
	private HenvistTil henvistTil;
	private HospitalOenske hospitalOenske;
	private Prioritering prioritering;
	private boolean patientSamtykke;
	private boolean ambulant;
	private String datoForslag; // skal typecheckes for korrekt dato input
	private AmbulantKoersel ambulantKoersel;
	private boolean indlaeggelse;
	private IndlaeggelseTransport indlaeggelseTransport;
	private boolean pregnancy; 
	private String cave;
	private ArrayList<SaerligeForhold> saerligeForhold;
	private String ilt;
	private String isolation;
	private String cytostatikaDato;
	private String tolk;
	private Undersoegelse undersoegelse;
	private String tidlBilleddiagnostiskUndersoegelseLokalt;
	private String relevantUndersoegelse;
	private String kliniskProblemstilling;
	private String triage;
	
	
	
	
	
	
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
