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
 * @author generated
 */
public class PETCTKontrolskema extends AbstractDto {

	public enum Formaal {
		PRIMAERDIAG,
		KONTROLBEH,
		KONTROLREMISSION,
		KONTROLRECIDIV
	}

	public enum KemoOgStraale {
		NEJ,
		KEMOTERAPI,
		STRAALETERAPI,
		KEMO_OG_STRAALE
	}

	////////////////////////////////////////////////////////////////////////////
	// Static
	////////////////////////////////////////////////////////////////////////////

	public static final String TABLE = "PETCT_kontrolskema";

	////////////////////////////////////////////////////////////////////////////
	// Attributes
	////////////////////////////////////////////////////////////////////////////

	private Integer pETCTKontrolskemaId;
	private PETCTKontrolskema.Formaal formaal;
	private String formaalTekst;
	private Boolean kanPtLiggeStille30;
	private Boolean ptTaalerFaste;
	private Boolean diabetes;
	private String dMBeh;
	private Boolean smerter;
	private Boolean respInsuff;
	private Boolean klaustrofobi;
	private Boolean allergi;
	private String allergiTekst;
	private Boolean fedme;
	private Integer vaegt;
	private Boolean biopsi;
	private String biopsiTekst;
	private Boolean operation;
	private String operationTekst;
	private PETCTKontrolskema.KemoOgStraale kemoOgStraale;
	private Boolean kontrastReaktion;
	private String kontrastReaktionTekst;
	private Boolean nedsatNyreFkt;
	private Integer sidstePKreatinin;
	private Timestamp sidstePKreatTimestamp;
	private Timestamp straaleDato;



	private Boolean preMed;
	private Boolean dMRegime;
	private Boolean pOKontrast;
	private Boolean iVKontrast;
	private Integer aktuelPKreatinin;
	private Timestamp aktuelPKreatTimestamp;
	private String aktuelAndetTekst;

	////////////////////////////////////////////////////////////////////////////
	// Constructors
	////////////////////////////////////////////////////////////////////////////

	/**
	 * Creates a new empty DTO.
	 */
	public PETCTKontrolskema() {
	}

	////////////////////////////////////////////////////////////////////////////
	// Public
	////////////////////////////////////////////////////////////////////////////

	public Timestamp getStraaleDato() {
		return straaleDato;
	}

	public void setStraaleDato(Timestamp straaleDato) {
		this.straaleDato = straaleDato;
	}

	public void setStraaleDato(java.util.Date _val ) {
		setStraaleDato((Timestamp)( _val != null ? new Timestamp( _val.getTime()) : null ));
	}

	public Integer getPETCTKontrolskemaId() {
		return pETCTKontrolskemaId;
	}

	public void setPETCTKontrolskemaId( Integer _val) {
		this.pETCTKontrolskemaId = _val;
	}

	public PETCTKontrolskema.Formaal getFormaal() {
		return formaal;
	}

	public void setFormaal( PETCTKontrolskema.Formaal _val) {
		this.formaal = _val;
	}

	public String getFormaalTekst() {
		return formaalTekst;
	}

	public void setFormaalTekst( String _val) {
		this.formaalTekst = _val;
	}

	public Boolean getKanPtLiggeStille30() {
		return kanPtLiggeStille30;
	}

	public void setKanPtLiggeStille30( Boolean _val) {
		this.kanPtLiggeStille30 = _val;
	}

	public Boolean getPtTaalerFaste() {
		return ptTaalerFaste;
	}

	public void setPtTaalerFaste( Boolean _val) {
		this.ptTaalerFaste = _val;
	}

	public Boolean getDiabetes() {
		return diabetes;
	}

	public void setDiabetes( Boolean _val) {
		this.diabetes = _val;
	}

	public String getDMBeh() {
		return dMBeh;
	}

	public void setDMBeh( String _val) {
		this.dMBeh = _val;
	}

	public Boolean getSmerter() {
		return smerter;
	}

	public void setSmerter( Boolean _val) {
		this.smerter = _val;
	}

	public Boolean getRespInsuff() {
		return respInsuff;
	}

	public void setRespInsuff( Boolean _val) {
		this.respInsuff = _val;
	}

	public Boolean getKlaustrofobi() {
		return klaustrofobi;
	}

	public void setKlaustrofobi( Boolean _val) {
		this.klaustrofobi = _val;
	}

