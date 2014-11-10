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

import database.dto.UndersoegelsesType;


/**
 * This is the DAO.
 *
 * @author generated
 */
public interface UndersoegelsesTypeDao extends AbstractDao {

    /**
     * Finds a record identified by its primary key.
     * @return the record found or null
     */
    public UndersoegelsesType findByPrimaryKey( int undersoegelsesTypeId );

    /**
     * Finds records.
     */
    public UndersoegelsesType[] findDynamic( String cond, int offset, int count, Object... params );

    /**
     * Inserts a new record.
     * @return the generated primary key - undersoegelsesTypeId
     */
    public int insert( UndersoegelsesType dto ) throws DaoException;

    /**
     * Updates one record found by primary key.
     * @return true iff the record was really updated (=found and any change was really saved)
     */
    public boolean update( int undersoegelsesTypeId, UndersoegelsesType dto ) throws DaoException;

}
