package database.dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import database.dao.BrugerDao;
import database.dao.CtKontrastKontrolskemaDao;
import database.dao.MRKontrolskemaDao;
import database.dao.ModalitetDao;
import database.dao.PETCTKontrolskemaDao;
import database.dao.PatientDao;
import database.dao.UndersoegelsesTypeDao;
import database.dto.Modalitet;
import database.dto.Patient;
import database.dto.RekvisitionExtended;
import database.dto.RekvisitionExtended;
import database.dto.RekvisitionExtended.Status;

public class RekvisitionDaoImplExt extends RekvisitionDaoImpl {
	
	private static final String ADV_SEARCH = "SELECT * FROM rekvisition NATURAL JOIN patient NATURAL JOIN modalitet NATURAL JOIN undersoegelses_type WHERE ";
	private static final String GET_ALL_REKV =  "SELECT * FROM rekvisition NATURAL JOIN patient NATURAL JOIN modalitet NATURAL JOIN undersoegelses_type";

	public RekvisitionDaoImplExt(Connection conn) {
		super(conn);
	}
	
	//TODO skal flyttes ind i sin superclass senere
	public RekvisitionExtended[] findDynamic( String cond, int offset, int count, Object... params ) {
		for(int i = 0; i < params.length; i++){
			if(params[i].getClass().isEnum()){
				params[i] = ((Enum<?>) params[i]).ordinal() +1;
			}
		}
		RekvisitionExtended[] rekv = findManyArray( cond, offset, count, params);
		return addObjectsToRekvisition(rekv);
		
	}

