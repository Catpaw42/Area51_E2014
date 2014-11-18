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

import database.dto.UlInvKontrolskema;


/**
 * This is the DAO.
 *
 * @author generated
 */
public interface UlInvKontrolskemaDao extends AbstractDao {

    /**
     * Finds a record identified by its primary key.
     * @return the record found or null
     */
    public UlInvKontrolskema findByPrimaryKey( int ulInvKontrolskemaId );

    /**
     * Finds records.
     */
    public UlInvKontrolskema[] findDynamic( String cond, int offset, int count, Object... params );

    /**
     * Inserts a new record.
     * @return the generated primary key - ulInvKontrolskemaId
     */
    public int insert( UlInvKontrolskema dto ) throws DaoException;

}