	public Boolean getAllergi() {
		return allergi;
	}

	public void setAllergi( Boolean _val) {
		this.allergi = _val;
	}

	public String getAllergiTekst() {
		return allergiTekst;
	}

	public void setAllergiTekst( String _val) {
		this.allergiTekst = _val;
	}

	public Boolean getFedme() {
		return fedme;
	}

	public void setFedme( Boolean _val) {
		this.fedme = _val;
	}

	public Integer getVaegt() {
		return vaegt;
	}

	public void setVaegt( Integer _val) {
		this.vaegt = _val;
	}

	public Boolean getBiopsi() {
		return biopsi;
	}

	public void setBiopsi( Boolean _val) {
		this.biopsi = _val;
	}

	public String getBiopsiTekst() {
		return biopsiTekst;
	}

	public void setBiopsiTekst( String _val) {
		this.biopsiTekst = _val;
	}

	public Boolean getOperation() {
		return operation;
	}

	public void setOperation( Boolean _val) {
		this.operation = _val;
	}

	public String getOperationTekst() {
		return operationTekst;
	}

	public void setOperationTekst( String _val) {
		this.operationTekst = _val;
	}

	public PETCTKontrolskema.KemoOgStraale getKemoOgStraale() {
		return kemoOgStraale;
	}

	public void setKemoOgStraale( PETCTKontrolskema.KemoOgStraale _val) {
		this.kemoOgStraale = _val;
	}

	public Boolean getKontrastReaktion() {
		return kontrastReaktion;
	}

	public void setKontrastReaktion( Boolean _val) {
		this.kontrastReaktion = _val;
	}

	public String getKontrastReaktionTekst() {
		return kontrastReaktionTekst;
	}

	public void setKontrastReaktionTekst( String _val) {
		this.kontrastReaktionTekst = _val;
	}

	public Boolean getNedsatNyreFkt() {
		return nedsatNyreFkt;
	}

	public void setNedsatNyreFkt( Boolean _val) {
		this.nedsatNyreFkt = _val;
	}

	public Integer getSidstePKreatinin() {
		return sidstePKreatinin;
	}

	public void setSidstePKreatinin( Integer _val) {
		this.sidstePKreatinin = _val;
	}

	public Timestamp getSidstePKreatTimestamp() {
		return sidstePKreatTimestamp;
	}

	public void setSidstePKreatTimestamp( java.util.Date _val ) {
		setSidstePKreatTimestamp((Timestamp)( _val != null ? new Timestamp( _val.getTime()) : null ));
	}

	public void setSidstePKreatTimestamp( Timestamp _val) {
		this.sidstePKreatTimestamp = _val;
	}

	public Boolean getPreMed() {
		return preMed;
	}

	public void setPreMed( Boolean _val) {
		this.preMed = _val;
	}

	public Boolean getDMRegime() {
		return dMRegime;
	}

	public void setDMRegime( Boolean _val) {
		this.dMRegime = _val;
	}

	public Boolean getPOKontrast() {
		return pOKontrast;
	}

	public void setPOKontrast( Boolean _val) {
		this.pOKontrast = _val;
	}

	public Boolean getIVKontrast() {
		return iVKontrast;
	}

	public void setIVKontrast( Boolean _val) {
		this.iVKontrast = _val;
	}

	public Integer getAktuelPKreatinin() {
		return aktuelPKreatinin;
	}

	public void setAktuelPKreatinin( Integer _val) {
		this.aktuelPKreatinin = _val;
	}

	public Timestamp getAktuelPKreatTimestamp() {
		return aktuelPKreatTimestamp;
	}

	public void setAktuelPKreatTimestamp( java.util.Date _val ) {
		setAktuelPKreatTimestamp((Timestamp)( _val != null ? new Timestamp( _val.getTime()) : null ));
	}

	public void setAktuelPKreatTimestamp( Timestamp _val) {
		this.aktuelPKreatTimestamp = _val;
	}

	public String getAktuelAndetTekst() {
		return aktuelAndetTekst;
	}

	public void setAktuelAndetTekst( String _val) {
		this.aktuelAndetTekst = _val;
	}

