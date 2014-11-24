package database;

import java.io.ObjectInputStream.GetField;
import java.sql.Connection;

import com.spoledge.audao.db.dao.DaoException;

import database.dao.BrugerDao;
import database.dao.CtKontrastKontrolskemaDao;
import database.dao.MRKontrolskemaDao;
import database.dao.ModalitetDao;
import database.dao.PETCTKontrolskemaDao;
import database.dao.PatientDao;
import database.dao.RekvisitionDao;
import database.dao.UlInvKontrolskemaDao;
import database.dao.UndersoegelsesTypeDao;
import database.dao.mysql.BrugerDaoImplExtended;
import database.dao.mysql.CtKontrastKontrolskemaDaoImpl;
import database.dao.mysql.MRKontrolskemaDaoImpl;
import database.dao.mysql.ModalitetDaoImpl;
import database.dao.mysql.PETCTKontrolskemaDaoImpl;
import database.dao.mysql.PatientDaoImpl;
import database.dao.mysql.RekvisitionDaoImplExt;
import database.dao.mysql.UlInvKontrolskemaDaoImpl;
import database.dao.mysql.UndersoegelsesTypeDaoImpl;
import database.dto.Bruger;
import database.interfaces.IDataSourceConnector.ConnectionException;

public class DatabaseController{
	
	private BrugerDao brugerDao;
	private CtKontrastKontrolskemaDao ctKontrastKontrolskemaDao;
	private ModalitetDao modalitetDao;
	private MRKontrolskemaDao mrKontrolskemaDao;
	private PatientDao patientDao;
	private PETCTKontrolskemaDao petCtKontrolskemaDao;
	private RekvisitionDao rekvisitionDao;
	private UlInvKontrolskemaDao ulInvKontrolskemaDao;
	private UndersoegelsesTypeDao undersoegelsesTypeDao;
	private Connection conn;
	
	public DatabaseController(){
			this.conn = getConnection();
	}
	
	private Connection getConnection(){
		if(conn == null){
			try {
				this.conn = DataSourceConnector.getConnection();
			} catch (ConnectionException e) {
				e.printStackTrace();
			}
		}
		return conn;
	}
	
	public BrugerDao getBrugerDao() {
		if(conn == null || this.brugerDao == null){
			this.brugerDao = new BrugerDaoImplExtended(getConnection());
		}
		return brugerDao;
	}

	public void setBrugerDao(BrugerDao brugerDao) {
		this.brugerDao = brugerDao;
	}

	public CtKontrastKontrolskemaDao getCtKontrastKontrolskemaDao() {
		if(conn == null || this.ctKontrastKontrolskemaDao == null){
			this.ctKontrastKontrolskemaDao = new CtKontrastKontrolskemaDaoImpl(getConnection());
		}
		return ctKontrastKontrolskemaDao;
	}

	public void setCtKontrastKontrolskemaDao(
			CtKontrastKontrolskemaDao ctKontrastKontrolskemaDao) {
		this.ctKontrastKontrolskemaDao = ctKontrastKontrolskemaDao;
	}

	public ModalitetDao getModalitetDao() {
		if(conn == null || this.modalitetDao == null){
			this.modalitetDao = new ModalitetDaoImpl(getConnection());
		}
		return modalitetDao;
	}

	public void setModalitetDao(ModalitetDao modalitetDao) {
		this.modalitetDao = modalitetDao;
	}

	public MRKontrolskemaDao getMrKontrolskemaDao() {
		if(conn == null || this.mrKontrolskemaDao == null){
			this.mrKontrolskemaDao = new MRKontrolskemaDaoImpl(getConnection());
		}
		return mrKontrolskemaDao;
	}

	public void setMrKontrolskemaDao(MRKontrolskemaDao mrKontrolskemaDao) {
		this.mrKontrolskemaDao = mrKontrolskemaDao;
	}

	public PatientDao getPatientDao() {
		if(conn == null || this.patientDao == null){
			this.patientDao = new PatientDaoImpl(getConnection());
		}
		return patientDao;
	}

	public void setPatientDao(PatientDao patientDao) {
		this.patientDao = patientDao;
	}

	public PETCTKontrolskemaDao getPetCtKontrolskemaDao() {
		if(conn == null || this.petCtKontrolskemaDao == null){
			this.petCtKontrolskemaDao = new PETCTKontrolskemaDaoImpl(getConnection());
		}
		return petCtKontrolskemaDao;
	}

	public void setPetCtKontrolskemaDao(PETCTKontrolskemaDao petCtKontrolskemaDao) {
		this.petCtKontrolskemaDao = petCtKontrolskemaDao;
	}

	public RekvisitionDao getRekvisitionDao() {
		if(conn == null || this.rekvisitionDao == null){
			this.rekvisitionDao = new RekvisitionDaoImplExt(getConnection());
		}
		return rekvisitionDao;
	}

	public void setRekvisitionDao(RekvisitionDao rekvisitionDao) {
		this.rekvisitionDao = rekvisitionDao;
	}

	public UlInvKontrolskemaDao getUlInvKontrolskemaDao() {
		if(conn == null || this.ulInvKontrolskemaDao == null){
			this.ulInvKontrolskemaDao = new UlInvKontrolskemaDaoImpl(getConnection());
		}
		return ulInvKontrolskemaDao;
	}

	public void setUlInvKontrolskemaDao(UlInvKontrolskemaDao ulInvKontrolskemaDao) {
		this.ulInvKontrolskemaDao = ulInvKontrolskemaDao;
	}

	public UndersoegelsesTypeDao getUndersoegelsesTypeDao() {
		if(conn == null || this.undersoegelsesTypeDao == null){
			this.undersoegelsesTypeDao = new UndersoegelsesTypeDaoImpl(getConnection());
		}
		return undersoegelsesTypeDao;
	}

	public void setUndersoegelsesTypeDao(UndersoegelsesTypeDao undersoegelsesTypeDao) {
		this.undersoegelsesTypeDao = undersoegelsesTypeDao;
	}



	

}
