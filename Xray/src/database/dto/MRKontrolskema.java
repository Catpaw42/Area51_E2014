/*
 * This file was generated - do not edit it directly !!
 * Generated by AuDAO tool, a product of Spolecne s.r.o.
 * For more information please visit http://www.spoledge.com
 */
package database.dto;

import com.spoledge.audao.db.dto.AbstractDto;

/**
 * This is a DTO class.
 *
 * @author generated
 */
public class MRKontrolskema extends AbstractDto {

    public enum MRBoern {
        UDEN_SEDERING,
        I_GENEREL_ANAESTESI
    }

    public enum MRVoksen {
        UDEN_SEDERING,
        MED_SEDERING,
        I_GENEREL_ANAESTESI
    }

    ////////////////////////////////////////////////////////////////////////////
    // Static
    ////////////////////////////////////////////////////////////////////////////

    public static final String TABLE = "MR_kontrolskema";

    ////////////////////////////////////////////////////////////////////////////
    // Attributes
    ////////////////////////////////////////////////////////////////////////////

    private Integer mRKontrolskemaId;
    private Integer mRKontrolantId;
    private String tidlBilledDiagnostik;
    private Boolean pacemaker;
    private Boolean metalImplantater;
    private String metalImplantaterBeskrivelse;
    private Boolean andetMetalisk;
    private String andetMetaliskBeskrivelse;
    private Boolean nyresygdom;
    private Integer nyresygdomKreatinin;
    private Boolean graviditet;
    private Integer graviditetUge;
    private Boolean klaustrofobi;
    private String praepForsyn;
    private Integer hoejde;
    private Integer vaegt;
    private MRKontrolskema.MRBoern mRBoern;
    private MRKontrolskema.MRVoksen mRVoksen;

    private boolean isTidlBilledDiagnostikModified;
    private boolean isMetalImplantaterBeskrivelseModified;
    private boolean isAndetMetaliskBeskrivelseModified;
    private boolean isNyresygdomKreatininModified;
    private boolean isGraviditetModified;
    private boolean isGraviditetUgeModified;
    private boolean isPraepForsynModified;
    private boolean isMRBoernModified;
    private boolean isMRVoksenModified;

    ////////////////////////////////////////////////////////////////////////////
    // Constructors
    ////////////////////////////////////////////////////////////////////////////

    /**
     * Creates a new empty DTO.
     */
    public MRKontrolskema() {
    }

    ////////////////////////////////////////////////////////////////////////////
    // Public
    ////////////////////////////////////////////////////////////////////////////

    public Integer getMRKontrolskemaId() {
        return mRKontrolskemaId;
    }

    public void setMRKontrolskemaId( Integer _val) {
        this.mRKontrolskemaId = _val;
    }

    public Integer getMRKontrolantId() {
        return mRKontrolantId;
    }

