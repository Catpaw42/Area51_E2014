/*
 * This file was generated - do not edit it directly !!
 * Generated by AuDAO tool, a product of Spolecne s.r.o.
 * For more information please visit http://www.spoledge.com
 */
package database.dto;

import java.sql.Timestamp;

import com.spoledge.audao.db.dto.AbstractDto;

/**
 * This is a DTO class.
 *
 * @author generated, Rúni
 */
public class RekvisitionExtended extends AbstractDto implements Cloneable{

    public enum HenvistTil {
        RADIOLOGISK,
        KLINISK
    }

    public enum HospitalOenske {
        HILLEROED,
        FREDERIKSSUND,
        HELSINGOER
    }

    public enum Prioritering {
        HASTE,
        RUTINE,
        FREMSKYNDET,
        PAKKEFORLOEB
    }

    public enum AmbulantKoersel {
        INGEN,
        SIDDENDE,
        LIGGENDE
    }

    public enum IndlaeggelseTransport {
        GAA_UDEN_PORTOER,
        GAA_MED_PORTOER,
        KOERESTOL,
        SENG
    }

    public enum Status {
        PENDING,
        CANCELED,
        APPROVED,
        DECLINED,
        BOOKED
    }

    public enum Samtykke {
        JA,
        NEJ,
        UDEN_SAMTYKKE
    }

    ////////////////////////////////////////////////////////////////////////////
    // Static
    ////////////////////////////////////////////////////////////////////////////

    public static final String TABLE = "rekvisition";

	

    ////////////////////////////////////////////////////////////////////////////
    // Attributes
    ////////////////////////////////////////////////////////////////////////////
    
	private MRKontrolskema mrKontrolskema;
	private PETCTKontrolskema petctKontrolskema;
	private CtKontrastKontrolskema ctKontrastKontrolskema;
	private UlInvKontrolskema ulInvKontrolskema;
	private UndersoegelsesType undersoegelsesType;
	private Bruger rekvirent;
	private Bruger visitator;
	private Patient patient;
	private Modalitet modalitet;

    private Integer rekvisitionId;
    private Integer mRKontrolskemaId;
    private Integer pETCTKontrolskemaId;
    private Integer cTKontrastKontrolskemaId;
    private Integer invasivULKontrolskemaId;
    private Integer undersoegelsesTypeId;
    private Integer rekvirentId;
    private Integer visitatorId;
    private Integer patientId;
    private RekvisitionExtended.HenvistTil henvistTil;
    private RekvisitionExtended.HospitalOenske hospitalOenske;
    private RekvisitionExtended.Prioritering prioritering;
    private Boolean udfIndlagt;
    private RekvisitionExtended.AmbulantKoersel ambulantKoersel;
    private RekvisitionExtended.IndlaeggelseTransport indlaeggelseTransport;
    private RekvisitionExtended.Status status;
    private RekvisitionExtended.Samtykke samtykke;
    private String paaroerende;
    private Boolean ambulant;
    private String datoForslag;
    private Boolean graviditet;
    private Integer graviditetUge;
    private String cave;
    private Boolean hoerehaemmet;
    private Boolean synshaemmet;
    private Boolean amputeret;
    private Boolean kanIkkeStaa;
    private Short iltLiterPrmin;
    private String tolkSprog;
    private Boolean dement;
    private Boolean afasi;
    private String isolation;
    private Timestamp cytostatikaDato;
    private String tidlBilledDiagnostik;
    private String kliniskProblemstilling;
    private String triage;
    private String henvLaege;
    private String henvAfd;
    private String kontaktTlf;
    private String visitatorPrioritering;
    private String visitatorBemaerkning;
    private Timestamp afsendtDato;

