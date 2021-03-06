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

import database.dao.ModalitetDao;
import database.dto.Modalitet;


/**
 * This is the DAO imlementation class.
 *
 * @author generated
 */
public class ModalitetDaoImpl extends AbstractDaoImpl<Modalitet> implements ModalitetDao {

    private static final String TABLE_NAME = "modalitet";

    protected static final String SELECT_COLUMNS = "modalitet_id, modalitet_navn";

    protected static final String PK_CONDITION = "modalitet_id=?";

    private static final String SQL_INSERT = "INSERT INTO modalitet (modalitet_navn) VALUES (?)";

    public ModalitetDaoImpl( Connection conn ) {
        super( conn );
    }

    /**
     * Finds a record identified by its primary key.
     * @return the record found or null
     */
    public Modalitet findByPrimaryKey( int modalitetId ) {
        return findOne( PK_CONDITION, modalitetId);
    }

    /**
     * Finds records.
     */
    public Modalitet[] findDynamic( String cond, int offset, int count, Object... params ) {
        return findManyArray( cond, offset, count, params);
    }

    /**
     * Inserts a new record.
     * @return the generated primary key - modalitetId
     */
    public int insert( Modalitet dto ) throws DaoException {
        PreparedStatement stmt = null;
        ResultSet rs = null;

        debugSql( SQL_INSERT, dto );

        try {
            stmt = conn.prepareStatement( SQL_INSERT, PreparedStatement.RETURN_GENERATED_KEYS );

            if ( dto.getModalitetNavn() == null ) {
                throw new DaoException("Value of column 'modalitet_navn' cannot be null");
            }
            checkMaxLength( "modalitet_navn", dto.getModalitetNavn(), 40 );
            stmt.setString( 1, dto.getModalitetNavn() );

            int n = stmt.executeUpdate();

            rs = stmt.getGeneratedKeys();
            rs.next();

            dto.setModalitetId( rs.getInt( 1 ));

            return dto.getModalitetId();
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
     * Returns the table name.
     */
    public String getTableName() {
        return TABLE_NAME;
    }

    protected String getSelectColumns() {
        return SELECT_COLUMNS;
    }

    protected Modalitet fetch( ResultSet rs ) throws SQLException {
    	String[] cn = SELECT_COLUMNS.split(", ");
        Modalitet dto = new Modalitet();
        dto.setModalitetId( rs.getInt( cn[0] ));
        dto.setModalitetNavn( rs.getString( cn[1] ));

        return dto;
    }

    protected Modalitet[] toArray(ArrayList<Modalitet> list ) {
        Modalitet[] ret = new Modalitet[ list.size() ];
        return list.toArray( ret );
    }

}
