/*
 * This file was generated - do not edit it directly !!
 * Generated by AuDAO tool, a product of Spolecne s.r.o.
 * For more information please visit http://www.spoledge.com
 */
package database.dao.mysql;


import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;

import java.util.ArrayList;

import com.spoledge.audao.db.dao.AbstractDaoImpl;
import com.spoledge.audao.db.dao.DBException;
import com.spoledge.audao.db.dao.DaoException;


import database.dao.MRKontrolskemaDao;
import database.dto.MRKontrolskema;


/**
 * This is the DAO imlementation class.
 *
 * @author generated
 */
public class MRKontrolskemaDaoImpl extends AbstractDaoImpl<MRKontrolskema> implements MRKontrolskemaDao {

    private static final String TABLE_NAME = "MR_kontrolskema";

    protected static final String SELECT_COLUMNS = "MR_kontrolskema_id, MR_kontrolant_id, pacemaker, metal_implantater, metal_implantater_beskrivelse, andet_metalisk, andet_metalisk_beskrivelse, nyresygdom, nyresygdom_kreatinin, graviditet, graviditet_uge, klaustrofobi, praep_forsyn, hoejde, vaegt, MR_boern, MR_voksen";

    protected static final String PK_CONDITION = "MR_kontrolskema_id=?";

    private static final String SQL_INSERT = "INSERT INTO MR_kontrolskema (MR_kontrolant_id,pacemaker,metal_implantater,metal_implantater_beskrivelse,andet_metalisk,andet_metalisk_beskrivelse,nyresygdom,nyresygdom_kreatinin,graviditet,graviditet_uge,klaustrofobi,praep_forsyn,hoejde,vaegt,MR_boern,MR_voksen) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
    private static final MRKontrolskema.MRBoern[] _MRKontrolskema_MRBoerns = { null, MRKontrolskema.MRBoern.UDEN_SEDERING, MRKontrolskema.MRBoern.I_GENEREL_ANAESTESI };
    private static final MRKontrolskema.MRVoksen[] _MRKontrolskema_MRVoksens = { null, MRKontrolskema.MRVoksen.UDEN_SEDERING, MRKontrolskema.MRVoksen.MED_SEDERING, MRKontrolskema.MRVoksen.I_GENEREL_ANAESTESI };

    public MRKontrolskemaDaoImpl( Connection conn ) {
        super( conn );
    }

    /**
     * Finds a record identified by its primary key.
     * @return the record found or null
     */
    public MRKontrolskema findByPrimaryKey( int mRKontrolskemaId ) {
        return findOne( PK_CONDITION, mRKontrolskemaId);
    }

    /**
     * Finds records.
     */
    public MRKontrolskema[] findDynamic( String cond, int offset, int count, Object... params ) {
        return findManyArray( cond, offset, count, params);
    }