    private boolean isMRKontrolskemaIdModified;
    private boolean isPETCTKontrolskemaIdModified;
    private boolean isCTKontrastKontrolskemaIdModified;
    private boolean isInvasivULKontrolskemaIdModified;
    private boolean isVisitatorIdModified;
    private boolean isHospitalOenskeModified;
    private boolean isAmbulantKoerselModified;
    private boolean isIndlaeggelseTransportModified;
    private boolean isSamtykkeModified;
    private boolean isDatoForslagModified;
    private boolean isGraviditetModified;
    private boolean isGraviditetUgeModified;
    private boolean isIltLiterPrminModified;
    private boolean isTolkSprogModified;
    private boolean isCytostatikaDatoModified;
    private boolean isTidlBilledDiagnostikModified;
    private boolean isTriageModified;
    private boolean isVisitatorPrioriteringModified;
    private boolean isVisitatorBemaerkningModified;

    ////////////////////////////////////////////////////////////////////////////
    // Constructors
    ////////////////////////////////////////////////////////////////////////////

    /**
     * Creates a new empty DTO.
     */
    public RekvisitionExtended() {
    	super();
    }

    ////////////////////////////////////////////////////////////////////////////
    // Public
    ////////////////////////////////////////////////////////////////////////////

    

	public Integer getRekvisitionId() {
        return rekvisitionId;
    }

    public void setRekvisitionId( Integer _val) {
        this.rekvisitionId = _val;
    }

    public Integer getMRKontrolskemaId() {
        return mRKontrolskemaId;
    }

    public void setMRKontrolskemaId( Integer _val) {
        this.mRKontrolskemaId = _val;
        this.isMRKontrolskemaIdModified = true;
    }

    public boolean isMRKontrolskemaIdModified() {
        return isMRKontrolskemaIdModified;
    }

    public Integer getPETCTKontrolskemaId() {
        return pETCTKontrolskemaId;
    }

    public void setPETCTKontrolskemaId( Integer _val) {
        this.pETCTKontrolskemaId = _val;
        this.isPETCTKontrolskemaIdModified = true;
    }

    public boolean isPETCTKontrolskemaIdModified() {
        return isPETCTKontrolskemaIdModified;
    }

    public Integer getCTKontrastKontrolskemaId() {
        return cTKontrastKontrolskemaId;
    }

    public void setCTKontrastKontrolskemaId( Integer _val) {
        this.cTKontrastKontrolskemaId = _val;
        this.isCTKontrastKontrolskemaIdModified = true;
    }

    public boolean isCTKontrastKontrolskemaIdModified() {
        return isCTKontrastKontrolskemaIdModified;
    }

    public Integer getInvasivULKontrolskemaId() {
        return invasivULKontrolskemaId;
    }

    public void setInvasivULKontrolskemaId( Integer _val) {
        this.invasivULKontrolskemaId = _val;
        this.isInvasivULKontrolskemaIdModified = true;
    }

    public boolean isInvasivULKontrolskemaIdModified() {
        return isInvasivULKontrolskemaIdModified;
    }

    public Integer getUndersoegelsesTypeId() {
        return undersoegelsesTypeId;
    }

    public void setUndersoegelsesTypeId( Integer _val) {
        this.undersoegelsesTypeId = _val;
    }

    public Integer getRekvirentId() {
        return rekvirentId;
    }

    public void setRekvirentId( Integer _val) {
        this.rekvirentId = _val;
    }

    public Integer getVisitatorId() {
        return visitatorId;
    }

    public void setVisitatorId( Integer _val) {
        this.visitatorId = _val;
        this.isVisitatorIdModified = true;
    }
    
    public boolean isVisitatorIdModified(){
    	return this.isVisitatorIdModified;
    }

    public Integer getPatientId() {
        return patientId;
    }

    public void setPatientId( Integer _val) {
        this.patientId = _val;
    }

    public RekvisitionExtended.HenvistTil getHenvistTil() {
        return henvistTil;
    }

    public void setHenvistTil( RekvisitionExtended.HenvistTil _val) {
        this.henvistTil = _val;
    }

    public RekvisitionExtended.HospitalOenske getHospitalOenske() {
        return hospitalOenske;
    }

    public void setHospitalOenske( RekvisitionExtended.HospitalOenske _val) {
        this.hospitalOenske = _val;
        this.isHospitalOenskeModified = true;
    }

    public boolean isHospitalOenskeModified() {
        return isHospitalOenskeModified;
    }

