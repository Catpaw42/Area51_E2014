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
import database.dto.Rekvisition;
import database.dto.RekvisitionExtended;

public class RekvisitionDaoImplExt extends RekvisitionDaoImpl {
	
	private static final String ADV_SEARCH = "SELECT * FROM rekvisition NATURAL JOIN patient NATURAL JOIN modalitet NATURAL JOIN undersoegelses_type WHERE ";
	private static final String GET_ALL_REKV =  "SELECT * FROM rekvisition NATURAL JOIN patient NATURAL JOIN modalitet NATURAL JOIN undersoegelses_type";

	public RekvisitionDaoImplExt(Connection conn) {
		super(conn);
	}
	
	//TODO skal flyttes ind i sin superclass senere
	public Rekvisition[] findDynamic( String cond, int offset, int count, Object... params ) {
		for(int i = 0; i < params.length; i++){
			if(params[i].getClass().isEnum()){
				params[i] = ((Enum<?>) params[i]).ordinal() +1;
			}
		}
		return findManyArray( cond, offset, count, params);
	}

	public Rekvisition[] findByAdvSearch(String cpr, String name, String modality, Rekvisition.Status status,Timestamp date, String department){ 	
		Timestamp lowBound = null;
		Timestamp upperBound = null;
		System.out.println("##############ARGS#############");
		System.out.println("cpr: "+ cpr);
		System.out.println("name: " + name);
		System.out.println("modality: " + modality);
		System.out.println("status: " + status);
		System.out.println("date: " + date);
		System.out.println("department: " + department);
		System.out.println("###############################");
		PreparedStatement stmt = null;
		ResultSet rs = null;
		ArrayList<Rekvisition> rekv = new ArrayList<>();
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
			System.out.println("########LOWER BOUND############");
			System.out.println(lowBound);
			System.out.println("########UPPER BOUND############");
			System.out.println(upperBound);
			query = query + (first ? " AND  " : " ") + "afsendt_dato>? AND afsendt_dato<? ";
			first = true;
		}
		if(department != null && !department.equals("") && !department.equalsIgnoreCase("alle")){
			query = query + (first ? " AND " : " ") + "stamafdeling=? ";
			first = true;
		}
		
		if(!first){
			query = GET_ALL_REKV;
		
		System.out.println("GET ALL");
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
					System.out.println("##################END##################");
					System.out.println(stmt.toString());
					rs = stmt.executeQuery();
					
					while (rs.next()){
						System.out.println("##### TEST: " + rs.getInt("rekvisition_id"));
						rekv.add(findByPrimaryKey(rs.getInt("rekvisition_id")));
						System.out.println(rekv.size());
					}
				} catch (SQLException e) {
					System.err.println(e.getSQLState());
				}
				
				MRKontrolskemaDao mrDao = new MRKontrolskemaDaoImpl(conn);
				PETCTKontrolskemaDao petctDao = new PETCTKontrolskemaDaoImpl(conn);
				CtKontrastKontrolskemaDao ctKontrDao = new CtKontrastKontrolskemaDaoImpl(conn);
				BrugerDao brugerDao = new BrugerDaoImpl(conn);
				PatientDao ptDao = new PatientDaoImpl(conn);
				ModalitetDao modDao = new ModalitetDaoImpl(conn);
				UndersoegelsesTypeDao undDao = new UndersoegelsesTypeDaoImpl(conn);
				
				Rekvisition[] ret = new Rekvisition[rekv.size()];
				for(int i = 0; i < rekv.size(); i++){
					rekv.get(i).setMrMkontroKontrolskema(mrDao.findByPrimaryKey(rekv.get(i).getMRKontrolskemaId() != null ? rekv.get(i).getMRKontrolskemaId() : -1));
					rekv.get(i).setPetctKontrolskema(petctDao.findByPrimaryKey(rekv.get(i).getPETCTKontrolskemaId() != null ? rekv.get(i).getPETCTKontrolskemaId() : -1));
					rekv.get(i).setCtKontrastKontrolskema(ctKontrDao.findByPrimaryKey(rekv.get(i).getCTKontrastKontrolskemaId() != null ? rekv.get(i).getCTKontrastKontrolskemaId() : -1));
					rekv.get(i).setRekvirent(brugerDao.findByPrimaryKey(rekv.get(i).getRekvirentId() != null ? rekv.get(i).getRekvirentId() : -1));
					rekv.get(i).setVisitator(brugerDao.findByPrimaryKey(rekv.get(i).getVisitatorId() != null ? rekv.get(i).getVisitatorId() : -1));
					rekv.get(i).setPatient(ptDao.findByPrimaryKey(rekv.get(i).getPatientId() != null ? rekv.get(i).getPatientId() : -1));
					rekv.get(i).setModalitet(modDao.findByPrimaryKey(undDao.findByPrimaryKey(rekv.get(i).getUndersoegelsesTypeId() != null ? rekv.get(i).getUndersoegelsesTypeId() : -1).getModalitetId()));
					ret[i] = rekv.get(i);
				}
				
				
				
		return ret;
	}
}