    /**
     * Inserts a new record.
     * @return the generated primary key - mRKontrolskemaId
     */
    public int insert( MRKontrolskema dto ) throws DaoException {
        PreparedStatement stmt = null;
        ResultSet rs = null;

        debugSql( SQL_INSERT, dto );

        try {
            stmt = conn.prepareStatement( SQL_INSERT, PreparedStatement.RETURN_GENERATED_KEYS );

            if ( dto.getMRKontrolantId() == null ) {
                stmt.setNull( 1, Types.INTEGER );
            }
            else {
                stmt.setInt( 1, dto.getMRKontrolantId() );
            }

            if ( dto.getPacemaker() == null ) {
                throw new DaoException("Value of column 'pacemaker' cannot be null");
            }
            stmt.setByte( 2, dto.getPacemaker() ? ((byte)1) : ((byte)0) );

            if ( dto.getMetalImplantater() == null ) {
                throw new DaoException("Value of column 'metal_implantater' cannot be null");
            }
            stmt.setByte( 3, dto.getMetalImplantater() ? ((byte)1) : ((byte)0) );

            if ( dto.getMetalImplantaterBeskrivelse() != null ) {
                checkMaxLength( "metal_implantater_beskrivelse", dto.getMetalImplantaterBeskrivelse(), 100 );
            }
            stmt.setString( 4, dto.getMetalImplantaterBeskrivelse() );

            if ( dto.getAndetMetalisk() == null ) {
                throw new DaoException("Value of column 'andet_metalisk' cannot be null");
            }
            stmt.setByte( 5, dto.getAndetMetalisk() ? ((byte)1) : ((byte)0) );

            if ( dto.getAndetMetaliskBeskrivelse() != null ) {
                checkMaxLength( "andet_metalisk_beskrivelse", dto.getAndetMetaliskBeskrivelse(), 100 );
            }
            stmt.setString( 6, dto.getAndetMetaliskBeskrivelse() );

            if ( dto.getNyresygdom() == null ) {
                throw new DaoException("Value of column 'nyresygdom' cannot be null");
            }
            stmt.setByte( 7, dto.getNyresygdom() ? ((byte)1) : ((byte)0) );

            if ( dto.getNyresygdomKreatinin() == null ) {
                stmt.setNull( 8, Types.INTEGER );
            }
            else {
                stmt.setInt( 8, dto.getNyresygdomKreatinin() );
            }

            if ( dto.getGraviditet() == null ) {
                stmt.setNull( 9, Types.TINYINT );
            }
            else {
                stmt.setByte( 9, dto.getGraviditet() ? ((byte)1) : ((byte)0) );
            }

            if ( dto.getGraviditetUge() == null ) {
                stmt.setNull( 10, Types.INTEGER );
            }
            else {
                stmt.setInt( 10, dto.getGraviditetUge() );
            }

            if ( dto.getKlaustrofobi() == null ) {
                throw new DaoException("Value of column 'klaustrofobi' cannot be null");
            }
            stmt.setByte( 11, dto.getKlaustrofobi() ? ((byte)1) : ((byte)0) );

            if ( dto.getPraepForsyn() != null ) {
                checkMaxLength( "praep_forsyn", dto.getPraepForsyn(), 100 );
            }
            stmt.setString( 12, dto.getPraepForsyn() );

            if ( dto.getHoejde() == null ) {
                throw new DaoException("Value of column 'hoejde' cannot be null");
            }
            stmt.setInt( 13, dto.getHoejde() );

            if ( dto.getVaegt() == null ) {
                throw new DaoException("Value of column 'vaegt' cannot be null");
            }
            stmt.setInt( 14, dto.getVaegt() );

            if ( dto.getMRBoern() == null ) {
                stmt.setNull( 15, Types.SMALLINT );
            }
            else {
                stmt.setShort( 15, (short) (dto.getMRBoern().ordinal() + 1) );
            }

            if ( dto.getMRVoksen() == null ) {
                stmt.setNull( 16, Types.SMALLINT );
            }
            else {
                stmt.setShort( 16, (short) (dto.getMRVoksen().ordinal() + 1) );
            }

            int n = stmt.executeUpdate();

            rs = stmt.getGeneratedKeys();
            rs.next();

            dto.setMRKontrolskemaId( rs.getInt( 1 ));

            return dto.getMRKontrolskemaId();
        }
        catch (SQLException e) {
            errorSql( e, SQL_INSERT, dto );
            handleException( e );
            throw new DBException( e );
        }
        finally {
            if (rs != null) try { rs.close(); } catch (SQLException e) {}
            if (stmt != null) try { stmt.close(); } catch (SQLException e) {}
        }
    }

