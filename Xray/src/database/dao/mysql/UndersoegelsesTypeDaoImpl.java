/*
 * This file was generated - do not edit it directly !!
 * Generated by AuDAO tool, a product of Spolecne s.r.o.
 * For more information please visit http://www.spoledge.com
 */
package database.dao.mysql;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.spoledge.audao.db.dao.AbstractDaoImpl;
import com.spoledge.audao.db.dao.DBException;
import com.spoledge.audao.db.dao.DaoException;

import database.dao.UndersoegelsesTypeDao;
import database.dto.UndersoegelsesType;


/**
 * This is the DAO imlementation class.
 *
 * @author generated
 */
public class UndersoegelsesTypeDaoImpl extends AbstractDaoImpl<UndersoegelsesType> implements UndersoegelsesTypeDao {

    private static final String TABLE_NAME = "undersoegelses_type";

    protected static final String SELECT_COLUMNS = "undersoegelses_type_id, undersoegelses_navn, modalitet_id";

    protected static final String PK_CONDITION = "undersoegelses_type_id=?";

    private static final String SQL_INSERT = "INSERT INTO undersoegelses_type (undersoegelses_navn,modalitet_id) VALUES (?,?)";

    public UndersoegelsesTypeDaoImpl( Connection conn ) {
        super( conn );
    }

    /**
     * Finds a record identified by its primary key.
     * @return the record found or null
     */
    public UndersoegelsesType findByPrimaryKey( int undersoegelsesTypeId ) {
        return findOne( PK_CONDITION, undersoegelsesTypeId);
    }

    /**
     * Finds records.
     */
    public UndersoegelsesType[] findDynamic( String cond, int offset, int count, Object... params ) {
        return findManyArray( cond, offset, count, params);
    }

    /**
     * Inserts a new record.
     * @return the generated primary key - undersoegelsesTypeId
     */
    public int insert( UndersoegelsesType dto ) throws DaoException {
        PreparedStatement stmt = null;
        ResultSet rs = null;

        debugSql( SQL_INSERT, dto );

        try {
            stmt = conn.prepareStatement( SQL_INSERT, PreparedStatement.RETURN_GENERATED_KEYS );

            if ( dto.getUndersoegelsesNavn() == null ) {
                throw new DaoException("Value of column 'undersoegelses_navn' cannot be null");
            }
            checkMaxLength( "undersoegelses_navn", dto.getUndersoegelsesNavn(), 100 );
            stmt.setString( 1, dto.getUndersoegelsesNavn() );

            if ( dto.getModalitetId() == null ) {
                throw new DaoException("Value of column 'modalitet_id' cannot be null");
            }
            stmt.setInt( 2, dto.getModalitetId() );

            int n = stmt.executeUpdate();

            rs = stmt.getGeneratedKeys();
            rs.next();

            dto.setUndersoegelsesTypeId( rs.getInt( 1 ));

            return dto.getUndersoegelsesTypeId();
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
    public boolean update( int undersoegelsesTypeId, UndersoegelsesType dto ) throws DaoException {
        StringBuffer sb = new StringBuffer();
        ArrayList<Object> params = new ArrayList<Object>();

        if ( dto.getUndersoegelsesNavn() != null ) {
            checkMaxLength( "undersoegelses_navn", dto.getUndersoegelsesNavn(), 100 );
            sb.append( "undersoegelses_navn=?" );
            params.add( dto.getUndersoegelsesNavn());
        }

        if ( dto.getModalitetId() != null ) {
            if (sb.length() > 0) {
                sb.append( ", " );
            }

            sb.append( "modalitet_id=?" );
            params.add( dto.getModalitetId());
        }

        if (sb.length() == 0) {
            return false;
        }

        params.add( undersoegelsesTypeId );

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

    protected UndersoegelsesType fetch( ResultSet rs ) throws SQLException {
    	String[] cn = SELECT_COLUMNS.split(", ");
        UndersoegelsesType dto = new UndersoegelsesType();
        dto.setUndersoegelsesTypeId( rs.getInt( cn[0] ));
        dto.setUndersoegelsesNavn( rs.getString( cn[1] ));
        dto.setModalitetId( rs.getInt( cn[2] ));

        return dto;
    }

    protected UndersoegelsesType[] toArray(ArrayList<UndersoegelsesType> list ) {
        UndersoegelsesType[] ret = new UndersoegelsesType[ list.size() ];
        return list.toArray( ret );
    }

}
