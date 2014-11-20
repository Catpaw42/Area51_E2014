package database.dto;

import java.sql.Timestamp;

public class RekvisitionExtended1 extends RekvisitionExtended {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private MRKontrolskema mrMkontroKontrolskema;
	private PETCTKontrolskema petctKontrolskema;
	private CtKontrastKontrolskema ctKontrastKontrolskema;
//	private invasiv_UL_kontrolskema ??
	private UndersoegelsesType undersoegelsesType;
	private Bruger rekvirent;
	private Bruger visitator;
	private Patient patient;
	private Modalitet modalitet;
	
	public RekvisitionExtended1(){
		
	}
	public RekvisitionExtended1(Integer rekvisitionId, Integer mRKontrolskemaId,
			Integer pETCTKontrolskemaId, Integer cTKontrastKontrolskemaId,
			Integer invasivULKontrolskemaId, Integer undersoegelsesTypeId,
			Integer rekvirentId, Integer visitatorId, Integer patientId,
			HenvistTil henvistTil, HospitalOenske hospitalOenske,
			Prioritering prioritering, Boolean udfIndlagt,
			AmbulantKoersel ambulantKoersel,
			IndlaeggelseTransport indlaeggelseTransport, Status status,
			Samtykke samtykke, String paaroerende, Boolean ambulant,
			String datoForslag, Boolean graviditet, Integer graviditetUge,
			String cave, Boolean hoerehaemmet, Boolean synshaemmet,
			Boolean amputeret, Boolean kanIkkeStaa, Short iltLiterPrmin,
			String tolkSprog, Boolean dement, Boolean afasi, String isolation,
			Timestamp cytostatikaDato, String tidlBilledDiagnostik,
			String kliniskProblemstilling, String triage, String henvLaege,
			String henvAfd, String kontaktTlf, String visitatorPrioritering,
			String visitatorBemaerkning, Timestamp afsendtDato,
			boolean isMRKontrolskemaIdModified,
			boolean isPETCTKontrolskemaIdModified,
			boolean isCTKontrastKontrolskemaIdModified,
			boolean isInvasivULKontrolskemaIdModified,
			boolean isHospitalOenskeModified,
			boolean isAmbulantKoerselModified,
			boolean isIndlaeggelseTransportModified,
			boolean isSamtykkeModified, boolean isDatoForslagModified,
			boolean isGraviditetModified, boolean isGraviditetUgeModified,
			boolean isIltLiterPrminModified, boolean isTolkSprogModified,
			boolean isCytostatikaDatoModified,
			boolean isTidlBilledDiagnostikModified, boolean isTriageModified,
			boolean isVisitatorPrioriteringModified,
			boolean isVisitatorBemaerkningModified) {
		

	}
	public MRKontrolskema getMRKontrolskema() {
		return mrMkontroKontrolskema;
	}
	public void setMRKontrolskema(MRKontrolskema mrMkontroKontrolskema) {
		this.mrMkontroKontrolskema = mrMkontroKontrolskema;
	}
	public PETCTKontrolskema getPetctKontrolskema() {
		return petctKontrolskema;
	}
	public void setPetctKontrolskema(PETCTKontrolskema petctKontrolskema) {
		this.petctKontrolskema = petctKontrolskema;
	}
	public CtKontrastKontrolskema getCtKontrastKontrolskema() {
		return ctKontrastKontrolskema;
	}
	public void setCtKontrastKontrolskema(
			CtKontrastKontrolskema ctKontrastKontrolskema) {
		this.ctKontrastKontrolskema = ctKontrastKontrolskema;
	}
	public UndersoegelsesType getUndersoegelsesType() {
		return undersoegelsesType;
	}
	public void setUndersoegelsesType(UndersoegelsesType undersoegelsesType) {
		this.undersoegelsesType = undersoegelsesType;
	}
	public Bruger getRekvirent() {
		return rekvirent;
	}
	public void setRekvirent(Bruger rekvirent) {
		this.rekvirent = rekvirent;
	}
	public Bruger getVisitator() {
		return visitator;
	}
	public void setVisitator(Bruger visitator) {
		this.visitator = visitator;
	}
	public Patient getPatient() {
		return patient;
	}
	public void setPatient(Patient patient) {
		this.patient = patient;
	}
	public Modalitet getModalitet() {
		return modalitet;
	}
	public void setModalitet(Modalitet modalitet) {
		this.modalitet = modalitet;
	}
	
	

}