	/**
	 * Indicates whether some other object is "equal to" this one.
	 * Uses 'columns' equality type.
	 */
	 @Override
	 public boolean equals( Object _other ) {
		if (_other == this) return true;
		if (_other == null || (!(_other instanceof PETCTKontrolskema))) return false;

		PETCTKontrolskema _o = (PETCTKontrolskema) _other;

		if ( pETCTKontrolskemaId == null ) {
			if ( _o.pETCTKontrolskemaId != null ) return false;
		}
		else if ( _o.pETCTKontrolskemaId == null || pETCTKontrolskemaId.intValue() != _o.pETCTKontrolskemaId.intValue()) return false;

		if ( formaal != _o.formaal ) return false;

		if ( formaalTekst == null ) {
			if ( _o.formaalTekst != null ) return false;
		}
		else if ( _o.formaalTekst == null || !formaalTekst.equals( _o.formaalTekst )) return false;

		if ( kanPtLiggeStille30 == null ) {
			if ( _o.kanPtLiggeStille30 != null ) return false;
		}
		else if ( _o.kanPtLiggeStille30 == null || kanPtLiggeStille30.booleanValue() != _o.kanPtLiggeStille30.booleanValue()) return false;

		if ( ptTaalerFaste == null ) {
			if ( _o.ptTaalerFaste != null ) return false;
		}
		else if ( _o.ptTaalerFaste == null || ptTaalerFaste.booleanValue() != _o.ptTaalerFaste.booleanValue()) return false;

		if ( diabetes == null ) {
			if ( _o.diabetes != null ) return false;
		}
		else if ( _o.diabetes == null || diabetes.booleanValue() != _o.diabetes.booleanValue()) return false;

		if ( dMBeh == null ) {
			if ( _o.dMBeh != null ) return false;
		}
		else if ( _o.dMBeh == null || !dMBeh.equals( _o.dMBeh )) return false;

		if ( smerter == null ) {
			if ( _o.smerter != null ) return false;
		}
		else if ( _o.smerter == null || smerter.booleanValue() != _o.smerter.booleanValue()) return false;

		if ( respInsuff == null ) {
			if ( _o.respInsuff != null ) return false;
		}
		else if ( _o.respInsuff == null || respInsuff.booleanValue() != _o.respInsuff.booleanValue()) return false;

		if ( klaustrofobi == null ) {
			if ( _o.klaustrofobi != null ) return false;
		}
		else if ( _o.klaustrofobi == null || klaustrofobi.booleanValue() != _o.klaustrofobi.booleanValue()) return false;

		if ( allergi == null ) {
			if ( _o.allergi != null ) return false;
		}
		else if ( _o.allergi == null || allergi.booleanValue() != _o.allergi.booleanValue()) return false;

		if ( allergiTekst == null ) {
			if ( _o.allergiTekst != null ) return false;
		}
		else if ( _o.allergiTekst == null || !allergiTekst.equals( _o.allergiTekst )) return false;

		if ( fedme == null ) {
			if ( _o.fedme != null ) return false;
		}
		else if ( _o.fedme == null || fedme.booleanValue() != _o.fedme.booleanValue()) return false;

		if ( vaegt == null ) {
			if ( _o.vaegt != null ) return false;
		}
		else if ( _o.vaegt == null || vaegt.intValue() != _o.vaegt.intValue()) return false;

		if ( biopsi == null ) {
			if ( _o.biopsi != null ) return false;
		}
		else if ( _o.biopsi == null || biopsi.booleanValue() != _o.biopsi.booleanValue()) return false;

		if ( biopsiTekst == null ) {
			if ( _o.biopsiTekst != null ) return false;
		}
		else if ( _o.biopsiTekst == null || !biopsiTekst.equals( _o.biopsiTekst )) return false;

		if ( operation == null ) {
			if ( _o.operation != null ) return false;
		}
		else if ( _o.operation == null || operation.booleanValue() != _o.operation.booleanValue()) return false;

		if ( operationTekst == null ) {
			if ( _o.operationTekst != null ) return false;
		}
		else if ( _o.operationTekst == null || !operationTekst.equals( _o.operationTekst )) return false;

		if ( kemoOgStraale != _o.kemoOgStraale ) return false;

		if ( kontrastReaktion == null ) {
			if ( _o.kontrastReaktion != null ) return false;
		}
		else if ( _o.kontrastReaktion == null || kontrastReaktion.booleanValue() != _o.kontrastReaktion.booleanValue()) return false;

		if ( kontrastReaktionTekst == null ) {
			if ( _o.kontrastReaktionTekst != null ) return false;
		}
		else if ( _o.kontrastReaktionTekst == null || !kontrastReaktionTekst.equals( _o.kontrastReaktionTekst )) return false;

		if ( nedsatNyreFkt == null ) {
			if ( _o.nedsatNyreFkt != null ) return false;
		}
		else if ( _o.nedsatNyreFkt == null || nedsatNyreFkt.booleanValue() != _o.nedsatNyreFkt.booleanValue()) return false;

		if ( sidstePKreatinin == null ) {
			if ( _o.sidstePKreatinin != null ) return false;
		}
		else if ( _o.sidstePKreatinin == null || sidstePKreatinin.intValue() != _o.sidstePKreatinin.intValue()) return false;

		if ( sidstePKreatTimestamp == null ) {
			if ( _o.sidstePKreatTimestamp != null ) return false;
		}
		else if ( _o.sidstePKreatTimestamp == null || sidstePKreatTimestamp.getTime() != _o.sidstePKreatTimestamp.getTime()) return false;

		if ( preMed == null ) {
			if ( _o.preMed != null ) return false;
		}
		else if ( _o.preMed == null || preMed.booleanValue() != _o.preMed.booleanValue()) return false;

		if ( dMRegime == null ) {
			if ( _o.dMRegime != null ) return false;
		}
		else if ( _o.dMRegime == null || dMRegime.booleanValue() != _o.dMRegime.booleanValue()) return false;

		if ( pOKontrast == null ) {
			if ( _o.pOKontrast != null ) return false;
		}
		else if ( _o.pOKontrast == null || pOKontrast.booleanValue() != _o.pOKontrast.booleanValue()) return false;

		if ( iVKontrast == null ) {
			if ( _o.iVKontrast != null ) return false;
		}
		else if ( _o.iVKontrast == null || iVKontrast.booleanValue() != _o.iVKontrast.booleanValue()) return false;

		if ( aktuelPKreatinin == null ) {
			if ( _o.aktuelPKreatinin != null ) return false;
		}
		else if ( _o.aktuelPKreatinin == null || aktuelPKreatinin.intValue() != _o.aktuelPKreatinin.intValue()) return false;

		if ( aktuelPKreatTimestamp == null ) {
			if ( _o.aktuelPKreatTimestamp != null ) return false;
		}
		else if ( _o.aktuelPKreatTimestamp == null || aktuelPKreatTimestamp.getTime() != _o.aktuelPKreatTimestamp.getTime()) return false;

		if ( aktuelAndetTekst == null ) {
			if ( _o.aktuelAndetTekst != null ) return false;
		}
		else if ( _o.aktuelAndetTekst == null || !aktuelAndetTekst.equals( _o.aktuelAndetTekst )) return false;

		return true;
	 }