    public RekvisitionExtended.Prioritering getPrioritering() {
        return prioritering;
    }

    public void setPrioritering( RekvisitionExtended.Prioritering _val) {
        this.prioritering = _val;
    }

    public Boolean getUdfIndlagt() {
        return udfIndlagt;
    }

    public void setUdfIndlagt( Boolean _val) {
        this.udfIndlagt = _val;
    }

    public RekvisitionExtended.AmbulantKoersel getAmbulantKoersel() {
        return ambulantKoersel;
    }

    public void setAmbulantKoersel( RekvisitionExtended.AmbulantKoersel _val) {
        this.ambulantKoersel = _val;
        this.isAmbulantKoerselModified = true;
    }

    public boolean isAmbulantKoerselModified() {
        return isAmbulantKoerselModified;
    }

    public RekvisitionExtended.IndlaeggelseTransport getIndlaeggelseTransport() {
        return indlaeggelseTransport;
    }

    public void setIndlaeggelseTransport( RekvisitionExtended.IndlaeggelseTransport _val) {
        this.indlaeggelseTransport = _val;
        this.isIndlaeggelseTransportModified = true;
    }

    public boolean isIndlaeggelseTransportModified() {
        return isIndlaeggelseTransportModified;
    }

    public RekvisitionExtended.Status getStatus() {
        return status;
    }

    public void setStatus( RekvisitionExtended.Status _val) {
        this.status = _val;
    }

    public RekvisitionExtended.Samtykke getSamtykke() {
        return samtykke;
    }

    public void setSamtykke( RekvisitionExtended.Samtykke _val) {
        this.samtykke = _val;
        this.isSamtykkeModified = true;
    }

    public boolean isSamtykkeModified() {
        return isSamtykkeModified;
    }

    public String getPaaroerende() {
        return paaroerende;
    }

    public void setPaaroerende( String _val) {
        this.paaroerende = _val;
    }

    public Boolean getAmbulant() {
        return ambulant;
    }

    public void setAmbulant( Boolean _val) {
        this.ambulant = _val;
    }

    public String getDatoForslag() {
        return datoForslag;
    }

    public void setDatoForslag( String _val) {
        this.datoForslag = _val;
        this.isDatoForslagModified = true;
    }

    public boolean isDatoForslagModified() {
        return isDatoForslagModified;
    }

    public Boolean getGraviditet() {
        return graviditet;
    }

    public void setGraviditet( Boolean _val) {
        this.graviditet = _val;
        this.isGraviditetModified = true;
    }

    public boolean isGraviditetModified() {
        return isGraviditetModified;
    }

    public Integer getGraviditetUge() {
        return graviditetUge;
    }

    public void setGraviditetUge( Integer _val) {
        this.graviditetUge = _val;
        this.isGraviditetUgeModified = true;
    }

    public boolean isGraviditetUgeModified() {
        return isGraviditetUgeModified;
    }

    public String getCave() {
        return cave;
    }

    public void setCave( String _val) {
        this.cave = _val;
    }

    public Boolean getHoerehaemmet() {
        return hoerehaemmet;
    }

    public void setHoerehaemmet( Boolean _val) {
        this.hoerehaemmet = _val;
    }

    public Boolean getSynshaemmet() {
        return synshaemmet;
    }

    public void setSynshaemmet( Boolean _val) {
        this.synshaemmet = _val;
    }

    public Boolean getAmputeret() {
        return amputeret;
    }

    public void setAmputeret( Boolean _val) {
        this.amputeret = _val;
    }

    public Boolean getKanIkkeStaa() {
        return kanIkkeStaa;
    }

    public void setKanIkkeStaa( Boolean _val) {
        this.kanIkkeStaa = _val;
    }

    public Short getIltLiterPrmin() {
        return iltLiterPrmin;
    }

    public void setIltLiterPrmin( int _val ) {
        setIltLiterPrmin( new Short((short) _val ));
    }

    public void setIltLiterPrmin( Short _val) {
        this.iltLiterPrmin = _val;
        this.isIltLiterPrminModified = true;
    }