    /**
     * Updates one record found by primary key.
     * @return true iff the record was really updated (=found and any change was really saved)
     */
    public boolean update( int mRKontrolskemaId, MRKontrolskema dto ) throws DaoException {
        StringBuffer sb = new StringBuffer();
        ArrayList<Object> params = new ArrayList<Object>();

        if ( dto.getPacemaker() != null ) {
            sb.append( "pacemaker=?" );
            params.add( (dto.getPacemaker() ? ((byte)1) : ((byte)0)));
        }

        if ( dto.getMetalImplantater() != null ) {
            if (sb.length() > 0) {
                sb.append( ", " );
            }

            sb.append( "metal_implantater=?" );
            params.add( (dto.getMetalImplantater() ? ((byte)1) : ((byte)0)));
        }

        if ( dto.isMetalImplantaterBeskrivelseModified()) {
            if (sb.length() > 0) {
                sb.append( ", " );
            }

            if ( dto.getMetalImplantaterBeskrivelse() == null ) {
                sb.append( "metal_implantater_beskrivelse=NULL" );
            }
            else {
                checkMaxLength( "metal_implantater_beskrivelse", dto.getMetalImplantaterBeskrivelse(), 100 );
                sb.append( "metal_implantater_beskrivelse=?" );
                params.add( dto.getMetalImplantaterBeskrivelse());
            }
        }

        if ( dto.getAndetMetalisk() != null ) {
            if (sb.length() > 0) {
                sb.append( ", " );
            }

            sb.append( "andet_metalisk=?" );
            params.add( (dto.getAndetMetalisk() ? ((byte)1) : ((byte)0)));
        }

        if ( dto.isAndetMetaliskBeskrivelseModified()) {
            if (sb.length() > 0) {
                sb.append( ", " );
            }

            if ( dto.getAndetMetaliskBeskrivelse() == null ) {
                sb.append( "andet_metalisk_beskrivelse=NULL" );
            }
            else {
                checkMaxLength( "andet_metalisk_beskrivelse", dto.getAndetMetaliskBeskrivelse(), 100 );
                sb.append( "andet_metalisk_beskrivelse=?" );
                params.add( dto.getAndetMetaliskBeskrivelse());
            }
        }

        if ( dto.getNyresygdom() != null ) {
            if (sb.length() > 0) {
                sb.append( ", " );
            }

            sb.append( "nyresygdom=?" );
            params.add( (dto.getNyresygdom() ? ((byte)1) : ((byte)0)));
        }

        if ( dto.isNyresygdomKreatininModified()) {
            if (sb.length() > 0) {
                sb.append( ", " );
            }

            if ( dto.getNyresygdomKreatinin() == null ) {
                sb.append( "nyresygdom_kreatinin=NULL" );
            }
            else {
                sb.append( "nyresygdom_kreatinin=?" );
                params.add( dto.getNyresygdomKreatinin());
            }
        }

        if ( dto.isGraviditetModified()) {
            if (sb.length() > 0) {
                sb.append( ", " );
            }

            if ( dto.getGraviditet() == null ) {
                sb.append( "graviditet=NULL" );
            }
            else {
                sb.append( "graviditet=?" );
                params.add( (dto.getGraviditet() ? ((byte)1) : ((byte)0)));
            }
        }

        if ( dto.isGraviditetUgeModified()) {
            if (sb.length() > 0) {
                sb.append( ", " );
            }

            if ( dto.getGraviditetUge() == null ) {
                sb.append( "graviditet_uge=NULL" );
            }
            else {
                sb.append( "graviditet_uge=?" );
                params.add( dto.getGraviditetUge());
            }
        }

        if ( dto.isPraepForsynModified()) {
            if (sb.length() > 0) {
                sb.append( ", " );
            }

            if ( dto.getPraepForsyn() == null ) {
                sb.append( "praep_forsyn=NULL" );
            }
            else {
                checkMaxLength( "praep_forsyn", dto.getPraepForsyn(), 100 );
                sb.append( "praep_forsyn=?" );
                params.add( dto.getPraepForsyn());
            }
        }

        if ( dto.getHoejde() != null ) {
            if (sb.length() > 0) {
                sb.append( ", " );
            }

            sb.append( "hoejde=?" );
            params.add( dto.getHoejde());
        }

        if ( dto.getVaegt() != null ) {
            if (sb.length() > 0) {
                sb.append( ", " );
            }

            sb.append( "vaegt=?" );
            params.add( dto.getVaegt());
        }

        if ( dto.isMRBoernModified()) {
            if (sb.length() > 0) {
                sb.append( ", " );
            }

            if ( dto.getMRBoern() == null ) {
                sb.append( "MR_boern=NULL" );
            }
            else {
                sb.append( "MR_boern=?" );
                params.add( dto.getMRBoern().ordinal() + 1);
            }
        }

        if ( dto.isMRVoksenModified()) {
            if (sb.length() > 0) {
                sb.append( ", " );
            }

            if ( dto.getMRVoksen() == null ) {
                sb.append( "MR_voksen=NULL" );
            }
            else {
                sb.append( "MR_voksen=?" );
                params.add( dto.getMRVoksen().ordinal() + 1);
            }
        }

        if (sb.length() == 0) {
            return false;
        }

        params.add( mRKontrolskemaId );

        Object[] oparams = new Object[ params.size() ];

        return updateOne( sb.toString(), PK_CONDITION, params.toArray( oparams ));
    }