	 /**
	  * Returns a hash code value for the object.
	  */
	 @Override
	 public int hashCode() {
		 int _ret = -987719444; // = "PETCTKontrolskema".hashCode()
		 _ret += pETCTKontrolskemaId == null ? 0 : pETCTKontrolskemaId;
		 _ret = 29 * _ret + (formaal == null ? 0 : formaal.hashCode());
		 _ret = 29 * _ret + (formaalTekst == null ? 0 : formaalTekst.hashCode());
		 _ret = 29 * _ret + (kanPtLiggeStille30 == null ? 0 : (kanPtLiggeStille30 ? 1 : 0));
		 _ret = 29 * _ret + (ptTaalerFaste == null ? 0 : (ptTaalerFaste ? 1 : 0));
		 _ret = 29 * _ret + (diabetes == null ? 0 : (diabetes ? 1 : 0));
		 _ret = 29 * _ret + (dMBeh == null ? 0 : dMBeh.hashCode());
		 _ret = 29 * _ret + (smerter == null ? 0 : (smerter ? 1 : 0));
		 _ret = 29 * _ret + (respInsuff == null ? 0 : (respInsuff ? 1 : 0));
		 _ret = 29 * _ret + (klaustrofobi == null ? 0 : (klaustrofobi ? 1 : 0));
		 _ret = 29 * _ret + (allergi == null ? 0 : (allergi ? 1 : 0));
		 _ret = 29 * _ret + (allergiTekst == null ? 0 : allergiTekst.hashCode());
		 _ret = 29 * _ret + (fedme == null ? 0 : (fedme ? 1 : 0));
		 _ret = 29 * _ret + (vaegt == null ? 0 : vaegt);
		 _ret = 29 * _ret + (biopsi == null ? 0 : (biopsi ? 1 : 0));
		 _ret = 29 * _ret + (biopsiTekst == null ? 0 : biopsiTekst.hashCode());
		 _ret = 29 * _ret + (operation == null ? 0 : (operation ? 1 : 0));
		 _ret = 29 * _ret + (operationTekst == null ? 0 : operationTekst.hashCode());
		 _ret = 29 * _ret + (kemoOgStraale == null ? 0 : kemoOgStraale.hashCode());
		 _ret = 29 * _ret + (kontrastReaktion == null ? 0 : (kontrastReaktion ? 1 : 0));
		 _ret = 29 * _ret + (kontrastReaktionTekst == null ? 0 : kontrastReaktionTekst.hashCode());
		 _ret = 29 * _ret + (nedsatNyreFkt == null ? 0 : (nedsatNyreFkt ? 1 : 0));
		 _ret = 29 * _ret + (sidstePKreatinin == null ? 0 : sidstePKreatinin);
		 _ret = 29 * _ret + (sidstePKreatTimestamp == null ? 0 : (int)sidstePKreatTimestamp.getTime());
		 _ret = 29 * _ret + (preMed == null ? 0 : (preMed ? 1 : 0));
		 _ret = 29 * _ret + (dMRegime == null ? 0 : (dMRegime ? 1 : 0));
		 _ret = 29 * _ret + (pOKontrast == null ? 0 : (pOKontrast ? 1 : 0));
		 _ret = 29 * _ret + (iVKontrast == null ? 0 : (iVKontrast ? 1 : 0));
		 _ret = 29 * _ret + (aktuelPKreatinin == null ? 0 : aktuelPKreatinin);
		 _ret = 29 * _ret + (aktuelPKreatTimestamp == null ? 0 : (int)aktuelPKreatTimestamp.getTime());
		 _ret = 29 * _ret + (aktuelAndetTekst == null ? 0 : aktuelAndetTekst.hashCode());

		 return _ret;
	 }


