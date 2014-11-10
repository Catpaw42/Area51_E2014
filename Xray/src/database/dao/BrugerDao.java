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

import database.dto.Bruger;


/**
 * This is the DAO.
 *
 * @author generated
 */
public interface BrugerDao extends AbstractDao {

    /**
     * Finds a record identified by its primary key.
     * @return the record found or null
     */
    public Bruger findByPrimaryKey( int brugerId );

    /**
     * Finds records.
     */
    public Bruger[] findDynamic( String cond, int offset, int count, Object... params );

    /**
     * Inserts a new record.
     * @return the generated primary key - brugerId
     */
    public int insert( Bruger dto ) throws DaoException;

    /**
     * Updates one record found by primary key.
     * @return true iff the record was really updated (=found and any change was really saved)
     */
    public boolean update( int brugerId, Bruger dto ) throws DaoException;
    
    /**
     * Method for validating userlogin
     */
    
    public boolean validate(String brugernavn, String kodeord);

}
