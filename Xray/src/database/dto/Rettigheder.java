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
public class Rettigheder extends AbstractDto {

    public enum Rettighed {
        ADMIN,
        BOOKING,
        ASSESSOR,
        REQUEST
    }

    ////////////////////////////////////////////////////////////////////////////
    // Static
    ////////////////////////////////////////////////////////////////////////////

    public static final String TABLE = "rettigheder";

    ////////////////////////////////////////////////////////////////////////////
    // Attributes
    ////////////////////////////////////////////////////////////////////////////

    private Integer rettighedsId;
    private Integer brugerId;
    private Rettigheder.Rettighed rettighed;

    private boolean isRettighedModified;

    ////////////////////////////////////////////////////////////////////////////
    // Constructors
    ////////////////////////////////////////////////////////////////////////////

    /**
     * Creates a new empty DTO.
     */
    public Rettigheder() {
    }

    ////////////////////////////////////////////////////////////////////////////
    // Public
    ////////////////////////////////////////////////////////////////////////////

    public Integer getRettighedsId() {
        return rettighedsId;
    }

    public void setRettighedsId( Integer _val) {
        this.rettighedsId = _val;
    }

    public Integer getBrugerId() {
        return brugerId;
    }

    public void setBrugerId( Integer _val) {
        this.brugerId = _val;
    }

    public Rettigheder.Rettighed getRettighed() {
        return rettighed;
    }

    public void setRettighed( Rettigheder.Rettighed _val) {
        this.rettighed = _val;
        this.isRettighedModified = true;
    }

    public boolean isRettighedModified() {
        return isRettighedModified;
    }

    /**
     * Indicates whether some other object is "equal to" this one.
     * Uses 'columns' equality type.
     */
    @Override
    public boolean equals( Object _other ) {
        if (_other == this) return true;
        if (_other == null || (!(_other instanceof Rettigheder))) return false;

        Rettigheder _o = (Rettigheder) _other;

        if ( rettighedsId == null ) {
            if ( _o.rettighedsId != null ) return false;
        }
        else if ( _o.rettighedsId == null || rettighedsId.intValue() != _o.rettighedsId.intValue()) return false;

        if ( brugerId == null ) {
            if ( _o.brugerId != null ) return false;
        }
        else if ( _o.brugerId == null || brugerId.intValue() != _o.brugerId.intValue()) return false;

        if ( rettighed != _o.rettighed ) return false;

        return true;
    }

    /**
     * Returns a hash code value for the object.
     */
    @Override
    public int hashCode() {
        int _ret = -1151339485; // = "Rettigheder".hashCode()
        _ret += rettighedsId == null ? 0 : rettighedsId;
        _ret = 29 * _ret + (brugerId == null ? 0 : brugerId);
        _ret = 29 * _ret + (rettighed == null ? 0 : rettighed.hashCode());

        return _ret;
    }


    ////////////////////////////////////////////////////////////////////////////
    // Protected
    ////////////////////////////////////////////////////////////////////////////
		
    /**
     * Constructs the content for the toString() method.
     */
    protected void contentToString(StringBuffer sb) {
        append( sb, "rettighedsId", rettighedsId );
        append( sb, "brugerId", brugerId );
        append( sb, "rettighed", rettighed );
    }
}