    public boolean isIltLiterPrminModified() {
        return isIltLiterPrminModified;
    }

    public String getTolkSprog() {
        return tolkSprog;
    }

    public void setTolkSprog( String _val) {
        this.tolkSprog = _val;
        this.isTolkSprogModified = true;
    }

    public boolean isTolkSprogModified() {
        return isTolkSprogModified;
    }

    public Boolean getDement() {
        return dement;
    }

    public void setDement( Boolean _val) {
        this.dement = _val;
    }

    public Boolean getAfasi() {
        return afasi;
    }

    public void setAfasi( Boolean _val) {
        this.afasi = _val;
    }

    public String getIsolation() {
        return isolation;
    }

    public void setIsolation( String _val) {
        this.isolation = _val;
    }

    public Timestamp getCytostatikaDato() {
        return cytostatikaDato;
    }

    public void setCytostatikaDato( java.util.Date _val ) {
        setCytostatikaDato((Timestamp)( _val != null ? new Timestamp( _val.getTime()) : null ));
    }

    public void setCytostatikaDato( Timestamp _val) {
        this.cytostatikaDato = _val;
        this.isCytostatikaDatoModified = true;
    }

    public boolean isCytostatikaDatoModified() {
        return isCytostatikaDatoModified;
    }

    public String getTidlBilledDiagnostik() {
        return tidlBilledDiagnostik;
    }

    public void setTidlBilledDiagnostik( String _val) {
        this.tidlBilledDiagnostik = _val;
        this.isTidlBilledDiagnostikModified = true;
    }

    public boolean isTidlBilledDiagnostikModified() {
        return isTidlBilledDiagnostikModified;
    }

    public String getKliniskProblemstilling() {
        return kliniskProblemstilling;
    }

    public void setKliniskProblemstilling( String _val) {
        this.kliniskProblemstilling = _val;
    }

    public String getTriage() {
        return triage;
    }

    public void setTriage( String _val) {
        this.triage = _val;
        this.isTriageModified = true;
    }

    public boolean isTriageModified() {
        return isTriageModified;
    }

    public String getHenvLaege() {
        return henvLaege;
    }

    public void setHenvLaege( String _val) {
        this.henvLaege = _val;
    }

    public String getHenvAfd() {
        return henvAfd;
    }

    public void setHenvAfd( String _val) {
        this.henvAfd = _val;
    }

    public String getKontaktTlf() {
        return kontaktTlf;
    }

    public void setKontaktTlf( String _val) {
        this.kontaktTlf = _val;
    }

    public String getVisitatorPrioritering() {
        return visitatorPrioritering;
    }

    public void setVisitatorPrioritering( String _val) {
        this.visitatorPrioritering = _val;
        this.isVisitatorPrioriteringModified = true;
    }

    public boolean isVisitatorPrioriteringModified() {
        return isVisitatorPrioriteringModified;
    }

    public String getVisitatorBemaerkning() {
        return visitatorBemaerkning;
    }

    public void setVisitatorBemaerkning( String _val) {
        this.visitatorBemaerkning = _val;
        this.isVisitatorBemaerkningModified = true;
    }

    public boolean isVisitatorBemaerkningModified() {
        return isVisitatorBemaerkningModified;
    }

    public Timestamp getAfsendtDato() {
        return afsendtDato;
    }

    public void setAfsendtDato( java.util.Date _val ) {
        setAfsendtDato((Timestamp)( _val != null ? new Timestamp( _val.getTime()) : null ));
    }

    public void setAfsendtDato( Timestamp _val) {
        this.afsendtDato = _val;
    }

