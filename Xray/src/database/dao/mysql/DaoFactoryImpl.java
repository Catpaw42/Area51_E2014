/*
 * This file was generated - do not edit it directly !!
 * Generated by AuDAO tool, a product of Spolecne s.r.o.
 * For more information please visit http://www.spoledge.com
 */
package database.dao.mysql;

import java.sql.Connection;

import database.dao.*;


/**
 * This is the main implementation class for obtaining DAO objects.
 * @author generated
 */
public class DaoFactoryImpl extends DaoFactory.Factory {
   
	public UndersoegelsesTypeDao createUndersoegelsesTypeDao( Connection conn ) {
        return new UndersoegelsesTypeDaoImpl( conn );
    }
    public BrugerDao createBrugerDao( Connection conn ) {
        return new BrugerDaoImpl( conn );
    }
    public CtKontrastKontrolskemaDao createCtKontrastKontrolskemaDao( Connection conn ) {
        return new CtKontrastKontrolskemaDaoImpl( conn );
    }
    public ModalitetDao createModalitetDao( Connection conn ) {
        return new ModalitetDaoImpl( conn );
    }
    public MRKontrolskemaDao createMrKontrolskemaDao( Connection conn ) {
        return new MRKontrolskemaDaoImpl( conn );
    }
    public PatientDao createPatientDao( Connection conn ) {
        return new PatientDaoImpl( conn );
    }
    public PETCTKontrolskemaDao createPetctKontrolskemaDao( Connection conn ) {
        return new PETCTKontrolskemaDaoImpl( conn );
    }
    public RekvisitionDao createRekvisitionDao( Connection conn ) {
        return new RekvisitionDaoImpl( conn );
    }
    public RettighederDao createRettighederDao( Connection conn ) {
        return new RettighederDaoImpl( conn );
    }
	public UlInvKontrolskemaDao createUlInvKontrolskemaDao(Connection conn) {
		return new UlInvKontrolskemaDaoImpl(conn);
	}



}