    public void setMRKontrolantId( Integer _val) {
        this.mRKontrolantId = _val;
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

    public Boolean getPacemaker() {
        return pacemaker;
    }

    public void setPacemaker( Boolean _val) {
        this.pacemaker = _val;
    }

    public Boolean getMetalImplantater() {
        return metalImplantater;
    }

    public void setMetalImplantater( Boolean _val) {
        this.metalImplantater = _val;
    }

    public String getMetalImplantaterBeskrivelse() {
        return metalImplantaterBeskrivelse;
    }

    public void setMetalImplantaterBeskrivelse( String _val) {
        this.metalImplantaterBeskrivelse = _val;
        this.isMetalImplantaterBeskrivelseModified = true;
    }

    public boolean isMetalImplantaterBeskrivelseModified() {
        return isMetalImplantaterBeskrivelseModified;
    }

    public Boolean getAndetMetalisk() {
        return andetMetalisk;
    }

    public void setAndetMetalisk( Boolean _val) {
        this.andetMetalisk = _val;
    }

    public String getAndetMetaliskBeskrivelse() {
        return andetMetaliskBeskrivelse;
    }

    public void setAndetMetaliskBeskrivelse( String _val) {
        this.andetMetaliskBeskrivelse = _val;
        this.isAndetMetaliskBeskrivelseModified = true;
    }

    public boolean isAndetMetaliskBeskrivelseModified() {
        return isAndetMetaliskBeskrivelseModified;
    }

    public Boolean getNyresygdom() {
        return nyresygdom;
    }

    public void setNyresygdom( Boolean _val) {
        this.nyresygdom = _val;
    }

    public Integer getNyresygdomKreatinin() {
        return nyresygdomKreatinin;
    }

    public void setNyresygdomKreatinin( Integer _val) {
        this.nyresygdomKreatinin = _val;
        this.isNyresygdomKreatininModified = true;
    }

    public boolean isNyresygdomKreatininModified() {
        return isNyresygdomKreatininModified;
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

    public Boolean getKlaustrofobi() {
        return klaustrofobi;
    }

    public void setKlaustrofobi( Boolean _val) {
        this.klaustrofobi = _val;
    }

    public String getPraepForsyn() {
        return praepForsyn;
    }

    public void setPraepForsyn( String _val) {
        this.praepForsyn = _val;
        this.isPraepForsynModified = true;
    }

    public boolean isPraepForsynModified() {
        return isPraepForsynModified;
    }

    public Integer getHoejde() {
        return hoejde;
    }

    public void setHoejde( Integer _val) {
        this.hoejde = _val;
    }

    public Integer getVaegt() {
        return vaegt;
    }

    public void setVaegt( Integer _val) {
        this.vaegt = _val;
    }

    public MRKontrolskema.MRBoern getMRBoern() {
        return mRBoern;
    }

    public void setMRBoern( MRKontrolskema.MRBoern _val) {
        this.mRBoern = _val;
        this.isMRBoernModified = true;
    }

    public boolean isMRBoernModified() {
        return isMRBoernModified;
    }

    public MRKontrolskema.MRVoksen getMRVoksen() {
        return mRVoksen;
    }

    public void setMRVoksen( MRKontrolskema.MRVoksen _val) {
        this.mRVoksen = _val;
        this.isMRVoksenModified = true;
    }

    public boolean isMRVoksenModified() {
        return isMRVoksenModified;
    }

    /**
     * Indicates whether some other object is "equal to" this one.
     * Uses 'columns' equality type.
     */
    @Override
    public boolean equals( Object _other ) {
        if (_other == this) return true;
        if (_other == null || (!(_other instanceof MRKontrolskema))) return false;

        MRKontrolskema _o = (MRKontrolskema) _other;

        if ( mRKontrolskemaId == null ) {
            if ( _o.mRKontrolskemaId != null ) return false;
        }
        else if ( _o.mRKontrolskemaId == null || mRKontrolskemaId.intValue() != _o.mRKontrolskemaId.intValue()) return false;

        if ( mRKontrolantId == null ) {
            if ( _o.mRKontrolantId != null ) return false;
        }
        else if ( _o.mRKontrolantId == null || mRKontrolantId.intValue() != _o.mRKontrolantId.intValue()) return false;

        if ( tidlBilledDiagnostik == null ) {
            if ( _o.tidlBilledDiagnostik != null ) return false;
        }
        else if ( _o.tidlBilledDiagnostik == null || !tidlBilledDiagnostik.equals( _o.tidlBilledDiagnostik )) return false;

        if ( pacemaker == null ) {
            if ( _o.pacemaker != null ) return false;
        }
        else if ( _o.pacemaker == null || pacemaker.booleanValue() != _o.pacemaker.booleanValue()) return false;

        if ( metalImplantater == null ) {
            if ( _o.metalImplantater != null ) return false;
        }
        else if ( _o.metalImplantater == null || metalImplantater.booleanValue() != _o.metalImplantater.booleanValue()) return false;

        if ( metalImplantaterBeskrivelse == null ) {
            if ( _o.metalImplantaterBeskrivelse != null ) return false;
        }
        else if ( _o.metalImplantaterBeskrivelse == null || !metalImplantaterBeskrivelse.equals( _o.metalImplantaterBeskrivelse )) return false;

        if ( andetMetalisk == null ) {
            if ( _o.andetMetalisk != null ) return false;
        }
        else if ( _o.andetMetalisk == null || andetMetalisk.booleanValue() != _o.andetMetalisk.booleanValue()) return false;

        if ( andetMetaliskBeskrivelse == null ) {
            if ( _o.andetMetaliskBeskrivelse != null ) return false;
        }
        else if ( _o.andetMetaliskBeskrivelse == null || !andetMetaliskBeskrivelse.equals( _o.andetMetaliskBeskrivelse )) return false;

        if ( nyresygdom == null ) {
            if ( _o.nyresygdom != null ) return false;
        }
        else if ( _o.nyresygdom == null || nyresygdom.booleanValue() != _o.nyresygdom.booleanValue()) return false;

        if ( nyresygdomKreatinin == null ) {
            if ( _o.nyresygdomKreatinin != null ) return false;
        }
        else if ( _o.nyresygdomKreatinin == null || nyresygdomKreatinin.intValue() != _o.nyresygdomKreatinin.intValue()) return false;

        if ( graviditet == null ) {
            if ( _o.graviditet != null ) return false;
        }
        else if ( _o.graviditet == null || graviditet.booleanValue() != _o.graviditet.booleanValue()) return false;

        if ( graviditetUge == null ) {
            if ( _o.graviditetUge != null ) return false;
        }
        else if ( _o.graviditetUge == null || graviditetUge.intValue() != _o.graviditetUge.intValue()) return false;

        if ( klaustrofobi == null ) {
            if ( _o.klaustrofobi != null ) return false;
        }
        else if ( _o.klaustrofobi == null || klaustrofobi.booleanValue() != _o.klaustrofobi.booleanValue()) return false;

        if ( praepForsyn == null ) {
            if ( _o.praepForsyn != null ) return false;
        }
        else if ( _o.praepForsyn == null || !praepForsyn.equals( _o.praepForsyn )) return false;

        if ( hoejde == null ) {
            if ( _o.hoejde != null ) return false;
        }
        else if ( _o.hoejde == null || hoejde.intValue() != _o.hoejde.intValue()) return false;

        if ( vaegt == null ) {
            if ( _o.vaegt != null ) return false;
        }
        else if ( _o.vaegt == null || vaegt.intValue() != _o.vaegt.intValue()) return false;

        if ( mRBoern != _o.mRBoern ) return false;

        if ( mRVoksen != _o.mRVoksen ) return false;

        return true;
    }

    /**
     * Returns a hash code value for the object.
     */
    @Override
    public int hashCode() {
        int _ret = 1029661025; // = "MRKontrolskema".hashCode()
        _ret += mRKontrolskemaId == null ? 0 : mRKontrolskemaId;
        _ret = 29 * _ret + (mRKontrolantId == null ? 0 : mRKontrolantId);
        _ret = 29 * _ret + (tidlBilledDiagnostik == null ? 0 : tidlBilledDiagnostik.hashCode());
        _ret = 29 * _ret + (pacemaker == null ? 0 : (pacemaker ? 1 : 0));
        _ret = 29 * _ret + (metalImplantater == null ? 0 : (metalImplantater ? 1 : 0));
        _ret = 29 * _ret + (metalImplantaterBeskrivelse == null ? 0 : metalImplantaterBeskrivelse.hashCode());
        _ret = 29 * _ret + (andetMetalisk == null ? 0 : (andetMetalisk ? 1 : 0));
        _ret = 29 * _ret + (andetMetaliskBeskrivelse == null ? 0 : andetMetaliskBeskrivelse.hashCode());
        _ret = 29 * _ret + (nyresygdom == null ? 0 : (nyresygdom ? 1 : 0));
        _ret = 29 * _ret + (nyresygdomKreatinin == null ? 0 : nyresygdomKreatinin);
        _ret = 29 * _ret + (graviditet == null ? 0 : (graviditet ? 1 : 0));
        _ret = 29 * _ret + (graviditetUge == null ? 0 : graviditetUge);
        _ret = 29 * _ret + (klaustrofobi == null ? 0 : (klaustrofobi ? 1 : 0));
        _ret = 29 * _ret + (praepForsyn == null ? 0 : praepForsyn.hashCode());
        _ret = 29 * _ret + (hoejde == null ? 0 : hoejde);
        _ret = 29 * _ret + (vaegt == null ? 0 : vaegt);
        _ret = 29 * _ret + (mRBoern == null ? 0 : mRBoern.hashCode());
        _ret = 29 * _ret + (mRVoksen == null ? 0 : mRVoksen.hashCode());

        return _ret;
    }


    ////////////////////////////////////////////////////////////////////////////
    // Protected
    ////////////////////////////////////////////////////////////////////////////
		
    /**
     * Constructs the content for the toString() method.
     */
    protected void contentToString(StringBuffer sb) {
        append( sb, "mRKontrolskemaId", mRKontrolskemaId );
        append( sb, "mRKontrolantId", mRKontrolantId );
        append( sb, "tidlBilledDiagnostik", tidlBilledDiagnostik );
        append( sb, "pacemaker", pacemaker );
        append( sb, "metalImplantater", metalImplantater );
        append( sb, "metalImplantaterBeskrivelse", metalImplantaterBeskrivelse );
        append( sb, "andetMetalisk", andetMetalisk );
        append( sb, "andetMetaliskBeskrivelse", andetMetaliskBeskrivelse );
        append( sb, "nyresygdom", nyresygdom );
        append( sb, "nyresygdomKreatinin", nyresygdomKreatinin );
        append( sb, "graviditet", graviditet );
        append( sb, "graviditetUge", graviditetUge );
        append( sb, "klaustrofobi", klaustrofobi );
        append( sb, "praepForsyn", praepForsyn );
        append( sb, "hoejde", hoejde );
        append( sb, "vaegt", vaegt );
        append( sb, "mRBoern", mRBoern );
        append( sb, "mRVoksen", mRVoksen );
    }
}