    /**
     * Indicates whether some other object is "equal to" this one.
     * Uses 'columns' equality type.
     */
    @Override
    public boolean equals( Object _other ) {
        if (_other == this) return true;
        if (_other == null || (!(_other instanceof RekvisitionExtended))) return false;

        RekvisitionExtended _o = (RekvisitionExtended) _other;

        if ( rekvisitionId == null ) {
            if ( _o.rekvisitionId != null ) return false;
        }
        else if ( _o.rekvisitionId == null || rekvisitionId.intValue() != _o.rekvisitionId.intValue()) return false;

        if ( mRKontrolskemaId == null ) {
            if ( _o.mRKontrolskemaId != null ) return false;
        }
        else if ( _o.mRKontrolskemaId == null || mRKontrolskemaId.intValue() != _o.mRKontrolskemaId.intValue()) return false;

        if ( pETCTKontrolskemaId == null ) {
            if ( _o.pETCTKontrolskemaId != null ) return false;
        }
        else if ( _o.pETCTKontrolskemaId == null || pETCTKontrolskemaId.intValue() != _o.pETCTKontrolskemaId.intValue()) return false;

        if ( cTKontrastKontrolskemaId == null ) {
            if ( _o.cTKontrastKontrolskemaId != null ) return false;
        }
        else if ( _o.cTKontrastKontrolskemaId == null || cTKontrastKontrolskemaId.intValue() != _o.cTKontrastKontrolskemaId.intValue()) return false;

        if ( invasivULKontrolskemaId == null ) {
            if ( _o.invasivULKontrolskemaId != null ) return false;
        }
        else if ( _o.invasivULKontrolskemaId == null || invasivULKontrolskemaId.intValue() != _o.invasivULKontrolskemaId.intValue()) return false;

        if ( undersoegelsesTypeId == null ) {
            if ( _o.undersoegelsesTypeId != null ) return false;
        }
        else if ( _o.undersoegelsesTypeId == null || undersoegelsesTypeId.intValue() != _o.undersoegelsesTypeId.intValue()) return false;

        if ( rekvirentId == null ) {
            if ( _o.rekvirentId != null ) return false;
        }
        else if ( _o.rekvirentId == null || rekvirentId.intValue() != _o.rekvirentId.intValue()) return false;

        if ( visitatorId == null ) {
            if ( _o.visitatorId != null ) return false;
        }
        else if ( _o.visitatorId == null || visitatorId.intValue() != _o.visitatorId.intValue()) return false;

        if ( patientId == null ) {
            if ( _o.patientId != null ) return false;
        }
        else if ( _o.patientId == null || patientId.intValue() != _o.patientId.intValue()) return false;

        if ( henvistTil != _o.henvistTil ) return false;

        if ( hospitalOenske != _o.hospitalOenske ) return false;

        if ( prioritering != _o.prioritering ) return false;

        if ( udfIndlagt == null ) {
            if ( _o.udfIndlagt != null ) return false;
        }
        else if ( _o.udfIndlagt == null || udfIndlagt.booleanValue() != _o.udfIndlagt.booleanValue()) return false;

        if ( ambulantKoersel != _o.ambulantKoersel ) return false;

        if ( indlaeggelseTransport != _o.indlaeggelseTransport ) return false;

        if ( status != _o.status ) return false;

        if ( samtykke != _o.samtykke ) return false;

        if ( paaroerende == null ) {
            if ( _o.paaroerende != null ) return false;
        }
        else if ( _o.paaroerende == null || !paaroerende.equals( _o.paaroerende )) return false;

        if ( ambulant == null ) {
            if ( _o.ambulant != null ) return false;
        }
        else if ( _o.ambulant == null || ambulant.booleanValue() != _o.ambulant.booleanValue()) return false;

        if ( datoForslag == null ) {
            if ( _o.datoForslag != null ) return false;
        }
        else if ( _o.datoForslag == null || !datoForslag.equals( _o.datoForslag )) return false;

        if ( graviditet == null ) {
            if ( _o.graviditet != null ) return false;
        }
        else if ( _o.graviditet == null || graviditet.booleanValue() != _o.graviditet.booleanValue()) return false;

        if ( graviditetUge == null ) {
            if ( _o.graviditetUge != null ) return false;
        }
        else if ( _o.graviditetUge == null || graviditetUge.intValue() != _o.graviditetUge.intValue()) return false;

        if ( cave == null ) {
            if ( _o.cave != null ) return false;
        }
        else if ( _o.cave == null || !cave.equals( _o.cave )) return false;

        if ( hoerehaemmet == null ) {
            if ( _o.hoerehaemmet != null ) return false;
        }
        else if ( _o.hoerehaemmet == null || hoerehaemmet.booleanValue() != _o.hoerehaemmet.booleanValue()) return false;

        if ( synshaemmet == null ) {
            if ( _o.synshaemmet != null ) return false;
        }
        else if ( _o.synshaemmet == null || synshaemmet.booleanValue() != _o.synshaemmet.booleanValue()) return false;

        if ( amputeret == null ) {
            if ( _o.amputeret != null ) return false;
        }
        else if ( _o.amputeret == null || amputeret.booleanValue() != _o.amputeret.booleanValue()) return false;

        if ( kanIkkeStaa == null ) {
            if ( _o.kanIkkeStaa != null ) return false;
        }
        else if ( _o.kanIkkeStaa == null || kanIkkeStaa.booleanValue() != _o.kanIkkeStaa.booleanValue()) return false;

        if ( iltLiterPrmin == null ) {
            if ( _o.iltLiterPrmin != null ) return false;
        }
        else if ( _o.iltLiterPrmin == null || iltLiterPrmin.shortValue() != _o.iltLiterPrmin.shortValue()) return false;

        if ( tolkSprog == null ) {
            if ( _o.tolkSprog != null ) return false;
        }
        else if ( _o.tolkSprog == null || !tolkSprog.equals( _o.tolkSprog )) return false;

        if ( dement == null ) {
            if ( _o.dement != null ) return false;
        }
        else if ( _o.dement == null || dement.booleanValue() != _o.dement.booleanValue()) return false;

        if ( afasi == null ) {
            if ( _o.afasi != null ) return false;
        }
        else if ( _o.afasi == null || afasi.booleanValue() != _o.afasi.booleanValue()) return false;

        if ( isolation == null ) {
            if ( _o.isolation != null ) return false;
        }
        else if ( _o.isolation == null || !isolation.equals( _o.isolation )) return false;

        if ( cytostatikaDato == null ) {
            if ( _o.cytostatikaDato != null ) return false;
        }
        else if ( _o.cytostatikaDato == null || cytostatikaDato.getTime() != _o.cytostatikaDato.getTime()) return false;

        if ( tidlBilledDiagnostik == null ) {
            if ( _o.tidlBilledDiagnostik != null ) return false;
        }
        else if ( _o.tidlBilledDiagnostik == null || !tidlBilledDiagnostik.equals( _o.tidlBilledDiagnostik )) return false;

        if ( kliniskProblemstilling == null ) {
            if ( _o.kliniskProblemstilling != null ) return false;
        }
        else if ( _o.kliniskProblemstilling == null || !kliniskProblemstilling.equals( _o.kliniskProblemstilling )) return false;

        if ( triage == null ) {
            if ( _o.triage != null ) return false;
        }
        else if ( _o.triage == null || !triage.equals( _o.triage )) return false;

        if ( henvLaege == null ) {
            if ( _o.henvLaege != null ) return false;
        }
        else if ( _o.henvLaege == null || !henvLaege.equals( _o.henvLaege )) return false;

        if ( henvAfd == null ) {
            if ( _o.henvAfd != null ) return false;
        }
        else if ( _o.henvAfd == null || !henvAfd.equals( _o.henvAfd )) return false;

        if ( kontaktTlf == null ) {
            if ( _o.kontaktTlf != null ) return false;
        }
        else if ( _o.kontaktTlf == null || !kontaktTlf.equals( _o.kontaktTlf )) return false;

        if ( visitatorPrioritering == null ) {
            if ( _o.visitatorPrioritering != null ) return false;
        }
        else if ( _o.visitatorPrioritering == null || !visitatorPrioritering.equals( _o.visitatorPrioritering )) return false;

        if ( visitatorBemaerkning == null ) {
            if ( _o.visitatorBemaerkning != null ) return false;
        }
        else if ( _o.visitatorBemaerkning == null || !visitatorBemaerkning.equals( _o.visitatorBemaerkning )) return false;

        if ( afsendtDato == null ) {
            if ( _o.afsendtDato != null ) return false;
        }
        else if ( _o.afsendtDato == null || afsendtDato.getTime() != _o.afsendtDato.getTime()) return false;

        return true;
    }