	public RekvisitionExtended[] findByAdvSearch(String cpr, String name, String modality, RekvisitionExtended.Status status,Timestamp date, String department){ 	
		Timestamp lowBound = null;
		Timestamp upperBound = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		ArrayList<RekvisitionExtended> rekv = new ArrayList<>();
		String query = ADV_SEARCH;
		boolean first = false;

		if (cpr != null && !cpr.equals("")){
			query = query + "patient_cpr=? ";
			first = true;
		}
		if(name != null && !name.equals("")){			
			query = query + (first ? " AND " : " ") + "patient_navn=? ";
			first=true;
		}
		if(modality != null && !modality.equals("")){
			// query = query + (first ? " AND modalitet_navn=? " : " modalitet_navn=? ");
			query = query + (first ? " AND " : " ") + "modalitet_id=? ";
			first = true;
		}
		if(status != null){
			query = query + (first ? " AND " : " ") + "status=? ";
			first = true;
		}
		if(date != null){
			long day = 60*60*24*1000-1;
			lowBound = new Timestamp(date.getTime());
			upperBound = new Timestamp(date.getTime()+day);
			query = query + (first ? " AND  " : " ") + "afsendt_dato>? AND afsendt_dato<? ";
			first = true;
		}
		if(department != null && !department.equals("") && !department.equalsIgnoreCase("alle")){
			query = query + (first ? " AND " : " ") + "stamafdeling=? ";
			first = true;
		}
		
		if(!first){
			query = GET_ALL_REKV;
		}
		//    	modalitet_navn
		//    	patient_cpr
		//    	patient_navn
		//    	stamafdeling
		//    	status
		//    	afsendt_dato

		System.out.println("Query: " + query);
				try {
					stmt = conn.prepareStatement(query);
					int i = 1;
					if (cpr != null && !cpr.equals("")){
						stmt.setString(i, cpr);
						i++;
					}
					if(name != null && !name.equals("")){			
						stmt.setString(i, name);
						i++;
					}
					if(modality != null && !modality.equals("")){
						stmt.setInt(i, Integer.valueOf(modality)+1);
						i++;
					}
					if(status != null){
						stmt.setShort( i, (short) (status.ordinal() + 1));
						i++;
					}
					if(date != null){
						stmt.setTimestamp(i, lowBound);
						i++;
						stmt.setTimestamp(i, upperBound);
						i++;
					}
					if(department != null && !department.equals("") && !department.equalsIgnoreCase("alle")){
						stmt.setString(i, department);
					}					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					// til workaround
//					PatientDaoImpl ptDao = new PatientDaoImpl(conn); // not right but workaround
//					ModalitetDaoImpl modDao = new ModalitetDaoImpl(conn);
//					UndersoegelsesTypeDaoImpl undDao = new UndersoegelsesTypeDaoImpl(conn); 
					
					rs = stmt.executeQuery();
					RekvisitionExtended rekvRsRow = new RekvisitionExtended(); 
					String pt_cpr = "patient_cpr";
					String pt_name = "patient_navn";
					String mod_name = "modalitet_navn";
					String stam_afdeling = "stamafdeling";
					String afs_dato = "afsendt_dato";
					String rekv_status = "status";
					String rekv_id = "rekvisition_id";
					while (rs.next()){
						rekvRsRow.setPatient(new Patient());
						rekvRsRow.setModalitet(new Modalitet());
						rekvRsRow.getPatient().setPatientCpr(rs.getString(pt_cpr));
						rekvRsRow.getPatient().setPatientNavn(rs.getString(pt_name));
						rekvRsRow.getModalitet().setModalitetNavn(rs.getString(mod_name));
						rekvRsRow.getPatient().setStamafdeling(rs.getString(stam_afdeling));
						rekvRsRow.setAfsendtDato(rs.getTimestamp(afs_dato));
						rekvRsRow.setStatus(_Rekvisition_Statuss[ rs.getShort( rekv_status) ]);
						rekvRsRow.setRekvisitionId(rs.getInt(rekv_id));
						rekv.add(rekvRsRow);
						
						//to get rekvisition with all attributes
//						rekvRsRow = fetch(rs);
//						rekvRsRow.setPatient(ptDao.fetch(rs));
//						rekvRsRow.setModalitet(modDao.fetch(rs));
//						rekvRsRow.setUndersoegelsesType(undDao.fetch(rs));
//						rekv.add(rekvRsRow);
//						rekv.add(findByPrimaryKey(rs.getInt("rekvisition_id")));
					}
				} catch (SQLException e) {
					System.err.println(e.getSQLState());
				}
						
				RekvisitionExtended[] ret = new RekvisitionExtended[rekv.size()];
				for(int i = 0; i < rekv.size(); i++){
					ret[i] = rekv.get(i);
				}
				
				
				// to add all attributes
//		return addObjectsToRekvisition(ret);
				return ret;
		
		
	}
	private RekvisitionExtended[] addObjectsToRekvisition(RekvisitionExtended[] rekv){
		if(rekv == null || rekv.length <= 0) return null;
		MRKontrolskemaDao mrDao = new MRKontrolskemaDaoImpl(conn);
		PETCTKontrolskemaDao petctDao = new PETCTKontrolskemaDaoImpl(conn);
		CtKontrastKontrolskemaDao ctKontrDao = new CtKontrastKontrolskemaDaoImpl(conn);
		BrugerDao brugerDao = new BrugerDaoImpl(conn);
	
		for(int i = 0; i < rekv.length; i++){
			rekv[i].setMrMkontroKontrolskema(mrDao.findByPrimaryKey(rekv[i].getMRKontrolskemaId() != null ? rekv[i].getMRKontrolskemaId() : -1));
			rekv[i].setPetctKontrolskema(petctDao.findByPrimaryKey(rekv[i].getPETCTKontrolskemaId() != null ? rekv[i].getPETCTKontrolskemaId() : -1));
			rekv[i].setCtKontrastKontrolskema(ctKontrDao.findByPrimaryKey(rekv[i].getCTKontrastKontrolskemaId() != null ? rekv[i].getCTKontrastKontrolskemaId() : -1));
			rekv[i].setRekvirent(brugerDao.findByPrimaryKey(rekv[i].getRekvirentId() != null ? rekv[i].getRekvirentId() : -1));
			rekv[i].setVisitator(brugerDao.findByPrimaryKey(rekv[i].getVisitatorId() != null ? rekv[i].getVisitatorId() : -1));
		}
		return rekv;
	}
}
