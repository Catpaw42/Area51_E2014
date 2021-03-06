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
import java.sql.Types;
import java.util.ArrayList;

import com.spoledge.audao.db.dao.AbstractDaoImpl;
import com.spoledge.audao.db.dao.DBException;
import com.spoledge.audao.db.dao.DaoException;

import database.dao.RettighederDao;
import database.dto.Rettigheder;


/**
 * This is the DAO imlementation class.
 *
 * @author generated
 */
public class RettighederDaoImpl extends AbstractDaoImpl<Rettigheder> implements RettighederDao {

    private static final String TABLE_NAME = "rettigheder";

    protected static final String SELECT_COLUMNS = "rettigheds_id, bruger_id, rettighed";

    protected static final String PK_CONDITION = "rettigheds_id=?";

    private static final String SQL_INSERT = "INSERT INTO rettigheder (bruger_id,rettighed) VALUES (?,?)";
    private static final Rettigheder.Rettighed[] _Rettigheder_Rettigheds = { null, Rettigheder.Rettighed.ADMIN, Rettigheder.Rettighed.BOOKING, Rettigheder.Rettighed.ASSESSOR, Rettigheder.Rettighed.REQUEST };

    public RettighederDaoImpl( Connection conn ) {
        super( conn );
    }

    /**
     * Finds a record identified by its primary key.
     * @return the record found or null
     */
    public Rettigheder findByPrimaryKey( int rettighedsId ) {
        return findOne( PK_CONDITION, rettighedsId);
    }

    /**
     * Finds records.
     */
    public Rettigheder[] findDynamic( String cond, int offset, int count, Object... params ) {
        return findManyArray( cond, offset, count, params);
    }

    /**
     * Inserts a new record.
     * @return the generated primary key - rettighedsId
     */
    public int insert( Rettigheder dto ) throws DaoException {
        PreparedStatement stmt = null;
        ResultSet rs = null;

        debugSql( SQL_INSERT, dto );

        try {
            stmt = conn.prepareStatement( SQL_INSERT, PreparedStatement.RETURN_GENERATED_KEYS );

            if ( dto.getBrugerId() == null ) {
                throw new DaoException("Value of column 'bruger_id' cannot be null");
            }
            stmt.setInt( 1, dto.getBrugerId() );

            if ( dto.getRettighed() == null ) {
                stmt.setNull( 2, Types.SMALLINT );
            }
            else {
                stmt.setShort( 2, (short) (dto.getRettighed().ordinal() + 1) );
            }

            int n = stmt.executeUpdate();

            rs = stmt.getGeneratedKeys();
            rs.next();

            dto.setRettighedsId( rs.getInt( 1 ));

            return dto.getRettighedsId();
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
    public boolean update( int rettighedsId, Rettigheder dto ) throws DaoException {
        StringBuffer sb = new StringBuffer();
        ArrayList<Object> params = new ArrayList<Object>();

        if ( dto.isRettighedModified()) {
            if ( dto.getRettighed() == null ) {
                sb.append( "rettighed=NULL" );
            }
            else {
                sb.append( "rettighed=?" );
                params.add( dto.getRettighed().ordinal() + 1);
            }
        }

        if (sb.length() == 0) {
            return false;
        }

        params.add( rettighedsId );

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

    protected Rettigheder fetch( ResultSet rs ) throws SQLException {
        Rettigheder dto = new Rettigheder();
        dto.setRettighedsId( rs.getInt( 1 ));
        dto.setBrugerId( rs.getInt( 2 ));
        dto.setRettighed( _Rettigheder_Rettigheds[ rs.getShort( 3 ) ]);

        if ( rs.wasNull()) {
            dto.setRettighed( null );
        }


        return dto;
    }

    protected Rettigheder[] toArray(ArrayList<Rettigheder> list ) {
        Rettigheder[] ret = new Rettigheder[ list.size() ];
        return list.toArray( ret );
    }

}
