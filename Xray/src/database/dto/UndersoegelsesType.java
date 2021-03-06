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
public class UndersoegelsesType extends AbstractDto {

    ////////////////////////////////////////////////////////////////////////////
    // Static
    ////////////////////////////////////////////////////////////////////////////

    public static final String TABLE = "undersoegelses_type";

    ////////////////////////////////////////////////////////////////////////////
    // Attributes
    ////////////////////////////////////////////////////////////////////////////

    private Integer undersoegelsesTypeId;
    private String undersoegelsesNavn;
    private Integer modalitetId;

    ////////////////////////////////////////////////////////////////////////////
    // Constructors
    ////////////////////////////////////////////////////////////////////////////

    /**
     * Creates a new empty DTO.
     */
    public UndersoegelsesType() {
    }

    ////////////////////////////////////////////////////////////////////////////
    // Public
    ////////////////////////////////////////////////////////////////////////////

    public Integer getUndersoegelsesTypeId() {
        return undersoegelsesTypeId;
    }

    public void setUndersoegelsesTypeId( Integer _val) {
        this.undersoegelsesTypeId = _val;
    }

    public String getUndersoegelsesNavn() {
        return undersoegelsesNavn;
    }

    public void setUndersoegelsesNavn( String _val) {
        this.undersoegelsesNavn = _val;
    }

    public Integer getModalitetId() {
        return modalitetId;
    }

    public void setModalitetId( Integer _val) {
        this.modalitetId = _val;
    }

    /**
     * Indicates whether some other object is "equal to" this one.
     * Uses 'columns' equality type.
     */
    @Override
    public boolean equals( Object _other ) {
        if (_other == this) return true;
        if (_other == null || (!(_other instanceof UndersoegelsesType))) return false;

        UndersoegelsesType _o = (UndersoegelsesType) _other;

        if ( undersoegelsesTypeId == null ) {
            if ( _o.undersoegelsesTypeId != null ) return false;
        }
        else if ( _o.undersoegelsesTypeId == null || undersoegelsesTypeId.intValue() != _o.undersoegelsesTypeId.intValue()) return false;

        if ( undersoegelsesNavn == null ) {
            if ( _o.undersoegelsesNavn != null ) return false;
        }
        else if ( _o.undersoegelsesNavn == null || !undersoegelsesNavn.equals( _o.undersoegelsesNavn )) return false;

        if ( modalitetId == null ) {
            if ( _o.modalitetId != null ) return false;
        }
        else if ( _o.modalitetId == null || modalitetId.intValue() != _o.modalitetId.intValue()) return false;

        return true;
    }

    /**
     * Returns a hash code value for the object.
     */
    @Override
    public int hashCode() {
        int _ret = 1571283646; // = "UndersoegelsesType".hashCode()
        _ret += undersoegelsesTypeId == null ? 0 : undersoegelsesTypeId;
        _ret = 29 * _ret + (undersoegelsesNavn == null ? 0 : undersoegelsesNavn.hashCode());
        _ret = 29 * _ret + (modalitetId == null ? 0 : modalitetId);

        return _ret;
    }


    ////////////////////////////////////////////////////////////////////////////
    // Protected
    ////////////////////////////////////////////////////////////////////////////
		
    /**
     * Constructs the content for the toString() method.
     */
    protected void contentToString(StringBuffer sb) {
        append( sb, "undersoegelsesTypeId", undersoegelsesTypeId );
        append( sb, "undersoegelsesNavn", undersoegelsesNavn );
        append( sb, "modalitetId", modalitetId );
    }
}