	 ////////////////////////////////////////////////////////////////////////////
	 // Protected
	 ////////////////////////////////////////////////////////////////////////////

	 /**
	  * Constructs the content for the toString() method.
	  */
	 protected void contentToString(StringBuffer sb) {
		 append( sb, "pETCTKontrolskemaId", pETCTKontrolskemaId );
		 append( sb, "formaal", formaal );
		 append( sb, "formaalTekst", formaalTekst );
		 append( sb, "kanPtLiggeStille30", kanPtLiggeStille30 );
		 append( sb, "ptTaalerFaste", ptTaalerFaste );
		 append( sb, "diabetes", diabetes );
		 append( sb, "dMBeh", dMBeh );
		 append( sb, "smerter", smerter );
		 append( sb, "respInsuff", respInsuff );
		 append( sb, "klaustrofobi", klaustrofobi );
		 append( sb, "allergi", allergi );
		 append( sb, "allergiTekst", allergiTekst );
		 append( sb, "fedme", fedme );
		 append( sb, "vaegt", vaegt );
		 append( sb, "biopsi", biopsi );
		 append( sb, "biopsiTekst", biopsiTekst );
		 append( sb, "operation", operation );
		 append( sb, "operationTekst", operationTekst );
		 append( sb, "kemoOgStraale", kemoOgStraale );
		 append( sb, "kontrastReaktion", kontrastReaktion );
		 append( sb, "kontrastReaktionTekst", kontrastReaktionTekst );
		 append( sb, "nedsatNyreFkt", nedsatNyreFkt );
		 append( sb, "sidstePKreatinin", sidstePKreatinin );
		 append( sb, "sidstePKreatTimestamp", sidstePKreatTimestamp );
		 append( sb, "preMed", preMed );
		 append( sb, "dMRegime", dMRegime );
		 append( sb, "pOKontrast", pOKontrast );
		 append( sb, "iVKontrast", iVKontrast );
		 append( sb, "aktuelPKreatinin", aktuelPKreatinin );
		 append( sb, "aktuelPKreatTimestamp", aktuelPKreatTimestamp );
		 append( sb, "aktuelAndetTekst", aktuelAndetTekst );
	 }
}
