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

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import com.spoledge.audao.db.dao.AbstractDaoImpl;
import com.spoledge.audao.db.dao.DBException;
import com.spoledge.audao.db.dao.DaoException;

import database.dao.BrugerDao;
import database.dto.Bruger;


/**
 * This is the DAO imlementation class.
 *
 * @author generated
 */
public class BrugerDaoImpl extends AbstractDaoImpl<Bruger> implements BrugerDao {

    private static final String TABLE_NAME = "bruger";

    protected static final String SELECT_COLUMNS = "bruger_id, bruger_navn, er_aktiv";

    protected static final String PK_CONDITION = "bruger_id=?";

    private static final String SQL_INSERT = "INSERT INTO bruger (bruger_navn,er_aktiv) VALUES (?,?)";

    public BrugerDaoImpl( Connection conn ) {
        super( conn );
    }

    /**
     * Finds a record identified by its primary key.
     * @return the record found or null
     */
    public Bruger findByPrimaryKey( int brugerId ) {
        return findOne( PK_CONDITION, brugerId);
    }

    /**
     * Inserts a new record.
     * @return the generated primary key - brugerId
     */
    public int insert( Bruger dto ) throws DaoException {
        PreparedStatement stmt = null;
        ResultSet rs = null;

        debugSql( SQL_INSERT, dto );

        try {
            stmt = conn.prepareStatement( SQL_INSERT, PreparedStatement.RETURN_GENERATED_KEYS );

            if ( dto.getBrugerNavn() == null ) {
                throw new DaoException("Value of column 'bruger_navn' cannot be null");
            }
            checkMaxLength( "bruger_navn", dto.getBrugerNavn(), 30 );
            stmt.setString( 1, dto.getBrugerNavn() );

            if ( dto.getErAktiv() == null ) {
                throw new DaoException("Value of column 'er_aktiv' cannot be null");
            }
            stmt.setByte( 2, dto.getErAktiv() ? ((byte)1) : ((byte)0) );
            
//            int n = stmt.executeUpdate();
            //TODO update to handle constraints....
            try {
				int n = stmt.executeUpdate();
			} catch (MySQLIntegrityConstraintViolationException e) {
				System.err.println("Caught Duplicate user exception");
            	throw new DuplicateEntryException("Username already exists");
			}

            rs = stmt.getGeneratedKeys();
            rs.next();

            dto.setBrugerId( rs.getInt( 1 ));

            return dto.getBrugerId();
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
     * Updates column er_aktiv of one record found by primary key.
     * @return true iff the record was really updated (=found)
     */
    public boolean updateErAktiv( int brugerId, boolean erAktiv ) throws DaoException {
        return updateOne( "er_aktiv=?", PK_CONDITION, (erAktiv ? ((byte)1) : ((byte)0)), brugerId);
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

    protected Bruger fetch( ResultSet rs ) throws SQLException {
        Bruger dto = new Bruger();
        dto.setBrugerId( rs.getInt( 1 ));
        dto.setBrugerNavn( rs.getString( 2 ));
        dto.setErAktiv( rs.getBoolean( 3 ) ? Boolean.TRUE : Boolean.FALSE );

        return dto;
    }

    protected Bruger[] toArray(ArrayList<Bruger> list ) {
        Bruger[] ret = new Bruger[ list.size() ];
        return list.toArray( ret );
    }

}