    /**
     * Returns a hash code value for the object.
     */
    @Override
    public int hashCode() {
        int _ret = 1766427477; // = "Rekvisition".hashCode()
        _ret += rekvisitionId == null ? 0 : rekvisitionId;
        _ret = 29 * _ret + (mRKontrolskemaId == null ? 0 : mRKontrolskemaId);
        _ret = 29 * _ret + (pETCTKontrolskemaId == null ? 0 : pETCTKontrolskemaId);
        _ret = 29 * _ret + (cTKontrastKontrolskemaId == null ? 0 : cTKontrastKontrolskemaId);
        _ret = 29 * _ret + (invasivULKontrolskemaId == null ? 0 : invasivULKontrolskemaId);
        _ret = 29 * _ret + (undersoegelsesTypeId == null ? 0 : undersoegelsesTypeId);
        _ret = 29 * _ret + (rekvirentId == null ? 0 : rekvirentId);
        _ret = 29 * _ret + (visitatorId == null ? 0 : visitatorId);
        _ret = 29 * _ret + (patientId == null ? 0 : patientId);
        _ret = 29 * _ret + (henvistTil == null ? 0 : henvistTil.hashCode());
        _ret = 29 * _ret + (hospitalOenske == null ? 0 : hospitalOenske.hashCode());
        _ret = 29 * _ret + (prioritering == null ? 0 : prioritering.hashCode());
        _ret = 29 * _ret + (udfIndlagt == null ? 0 : (udfIndlagt ? 1 : 0));
        _ret = 29 * _ret + (ambulantKoersel == null ? 0 : ambulantKoersel.hashCode());
        _ret = 29 * _ret + (indlaeggelseTransport == null ? 0 : indlaeggelseTransport.hashCode());
        _ret = 29 * _ret + (status == null ? 0 : status.hashCode());
        _ret = 29 * _ret + (samtykke == null ? 0 : samtykke.hashCode());
        _ret = 29 * _ret + (paaroerende == null ? 0 : paaroerende.hashCode());
        _ret = 29 * _ret + (ambulant == null ? 0 : (ambulant ? 1 : 0));
        _ret = 29 * _ret + (datoForslag == null ? 0 : datoForslag.hashCode());
        _ret = 29 * _ret + (graviditet == null ? 0 : (graviditet ? 1 : 0));
        _ret = 29 * _ret + (graviditetUge == null ? 0 : graviditetUge);
        _ret = 29 * _ret + (cave == null ? 0 : cave.hashCode());
        _ret = 29 * _ret + (hoerehaemmet == null ? 0 : (hoerehaemmet ? 1 : 0));
        _ret = 29 * _ret + (synshaemmet == null ? 0 : (synshaemmet ? 1 : 0));
        _ret = 29 * _ret + (amputeret == null ? 0 : (amputeret ? 1 : 0));
        _ret = 29 * _ret + (kanIkkeStaa == null ? 0 : (kanIkkeStaa ? 1 : 0));
        _ret = 29 * _ret + (iltLiterPrmin == null ? 0 : iltLiterPrmin);
        _ret = 29 * _ret + (tolkSprog == null ? 0 : tolkSprog.hashCode());
        _ret = 29 * _ret + (dement == null ? 0 : (dement ? 1 : 0));
        _ret = 29 * _ret + (afasi == null ? 0 : (afasi ? 1 : 0));
        _ret = 29 * _ret + (isolation == null ? 0 : isolation.hashCode());
        _ret = 29 * _ret + (cytostatikaDato == null ? 0 : (int)cytostatikaDato.getTime());
        _ret = 29 * _ret + (tidlBilledDiagnostik == null ? 0 : tidlBilledDiagnostik.hashCode());
        _ret = 29 * _ret + (kliniskProblemstilling == null ? 0 : kliniskProblemstilling.hashCode());
        _ret = 29 * _ret + (triage == null ? 0 : triage.hashCode());
        _ret = 29 * _ret + (henvLaege == null ? 0 : henvLaege.hashCode());
        _ret = 29 * _ret + (henvAfd == null ? 0 : henvAfd.hashCode());
        _ret = 29 * _ret + (kontaktTlf == null ? 0 : kontaktTlf.hashCode());
        _ret = 29 * _ret + (visitatorPrioritering == null ? 0 : visitatorPrioritering.hashCode());
        _ret = 29 * _ret + (visitatorBemaerkning == null ? 0 : visitatorBemaerkning.hashCode());
        _ret = 29 * _ret + (afsendtDato == null ? 0 : (int)afsendtDato.getTime());

        return _ret;
    }


