package database;

import java.sql.Connection;

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
import database.dao.mysql.RekvisitionDaoImpl;
import database.dao.mysql.UlInvKontrolskemaDaoImpl;
import database.dao.mysql.UndersoegelsesTypeDaoImpl;
import database.interfaces.IDatabaseController;
import database.interfaces.IDataSourceConnector.ConnectionException;
/**
 * 
 * @author Rúni
 *
 */
public class DatabaseController implements IDatabaseController{
	
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
	
	/* (non-Javadoc)
	 * @see database.IDatabaseController#getBrugerDao()
	 */
	@Override
	public BrugerDao getBrugerDao() {
		if(conn == null || this.brugerDao == null){
			this.brugerDao = new BrugerDaoImplExtended(getConnection());
		}
		return brugerDao;
	}

	/* (non-Javadoc)
	 * @see database.IDatabaseController#setBrugerDao(database.dao.BrugerDao)
	 */
	@Override
	public void setBrugerDao(BrugerDao brugerDao) {
		this.brugerDao = brugerDao;
	}

	/* (non-Javadoc)
	 * @see database.IDatabaseController#getCtKontrastKontrolskemaDao()
	 */
	@Override
	public CtKontrastKontrolskemaDao getCtKontrastKontrolskemaDao() {
		if(conn == null || this.ctKontrastKontrolskemaDao == null){
			this.ctKontrastKontrolskemaDao = new CtKontrastKontrolskemaDaoImpl(getConnection());
		}
		return ctKontrastKontrolskemaDao;
	}

	/* (non-Javadoc)
	 * @see database.IDatabaseController#setCtKontrastKontrolskemaDao(database.dao.CtKontrastKontrolskemaDao)
	 */
	@Override
	public void setCtKontrastKontrolskemaDao(
			CtKontrastKontrolskemaDao ctKontrastKontrolskemaDao) {
		this.ctKontrastKontrolskemaDao = ctKontrastKontrolskemaDao;
	}

	/* (non-Javadoc)
	 * @see database.IDatabaseController#getModalitetDao()
	 */
	@Override
	public ModalitetDao getModalitetDao() {
		if(conn == null || this.modalitetDao == null){
			this.modalitetDao = new ModalitetDaoImpl(getConnection());
		}
		return modalitetDao;
	}

	/* (non-Javadoc)
	 * @see database.IDatabaseController#setModalitetDao(database.dao.ModalitetDao)
	 */
	@Override
	public void setModalitetDao(ModalitetDao modalitetDao) {
		this.modalitetDao = modalitetDao;
	}

	/* (non-Javadoc)
	 * @see database.IDatabaseController#getMrKontrolskemaDao()
	 */
	@Override
	public MRKontrolskemaDao getMrKontrolskemaDao() {
		if(conn == null || this.mrKontrolskemaDao == null){
			this.mrKontrolskemaDao = new MRKontrolskemaDaoImpl(getConnection());
		}
		return mrKontrolskemaDao;
	}

	/* (non-Javadoc)
	 * @see database.IDatabaseController#setMrKontrolskemaDao(database.dao.MRKontrolskemaDao)
	 */
	@Override
	public void setMrKontrolskemaDao(MRKontrolskemaDao mrKontrolskemaDao) {
		this.mrKontrolskemaDao = mrKontrolskemaDao;
	}

	/* (non-Javadoc)
	 * @see database.IDatabaseController#getPatientDao()
	 */
	@Override
	public PatientDao getPatientDao() {
		if(conn == null || this.patientDao == null){
			this.patientDao = new PatientDaoImpl(getConnection());
		}
		return patientDao;
	}

	/* (non-Javadoc)
	 * @see database.IDatabaseController#setPatientDao(database.dao.PatientDao)
	 */
	@Override
	public void setPatientDao(PatientDao patientDao) {
		this.patientDao = patientDao;
	}

	/* (non-Javadoc)
	 * @see database.IDatabaseController#getPetCtKontrolskemaDao()
	 */
	@Override
	public PETCTKontrolskemaDao getPetCtKontrolskemaDao() {
		if(conn == null || this.petCtKontrolskemaDao == null){
			this.petCtKontrolskemaDao = new PETCTKontrolskemaDaoImpl(getConnection());
		}
		return petCtKontrolskemaDao;
	}

	/* (non-Javadoc)
	 * @see database.IDatabaseController#setPetCtKontrolskemaDao(database.dao.PETCTKontrolskemaDao)
	 */
	@Override
	public void setPetCtKontrolskemaDao(PETCTKontrolskemaDao petCtKontrolskemaDao) {
		this.petCtKontrolskemaDao = petCtKontrolskemaDao;
	}

	/* (non-Javadoc)
	 * @see database.IDatabaseController#getRekvisitionDao()
	 */
	@Override
	public RekvisitionDao getRekvisitionDao() {
		if(conn == null || this.rekvisitionDao == null){
			this.rekvisitionDao = new RekvisitionDaoImpl(getConnection());
		}
		return rekvisitionDao;
	}

	/* (non-Javadoc)
	 * @see database.IDatabaseController#setRekvisitionDao(database.dao.RekvisitionDao)
	 */
	@Override
	public void setRekvisitionDao(RekvisitionDao rekvisitionDao) {
		this.rekvisitionDao = rekvisitionDao;
	}

	/* (non-Javadoc)
	 * @see database.IDatabaseController#getUlInvKontrolskemaDao()
	 */
	@Override
	public UlInvKontrolskemaDao getUlInvKontrolskemaDao() {
		if(conn == null || this.ulInvKontrolskemaDao == null){
			this.ulInvKontrolskemaDao = new UlInvKontrolskemaDaoImpl(getConnection());
		}
		return ulInvKontrolskemaDao;
	}

	/* (non-Javadoc)
	 * @see database.IDatabaseController#setUlInvKontrolskemaDao(database.dao.UlInvKontrolskemaDao)
	 */
	@Override
	public void setUlInvKontrolskemaDao(UlInvKontrolskemaDao ulInvKontrolskemaDao) {
		this.ulInvKontrolskemaDao = ulInvKontrolskemaDao;
	}

	/* (non-Javadoc)
	 * @see database.IDatabaseController#getUndersoegelsesTypeDao()
	 */
	@Override
	public UndersoegelsesTypeDao getUndersoegelsesTypeDao() {
		if(conn == null || this.undersoegelsesTypeDao == null){
			this.undersoegelsesTypeDao = new UndersoegelsesTypeDaoImpl(getConnection());
		}
		return undersoegelsesTypeDao;
	}

	/* (non-Javadoc)
	 * @see database.IDatabaseController#setUndersoegelsesTypeDao(database.dao.UndersoegelsesTypeDao)
	 */
	@Override
	public void setUndersoegelsesTypeDao(UndersoegelsesTypeDao undersoegelsesTypeDao) {
		this.undersoegelsesTypeDao = undersoegelsesTypeDao;
	}



	

}
