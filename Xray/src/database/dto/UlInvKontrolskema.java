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
public class UlInvKontrolskema extends AbstractDto {

    ////////////////////////////////////////////////////////////////////////////
    // Static
    ////////////////////////////////////////////////////////////////////////////

    public static final String TABLE = "ul_inv_kontrolskema";

    ////////////////////////////////////////////////////////////////////////////
    // Attributes
    ////////////////////////////////////////////////////////////////////////////

    private Integer ulInvKontrolskemaId;
    private Timestamp akTimestamp;
    private Integer trombocytter;
    private Double inr;

    ////////////////////////////////////////////////////////////////////////////
    // Constructors
    ////////////////////////////////////////////////////////////////////////////

    /**
     * Creates a new empty DTO.
     */
    public UlInvKontrolskema() {
    }

    ////////////////////////////////////////////////////////////////////////////
    // Public
    ////////////////////////////////////////////////////////////////////////////

    public Integer getUlInvKontrolskemaId() {
        return ulInvKontrolskemaId;
    }

    public void setUlInvKontrolskemaId( Integer _val) {
        this.ulInvKontrolskemaId = _val;
    }

    public Timestamp getAkTimestamp() {
        return akTimestamp;
    }

    public void setAkTimestamp( java.util.Date _val ) {
        setAkTimestamp((Timestamp)( _val != null ? new Timestamp( _val.getTime()) : null ));
    }

    public void setAkTimestamp( Timestamp _val) {
        this.akTimestamp = _val;
    }

    public Integer getTrombocytter() {
        return trombocytter;
    }

    public void setTrombocytter( Integer _val) {
        this.trombocytter = _val;
    }

    public Double getInr() {
        return inr;
    }

    public void setInr( Double _val) {
        this.inr = _val;
    }

    /**
     * Indicates whether some other object is "equal to" this one.
     * Uses 'columns' equality type.
     */
    @Override
    public boolean equals( Object _other ) {
        if (_other == this) return true;
        if (_other == null || (!(_other instanceof UlInvKontrolskema))) return false;

        UlInvKontrolskema _o = (UlInvKontrolskema) _other;

        if ( ulInvKontrolskemaId == null ) {
            if ( _o.ulInvKontrolskemaId != null ) return false;
        }
        else if ( _o.ulInvKontrolskemaId == null || ulInvKontrolskemaId.intValue() != _o.ulInvKontrolskemaId.intValue()) return false;

        if ( akTimestamp == null ) {
            if ( _o.akTimestamp != null ) return false;
        }
        else if ( _o.akTimestamp == null || akTimestamp.getTime() != _o.akTimestamp.getTime()) return false;

        if ( trombocytter == null ) {
            if ( _o.trombocytter != null ) return false;
        }
        else if ( _o.trombocytter == null || trombocytter.intValue() != _o.trombocytter.intValue()) return false;

        if ( inr == null ) {
            if ( _o.inr != null ) return false;
        }
        else if ( _o.inr == null || inr.doubleValue() != _o.inr.doubleValue()) return false;

        return true;
    }

    /**
     * Returns a hash code value for the object.
     */
    @Override
    public int hashCode() {
        int _ret = 1994712566; // = "UlInvKontrolskema".hashCode()
        _ret += ulInvKontrolskemaId == null ? 0 : ulInvKontrolskemaId;
        _ret = 29 * _ret + (akTimestamp == null ? 0 : (int)akTimestamp.getTime());
        _ret = 29 * _ret + (trombocytter == null ? 0 : trombocytter);
        _ret = 29 * _ret + (inr == null ? 0 : Float.floatToIntBits(inr.floatValue()));

        return _ret;
    }


    ////////////////////////////////////////////////////////////////////////////
    // Protected
    ////////////////////////////////////////////////////////////////////////////
		
    /**
     * Constructs the content for the toString() method.
     */
    protected void contentToString(StringBuffer sb) {
        append( sb, "ulInvKontrolskemaId", ulInvKontrolskemaId );
        append( sb, "akTimestamp", akTimestamp );
        append( sb, "trombocytter", trombocytter );
        append( sb, "inr", inr );
    }
}
