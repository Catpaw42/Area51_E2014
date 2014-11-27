package database.interfaces;

import database.dao.BrugerDao;
import database.dao.CtKontrastKontrolskemaDao;
import database.dao.MRKontrolskemaDao;
import database.dao.ModalitetDao;
import database.dao.PETCTKontrolskemaDao;
import database.dao.PatientDao;
import database.dao.RekvisitionDao;
import database.dao.UlInvKontrolskemaDao;
import database.dao.UndersoegelsesTypeDao;
/**
 * 
 * @author Rúni
 *
 */
public interface IDatabaseController {

	public abstract BrugerDao getBrugerDao();

	public abstract void setBrugerDao(BrugerDao brugerDao);

	public abstract CtKontrastKontrolskemaDao getCtKontrastKontrolskemaDao();

	public abstract void setCtKontrastKontrolskemaDao(
			CtKontrastKontrolskemaDao ctKontrastKontrolskemaDao);

	public abstract ModalitetDao getModalitetDao();

	public abstract void setModalitetDao(ModalitetDao modalitetDao);

	public abstract MRKontrolskemaDao getMrKontrolskemaDao();

	public abstract void setMrKontrolskemaDao(
			MRKontrolskemaDao mrKontrolskemaDao);

	public abstract PatientDao getPatientDao();

	public abstract void setPatientDao(PatientDao patientDao);

	public abstract PETCTKontrolskemaDao getPetCtKontrolskemaDao();

	public abstract void setPetCtKontrolskemaDao(
			PETCTKontrolskemaDao petCtKontrolskemaDao);

	public abstract RekvisitionDao getRekvisitionDao();

	public abstract void setRekvisitionDao(RekvisitionDao rekvisitionDao);

	public abstract UlInvKontrolskemaDao getUlInvKontrolskemaDao();

	public abstract void setUlInvKontrolskemaDao(
			UlInvKontrolskemaDao ulInvKontrolskemaDao);

	public abstract UndersoegelsesTypeDao getUndersoegelsesTypeDao();

	public abstract void setUndersoegelsesTypeDao(
			UndersoegelsesTypeDao undersoegelsesTypeDao);

}