    /**
     * Returns the table name.
     */
    public String getTableName() {
        return TABLE_NAME;
    }

    protected String getSelectColumns() {
        return SELECT_COLUMNS;
    }

    protected MRKontrolskema fetch( ResultSet rs ) throws SQLException {
        MRKontrolskema dto = new MRKontrolskema();
        dto.setMRKontrolskemaId( rs.getInt( 1 ));
        dto.setMRKontrolantId( rs.getInt( 2 ));

        if ( rs.wasNull()) {
            dto.setMRKontrolantId( null );
        }

        dto.setPacemaker( rs.getBoolean( 3 ) ? Boolean.TRUE : Boolean.FALSE );
        dto.setMetalImplantater( rs.getBoolean( 4 ) ? Boolean.TRUE : Boolean.FALSE );
        dto.setMetalImplantaterBeskrivelse( rs.getString( 5 ));
        dto.setAndetMetalisk( rs.getBoolean( 6 ) ? Boolean.TRUE : Boolean.FALSE );
        dto.setAndetMetaliskBeskrivelse( rs.getString( 7 ));
        dto.setNyresygdom( rs.getBoolean( 8 ) ? Boolean.TRUE : Boolean.FALSE );
        dto.setNyresygdomKreatinin( rs.getInt( 9 ));

        if ( rs.wasNull()) {
            dto.setNyresygdomKreatinin( null );
        }

        dto.setGraviditet( rs.getBoolean( 10 ) ? Boolean.TRUE : Boolean.FALSE );

        if ( rs.wasNull()) {
            dto.setGraviditet( null );
        }

        dto.setGraviditetUge( rs.getInt( 11 ));

        if ( rs.wasNull()) {
            dto.setGraviditetUge( null );
        }

        dto.setKlaustrofobi( rs.getBoolean( 12 ) ? Boolean.TRUE : Boolean.FALSE );
        dto.setPraepForsyn( rs.getString( 13 ));
        dto.setHoejde( rs.getInt( 14 ));
        dto.setVaegt( rs.getInt( 15 ));
        dto.setMRBoern( _MRKontrolskema_MRBoerns[ rs.getShort( 16 ) ]);

        if ( rs.wasNull()) {
            dto.setMRBoern( null );
        }

        dto.setMRVoksen( _MRKontrolskema_MRVoksens[ rs.getShort( 17 ) ]);

        if ( rs.wasNull()) {
            dto.setMRVoksen( null );
        }


        return dto;
    }

    protected MRKontrolskema[] toArray(ArrayList<MRKontrolskema> list ) {
        MRKontrolskema[] ret = new MRKontrolskema[ list.size() ];
        return list.toArray( ret );
    }

}
