/*
 * This file was generated - do not edit it directly !!
 * Generated by AuDAO tool, a product of Spolecne s.r.o.
 * For more information please visit http://www.spoledge.com
 */
package database.dao;

import java.sql.Date;
import java.sql.Timestamp;

import com.spoledge.audao.db.dao.AbstractDao;
import com.spoledge.audao.db.dao.DaoException;

import database.dto.Rekvisition;


/**
 * This is the DAO.
 *
 * @author generated
 */
public interface RekvisitionDao extends AbstractDao {

    /**
     * Finds a record identified by its primary key.
     * @return the record found or null
     */
    public Rekvisition findByPrimaryKey( int rekvisitionId );
    
    /**
     * Finds records.
     */
    public Rekvisition[] findDynamic( String cond, int offset, int count, Object... params );

    /**
     * Inserts a new record.
     * @return the generated primary key - rekvisitionId
     */
    public int insert( Rekvisition dto ) throws DaoException;

    /**
     * Updates one record found by primary key.
     * @return true iff the record was really updated (=found and any change was really saved)
     */
    public boolean update( int rekvisitionId, Rekvisition dto ) throws DaoException;

}