    ////////////////////////////////////////////////////////////////////////////
    // Protected
    ////////////////////////////////////////////////////////////////////////////
		
    /**
     * Constructs the content for the toString() method.
     */
    protected void contentToString(StringBuffer sb) {
        append( sb, "rekvisitionId", rekvisitionId );
        append( sb, "mRKontrolskemaId", mRKontrolskemaId );
        append( sb, "pETCTKontrolskemaId", pETCTKontrolskemaId );
        append( sb, "cTKontrastKontrolskemaId", cTKontrastKontrolskemaId );
        append( sb, "invasivULKontrolskemaId", invasivULKontrolskemaId );
        append( sb, "undersoegelsesTypeId", undersoegelsesTypeId );
        append( sb, "rekvirentId", rekvirentId );
        append( sb, "visitatorId", visitatorId );
        append( sb, "patientId", patientId );
        append( sb, "henvistTil", henvistTil );
        append( sb, "hospitalOenske", hospitalOenske );
        append( sb, "prioritering", prioritering );
        append( sb, "udfIndlagt", udfIndlagt );
        append( sb, "ambulantKoersel", ambulantKoersel );
        append( sb, "indlaeggelseTransport", indlaeggelseTransport );
        append( sb, "status", status );
        append( sb, "samtykke", samtykke );
        append( sb, "paaroerende", paaroerende );
        append( sb, "ambulant", ambulant );
        append( sb, "datoForslag", datoForslag );
        append( sb, "graviditet", graviditet );
        append( sb, "graviditetUge", graviditetUge );
        append( sb, "cave", cave );
        append( sb, "hoerehaemmet", hoerehaemmet );
        append( sb, "synshaemmet", synshaemmet );
        append( sb, "amputeret", amputeret );
        append( sb, "kanIkkeStaa", kanIkkeStaa );
        append( sb, "iltLiterPrmin", iltLiterPrmin );
        append( sb, "tolkSprog", tolkSprog );
        append( sb, "dement", dement );
        append( sb, "afasi", afasi );
        append( sb, "isolation", isolation );
        append( sb, "cytostatikaDato", cytostatikaDato );
        append( sb, "tidlBilledDiagnostik", tidlBilledDiagnostik );
        append( sb, "kliniskProblemstilling", kliniskProblemstilling );
        append( sb, "triage", triage );
        append( sb, "henvLaege", henvLaege );
        append( sb, "henvAfd", henvAfd );
        append( sb, "kontaktTlf", kontaktTlf );
        append( sb, "visitatorPrioritering", visitatorPrioritering );
        append( sb, "visitatorBemaerkning", visitatorBemaerkning );
        append( sb, "afsendtDato", afsendtDato );
    }
    
	public MRKontrolskema getMRKontrolskema() {
		return mrKontrolskema;
	}
	public void setMRKontrolskema(MRKontrolskema mrKontrolskema) {
		this.mrKontrolskema = mrKontrolskema;
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

	public UlInvKontrolskema getUlInvKontrolskema() {
		return ulInvKontrolskema;
	}

	public void setUlInvKontrolskema(UlInvKontrolskema ulInvKontrolskema) {
		this.ulInvKontrolskema = ulInvKontrolskema;
	}
    
}
